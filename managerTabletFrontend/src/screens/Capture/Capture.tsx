import React, { useState, useEffect, useRef } from "react";
import { Text, View, TouchableOpacity, Image, Modal } from "react-native";
import { Camera } from "expo-camera";
import {
  Container,
  CameraButton,
  Containerfooter,
  ContainerHeaderCapture,
  TextHeaderCapture,
} from "./styles";
import ModalCapturedPicture from "../../Components/Modals/ModalCapturedPicture/ModalCapturedPicture";

export default function App() {
  const camRef = useRef(null);
  const [hasPermission, setHasPermission] = useState(null);
  const [capturedPhotoURI, setCapturedPhotoURI] = useState(null);
  const [openPicture, setOpenPicture] = useState(false);
  const [capturedPicturesNumber, setCapturedPicturesNumber] = useState(1);
  const [capturedPitureExpression, setCapturedPitureExpression] =
    useState("Sorrindo");

  const closePicture = () => {
    setOpenPicture(false);
  };

  const closePictureAndCounter = () => {
    setOpenPicture(false);
    setCapturedPicturesNumber(capturedPicturesNumber + 1);

    console.log("capturedPicturesNumber: " + capturedPicturesNumber);

    if (capturedPicturesNumber > 4) {
      if (capturedPicturesNumber <= 9) {
        setCapturedPitureExpression("Sério");
      } else if (capturedPicturesNumber <= 14) {
        setCapturedPitureExpression("Lado esquerdo do rosto");
      } else if (capturedPicturesNumber <= 19) {
        setCapturedPitureExpression("Lado direito do rosto");
      } else if (capturedPicturesNumber >= 19) {
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
          {`${capturedPicturesNumber} / 25 \n ${capturedPitureExpression}`}
        </TextHeaderCapture>
      </ContainerHeaderCapture>

      <Camera
        style={{ flex: 1 }}
        type={Camera.Constants.Type.back}
        ref={camRef}
      />

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
}
