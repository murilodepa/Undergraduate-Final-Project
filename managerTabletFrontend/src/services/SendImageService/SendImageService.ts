import backendApi from "../backendApi"

export class SendImageService {
    async insertImage(image: string): Promise<any> {
        console.log("chegou aqui" + image)
        const a = await backendApi.post("/image/sendImage", null, {
            params: {
                image
            },
        })
        console.log("bbbbbbbbbb " + a)
    }
   
} 