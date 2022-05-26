import React, { useState } from "react";
import { TouchableOpacity } from "react-native";
import HeaderProfile from "../../Components/HeaderProfile/HeaderProfile";
import FooterMenu from "../../Components/FooterMenu/FooterMenu";

import {
  Container,
  ContainerHeader,
  ProfileImage,
} from "../Initial/InitialStyles";

import {
  ContainerMiddle,
  ContainerButton,
  ImageButton,
  TextButton,
} from "./styles";

const SellerMenu = ({ navigation }) => {
  const eventSearchSeller = async () => {
    console.log("Search Seller");
  };

  const eventRegisterSeller = async () => {
    console.log("Register Seller");
  };

  const eventRemoveSeller = async () => {
    console.log("Remove Seller");
  };

  return (
    <Container>
      <HeaderProfile name="Murilo Araujo" navigation={navigation} />
      <ContainerMiddle>
        <TouchableOpacity onPress={() => eventSearchSeller()}>
          <ContainerButton>
            <ImageButton source={require("../../assets/search-seller.png")} />
            <TextButton> Buscar Funcionário </TextButton>
          </ContainerButton>
        </TouchableOpacity>

        <TouchableOpacity onPress={() => eventRegisterSeller()}>
          <ContainerButton>
            <ImageButton source={require("../../assets/register-seller.png")} />
            <TextButton> Cadastrar Funcionário </TextButton>
          </ContainerButton>
        </TouchableOpacity>

        <TouchableOpacity onPress={() => eventRemoveSeller()}>
          <ContainerButton style={{ marginBottom: 60 }}>
            <ImageButton source={require("../../assets/remove-seller.png")} />
            <TextButton> Remover Funcionário </TextButton>
          </ContainerButton>
        </TouchableOpacity>
      </ContainerMiddle>
      <FooterMenu navigation={navigation} />
    </Container>
  );
};

export default SellerMenu;
