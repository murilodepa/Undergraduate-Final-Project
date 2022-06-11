import * as React from "react";
import { RootNavigator } from "./src/routes/RootNavigator";
import { LogBox } from "react-native";
import { SellerContext } from "./src/context/SellerContext";

export default function App() {
  const [name, setName] = React.useState<string>(null);
  const [profileImage, setProfileImage] = React.useState<any>(null);

  LogBox.ignoreLogs(["Remote debugger"]);
  return (
    <SellerContext.Provider
      value={{ name, setName, profileImage, setProfileImage }}
    >
      <RootNavigator />
    </SellerContext.Provider>
  );
}
