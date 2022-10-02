import { AxiosResponse } from "axios";
import backendApi from "../backendApi";

import { IUpdateAttendanceProps } from "./ClientSellerAttendanceInterface";

export class ClientSellerAttendance implements IUpdateAttendanceProps {

    async updateStatus(sellerId: number): Promise<AxiosResponse> {
        return await backendApi.put("/clientSeller/updateStatus/"+sellerId)
    }

    async updateStatusAndEndTime(sellerId: number): Promise<AxiosResponse> {
        return await backendApi.put("/clientSeller/updateStatusAndEndTime/"+sellerId)
    }

    async deleteAttendance(sellerId: number): Promise<AxiosResponse> {
        return await backendApi.delete("/clientSeller/deleteAttendance/"+sellerId)
    }
}