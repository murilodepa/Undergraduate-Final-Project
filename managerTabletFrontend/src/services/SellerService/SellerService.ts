import { AxiosResponse } from "axios";
import banckendApi from "../backendApi";
import { IRegisterDataSellerProps } from "./SellerServiceInterface";

export class SellerService implements IRegisterDataSellerProps {
  async insertSeller(response: any): Promise<AxiosResponse> {
    console.log("Chegou aqui..." + response)
    return await banckendApi.post("/seller/insertSeller", response);
  }
}