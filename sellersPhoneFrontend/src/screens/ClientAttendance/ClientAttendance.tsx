import React, { useState } from "react";

import HeaderProfile from "../../components/HeaderProfile/HeaderProfile";
import { ClientSellerAttendance } from "../../services/ClientSellerAttendance/ClientSellerAttendance"
import { useGlobalContext } from "../../context/SellerContext";

import {
  Container,
  Line,
  ClientImage,
  ClientName,
  ContainerClientName,
  Button,
  DescriptionButton,
} from "./styles";
import { IClientInformationsData } from "../../services/ClientService/ClientServiceInterface";

const ClientAttendance = ({ navigation, route }: any) => {
  const { id } = useGlobalContext();
  const [clientInformationsData, setClientInformationsData] = useState<IClientInformationsData>({
    id: route.params.paramKey.clientId,
    name: route.params.paramKey.name,
    gender: route.params.paramKey.gender,
    birth: route.params.paramKey.birth
  });

  const eventClientFound = async () => {
    console.log("Event - Client found");
    let response: any;
    try {
        response = await new ClientSellerAttendance().updateStatus(clientInformationsData.id, id);
    } catch (error) {
      console.error("Error to update status", error);
    }

    navigation.navigate("ClientInformations", {paramKey: clientInformationsData});
  };

  const eventClientNotFound = async () => {
    console.log("Event - Client not found");

    let response: any;
    try {
        response = await new ClientSellerAttendance().deleteAttendance(clientInformationsData.id, id);
    } catch (error) {
      console.error("Error to update status", error);
    }

    navigation.navigate("Menu");
  };

  return (
    <Container>
      <HeaderProfile navigation={navigation}/>

      <Line />

      <ClientImage source={{uri: route.params.paramKey.profileImage}} />

      <ContainerClientName>
        <ClientName>{clientInformationsData.name}</ClientName>
      </ContainerClientName>

      <Button onPress={() => eventClientFound()}>
        <DescriptionButton> Cliente Encontrado </DescriptionButton>
      </Button>

      <Button style={{ backgroundColor: "red" }} onPress={() => eventClientNotFound()}>
        <DescriptionButton>Cliente não encontrado</DescriptionButton>
      </Button>
    </Container>
  );
};

export default ClientAttendance;
