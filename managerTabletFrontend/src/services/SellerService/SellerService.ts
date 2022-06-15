import { AxiosResponse } from "axios";
import backendApi from "../backendApi";
import { ISellerData, IGetSellerData, IRegisterDataSellerProps } from "./SellerServiceInterface";

export class SellerService implements IRegisterDataSellerProps {
  async insertSeller(response: any): Promise<AxiosResponse> {
    return await backendApi.post("/seller/insertSeller", response);
  }

  async updateSeller(response: any): Promise<AxiosResponse> {
    return await backendApi.put("/seller/updateSeller/"+response.id, response);
  }

  getAllSellers = async (): Promise<ISellerData> => {
    const { data } = await backendApi.get("/seller/getSeller");
    console.log(data);
    return data; 
  }

  getSeller = async (id: number): Promise<ISellerData> => {
    const { data } = await backendApi.get("/seller/getSeller/"+id);
    return data; 
  }

  getManager = async (): Promise<IGetSellerData> => {
    const { data } = await backendApi.get("/seller/getSeller/1");
    return data; 
  }

  removeSeller = async (id: number): Promise<AxiosResponse> => {
    return await backendApi.delete("/seller/removeSeller/"+id);
  } 
}