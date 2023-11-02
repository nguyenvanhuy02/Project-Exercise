package com.example.bai_tapp.dto;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ImgFormDto implements Validator {

    private Integer id;
    private String url;
    private Integer formId;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public Integer getFormId() {
        return formId;
    }

    public void setFormId(final Integer formId) {
        this.formId = formId;
    }

    @Override
    public boolean supports(final Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(final Object target, final Errors errors) {

    }
}
