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
import { useGlobalContext } from "../../context/managerContext";
import { IClientIdNameImageList } from "../../services/SendImageClientService/SendImageClientServiceInterface";
import { SendImageClientService } from "../../services/SendImageClientService/SendImageClientService";

const ClientInputs = (props: any) => {
  function formataStringData(data: string) {
    var day = data.split("/")[0];
    var month = data.split("/")[1];
    var year = data.split("/")[2];
    return year + "-" + ("0" + month).slice(-2) + "-" + ("0" + day).slice(-2);
  }

  function validateYear(data: string) {
    var year = parseInt(data.split("/")[2]);
    const currentYear = new Date().getFullYear();
    if(year > currentYear || year < (currentYear-120)) {
      return false;
    }
    return true;
  }

  const regexDate =
    /^(((0[1-9]|[12][0-9]|30)[-/]?(0[13-9]|1[012])|31[-/]?(0[13578]|1[02])|(0[1-9]|1[0-9]|2[0-8])[-/]?02)[-/]?[0-9]{4}|29[-/]?02[-/]?([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048]|0[0-9]|1[0-6])00))$/;
  
    function validateDate(date: any) {
    if (regexDate.test(date) && validateYear(date)) {
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
  const { setResultClientData } = useGlobalContext();

  async function getClientData() {
    let response: IClientIdNameImageList;
    try {
      response = await new SendImageClientService().getClientIdNameImage();
    } catch (error) {
      console.error("Error to get seller date", error);
    }
    setResultClientData(response);
  };

  function clone(obj: string) {

    // Handle the 3 simple types, and null or undefined
    if (null == obj || "object" != typeof obj) return obj;
  }

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
        console.log("clientData", clientData);

        try {
          const response = await new ClientService().updateClient(clientData);
        } catch (error) {
          console.error("Error to edit!", error);
        }
        if (props.placeholderInputs.name != clientData.name) {
          getClientData();
        }
        props.closeEditClientProfileAndBack();
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

function structuredClone(object: any) {
  throw new Error("Function not implemented.");
}
