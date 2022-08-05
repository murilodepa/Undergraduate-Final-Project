import { AxiosResponse } from "axios";

export interface ISellerEmailPassword {
  email?: string;
  password: string;
}

export interface ISellerData {
  name: string;
  profileImage: any;
  gender?: string;
  birth?: string;
  sector?: string;
  available?: boolean;
  attendances?: number;
  email?: string;
}

export interface IGetSellerData {
  id?: number;
  name?: string;
  gender?: string;
  birth?: string;
  sector?: string;
  available?: boolean;
  attendances?: number;
  email?: string;
}

export interface IRegisteredSellerProps {
  getRegisteredSeller: (
    email: string,
    password: string
  ) => Promise<ISellerData>
}