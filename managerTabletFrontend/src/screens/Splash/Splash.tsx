import React from "react";
import LottieView from "lottie-react-native";
import { Container, Venda, Mais, ContainerVendaMais } from "./styles";
import { Text, View } from "react-native";

const Splash = ({ navigation }) => {
  return (
    <>
    <ContainerVendaMais>
    <Venda source={require("../../assets/venda.png")} />
    <Mais source={require("../../assets/mais.png")} />
    </ContainerVendaMais>
    <Container>
      <LottieView
        source={require("../../assets/store-splash.json")}
        autoPlay
        loop={false}
        duration={6500}
        onAnimationFinish={() => navigation.navigate("Initial")}
        />
    </Container></>
  );
};

export default Splash;
