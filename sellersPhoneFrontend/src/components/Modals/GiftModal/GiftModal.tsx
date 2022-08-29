import React, { useState } from "react";
import { TouchableOpacity, Modal } from "react-native";
import { PurchaseService } from "../../../services/PurchaseService/PurchaseService";

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
    ContainerButtonsOptions,
    InvalidText
} from "./styles";

export default function GiftModal({
    openGiftModal,
    closeGiftModal,
    closeGiftModalAndNo,
    closeGiftModalAndYes,
    id
}) {

    const [hideTextInvalidSuggestion, setHideTextInvalidSuggestion] = useState(false);

    const eventYes = async () => {
        console.log("Event - Yes - GiftModal");

        let response: any;
        try {
            response = await new PurchaseService().getPeopleGift(id);
        } catch (error) {
            console.error("Error to update status", error);
        }
        console.log("response: ", response)

        if (response != null && response != '') {
            closeGiftModalAndYes(response)
        } else {
            setHideTextInvalidSuggestion(true)
        }

    };

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

                        <InvalidText>
                            {" "}
                            {hideTextInvalidSuggestion ? "Nenhuma compra de presente encontrada no sistema..." : null}{" "}
                        </InvalidText>

                        <ContainerButtonsOptions>
                            <TouchableOpacity onPress={() => closeGiftModalAndNo()}>
                                <ButtonNoImage source={require("../../../assets/no.png")} />
                            </TouchableOpacity>

                            <TouchableOpacity onPress={() => eventYes()}>
                                <ButtonImage source={require("../../../assets/yes.png")} />
                            </TouchableOpacity>
                        </ContainerButtonsOptions>
                    </ContainerDescription>
                </ContainerModal>
            </Container>
        </Modal>
    );
}
