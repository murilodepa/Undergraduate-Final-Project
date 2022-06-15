import React, { useState } from "react";
import { ContainerItem, ButtonSelectText, ProfileImage, ButtonSelect, NameText } from "./styles";
import ModalProfileSettings from '../../Components/Modals/ProfileSettings/ProfileSettings';
import ModalEditProfile from '../../Components/Modals/EditProfile/EditProfile';
import ModalClientProfileSettings from "../Modals/ClientProfileSettings/ClientProfileSettings";
import ModalEditClientProfile from "../Modals/EditClientProfile/EditClientProfile";
import { IGetSellerData, ISellerData } from "../../services/SellerService/SellerServiceInterface";
import { SellerService } from "../../services/SellerService/SellerService";
import { useGlobalContext } from "../../context/managerContext";
import { ISellerIdNameImageList } from "../../services/SendImageSellerService/SendImageSellerServiceInterface";
import { SendImageSellerService } from "../../services/SendImageSellerService/SendImageSellerService";
import { ClientService } from "../../services/ClientService/ClientService";
import { IGetClientData } from "../../services/ClientService/ClientServiceInterface";

const ListItem = (props: any) => {
  const [openProfileSettings, setOpenProfileSettings] = useState(false);
  const [openClientProfileSettings, setOpenClientProfileSettings] = useState(false);
  const [openEditProfile, setOpenEditProfile] = useState(false);
  const [openEditClientProfile, setOpenEditClientProfile] = useState(false);
  const [managerData, setManagerData] = useState<IGetSellerData>();
  const [clientData, setClientData] = useState<IGetClientData>();
  const [age, setAge] = useState(0);
  const [placeholderInputs, setPlaceholderInputs] = useState<ISellerData>();
  const [profileImage, setProfileImage] = useState();
  const { setResultSellerData } = useGlobalContext();

  const eventSelectedSeller = async (id: number, image: any) => {
    if (props.clientOrSeller === "funcionário") {
      if (props.buttonText == "Selecionar") {
        setProfileImage(image);
        selectSeller(id);
        console.log("Selected Seller", id);
      } else {
        removeSeller(id);
        console.log("Removed Seller", id);
      }
    } else {
      setProfileImage(image);
      selectClient(id);
      console.log("Selected Client", id);
    }
  };

  function reformatDate(dateStr: string) {
    const dArr = dateStr.split("-");
    return dArr[2] + "/" + dArr[1] + "/" + dArr[0];
  }

  const closeProfileSettings = async () => {
    setOpenProfileSettings(false);
  };

  const closeClientProfileSettings = async () => {
    setOpenClientProfileSettings(false);
  };

  const closeEditProfile = async () => {
    setOpenEditProfile(false);
    setOpenProfileSettings(true);
  };

  const closeEditClientProfile = async () => {
    setOpenEditClientProfile(false);
    setOpenClientProfileSettings(true);
  };

  const closeEditProfileAndBack = async () => {
    setOpenEditProfile(false);
    props.closeModalSearch();
    console.log("teste");
    props.navigation.navigate("Menu");
  };

  const closeEditClientProfileAndBack = async () => {
    setOpenEditClientProfile(false);
    props.closeModalSearch();
  };

  const EditProfile = async () => {
    setOpenProfileSettings(false);
    setOpenEditProfile(true);
  };

  const EditClientProfile = async () => {
    setOpenClientProfileSettings(false);
    setOpenEditClientProfile(true);
  };

  async function calculateAge(birth: string) {
    var currentDate = new Date();
    var currentYear = currentDate.getFullYear();
    var ddMMyyyy = birth.split('/');
    var age = currentYear - parseInt(ddMMyyyy[2], 10);
    var monthOfBirth = parseInt(ddMMyyyy[1], 10);
    var currentMonth = currentDate.getMonth() + 1;

    if (currentMonth < monthOfBirth) {
      age--;
      return age;
    }
    if (currentMonth > monthOfBirth) {
      return age;
    }
    if (currentDate.getDate() < parseInt(ddMMyyyy[0], 10)) {
      age--;
      return age;
    }
    return age;
  }

  const selectSeller = async (id: number) => {
    let data: any;
    try {
      data = await new SellerService().getSeller(id);
    } catch (error) {
      console.error("Error to get manager date", error);
    }
    props.clearSearchText();
    if (data != undefined) {
      data.birth = reformatDate(data.birth);
      setPlaceholderInputs(data);
      setManagerData(data);
      setAge(await calculateAge(data.birth));
      setOpenProfileSettings(true);
    }
  };

  const selectClient = async (id: number) => {
    let data: any;
    try {
      data = await new ClientService().getClient(id);
    } catch (error) {
      console.error("Error to get client date", error);
    }
    props.clearSearchText();
    if (data != undefined) {
      if(data.purchaseSuggestion == null || data.purchaseSuggestion == undefined) {
        data.purchaseSuggestion = "Nenhuma Sugestão...";
      }
      data.birth = reformatDate(data.birth);
      setPlaceholderInputs(data);
      setClientData(data);
      setAge(await calculateAge(data.birth));
      setOpenClientProfileSettings(true);
    }
  };

  async function getSellerData() {
    let response: ISellerIdNameImageList;
    try {
      response = await new SendImageSellerService().getSellerIdNameImage();
    } catch (error) {
      console.error("Error to get seller date", error);
    }
    setResultSellerData(response);
  };

  const removeSeller = async (id: number) => {
    let data: any;
    try {
      data = await new SellerService().removeSeller(id);
    } catch (error) {
      console.error("Error to remove seller", error);
    }
    props.clearSearchText();
    getSellerData();
    props.closeModalSearch();
  }

  return (
    <ContainerItem>
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
      {
        <ModalClientProfileSettings
          openClientProfileSettings={openClientProfileSettings}
          closeClientProfileSettings={closeClientProfileSettings}
          EditClientProfile={EditClientProfile}
          clientData={clientData}
          age={age}
          profileImage={profileImage}
        />
      }
      {
        <ModalEditClientProfile
          openEditClientProfile={openEditClientProfile}
          closeEditClientProfile={closeEditClientProfile}
          placeholderInputs={placeholderInputs}
          profileImage={profileImage}
          closeEditClientProfileAndBack={closeEditClientProfileAndBack}
        />
      }
      <ProfileImage
        source={{ uri: props.data.profileImage }}
      />
      <NameText>{props.data.name}</NameText>

      <ButtonSelect style={{ backgroundColor: props.buttonColor }} onPress={() => eventSelectedSeller(props.data.userId, props.data.profileImage)}>
        <ButtonSelectText>
          {props.buttonText}
        </ButtonSelectText>
      </ButtonSelect>

    </ContainerItem>
  );
};

export default ListItem;
