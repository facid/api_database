package ru.bellintegrator.practice.view;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Тип документа
 */

public class DocumentTypeView {
    /**
     * Уникальный идентификатор
     */
    @JsonIgnore
    @NotNull
    public Long id;

    /**
     * Название
     */
    @Size(max = 50)
    @NotEmpty(message = "docName can not be null")
    public String docName;

    /**
     * Код
     */
    @Size(max = 2)
    @NotEmpty(message = "docCode can not be null")
    public String docCode;
}
