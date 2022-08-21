import React, { useState } from "react";
import date from "./date";
import { ScrollView, Text } from "react-native";

import HeaderProfile from "../../components/HeaderProfile/HeaderProfile";

import {
  Container,
  Line,
  ClientName,
  ContainerClientName,
  Button,
  DescriptionButton,
  LineDescription,
  ClientDescription,
  ContainerScrollView,
  DescriptionItens,
  LineItens,
} from "./styles";
import { IClientInformationsData } from "../../services/ClientService/ClientServiceInterface";
import { ClientSellerAttendance } from "../../services/ClientSellerAttendance/ClientSellerAttendance";
import { useGlobalContext } from "../../context/SellerContext";

const ClientInformations = ({ navigation, route }: any) => {
  const { id } = useGlobalContext();

  const [clientInformationsData, setClientInformationsData] = useState<IClientInformationsData>({
    id: route.params.paramKey.id,
    name: route.params.paramKey.name,
    gender: route.params.paramKey.gender,
    birth: route.params.paramKey.birth
  });

  const eventClientAttendance = async () => {
    console.log("Event - Client attendance");
    let response: any;
    try {
      response = await new ClientSellerAttendance().updateStatusAndEndTime(clientInformationsData.id, id);
    } catch (error) {
      console.error("Error to update status", error);
    }

    navigation.navigate("Menu");
  };

  return (
    <Container>
      <HeaderProfile navigation={navigation} />

      <Line />
      <ContainerClientName>
        <ClientName>{clientInformationsData.name}</ClientName>
        <LineDescription />
        <ClientDescription>
          {`${clientInformationsData.gender} \n ${clientInformationsData.birth} anos \n Sugestão: Camiseta`}
        </ClientDescription>
      </ContainerClientName>

      <ScrollView style={{ marginTop: "8%", maxHeight: "30%", width: "70%" }}>
        <ContainerScrollView>
          {date.map((value, index) => (
            <>
              <DescriptionItens
                key={index}
              >{`${value.product} - ${value.date}`}</DescriptionItens>
              <LineItens />
            </>
          ))}
        </ContainerScrollView>
      </ScrollView>

      <Button onPress={() => eventClientAttendance()}>
        <DescriptionButton> Cliente Atendido </DescriptionButton>
      </Button>
    </Container>
  );
};

export default ClientInformations;
