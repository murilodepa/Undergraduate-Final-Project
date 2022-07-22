import React, { useEffect } from "react";
import { TouchableOpacity, BackHandler } from "react-native";
import HeaderProfile from "../../components/HeaderProfile/HeaderProfile";

import { Container, Line, Description, ContainerDescription, Logo} from "./styles";

const Menu = ({ navigation }) => {

  const eventEasterEgg = async () => {
    console.log("Button - event Easter Egg");
    navigation.navigate("ClientAttendance");
  };

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
