package com.example.bai_tapp.controller;

import com.example.bai_tapp.dto.FormDto;
import com.example.bai_tapp.dto.IFormDto;
import com.example.bai_tapp.dto.ImgFormDto;
import com.example.bai_tapp.model.Form;
import com.example.bai_tapp.model.Image;
import com.example.bai_tapp.service.IFormService;
import com.example.bai_tapp.service.IImgUrlFormService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.List;

@Validated
@CrossOrigin("*")
@RestController
@RequestMapping("/form")
public class FormController {

    @Autowired
    private IFormService fromService;

    @Autowired
    private IImgUrlFormService iImgUrlFormService;

    @GetMapping("/list")
    public ResponseEntity<Page<IFormDto>> formList(
            @PageableDefault(value = 5) Pageable pageable
    ) {
        Page<IFormDto> formListDto = fromService.formList(pageable);
        if (formListDto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(formListDto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createForm(@RequestBody @Valid FormDto formDto) {
        Form form = new Form();
        BeanUtils.copyProperties(formDto, form);
        fromService.createForm(form);
        return new ResponseEntity<>(form, HttpStatus.OK);
    }

    @PostMapping("/img/create")
    public ResponseEntity<List<FieldError>> saveImg(@RequestBody @Valid ImgFormDto imgFormDto) {
        Image image = new Image();
        BeanUtils.copyProperties(imgFormDto, image);
        Form form = fromService.findById(imgFormDto.getFormId());
        image.setForm(form);
        iImgUrlFormService.saveImgForm(image);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
