import styled from "styled-components/native";

export const Container = styled.View`
  flex: 1;
  align-items: center;
  background-color: #c4c4c4;
`;

export const ContainerScrollView = styled.View`
  flex: 1;
  align-items: center;
  background-color: #3A93E3;
  border-color: #000000;
  border-width: 2px;
`;

export const Line = styled.View`
  flex: 1;
  background-color: #FFFFFF;
  max-width: 100%;
  width: 100%;
  max-height: 3px;
  height: 3px;
`

export const LineDescription = styled.View`
  flex: 1;
  background-color: #000000;
  max-width: 100%;
  width: 100%;
  max-height: 2px;
  height: 2px;
`

export const LineItens = styled.View`
  flex: 1;
  background-color: #000000;
  max-width: 100%;
  width: 100%;
  max-height: 2px;
  height: 2px;
  margin-top: 2%;
  margin-bottom: 2%;
`

export const ContainerClientName = styled.View`
  background-color: #3A93E3;
  height: 20%;
  width: 80%;
  border-radius: 40px;
  border-color: #000000;
  border-width: 2px;
  margin-bottom: 3%;
  margin-top: 10%;
`;

export const ClientName = styled.Text`
  font-size: 30px;
  text-align: center;
  color: #FFFFFF;
  margin-bottom: 1%;
  font-weight: bold;
`;

export const ClientDescription = styled.Text`
  font-size: 22px;
  text-align: center;
  color: #FFFFFF;
  line-height: 30px;
`;

export const DescriptionButton = styled.Text`
  font-size: 21px;
  text-align: center;
  color: #FFFFFF;
  font-weight: bold;
  line-height: 25px;
`;

export const DescriptionItens = styled.Text`
  font-size: 18px;
  color: #ffffff;
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
  margin-top: 10%;
`;
