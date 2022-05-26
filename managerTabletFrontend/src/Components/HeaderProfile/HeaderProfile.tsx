import React, { useState } from "react";
import { TouchableOpacity } from "react-native";
import ModalProfileSettings from "../../Components/Modals/ProfileSettings/ProfileSettings";
import ModalEditProfile from "../../Components/Modals/EditProfile/EditProfile";
import { SellerService } from "../../services/SellerService/SellerService";
import { ISellerData } from "../../services/SellerService/SellerServiceInterface";
import { IGetSellerData } from "../../services/SellerService/SellerServiceInterface";

import {
    ContainerHeader,
    ProfileImage,
    ProfileText,
} from "./styles";


const SellerInputs = (props: any) => {
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
    };
  
    const eventSellerMenu = async () => {
      console.log("Seller");
      props.navigation.navigate("SellerMenu");
    };
  
    const eventClientMenu = async () => {
      console.log("Clients");
    };
 
  return (
    <ContainerHeader>
        
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
        <TouchableOpacity onPress={() => eventProfileSetting()}>
      <ProfileImage
        source={require("../../assets/profile-image-setting.png")}
      />
    </TouchableOpacity>
    <ProfileText>
        {props.name}
    </ProfileText>
  </ContainerHeader>
  );
};

export default SellerInputs;