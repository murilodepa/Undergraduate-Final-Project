import React from "react";
import LottieView from "lottie-react-native";
import { Container } from "./styles";

const Splash = ({ navigation }) => {
  return (
    <Container>
      <LottieView
        source={require("../../assets/store-splash.json")}
        autoPlay
        speed={0.6}
        loop={false}
        onAnimationFinish={() => navigation.navigate("Initial")}
      />
    </Container>
  );
};

export default Splash;
