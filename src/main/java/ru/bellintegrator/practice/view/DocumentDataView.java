package ru.bellintegrator.practice.view;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Данные документа
 */

public class DocumentDataView {
    /**
     * Уникальный идентификатор
     */
    @JsonIgnore
    @NotNull
    public Long id;

    /**
     * Номер документа
     */
    @Size(max = 10)
    @NotEmpty(message = "docNumber can not be null")
    public String docNumber;

    /**
     * Дата
     */
    @Past
    @NotEmpty(message = "docDate can not be null")
    public Date docDate;

    /**
     * Объект связи DocumentData с DocumentType
     */
    public DocumentTypeView docType;
}
