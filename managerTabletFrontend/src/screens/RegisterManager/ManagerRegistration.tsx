import React, { useState } from "react";
import { TouchableOpacity } from "react-native";
import { TextInputMask } from "react-native-masked-text";
import { Picker } from "@react-native-picker/picker";
import { ISellerData } from "../../services/SellerService/SellerServiceInterface";

import {
  Container,
  ContainerHeader,
  ProfileImage,
} from "../Initial/InitialStyles";

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

  function formataStringData(data: string) {
    var dia  = data.split("/")[0];
    var mes  = data.split("/")[1];
    var ano  = data.split("/")[2];
    return ano + '-' + ("0"+mes).slice(-2) + '-' + ("0"+dia).slice(-2);
  }
  
  console.log(formataStringData('02/03/2018'));
  
  const regexDate = /^(((0[1-9]|[12][0-9]|30)[-/]?(0[13-9]|1[012])|31[-/]?(0[13578]|1[02])|(0[1-9]|1[0-9]|2[0-8])[-/]?02)[-/]?[0-9]{4}|29[-/]?02[-/]?([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048]|0[0-9]|1[0-6])00))$/
  function validateDate (date: any) {

    if(regexDate.test(date)){
      const birth = formataStringData(date)
      setSellerData({...sellerData, birth: date})
      console.log("Date is validated! - Date", birth);
      return true;
    } else {
      console.log("Date is invalid!");
      return false;
    }
  };

  const regexNumber = /[0-9]/;
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
  const [inputNameColor, setInputNameColor] = useState("black");
  const [inputBirthColor, setInputBirthColor] = useState("black");

  const eventCapture = async () => {
    var count = 0;
    if (
      sellerData.name != undefined &&
      sellerData.name.length > 3 &&
      !regexNumber.test(sellerData.name)
    ) {
      console.log("Name is valid!");
      setInputNameColor("black");
      count++;
    } else {
      setInputNameColor("red");
      count--;
    }
    if (sellerData.gender == undefined) {
      setSellerData({...sellerData, gender: genderList[0]})
      console.log("Gender is valid!");
    }
    if (sellerData.sector == undefined) {
      setSellerData({...sellerData, sector: sectorList[0]})
      console.log("Sector is valid!");
    }
    if (sellerData.birth != undefined && validateDate(sellerData.birth)) {
      setInputBirthColor("black");
      count++;
    } else {
      setInputBirthColor("red");
      count--;
    }
    if (count == 2) {
      navigation.navigate("Capture", { paramKey: "seller" });
    }
  };

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
              style={[PlaceHolder, { borderColor: inputNameColor }]}
              onChangeText={(value) =>
                setSellerData({
                  ...sellerData,
                  name: value,
                })
              }
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
                  birth: value,
                })
              }
              style={[
                PlaceHolder,
                { width: 175, borderColor: inputBirthColor },
              ]}
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
            <TouchableOpacity onPress={() => eventCapture()}>
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
