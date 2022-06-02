import backendApi from "../backendApi";
import {
  ISellerImageAndName,
  IGetSellerImageAndNameProps,
} from "./SendImageSellerServiceInterface";

export class SendImageSellerService implements IGetSellerImageAndNameProps {
  async insertImage(images: any): Promise<any> {
    var image = new FormData();

    image.append("image", {
      type: "image/jpeg",
      name: `image.jpg`,
      uri: images,
    });

    const result = await backendApi.post("/seller/sendImage", image, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
    console.log(result);
  }

  getSellerImageAndName = async (id: number): Promise<ISellerImageAndName> => {
    //{{host}}/client/getUserImage/1
    const { data } = await backendApi.get("/seller/getSellerImage/" + id);
    console.log(data);
    return data;
  };
}
