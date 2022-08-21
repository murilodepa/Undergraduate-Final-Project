import { AxiosResponse } from "axios";

export interface IUpdateAttendanceProps {
    updateStatus: (clientId: number, sellerId: number) => Promise<AxiosResponse>;
}

export interface IUpdateAttendanceProps {
    updateStatusAndEndTime: (clientId: number, sellerId: number) => Promise<AxiosResponse>;
}

export interface IUpdateAttendanceProps {
    deleteAttendance: (clientId: number, sellerId: number) => Promise<AxiosResponse>;
}