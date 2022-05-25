import React from "react";
import { TouchableOpacity, Modal } from "react-native";

import {
  Container,
  ProfileImage,
  ContainerModal,
  ContainerDescription,
  Line,
  ContainerProfileImage,
  EditCloseProfileButton,
  ContainerButtons,
  ProfileDescription,
  Name,
  Description
} from "./styles";

export default function ModalCapturedPicture({
  openProfileSettings,
  closeProfileSettings,
  EditProfile,
  closeEditProfile,
}) {
  return (
    <Modal
      animationType="slide"
      transparent={true}
      visible={openProfileSettings}
    >
      <Container>
        <ContainerModal>
          <ContainerButtons>
            <TouchableOpacity onPress={() => EditProfile()}>
              <EditCloseProfileButton
                source={require("../../../assets/profile-edit.png")}
              />
            </TouchableOpacity>

            <TouchableOpacity onPress={() => closeProfileSettings()}>
              <EditCloseProfileButton
                source={require("../../../assets/window-close.png")}
              />
            </TouchableOpacity>
          </ContainerButtons>

          <ContainerProfileImage>
            <ProfileImage
              source={require("../../../assets/profile-image-setting.png")}
            />
          </ContainerProfileImage>

          <ContainerDescription>
              <ProfileDescription>

              <Name>
                Murilo Araujo
              </Name>
              <Line />
              <Description>
              {`Email \n Senha \n 42 anos \n Masculino \n Camiseta \ 100 Atendimentos`}
            </Description>

              </ProfileDescription>
          </ContainerDescription>
        </ContainerModal>
      </Container>
    </Modal>
  );
}
