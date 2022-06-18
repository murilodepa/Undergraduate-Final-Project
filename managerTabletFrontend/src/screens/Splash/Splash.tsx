import React from "react";
import LottieView from "lottie-react-native";
import { Container, Venda, Mais, ContainerVendaMais } from "./styles";
import { useGlobalContext } from "../../context/managerContext";
import { ISellerIdNameImageList, ISellerImageAndName } from "../../services/SendImageSellerService/SendImageSellerServiceInterface";
import { SendImageSellerService } from "../../services/SendImageSellerService/SendImageSellerService";
import { SellerService } from "../../services/SellerService/SellerService";
import { LogBox } from 'react-native';
import { IClientIdNameImageList } from "../../services/SendImageClientService/SendImageClientServiceInterface";
import { SendImageClientService } from "../../services/SendImageClientService/SendImageClientService";

LogBox.ignoreLogs(['Warning: ...']);
LogBox.ignoreAllLogs();

const Splash = ({ navigation }) => {

  const { setName, setProfileImage, setResultSellerData, setResultClientData } = useGlobalContext();

  async function handleOrderList(list: any, isSeller: boolean) {

    list.sort((x, y): any =>
      x.name > y.name ? 1 : y.name > x.name ? -1 : 0
    );

    if (isSeller) {
      console.log("Seller - Sort result data list by names");
      setResultSellerData(list);
    } else {
      console.log("Client - Sort result data list by names");
      setResultClientData(list);
    }
  };

  async function getSellerData() {
    let response: ISellerIdNameImageList;
    try {
      response = await new SendImageSellerService().getSellerIdNameImage();
    } catch (error) {
      console.error("Error to get seller date", error);
    }
    handleOrderList(response, true);
  };

  async function getClientData() {
    let response: IClientIdNameImageList;
    try {
      response = await new SendImageClientService().getClientIdNameImage();
    } catch (error) {
      console.error("Error to get client date", error);
    }
    handleOrderList(response, false);
  };

  const eventOnAnimationFinish = async () => {

    let response: ISellerImageAndName;
    try {
      response = await new SendImageSellerService().getSellerImageAndName(11);
    } catch (error) {
      console.error("Error to get manager date", error);
    }
    if (response.profileImage != null && response.profileImage != undefined) {
      setProfileImage(response.profileImage);
      setName(response.name);
      getSellerData();
      getClientData();
      navigation.navigate("Menu");
      console.log("Updating name and photo of the manager");
    } else {
      navigation.navigate("Initial");
    }
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
