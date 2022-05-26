import { ISellerData } from "../../services/SellerService/SellerServiceInterface";

export interface IRegisterDataSellerProps {
  insertSeller: (responses: any) => Promise<ISellerData>;
}
