import React, { useState } from "react";
import { TouchableOpacity } from "react-native";
import { TextInputMask } from "react-native-masked-text";
import { Picker } from "@react-native-picker/picker";
import { SellerService } from "../../services/SellerService/SellerService";
import { ISellerData } from "../../services/SellerService/SellerServiceInterface";
import { Ionicons } from "@expo/vector-icons";

import {
  Line,
  LineFooter,
  ManagerPicture,
  CharacteristicText,
  CharacteristicInput,
  ViewTextInput,
  PlaceHolder,
  ContainerRegisterButton,
  RegisterButton,
  ContainerSelect,
  ContainerProfileImage,
  ContainerPasswordInput,
  PasswordInput,
} from "./styles";

const SellerInputs = (props: any) => {
  const maskDate = (value: string) => {
    return value
      .replace(/\D/g, "")
      .replace(/(\d{2})(\d)/, "$1/$2")
      .replace(/(\d{2})(\d)/, "$1/$2")
      .replace(/(\d{4})(\d)/, "$1");
  };

  function formataStringData(data: string) {
    var dia = data.split("/")[0];
    var mes = data.split("/")[1];
    var ano = data.split("/")[2];
    return ano + "-" + ("0" + mes).slice(-2) + "-" + ("0" + dia).slice(-2);
  }

  const regexEmail =
    /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;
  const regexDate =
    /^(((0[1-9]|[12][0-9]|30)[-/]?(0[13-9]|1[012])|31[-/]?(0[13578]|1[02])|(0[1-9]|1[0-9]|2[0-8])[-/]?02)[-/]?[0-9]{4}|29[-/]?02[-/]?([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048]|0[0-9]|1[0-6])00))$/;
  function validateDate(date: any) {
    if (regexDate.test(date)) {
      const birth = formataStringData(date);
      setSellerData({ ...sellerData, birth: date });
      console.log("Date is validated! - Date", birth);
      return true;
    } else {
      console.log("Date is invalid!");
      return false;
    }
  }

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
  const [sellerData, setSellerData] = useState<ISellerData>(props.sellerData);
  const [inputNameColor, setInputNameColor] = useState("black");
  const [inputBirthColor, setInputBirthColor] = useState("black");
  const [inputEmailColor, setInputEmailColor] = useState("black");
  const [inputPasswordColor, setInputPasswordColor] = useState("black");
  const [inputGenderColor, setInputGenderColor] = useState("black");
  const [inputSectorColor, setInputSectorColor] = useState("black");
  const [hidePassword, setHidePassword] = useState(true);

  const eventCaptureOrEdit = async () => {
    var count = 0;
    if (
      sellerData.name != undefined &&
      sellerData.name.length > 3 &&
      !regexNumber.test(sellerData.name)
    ) {
      console.log("Name is valid! +1");
      setInputNameColor("black");
      count++;
    } else {
      setInputNameColor("red");
      count--;
    }
    if (sellerData.gender == undefined || sellerData.gender == null) {
      setInputGenderColor("red");
      count--;
    } else {
      setInputGenderColor("black");
      console.log("Gender is valid! +1");
      count++;
    }
    if (sellerData.sector == undefined || sellerData.sector == null) {
      setInputSectorColor("red");
      count--;
    } else {
      setInputSectorColor("black");
      console.log("Sector is valid! +1");
      count++;
    }
    if (sellerData.birth != undefined && validateDate(sellerData.birth)) {
      setInputBirthColor("black");
      count++;
    } else {
      setInputBirthColor("red");
      count = count - 1;
    }
    if (
      sellerData.email != undefined &&
      sellerData.email.length > 5 &&
      regexEmail.test(sellerData.email)
    ) {
      console.log("Email is valid! +1");
      setInputEmailColor("black");
      count++;
    } else {
      setInputEmailColor("red");
      count--;
    }
    if (
      props.buttonName == "Cadastrar" ||
      (props.buttonName != "Cadastrar" &&
        (sellerData.password != undefined || sellerData.password != null))
    ) {
      if (sellerData.password != undefined && sellerData.password.length > 5) {
        console.log("Password is valid!");
        setInputPasswordColor("black");
        count++;
      } else {
        setInputPasswordColor("red");
        count--;
      }
    } else {
      setInputPasswordColor("black");
      count++;
    }
    if (count == 6) {
      if (props.buttonName == "Cadastrar") {
        try {
          const response = await new SellerService().insertSeller(sellerData);
        } catch (error) {
          console.error("Falha no cadastro", error);
        }
        props.navigation.navigate("Capture", { paramKey: "seller" });
      } else {
        try {
          const response = await new SellerService().updateSeller(sellerData);
        } catch (error) {
          console.error("Error to edit!", error);
        }
        console.log("EDITOOU");
      }
    }
  };

  return (
    <>
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
          placeholder={props.placeholderInputs.name}
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
        <ContainerSelect style={{ borderColor: inputGenderColor }}>
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
          {" "}
          Data de {"\n"} Nascimento:{" "}
        </CharacteristicText>
        <TextInputMask
          type={"datetime"}
          options={{
            format: "DD/MM/YYYY",
          }}
          placeholder={props.placeholderInputs.birth}
          value={sellerData.birth}
          onChangeText={(value) =>
            setSellerData({
              ...sellerData,
              birth: value,
            })
          }
          style={[PlaceHolder, { width: 175, borderColor: inputBirthColor }]}
        />
        {console.log("name: ", sellerData.name)}
        {console.log("gender: ", sellerData.gender)}
        {console.log("data: ", sellerData.birth)}
        {console.log("sector: ", sellerData.sector)}
        {console.log("email: ", sellerData.email)}
        {console.log("password: ", sellerData.password)}
      </ViewTextInput>
      <ViewTextInput>
        <CharacteristicText> Setor: </CharacteristicText>
        <ContainerSelect style={{ borderColor: inputSectorColor }}>
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
      <ViewTextInput>
        <CharacteristicText> E-mail: </CharacteristicText>
        <CharacteristicInput
          maxLength={100}
          keyboardType="default"
          placeholder={props.placeholderInputs.email}
          style={[PlaceHolder, { borderColor: inputEmailColor }]}
          onChangeText={(value) =>
            setSellerData({
              ...sellerData,
              email: value,
            })
          }
        />
      </ViewTextInput>
      <ViewTextInput>
        <CharacteristicText> Senha: </CharacteristicText>
        <ContainerPasswordInput style={{ borderColor: inputPasswordColor }}>
          <PasswordInput
            maxLength={100}
            keyboardType="default"
            placeholder="********"
            style={[PlaceHolder, { borderWidth: 0, width: 200 }]}
            onChangeText={(value) =>
              setSellerData({
                ...sellerData,
                password: value,
              })
            }
            secureTextEntry={hidePassword}
          />
          <TouchableOpacity onPress={() => setHidePassword(!hidePassword)}>
            {hidePassword ? (
              <Ionicons name="eye" color="#000000" size={30} />
            ) : (
              <Ionicons name="eye-off" color="#000000" size={30} />
            )}
          </TouchableOpacity>
        </ContainerPasswordInput>
      </ViewTextInput>
      <LineFooter />
      <ContainerRegisterButton>
        <TouchableOpacity onPress={() => eventCaptureOrEdit()}>
          <RegisterButton style={{ textAlignVertical: "center" }}>
            {props.buttonName}
          </RegisterButton>
        </TouchableOpacity>
      </ContainerRegisterButton>
    </>
  );
};

export default SellerInputs;
