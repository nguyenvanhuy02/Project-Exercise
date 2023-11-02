package com.example.bai_tapp.repository;

import com.example.bai_tapp.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IImgUrlFormRepository
        extends JpaRepository<Image, Integer> {

    @Modifying
    @Query(value = "INSERT INTO image (url,id_form) VALUES (:url, :id_form)", nativeQuery = true)
    void createImgForm(@Param("url") String url,
                          @Param("id_form") Integer id);
}
