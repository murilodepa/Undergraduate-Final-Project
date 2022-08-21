import * as React from 'react';
import { RootNavigator } from './src/routes/RootNavigator';
import { LogBox } from 'react-native';
import { ManagerContext } from './src/context/managerContext';

export default function App() {
  const [name, setName] = React.useState<string>(null)
  const [profileImage, setProfileImage] = React.useState<any>(null)
  const [resultSellerData, setResultSellerData] = React.useState<any>(null)
  const [resultClientData, setResultClientData] = React.useState<any>(null)

  LogBox.ignoreLogs(['Remote debugger']);
  return (
    <ManagerContext.Provider value={{name, setName, profileImage, setProfileImage, resultSellerData, setResultSellerData, resultClientData, setResultClientData}}>
          <RootNavigator/>
    </ManagerContext.Provider>
  );
}
