import React, { useState } from "react";
import { TouchableOpacity } from "react-native";
import ModalProfileSettings from "../../Components/Modals/ProfileSettings/ProfileSettings";
import ModalEditProfile from "../../Components/Modals/EditProfile/EditProfile";
import { SellerService } from "../../services/SellerService/SellerService";
import { ISellerData } from "../../services/SellerService/SellerServiceInterface";
import { IGetSellerData } from "../../services/SellerService/SellerServiceInterface";

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
