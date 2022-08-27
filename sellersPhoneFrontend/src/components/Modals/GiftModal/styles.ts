import styled from "styled-components/native";

export const Container = styled.View`
  flex: 1;
  justify-content: center;
  align-items: center;
  margin-top: 33%;
`;

export const ContainerModal = styled.View`
  flex: 1;
  background-color: #6bb1f1;
  max-height: 540px;
  height: 540px;
  width: 91%;
  max-width: 91%;
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
  max-height: 270px;
  height: 270px;
  width: 90%;
  max-width: 90%;
  border-radius: 20px;
  border-color: #000000;
  border-width: 2px;
  background-color: #3A93E3;
  margin-top: 40px;
`;

export const Name = styled.Text`
  font-size: 30px;
  text-align: center;
  color: white;
`;

export const Description = styled.Text`
  font-size: 22px;
  text-align: center;
  line-height: 40px;
  color: white;
  margin-top: 10px;
`;

export const ContainerProfileImage = styled.View`
  flex: 1;
  justify-content: center;
  align-items: center;
  height: 100px;
  max-height: 100px;
`;

export const ButtonImage = styled.Image`
  max-width: 80px;
  max-height: 80px;
  height: 80px;
  width: 80px;
`;

export const ContainerButtons = styled.View`
  flex-direction: row;
  justify-content: flex-end;
  align-items: flex-end;
  height: 40px;
  max-height: 40px;
`;

export const ContainerButtonsLeave = styled.View`
  justify-content: center;
  align-items: center;
  max-height: 13%;
  height: 13%;
  max-width: 40%;
  width: 40%;
  background-color: red;
  border-radius: 30px;
  border-color: #FFFFFF;
  border-width: 2px;
  margin-top: 5%;
`;

export const LeaveText = styled.Text`
  font-size: 20px;
  text-align: center;
  color: #FFFFFF;
  font-weight: bold;
`;

export const EditCloseProfileButton = styled.Image`
  max-width: 30px;
  max-height: 30px;
  height: 30px;
  width: 30px;
  margin-right: 12px;
`;

export const Line = styled.View`
  background-color: #000000;
  max-height: 2px;
  height: 2px;
  margin-top: 7px;
`
