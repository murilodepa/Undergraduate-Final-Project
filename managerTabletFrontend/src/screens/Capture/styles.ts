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
      max-height: 760px;
      max-width: 760px;
      height: 570px;
      width: 570px;
      border-width: 4px;
      border-color: #000000;
`;
export const RevertButton = styled.Image`
      border-radius: 10px;
      height: 60px;
      width: 60px;
      margin-left: 24%;
`;

export const CameraButton = styled.Image`
      justify-content: center;
      align-items: center;
      height: 80px;
      width: 80px;
      margin-left: 30%;
`;

export const Containerfooter = styled.View`
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

  