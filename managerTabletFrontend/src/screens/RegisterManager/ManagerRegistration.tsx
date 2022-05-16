import React, { useState } from "react";
import {
  Text,
  StyleSheet,
  View,
  TouchableOpacity,
  BackHandler,
} from "react-native";
import { TextInputMask } from "react-native-masked-text";
import { Picker } from "@react-native-picker/picker";
import { ISellerData } from "../../services/SellerService/SellerServiceInterface";
import validator from "validator";

import {
  Container,
  ContainerHeader,
  ProfileImage,
} from "../Initial/stylesInitial";

import {
  MenuIcon,
  ContainerMiddle,
  ContainerDescription,
  Line,
  LineFooter,
  MenuText,
  MenuButton,
  ManagerPicture,
  CharacteristicText,
  CharacteristicInput,
  ViewTextInput,
  PlaceHolder,
  ContainerRegisterButton,
  RegisterButton,
  ContainerSelect,
  ContainerProfileImage,
  ContainerFooter,
} from "./styles";

const ManagerRegistration = ({ navigation }) => {
  const maskDate = (value: string) => {
    return value
      .replace(/\D/g, "")
      .replace(/(\d{2})(\d)/, "$1/$2")
      .replace(/(\d{2})(\d)/, "$1/$2")
      .replace(/(\d{4})(\d)/, "$1");
  };

  const validateDate = (value) => {
    if (validator.isDate(value)) {
      console.log("Date is validated! - Date", sellerData.birth);
    } else {
      console.log("Date is invalid!");
    }
  };

  const genderList = ["Feminino", "Masculino", "Outros"];
  const sectorList = [
    "Camiseta",
    "Calça",
    "Vestido",
    "Blusas",
    "Roupas Íntimas",
    "Saia",
    "Short",
  ];
  const [sellerData, setSellerData] = useState<ISellerData>("");

  return (
    <Container>
      <ContainerHeader>
        <ProfileImage
          source={require("../../assets/profile-image-setting.png")}
        />
      </ContainerHeader>
      <ContainerMiddle>
        <ContainerDescription>
          <ContainerProfileImage style={{ alignContent: "center" }}>
            <ManagerPicture
              source={require("../../assets/profile-image-setting.png")}
            />
          </ContainerProfileImage>

          <Line />

          <ViewTextInput>
            <CharacteristicText> Nome: </CharacteristicText>
            <CharacteristicInput
              maxLength={100}
              keyboardType="default"
              placeholder=" Nome..."
              onChangeText={(value) =>
                setSellerData({
                  ...sellerData,
                  name: value,
                })
              }
              style={PlaceHolder}
            />
          </ViewTextInput>

          <ViewTextInput>
            <CharacteristicText> Genêro: </CharacteristicText>
            <ContainerSelect>
              <Picker
                selectedValue={sellerData.gender}
                onValueChange={(itemValue) =>
                  setSellerData({ ...sellerData, gender: itemValue })
                }
              >
                {genderList.map((gender, key) => {
                  return (
                    <Picker.Item
                      style={{ fontSize: 24 }}
                      label={gender}
                      value={gender}
                      key={key}
                    />
                  );
                })}
              </Picker>
            </ContainerSelect>
          </ViewTextInput>

          <ViewTextInput>
            <CharacteristicText>
              {"     "}
              Data de {"\n"} Nascimento:{" "}
            </CharacteristicText>
            <TextInputMask
              type={"datetime"}
              options={{
                format: "DD/MM/YYYY",
              }}
              placeholder=" dd/mm/aaaa"
              value={sellerData.birth}
              onChangeText={(value) =>
                setSellerData({
                  ...sellerData,
                  birth: maskDate(value),
                })
              }
              style={[PlaceHolder, { width: 175 }]}
            />
            {console.log("name: ", sellerData.name)}
            {console.log("gender: ", sellerData.gender)}
            {console.log("data: ", sellerData.birth)}
            {console.log("sector: ", sellerData.sector)}
          </ViewTextInput>

          <ViewTextInput>
            <CharacteristicText> Setor: </CharacteristicText>
            <ContainerSelect>
              <Picker
                selectedValue={sellerData.sector}
                onValueChange={(itemValue) =>
                  setSellerData({ ...sellerData, sector: itemValue })
                }
              >
                {sectorList.map((sector, key) => {
                  return (
                    <Picker.Item
                      style={{ fontSize: 24 }}
                      label={sector}
                      value={sector}
                      key={key}
                    />
                  );
                })}
              </Picker>
            </ContainerSelect>
          </ViewTextInput>
          <LineFooter />

          <ContainerRegisterButton>
            <TouchableOpacity onPress={() => {}}>
              <RegisterButton style={{ textAlignVertical: "center" }}>
                {" "}
                Cadastrar{" "}
              </RegisterButton>
            </TouchableOpacity>
          </ContainerRegisterButton>
        </ContainerDescription>
      </ContainerMiddle>

      <ContainerFooter>
        <MenuButton style={{ flexDirection: "row" }}>
          <MenuIcon source={require("../../assets//menu.png")} />
          <MenuText> Menu </MenuText>
        </MenuButton>
      </ContainerFooter>
    </Container>
  );
};

export default ManagerRegistration;
