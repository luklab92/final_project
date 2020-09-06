package com.sda.final_project_wro27.repository;

import com.sda.final_project_wro27.model.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategoryEntity, Long> {
    
    @Query("select case when count(pc)> 0 then true else false end from ProductCategoryEntity pc where lower(pc.title) like lower(?1)")
    boolean existsByTitle(String title);
}
