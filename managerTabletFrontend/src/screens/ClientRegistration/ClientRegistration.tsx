import React, { useState } from "react";
import ClientInputs from "../../Components/clientInputs/clientInputs";
import { IClientData } from "../../services/ClientService/ClientServiceInterface";
import HeaderProfile from "../../Components/HeaderProfile/HeaderProfile";

import { Container } from "../Initial/InitialStyles";

import { ContainerMiddle, ContainerDescription } from "./styles";

const ClientRegistration = ({ navigation }) => {
  const [placeholderInputs, setPlaceholderInputs] = useState<IClientData>({
    name: "Name...",
    birth: "dd/mm/aaaa",
  });

  const [clientData, setClientData] = useState<IClientData>({
    gender: "Feminino",
  });

  return (
    <Container>
      <HeaderProfile name="Murilo Araujo" navigation={navigation} />
      <ContainerMiddle>
        <ContainerDescription>
          <ClientInputs
            navigation={navigation}
            buttonName="Cadastrar"
            placeholderInputs={placeholderInputs}
            clientData={clientData}
          />
        </ContainerDescription>
      </ContainerMiddle>
    </Container>
  );
};

export default ClientRegistration;
