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

export const Logo = styled.Image`
  margin-top: 10%;
  margin-bottom: 10%;
  height: 100px;
  width: 330px;
`;

export const ContainerDescription = styled.View`
  flex: 1;
  align-items: center;
  justify-content: center;
`;

export const Description = styled.Text`
  font-size: 40px;
  text-align: center;
  color: #000000;
  line-height: 70px;
`;