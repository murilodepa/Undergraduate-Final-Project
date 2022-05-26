import React, { useState } from "react";
import { TouchableOpacity } from "react-native";
import { TextInputMask } from "react-native-masked-text";
import { Picker } from "@react-native-picker/picker";
import { ClientService } from "../../services/ClientService/ClientService";
import { IClientData } from '../../services/ClientService/ClientServiceInterface'

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
} from "./styles";

const ClientInputs = (props: any) => {
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

  const regexDate =
    /^(((0[1-9]|[12][0-9]|30)[-/]?(0[13-9]|1[012])|31[-/]?(0[13578]|1[02])|(0[1-9]|1[0-9]|2[0-8])[-/]?02)[-/]?[0-9]{4}|29[-/]?02[-/]?([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048]|0[0-9]|1[0-6])00))$/;
  function validateDate(date: any) {
    if (regexDate.test(date)) {
      const birth = formataStringData(date);
      setClientData({ ...clientData, birth: date });
      console.log("Date is validated! - Date", birth);
      return true;
    } else {
      console.log("Date is invalid!");
      return false;
    }
  }

  const regexNumber = /[0-9]/;
  const genderList = ["Feminino", "Masculino", "Outros"];
  const [clientData, setClientData] = useState<IClientData>(props.clientData);
  const [inputNameColor, setInputNameColor] = useState("black");
  const [inputBirthColor, setInputBirthColor] = useState("black");
  const [inputGenderColor, setInputGenderColor] = useState("black");

  const eventCaptureOrEdit = async () => {
    var count = 0;
    if (
        clientData.name != undefined &&
        clientData.name.length > 3 &&
      !regexNumber.test(clientData.name)
    ) {
      console.log("Name is valid! +1");
      setInputNameColor("black");
      count++;
    } else {
      setInputNameColor("red");
      count--;
    }
    if (clientData.gender == undefined || clientData.gender == null) {
      setInputGenderColor("red");
      count--;
    } else {
      setInputGenderColor("black");
      console.log("Gender is valid! +1");
      count++;
    }
    if (clientData.birth != undefined && validateDate(clientData.birth)) {
      setInputBirthColor("black");
      count++;
    } else {
      setInputBirthColor("red");
      count = count - 1;
    }
    if (count == 3) {
      if (props.buttonName == "Cadastrar") {
        try {
          const response = await new ClientService().insertClient(clientData);
        } catch (error) {
          console.error("Falha no cadastro", error);
        }
        props.navigation.navigate("Capture", { paramKey: "client" });
      } else {
    /*    try {
          const response = await new ClientService().updateClient(clientData);
        } catch (error) {
          console.error("Error to edit!", error);
        }*/
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
            setClientData({
              ...clientData,
              name: value,
            })
          }
        />
      </ViewTextInput>
      <ViewTextInput>
        <CharacteristicText> Genêro: </CharacteristicText>
        <ContainerSelect style={{ borderColor: inputGenderColor }}>
          <Picker
            selectedValue={clientData.gender}
            onValueChange={(itemValue) =>
              setClientData({ ...clientData, gender: itemValue })
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
          value={clientData.birth}
          onChangeText={(value) =>
            setClientData({
              ...clientData,
              birth: value,
            })
          }
          style={[PlaceHolder, { width: 175, borderColor: inputBirthColor }]}
        />
        {console.log("name: ", clientData.name)}
        {console.log("gender: ", clientData.gender)}
        {console.log("data: ", clientData.birth)}
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

export default ClientInputs;