import React from 'react';
import { Text,  } from 'react-native';
import { TouchableOpacity } from 'react-native-gesture-handler';
import { ContainerButtonItem, ButtonSelect } from "./styles";

const ListItem = ({ data }) => {
  return (
    <TouchableOpacity style={{alignContent: 'center', justifyContent: 'center'}}>
      <ContainerButtonItem>
        <Text style={{ fontSize: 30 }}>{data.name}</Text>
      </ContainerButtonItem>
      <ButtonSelect style={{textAlignVertical: 'center'}}>
          Selecionar
      </ButtonSelect>
    </TouchableOpacity>
  );
};

export default ListItem;
