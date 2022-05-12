import backendApi from "../backendApi"

export class SendImageSellerService {
    async insertImage(images: any): Promise<any> {

        var image = new FormData();

        image.append("image", {
          type: "image/jpeg",
          name: `image.jpg`,
          uri: images,
        });
       
        const result = await backendApi.post("/sellerImage/sendImage", image, {
            headers: {
              "Content-Type": "multipart/form-data",
            },
        })
        console.log(result)
    }
} 