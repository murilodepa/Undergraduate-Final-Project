import React, { useState } from "react";
import { View, TouchableOpacity, Modal } from "react-native";
import { CheckBox } from "react-native-elements";
import { IGiftPersonData, INewPurchaseGiftData } from "../../../services/PurchaseService/PurchaseServiceInterface";
import { Picker } from '@react-native-picker/picker';

import {
    Container,
    ButtonImage,
    ContainerModal,
    ContainerDescription,
    ContainerCheckBox,
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
    ContainerPicker,
    DescriptionButton,
    Button,
    CharacteristicInput,
    PlaceHolder,
    ContainerSuggestion,
    SuggestionText,
    ViewTextInput,
    RowSuggestion,
    LastItem1CheckBox,
    LastItem2CheckBox,
    LastItem3CheckBox
} from "./styles";

export default function NewClientGiftModal({
    openAlreadyBoughtGiftModal,
    closeNewClientGiftModal,
}) {

    const [personData, setPersonData] = useState<IGiftPersonData>({
        gender: '', 
        age: '', 
        size: '', 
        selectedSummer: false, 
        selectedWinter: false, 
        selectedSocial: false, 
        selectedCasual: false, 
        selectedPajama: false, 
        selectedFitness: false
    });

    /* List to Picker */
    const genderList = ['', 'Feminino', 'Masculino', 'Outros'];
    const ageList = ['', 'Criança', 'Jovem', 'Adulto', 'Idoso'];
    const sizeList = ['', 'PP', 'P', 'M', 'G', 'GG', 'XGG'];


    const [suggestion, setSuggestion] = useState<string>("");
    const eventSuggestion = async () => {
        console.log("Event - Suggestion to new client - YES", personData);
        setSuggestion("Short")
    };

    const closeAndClearModal = async () => {
        setPersonData({
            gender: '', 
            age: '', 
            size: '', 
            selectedSummer: false, 
            selectedWinter: false, 
            selectedSocial: false, 
            selectedCasual: false, 
            selectedPajama: false, 
            selectedFitness: false
        });
        setSuggestion("");
        closeNewClientGiftModal();
    }

    return (
        <Modal
            animationType="slide"
            transparent={true}
            visible={openAlreadyBoughtGiftModal}
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
                            <ViewTextInput>
                                <Question> Gênero:      </Question>
                                <ContainerPicker>
                                    <Picker
                                        selectedValue={personData.gender}
                                        onValueChange={(itemValue) =>
                                            setPersonData({ ...personData, gender: itemValue })
                                        }
                                    >
                                        {genderList.map((gender, key) => {
                                            return (
                                                <Picker.Item
                                                    style={{ fontSize: 20 }}
                                                    label={gender}
                                                    value={gender}
                                                    key={key}
                                                />
                                            );
                                        })}
                                    </Picker>
                                </ContainerPicker>
                            </ViewTextInput>
                            <ViewTextInput>
                                <Question> Idade:         </Question>
                                <ContainerPicker style={{ width: 140 }}>
                                    <Picker
                                        selectedValue={personData.age}
                                        onValueChange={(itemValue) =>
                                            setPersonData({ ...personData, age: itemValue })
                                        }
                                    >
                                        {ageList.map((age, key) => {
                                            return (
                                                <Picker.Item
                                                    style={{ fontSize: 20 }}
                                                    label={age}
                                                    value={age}
                                                    key={key}
                                                />
                                            );
                                        })}
                                    </Picker>
                                </ContainerPicker>
                            </ViewTextInput>
                            <ViewTextInput>
                                <Question> Tamanho:  </Question>
                                <ContainerPicker style={{ width: 110 }}>
                                    <Picker
                                        selectedValue={personData.size}
                                        onValueChange={(itemValue) =>
                                            setPersonData({ ...personData, size: itemValue })
                                        }
                                    >
                                        {sizeList.map((size, key) => {
                                            return (
                                                <Picker.Item
                                                    style={{ fontSize: 20 }}
                                                    label={size}
                                                    value={size}
                                                    key={key}
                                                />
                                            );
                                        })}
                                    </Picker>
                                </ContainerPicker>
                            </ViewTextInput>
                        </ProfileDescription>

                        <ContainerCheckBox>
                            <RowSuggestion>
                                <CheckBox
                                    title="Verão"
                                    checkedColor="blue"
                                    uncheckedColor="gray"
                                    checked={personData.selectedSummer}
                                    onPress={() => setPersonData({ ...personData, selectedSummer: !personData.selectedSummer})}
                                />
                                <LastItem1CheckBox>
                                    <CheckBox
                                        title="Inverno"
                                        checkedColor="blue"
                                        uncheckedColor="gray"
                                        checked={personData.selectedWinter}
                                        onPress={() => setPersonData({ ...personData, selectedWinter: !personData.selectedWinter})}
                                    />
                                </LastItem1CheckBox>
                            </RowSuggestion>
                            <RowSuggestion>
                                <CheckBox
                                    title="Social"
                                    checkedColor="blue"
                                    uncheckedColor="gray"
                                    checked={personData.selectedSocial}
                                    onPress={() => setPersonData({ ...personData, selectedSocial: !personData.selectedSocial})}
                                />
                                <LastItem2CheckBox>
                                    <CheckBox
                                        title="Casual"
                                        checkedColor="blue"
                                        uncheckedColor="gray"
                                        checked={personData.selectedCasual}
                                        onPress={() => setPersonData({ ...personData, selectedCasual: !personData.selectedCasual})}
                                    />
                                </LastItem2CheckBox>
                            </RowSuggestion>
                            <RowSuggestion>
                                <CheckBox
                                    title="Esportiva"
                                    checkedColor="blue"
                                    uncheckedColor="gray"
                                    checked={personData.selectedFitness}
                                    onPress={() => setPersonData({ ...personData, selectedFitness: !personData.selectedFitness})}
                                />
                                <LastItem3CheckBox>
                                    <CheckBox
                                        title="Pijama"
                                        checkedColor="blue"
                                        uncheckedColor="gray"
                                        checked={personData.selectedPajama}
                                        onPress={() => setPersonData({ ...personData, selectedPajama: !personData.selectedPajama})}
                                    />
                                </LastItem3CheckBox>
                            </RowSuggestion>
                        </ContainerCheckBox>

                        <ContainerSuggestion>
                            <Description> Sugestão: </Description>
                            <SuggestionText>
                                {suggestion && suggestion}
                            </SuggestionText>
                        </ContainerSuggestion>

                        <Button onPress={() => eventSuggestion()}>
                            <DescriptionButton>Requisitar sugestão</DescriptionButton>
                        </Button>
                    </ContainerDescription>
                </ContainerModal>
            </Container>
        </Modal>
    );
}
