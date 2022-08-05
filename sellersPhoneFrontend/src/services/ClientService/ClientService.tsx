import backendApi from "../backendApi";

import { IClientsList, IClientsProps } from "./ClientServiceInterface";

export class ClientService implements IClientsProps {

    getClientsList = async (): Promise<IClientsList> => {
        const { data } = await backendApi.get('/clientSeller/clientsWaitingAttendance')
        return data
    }
}