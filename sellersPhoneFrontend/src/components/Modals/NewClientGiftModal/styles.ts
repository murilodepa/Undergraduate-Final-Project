import styled from "styled-components/native";

export const Container = styled.View`
  flex: 1;
  justify-content: center;
  align-items: center;
  margin-top: 28%;
`;

export const ContainerModal = styled.View`
  flex: 1;
  background-color: #6bb1f1;
  max-height: 565px;
  height: 565px;
  width: 93%;
  max-width: 93%;
  border-radius: 20px;
  border-color: #FFFFFF;
  border-width: 3px;
`;

export const ContainerDescription = styled.View`
  flex: 1;
  align-items: center;
`;

export const ProfileDescription = styled.View`
  max-height: 220px;
  height: 220px;
  width: 80%;
  max-width: 80%;
  border-radius: 20px;
  border-color: #FFFFFF;
  border-width: 2px;
  background-color: #3A93E3;
  align-items: center;
`;

export const PurchasesContainer = styled.View`
  max-height: 200px;
  height: 200px;
  width: 80%;
  max-width: 80%;
  border-radius: 10px;
  border-color: #FFFFFF;
  border-width: 2px;
  background-color: #3A93E3;
  align-items: center;
  margin-top: 20px;
`;

export const Question = styled.Text`
  font-size: 30px;
  text-align: center;
  color: white;
  margin-top: 0px;
`;

export const Description = styled.Text`
  font-size: 26px;
  font-weight: bold;
  line-height: 40px;
  color: white;
  justify-content: center;
  align-items: center;
`;

export const SuggestionText = styled.Text`
  font-size: 20px;
  line-height: 40px;
  padding-left: 7px;
  padding-right: 5px;
  color: white;
  justify-content: center;
  align-items: center;
  margin-top: 4px;
  margin-left: 5px;
  border-color: #000000;
  border-width: 2px;
  border-radius: 10px;
  background-color: #3A93E3;
`;

export const ContainerProfileImage = styled.View`
  flex: 1;
  justify-content: center;
  align-items: center;
  height: 100px;
  max-height: 100px;
`;

export const ButtonNoImage = styled.Image`
  max-width: 80px;
  max-height: 80px;
  height: 80px;
  width: 80px;
`;

export const ContainerSuggestion = styled.View`
  flex-direction: row;
  justify-content: center;
  align-items: center;
  margin-top: 20px;
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

export const ContainerButtonsOptions = styled.View`
  flex-direction: row;
  justify-content: center;
  align-items: center;
  margin-top: 100px;
  
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
export const Button = styled.TouchableOpacity`
  background-color: #0085FF;
  height: 70px;
  width: 50%;
  border-radius: 20px;
  border-color: #FFFFFF;
  border-width: 2px;
  margin-top: 50px;
  align-items: center;
  justify-content: center;
`;

export const ItemButton = styled.TouchableOpacity`
  background-color: #0085FF;
  height: 35px;
  width: 80%;
  border-radius: 10px;
  border-color: #000;
  border-width: 2px;
  margin-top: 5px;
  align-items: center;
  justify-content: center;
`;

export const DescriptionButton = styled.Text`
  font-size: 21px;
  text-align: center;
  color: #FFFFFF;
  font-weight: bold;
  line-height: 25px;
`;

export const CharacteristicInput = styled.TextInput`
    font-size: 30px;
    text-align: center;
    border-color: #000000;
    border-radius: 10px;
    width: 90%;
    margin-left: 5px;
`;

export const PlaceHolder = {
    fontSize: 20,
    backgroundColor: "6bb1f1",
    borderWidth: 2,
    borderRadius: 10,
    width: '90%',
    paddingLeft: 5,
    paddingRight: 5,
    justiftContent: "center",
    alignItems: "center"
}

export const ContainerScrollView = styled.View`
  flex: 1;
  align-items: center;
  background-color: #3A93E3;
`;

export const DescriptionItens = styled.Text`
  font-size: 18px;
  color: #ffffff;
`;

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