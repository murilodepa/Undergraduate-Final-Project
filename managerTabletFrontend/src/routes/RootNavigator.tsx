import React from 'react';
import { createStackNavigator } from '@react-navigation/stack';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { NavigationContainer } from '@react-navigation/native';


import Splash from '../screens/Splash/Splash';
import Initial from '../screens/Initial/Initial';
import Capture from '../screens/Capture/Capture';
import ManagerRegistration from '../screens/RegisterManager/ManagerRegistration';
import Menu from '../screens/Menu/Menu'

const Stack = createNativeStackNavigator();

export function RootNavigator() {
  const { Navigator, Screen } = Stack

  return (
    <NavigationContainer>
      <Navigator initialRouteName="Splash" screenOptions={{ headerShown: false }}>
        <Screen name="Splash" component={Splash} />
        <Screen name="Initial" component={Initial} />
        <Screen name="Capture" component={Capture} />
        <Screen name="ManagerRegistration" component={ManagerRegistration} />
        <Screen name="Menu" component={Menu} />

      </Navigator>
    </NavigationContainer>
  )
}