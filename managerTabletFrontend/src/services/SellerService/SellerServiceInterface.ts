import { AxiosResponse } from "axios";

export interface ISellerData {
    name: string
    gender: string
    birth: string
    sector: string
    email: string
    password: string
  }

  export interface IRegisterDataSellerProps {
    insertSeller:(responses: any) => Promise<AxiosResponse>
}