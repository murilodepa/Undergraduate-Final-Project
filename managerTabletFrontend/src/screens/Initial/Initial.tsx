import React, { useEffect } from "react";
import { Text, StyleSheet, View, TouchableOpacity, BackHandler } from "react-native";
import {
  Container,
  ContainerHeader,
  ProfileImage,
  ContainerFooter,
  NextArrow,
  ContainerMiddle,
  ContainerDescription,
  StoreLogo,
  StoreName,
  Description,
  Line,
} from "./stylesInitial";

const Initial = ({ navigation }) => {

  useEffect(() => {
    BackHandler.addEventListener('hardwareBackPress', () => {
      return true
    })
  }, [])

  var test = "seller";

  const eventRegisterManager = async () => {
		navigation.navigate("Capture", {paramKey: test,
    })
	}

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
          <StoreLogo source={require("../../assets/clothing-store.png")} />

          <StoreName> Campinense </StoreName>
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
}

export default Initial;
