import React, { useState } from 'react';
import { TouchableOpacity } from "react-native";
import HeaderProfile from "../../Components/HeaderProfile/HeaderProfile";
import FooterMenu from "../../Components/FooterMenu/FooterMenu";
import ModalSearch from '../../Components/Modals/Search/Search';
import { useGlobalContext } from "../../context/managerContext";

import {
  Container,
} from "../Initial/InitialStyles";

import {
  ContainerMiddle,
  ContainerButton,
  ImageButton,
  TextButton,
} from "../SellerMenu/styles";

const ClientMenu = ({ navigation }) => {

  const [openModalSearch, setOpenModalSearch] = useState(false);
  const [buttonColor, setButtonColor] = useState('white');
  const [buttonText, setButtonText] = useState('');
  const { resultClientData } = useGlobalContext();

  const closeModalSearch = async () => {
    setOpenModalSearch(false);
  };

  const closeModalSearchAndBack = async () => {
    setOpenModalSearch(false);
    navigation.navigate("Menu");
  };

  const eventSearchClient = async () => {
    console.log("Search Client");
    setButtonColor('#4197E5');
    setButtonText('Selecionar');
    setOpenModalSearch(true);
  };

  const eventRegisterClient = async () => {
    console.log("Register Client");
    navigation.navigate("ClientRegistration");
  };

  return (
    <Container>
      {
        <ModalSearch
          openModalSearch={openModalSearch}
          closeModalSearch={closeModalSearch}
          clientOrSeller={"cliente"}
          buttonColor={buttonColor}
          buttonText={buttonText}
          resultData={resultClientData}
          closeModalSearchAndBack={closeModalSearchAndBack}
        />
      }
      <HeaderProfile />
      <ContainerMiddle>
        <TouchableOpacity onPress={() => eventSearchClient()}>
          <ContainerButton>
            <ImageButton source={require("../../assets/search-client.png")} />
            <TextButton> Buscar Cliente </TextButton>
          </ContainerButton>
        </TouchableOpacity>

        <TouchableOpacity onPress={() => eventRegisterClient()}>
          <ContainerButton style={{ marginBottom: 60, marginTop: 100 }}>
            <ImageButton source={require("../../assets/register-client.png")} />
            <TextButton> Cadastrar Cliente </TextButton>
          </ContainerButton>
        </TouchableOpacity>

      </ContainerMiddle>
      <FooterMenu navigation={navigation} />
    </Container>
  );
};

export default ClientMenu;
