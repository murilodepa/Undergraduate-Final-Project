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
  height: 800px;
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

export const MenuButton = styled.TouchableOpacity`
      justify-content: center;
      align-items: center;
`;

export const MenuText = styled.Text`
  font-size: 38px;
  font-weight: bold;
`;

export const CharacteristicText = styled.Text`
  font-size: 30px;
  font-weight: bold;
`;

export const CharacteristicInput = styled.TextInput`
  font-size: 28px;
  font-weight: bold;
  background-color: #FFFFFF;
  border-color: #000000;
  border-width: 2px;
  border-radius: 20px;
  width: 250px;
  margin-left: 5px;
`;

export const PlaceHolder = {
  fontSize: 28, 
  backgroundColor: "#FFFFFF", 
  borderColor: "#000000",
  borderWidth: 2, 
  borderRadius: 20, 
  width: 250, 
  marginStart: 5
}

export const ViewTextInput = styled.View`
  flex-direction: row;
  justify-content: center;
  align-items: center;
`;

export const ManagerPicture = styled.Image`
  height: 200px;
  width: 200px;
`;

export const MenuIcon = styled.Image`
  height: 70px;
  width: 70px;
`;

export const ContainerFooter = styled.View`
  justify-content: center;
  text-align: center;
  background-color: #b5b4b4;
  height: 100px;
`;
