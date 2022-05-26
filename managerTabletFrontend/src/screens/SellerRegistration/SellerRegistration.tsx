import React, { useState } from "react";
import SellerInputs from "../../Components/SellerInputs/SellerInputs";
import { ISellerData } from "../../services/SellerService/SellerServiceInterface";

import {
  Container,
  ContainerHeader,
  ProfileImage,
} from "../Initial/InitialStyles";

import {
  ContainerMiddle,
  ContainerDescription,
} from "./styles";

const SellerRegistration = ({ navigation }) => {

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
    </Container>
  );
};

export default SellerRegistration;
