import React from "react";
import { TouchableOpacity, Modal } from "react-native";

import {
  Containerfooter,
  NotCaptureButton,
  CaptureButton,
  ContainerHeaderSave,
  ContainerCapturedImage,
  TextHeaderSave,
  CapturedImage,
} from "./styles";

export default function ModalCapturedPicture({
  openPicture,
  closePicture,
  closePictureAndCounter,
  capturedPhotoURI,
}) {
  return (
    <Modal animationType="slide" transparent={false} visible={openPicture}>
      <ContainerHeaderSave>
        <TextHeaderSave style={{ textAlignVertical: "center" }}>
          Deseja armazenar essa imagem?
        </TextHeaderSave>
      </ContainerHeaderSave>
      
      <ContainerCapturedImage>
        <CapturedImage source={{ uri: capturedPhotoURI }} />
      </ContainerCapturedImage>

      <Containerfooter>
        <TouchableOpacity
          onPress={() => {
            closePictureAndCounter();
          }}
        >
          <CaptureButton source={require("../../../assets/capture.png")} />
        </TouchableOpacity>

        <TouchableOpacity
          onPress={() => {
            closePicture();
          }}
        >
          <NotCaptureButton source={require("../../../assets/not_capture.png")} />
        </TouchableOpacity>
      </Containerfooter>
    </Modal>
  );
}
