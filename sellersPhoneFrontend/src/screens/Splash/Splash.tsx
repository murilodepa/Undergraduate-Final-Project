import React from "react";
import LottieView from "lottie-react-native";
import { Container, Venda, Mais, ContainerVendaMais } from "./styles";
import { LogBox } from 'react-native';

LogBox.ignoreLogs(['Warning: ...']); // Ignore log notification by message
LogBox.ignoreAllLogs();

const Splash = ({ navigation }) => {

  const eventOnAnimationFinish = async () => {
      navigation.navigate("Initial");
  };

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
          onAnimationFinish={() => eventOnAnimationFinish()}
        />
      </Container>
    </>
  );
};

export default Splash;
