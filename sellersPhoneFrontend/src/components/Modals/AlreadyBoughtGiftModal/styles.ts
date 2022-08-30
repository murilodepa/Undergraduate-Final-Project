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
  margin-left: 3px;
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
  background-color: #3A93E3;
  margin-top: 22px;
  padding-top: 9px;
`;

export const Question = styled.Text`
  font-size: 22px;
  font-weight: bold;
  color: white;
`;

export const SuggestionText = styled.Text`
  font-size: 15px;
  line-height: 40px;
  color: white;
  justify-content: center;
  align-items: center;
  border-color: #000000;
`;

export const ContainerPicker = styled.View`
  border-width: 2px;
  border-radius: 20px;
  width: 170px;
  height: 35px;
  justify-content: center;
  border-color: #000000;
  backgroundColor: #0085FF;
`;

export const ContainerSuggestion = styled.View`
  border-width: 2px;
  border-radius: 10px;
  background-color: #3A93E3;
  justify-content: center;
  align-items: center;
  margin-top: 20px;
  max-width: 96%;
  padding-left: 4px;
  padding-right: 4px;
`;

export const RowSuggestion = styled.View`
  flex-direction: row;
  margin-left: 10px;
`;

export const ContainerButtons = styled.View`
  flex-direction: row;
  justify-content: flex-end;
  align-items: flex-end;
  height: 40px;
  max-height: 40px;
`;

export const EditCloseProfileButton = styled.Image`
  max-width: 30px;
  max-height: 30px;
  height: 30px;
  width: 30px;
  margin-right: 12px;
`;

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

export const ViewTextInput = styled.View `
  flex-direction: row;
  justify-content: flex-start;
  align-items: flex-start;
  margin-top: 12px;
`;