import React from "react";
import { TouchableOpacity } from "react-native";
import HeaderProfile from "../../Components/HeaderProfile/HeaderProfile";

import {
  Container,
  ContainerButtons,
  ContainerSellerClientButton,
  SellerClientButton,
  SellerClientText,
} from "./styles";

const Menu = ({ navigation }) => {
  const eventSellerMenu = async () => {
    console.log("Seller");
    navigation.navigate("SellerMenu");
  };

  const eventClientMenu = async () => {
    console.log("Clients");
  };

  return (
    <Container>
      <HeaderProfile name="Murilo Araujo" navigation={navigation} />

      <ContainerButtons>
        <ContainerSellerClientButton>
          <TouchableOpacity onPress={() => eventSellerMenu()}>
            <SellerClientButton
              source={require("../../assets/seller-button.png")}
            />
            <SellerClientText> Funcionários </SellerClientText>
          </TouchableOpacity>
        </ContainerSellerClientButton>
        <ContainerSellerClientButton style={{ marginTop: 100 }}>
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

export default Menu;
