/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.services;

import com.api.tcc.database.Models.ClientModel;
import com.api.tcc.database.Models.ProductModel;
import com.api.tcc.database.Models.PurchaseModel;
import com.api.tcc.database.Models.SellerModel;
import com.api.tcc.database.dtos.PersonDataWithTagsToGiftDTO;
import com.api.tcc.enums.ClothingCategory;
import com.api.tcc.enums.Gender;
import com.api.tcc.repositories.ProductRepository;
import com.api.tcc.repositories.PurchaseRepository;
import com.api.tcc.repositories.SellerRepository;
import com.api.tcc.utils.*;
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

    @Autowired
    private SellerRepository sellerRepository;

    private static final String URL_DECISION_TREE = "http://127.0.0.1:9090/postPurchaseSuggestion";

    /* Variable to send to decision tree:  _ _ _ _ _ _. Gender - Bought - Sold - Size - Seller - Inventory */

    private static final String FEMININO = "Feminino";
    private static final String MASCULINO = "Masculino";

    private static final String INDECISAO = "Indecisão";
    private static final String CAMISETA = "Camiseta";
    private static final String CALCA = "Calça";
    private static final String SHORT = "Short";
    private static final String VESTIDO = "Vestido";

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

    public List<CategorySizeAndDate> getPurchasedList(long clientId, String name, String kinship) {
        List<PurchaseModel> purchaseModelList = purchaseRepository.findAll();
        if (!purchaseModelList.isEmpty()) {
            purchaseModelList.sort(Comparator.comparing(PurchaseModel::getDate).reversed());
        }
        boolean isDuplicated;
        List<CategorySizeAndDate> listOfCategoryDatePurchased = new ArrayList<>();

        if (!purchaseModelList.isEmpty()) {
            for (PurchaseModel purchaseModel : purchaseModelList) {
                if (purchaseModel.getClientModel().getId() == clientId && Objects.equals(purchaseModel.getPersonsName(),
                        name) && Objects.equals(purchaseModel.getKinship(), kinship)) {
                    CategorySizeAndDate categorySizeAndDate = new CategorySizeAndDate(purchaseModel.getCategory(),
                            purchaseModel.getSize(), purchaseModel.getDate());
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
                if (purchaseModel.getClientModel().getId() == id && purchaseModel.getPersonsName() != null &&
                        purchaseModel.getKinship() != null) {
                    NameAndKinship nameAndKinship = new NameAndKinship(purchaseModel.getPersonsName(),
                            purchaseModel.getKinship());
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

 /*   public String getPurchaseSuggestion() throws IOException {
        String result = getPurchaseSuggestion("4,2,3,4");
        System.out.println("Purchase Suggestion: " + result);
        return result;
    }*/

    private boolean isTheCategoryInStock(CategoryAndSize categoryAndSize) {
        List<ProductModel> productModelList = productRepository.findAll();
        String category = categoryAndSize.getCategory(), size = categoryAndSize.getSize();

        for (ProductModel productModel : productModelList) {
            if (Objects.equals(productModel.getCategory(), category) &&
                    Objects.equals(productModel.getSize(), size) &&
                    productModel.getQuantity() > 0) {
                return true;
            }
        }
        return false;
    }

    private String getElementHavingMaxFreq(List<String> recommendedSizeList) {
        if (!recommendedSizeList.isEmpty()) {
            int maxCount = 0, i, j, count, size = recommendedSizeList.size();
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

    private String mostPurchasedSize(List<PurchaseModel> purchaseModelList, String category) {
        List<String> recommendedSizeList = new ArrayList<>();

        for (PurchaseModel purchaseModel : purchaseModelList) {
            if (Objects.equals(purchaseModel.getCategory(), category)) {
                recommendedSizeList.add(purchaseModel.getSize());
            }
        }
        return getElementHavingMaxFreq(recommendedSizeList);
    }

    private char convertCategoryInIndex(String category) {
        if (Objects.equals(category, VESTIDO)) {
            return ClothingCategory.DRESS.getCategory();
        } else if (Objects.equals(category, CALCA)) {
            return ClothingCategory.PANTS.getCategory();
        } else if (Objects.equals(category, SHORT)) {
            return ClothingCategory.SHORT.getCategory();
        } else if (Objects.equals(category, CAMISETA)) {
            return ClothingCategory.SHIRT.getCategory();
        }
        return ClothingCategory.INDECISION.getCategory();
    }

    private char getMostPurchase(List<CategoryAndQuantity> purchaseCategoryQuantityList, List<PurchaseModel>
            purchaseModelOneClientList) {
        int size = purchaseCategoryQuantityList.size(), i;
        String category = purchaseCategoryQuantityList.get(0).getCategory();
        CategoryAndSize categoryAndSize;
        String mostSize;

        if (size == 1) {
            mostSize = mostPurchasedSize(purchaseModelOneClientList, category);
            if (isTheCategoryInStock(new CategoryAndSize(category, mostSize))) {
                return convertCategoryInIndex(category);
            }
        } else {
            for (i = 0; i < size; i++) {
                if ((i + 1) < size &&
                        purchaseCategoryQuantityList.get(i).getQuantity() > purchaseCategoryQuantityList.get((i + 1))
                                .getQuantity() &&
                        purchaseCategoryQuantityList.get(i).getQuantity() == purchaseCategoryQuantityList.get(0)
                                .getQuantity()) {
                    category = purchaseCategoryQuantityList.get(i).getCategory();
                    mostSize = mostPurchasedSize(purchaseModelOneClientList, category);
                    if (isTheCategoryInStock(new CategoryAndSize(category, mostSize))) {
                        return convertCategoryInIndex(category);
                    }
                }
            }
        }
        return ClothingCategory.INDECISION.getCategory();
    }

    private char getMostSize(List<CategorySizeAndQuantity> purchaseCategoryQuantityList) {
        int size = purchaseCategoryQuantityList.size();
        String category = purchaseCategoryQuantityList.get(0).getCategory();
        CategoryAndSize categoryAndSize;

        if (size == 1) {
            categoryAndSize = new CategoryAndSize(category, purchaseCategoryQuantityList.get(0).getSize());
            if (isTheCategoryInStock(categoryAndSize)) {
                return convertCategoryInIndex(category);
            }
        } else {
            for (int i = 0; i < size; i++) {
                if ((i + 1) < size &&
                        purchaseCategoryQuantityList.get(i).getQuantity() > purchaseCategoryQuantityList.get((i + 1))
                                .getQuantity() &&
                        purchaseCategoryQuantityList.get(i).getQuantity() == purchaseCategoryQuantityList.get(0)
                                .getQuantity()) {
                    categoryAndSize = new CategoryAndSize(purchaseCategoryQuantityList.get(i).getCategory(), purchaseCategoryQuantityList.get(i).getSize());

                    if (isTheCategoryInStock(categoryAndSize)) {
                        return convertCategoryInIndex(category);
                    }
                }
            }
        }
        return ClothingCategory.INDECISION.getCategory();
    }

    private char verifyMostSize(List<PurchaseModel> purchaseModelOneClientList, String category, String clothingSize) {
        String mostSize;
        if (purchaseModelOneClientList != null) {
            mostSize = mostPurchasedSize(purchaseModelOneClientList, category);
        } else if (clothingSize != null) {
            mostSize = clothingSize;
        } else {
            return ClothingCategory.INDECISION.getCategory();
        }
        if (isTheCategoryInStock(new CategoryAndSize(category, mostSize))) {
            return convertCategoryInIndex(category);
        }
        return ClothingCategory.INDECISION.getCategory();
    }

    private char sortAndGetMostCategory(List<CategoryAndQuantity> CategoryAndQuantityList, List<PurchaseModel>
            purchaseModelOneClientList, String clothingSize) {
        String category = CategoryAndQuantityList.get(0).getCategory();

        if (!CategoryAndQuantityList.isEmpty()) {
            CategoryAndQuantityList.sort(Comparator.comparing(CategoryAndQuantity::getQuantity).reversed());

            if (CategoryAndQuantityList.size() == 1) {
                return verifyMostSize(purchaseModelOneClientList, category, clothingSize);
            } else {
                int i, size = CategoryAndQuantityList.size();
                for (i = 0; i < size; i++) {
                    if ((i + 1) < size &&
                            CategoryAndQuantityList.get(i).getQuantity() > CategoryAndQuantityList.get((i + 1))
                                    .getQuantity() &&
                            CategoryAndQuantityList.get(i).getQuantity() == CategoryAndQuantityList.get(0)
                                    .getQuantity()) {
                        category = CategoryAndQuantityList.get(i).getCategory();
                        return verifyMostSize(purchaseModelOneClientList, category, clothingSize);
                    }
                }
            }
        }
        return ClothingCategory.INDECISION.getCategory();
    }

    private char getMostProduct(List<PurchaseModel> purchaseModelOneClientList, String size) {
        List<PurchaseModel> purchaseModelList = purchaseRepository.findAll();
        List<CategoryAndQuantity> categoryAndQuantityList = new ArrayList<>();
        boolean flagIsDuplicated;

        for (PurchaseModel purchaseModel : purchaseModelList) {
            flagIsDuplicated = false;
            for (CategoryAndQuantity categoryAndQuantity : categoryAndQuantityList) {
                if (Objects.equals(categoryAndQuantity.getCategory(), purchaseModel.getCategory())) {
                    categoryAndQuantity.setQuantity(categoryAndQuantity.getQuantity() + purchaseModel.getQuantity());
                    flagIsDuplicated = true;
                    break;
                }
            }
            if (!flagIsDuplicated) {
                categoryAndQuantityList.add(new CategoryAndQuantity(purchaseModel.getCategory(),
                        purchaseModel.getQuantity()));
            }
        }

        return sortAndGetMostCategory(categoryAndQuantityList, purchaseModelOneClientList, size);
    }

    private char getMostSeller(List<PurchaseModel> purchaseModelOneClientList, String size) {
        List<SellerModel> sellerModelList = sellerRepository.findAll();
        List<CategoryAndQuantity> categoryAndQuantityOfSellersList = new ArrayList<>();
        boolean flagIsDuplicated;

        for (SellerModel sellerModel : sellerModelList) {
            flagIsDuplicated = false;
            for (CategoryAndQuantity categoryAndQuantity : categoryAndQuantityOfSellersList) {
                if (Objects.equals(categoryAndQuantity.getCategory(), sellerModel.getSector())) {
                    categoryAndQuantity.setQuantity(categoryAndQuantity.getQuantity() + 1);
                    flagIsDuplicated = true;
                    break;
                }
            }
            if (!flagIsDuplicated) {
                categoryAndQuantityOfSellersList.add(new CategoryAndQuantity(sellerModel.getSector(),
                        1));
            }
        }
        return sortAndGetMostCategory(categoryAndQuantityOfSellersList, purchaseModelOneClientList, size);
    }

    private char getMostInStock(List<PurchaseModel> purchaseModelOneClientList, String size) {
        List<ProductModel> productModelList = productRepository.findAll();
        List<CategoryAndQuantity> categoryAndQuantityList = new ArrayList<>();
        boolean flagIsDuplicated;

        for (ProductModel productModel : productModelList) {
            flagIsDuplicated = false;
            for (CategoryAndQuantity categoryAndQuantity : categoryAndQuantityList) {
                if (Objects.equals(categoryAndQuantity.getCategory(), productModel.getCategory())) {
                    categoryAndQuantity.setQuantity(categoryAndQuantity.getQuantity() + productModel.getQuantity());
                    flagIsDuplicated = true;
                    break;
                }
            }
            if (!flagIsDuplicated) {
                categoryAndQuantityList.add(new CategoryAndQuantity(productModel.getCategory(),
                        productModel.getQuantity()));
            }
        }

        return sortAndGetMostCategory(categoryAndQuantityList, purchaseModelOneClientList, size);
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

    public String getMostSuggestion(ClientModel clientModel, String name, String kinship) throws IOException {
        char gender = getGender(clientModel, kinship), mostPurchase, mostSize, mostProduct, mostSeller, mostInStock;
        String suggestion;
        StringBuilder params = new StringBuilder();

        List<PurchaseModel> purchaseModelOneClientList = getPurchaseOfJustOneClient(clientModel.getId(), name, kinship);
        if (purchaseModelOneClientList != null) {

            List<CategoryAndQuantity> separatePurchaseByCategoryList = new ArrayList<>();
            List<CategorySizeAndQuantity> separatePurchaseByCategoryAndSizeList = new ArrayList<>();
            boolean flag_isAddList;

            for (PurchaseModel purchaseModel : purchaseModelOneClientList) {
                flag_isAddList = false;

                for (CategorySizeAndQuantity categorySizeAndQuantity : separatePurchaseByCategoryAndSizeList) {
                    if (Objects.equals(categorySizeAndQuantity.getCategory(), purchaseModel.getCategory()) &&
                            Objects.equals(categorySizeAndQuantity.getSize(), purchaseModel.getSize())) {
                        categorySizeAndQuantity.setQuantity(categorySizeAndQuantity.getQuantity() + purchaseModel.getQuantity());
                        flag_isAddList = true;
                        break;
                    }
                }
                if (!flag_isAddList) {
                    separatePurchaseByCategoryAndSizeList.add(new CategorySizeAndQuantity(purchaseModel.getCategory(), purchaseModel.getSize(), purchaseModel.getQuantity()));
                }

                flag_isAddList = false;
                for (CategoryAndQuantity categoryAndQuantity : separatePurchaseByCategoryList) {
                    if (Objects.equals(categoryAndQuantity.getCategory(), purchaseModel.getCategory())) {
                        categoryAndQuantity.setQuantity(categoryAndQuantity.getQuantity() + purchaseModel.getQuantity());
                        flag_isAddList = true;
                        break;
                    }
                }
                if (!flag_isAddList) {
                    separatePurchaseByCategoryList.add(new CategoryAndQuantity(purchaseModel.getCategory(), purchaseModel.getQuantity()));
                }
            }

            if (!separatePurchaseByCategoryAndSizeList.isEmpty()) {
                separatePurchaseByCategoryAndSizeList.sort(Comparator.comparing(CategorySizeAndQuantity::getQuantity).reversed());
                mostSize = getMostSize(separatePurchaseByCategoryAndSizeList);
            } else {
                mostSize = ClothingCategory.INDECISION.getCategory();
            }

            if (!separatePurchaseByCategoryList.isEmpty()) {
                separatePurchaseByCategoryList.sort(Comparator.comparing(CategoryAndQuantity::getQuantity).reversed());
                mostPurchase = getMostPurchase(separatePurchaseByCategoryList, purchaseModelOneClientList);
            } else {
                mostPurchase = ClothingCategory.INDECISION.getCategory();
            }

            mostProduct = getMostProduct(purchaseModelOneClientList, null);
            mostSeller = getMostSeller(purchaseModelOneClientList, null);
            mostInStock = getMostInStock(purchaseModelOneClientList, null);
            params.append(gender);
            params.append(",");
            params.append(mostPurchase);
            params.append(",");
            params.append(mostSize);
            params.append(",");
            params.append(mostProduct);
            params.append(",");
            params.append(mostSeller);
            params.append(",");
            params.append(mostInStock);
            System.out.println("params: " + params);
            suggestion = getPurchaseSuggestion(String.valueOf(params));

            if (isTheCategoryInStock(new CategoryAndSize(suggestion, mostPurchasedSize(purchaseModelOneClientList, suggestion)))) {
                return suggestion;
            }
        }
        return null;
    }

    private List<DescriptionAndQuantity> getSellingProductList(String category, boolean isKids, String size) {
        List<PurchaseModel> purchaseModelList = purchaseRepository.findAll();
        List<DescriptionAndQuantity> purchaseDataList = new ArrayList<>();
        boolean flag_isAddList;

        for (PurchaseModel purchaseModel : purchaseModelList) {
            flag_isAddList = false;
            if (Objects.equals(purchaseModel.getCategory(), category)) {
                for (DescriptionAndQuantity purchaseData : purchaseDataList) {
                    if (Objects.equals(purchaseData.getDescription(), purchaseModel.getDescription())) {
                        purchaseData.setQuantity(purchaseData.getQuantity() + purchaseModel.getQuantity());
                        flag_isAddList = true;
                        break;
                    }
                }
                if (!flag_isAddList && Objects.equals(purchaseModel.getSize(), size)) {
                    if (isKids && (purchaseModel.getDescription().contains("criança") || purchaseModel
                            .getDescription().contains("infantil"))) {
                        purchaseDataList.add(new DescriptionAndQuantity(purchaseModel.getDescription(),
                                purchaseModel.getQuantity()));
                    } else if (!isKids && !purchaseModel.getDescription().contains("criança") && !purchaseModel
                            .getDescription().contains("infantil")) {
                        purchaseDataList.add(new DescriptionAndQuantity(purchaseModel.getDescription(), purchaseModel
                                .getQuantity()));
                    }
                }
            }
        }
        if (!purchaseDataList.isEmpty()) {
            purchaseDataList.sort(Comparator.comparing(DescriptionAndQuantity::getQuantity).reversed());
            return purchaseDataList;
        }
        return null;
    }

    private String getBestSellingProductWithTags(List<DescriptionAndQuantity> purchaseDataList, boolean winter,
                                                 boolean summer, boolean stripe, boolean printer, boolean casual,
                                                 boolean social) {

        for (DescriptionAndQuantity purchaseData : purchaseDataList) {
            if ((!summer && (purchaseData.getDescription().contains("verão") ||
                    purchaseData.getDescription().contains("curta") ||
                    purchaseData.getDescription().contains("calor"))) ||
                    (summer && (!purchaseData.getDescription().contains("verão") ||
                            !purchaseData.getDescription().contains("curta") ||
                            !purchaseData.getDescription().contains("calor")))) {
                purchaseDataList.remove(purchaseData);
            }
            purchaseDataList.remove(purchaseData);
            if ((!winter && (purchaseData.getDescription().contains("inverno") ||
                    purchaseData.getDescription().contains("frio") ||
                    purchaseData.getDescription().contains("agasalho") ||
                    purchaseData.getDescription().contains("moletom"))) ||
                    (winter && (!purchaseData.getDescription().contains("inverno") ||
                            !purchaseData.getDescription().contains("frio") ||
                            !purchaseData.getDescription().contains("agasalho") ||
                            !purchaseData.getDescription().contains("moletom")))) {
                purchaseDataList.remove(purchaseData);
            }
            if ((!stripe && (purchaseData.getDescription().contains("listra") ||
                    purchaseData.getDescription().contains("listrado") ||
                    purchaseData.getDescription().contains("listrada"))) ||
                    (stripe && (!purchaseData.getDescription().contains("listra") ||
                            purchaseData.getDescription().contains("listrado") ||
                            !purchaseData.getDescription().contains("listrada")))) {
                purchaseDataList.remove(purchaseData);
            }
            if ((!printer && (purchaseData.getDescription().contains("estampa") ||
                    purchaseData.getDescription().contains("estampada") ||
                    purchaseData.getDescription().contains("estampado"))) ||
                    (printer && (!purchaseData.getDescription().contains("estampa") ||
                            !purchaseData.getDescription().contains("estampada") ||
                            !purchaseData.getDescription().contains("estampado")))) {
                purchaseDataList.remove(purchaseData);
            }
            if (!casual && purchaseData.getDescription().contains("casual") ||
                    casual && !purchaseData.getDescription().contains("casual")) {
                purchaseDataList.remove(purchaseData);
            }
            if (!social && purchaseData.getDescription().contains("social") ||
                    casual && !purchaseData.getDescription().contains("social")) {
                purchaseDataList.remove(purchaseData);
            }
        }

        if (!purchaseDataList.isEmpty()) {
            return purchaseDataList.get(0).getDescription();
        }

        return null;
    }

    private char getGender(ClientModel clientModel, String kinship) {
        if (clientModel.getGender() != null && kinship == null) {
            if (Objects.equals(clientModel.getGender(), FEMININO))
                return Gender.FEMININO.getGender();
            if (Objects.equals(clientModel.getGender(), MASCULINO))
                return Gender.MASCULINO.getGender();
            return Gender.OUTROS.getGender();
        } else if (kinship != null) {
            char lastLetter = kinship.substring(kinship.length() - 1).charAt(0);
            if (lastLetter == 'ô' ||
                    lastLetter == 'i' ||
                    lastLetter == 'o') {
                return Gender.MASCULINO.getGender();
            }
        }
        return Gender.FEMININO.getGender(); // a - e - ó
    }

    //Retorna produto mais comprado de um determinado cliente, entrando com uma categoria
    public String getProductToGiftWithCategory(PersonDataWithTagsToGiftDTO params) throws IOException {
        //TO DO VERIFICAR SUGGESTÃO DE COMPRA
        String suggestion, category;
        StringBuilder decisionTreeParams = new StringBuilder();
        char mostProduct, mostSeller, mostInStock;

        mostProduct = getMostProduct(null, params.getSize());
        mostSeller = getMostSeller(null, params.getSize());
        mostInStock = getMostInStock(null, params.getSize());
        decisionTreeParams.append(params.getGender());
        decisionTreeParams.append(",");
        decisionTreeParams.append(ClothingCategory.INDECISION.getCategory());
        decisionTreeParams.append(",");
        decisionTreeParams.append(params.getSize());
        decisionTreeParams.append(",");
        decisionTreeParams.append(mostProduct);
        decisionTreeParams.append(",");
        decisionTreeParams.append(mostSeller);
        decisionTreeParams.append(",");
        decisionTreeParams.append(mostInStock);
        System.out.println("params: " + decisionTreeParams);
        suggestion = getPurchaseSuggestion(String.valueOf(decisionTreeParams));

        if (suggestion != null && params.getAge() != null && params.getSize() != null) {
            List<DescriptionAndQuantity> resultList = getSellingProductList(suggestion, params.getAge().equals("Criança"),
                    params.getSize());
            if (resultList != null) {
                category = getBestSellingProductWithTags(resultList, params.isSelectedWinter(),
                        params.isSelectedSummer(), params.isSelectedStripe(), params.isSelectedPatterned(),
                        params.isSelectedCasual(), params.isSelectedSocial());
                if (category != null)
                    return suggestion;
            }
            return suggestion;
        }
        return "";
    }
}