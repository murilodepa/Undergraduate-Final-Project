import styled from "styled-components/native";

export const Container = styled.View`
  flex: 1;
  justify-content: center;
  align-items: center;
  margin-top: 30%;
`;

export const ContainerModal = styled.View`
  flex: 1;
  background-color: #6bb1f1;
  max-height: 570px;
  height: 570px;
  width: 95%;
  max-width: 95%;
  border-radius: 20px;
  border-color: #FFFFFF;
  border-width: 3px;
`;

export const ContainerDescription = styled.View`
  flex: 1;
  align-items: center;
`;

export const ProfileDescription = styled.View`
  max-height: 153px;
  height: 153px;
  width: 90%;
  max-width: 90%;
  border-radius: 20px;
  border-color: #000000;
  border-width: 2px;
  background-color: #3A93E3;
  margin-top: 5px;
`;

export const ContainerCheckBox = styled.View`
  max-height: 190px;
  height: 190px;
  width: 90%;
  max-width: 90%;
  border-radius: 20px;
  border-color: #000000;
  border-width: 2px;
  background-color: lightgray;
  margin-top: 22px;
  padding-top: 9px;
`;

export const LastItem1CheckBox = styled.View`
  margin-left: 24px;
`;

export const LastItem2CheckBox = styled.View`
  margin-left: 22px;
`;

export const LastItem3CheckBox = styled.View`
  margin-left: 2px;
`;

export const Question = styled.Text`
  font-size: 22px;
  font-weight: bold;
  color: white;
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

export const ContainerPicker = styled.View`
  border-width: 2px;
  border-radius: 20px;
  width: 170px;
  height: 35px;
  justify-content: center;
  border-color: #000000;
`;

export const ContainerSuggestion = styled.View`
  flex-direction: row;
  justify-content: center;
  align-items: center;
  margin-top: 20px;
`;

export const RowSuggestion = styled.View`
  flex-direction: row;
  margin-left: 10px;
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
`
export const Button = styled.TouchableOpacity`
  background-color: #0085FF;
  height: 60px;
  width: 62%;
  border-radius: 20px;
  border-color: #FFFFFF;
  border-width: 2px;
  margin-top: 20px;
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
    font-size: 20px;
    border-color: #000000;
    border-radius: 10px;
    margin-left: 5px;
    max-width: 180px;
`;

export const PlaceHolder = {
    fontSize: 20,
    backgroundColor: "#6bb1f1",
    borderWidth: 2,
    borderRadius: 10,
    width: '90%',
    paddingLeft: 5,
    paddingRight: 5,
    justiftContent: "center",
    alignItems: "center"
}

export const ViewTextInput = styled.View `
  flex-direction: row;
  justify-content: flex-start;
  align-items: flex-start;
  margin-top: 12px;
`;