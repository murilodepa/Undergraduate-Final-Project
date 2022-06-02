export interface IClientImageAndName {
  name: string;
  profileImage: string;
}

export interface IGetClientImageAndNameProps {
  getClientImageAndName: (id: number) => Promise<IClientImageAndName>;
}
