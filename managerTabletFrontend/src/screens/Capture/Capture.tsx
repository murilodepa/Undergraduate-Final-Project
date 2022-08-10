import React, { useState, useEffect, useRef, useContext } from "react";
import { Text, View, TouchableOpacity, Image, Modal } from "react-native";
import { Camera } from "expo-camera";

import {
  Container,
  CameraButton,
  Containerfooter,
  ContainerHeaderCapture,
  TextHeaderCapture,
  ContainerCamera,
  CameraEdge,
  RevertButton
} from "./styles";

import ModalCapturedPicture from "../../Components/Modals/CapturedPicture/CapturedPicture";
import { SendImageClientService } from "../../services/SendImageClientService/SendImageClientService";
import { SendImageSellerService } from "../../services/SendImageSellerService/SendImageSellerService";
import { ISellerImageAndName } from '../../services/SendImageSellerService/SendImageSellerServiceInterface'
import { useGlobalContext } from "../../context/managerContext";
import { ISellerIdNameImageList } from "../../services/SendImageSellerService/SendImageSellerServiceInterface";
import { IClientIdNameImageList } from "../../services/SendImageClientService/SendImageClientServiceInterface";

const Capture = ({ navigation, route }: any) => {
  const camRef = useRef(null);
  const [type, setType] = useState(Camera.Constants.Type.back);
  const [hasPermission, setHasPermission] = useState(null);
  const [capturedPhotoURI, setCapturedPhotoURI] = useState(null);
  const [openPicture, setOpenPicture] = useState(false);
  const [capturedPicturesNumber, setCapturedPicturesNumber] = useState(0);
  const [capturedPitureExpression, setCapturedPitureExpression] = useState("Sorrindo");
  const { setName, setProfileImage, setResultSellerData, setResultClientData } = useGlobalContext();
  const maxPhotos = 6;

  const closePicture = () => {
    setOpenPicture(false);
  };

  async function handleOrderList(list: any, isSeller: boolean) {

    list.sort((x, y): any =>
      x.name > y.name ? 1 : y.name > x.name ? -1 : 0
    );

    if (isSeller) {
      console.log("Seller - Sort result data list by names");
      setResultSellerData(list);
    } else {
      console.log("Client - Sort result data list by names");
      setResultClientData(list);
    }
  };

  async function getSellerData() {
    let response: ISellerIdNameImageList;
    try {
      response = await new SendImageSellerService().getSellerIdNameImage();
    } catch (error) {
      console.error("Error to get seller date", error);
    }
    handleOrderList(response, true);
  };

  async function getClientData() {
    let response: IClientIdNameImageList;
    try {
      response = await new SendImageClientService().getClientIdNameImage();
    } catch (error) {
      console.error("Error to get client date", error);
    }
    handleOrderList(response, false);
  };

  async function getProfileImage() {
    let response: ISellerImageAndName;
    try {
      response = await new SendImageSellerService().getSellerImageAndName(1);
    } catch (error) {
      console.error("Error to get manager date", error);
    }
    if (response.profileImage != null && response.profileImage != undefined) {
      setProfileImage(response.profileImage);
      setName(response.name);
    }
    console.log("Updating name and photo...");
  };

  const closePictureAndCounter = async () => {
    await sendImage(capturedPhotoURI);
    setOpenPicture(false);
    setCapturedPicturesNumber(capturedPicturesNumber + 1);

    /* When is defined 25 photos
        if (capturedPicturesNumber > 3) {
          if (capturedPicturesNumber <= 8) {
            if (capturedPicturesNumber == 4) {
              getProfileImage();
              if (route.params.paramKey == "seller") {
                getSellerData();
              } else {
                getClientData();
              }
            }
            setCapturedPitureExpression("Sério");
          } else if (capturedPicturesNumber <= 13) {
            setCapturedPitureExpression("Lado esquerdo do rosto");
          } else if (capturedPicturesNumber <= 18) {
            setCapturedPitureExpression("Lado direito do rosto");
          } else if (capturedPicturesNumber > 18) {
            setCapturedPitureExpression("Em baixo e em cima");
            if (capturedPicturesNumber >= (maxPhotos - 1)) {
              navigation.navigate("Menu");
            }
          }
        }
    */

    if (capturedPicturesNumber == 2) {
      setCapturedPitureExpression("Sério");
      if (route.params.paramKey == "seller") {
        getSellerData();
      } else {
        getClientData();
      }
    } else if (capturedPicturesNumber == 3) {
      setCapturedPitureExpression("Lateral do rosto");
    } else if (capturedPicturesNumber >= 5) { // capture 6 photos of the user  
      getProfileImage();
      getSellerData();
      getClientData();
      console.log("User registered successfully - going to Menu");
      navigation.navigate("Menu");
    };
  }


  useEffect(() => {
    (async () => {
      const { status } = await Camera.requestCameraPermissionsAsync();
      setHasPermission(status === "granted");
    })();
  }, []);

  if (hasPermission === null) {
    return <View />;
  }

  if (hasPermission === false) {
    return <Text> Acesso Negado! </Text>;
  }

  async function sendImage(image: any) {
    try {
      var result: any;
      if (route.params.paramKey == "seller") {
        result = await new SendImageSellerService().insertImage(image);
      } else {
        result = await new SendImageClientService().insertImage(image);
      }
    } catch (error) {
      console.error(error);
    }
  }

  async function takePicture() {
    if (camRef) {
      const data = await camRef.current.takePictureAsync();
      setCapturedPhotoURI(data.uri);
      setOpenPicture(true);
      console.log(data);
    }
  }

  return (
    <Container>
      <ContainerHeaderCapture>
        <TextHeaderCapture style={{ textAlignVertical: "center" }}>
          {`${capturedPicturesNumber} / ${maxPhotos} \n ${capturedPitureExpression}`}
        </TextHeaderCapture>
      </ContainerHeaderCapture>
      <ContainerCamera>
        <CameraEdge>
          <Camera
            style={{
              flex: 1,
              borderRadius: 20,
              borderColor: "#FFFFFF"
            }}
            type={type}
            ref={camRef}
          />
        </CameraEdge>
      </ContainerCamera>

      <Containerfooter>
        <TouchableOpacity onPress={() => { setType(type === Camera.Constants.Type.back ? Camera.Constants.Type.front : Camera.Constants.Type.back) }}>
          <RevertButton source={require("../../assets/revert-camera.png")} />
        </TouchableOpacity>

        <TouchableOpacity onPress={takePicture}>
          <CameraButton source={require("../../assets/camera.png")} />
        </TouchableOpacity>
      </Containerfooter>

      {capturedPhotoURI && (
        <ModalCapturedPicture
          openPicture={openPicture}
          closePicture={closePicture}
          closePictureAndCounter={closePictureAndCounter}
          capturedPhotoURI={capturedPhotoURI}
        />
      )}
    </Container>
  );
};

export default Capture;
