import React, { useEffect, useState } from "react";
import date from "./date";
import { ScrollView, TouchableOpacity } from "react-native";

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
  ContainerGifAndButton
} from "./styles";
import { IClientInformationsData, IPurchaseList } from "../../services/ClientService/ClientServiceInterface";
import { ClientSellerAttendance } from "../../services/ClientSellerAttendance/ClientSellerAttendance";
import { useGlobalContext } from "../../context/SellerContext";
import { ClientService } from "../../services/ClientService/ClientService";
import GiftModal from "../../components/Modals/GiftModal/GiftModal";

const ClientInformations = ({ navigation, route }: any) => {
  const { id } = useGlobalContext();

  const [openGiftModal, setOpenGiftModal] = useState(false);

  const closeGiftModal = async () => {
    setOpenGiftModal(false);
  };

  const eventOpenGiftModal = async () => {
    console.log("Event - Open gift modal");
    setOpenGiftModal(true);
  };

  const [clientInformationsData, setClientInformationsData] = useState<IClientInformationsData>({
    id: route.params.paramKey.id,
    name: route.params.paramKey.name,
    gender: route.params.paramKey.gender,
    birth: route.params.paramKey.birth
  });

  const [responsePurchaseList, setResponsePurchaseList] = useState<IPurchaseList>();

  const eventClientAttendance = async () => {
    console.log("Event - Client attendance");
    let response: any;
    try {
      response = await new ClientSellerAttendance().updateStatusAndEndTime(clientInformationsData.id, id);
    } catch (error) {
      console.error("Error to update status", error);
    }

    navigation.navigate("Menu");
  };

  useEffect(() => {
    async function getList() {

      try {
        setResponsePurchaseList(await new ClientService().getPurchaseList(clientInformationsData.id));
      } catch (error) {
        console.error("Error to get client date", error);
      }
    }
    getList();
  }, [])

  return (
    <Container>
      <HeaderProfile navigation={navigation} />

      {
        <GiftModal
          openGiftModal={openGiftModal}
          closeGiftModal={closeGiftModal}
        />
      }

      <Line />
      <ContainerClientName>
        <ClientName>{clientInformationsData.name}</ClientName>
        <LineDescription />
        <ClientDescription>
          {`${clientInformationsData.gender} \n ${clientInformationsData.birth} anos \n Sugestão: Camiseta`}
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

      <ContainerGifAndButton>
        <TouchableOpacity onPress={() => eventOpenGiftModal()}>
          <GiftGif source={require("../../assets/gift.gif")} />
        </TouchableOpacity>

        <Button onPress={() => eventClientAttendance()}>
          <DescriptionButton> Cliente Atendido </DescriptionButton>
        </Button>
      </ContainerGifAndButton>
    </Container>
  );
};

export default ClientInformations;
