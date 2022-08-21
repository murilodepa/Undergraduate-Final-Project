import { AxiosResponse } from "axios";
import backendApi from "../backendApi";

import { IUpdateAttendanceProps } from "./ClientSellerAttendanceInterface";

export class ClientSellerAttendance implements IUpdateAttendanceProps {

    async updateStatus(clientId: number, sellerId: number): Promise<AxiosResponse> {
        return await backendApi.put("/clientSeller/updateStatus/"+clientId+"/"+sellerId)
    }

    async updateStatusAndEndTime(clientId: number, sellerId: number): Promise<AxiosResponse> {
        return await backendApi.put("/clientSeller/updateStatusAndEndTime/"+clientId+"/"+sellerId)
    }

    async deleteAttendance(clientId: number, sellerId: number): Promise<AxiosResponse> {
        return await backendApi.delete("/clientSeller/deleteAttendance/"+clientId+"/"+sellerId)
    }
}