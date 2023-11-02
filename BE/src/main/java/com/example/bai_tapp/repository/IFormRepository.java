package com.example.bai_tapp.repository;

import com.example.bai_tapp.dto.IFormDto;
import com.example.bai_tapp.model.Form;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface IFormRepository extends JpaRepository<Form,Integer> {

    @Query(value = "select f.id, f.name, f.description, i.url \n" +
            "             from `form` as f \n" +
            "            join `image` as i on f.id = i.id_form \n" +
            "            group by f.id order by f.id Desc ", nativeQuery = true)
    Page<IFormDto> formList(Pageable pageable);
}
