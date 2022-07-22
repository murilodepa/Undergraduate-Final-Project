import React, { useState } from "react";
import { TouchableOpacity } from "react-native";
import { ContainerHeader, ProfileImage, ProfileText} from "./styles";
import { useGlobalContext } from "../../context/SellerContext";
import ModalSellerInformations from "../Modals/SellerInformations";
import { IGetSellerData } from "../../services/SellerService/SellerServiceInterface";

const SellerInputs = ({ navigation }) => {
  const { name, profileImage } = useGlobalContext();
  const [openSellerInformations, setOpenSellerInformations] = useState(false);
  const [sellerData, setSellerData] = useState<IGetSellerData>();
  
  const closeSellerInformations = async () => {
    setOpenSellerInformations(false);
  };

  const closeAndLeave = async () => {
    setOpenSellerInformations(false);
    navigation.navigate("Initial");
  };
  
  function reformatDate(dateStr: string) {
    const dArr = dateStr.split("-");
    return dArr[2] + "/" + dArr[1] + "/" + dArr[0];
  }

  const eventProfileSetting = async () => {
    console.log("eventProfileSetting()");
  /*  let data: any;
    try {
      data = await new SellerService().getSeller();
    } catch (error) {
      console.error("Error to get seller date", error);
    }
    data.birth = reformatDate(data.birth);
    console.log("birth: ", data.birth);
    setSellerData(data);
    if (data.name != undefined && data.name != null) {
      setOpenSellerInformations(true);
    }*/

    setOpenSellerInformations(true);
  };

  return (
    <ContainerHeader>
            {
        <ModalSellerInformations
        openSellerInformations={openSellerInformations}
        closeSellerInformations={closeSellerInformations}
        sellerInformations={sellerData}
        closeAndLeave={closeAndLeave}
        />
      }
      <TouchableOpacity onPress={() => eventProfileSetting()}>
      <ProfileImage source={require("../../assets/Profile.png")} />
      </TouchableOpacity>
      <ProfileText>Murilo Araujo</ProfileText>
    </ContainerHeader>
    
  );
};

export default SellerInputs;
