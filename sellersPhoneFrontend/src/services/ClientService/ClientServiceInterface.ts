export interface IClientsList {
    filter(arg0: (item: any) => boolean): import("react").SetStateAction<IClientsList>;
    id: number;
    name: string;
    profileImage: any;
    gender?: string;
    birth?: string;
  }

export interface IClientsProps {
    getClientsList: () => Promise<any>;
  }