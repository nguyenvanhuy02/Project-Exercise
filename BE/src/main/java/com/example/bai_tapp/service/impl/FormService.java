package com.example.bai_tapp.service.impl;

import com.example.bai_tapp.dto.IFormDto;
import com.example.bai_tapp.model.Form;
import com.example.bai_tapp.repository.IFormRepository;
import com.example.bai_tapp.service.IFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormService implements IFormService {

    @Autowired
    private IFormRepository formRepository;

    @Override
    public Page<IFormDto> formList(Pageable pageable){
        return formRepository.formList(pageable);
    }

    @Override
    public Form createForm(Form form){
        return formRepository.save(form);
    }

    @Override
    public Form findById(Integer id){
        return formRepository.findById(id).orElse(null);
    }
}
