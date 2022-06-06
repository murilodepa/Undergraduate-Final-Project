import React, { useState, useContext } from 'react';
import { TouchableOpacity } from 'react-native';
import ModalProfileSettings from '../../Components/Modals/ProfileSettings/ProfileSettings';
import ModalEditProfile from '../../Components/Modals/EditProfile/EditProfile';
import { SellerService } from '../../services/SellerService/SellerService';
import { ISellerData } from '../../services/SellerService/SellerServiceInterface';
import { IGetSellerData } from '../../services/SellerService/SellerServiceInterface';
import { ContainerHeader, ProfileImage, ProfileText } from "./styles";
import { useGlobalContext } from '../../context/managerContext';

const HeaderProfile = () => {
  const [openProfileSettings, setOpenProfileSettings] = useState(false);
  const [openEditProfile, setOpenEditProfile] = useState(false);
  const [placeholderInputs, setPlaceholderInputs] = useState<ISellerData>();
  const [managerData, setManagerData] = useState<IGetSellerData>();
  const [age, setAge] = useState(0);
  const { name, profileImage } = useGlobalContext();

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

  const closeEditProfileAndBack = async () => {
    setOpenEditProfile(false);
  };

  const EditProfile = async () => {
    setOpenProfileSettings(false);
    setOpenEditProfile(true);
  };
  
  async function calculateAge (birth: string) {
    var currentDate = new Date();
    var currentYear = currentDate.getFullYear();
    var ddMMyyyy = birth.split('/');
    var age = currentYear - parseInt(ddMMyyyy[2], 10);
    var monthOfBirth = parseInt(ddMMyyyy[1], 10);
    var currentMonth = currentDate.getMonth() + 1;
  
    if(currentMonth < monthOfBirth) {
      age--;
      return age;
    }
    if(currentMonth > monthOfBirth) {
      return age;
    }
    if(currentDate.getDate() < parseInt(ddMMyyyy[0], 10)) {
      age--;
      return age;
    }
    return age;
  }

  const eventProfileSetting = async () => {
    let data: any;
    try {
      data = await new SellerService().getManager();
    } catch (error) {
      console.error("Error to get manager date", error);
    }
    if (data != undefined) {
      data.birth = reformatDate(data.birth);
      setPlaceholderInputs(data);
      setManagerData(data);
      setAge(await calculateAge(data.birth));
      setOpenProfileSettings(true);
    }
  };

  return (
    <ContainerHeader>       
      {
        <ModalProfileSettings
          openProfileSettings={openProfileSettings}
          closeProfileSettings={closeProfileSettings}
          EditProfile={EditProfile}
          managerData={managerData}
          age={age}
          profileImage={profileImage}
        />
      }
      {
        <ModalEditProfile
          openEditProfile={openEditProfile}
          closeEditProfile={closeEditProfile}
          placeholderInputs={placeholderInputs}
          profileImage={profileImage}
          closeEditProfileAndBack={closeEditProfileAndBack}
        />
      }
      <TouchableOpacity onPress={() => eventProfileSetting()}>
        <ProfileImage
          source={{uri: profileImage}}
        />
      </TouchableOpacity>
      <ProfileText>{name}</ProfileText>
    </ContainerHeader>
  );
};

export default HeaderProfile;