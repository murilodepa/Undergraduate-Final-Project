import backendApi from "../backendApi"

export class SendImageService {
    async insertImage(images: any): Promise<any> {

        var image = new FormData();

        image.append("image", {
          type: "image/jpeg",
          name: `image.jpg`,
          uri: images.uri,
        });
       
        const result = await backendApi.post("/image/sendImage", image, {
            headers: {
              "Content-Type": "multipart/form-data",
            },
        })

        console.log(result)
    }
} 