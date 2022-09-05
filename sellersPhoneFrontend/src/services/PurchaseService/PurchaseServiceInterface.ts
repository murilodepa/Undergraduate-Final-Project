import { AxiosResponse } from "axios";

export interface INewPurchaseGiftData {
  name?: string;
  kinship?: string;
}

export interface IGiftPersonData {
  gender?: string;
  age?: string;
  size?: string;
  selectedSummer?: boolean;
  selectedWinter?: boolean;
  selectedSocial?: boolean;
  selectedCasual?: boolean;
  selectedPatterned?: boolean;
  selectedStripe?: boolean;
}

export interface PurchaseGiftData {
  id?: number;
  name?: string;
  kinship?: string;
}

export interface IPurchasePeopleGiftList {
  [x: string]: any;
  filter(arg0: (item: any) => boolean): import("react").SetStateAction<IPurchasePeopleGiftList>;
  name?: string;
  kinship?: string;
}

export interface IPurchaseList {
  [x: string]: any;
  filter(arg0: (item: any) => boolean): import("react").SetStateAction<IPurchaseList>;
  category?: string;
  size?: string;
  data?: string;
}

export interface IPurchaseDataProps {
  getPeopleGift: (userId: number) => Promise<IPurchasePeopleGiftList>;
}

export interface IPurchaseDataProps {
  getPurchaseGift: (id: number, name: string, kinship: string) => Promise<IPurchaseList>;
}

export interface IPurchaseDataProps {
  getSuggestionGift: (id: number, name: string, kinship: string) => Promise<string>;
}

export interface IPurchaseDataProps {
  verifySuggestionGiftWithTags: (response: IGiftPersonData) => Promise<string>;
}

export interface IPurchaseDataProps {
  getSuggestion: (id: number) => Promise<string>;
}