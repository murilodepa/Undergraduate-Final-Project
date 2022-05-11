import styled from "styled-components/native";

export const Container = styled.View`
  flex: 1;
  justify-content: center;
  background-color: #c4c4c4;
`;

export const ProfileImage = styled.Image`
  justify-content: center;
  border-radius: 10px;
  height: 90px;
  width: 90px;
  align-items: center;
  margin-left: 30px;
`;

export const NextArrow = styled.Image`
  justify-content: center;
  border-radius: 10px;
  height: 75px;
  width: 75px;
  align-items: center;
  margin-right: 40px;
`;

export const StoreLogo = styled.Image`
  height: 80px;
  width: 100px;
  justify-content: flex-start;
  align-items: flex-start;
  margin-top: 30px;
  margin-left: 20px;
`;

export const StoreName = styled.Text`
  position: absolute;
  font-size: 43px;
  font-weight: bold;
  padding-left: 120px;
  padding-top: 38px;
`;

export const Line = styled.View`
  flex: 1;
  background-color: #FFFFFF;
  max-height: 2px;
  margin-top: 30px;
  margin-bottom: 12px;
`

export const ContainerDescription = styled.View`
  background-color: #6bb1f1;
  max-height: 600px;
  height: 400px;
  width: 400px;
  max-width: 400px;
  border-radius: 20px;
  border-color: #FFFFFF;
  border-width: 2PX;
`;

export const Description = styled.Text`
  font-size: 30px;
  padding: 20px;
  text-align: center;
  line-height: 38px;
`;

export const ContainerHeader = styled.View`
  background-color: #b5b4b4;
  height: 140px;
  width: 100%;
  justify-content: center;
  align-items: flex-start;
  margin-right: 20px;
  margin-top: 20px;
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
`;
