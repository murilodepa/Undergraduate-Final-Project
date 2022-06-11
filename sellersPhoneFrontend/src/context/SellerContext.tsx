import React, {createContext, useContext, useState } from 'react';

export type GlobalContent= {
    name: string
    setName: (name: string) => void
    profileImage: any
    setProfileImage: (uri: any) => void
}

export const SellerContext = createContext<GlobalContent>({
    name: "",
    setName: () => {},
    profileImage: "",
    setProfileImage: () => {},
})

export const useGlobalContext = () => useContext(SellerContext);