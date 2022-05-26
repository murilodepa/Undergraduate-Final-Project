import { AxiosResponse } from "axios";
import backendApi from "../backendApi";
import banckendApi from "../backendApi";
import { ISellerData, IGetSellerData, IRegisterDataSellerProps } from "./SellerServiceInterface";

export class SellerService implements IRegisterDataSellerProps {
  async insertSeller(response: any): Promise<AxiosResponse> {
    return await banckendApi.post("/seller/insertSeller", response);
  }

  async updateSeller(response: any): Promise<AxiosResponse> {
    console.log("Chegou aqui..." + response)
    return await banckendApi.put("/seller/updateSeller/"+response.id, response);
  }

  getSellers = async (): Promise<ISellerData> => {
    const { data } = await backendApi.get("/seller/getSeller");
    console.log(data);
    return data; 
  }

  getManager = async (): Promise<IGetSellerData> => {
    const { data } = await backendApi.get("/seller/getSeller/1");
    console.log(data);
    return data; 
  }
}