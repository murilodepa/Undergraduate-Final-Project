import { AxiosResponse } from "axios";
import backendApi from "../backendApi";
import { IGiftPersonData, IPurchaseDataProps, IPurchaseList, IPurchasePeopleGiftList, PurchaseGiftData } from "./PurchaseServiceInterface";

export class PurchaseService implements IPurchaseDataProps {

    getPeopleGift = async (id: number): Promise<IPurchasePeopleGiftList> => {
        const { data } = await backendApi.get('/purchase/getPeopleGifts/' + id);
        return data;
    }

    getPurchaseGift = async (id: number, name: string, kinship: string): Promise<IPurchaseList> => {
        const { data } = await backendApi.get('/purchase/getPurchaseGifts/' + id, { params: { name: name, kinship: kinship}});
        return data;
    }

    getSuggestionGift = async (id: number, name: string, kinship: string): Promise<string> => {
        const { data } = await backendApi.get('/purchase/getSuggestionGifts/' + id, { params: { name: name, kinship: kinship}});
        return data;
    }

    getSuggestion = async (id: number): Promise<string> => {
        const { data } = await backendApi.get('/purchase/getSuggestion/' + id);
        return data;
    }

    verifySuggestionGiftWithTags = async (response: IGiftPersonData): Promise<string> => {
        const { data } = await backendApi.post('/purchase/verifySuggestionWithTags', response);
        return data;
    }
}