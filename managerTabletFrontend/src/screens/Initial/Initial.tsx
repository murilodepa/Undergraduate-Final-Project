import React, { useEffect } from "react";
import { TouchableOpacity, BackHandler } from "react-native";
import {
  Container,
  ContainerHeader,
  ProfileImage,
  ContainerFooter,
  NextArrow,
  ContainerMiddle,
  ContainerDescription,
  StoreLogo,
  ContainerLogo,
  Description,
  Line,
} from "./InitialStyles";

const Initial = ({ navigation }) => {
  useEffect(() => {
    BackHandler.addEventListener("hardwareBackPress", () => {
      return true;
    });
  }, []);

  const eventRegisterManager = async () => {
    navigation.navigate("SellerRegistration");
  };

  return (
    <Container>
      <ContainerHeader>
        <TouchableOpacity>
          <ProfileImage
            source={require("../../assets/profile-image-setting.png")}
          />
        </TouchableOpacity>
      </ContainerHeader>
      <ContainerMiddle>
        <ContainerDescription>
          <ContainerLogo>
            <StoreLogo source={require("../../assets/venda-mais-logo.png")} />
          </ContainerLogo>
          <Line />
          <Description>
            Primeiro vamos realizar o cadastramento do diretor ou um funcionário
            que possui cargo de confiança do establecimento.
          </Description>
        </ContainerDescription>
      </ContainerMiddle>
      <ContainerFooter>
        <TouchableOpacity onPress={() => eventRegisterManager()}>
          <NextArrow source={require("../../assets/next-arrow.png")} />
        </TouchableOpacity>
      </ContainerFooter>
    </Container>
  );
};

export default Initial;
