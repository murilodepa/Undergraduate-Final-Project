import axios from "axios";

const backendApi = axios.create({
    baseURL: "http://:8080",
})

export default backendApi;