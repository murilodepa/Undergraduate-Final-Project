import React from "react";
import { TouchableOpacity, Modal, ScrollView } from "react-native";
import { ContainerScrollView, DescriptionItens, LineItens } from "../../../screens/ClientInformations/styles";

import {
    Container,
    ButtonImage,
    ContainerModal,
    ContainerDescription,
    Line,
    ContainerProfileImage,
    EditCloseProfileButton,
    ContainerButtons,
    ContainerButtonsLeave,
    LeaveText,
    ProfileDescription,
    Question,
    Description,
    ButtonNoImage,
    ContainerButtonsOptions
} from "./styles";

export default function ProductSuggestionsModal({
    openGiftModal,
    closeGiftModal,
    closeGiftModalAndNo,
    closeGiftModalAndYes
}) {

    return (
        <Modal
            animationType="slide"
            transparent={true}
            visible={openGiftModal}
        >
            <Container>
                <ContainerModal>
                    <ContainerButtons>
                        <TouchableOpacity onPress={() => closeGiftModal()}>
                            <EditCloseProfileButton
                                source={require("../../../assets/window-close.png")}
                            />
                        </TouchableOpacity>
                    </ContainerButtons>
                    <ContainerDescription>
                        <ProfileDescription>
                            <Question>
                                Já foi realizada compra de presente para essa pessoa na loja?
                            </Question>
                        </ProfileDescription>
                    </ContainerDescription>

                    <ScrollView style={{ marginTop: "6%", maxHeight: "30%", width: "70%" }}>
        <ContainerScrollView>
        </ContainerScrollView>
      </ScrollView>
      
                </ContainerModal>
            </Container>
        </Modal>
    );
}
