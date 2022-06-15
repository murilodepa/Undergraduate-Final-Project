import React, { useEffect, useState } from "react";
import { TouchableOpacity, BackHandler } from "react-native";
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
  const [hidePassword, setHidePassword] = useState(false);
  const { setName, setProfileImage } = useGlobalContext();

  const eventLogin = async () => {
    console.log("E-mail: ", sellerEmailPassword.email);
    console.log("Password: ", sellerEmailPassword.password);

    setName("Murilo")
    //setProfileImage()

    if (parseInt(sellerEmailPassword.password) == 1234) {
      setHideTextInvalidPassword(true);
    }
    navigation.navigate("Menu");
  };

  return (
    <Container>
      <Logo source={require("../../assets/venda-mais-logo.png")} />
      <LoginText>Faça seu Login</LoginText>

      <ContainerLogin>
        <ContainerInput
          maxLength={100}
          keyboardType="default"
          placeholder="E-mail"
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
              <Ionicons name="eye" color="#000000" size={25} />
            ) : (
              <Ionicons name="eye-off" color="#000000" size={25} />
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
