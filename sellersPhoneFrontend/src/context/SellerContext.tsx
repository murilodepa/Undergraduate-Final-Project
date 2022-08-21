import React, {createContext, useContext, useState } from 'react';

export type GlobalContent= {
    id: number
    setId: (id: number) => void
    name: string
    setName: (name: string) => void
    gender: string
    setGender: (gender: string) => void
    birth: number
    setBirth: (birth: number) => void
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
    id: 0,
    setId: () => {},
    name: "",
    setName: () => {},
    gender: "",
    setGender: () => {},
    birth: 0,
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
    setProfileImage: () => {}
})

export const useGlobalContext = () => useContext(SellerContext);