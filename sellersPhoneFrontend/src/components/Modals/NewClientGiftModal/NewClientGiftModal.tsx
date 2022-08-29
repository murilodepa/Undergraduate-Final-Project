import React, { useEffect, useState } from "react";
import { TouchableOpacity, Modal, ScrollView } from "react-native";
import { PurchaseService } from "../../../services/PurchaseService/PurchaseService";
import { INewPurchaseGiftData, IPurchaseList, IPurchasePeopleGiftList } from "../../../services/PurchaseService/PurchaseServiceInterface";

import {
    Container,
    ButtonImage,
    ItemButton,
    ContainerModal,
    ContainerDescription,
    Line,
    ContainerProfileImage,
    EditCloseProfileButton,
    ContainerScrollView,
    ContainerButtons,
    ContainerButtonsLeave,
    LeaveText,
    ProfileDescription,
    Question,
    Description,
    ButtonNoImage,
    ContainerButtonsOptions,
    DescriptionButton,
    Button,
    CharacteristicInput,
    PlaceHolder,
    ContainerSuggestion,
    SuggestionText,
    DescriptionItens,
    PurchasesContainer,
    LineItens
} from "./styles";

export default function NewClientGiftModal({
    openNewClientGiftModal,
    closeNewClientGiftModal,
    id,
    list
}) {

    const [suggestion, setSuggestion] = useState<string>("");
    const [purchaseList, setPurchaseList] = useState<IPurchaseList>();
    const [itemSelected, setItemSelected]=useState(-1);

    const eventItemSelected = async (index: any, name: string, kinship: string) => {
        console.log("Event - Item Selected: ", index);

        setItemSelected(index);

        let response: IPurchaseList;
        try {
            response = await new PurchaseService().getPurchaseGift(id, name, kinship);
        } catch (error) {
            console.error("Error to get purchase list of gifts", error);
        }
        console.log("response: ", response)

        if (response != null && response != undefined) {
            setPurchaseList(response);
        } 

        let suggestion: string;
        try {
            suggestion = await new PurchaseService().getSuggestionGift(id, name, kinship);
        } catch (error) {
            console.error("Error to get suggestion of gift", error);
        }
        console.log("suggestion: ", suggestion)

        if (suggestion != null && suggestion != undefined && suggestion != '') {
            setSuggestion(suggestion);
        } 
    }

    const closeAndClearModal = async () => {
        setPurchaseList(null);
        setSuggestion("");
        setItemSelected(-1);
        closeNewClientGiftModal();
    }

    return (
        <Modal
            animationType="slide"
            transparent={true}
            visible={openNewClientGiftModal}
        >
            <Container>
                <ContainerModal>
                    <ContainerButtons>
                        <TouchableOpacity onPress={() => closeAndClearModal()}>
                            <EditCloseProfileButton
                                source={require("../../../assets/window-close.png")}
                            />
                        </TouchableOpacity>
                    </ContainerButtons>
                    <ContainerDescription>
                        <ProfileDescription>
                            <ScrollView style={{ marginTop: "5%", height: "90%", maxHeight: "90%", width: "90%" }}>
                                <ContainerScrollView>
                                    {list && list.map((value: any, index: number) => (
                                        <>
                                            <ItemButton key={index}  style={{backgroundColor: itemSelected == index ? '#006BCE' : '#0085FF'}}                                            
                                                        onPress={() => eventItemSelected(index, value.name, value.kinship)}>
                                                <DescriptionItens  key={`${index}`}>
                                                    {`${value.name}  (${value.kinship})`}
                                                </DescriptionItens>
                                            </ItemButton>
                                        </>
                                    ))}
                                </ContainerScrollView>
                            </ScrollView>
                        </ProfileDescription>

                        <PurchasesContainer>
                            <ScrollView style={{ marginTop: "5%", height: "90%", maxHeight: "90%", width: "90%" }}>
                                <ContainerScrollView>
                                    {purchaseList && purchaseList.map((value: any, index: number) => (
                                        <>
                                                <DescriptionItens key={`${index}`}>
                                                    {`${value.category}  (${value.size}) – ${value.date}`}
                                                </DescriptionItens>
                                                <LineItens />
                                        </>
                                    ))}
                                </ContainerScrollView>
                            </ScrollView>
                        </PurchasesContainer>                        
                        <ContainerSuggestion>
                            <Description>Sugestão: </Description>
                            <SuggestionText>
                                {suggestion && suggestion}
                            </SuggestionText>
                        </ContainerSuggestion>
                    </ContainerDescription>
                </ContainerModal>
            </Container>
        </Modal>
    );
}
