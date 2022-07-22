import React, { useEffect, useState, useRef } from "react";
import { TouchableOpacity, BackHandler, TextInput } from "react-native";
import { ISellerEmailPassword } from "../../services/SellerService/SellerServiceInterface";
import { Ionicons } from "@expo/vector-icons";
import { useGlobalContext } from "../../context/SellerContext";

import {
  Container,
  Logo,
  LoginText,
  ContainerLogin,
  ContainerInput,
  LoginButton,
  LoginButtonText,
  InvalidText,
  PlaceHolder,
  ContainerPasswordInput,
  InputPassword,
} from "./styles";

const Initial = ({ navigation }) => {
  useEffect(() => {
    BackHandler.addEventListener("hardwareBackPress", () => {
      return true;
    });
  }, []);

  const [sellerEmailPassword, setSellerEmailPassword] =
    useState<ISellerEmailPassword>();
  const [hideTextInvalidPassword, setHideTextInvalidPassword] = useState(false);
  const [hidePassword, setHidePassword] = useState(true);
  const { setName, setProfileImage } = useGlobalContext();
  const ContainerEmailInputRef = useRef(null);
  const ContainerPasswordInputRef = useRef(null);

  const eventLogin = async () => {
    setName("Murilo Araujo");
    //setProfileImage()

   /* if (parseInt(sellerEmailPassword.password) == 1234) {
      setHideTextInvalidPassword(true);
    }*/

    if (
      sellerEmailPassword.email != "" &&
      sellerEmailPassword.email != undefined &&
      sellerEmailPassword.password != "" &&
      sellerEmailPassword.password != undefined
    ) {
      console.log("E-mail: ", sellerEmailPassword.email);
      console.log("Password: ", sellerEmailPassword.password);

      ContainerEmailInputRef.current.clear();
      ContainerPasswordInputRef.current.clear();
      sellerEmailPassword.email = "";
      sellerEmailPassword.password = "";
      navigation.navigate("Menu");
    }
  };

  return (
    <Container>
      <Logo source={require("../../assets/venda-mais-logo.png")} />
      <LoginText>Faça seu Login</LoginText>

      <ContainerLogin>
        <ContainerInput
          ref={ContainerEmailInputRef}
          maxLength={100}
          keyboardType="default"
          placeholder="E-mail"
          clearButtonMode="always"
          style={PlaceHolder}
          onChangeText={(value) =>
            setSellerEmailPassword({
              ...sellerEmailPassword,
              email: value,
            })
          }
        />
        <ContainerPasswordInput>
          <InputPassword
            ref={ContainerPasswordInputRef}
            maxLength={100}
            keyboardType="default"
            placeholder="Senha"
            style={PlaceHolder}
            onChangeText={(value) =>
              setSellerEmailPassword({
                ...sellerEmailPassword,
                password: value,
              })
            }
            secureTextEntry={hidePassword}
          />
          <TouchableOpacity onPress={() => setHidePassword(!hidePassword)}>
            {hidePassword ? (
              <Ionicons name="eye-off" color="#000000" size={25} />
            ) : (
              <Ionicons name="eye" color="#000000" size={25} />
            )}
          </TouchableOpacity>
        </ContainerPasswordInput>
      </ContainerLogin>

      <InvalidText>
        {" "}
        {hideTextInvalidPassword ? "Usuário ou Senha Inválido" : null}{" "}
      </InvalidText>

      <LoginButton onPress={() => eventLogin()}>
        <LoginButtonText>Entrar</LoginButtonText>
      </LoginButton>
    </Container>
  );
};

export default Initial;
