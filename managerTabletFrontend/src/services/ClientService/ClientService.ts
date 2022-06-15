import { AxiosResponse } from "axios"
import backendApi from "../backendApi"
import { IClientData, IRegisterClientDataProps, IGetClientData } from "./ClientServiceInterface"

export class ClientService implements IRegisterClientDataProps {

    insertClient = async (response: IClientData): Promise<AxiosResponse> => {
        const data = await backendApi.post("client/insertClient", response);
        return data;
    };

    getClient = async (id: number): Promise<IGetClientData> => {
        const { data } = await backendApi.get("/client/getClient/" + id);
        return data;
    }

    async updateClient(response: any): Promise<AxiosResponse> {
        return await backendApi.put("/client/updateClient/"+response.id, response);
      }
} 