import React from "react";
import LottieView from "lottie-react-native";
import { Container, Venda, Mais, ContainerVendaMais } from "./styles";
import { SellerService } from "../../services/SellerService/SellerService";

const Splash = ({ navigation }) => {

  const eventOnAnimationFinish = async () => {
    let data: any;
   /* try {
      data = await new SellerService().getSellers();
    } catch (error) {
      console.error("Falha no cadastro", error);
    }
    if (data.length === 0) { 
      navigation.navigate("Initial");
    } else {*/
      navigation.navigate("Menu");
  //  }
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
