import axios from "axios";

const backendApi = axios.create({
    baseURL: "http://172.16.226.206:8080",
})

export default backendApi;