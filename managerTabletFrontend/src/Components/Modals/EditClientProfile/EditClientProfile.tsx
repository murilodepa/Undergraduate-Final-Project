import React from "react";
import { TouchableOpacity, Modal, View } from "react-native";
import ClientInputs from "../../clientInputs/clientInputs";

import {
  Container,
  ContainerModal,
  ContainerButtons,
  EditCloseProfileButton
} from '../EditProfile/styles';

export default function ModalEditClientProfile({
  openEditClientProfile,
  closeEditClientProfile,
  placeholderInputs,
  profileImage,
  closeEditClientProfileAndBack
}) {
  return (
    <Modal
      animationType="slide"
      transparent={true}
      visible={openEditClientProfile}
    >
      <Container>
        <ContainerModal style={{height: 600}}>
          <ContainerButtons>
            <TouchableOpacity onPress={() => closeEditClientProfile()}>
              <EditCloseProfileButton
                source={require("../../../assets/window-close.png")}
              />
            </TouchableOpacity>
          </ContainerButtons>
          <ClientInputs buttonName="Editar" placeholderInputs={placeholderInputs} clientData={placeholderInputs} image={profileImage} closeEditClientProfileAndBack={closeEditClientProfileAndBack.bind()} />
        </ContainerModal>
      </Container>
    </Modal>
  );
}
