import React, { useState, useEffect, useRef } from "react";
import { Text, View, TouchableOpacity, Image, Modal } from "react-native";
import { Camera } from "expo-camera";
import axios from "axios";

import {
  Container,
  CameraButton,
  Containerfooter,
  ContainerHeaderCapture,
  TextHeaderCapture,
  ContainerCamera,
  CameraEdge,
} from "./styles";

import ModalCapturedPicture from "../../Components/Modals/CapturedPicture/CapturedPicture";
import { SendImageClientService } from "../../services/SendImageClientService/SendImageClientService";
import { SendImageSellerService } from "../../services/SendImageSellerService/SendImageSellerService"

const Capture = ({ navigation, route }: any) => {
  const camRef = useRef(null);
  const [hasPermission, setHasPermission] = useState(null);
  const [capturedPhotoURI, setCapturedPhotoURI] = useState(null);
  const [openPicture, setOpenPicture] = useState(false);
  const [capturedPicturesNumber, setCapturedPicturesNumber] = useState(0);
  const [capturedPitureExpression, setCapturedPitureExpression] = useState("Sorrindo");
  const maxPhotos = 25;

  const closePicture = () => {
    setOpenPicture(false);
  };

  const closePictureAndCounter = async () => {
    await sendImage(capturedPhotoURI);
    setOpenPicture(false);
    setCapturedPicturesNumber(capturedPicturesNumber + 1);

    if(capturedPicturesNumber >= (maxPhotos-1)) {
      navigation.navigate("Menu");
    }

    if (capturedPicturesNumber > 3) {
      if (capturedPicturesNumber <= 8) {
        setCapturedPitureExpression("Sério");
      } else if (capturedPicturesNumber <= 13) {
        setCapturedPitureExpression("Lado esquerdo do rosto");
      } else if (capturedPicturesNumber <= 18) {
        setCapturedPitureExpression("Lado direito do rosto");
      } else if (capturedPicturesNumber > 18) {
        setCapturedPitureExpression("Em baixo e em cima");
      }
    }
  };

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
      if(route.params.paramKey == "seller") { // Utilizar mesma tela para vendedor e funcionários
        //result = await new SendImageSellerService().insertImage(image);
      } else {
        //result = await new SendImageClientService().insertImage(image);
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
              borderColor: "#FFFFFF"}}
            type={Camera.Constants.Type.back}
            ref={camRef}
          />
        </CameraEdge>
      </ContainerCamera>

      <Containerfooter>
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
