package ru.bellintegrator.practice.view;

import javax.validation.constraints.NotEmpty;

/**
 * Тип документа
 */

public class DocumentTypeView {

    /**
     * Уникальный идентификатор
     */
    @NotEmpty
    public Long id;

    /**
     * Название
     */
    public String name;

    /**
     * Код
     */
    public String code;

    @Override
    public String toString(){
        return "{id:" + id + ";name:" + name + ";code:" + code +"}";
    }
}
