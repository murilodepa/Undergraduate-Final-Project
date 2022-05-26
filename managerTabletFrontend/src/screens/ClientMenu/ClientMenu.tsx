import React from "react";
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
  const eventSearchClient = async () => {
    console.log("Search Client");
  };

  const eventRegisterClient = async () => {
    console.log("Register Client");
    navigation.navigate("ClientRegistration");
  };

  return (
    <Container>
      <HeaderProfile name="Murilo Araujo" navigation={navigation} />
      <ContainerMiddle>
        <TouchableOpacity onPress={() => eventSearchClient()}>
          <ContainerButton>
            <ImageButton source={require("../../assets/search-client.png")} />
            <TextButton> Buscar Cliente </TextButton>
          </ContainerButton>
        </TouchableOpacity>

        <TouchableOpacity onPress={() => eventRegisterClient()}>
        <ContainerButton style={{ marginBottom: 60, marginTop: 100 }}>
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
