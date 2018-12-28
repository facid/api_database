package ru.bellintegrator.practice.view;

import javax.validation.constraints.NotEmpty;

/**
 * Гражданство
 */

public class CountryView {

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
