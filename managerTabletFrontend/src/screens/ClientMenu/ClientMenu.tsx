import React, { useState } from "react";
import { TouchableOpacity } from "react-native";
import HeaderProfile from "../../Components/HeaderProfile/HeaderProfile";
import FooterMenu from "../../Components/FooterMenu/FooterMenu";

import {
  Container,
} from "../Initial/InitialStyles";

import {
  ContainerMiddle,
  ContainerButton,
  ImageButton,
  TextButton,
} from "../SellerMenu/styles";

const ClientMenu = ({ navigation }) => {
  const eventSearchSeller = async () => {
    console.log("Search Client");
  };

  const eventRegisterSeller = async () => {
    console.log("Register Client");
  };

  return (
    <Container>
      <HeaderProfile name="Murilo Araujo" navigation={navigation} />
      <ContainerMiddle>
        <TouchableOpacity onPress={() => eventSearchSeller()}>
          <ContainerButton>
            <ImageButton source={require("../../assets/search-client.png")} />
            <TextButton> Buscar Cliente </TextButton>
          </ContainerButton>
        </TouchableOpacity>

        <TouchableOpacity onPress={() => eventRegisterSeller()}>
          <ContainerButton>
            <ImageButton source={require("../../assets/register-client.png")} />
            <TextButton> Cadastrar Cliente </TextButton>
          </ContainerButton>
        </TouchableOpacity>

      </ContainerMiddle>
      <FooterMenu navigation={navigation} />
    </Container>
  );
};

export default ClientMenu;
