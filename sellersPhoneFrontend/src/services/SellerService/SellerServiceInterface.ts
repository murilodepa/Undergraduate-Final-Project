export interface ISellerEmailPassword {
  email?: string;
  password: string;
}

export interface ISellerData {
  id: number;
  name: string;
  profileImage: any;
  gender?: string;
  birth?: number;
  sector?: string;
  available?: boolean;
  attendances?: number;
  email?: string;
}

export interface IRegisteredSellerProps {
  getRegisteredSeller: (
    email: string,
    password: string
  ) => Promise<ISellerData>
}