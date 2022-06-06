package com.api.tcc.controllers;

import com.api.tcc.database.Models.ClientImageModel;
import com.api.tcc.database.Models.ClientModel;
import com.api.tcc.database.dtos.IdImageNameDTO;
import com.api.tcc.database.dtos.ImageAndNamesDTO;
import com.api.tcc.services.ClientService;
import com.api.tcc.utils.ManipulatingImage;
import com.api.tcc.services.ClientImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientImageController {

    @Autowired
    private ClientImageService clientImageService;
    @Autowired
    private ClientService clientService;
    private ManipulatingImage manipulatingImage = new ManipulatingImage();

    @PostMapping("/sendImage")
    public ResponseEntity<?> sendImage(@RequestPart(value = "image", required = false) MultipartFile image) throws Exception {
        System.out.println("Images Receives: " + image.getInputStream());
        final byte[] encodeImage;
        long clientId = (clientImageService.getClientId());
        if (clientId > 0) {
            final String fileName = manipulatingImage.fileName(true, (int) (clientId));
            try {
                Files.write(manipulatingImage.fileNameAndPath(true, fileName), image.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            encodeImage = manipulatingImage.encodeImage(true, fileName);
            clientImageService.saveImage(encodeImage, clientId);
            return ResponseEntity.ok("Image was received successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found!");
        }
    }

    @GetMapping("/getClientImage/{id}")
    public ResponseEntity<ImageAndNamesDTO> getClientImage(@PathVariable(value = "id") long id) throws IOException {
        List<ClientImageModel> clientImageModelList = clientImageService.findClientImages(id);
        ImageAndNamesDTO imageAndNamesDTO = new ImageAndNamesDTO();
        if (clientImageModelList.isEmpty()) {
            imageAndNamesDTO.setProfileImage(null);
            imageAndNamesDTO.setName(null);
            return ResponseEntity.status(HttpStatus.OK).body(imageAndNamesDTO);
        }
        ClientImageModel clientImageModel = clientImageModelList.get(0);
        imageAndNamesDTO.setProfileImage(manipulatingImage.decodeImage(clientImageModel.getImage()));
        imageAndNamesDTO.setName(clientImageModel.getClientModel().getName());
        return ResponseEntity.status(HttpStatus.OK).body(imageAndNamesDTO);
    }

    @GetMapping("/getClientImageNameList")
    public ResponseEntity<List<IdImageNameDTO>> getClientIdImageNameList() throws IOException {
        List<ClientModel> clientModelList = clientService.findAll();
        if(!clientModelList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(clientImageService.findClientIdImagesNamesList(clientModelList));
        }
        return null;
    }
}
