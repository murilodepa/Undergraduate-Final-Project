import backendApi from "../backendApi";

import { IRegisteredSellerProps, ISellerData } from "./SellerServiceInterface";

export class SellerService implements IRegisteredSellerProps {

    getRegisteredSeller = async (
        email: string,
        password: string): Promise<ISellerData> => {
        const { data } = await backendApi.get('/seller/getRegisteredSeller', {
            params: {
                email: email,
                password: password
            }
        })
        return data
    }
}