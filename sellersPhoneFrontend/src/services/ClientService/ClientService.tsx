import backendApi from "../backendApi";

import { IClientData, IClientDataProps } from "./ClientServiceInterface";

export class ClientService implements IClientDataProps {

    getClientData = async (sellerId: number): Promise<IClientData> => {
        const { data } = await backendApi.get('/clientSeller/clientWaitingAttendance/'+sellerId)
        return data
    }
}