import React from 'react';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { NavigationContainer } from '@react-navigation/native';

import Splash from '../screens/Splash/Splash';
import Initial from '../screens/Initial/Initial';
import Menu from '../screens/Menu/Menu';
import ClientAttendance from '../screens/ClientAttendance/ClientAttendance'
import ClientInformations from '../screens/ClientInformations/ClientInformations';


const Stack = createNativeStackNavigator();

export function RootNavigator() {
  const { Navigator, Screen } = Stack

  return (
    <NavigationContainer>
      <Navigator initialRouteName='Splash' screenOptions={{ headerShown: false }}>
        <Screen name='Splash' component={Splash} />
        <Screen name='Initial' component={Initial} />
        <Screen name='Menu' component={Menu} />
        <Screen name='ClientAttendance' component={ClientAttendance} />
        <Screen name='ClientInformations' component={ClientInformations} />
      </Navigator>
    </NavigationContainer>
  )
}