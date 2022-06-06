import React from 'react';
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
  Description,
} from "./styles";

export default function ModalProfileSettings({
  openProfileSettings,
  closeProfileSettings,
  EditProfile,
  managerData,
  age,
  profileImage
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
                source={{uri: profileImage}}
            />
          </ContainerProfileImage>

          <ContainerDescription>
            <ProfileDescription>
              <Name>  
              {managerData && 
                `${managerData.name}`}           
                     </Name>
              <Line />
              <Description>
                {managerData && `${managerData.email} \n ${age} anos \n ${managerData.gender} \n ${managerData.sector} \n ${managerData.attendances} Atendimentos `}
              </Description>
            </ProfileDescription>
          </ContainerDescription>
        </ContainerModal>
      </Container>
    </Modal>
  );
}
