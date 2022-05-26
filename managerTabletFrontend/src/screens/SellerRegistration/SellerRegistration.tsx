import React, { useState, useEffect } from 'react';
import { BackHandler } from "react-native";
import SellerInputs from '../../Components/SellerInputs/SellerInputs';
import { ISellerData } from '../../services/SellerService/SellerServiceInterface';
import HeaderProfile from '../../Components/HeaderProfile/HeaderProfile';

import {
  Container,
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
      <HeaderProfile name="Murilo Araujo" navigation={navigation} />
      <ContainerMiddle>
        <ContainerDescription>
        <SellerInputs navigation={navigation} buttonName="Cadastrar" placeholderInputs={placeholderInputs} sellerData={managerData} />
        </ContainerDescription>
      </ContainerMiddle>
    </Container>
  );
};

export default SellerRegistration;
