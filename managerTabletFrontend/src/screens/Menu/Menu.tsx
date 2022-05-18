import React, { useState } from "react";
import { TouchableOpacity } from "react-native";
import { TextInputMask } from "react-native-masked-text";
import { Picker } from "@react-native-picker/picker";
import { ISellerData } from "../../services/SellerService/SellerServiceInterface";
import validator from "validator";

import {
  Container,
  ContainerHeader,
  ProfileImage,
  ContainerButtons,
  ContainerSellerClientButton,
  SellerClientButton,
  SellerClientText,
} from "./styles";

const ManagerRegistration = ({ navigation }) => {

  const eventSellerMenu = async () => {
    console.log("Seller")
  };

  const eventClientMenu = async () => {
    console.log("Clientes")
  };

  return (
    <Container>
      <ContainerHeader>
        <ProfileImage
          //<CapturedImage source={{ uri: capturedPhotoURI }} />

          source={require("../../assets/profile-image-setting.png")}
        />
      </ContainerHeader>
      <ContainerButtons>
          <ContainerSellerClientButton>
            <TouchableOpacity onPress={() => eventSellerMenu()}>
              <SellerClientButton
                source={require("../../assets/seller-button.png")}
              />
              <SellerClientText> Funcionários </SellerClientText>
            </TouchableOpacity>
          </ContainerSellerClientButton>
          <ContainerSellerClientButton style={{marginTop: 100}}>
            <TouchableOpacity onPress={() => eventClientMenu()}>
              <SellerClientButton
                source={require("../../assets/client-button.png")}
              />
              <SellerClientText> Clientes </SellerClientText>
            </TouchableOpacity>
          </ContainerSellerClientButton>
      </ContainerButtons>
    </Container>
  );
};

export default ManagerRegistration;
