import * as React from "react";
import { RootNavigator } from "./src/routes/RootNavigator";
import { LogBox } from "react-native";
import { SellerContext } from "./src/context/SellerContext";

export default function App() {
  const [id, setId] = React.useState<number>(null);
  const [name, setName] = React.useState<string>(null);
  const [profileImage, setProfileImage] = React.useState<any>(null);
  const [gender, setGender] = React.useState<string>(null);
  const [birth, setBirth] = React.useState<any>(null);
  const [sector, setSector] = React.useState<string>(null);
  const [available, setAvailable] = React.useState<any>(null);
  const [attendances, setAttendances] = React.useState<number>(null);
  const [email, setEmail] = React.useState<string>(null);

  LogBox.ignoreLogs(["Remote debugger"]);
  LogBox.ignoreLogs(['Warning: ...']);
  LogBox.ignoreAllLogs();
  
  return (
    <SellerContext.Provider
      value={{ id, setId, name, setName, gender, setGender, birth, setBirth, sector, setSector, available, setAvailable, attendances, setAttendances, email, setEmail, profileImage, setProfileImage }}
    >
      <RootNavigator />
    </SellerContext.Provider>
  );
}
