import * as React from 'react';
import { RootNavigator } from './src/routes/RootNavigator';
import { LogBox } from 'react-native';


export default function App() {
  LogBox.ignoreLogs(['Remote debugger']);
  return (
    <RootNavigator/>
  );
}
