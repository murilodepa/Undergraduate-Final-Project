import React, { useEffect } from "react";
import { TouchableOpacity, BackHandler } from "react-native";
import HeaderProfile from "../../components/HeaderProfile/HeaderProfile";
import { ClientService } from "../../services/ClientService/ClientService";
import { IClientsList } from "../../services/ClientService/ClientServiceInterface";

import { Container, Line, Description, ContainerDescription, Logo} from "./styles";

const Menu = ({ navigation }) => {

  const eventEasterEgg = async () => {
    console.log("Button - event Easter Egg");
    navigation.navigate("ClientAttendance");
  };

  useEffect(() => {
    const intervalId = setInterval(async () => {
      console.log("Hello, World!")

      let userRegistered: boolean = true;
      let response: IClientsList;
      try {
        response = await new ClientService().getClientsList();
      } catch (error) {
        console.error("Error to get clients list", error);
      }

      console.log("response, response: ", response)
      console.log("Hello, World!")

    }, 30000)
    return () => clearInterval(intervalId)
  }, [])



  return (
    <Container>
      <HeaderProfile navigation={navigation}/>

      <Line />
      <TouchableOpacity onPress={() => eventEasterEgg()}>
      <Logo source={require("../../assets/venda-mais-logo.png")} />
      </TouchableOpacity>
      <Line />

      <ContainerDescription>
        <Description>Nenhum cliente na loja para ser atendido.</Description>
      </ContainerDescription>
    </Container>
  );
};

export default Menu;
