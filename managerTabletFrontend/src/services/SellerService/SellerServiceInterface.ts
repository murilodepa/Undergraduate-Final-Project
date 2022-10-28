import { AxiosResponse } from "axios";

export interface ISellerData {
  id?: number,
  name?: string;
  gender?: string;
  birth?: string;
  sector?: string;
  available?: boolean;
  servicePreference?: string,
  email?: string;
  password: string;
}

export interface IGetSellerData {
  id?: number,
  name?: string;
  gender?: string;
  birth?: string;
  sector?: string;
  available?: boolean,
  attendances?: number,
  email?: string;
  password?: string;
}
export interface IRegisterDataSellerProps {
  insertSeller: (responses: any) => Promise<AxiosResponse>;
}

export interface IRegisterDataSellerProps {
  updateSeller: (response: any) => Promise<AxiosResponse>;
}

export interface IRegisterDataSellerProps {
  getAllSellers: () => Promise<ISellerData>;
}

export interface IRegisterDataSellerProps {
  getSeller: (id: number) => Promise<ISellerData>;
}

export interface IRegisterDataSellerProps {
  getManager: () => Promise<IGetSellerData>;
}

export interface IRegisterDataSellerProps {
  removeSeller: (id: number) => Promise<AxiosResponse>;
}