import React from "react";
import Capture from './src/screens/Capture/Capture'

import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { NavigationContainer } from "@react-navigation/native";
import { View, Text } from "react-native";

const Tab = createBottomTabNavigator();

function StartScreen() {
  return (
    <View>
      <Text>
        Hi I am your Start Screen
      </Text>
    </View>
  )
}

export default function App() {
  return (
    <NavigationContainer>
      <Capture />
    </NavigationContainer>
    //<Capture />
  );
}