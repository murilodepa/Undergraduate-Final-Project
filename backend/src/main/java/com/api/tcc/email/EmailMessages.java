/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.email;

import com.api.tcc.database.Models.SellerModel;

/**
 * @author Murilo de Paula Araujo
 */
public class EmailMessages {

    public static String createTitle(SellerModel sellerModel) {
        return sellerModel.getName() + ", o seu cadastro como vendedor(a) foi realizado com sucesso!";
    }

    public static String createTitleUpdate(SellerModel sellerModel) {
        return sellerModel.getName() + ", as suas informações pessoais foram atualizados com sucesso!";
    }

    public static String createTitleToRemove(SellerModel sellerModel) {
        return sellerModel.getName() + ", você está sendo removido como vendedor(a) de nossa loja!";
    }

    public static String messageToNewSeller(SellerModel sellerModel) {
        return "Olá " + sellerModel.getName()
                + ",\n\nParabéns! Estamos felizes com a sua contratação! \n\n"
                + "Seja muito bem-vindo(a) como vendedor(a) em nossa loja."
                + " Os seus dados e a sua senha de acesso em nosso aplicativo, estão disponíveis logo abaixo: \n\n"
                + "--------------------------------------------------------------- \n"
                + "Nome: " + sellerModel.getName() + "\n"
                + "E-mail: " + sellerModel.getEmail() + "\n"
                + "Senha: " + sellerModel.getPassword() + "\n"
                + "--------------------------------------------------------------- \n\n"
                + "Para qualquer alteração dos seus dados, procurar o(a) gerente da loja! \n"
                ;
    }

    public static String messageToUpdateSeller(SellerModel sellerModel) {
        return "Olá " + sellerModel.getName()
                + ",\n\nOs seus dados foram atualizados, você pode observar com mais detalhes logo abaixo. Por favor, confira se está tudo certo. \n\n"
                + "--------------------------------------------------------------- \n"
                + "Nome: " + sellerModel.getName() + "\n"
                + "Gênero: " + sellerModel.getGender() + "\n"
                + "Data de Nascimento: " + sellerModel.getBirth() + "\n"
                + "Sector: " + sellerModel.getSector() + "\n"
                + "Número de atendimentos: " + sellerModel.getAttendances() + "\n"
                + "E-mail: " + sellerModel.getEmail() + "\n"
                + "Senha: " + sellerModel.getPassword() + "\n"
                + "--------------------------------------------------------------- \n\n"
                + "Para qualquer outra alteração dos seus dados, procurar o(a) gerente da loja! \n"
                ;
    }

    public static String messageToRemoveSeller(SellerModel sellerModel) {
        return "Olá " + sellerModel.getName()
                + ",\n\nEstamos encaminhando esse e-mail como uma forma de despedida e agradecimento de seu serviço prestado para nossa loja! \n\n"
                + "Desejamos sorte na sua nova jornada e que consiga alcançar seus sonhos e objetivos! \n\n"
                + "Estamos a disposição e de portas abertas, caso deseja trabalhar conosco novamente! \n";
    }
}
