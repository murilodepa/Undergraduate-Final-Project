import React, { useEffect } from "react";
import { Text, StyleSheet, View, TouchableOpacity, BackHandler } from "react-native";
import { TextInputMask } from 'react-native-masked-text'
import {Picker} from '@react-native-picker/picker';
import {
  Container,
  ContainerHeader,
  ProfileImage,
  ContainerFooter,
  MenuIcon,
  ContainerMiddle,
  ContainerDescription,
  StoreLogo,
  StoreName,
  Description,
  Line,
  MenuText,
  MenuButton,
  ManagerPicture,
  CharacteristicText,
  CharacteristicInput,
  ViewTextInput,
  PlaceHolder,
} from "./styles";

const ManagerRegistration  = ({ navigation }) => {

  return (
    <Container>
      <ContainerHeader>
          <ProfileImage
            source={require("../../assets/profile-image-setting.png")}
          />
      </ContainerHeader>
      <ContainerMiddle>
        <ContainerDescription>
          <ManagerPicture source={require("../../assets/manager-picture.png")} />

          <StoreName> Campinense </StoreName>
          <Line />

          <ViewTextInput>
            <CharacteristicText> Nome: </CharacteristicText>
            <CharacteristicInput maxLength={10} keyboardType="default" placeholder=" Nome..."/>
          </ViewTextInput>

          <ViewTextInput>
          <CharacteristicText> Genêro </CharacteristicText>

         
          </ViewTextInput>

          <ViewTextInput>
          <CharacteristicText> Data de {'\n'} Nascimento: </CharacteristicText>
         <TextInputMask type={'datetime'}             placeholder="DD/MM/YYYY" options={{format: 'DD/MM/YYYY'}} style={PlaceHolder}/>
          </ViewTextInput>

          <Description>
            Primeiro vamos realizar o cadastramento do diretor ou um funcionário
            que possui cargo de confiança do establecimento.
          </Description>
        </ContainerDescription>
      </ContainerMiddle>
      <ContainerFooter>
        <MenuButton style={{flexDirection: "row"}}>
          <MenuIcon source={require("../../assets//menu.png")} />
          <MenuText> Menu </MenuText>
        </MenuButton>
      </ContainerFooter>
    </Container>
  );
}

export default ManagerRegistration;
