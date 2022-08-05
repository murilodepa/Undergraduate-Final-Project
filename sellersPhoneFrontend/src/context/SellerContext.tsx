import React, {createContext, useContext, useState } from 'react';

export type GlobalContent= {
    name: string
    setName: (name: string) => void
    gender: string
    setGender: (gender: string) => void
    birth: string
    setBirth: (birth: string) => void
    sector: string
    setSector: (sector: string) => void
    available: boolean
    setAvailable: (available: boolean) => void
    attendances: number
    setAttendances: (attendances: number) => void
    email: string
    setEmail: (email: string) => void
    profileImage: any
    setProfileImage: (uri: any) => void
}

export const SellerContext = createContext<GlobalContent>({
    name: "",
    setName: () => {},
    gender: "",
    setGender: () => {},
    birth: "",
    setBirth: () => {},
    sector: "",
    setSector: () => {},
    available: false,
    setAvailable: () => {},
    attendances: 0,
    setAttendances: () => {},
    email: "",
    setEmail: () => {},
    profileImage: "",
    setProfileImage: () => {},
})

export const useGlobalContext = () => useContext(SellerContext);