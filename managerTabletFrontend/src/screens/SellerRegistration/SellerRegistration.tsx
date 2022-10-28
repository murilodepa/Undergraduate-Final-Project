import React, { useState } from 'react';
import { TouchableOpacity } from "react-native";
import SellerInputs from '../../Components/SellerInputs/SellerInputs';
import { ISellerData } from '../../services/SellerService/SellerServiceInterface';
import HeaderProfile from '../../Components/HeaderProfile/HeaderProfile';
import ProfileImageJson from '../../assets/url-profile-image-setting.json';


import {
  Container, ProfileImage,
} from "../Initial/InitialStyles";

import {
  ContainerMiddle,
  ContainerDescription,
  ContainerButtons,
  CloseButton
  
} from "./styles";

const SellerRegistration = ({ navigation, route }: any) => {

  const [placeholderInputs, setPlaceholderInputs] = useState<ISellerData>({
    name: "Name...",
    birth: "dd/mm/aaaa",
    email: "E-mail..."
  });

  const [managerData, setManagerData] = useState<ISellerData>({
    gender: "Selecione",
    sector: "Selecione",
    servicePreference: "Selecione"
  });

  const closeEditProfile = async () => {
    if(route.params.paramKey == "seller") {
      navigation.navigate("SellerMenu");
    }
  };

  return (
    <Container>
      <HeaderProfile />
      <ContainerMiddle>
        <ContainerDescription>
          <ContainerButtons>
            <TouchableOpacity onPress={() => closeEditProfile()}>
              <CloseButton
                source={require("../../assets/window-close.png")}
              />
            </TouchableOpacity>
          </ContainerButtons>
          <SellerInputs navigation={navigation} buttonName="Cadastrar" placeholderInputs={placeholderInputs} sellerData={managerData} image={ProfileImageJson} />
        </ContainerDescription>
      </ContainerMiddle>
    </Container>
  );
};

export default SellerRegistration;
