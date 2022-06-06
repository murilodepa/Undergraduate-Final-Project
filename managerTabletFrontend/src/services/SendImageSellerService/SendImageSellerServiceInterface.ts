export interface ISellerImageAndName {
    name: string;
    profileImage: any;
  }
  
  export interface ISellerIdNameImageList {
    filter(arg0: (item: any) => boolean): import("react").SetStateAction<ISellerIdNameImageList>;
    id: number;
    name: string;
    profileImage: any;
  }

  export interface IGetSellerImageAndNameProps {
    getSellerImageAndName: (id: number) => Promise<ISellerImageAndName>;
  }

  export interface IGetSellerImageAndNameProps {
    getSellerIdNameImage: () => Promise<ISellerIdNameImageList>;
  }
  