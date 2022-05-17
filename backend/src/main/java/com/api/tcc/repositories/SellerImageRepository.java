package com.api.tcc.repositories;

import com.api.tcc.database.Models.SellerImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerImageRepository extends JpaRepository<SellerImageModel, Long> {
}
