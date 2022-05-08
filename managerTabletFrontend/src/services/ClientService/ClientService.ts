import { AxiosResponse } from "axios"
import banckendApi from "../backendApi"
import { IClientData, IRegisterClientData } from "./ClientServiceInterface"

export class ClientService implements IRegisterClientData {

    insertClient = async (response: IClientData): Promise<AxiosResponse> => {
        const data = await banckendApi.post("client/insertClient", response);
        return data;
    };
} 