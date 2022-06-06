import React, {createContext, useContext, useState } from 'react';
import { ISellerIdNameImageList } from "../services/SendImageSellerService/SendImageSellerServiceInterface";



export type GlobalContent= {
    name: string
    setName: (name: string) => void
    profileImage: any
    setProfileImage: (uri: any) => void
    resultSellerData: any
    setResultSellerData: (response: any) => void
    resultClientData: any
    setResultClientData: (response: any) => void
}

export const ManagerContext = createContext<GlobalContent>({
    name: "",
    setName: () => {},
    profileImage: "",
    setProfileImage: () => {},
    resultSellerData: "",
    setResultSellerData: () => {},
    resultClientData: "",
    setResultClientData: () => {},
})

export const useGlobalContext = () => useContext(ManagerContext);