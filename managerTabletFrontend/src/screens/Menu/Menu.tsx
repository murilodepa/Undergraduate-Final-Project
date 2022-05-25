import React, { useState } from "react";
import { TouchableOpacity } from "react-native";
import ModalProfileSettings from "../../Components/Modals/ProfileSettings/ProfileSettings";

import {
  Container,
  ContainerHeader,
  ProfileImage,
  ContainerButtons,
  ContainerSellerClientButton,
  SellerClientButton,
  SellerClientText,
} from "./styles";

const ManagerRegistration = ({ navigation }) => {

  const [openProfileSettings, setOpenProfileSettings] = useState(false);
  const [openEditProfile, setOpenEditProfile] = useState(false);

  const closeProfileSettings = () => {
    setOpenProfileSettings(false);
  };

  const closeEditProfile = () => {
    setOpenEditProfile(false);
    setOpenProfileSettings(true);
  };

  const EditProfile = () => {
    setOpenProfileSettings(false);
    setOpenEditProfile(true);
  };

  const eventProfileSetting = async () => {
    console.log("Settings");
    setOpenProfileSettings(true);
  };

  const eventSellerMenu = async () => {
    console.log("Seller");
  };

  const eventClientMenu = async () => {
    console.log("Clients");
  };

  return (
    <Container>
      <ContainerHeader>
        <TouchableOpacity onPress={() => eventProfileSetting()}>
          <ProfileImage
            //<CapturedImage source={{ uri: capturedPhotoURI }} />
            source={require("../../assets/profile-image-setting.png")}
          />
        </TouchableOpacity>
      </ContainerHeader>

      {
        <ModalProfileSettings
        openProfileSettings={openProfileSettings}
        closeProfileSettings={closeProfileSettings}
        EditProfile={EditProfile}
        closeEditProfile={closeEditProfile}
        />
      }
      
      <ContainerButtons>
        <ContainerSellerClientButton>
          <TouchableOpacity onPress={() => eventSellerMenu()}>
            <SellerClientButton
              source={require("../../assets/seller-button.png")}
            />
            <SellerClientText> Funcionários </SellerClientText>
          </TouchableOpacity>
        </ContainerSellerClientButton>
        <ContainerSellerClientButton style={{ marginTop: 100 }}>
          <TouchableOpacity onPress={() => eventClientMenu()}>
            <SellerClientButton
              source={require("../../assets/client-button.png")}
            />
            <SellerClientText> Clientes </SellerClientText>
          </TouchableOpacity>
        </ContainerSellerClientButton>
      </ContainerButtons>
    </Container>
  );
};

export default ManagerRegistration;
