export interface ISellerImageAndName {
    name: string;
    profileImage: string;
  }
  
  export interface IGetSellerImageAndNameProps {
    getSellerImageAndName: (id: number) => Promise<ISellerImageAndName>;
  }
  