import { AxiosResponse } from "axios";
import backendApi from "../backendApi";
import banckendApi from "../backendApi";
import { ISellerData, IRegisterDataSellerProps } from "./SellerServiceInterface";

export class SellerService implements IRegisterDataSellerProps {
  async insertSeller(response: any): Promise<AxiosResponse> {
    console.log("Chegou aqui..." + response)
    return await banckendApi.post("/seller/insertSeller", response);
  }

  getSellers = async (): Promise<ISellerData> => {
    const { data } = await backendApi.get("/seller/getSeller");
    console.log(data);
    return data; 
  }
}