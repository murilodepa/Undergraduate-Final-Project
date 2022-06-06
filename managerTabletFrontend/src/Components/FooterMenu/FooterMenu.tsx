import React, { useState } from "react";
import { TouchableOpacity } from "react-native";

import {
    ContainerFooter,
    MenuButton,
    MenuIcon,
    MenuText
} from "./styles";


const FooterMenu = (props: any) => {

    const eventMenu = async () => {
        props.navigation.navigate("Menu");
      };

    return (
        <ContainerFooter>
        <TouchableOpacity onPress={() => eventMenu()}>
          <MenuButton style={{ flexDirection: "row" }}>
            <MenuIcon source={require("../../assets//menu.png")} />
            <MenuText> Menu </MenuText>
          </MenuButton>
        </TouchableOpacity>
      </ContainerFooter>
    );
};

export default FooterMenu;
