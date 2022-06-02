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
}) {
  const [searchText, setSearchText] = useState("");
  const [list, setList] = useState(results);

  useEffect(() => {
    if (searchText === "") {
      setList(results);
    } else {
      setList(
        results.filter(
          (item) =>
            item.name.toLowerCase().indexOf(searchText.toLowerCase()) > -1
        )
      );
    }
  }, [searchText]);

  // Ordenar a lista de nomes
  const handleOrderList = () => {
    let newList = [...results];

    newList.sort((x, y): any =>
      x.name > y.name ? 1 : y.name > x.name ? -1 : 0
    );

    setList(newList);
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
                placeholder={"Buscar " + clientOrSeller}
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
              style={{ width: 350, marginLeft: 100}}
              renderItem={({ item }) => <ListItem data={item} />}
              keyExtractor={(item) => item.id}
            />
          </ContainerList>
        </ContainerModal>
      </Container>
    </Modal>
  );
}
