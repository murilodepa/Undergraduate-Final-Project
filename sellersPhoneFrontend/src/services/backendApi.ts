import axios from "axios";

const backendApi = axios.create({
    baseURL: "http://192.168.15.85:8080",
})

export default backendApi;