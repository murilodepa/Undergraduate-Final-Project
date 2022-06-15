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

export default function ModalClientProfileSettings({
  openClientProfileSettings,
  closeClientProfileSettings,
  EditClientProfile,
  clientData,
  age,
  profileImage
}) {

  return (
    <Modal
      animationType="slide"
      transparent={true}
      visible={openClientProfileSettings}
    >
      <Container>
        <ContainerModal>
          <ContainerButtons>
            <TouchableOpacity onPress={() => EditClientProfile()}>
              <EditCloseProfileButton
                source={require("../../../assets/profile-edit.png")}
              />
            </TouchableOpacity>

            <TouchableOpacity onPress={() => closeClientProfileSettings()}>
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
              {clientData && 
                `${clientData.name}`}           
                     </Name>
              <Line />
              <Description>
                {clientData && `${age} anos \n ${clientData.gender} \n ${clientData.purchaseSuggestion}`}
              </Description>
            </ProfileDescription>
          </ContainerDescription>
        </ContainerModal>
      </Container>
    </Modal>
  );
}
