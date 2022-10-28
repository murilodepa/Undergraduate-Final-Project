import React, { useState } from "react";
import { TouchableOpacity } from 'react-native';
import ClientInputs from "../../Components/clientInputs/clientInputs";
import { IClientData } from "../../services/ClientService/ClientServiceInterface";
import HeaderProfile from "../../Components/HeaderProfile/HeaderProfile";

import { Container } from "../Initial/InitialStyles";

import { ContainerMiddle, ContainerDescription, ContainerButtons, CloseButton } from "./styles";

const ClientRegistration = ({ navigation }) => {
  const [placeholderInputs, setPlaceholderInputs] = useState<IClientData>({
    name: "Name...",
    birth: "dd/mm/aaaa",
  });

  const [clientData, setClientData] = useState<IClientData>({
    gender: "Selecione",
  });

  const closeEditProfile = async () => {
      navigation.navigate("ClientMenu");
  };

  return (
    <Container>
      <HeaderProfile/>
      <ContainerMiddle>
        <ContainerDescription>
        <ContainerButtons>
            <TouchableOpacity onPress={() => closeEditProfile()}>
              <CloseButton
                source={require("../../assets/window-close.png")}
              />
            </TouchableOpacity>
          </ContainerButtons>
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
