import styled from "styled-components/native";

export const Container = styled.View`
  flex: 1;
  justify-content: center;
  background-color: #c4c4c4;
`;

export const ContainerProfileImage = styled.View`
  flex: 1;
  justify-content: center;
  align-items: center;
  width: auto;
  max-height: 250px;
`;

export const Line = styled.View`
  flex: 1;
  background-color: #FFFFFF;
  max-height: 3px;
  margin-bottom: 30px;
`

export const LineFooter = styled.View`
  flex: 1;
  background-color: #FFFFFF;
  max-height: 3px;
`

export const ContainerHeader = styled.View`
  background-color: #b5b4b4;
  height: 140px;
  width: 100%;
  justify-content: center;
  align-items: flex-start;
  margin-right: 20px;
  margin-top: 20px;
`;

export const CharacteristicText = styled.Text`
  font-size: 30px;
  font-weight: bold;
  justify-content: center;
  align-items: center;
  margin-left: 30px;
`;

export const CharacteristicInput = styled.TextInput`
  font-size: 28px;
  font-weight: bold;
  border-color: #000000;
  border-radius: 20px;
  width: 250px;
  margin-left: 5px;
`;

export const PasswordInput = styled.TextInput`
  font-size: 28px;
  font-weight: bold;
  border-radius: 20px;
  margin-left: 5px;
`;

export const ContainerPasswordInput = styled.View`
  flex-direction: row;
  align-items: center;
  font-size: 28px;
  font-weight: bold;
  border-color: #000000;
  border-radius: 20px;
  border-width: 2px;
  width: 250px;
  margin-left: 5px;
`;

export const PlaceHolder = {
  fontSize: 24, 
  backgroundColor: "#6bb1f1", 
  borderWidth: 2, 
  borderRadius: 20, 
  width: 360,
  paddingLeft: 10,
  paddingRight: 10,
  justifyContent: "center",
  alignItems: "center"
}

export const ViewTextInput = styled.View`
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  margin-bottom: 30px;
`;

export const ManagerPicture = styled.Image`
  height: 190px;
  width: 190px;
  justify-content: center;
  align-items: center;
`;

export const ContainerSelect = styled.View`
  border-width: 2px;
  border-radius: 20px;
  width: 180px;
  height: 35px;
  justify-content: center;
  border-color: #000000;
`;

export const ContainerRegisterButton = styled.View`
  justify-content: center;
  align-items: center;
  height: 90px;
`;

export const RegisterButton = styled.Text`
  width: 200px;
  height: 50px;
  color: #FFFFFF;
  font-size: 30px;
  background-color: #000000;
  border-radius: 30px;
  text-align: center;
  align-items: center;
  justify-content: center;
  border-color: #FFFFFF;
  border-width: 2px;
`;