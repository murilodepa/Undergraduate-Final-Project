import { AxiosResponse } from "axios";

export interface IClientData {
  id?: string;
  name?: string;
  gender?: string;
  birth?: string;
}

export interface IRegisterClientDataProps {
  insertClient: (response: IClientData) => Promise<AxiosResponse>;
}

export interface IRegisterClientDataProps {
  updateClient: (response: IClientData) => Promise<AxiosResponse>;
}

export interface IRegisterClientDataProps {
  getClient: () => Promise<IClientData>;
}
