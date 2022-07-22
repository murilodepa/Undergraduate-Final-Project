import React from "react";

import HeaderProfile from "../../components/HeaderProfile/HeaderProfile";

import {
  Container,
  Line,
  ClientImage,
  ClientName,
  ContainerClientName,
  Button,
  DescriptionButton,
} from "./styles";

const ClientAttendance = ({ navigation }) => {
  const eventClientFound = async () => {
    console.log("Button - Cliente encontrado");
    navigation.navigate("ClientInformations");
  };

  const eventClientNotFound = async () => {
    console.log("Button - Cliente não encontrado");
    navigation.navigate("Menu");
  };

  return (
    <Container>
      <HeaderProfile navigation={navigation}/>

      <Line />

      <ClientImage source={require("../../assets/client-profile.jpg")} />

      <ContainerClientName>
        <ClientName>Murilo Araujo</ClientName>
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
