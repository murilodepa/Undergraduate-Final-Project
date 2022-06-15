import styled from "styled-components/native";

export const ContainerItem = styled.View`
  align-items: center;
  background-color: #DEDEDE;
  max-width: 350px;
  width: 350px;
  max-height: 280px;
  height: 280px;
  margin-bottom: 40px;
  border-color: #000000;
  border-width: 3px;
`;

export const ProfileImage = styled.Image`
  justify-content: center;
  max-width: 130px;
  max-height: 130px;
  height: 130px;
  width: 130px;
  align-items: center;
  justify-content: center;
  border-radius: 80px;
  border-width: 2px;
  border-color: #000000;
  margin-top: 5%;
`;

export const NameText = styled.Text`
font-size: 24px;
font-weight: bold;
color: #000000;
margin-top: 5%
`;

export const ButtonSelect = styled.TouchableOpacity`
border-color: #000000;
border-width: 2px;
border-radius: 20px;
width: 200px;
height: 48px;
justify-content: center;
align-items: center;
text-align: center;
margin-top: 5%
`;

export const ButtonSelectText = styled.Text`
font-size: 28px;
font-weight: bold;
color: #FFFFFF;
`;

