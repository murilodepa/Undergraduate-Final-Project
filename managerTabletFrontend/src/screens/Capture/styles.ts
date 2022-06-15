import styled from "styled-components/native";

export const Container = styled.View`
    flex: 1;
    justify-content: center;
`;

export const ContainerCamera = styled.View`
    flex: 1;
    justify-content: center;
    align-items: center;
    background-color: #B4CFEF;
`;

export const CameraEdge = styled.View`
      flex: 1;
      max-height: 585px;
      max-width: 585px;
      height: 585px;
      width: 585px;
      align-content: center;
      border-width: 5px;
      border-color: #000000;
`;

export const CameraButton = styled.Image`
      justify-content: center;
      border-radius: 10px;
      height: 75px;
      width: 75px;
`;

export const Containerfooter = styled.View`
      justify-content: center;
      align-items: center;
      flex-direction: row;
      background-color: #B5B4B4;
      height: 100px;
`;

export const ContainerHeaderCapture = styled.View`
      background-color: #B5B4B4;
      height: 100px;
`;

export const TextHeaderCapture = styled.Text`
      flex: 1;
      text-align: center;
      font-size: 30px;
      margin-top: 5px;
`;

  