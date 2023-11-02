package com.example.bai_tapp.service;

import com.example.bai_tapp.dto.IFormDto;
import com.example.bai_tapp.model.Form;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IFormService {

    Page<IFormDto> formList(Pageable pageable);

    Form createForm (Form form);

    Form findById(Integer id);
}
