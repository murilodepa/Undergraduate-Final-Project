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
  ContainerButtonsLeave,
  LeaveText,
  ProfileDescription,
  Name,
  Description,
} from "./styles";

export default function ModalSellerInformations({
  openSellerInformations,
  closeSellerInformations,
  sellerInformations,
  closeAndLeave,
}) {
  return (
    <Modal
      animationType="slide"
      transparent={true}
      visible={openSellerInformations}
    >
      <Container>
        <ContainerModal>
          <ContainerButtons>
            <TouchableOpacity onPress={() => closeSellerInformations()}>
              <EditCloseProfileButton
                source={require("../../assets/window-close.png")}
              />
            </TouchableOpacity>
          </ContainerButtons>

          <ContainerProfileImage>
            <ProfileImage source={require("../../assets/Profile.png")} />
          </ContainerProfileImage>

          <ContainerDescription>
            <ProfileDescription>
              <Name>
                {/* {sellerInformations && 
                `${sellerInformations.name}`}            */}
                Murilo Araujo
              </Name>
              <Line />
              <Description>
                {/* {sellerInformations && `${sellerInformations.email} \n ${sellerInformations.birth} \n ${sellerInformations.gender} \n ${sellerInformations.sector} \n ${sellerInformations.attendances} Atendimentos `} */}
                {`murilodepa@gmail.com \n 30 anos \n Masculino \n Calça \n 123 Atendimentos `}
              </Description>
            </ProfileDescription>
            <ContainerButtonsLeave>
              <TouchableOpacity onPress={() => closeAndLeave()}>
                <LeaveText>Sair</LeaveText>
              </TouchableOpacity>
            </ContainerButtonsLeave>
          </ContainerDescription>
        </ContainerModal>
      </Container>
    </Modal>
  );
}
