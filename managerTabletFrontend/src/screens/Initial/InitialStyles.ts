import styled from "styled-components/native";

export const Container = styled.View`
  flex: 1;
  justify-content: center;
  align-items: center;
  background-color: #c4c4c4;
`;

export const ProfileImage = styled.Image`
  justify-content: center;
  border-radius: 10px;
  height: 160px;
  width: 160px;
  align-items: center;
  margin-left: 30px;
`;

export const ProfileText = styled.Image`
  font-size: 30px;
  font-weight: bold;
  margin-left: 10px;
`;

export const NextArrow = styled.Image`
  justify-content: center;
  border-radius: 10px;
  height: 75px;
  width: 75px;
  align-items: center;
  margin-right: 40px;
`;

export const ContainerLogo = styled.View`
  flex: 1;
  justify-content: center;
  align-items: center;
`;

export const StoreLogo = styled.Image`
  height: 110px;
  width: 360px;
  justify-content: center;
  align-items: center;
`;

export const Line = styled.View`
  flex: 1;
  background-color: #ffffff;
  max-height: 3px;
  margin-bottom: 12px;
`;

export const ContainerDescription = styled.View`
  background-color: #6bb1f1;
  max-height: 600px;
  height: 470px;
  width: 430px;
  max-width: 430px;
  border-radius: 20px;
  border-color: #ffffff;
  border-width: 3px;
`;

export const Description = styled.Text`
  font-size: 32px;
  padding: 20px;
  text-align: center;
  line-height: 38px;
`;

export const ContainerHeader = styled.View`
  background-color: #b5b4b4;
  height: 200px;
  width: 100%;
  justify-content: center;
  align-items: flex-start;
`;

export const ContainerMiddle = styled.View`
  justify-content: center;
  align-items: center;
  flex: 1;
`;

export const ContainerFooter = styled.View`
  justify-content: flex-end;
  align-items: center;
  flex-direction: row;
  background-color: #b5b4b4;
  height: 100px;
  width: 100%;
`;
