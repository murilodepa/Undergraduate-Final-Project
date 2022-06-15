import { AxiosResponse } from "axios";

export interface IClientData {
  id?: string;
  name?: string;
  gender?: string;
  birth?: string;
}

export interface IGetClientData {
  id?: number,
  name?: string;
  gender?: string;
  birth?: string;
  purchaseSuggestion?: string;
}

export interface IRegisterClientDataProps {
  insertClient: (response: IClientData) => Promise<AxiosResponse>;
}

export interface IRegisterClientDataProps {
  getClient: (id: number) => Promise<IGetClientData>;
}

export interface IRegisterClientDataProps {
  updateClient: (response: any) => Promise<AxiosResponse>;
}
