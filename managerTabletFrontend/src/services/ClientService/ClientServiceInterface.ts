import { AxiosResponse } from "axios";

export interface IClientData{
    name?: string,
    gender?:string,
    birth?: string,
    cpf?: string,
}

export interface IRegisterClientData {
    insertClient: (response: IClientData|null) => Promise<AxiosResponse>
}