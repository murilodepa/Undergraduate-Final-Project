export interface IClientData {
  id: number;
  name: string;
  profileImage: any;
  gender?: string;
  birth?: number;
}

export interface IClientInformationsData {
  id: number;
  name: string;
  gender?: string;
  birth?: number;
}

export interface IClientDataProps {
  getClientData: (sellerId: number) => Promise<IClientData>;
}