import React, { useState } from "react";
import { TouchableOpacity } from "react-native";
import ModalProfileSettings from "../../Components/Modals/ProfileSettings/ProfileSettings";
import ModalEditProfile from "../../Components/Modals/EditProfile/EditProfile";
import { SellerService } from "../../services/SellerService/SellerService";

import {
  Container,
  ContainerHeader,
  ProfileImage,
  ContainerButtons,
  ContainerSellerClientButton,
  SellerClientButton,
  SellerClientText,
} from "./styles";
import { ISellerData } from "../../services/SellerService/SellerServiceInterface";
import { IGetSellerData } from "../../services/SellerService/SellerServiceInterface";

const ManagerRegistration = ({ navigation }) => {
  const [openProfileSettings, setOpenProfileSettings] = useState(false);
  const [openEditProfile, setOpenEditProfile] = useState(false);

  const [placeholderInputs, setPlaceholderInputs] = useState<ISellerData>();
  const [managerData, setManagerData] = useState<IGetSellerData>();

  function reformatDate(dateStr: string) {
    const dArr = dateStr.split("-");
    return dArr[2] + "/" + dArr[1] + "/" + dArr[0]; 
  }

  const closeProfileSettings = async () => {
    setOpenProfileSettings(false);
  };

  const closeEditProfile = async () => {
    setOpenEditProfile(false);
    setOpenProfileSettings(true);
  };

  const EditProfile = async () => {
    setOpenProfileSettings(false);
    setOpenEditProfile(true);
  };

  const eventProfileSetting = async () => {
    console.log("Settings");
    let data: any;
    try {
      data = await new SellerService().getManager();
    } catch (error) {
      console.error("Error to get manager date", error);
    }
    data.birth = reformatDate(data.birth)
    console.log("birth: ", data.birth)

    setPlaceholderInputs(data);
    setManagerData(data);

    setOpenProfileSettings(true);
    console.log("xxxxxxxxxx: ", placeholderInputs.name);
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
        <ModalEditProfile
          openEditProfile={openEditProfile}
          closeEditProfile={closeEditProfile}
          placeholderInputs={placeholderInputs}
        />
      }

      {
        <ModalProfileSettings
          openProfileSettings={openProfileSettings}
          closeProfileSettings={closeProfileSettings}
          EditProfile={EditProfile}
          managerData={managerData}
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
