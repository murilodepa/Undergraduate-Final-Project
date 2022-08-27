import React from "react"; GiftModal
import { TouchableOpacity, Modal } from "react-native";

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
    Name,
    Description,
} from "./styles";

export default function GiftModal({
    openGiftModal,
    closeGiftModal
}) {

    const eventYes = async () => {
        console.log("Event - Yes");
    };

    const eventNo = async () => {
        console.log("Event - No");
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



                        </ProfileDescription>

                        <ContainerButtons>
                            <TouchableOpacity onPress={() => eventYes()}>
                                <ButtonImage source={require("../../../assets/yes.png")} />
                            </TouchableOpacity>

                            <TouchableOpacity onPress={() => eventNo()}>
                                <ButtonImage source={require("../../../assets/no.png")} />
                            </TouchableOpacity>
                        </ContainerButtons>


                    </ContainerDescription>
                </ContainerModal>
            </Container>
        </Modal>
    );
}
