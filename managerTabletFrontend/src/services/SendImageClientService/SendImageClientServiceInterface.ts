export interface IClientImageAndName {
  name: string,
  profileImage: string
}

export interface IClientIdNameImageList {
  filter(arg0: (item: any) => boolean): import("react").SetStateAction<IClientIdNameImageList>;
  id: number;
  name: string;
  profileImage: any;
}

export interface IGetClientImageAndNameProps {
  getClientImageAndName: (id: number) => Promise<IClientImageAndName>;
}

export interface IGetClientImageAndNameProps {
  getClientIdNameImage: () => Promise<IClientIdNameImageList>;
}
