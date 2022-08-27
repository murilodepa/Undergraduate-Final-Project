import backendApi from "../backendApi";

import { IClientData, IClientDataProps, IPurchaseList } from "./ClientServiceInterface";

export class ClientService implements IClientDataProps {

    getClientData = async (sellerId: number): Promise<IClientData> => {
        const { data } = await backendApi.get('/clientSeller/clientWaitingAttendance/' + sellerId)
        return data
    }

    getPurchaseList = async (id: number): Promise<IPurchaseList> => {
        const { data } = await backendApi.get('/purchase/getPurchaseList/' + id);
        console.log("response", data)
        return data;
    }
}