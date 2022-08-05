import React from "react";
import { TouchableOpacity, Modal } from "react-native";
import { useGlobalContext } from "../../context/SellerContext";

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
  
  const { name, gender, birth, sector, available, attendances, email } = useGlobalContext();

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
                {name}
              </Name>
              <Line />
              <Description>
                {`${email} \n ${birth} \n ${gender} \n ${sector} \n ${attendances} Atendimentos `}
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
