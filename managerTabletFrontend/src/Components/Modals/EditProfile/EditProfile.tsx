import React from "react";
import { TouchableOpacity, Modal, View } from "react-native";
import SellerInputs from "../../SellerInputs/SellerInputs";

import {
  Container,
  ContainerModal,
  ContainerButtons,
  EditCloseProfileButton,
} from "./styles";

export default function ModalEditProfile({
  openEditProfile,
  closeEditProfile,
  placeholderInputs
}) {
  return (
    <Modal
      animationType="slide"
      transparent={true}
      visible={openEditProfile}
    >
      <Container>
        <ContainerModal>
          <ContainerButtons>
            <TouchableOpacity  onPress={() => closeEditProfile()}>
              <EditCloseProfileButton
                source={require("../../../assets/window-close.png")}
              />
            </TouchableOpacity>
            </ContainerButtons>
            <SellerInputs buttonName="Editar" placeholderInputs={placeholderInputs} sellerData={placeholderInputs} />
        </ContainerModal>
      </Container>
    </Modal>
  );
}
