import styled from "styled-components/native";

export const Container = styled.View`
  flex: 1;
  align-items: center;
  background-color: #c4c4c4;
`;

export const Line = styled.View`
  flex: 1;
  background-color: #FFFFFF;
  max-width: 100%;
  width: 100%;
  max-height: 3px;
  height: 3px;
`

export const ClientImage = styled.Image`
  margin-top: 10%;
  margin-bottom: 10%;
  height: 190px;
  width: 190px;
  border-radius: 10px;
  border-color: #000000;
  border-width: 2px;
`;

export const ContainerClientName = styled.View`
  background-color: #3A93E3;
  align-items: center;
  justify-content: center;
  height: 11%;
  width: 85%;
  border-radius: 50px;
  border-color: #000000;
  border-width: 2px;
  margin-bottom: 3%;
`;

export const ClientName = styled.Text`
  font-size: 32px;
  text-align: center;
  color: #FFFFFF;
  font-weight: bold;
`;

export const DescriptionButton = styled.Text`
  font-size: 21px;
  text-align: center;
  color: #FFFFFF;
  font-weight: bold;
  line-height: 25px;
`;

export const Button = styled.TouchableOpacity`
  background-color: #0085FF;
  align-items: center;
  justify-content: center;
  height: 9%;
  width: 55%;
  border-radius: 20px;
  border-color: #000000;
  border-width: 2px;
  margin-top: 7%;
`;
