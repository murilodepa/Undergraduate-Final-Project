import React, { useState } from "react";
import { TouchableOpacity, Modal } from "react-native";
import { CheckBox } from "react-native-elements";
import { IGiftPersonData } from "../../../services/PurchaseService/PurchaseServiceInterface";
import { Picker } from '@react-native-picker/picker';

import {
    Container,
    ContainerModal,
    ContainerDescription,
    ContainerCheckBox,
    EditCloseProfileButton,
    ContainerButtons,
    ProfileDescription,
    Question,
    ContainerPicker,
    DescriptionButton,
    Button,
    ContainerSuggestion,
    SuggestionText,
    ViewTextInput,
    RowSuggestion
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
        selectedPatterned: false,
        selectedStripe: false
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
            selectedPatterned: false,
            selectedStripe: false
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
                                    containerStyle={{ backgroundColor: "#0085FF", borderWidth: 2, borderRadius: 12 }}
                                    textStyle={{
                                        fontSize: 14,
                                        fontWeight: "bold",
                                        color: "white"
                                    }}
                                    title="Verão"
                                    checkedColor="blue"
                                    uncheckedColor="white"
                                    checked={personData.selectedSummer}
                                    style={{ backgroundColor: 'red' }}
                                    onPress={() => { !personData.selectedWinter ? setPersonData({ ...personData, selectedSummer: !personData.selectedSummer }) : null }}
                                />
                                <CheckBox
                                    containerStyle={{ backgroundColor: "#0085FF", borderWidth: 2, borderRadius: 12, marginLeft: 24 }}
                                    textStyle={{
                                        fontSize: 14,
                                        fontWeight: "bold",
                                        color: "white"
                                    }}
                                    title="Inverno"
                                    checkedColor="blue"
                                    uncheckedColor="white"
                                    checked={personData.selectedWinter}
                                    onPress={() => { !personData.selectedSummer ? setPersonData({ ...personData, selectedWinter: !personData.selectedWinter }) : null }}
                                />
                            </RowSuggestion>
                            <RowSuggestion>
                                <CheckBox
                                    containerStyle={{ backgroundColor: "#0085FF", borderWidth: 2, borderRadius: 12 }}
                                    textStyle={{
                                        fontSize: 14,
                                        fontWeight: "bold",
                                        color: "white"
                                    }}
                                    title="Social"
                                    checkedColor="blue"
                                    uncheckedColor="white"
                                    checked={personData.selectedSocial}
                                    onPress={() => { !personData.selectedCasual ? setPersonData({ ...personData, selectedSocial: !personData.selectedSocial }) : null }}
                                />
                                <CheckBox
                                    containerStyle={{ backgroundColor: "#0085FF", borderWidth: 2, borderRadius: 12, marginLeft: 23 }}
                                    textStyle={{
                                        fontSize: 14,
                                        fontWeight: "bold",
                                        color: "white"
                                    }}
                                    title="Casual"
                                    checkedColor="blue"
                                    uncheckedColor="white"
                                    checked={personData.selectedCasual}
                                    onPress={() => { !personData.selectedSocial ? setPersonData({ ...personData, selectedCasual: !personData.selectedCasual }) : null }}
                                />
                            </RowSuggestion>
                            <RowSuggestion>
                                <CheckBox
                                    containerStyle={{ backgroundColor: "#0085FF", borderWidth: 2, borderRadius: 12 }}
                                    textStyle={{
                                        fontSize: 14,
                                        fontWeight: "bold",
                                        color: "white"
                                    }}
                                    title="Listra"
                                    checkedColor="blue"
                                    uncheckedColor="white"
                                    checked={personData.selectedStripe}
                                    onPress={() => { !personData.selectedPatterned ? setPersonData({ ...personData, selectedStripe: !personData.selectedStripe }) : null }}
                                />
                                <CheckBox
                                    containerStyle={{ backgroundColor: "#0085FF", borderWidth: 2, borderRadius: 12, marginLeft: 26 }}
                                    textStyle={{
                                        fontSize: 14,
                                        fontWeight: "bold",
                                        color: "white"
                                    }}
                                    title="Estampa"
                                    checkedColor="blue"
                                    uncheckedColor="white"
                                    checked={personData.selectedPatterned}
                                    onPress={() => { !personData.selectedStripe ? setPersonData({ ...personData, selectedPatterned: !personData.selectedPatterned }) : null }}
                                />
                            </RowSuggestion>
                        </ContainerCheckBox>

                        <ContainerSuggestion>
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