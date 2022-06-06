import React, { useEffect } from "react";
import { TouchableOpacity, BackHandler } from "react-native";
import HeaderProfile from "../../Components/HeaderProfile/HeaderProfile";

import {
  Container,
  ContainerButtons,
  ContainerSellerClientButton,
  SellerClientButton,
  SellerClientText,
} from "./styles";

const Menu = ({ navigation }) => {
  useEffect(() => {
    BackHandler.addEventListener("hardwareBackPress", () => {
      return true;
    });
  }, []);

  const eventSellerMenu = async () => {
    navigation.navigate("SellerMenu");
  };

  const eventClientMenu = async () => {
    console.log("Clients");
    navigation.navigate("ClientMenu");
  };

  return (
    <Container>
      <HeaderProfile/>
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
