package com.api.tcc.controllers;

import com.api.tcc.database.Models.ClientImageModel;
import com.api.tcc.database.dtos.ImageAndNamesDTO;
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
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientImageController {

    @Autowired
    private ClientImageService clientImageService;
    private ManipulatingImage manipulatingImage = new ManipulatingImage();

    @PostMapping("/sendImage")
    public ResponseEntity<?> sendImage(@RequestPart(value = "image", required = false) MultipartFile image) throws Exception {
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
        Optional<List<ClientImageModel>> clientImageModelOptional = clientImageService.findClientImages(id);
        if(!clientImageModelOptional.isPresent()) {
            return null;
        }
        ClientImageModel clientImageModel = clientImageModelOptional.get().get(0);
        ImageAndNamesDTO imageAndNamesDTO = new ImageAndNamesDTO();
        imageAndNamesDTO.setProfileImage(manipulatingImage.decodeImage(clientImageModel.getImage()));
        imageAndNamesDTO.setName(clientImageModel.getClientModel().getName());
        return ResponseEntity.status(HttpStatus.OK).body(imageAndNamesDTO);
    }
}
