/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.services;

import com.api.tcc.database.Models.ProductModel;
import com.api.tcc.database.Models.PurchaseModel;
import com.api.tcc.repositories.ProductRepository;
import com.api.tcc.repositories.PurchaseRepository;
import com.api.tcc.utils.CategorySizeAndDate;
import com.api.tcc.utils.NameAndKinship;
import com.api.tcc.utils.PurchaseData;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

/**
 * @author Murilo de Paula Araujo
 */
@Service
public class PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ProductRepository productRepository;

    private static final String URL_DECISION_TREE = "http://127.0.0.1:9090/postPurchaseSuggestion";

    @Transactional
    public PurchaseModel save(PurchaseModel purchaseModel) {
        return purchaseRepository.save(purchaseModel);
    }

    public List<PurchaseModel> findAll() {
        return purchaseRepository.findAll();
    }

    public Optional<PurchaseModel> findById(UUID id) {
        return purchaseRepository.findById(id);
    }

    @Transactional
    public void delete(PurchaseModel purchaseModel) {
        purchaseRepository.delete(purchaseModel);
    }

    public String getPurchaseSuggestion(String request) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            JSONObject json = new JSONObject();
            json.put("content", request);
            StringEntity params = new StringEntity(json.toString());
            HttpPost httpPost = new HttpPost(URL_DECISION_TREE);
            httpPost.addHeader("content-type", "application/json");
            httpPost.setEntity(params);

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                ResponseHandler<String> handler = new BasicResponseHandler();
                String body = handler.handleResponse(response);
                System.out.println(body);
                return body;
            }
        }
    }

    public List<CategorySizeAndDate> getPurchasedList(long clientId, String name, String kinship) throws ParseException {
        List<PurchaseModel> purchaseModelList = purchaseRepository.findAll();
        purchaseModelList.sort(Comparator.comparing(PurchaseModel::getDate).reversed());
        boolean isDuplicated;
        List<CategorySizeAndDate> listOfCategoryDatePurchased = new ArrayList<>();

        if (!purchaseModelList.isEmpty()) {
            for (PurchaseModel purchaseModel : purchaseModelList) {
                if (purchaseModel.getClientModel().getId() == clientId && Objects.equals(purchaseModel.getPersonsName(), name) && Objects.equals(purchaseModel.getKinship(), kinship)) {
                    CategorySizeAndDate categorySizeAndDate = new CategorySizeAndDate(purchaseModel.getCategory(), purchaseModel.getSize(), purchaseModel.getDate());
                    if (!listOfCategoryDatePurchased.isEmpty()) {
                        isDuplicated = false;
                        for (CategorySizeAndDate categorySizeAndDateAux : listOfCategoryDatePurchased) {
                            if (Objects.equals(categorySizeAndDateAux.getCategory(), categorySizeAndDate.getCategory()) &&
                                    Objects.equals(categorySizeAndDateAux.getDate(), categorySizeAndDate.getDate()) &&
                                    Objects.equals(categorySizeAndDateAux.getSize(), categorySizeAndDate.getSize())) {
                                isDuplicated = true;
                                break;
                            }
                        }
                        if (!isDuplicated) {
                            listOfCategoryDatePurchased.add(categorySizeAndDate);
                        }
                    } else {
                        listOfCategoryDatePurchased.add(categorySizeAndDate);
                    }
                }
            }
            return listOfCategoryDatePurchased;
        }
        return null;
    }

    public List<NameAndKinship> getPeopleGifts(long id) {
        List<PurchaseModel> purchaseModelList = purchaseRepository.findAll();
        List<NameAndKinship> nameAndKinshipList = new ArrayList<>();
        boolean isDuplicated;

        if (!purchaseModelList.isEmpty()) {
            for (PurchaseModel purchaseModel : purchaseModelList) {
                if (purchaseModel.getClientModel().getId() == id && purchaseModel.getPersonsName() != null && purchaseModel.getKinship() != null) {
                    NameAndKinship nameAndKinship = new NameAndKinship(purchaseModel.getPersonsName(), purchaseModel.getKinship());
                    if (!nameAndKinshipList.isEmpty()) {
                        isDuplicated = false;
                        for (NameAndKinship nameAndKinshipAux : nameAndKinshipList) {
                            if (Objects.equals(nameAndKinshipAux.getName(), nameAndKinship.getName()) &&
                                    Objects.equals(nameAndKinshipAux.getKinship(), nameAndKinship.getKinship())) {
                                isDuplicated = true;
                                break;
                            }
                        }
                        if (!isDuplicated) {
                            nameAndKinshipList.add(nameAndKinship);
                        }
                    } else {
                        nameAndKinshipList.add(nameAndKinship);
                    }
                }
            }
        }
        if (!nameAndKinshipList.isEmpty()) {
            return nameAndKinshipList;
        }
        return null;
    }

    public String getPurchaseSuggestion() throws IOException {
        String result = getPurchaseSuggestion("4,2,3,4");
        System.out.println("Purchase Suggestion: " + result);
        return result;
    }

    public List<PurchaseData> getListPurchasedProductsForOneClient(long id, PurchaseData purchase) {
        List<PurchaseModel> purchaseModelList = purchaseRepository.findAll();
        List<PurchaseData> purchaseDataList = new ArrayList<>();
        boolean flag_isAddList;

        purchaseDataList.add(new PurchaseData(purchase.getCategory(), purchase.getQuantity()));

        if (!purchaseModelList.isEmpty()) {
            for (PurchaseModel purchaseModel : purchaseModelList) {
                if (purchaseModel.getClientModel().getId() == id) {
                    if (!purchaseDataList.isEmpty()) {
                        flag_isAddList = false;

                        for (PurchaseData purchaseData : purchaseDataList) {
                            if (Objects.equals(purchaseData.getCategory(), purchaseModel.getCategory())) {
                                purchaseData.setQuantity(purchaseData.getQuantity() + purchaseModel.getQuantity());
                                flag_isAddList = true;
                                break;
                            }
                        }
                        if (!flag_isAddList) {
                            purchaseDataList.add(new PurchaseData(purchaseModel.getCategory(), purchaseModel.getQuantity()));
                        }
                    }
                }
            }
        }

        if (!purchaseDataList.isEmpty()) {
            purchaseDataList.sort(Comparator.comparing(PurchaseData::getQuantity).reversed());
        }

        return purchaseDataList;
    }

    private boolean isTheCategoryInStock(String category, String size) {
        List<ProductModel> productModelList = productRepository.findAll();

        for (ProductModel productModel : productModelList) {
            if (Objects.equals(productModel.getCategory(), category) &&
                    Objects.equals(productModel.getSize(), size) &&
                    productModel.getQuantity() > 0) {
                return true;
            }
        }
        return false;
    }

    private String mostPurchasedSize(List<PurchaseModel> purchaseModelList, String category) {
        //TO DO retornar o tamanho que o cliente mais compra String name (optional para presente)
        List<String> recommendedSizeList = new ArrayList<>();

        for (PurchaseModel purchaseModel : purchaseModelList) {
            if (Objects.equals(purchaseModel.getCategory(), category)) {
                recommendedSizeList.add(purchaseModel.getSize());
            }
        }
        if (!recommendedSizeList.isEmpty()) {
            int maxCount = 0, i , j, count, size = recommendedSizeList.size();
            String element_having_max_freq = "";
            for (i = 0; i < size; i++) {
                count = 0;
                for (j = 0; j < size; j++) {
                    if (Objects.equals(recommendedSizeList.get(i), recommendedSizeList.get(j))) {
                        count++;
                    }
                }

                if (count > maxCount) {
                    maxCount = count;
                    element_having_max_freq = recommendedSizeList.get(i);
                }
            }
            return element_having_max_freq;
        }
        return "";
    }

    public String getBiggestCategoryOfProducts(List<PurchaseData> purchaseCategoryQuantityList, List<PurchaseModel> purchaseModelOneClientList) {
        int size = purchaseCategoryQuantityList.size();
        String category = purchaseCategoryQuantityList.get(0).getCategory();

        if (size == 1) {
            return category + " (" + mostPurchasedSize(purchaseModelOneClientList, category) + ")";
        } else {
            for(int i=0; i<4; i++) {
                if ((i+1) < 4 &&
                        purchaseCategoryQuantityList.get(i).getQuantity() > purchaseCategoryQuantityList.get((i+1)).getQuantity() &&
                        purchaseCategoryQuantityList.get(i).getQuantity() == purchaseCategoryQuantityList.get(0).getQuantity()){
                    category = purchaseCategoryQuantityList.get(i).getCategory();
                    String mostSize = mostPurchasedSize(purchaseModelOneClientList, category);
                    if(isTheCategoryInStock(category, mostSize)) {
                        return category + " (" + mostSize + ")";
                    }
                }
            }
        }

        return null;
    }

/*
    public String getSuggestionGifts(long id, String name, String kinship) {
        List<PurchaseModel> purchaseModelOneClientList = getPurchaseOfJustOneClientGift(id, name, kinship);
        if (purchaseModelOneClientList != null) {
            List<PurchaseData> purchaseCategoryQuantityList = getListPurchasedProductsForOnePersonGift(purchaseModelOneClientList);
            if (purchaseCategoryQuantityList != null) {
                String result = getBiggestCategoryOfProducts(purchaseCategoryQuantityList, purchaseModelOneClientList);
                if (result != null) {
                    return result;
                }
            }
        }
        return "";
    }
*/


    private List<PurchaseData> getListPurchasedProductsForOnePersonGift(List<PurchaseModel> purchaseModelOneClientList) {
        List<PurchaseData> purchaseDataList = new ArrayList<>();
        boolean flag_isAddList;

        for (PurchaseModel purchaseModel : purchaseModelOneClientList) {
            if (!purchaseDataList.isEmpty()) {
                flag_isAddList = false;

                for (PurchaseData purchaseData : purchaseDataList) {
                    if (Objects.equals(purchaseData.getCategory(), purchaseModel.getCategory())) {
                        purchaseData.setQuantity(purchaseData.getQuantity() + purchaseModel.getQuantity());
                        flag_isAddList = true;
                        break;
                    }
                }
                if (!flag_isAddList) {
                    purchaseDataList.add(new PurchaseData(purchaseModel.getCategory(), purchaseModel.getQuantity()));
                }
            } else {
                purchaseDataList.add(new PurchaseData(purchaseModel.getCategory(), purchaseModel.getQuantity()));
            }
        }

        if (!purchaseDataList.isEmpty()) {
            purchaseDataList.sort(Comparator.comparing(PurchaseData::getQuantity).reversed());
            return purchaseDataList;
        }
        return null;
    }

    private List<PurchaseModel> getPurchaseOfJustOneClient(long clientId, String name, String kinship) {
        List<PurchaseModel> purchaseModelList = purchaseRepository.findAll();
        List<PurchaseModel> purchaseModelOneClientList = new ArrayList<>();

        if (!purchaseModelList.isEmpty()) {
            for (PurchaseModel purchaseModel : purchaseModelList) {
                if (clientId == purchaseModel.getClientModel().getId() &&
                        Objects.equals(name, purchaseModel.getPersonsName()) &&
                        Objects.equals(kinship, purchaseModel.getKinship())) {
                    purchaseModelOneClientList.add(purchaseModel);
                }
            }
            if (!purchaseModelOneClientList.isEmpty()) {
                return purchaseModelOneClientList;
            }
            return null;
        }
        return null;
    }

    public String getMostSuggestion(long id, String name, String kinship) {
        List<PurchaseModel> purchaseModelOneClientList = getPurchaseOfJustOneClient(id, name, kinship);
        if (purchaseModelOneClientList != null) {
            List<PurchaseData> purchaseCategoryQuantityList = getListPurchasedProductsForOnePersonGift(purchaseModelOneClientList);
            if (purchaseCategoryQuantityList != null) {
                String result = getBiggestCategoryOfProducts(purchaseCategoryQuantityList, purchaseModelOneClientList);
                if (result != null) {
                    return result;
                }
            }
        }
        return "";
    }
}