import React, { useEffect, useState, useRef } from "react";
import { TouchableOpacity, BackHandler, TextInput } from "react-native";
import { ISellerData, ISellerEmailPassword } from "../../services/SellerService/SellerServiceInterface";
import { Ionicons } from "@expo/vector-icons";
import { useGlobalContext } from "../../context/SellerContext";
import Loading from "../../components/Loading/Loading";

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
import { SellerService } from "../../services/SellerService/SellerService";

const Initial = ({ navigation }) => {
  useEffect(() => {
    BackHandler.addEventListener("hardwareBackPress", () => {
      return true;
    });
  }, []);


  const regexEmail =
    /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;

  const [sellerEmailPassword, setSellerEmailPassword] =
    useState<ISellerEmailPassword>();
  const [hideTextInvalidPassword, setHideTextInvalidPassword] = useState(false);
  const [hidePassword, setHidePassword] = useState(true);
  const { setId, setName, setProfileImage, setGender, setBirth, setSector, setAvailable, setAttendances, setEmail } = useGlobalContext();
  const ContainerEmailInputRef = useRef(null);
  const ContainerPasswordInputRef = useRef(null);
  const [inputEmailColor, setInputEmailColor] = useState("black");
  const [inputPasswordColor, setInputPasswordColor] = useState("black");
  const [visibleLoading, setVisibleLoading] = useState(false);

  const eventLogin = async () => {
    var count = 0;
    //setProfileImage()

    /* if (parseInt(sellerEmailPassword.password) == 1234) {
       setHideTextInvalidPassword(true);
     }*/

    if (
      sellerEmailPassword.email != "" &&
      sellerEmailPassword.email != undefined &&
      sellerEmailPassword.email != null &&
      sellerEmailPassword.email.length > 5 &&
      regexEmail.test(sellerEmailPassword.email)
    ) {
      console.log("Email is valid! +1");
      setInputEmailColor("black");
      count++;
    } else {
      setInputEmailColor("red");
      count--;
    }
    if (
      sellerEmailPassword.password != "" &&
      sellerEmailPassword.password != undefined &&
      sellerEmailPassword.password != null &&
      sellerEmailPassword.password.length > 5
    ) {
      console.log("Password is valid! +1");
      setInputPasswordColor("black");
      count++;
    } else {
      setInputPasswordColor("red");
      count--;
    }

    if (count == 2) {
      console.log("Sign-in!");
      setVisibleLoading(true);
      setInputEmailColor("black");
      setInputPasswordColor("black");

      let userRegistered: boolean = true;
      let response: ISellerData;
      try {
        response = await new SellerService().getRegisteredSeller(sellerEmailPassword.email, sellerEmailPassword.password);
      } catch (error) {
        console.error("Error to get seller registered date", error);
        userRegistered = false;
      }

      if (userRegistered) {
        setId(response.id)
        setName(response.name);
        setProfileImage(response.profileImage);
        setGender(response.gender);
        setBirth(response.birth);
        setSector(response.sector);
        setAvailable(response.available);
        setAttendances(response.attendances);
        setEmail(response.email);
        ContainerEmailInputRef.current.clear();
        ContainerPasswordInputRef.current.clear();
        setHideTextInvalidPassword(false);
        sellerEmailPassword.email = "";
        sellerEmailPassword.password = "";
        setVisibleLoading(false);
        navigation.navigate("Menu");
      } else {
        console.log("User not registered!");
        setInputEmailColor("red");
        setInputPasswordColor("red");
        setHideTextInvalidPassword(true);
        setVisibleLoading(false);
      }

    }
  };

  return (
    <Container>
      <Loading visible={visibleLoading} />
      <Logo source={require("../../assets/venda-mais-logo.png")} />
      <LoginText>Faça seu Login</LoginText>

      <ContainerLogin>
        <ContainerInput
          ref={ContainerEmailInputRef}
          maxLength={100}
          keyboardType="default"
          placeholder="E-mail"
          clearButtonMode="always"
          style={[PlaceHolder, { borderColor: inputEmailColor }]}
          onChangeText={(value) =>
            setSellerEmailPassword({
              ...sellerEmailPassword,
              email: value,
            })
          }
        />
        <ContainerPasswordInput style={{ borderColor: inputPasswordColor }}>
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
