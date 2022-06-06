import React from "react";
import LottieView from "lottie-react-native";
import { Container, Venda, Mais, ContainerVendaMais } from "./styles";
import { useGlobalContext } from "../../context/managerContext";
import { ISellerImageAndName } from "../../services/SendImageSellerService/SendImageSellerServiceInterface";
import { SendImageSellerService } from "../../services/SendImageSellerService/SendImageSellerService";
import { SellerService } from "../../services/SellerService/SellerService";
import { LogBox } from 'react-native';

LogBox.ignoreLogs(['Warning: ...']); // Ignore log notification by message
LogBox.ignoreAllLogs();

const Splash = ({ navigation }) => {

  const {setName, setProfileImage} = useGlobalContext();

  const eventOnAnimationFinish = async () => {

    let response: ISellerImageAndName;
    try {
      response = await new SendImageSellerService().getSellerImageAndName(1);
    } catch (error) {
      console.error("Error to get manager date", error);
    }
 /*   if (response.profileImage != null && response.profileImage != undefined) {
      setProfileImage(response.profileImage);
      setName(response.name);
      navigation.navigate("Menu");
      console.log("Atualizando name and foto");
    } else {
     navigation.navigate("Initial");
    }*/
    navigation.navigate("SellerMenu");
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
