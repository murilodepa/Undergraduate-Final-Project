import styled from "styled-components/native";

export const Container = styled.View`
  flex: 1;
  justify-content: center;
  align-items: center;
  background-color: #c4c4c4;
`;

export const ContainerButtons = styled.View`
  flex: 1;
  justify-content: center;
  text-align: center;
`;

export const ContainerSellerClientButton = styled.View`
  flex: 1;
  background-color: #6bb1f1;
  max-height: 250px;
  height: 250px;
  width: 250px;
  max-width: 250px;
  border-radius: 20px;
  border-color: #000000;
  border-width: 3px;
  justify-content: center;
  align-items: center;
  align-content: center;
`;

export const SellerClientButton = styled.Image`
  flex: 1;
  max-height: 210px;
  height: 210px;
  width: 200px;
  max-width: 200px;
  justify-content: center;
  align-items: center;
  align-content: center;
`;

export const SellerClientText = styled.Text`
  font-size: 30px;
  color: #000000;
  justify-content: center;
  align-items: center;
  text-align: center;
  font-weight: bold;
`;