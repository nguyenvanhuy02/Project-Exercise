package com.example.bai_tapp.service.impl;

import com.example.bai_tapp.model.Image;
import com.example.bai_tapp.repository.IImgUrlFormRepository;
import com.example.bai_tapp.service.IImgUrlFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImgUrlFormService
        implements IImgUrlFormService {

    @Autowired
    private IImgUrlFormRepository iImgUrlFormRepository;

    @Override
    public void saveImgForm(Image image) {
        iImgUrlFormRepository.createImgForm(image.getUrl(), image.getForm().getId());
    }
}
