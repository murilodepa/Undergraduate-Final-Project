import React from "react";
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

const ClientInformations = ({ navigation }) => {

    const eventClientAttendance = async () => {
    console.log("Button - Cliente Atendido");
    navigation.navigate("Menu");
  };

  return (
    <Container>
      <HeaderProfile navigation={navigation}/>

      <Line />
      <ContainerClientName>
        <ClientName>Murilo Araujo</ClientName>
        <LineDescription />
        <ClientDescription>
          {`Masculino \n 23 anos \n Sugestão: Camiseta`}
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
