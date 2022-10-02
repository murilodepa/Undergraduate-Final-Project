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

export const HappyBirthdayContainer = styled.View`
  position: absolute;
`;

export const HappyBirthdayImage = styled.Image`
  max-width: 60px;
  max-height: 60px;
  height: 60px;
  width: 60px;
  position: absolute;
  margin-top: 60px;
  margin-left: 110px;
  opacity: 1
`;

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
  height: 62px;
  width: 40%;
  border-radius: 20px;
  border-color: #FFFFFF;
  border-width: 2px;
  margin-left: 12%;
  align-items: center;
  justify-content: center;
`;

export const ContainerGifAndButton = styled.View`
  flexDirection: row;
  height: 100px;
  max-height: 100px;
  margin-top: 5%;
  margin-right: 3%;
  align-items: center;
  justify-content: center;
`;

export const GiftGif = styled.Image`
  max-width: 110px;
  max-height: 110px;
  height: 110px;
  width: 110px;
  margin-bottom: 15px;
  margin-right: 2%;
`;