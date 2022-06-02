import React, { useState } from 'react';
import { TouchableOpacity } from 'react-native';
import ModalProfileSettings from '../../Components/Modals/ProfileSettings/ProfileSettings';
import ModalEditProfile from '../../Components/Modals/EditProfile/EditProfile';
import { SellerService } from '../../services/SellerService/SellerService';
import { ISellerData } from '../../services/SellerService/SellerServiceInterface';
import { IGetSellerData } from '../../services/SellerService/SellerServiceInterface';
import { ISellerImageAndName } from '../../services/SendImageSellerService/SendImageSellerServiceInterface'

import { ContainerHeader, ProfileImage, ProfileText } from "./styles";
import { SendImageSellerService } from '../../services/SendImageSellerService/SendImageSellerService';

const SellerInputs = (props: any) => {
  const [imageAndName, setImageAndName] = useState<ISellerImageAndName>();
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

  const getImageAndName = async () => {
    let response: ISellerImageAndName;
    try {
      response = await new SendImageSellerService().getSellerImageAndName(1);
    } catch (error) {
      console.error("Error to get manager date", error);
    }
    console.log("GetClientImage: ", response);
    setImageAndName({...imageAndName, name: response.name});
    setImageAndName({...imageAndName, profileImage: response.profileImage});
  };

  const eventProfileSetting = async () => {
    console.log("Settings");
    let data: any;
    try {
      data = await new SellerService().getManager();
    } catch (error) {
      console.error("Error to get manager date", error);
    }
    data.birth = reformatDate(data.birth);
    console.log("birth: ", data.birth);
    setPlaceholderInputs(data);
    setManagerData(data);
    if (data.name != undefined && data.name != null) {
      setOpenProfileSettings(true);
    }
  };

  return (
    <ContainerHeader>
    {getImageAndName()}
      {
        <ModalProfileSettings
          openProfileSettings={openProfileSettings}
          closeProfileSettings={closeProfileSettings}
          EditProfile={EditProfile}
          managerData={managerData}
        />
      }
            
      {
        <ModalEditProfile
          openEditProfile={openEditProfile}
          closeEditProfile={closeEditProfile}
          placeholderInputs={placeholderInputs}
        />
      }
      <TouchableOpacity onPress={() => eventProfileSetting()}>
        <ProfileImage
          source={{uri: imageAndName.profileImage}}
        />
      </TouchableOpacity>
      <ProfileText>{imageAndName.name}</ProfileText>
    </ContainerHeader>
  );
};

export default SellerInputs;