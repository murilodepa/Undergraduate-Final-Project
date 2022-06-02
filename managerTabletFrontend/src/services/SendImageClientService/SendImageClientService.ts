import backendApi from "../backendApi"
import { IGetClientImageAndNameProps, IClientImageAndName } from "./SendImageClientServiceInterface";

export class SendImageClientService implements IGetClientImageAndNameProps {
    async insertImage(images: any): Promise<any> {

        var image = new FormData();

        image.append("image", {
          type: "image/jpeg",
          name: `image.jpg`,
          uri: images,
        });
       
        const result = await backendApi.post("/client/sendImage", image, {
            headers: {
              "Content-Type": "multipart/form-data",
            },
        })
        console.log(result)
    }

    getClientImageAndName = async (id: number): Promise<IClientImageAndName> => {
//{{host}}/client/getUserImage/1
      const { data } = await backendApi.get("/client/getClientImage/" + id); 
      console.log(data);
      return data;
    }
} 