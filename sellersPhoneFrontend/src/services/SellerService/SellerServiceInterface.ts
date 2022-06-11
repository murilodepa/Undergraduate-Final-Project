export interface ISellerEmailPassword {
  email?: string;
  password: string;
}

export interface IGetSellerData {
  id?: number;
  name?: string;
  gender?: string;
  birth?: string;
  sector?: string;
  available?: boolean;
  attendances?: number;
  email?: string;
}
