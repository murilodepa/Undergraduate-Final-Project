import React, { useEffect, useContext } from "react";
import { TouchableOpacity, BackHandler, Text } from "react-native";
import ProfileImageJson from '../../assets/url-profile-image-setting.json';
import { useGlobalContext } from "../../context/managerContext";

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

export default function Initial ({ navigation }) {
  useEffect(() => {
    BackHandler.addEventListener("hardwareBackPress", () => {
      return true;
    });
  }, []);

  const { setName, setProfileImage } = useGlobalContext();

  const eventRegisterManager = async () => {
    setName(" ")
    setProfileImage(ProfileImageJson)
    navigation.navigate("SellerRegistration", {paramKey: "manager"});
  };

  return (
    <><Container>
        <ContainerHeader>
          <TouchableOpacity>
            <ProfileImage
              source={require("../../assets/profile-image-setting.png")} />
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
      </Container></>
  );
};
