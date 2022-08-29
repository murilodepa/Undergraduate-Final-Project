import React, { useEffect, useState } from "react";
import HeaderProfile from "../../components/HeaderProfile/HeaderProfile";
import { ClientService } from "../../services/ClientService/ClientService";
import { IClientData } from "../../services/ClientService/ClientServiceInterface";
import { Container, Line, Description, ContainerDescription, Logo } from "./styles";
import { useGlobalContext } from "../../context/SellerContext";
import { useIsFocused } from '@react-navigation/native';

const Menu = ({ navigation }) => {
  const { id } = useGlobalContext();
  const isFocused = useIsFocused();

  const eventEasterEgg = async () => {
    console.log("Event - Easter Egg Aux");
    navigation.navigate("ClientAttendance");
  };

  useEffect(() => {
    if (isFocused) {
      const intervalId = setInterval(async () => {
        let isCustomerWaitingAttendance: boolean = true;
        let response: IClientData;
        try {
          response = await new ClientService().getClientData(id);
        } catch (error) {
          console.error("Error to get clients list", error);
          isCustomerWaitingAttendance = false;
        }

        console.log("Pulling...")

        if (isCustomerWaitingAttendance && response.name != "" && response.name != " " && response.name != null) {
          navigation.navigate("ClientAttendance", { paramKey: response });
        }
      }, 3000)
      return () => clearInterval(intervalId)
    }
  }, [isFocused])

  return (
    <Container>
      <HeaderProfile navigation={navigation} />

      <Line />
      {/*<TouchableOpacity onPress={() => eventEasterEgg()}>*/}
      <Logo source={require("../../assets/venda-mais-logo.png")} />
      {/*</TouchableOpacity>*/}
      <Line />

      <ContainerDescription>
        <Description>Nenhum cliente na loja para ser atendido.</Description>
      </ContainerDescription>
    </Container>
  );
};

export default Menu;
