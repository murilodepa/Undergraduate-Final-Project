export interface IManagerContext{
    setManagerName:(name: string) => Promise<void>;
    managerName?:string | null;
    setProfileImage:(uri: any) => void;
    profileImage?:any | null
}