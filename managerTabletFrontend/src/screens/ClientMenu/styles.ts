import styled from "styled-components/native";

export const ContainerMiddle = styled.View`
  justify-content: center;
  align-items: center;
  flex: 1;
  flex-direction: column;
`;

export const ContainerButton = styled.View`
  align-items: center;
  flex-direction: row;
  background-color: #FFFFFF;
  border-radius: 20px;
  border-width: 2px;
  border-color: #000000;
  max-height: 170px;
  height: 170px;
  max-width: 450px;
  width: 450px;
  margin-top: 60px;
`;

export const ImageButton = styled.Image`
  height: 80px;
  width: 80px;
  margin-left: 25px;
`;

export const TextButton = styled.Text`
  font-size: 30px;
  font-weight: bold;
  margin-left: 10px;
`;
