import React, { useEffect } from "react";
import { TouchableOpacity, BackHandler } from "react-native";
import HeaderProfile from "../../components/HeaderProfile/HeaderProfile";

import { Container, Line, Description, ContainerDescription, Logo} from "./styles";

const ClientAttendance = ({ navigation }) => {
  return (
    <Container>
      <HeaderProfile />

      <Line />
      <Logo source={require("../../assets/venda-mais-logo.png")} />
      <Line />

      <ContainerDescription>
        <Description>Nenhum cliente na loja para ser atendido.</Description>
      </ContainerDescription>
    </Container>
  );
};

export default ClientAttendance;
