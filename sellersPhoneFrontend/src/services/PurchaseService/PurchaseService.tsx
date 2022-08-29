import backendApi from "../backendApi";
import { IPurchaseDataProps, IPurchaseList, IPurchasePeopleGiftList, PurchaseGiftData } from "./PurchaseServiceInterface";

export class PurchaseService implements IPurchaseDataProps {

    getPeopleGift = async (id: number): Promise<IPurchasePeopleGiftList> => {
        const { data } = await backendApi.get('/purchase/getPeopleGifts/' + id);
        console.log("response", data)
        return data;
    }

    getPurchaseGift = async (id: number, name: string, kinship: string): Promise<IPurchaseList> => {
        const { data } = await backendApi.get('/purchase/getPurchaseGifts/' + id, { params: { name: name, kinship: kinship}});
        console.log("response", data)
        return data;
    }

    getSuggestionGift = async (id: number, name: string, kinship: string): Promise<string> => {
        const { data } = await backendApi.get('/purchase/getSuggestionGifts/' + id, { params: { name: name, kinship: kinship}});
        console.log("response", data)
        return data;
    }

    getSuggestion = async (id: number): Promise<string> => {
        const { data } = await backendApi.get('/purchase/getSuggestion/' + id);
        console.log("response", data)
        return data;
    }
}