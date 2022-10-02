import React, { useEffect, useState } from "react";
import date from "./date";
import { ScrollView, TouchableOpacity, ToastAndroid } from "react-native";

import HeaderProfile from "../../components/HeaderProfile/HeaderProfile";

import {
  Container,
  Line,
  ClientName,
  ContainerClientName,
  Button,
  DescriptionButton,
  LineDescription,
  ClientDescription,
  ContainerScrollView,
  DescriptionItens,
  LineItens,
  GiftGif,
  ContainerGifAndButton,
  HappyBirthdayContainer,
  HappyBirthdayImage
} from "./styles";
import { IClientInformationsData, IPurchaseList } from "../../services/ClientService/ClientServiceInterface";
import { ClientSellerAttendance } from "../../services/ClientSellerAttendance/ClientSellerAttendance";
import { useGlobalContext } from "../../context/SellerContext";
import { ClientService } from "../../services/ClientService/ClientService";
import GiftModal from "../../components/Modals/GiftModal/GiftModal";
import NewClientGiftModal from "../../components/Modals/NewClientGiftModal/NewClientGiftModal";
import NewBuyGiftModal from "../../components/Modals/NewBuyGiftModal/NewBuyGiftModal";
import { PurchaseService } from "../../services/PurchaseService/PurchaseService";
import { IPurchasePeopleGiftList } from "../../services/PurchaseService/PurchaseServiceInterface";

const ClientInformations = ({ navigation, route }: any) => {
  const { id } = useGlobalContext();

  const [openGiftModal, setOpenGiftModal] = useState(false);
  const [openNewClientGiftModal, setOpenNewClientGiftModal] = useState(false);
  const [openNewBuyGiftModal, setOpenNewBuyGiftModal] = useState(false);
  const [list, setlist] = useState<IPurchasePeopleGiftList>();
  const [visibleToast, setVisibleToast] = useState(false);

  const closeGiftModal = async () => {
    setOpenGiftModal(false);
  };

  const closeNewClientGiftModal = async () => {
    setOpenNewClientGiftModal(false);
  };

  const [clientInformationsData, setClientInformationsData] = useState<IClientInformationsData>({
    id: route.params.paramKey.id,
    name: route.params.paramKey.name,
    gender: route.params.paramKey.gender,
    birth: route.params.paramKey.birth,
    visibleHappyBirthdayImage: route.params.paramKey.visibleHappyBirthdayImage
  });

  const closeNewBuyGiftModal = async () => {
      setOpenNewBuyGiftModal(false);
  };

  const closeGiftModalAndNo = async () => {
    console.log("Event - No");
    setOpenGiftModal(false);
    setOpenNewBuyGiftModal(true);
  };

  const closeGiftModalAndYes = async (list: IPurchasePeopleGiftList) => {
    console.log("Event - Yes - ClientInformations");

    setOpenGiftModal(false);

    setlist(list)
    setOpenNewClientGiftModal(true);

  };

  const Toast = ({ visible, message }) => {
    if (visible) {
      ToastAndroid.showWithGravityAndOffset(
        message,
        ToastAndroid.LONG,
        ToastAndroid.BOTTOM,
        25,
        50
      );
      return null;
    }
    return null;
  };
  
  useEffect(() => setVisibleToast(false), [visibleToast]);

  const eventOpenGiftModal = async () => {
    console.log("Event - Open gift modal");
    if(clientInformationsData.id != 0) {
      setOpenGiftModal(true);
    } else {
      setVisibleToast(true);
    }
  };

  const [responsePurchaseList, setResponsePurchaseList] = useState<IPurchaseList>();
  const [suggestion, setSuggestion] = useState<string>("");

  function getOpacityToHappyBirthdayImage() {
    if(clientInformationsData != null && clientInformationsData != undefined && clientInformationsData.visibleHappyBirthdayImage != null) {
      if (clientInformationsData.visibleHappyBirthdayImage == 2) {
        return 0.2;
      } else if (clientInformationsData.visibleHappyBirthdayImage == 1) {
        return 1;
      }
    } 
    return 0;
  }

  const opacityHappyBirthdayImage = getOpacityToHappyBirthdayImage();

  const eventClientAttendance = async () => {
    console.log("Event - Client attendance");
    let response: any;
    try {
      response = await new ClientSellerAttendance().updateStatusAndEndTime(id);
    } catch (error) {
      console.error("Error to update status", error);
    }

    navigation.navigate("Menu");
  };

  useEffect(() => {
    if(clientInformationsData.id != 0) {
      let clientId = clientInformationsData.id;
      async function getList() {
        let response: IPurchaseList;
        try {
          response = await new ClientService().getPurchaseList(clientId);
        } catch (error) {
          console.error("Error to get client date", error);
        }

        setResponsePurchaseList(response);
      }
      getList();

      async function getSuggestion() {
        let suggestion: string;
        try {
          suggestion = await new PurchaseService().getSuggestion(clientId);
        } catch (error) {
          console.error("Error to get suggestion of gift", error);
        }
        console.log("suggestion: ", suggestion)

        if(suggestion != undefined) {
          setSuggestion(suggestion);
        } else {
          setSuggestion("");
        }
      }
      getSuggestion();
  } else {
    setOpenNewBuyGiftModal(true);
  }


}, [])

  return (
    <Container>
      <HeaderProfile navigation={navigation} />
      {
        <GiftModal
          openGiftModal={openGiftModal}
          closeGiftModal={closeGiftModal}
          closeGiftModalAndNo={closeGiftModalAndNo}
          closeGiftModalAndYes={closeGiftModalAndYes}
          id={clientInformationsData.id}
        />
      }
      {
        <NewClientGiftModal
          openNewClientGiftModal={openNewClientGiftModal}
          closeNewClientGiftModal={closeNewClientGiftModal}
          id={clientInformationsData.id}
          list={list}
        />
      }
      {
        <NewBuyGiftModal
          openNewBuyGiftModal={openNewBuyGiftModal}
          closeNewClientGiftModal={closeNewBuyGiftModal}
        />
      }
      <HappyBirthdayContainer>
        <HappyBirthdayImage style={{opacity: opacityHappyBirthdayImage}} source={require("../../assets/happy-birthday.png")} />
      </HappyBirthdayContainer>
      <Line />
      <ContainerClientName>
        <ClientName>{clientInformationsData.name}</ClientName>
        <LineDescription />
        <ClientDescription>
          {`${clientInformationsData.gender} \n ${clientInformationsData.birth} \n Sugestão: ${suggestion && suggestion}`}
        </ClientDescription>
      </ContainerClientName>

      <ScrollView style={{ marginTop: "6%", maxHeight: "30%", width: "70%" }}>
        <ContainerScrollView>
          {responsePurchaseList && responsePurchaseList.map((value: any, index: number) => (
            <>
              <DescriptionItens key={`${index}`}>
                {`${value.category}  (${value.size}) – ${value.date}`}
              </DescriptionItens>
              <LineItens />
            </>
          ))}
        </ContainerScrollView>
      </ScrollView>

      <Toast visible={visibleToast} message="Opção apenas para clientes cadastrados!" />
      
      <ContainerGifAndButton>
        <TouchableOpacity onPress={() => eventOpenGiftModal()}>
          <GiftGif source={require("../../assets/gift.gif")} />
        </TouchableOpacity>

        <Button onPress={() => eventClientAttendance()}>
          <DescriptionButton>{` Cliente \n Atendido`}</DescriptionButton>
        </Button>
      </ContainerGifAndButton>
    </Container>
  );
};

export default ClientInformations;
