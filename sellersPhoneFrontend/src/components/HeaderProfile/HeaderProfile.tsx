import React, { useState } from "react";
import { TouchableOpacity } from "react-native";
import { ContainerHeader, ProfileImage, ProfileText} from "./styles";
import { useGlobalContext } from "../../context/SellerContext";
import ModalSellerInformations from "../Modals/SellerInformations";

const HeaderProfile = ({ navigation }) => {
  const { name, profileImage } = useGlobalContext();
  const [openSellerInformations, setOpenSellerInformations] = useState(false);
  
  const closeSellerInformations = async () => {
    setOpenSellerInformations(false);
  };

  const closeAndLeave = async () => {
    setOpenSellerInformations(false);
    navigation.navigate("Initial");
  };

  const eventProfileSetting = async () => {
    console.log("Event - Open profile setting");
    setOpenSellerInformations(true);
  };

  return (
    <ContainerHeader>
            {
        <ModalSellerInformations
        openSellerInformations={openSellerInformations}
        closeSellerInformations={closeSellerInformations}
        closeAndLeave={closeAndLeave}
        />
      }
      <TouchableOpacity onPress={() => eventProfileSetting()}>
      <ProfileImage source={{uri: profileImage}} />
      </TouchableOpacity>
      <ProfileText>{name}</ProfileText>
    </ContainerHeader>
  );
};

export default HeaderProfile;
