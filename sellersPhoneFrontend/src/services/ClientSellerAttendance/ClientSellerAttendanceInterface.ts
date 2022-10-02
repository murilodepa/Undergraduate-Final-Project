import { AxiosResponse } from "axios";

export interface IUpdateAttendanceProps {
    updateStatus: (sellerId: number) => Promise<AxiosResponse>;
}

export interface IUpdateAttendanceProps {
    updateStatusAndEndTime: (sellerId: number) => Promise<AxiosResponse>;
}

export interface IUpdateAttendanceProps {
    deleteAttendance: (sellerId: number) => Promise<AxiosResponse>;
}