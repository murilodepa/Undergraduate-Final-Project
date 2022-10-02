export interface IPurchaseList {
  [x: string]: any;
  filter(arg0: (item: any) => boolean): import("react").SetStateAction<IPurchaseList>;
  category?: string;
  size?: string;
  data?: string;
}

export interface IClientData {
  id?: number;
  name?: string;
  profileImage?: any;
  gender?: string;
  birth?: string;
  visibleHappyBirthdayImage?: number;
}

export interface IClientInformationsData {
  id?: number;
  name?: string;
  gender?: string;
  birth?: string;
  purchaseList?: any;
  visibleHappyBirthdayImage?: number;
}

export interface IClientDataProps {
  getClientData: (sellerId: number) => Promise<IClientData>;
}

export interface IClientDataProps {
  getPurchaseList: (id: number) => Promise<IPurchaseList>;
}