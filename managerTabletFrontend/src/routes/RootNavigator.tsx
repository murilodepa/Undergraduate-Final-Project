import React from 'react';
import { createStackNavigator } from '@react-navigation/stack';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { NavigationContainer } from '@react-navigation/native';

import Splash from '../screens/Splash/Splash';
import Initial from '../screens/Initial/Initial';
import Capture from '../screens/Capture/Capture';
import SellerRegistration from '../screens/SellerRegistration/SellerRegistration';
import Menu from '../screens/Menu/Menu'
import SellerMenu from '../screens/SellerMenu/SellerMenu'
import ClientMenu from '../screens/ClientMenu/ClientMenu'

const Stack = createNativeStackNavigator();

export function RootNavigator() {
  const { Navigator, Screen } = Stack

  return (
    <NavigationContainer>
      <Navigator initialRouteName='Splash' screenOptions={{ headerShown: false }}>
        <Screen name='Splash' component={Splash} />
        <Screen name='Initial' component={Initial} />
        <Screen name='Capture' component={Capture} />
        <Screen name='SellerRegistration' component={SellerRegistration} />
        <Screen name='Menu' component={Menu} />
        <Screen name='SellerMenu' component={SellerMenu} />
        <Screen name='ClientMenu' component={ClientMenu} />
      </Navigator>
    </NavigationContainer>
  )
}