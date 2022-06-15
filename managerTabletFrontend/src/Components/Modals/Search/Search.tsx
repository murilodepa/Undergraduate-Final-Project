import React, { useState, useEffect } from "react";
import { TouchableOpacity, Modal, FlatList } from "react-native";
import { Container, ContainerModal } from "../EditProfile/styles";
import results from "./results";
import ListItem from "../../ListItem/ListItem";

import {
  ContainerSearch,
  SearchInput,
  ContainerButtons,
  CloseButton,
  Input,
  PlaceHolder,
  ContainerSearchImage,
  SearchImage,
  ContainerList,
} from "./styles";

export default function ModalSearch({
  openModalSearch,
  closeModalSearch,
  clientOrSeller,
  buttonColor,
  buttonText,
  resultData,
  closeModalSearchAndBack
}) {
  const [searchText, setSearchText] = useState("");
  const [list, setList] = useState(resultData);

  useEffect(() => {
    if (resultData != null) {
      if (searchText === "") {
        setList(resultData);
      } else {
        setList(
          resultData.filter(
            (item) =>
              item.name.toLowerCase().indexOf(searchText.toLowerCase()) > -1
          )
        );
      }
    }
  }, [searchText]);

  const clearSearchText = async () => {
    console.log("clearSearchText");
    setSearchText("");
  };

  return (
    <Modal animationType="slide" transparent={true} visible={openModalSearch}>
      <Container>
        <ContainerModal>
          <ContainerSearch>
            <SearchInput>
              <ContainerSearchImage>
                <SearchImage source={require("../../../assets/search.png")} />
              </ContainerSearchImage>

              <Input
                maxLength={100}
                keyboardType="default"
                placeholder={"Buscar " + clientOrSeller + "..."}
                style={PlaceHolder}
                value={searchText}
                onChangeText={(text) => setSearchText(text)}
              />
            </SearchInput>
          </ContainerSearch>
          <ContainerButtons>
            <TouchableOpacity onPress={() => closeModalSearch()}>
              <CloseButton
                source={require("../../../assets/window-close.png")}
              />
            </TouchableOpacity>
          </ContainerButtons>

          <ContainerList>
            <FlatList
              data={list}
              style={{ width: 360, alignContent: "center" }}
              renderItem={({ item }) => <ListItem key={item.id} data={item} buttonColor={buttonColor} buttonText={buttonText} clientOrSeller={clientOrSeller} closeModalSearch={closeModalSearchAndBack.bind()} clearSearchText={clearSearchText} />}
              keyExtractor={(item) => item.id}
            />
          </ContainerList>
        </ContainerModal>
      </Container>
    </Modal>
  );
}
