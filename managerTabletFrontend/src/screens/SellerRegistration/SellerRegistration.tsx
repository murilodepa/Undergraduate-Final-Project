import React, { useState } from "react";
import SellerInputs from "../../Components/SellerInputs/SellerInputs";
import { ISellerData } from "../../services/SellerService/SellerServiceInterface";

import {
  Container,
  ContainerHeader,
  ProfileImage,
} from "../Initial/InitialStyles";

import {
  MenuIcon,
  ContainerMiddle,
  ContainerDescription,
  MenuText,
  MenuButton,
  ContainerFooter,
} from "./styles";

const ManagerRegistration = ({ navigation }) => {

  const [placeholderInputs, setPlaceholderInputs] = useState<ISellerData>({
    name: "Name...",
    birth: "dd/mm/aaaa",
    email: "E-mail..."
});

  const [managerData, setManagerData] = useState<ISellerData>({
    gender: "Feminino",
    sector: "Camiseta"
  });

  return (
    <Container>
      <ContainerHeader>
        <ProfileImage
          source={require("../../assets/profile-image-setting.png")}
        />
      </ContainerHeader>
      <ContainerMiddle>
        <ContainerDescription>
        <SellerInputs navigation={navigation} buttonName="Cadastrar" placeholderInputs={placeholderInputs} sellerData={managerData} />
        </ContainerDescription>
      </ContainerMiddle>

      <ContainerFooter>
        <MenuButton style={{ flexDirection: "row" }}>
          <MenuIcon source={require("../../assets//menu.png")} />
          <MenuText> Menu </MenuText>
        </MenuButton>
      </ContainerFooter>
    </Container>
  );
};

export default ManagerRegistration;
