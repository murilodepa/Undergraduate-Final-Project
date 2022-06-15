import styled from "styled-components/native";

export const Container = styled.View`
  flex: 1;
  justify-content: center;
  align-items: center;
  margin-top: 170px;
`;

export const ContainerModal = styled.View`
  flex: 1;
  background-color: #6bb1f1;
  max-height: 620px;
  height: 620px;
  width: 450px;
  max-width: 450px;
  border-radius: 20px;
  border-color: #FFFFFF;
  border-width: 3px;
`;

export const ContainerDescription = styled.View`
  flex: 1;
  justify-content: center;
  align-items: center;
`;

export const ProfileDescription = styled.View`
  flex: 1;
  max-height: 320px;
  height: 320px;
  width: 310px;
  max-width: 310px;
  border-radius: 20px;
  border-color: #000000;
  border-width: 2px;
  background-color: #3A93E3;
`;

export const Name = styled.Text`
  font-size: 40px;
  text-align: center;
  color: white;
`;

export const Description = styled.Text`
  font-size: 22px;
  text-align: center;
  line-height: 46px;
  color: white;
  margin-top: 10px;
`;

export const ContainerProfileImage = styled.View`
  flex: 1;
  justify-content: center;
  align-items: center;
  height: 180px;
  max-height: 180px;
`;

export const ProfileImage = styled.Image`
  justify-content: center;
  max-width: 170px;
  max-height: 170px;
  height: 170px;
  width: 170px;
  align-items: center;
  justify-content: center;
  border-radius: 100px;
  border-width: 2px;
  border-color: #000000;
`;

export const ContainerButtons = styled.View`
  flex-direction: row;
  justify-content: flex-end;
  align-items: flex-end;
  height: 60px;
  max-height: 60px;
`;

export const EditCloseProfileButton = styled.Image`
  max-width: 45px;
  max-height: 45px;
  height: 45px;
  width: 45px;
  margin-right: 16px;
`;

export const Line = styled.View`
  background-color: #000000;
  max-height: 2px;
  height: 2px;
  margin-top: 7px;
`

