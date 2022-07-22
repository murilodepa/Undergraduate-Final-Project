import styled from "styled-components/native";

export const Container = styled.View`
  flex: 1;
  align-items: center;
  background-color: #c4c4c4;
`;

export const Logo = styled.Image`
  margin-top: 15%;
  height: 100px;
  width: 330px;
`;

export const ContainerLogin = styled.View`
  align-items: center;
  max-width: 90%;
  max-height: 205px;
  width: 90%;
  height: 205px;
  border-radius: 20px;
  border-width: 3px;
  border-color: #000000;
  background-color: #6BB1F1;
  margin-top: 11%;
`;

export const ContainerInput = styled.TextInput`
  align-items: center;
  max-width: 90%;
  max-height: 48px;
  width: 90%;
  height: 48px;
  border-radius: 10px;
  border-width: 1px;
  border-color: #000000;
  background-color: #FFFFFF;
  margin-top: 11%;
`;
export const ContainerPasswordInput = styled.View`
  flex-direction: row;
  align-items: center;
  max-width: 90%;
  max-height: 48px;
  width: 90%;
  height: 48px;
  border-radius: 10px;
  border-width: 1px;
  border-color: #000000;
  background-color: #FFFFFF;
  margin-top: 11%;
`;

export const InputPassword = styled.TextInput`
  align-items: center;
  max-width: 87%;
  max-height: 45px;
  width: 87%;
  height: 45px;
  border-radius: 10px;
`;

export const LoginText = styled.Text`
  font-size: 40px;
  text-align: center;
  color: #000000;
  font-weight: bold;
  margin-top: 10%;
`;

export const LoginButton = styled.TouchableOpacity`
  width: 60%;
  height: 8%;
  background-color: #0085FF;
  justify-content: center;
  align-items: center;
  border-radius: 20px;
  border-width: 2px;
  border-color: #000000;
  margin-top: 20%;
`;

export const InvalidText = styled.Text`
  font-size: 20px;
  text-align: center;
  color: red;
  font-weight: bold;
  margin-top: 5%;
`;

export const LoginButtonText = styled.Text`
  font-size: 32px;
  text-align: center;
  color: #FFFFFF;
`;

export const PlaceHolder = {
  fontSize: 20, 
  width: 360,
  paddingLeft: 10,
  paddingRight: 10,
  justifyContent: "center",
  alignItems: "center"
}

