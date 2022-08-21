/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.services;

import com.api.tcc.database.Models.ClientModel;
import com.api.tcc.database.Models.SellerImageModel;
import com.api.tcc.database.Models.SellerModel;
import com.api.tcc.database.dtos.RegistrationSellerDTO;
import com.api.tcc.email.EmailMessages;
import com.api.tcc.email.SendEmail;
import com.api.tcc.repositories.SellerImageRepository;
import com.api.tcc.repositories.SellerRepository;
import com.api.tcc.utils.FormattingDates;
import com.api.tcc.utils.IndexAndName;
import com.api.tcc.utils.ManipulatingImage;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Murilo de Paula Araujo
 */
@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private SendEmail sendEmail;

    @Autowired
    private SellerImageRepository sellerImageRepository;

    @Transactional
    public SellerModel save(SellerModel sellerModel) {
        this.sendEmail.send(sellerModel.getEmail(),
                EmailMessages.createTitle(sellerModel), EmailMessages.messageToNewSeller(sellerModel));
        return sellerRepository.save(sellerModel);
    }

    @Transactional
    public SellerModel update(SellerModel sellerModel) {
        this.sendEmail.send(sellerModel.getEmail(),
                EmailMessages.createTitleUpdate(sellerModel), EmailMessages.messageToUpdateSeller(sellerModel));
        return sellerRepository.save(sellerModel);
    }

    public List<SellerModel> findAll() {
        return sellerRepository.findAll();
    }

    public List<SellerModel> findAllSellerAvailable() {
        return sellerRepository.findAllSellerAvailable();
    }

    public Optional<SellerModel> findById(long id) {
        return sellerRepository.findById(id);
    }

    private void deletePhotos(long sellerId) {
        String sellerPhotosPath;
        int count;
        for(count=1; count<=ManipulatingImage.QUANTITY_OF_PHOTOS; count++) {
            sellerPhotosPath = "src\\main\\resources\\photos\\sellers\\person." + sellerId + "." + count + ".jpg";
            File image = new File(sellerPhotosPath);
            if(image.exists()) {
                image.delete();
            }
        }
    }

    @Transactional
    public void delete(SellerModel sellerModel) {
        deletePhotos(sellerModel.getId());
        this.sendEmail.send(sellerModel.getEmail(),
                EmailMessages.createTitleToRemove(sellerModel), EmailMessages.messageToRemoveSeller(sellerModel));
        sellerRepository.delete((sellerModel));
    }

    public boolean existsByEmail(String email) {
        return sellerRepository.existsByEmail(email);
    }

    public List<IndexAndName> getSellerNames() {
        List<IndexAndName> sellerDate = new ArrayList<>();
        List<SellerModel> sellerModelList = sellerRepository.findAll();

        for (SellerModel sellerModel : sellerModelList) {
            sellerDate.add(new IndexAndName(sellerModel.getId(), sellerModel.getName()));
        }
        return sellerDate;
    }

    public RegistrationSellerDTO getRegisteredSeller(String email, String password) throws IOException {
        List<SellerModel> sellerModelList = sellerRepository.findAll();
        RegistrationSellerDTO registrationSellerDTO = new RegistrationSellerDTO();
        ManipulatingImage manipulatingImage = new ManipulatingImage();


        for (SellerModel sellerModel : sellerModelList) {
            if (Objects.equals(email, sellerModel.getEmail()) && Objects.equals(password, sellerModel.getPassword())) {
                List<SellerImageModel> sellerImageModelList = sellerImageRepository.findSellerImages(sellerModel.getId());
                BeanUtils.copyProperties(sellerModel, registrationSellerDTO);
                registrationSellerDTO.setProfileImage(manipulatingImage.decodeImage(sellerImageModelList.get(0).getImage()));
                FormattingDates formattingDates = new FormattingDates();
                registrationSellerDTO.setBirth(formattingDates.convertDateToAge(sellerModel.getBirth()));
                return registrationSellerDTO;
            }
        }
        return null;
    }

    //TO DO Melhorar esse método e aplicar no end point criado em python
    public String matchMakingGetBestSeller(List<SellerModel> sellerModelList, ClientModel clientModel) {
        try(CloseableHttpClient closeableHttpClient = HttpClients.createDefault()) {
            JSONObject jsonObject = new JSONObject();
            int i=0;
            List<NameValuePair> sellerModelParameters = new ArrayList<>();
            for(SellerModel sellerModel: sellerModelList) {
                sellerModelParameters.add(new BasicNameValuePair("seller-"+i, sellerModel.getName()));
            }
            jsonObject.put("content", sellerModelParameters);
            StringEntity params = new StringEntity(jsonObject.toString());
            HttpPost httpPost = new HttpPost("http://127.0.0.1:9090/postMatchMaking");
            httpPost.addHeader("content-type", "application/json");
            httpPost.setEntity(params);

            try (CloseableHttpResponse response = closeableHttpClient.execute(httpPost)) {
                ResponseHandler<String> handler = new BasicResponseHandler();
                String body = handler.handleResponse(response);
                System.out.println(body);
                return body;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
