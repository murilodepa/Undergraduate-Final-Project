import styled from "styled-components/native";

export const ContainerSearch = styled.View`
  justify-content: center;
  align-items: flex-start;
  max-height: 15%;
  height: 15%;
`;

export const SearchInput = styled.View`
  max-height: 50%;
  width: 75%;
  margin-left: 5%;
  border-radius: 30px;
  border-width: 2px;
  border-color: #000000;
  flex-direction: row;
`;

export const Input = styled.TextInput`
  flex: 1;
  font-size: 40px;
  font-weight: bold;
  width: 250px;
`;

export const ContainerSearchImage = styled.View`
  justify-content: center;
  align-items: center;
  margin-left: 3%;
`;

export const SearchImage = styled.Image`
  height: 50px;
  width: 50px;
`;

export const ContainerButtons = styled.View`
  position: absolute;
  max-width: 60px;
  margin-left: 85%;
  margin-top: 7%;
`;

export const CloseButton = styled.Image`
  max-width: 55px;
  max-height: 55px;
  height: 55px;
  width: 55px;
`;

export const PlaceHolder = {
  fontSize: 26,
  borderRadius: 20,
  width: 360,
  paddingLeft: 10,
  justifyContent: "center",
  alignItems: "center",
};

export const ContainerList = styled.View`
    flex: 1;
    justify-content: center;
    align-items: center;
`;