package ru.bellintegrator.practice.view;

import javax.validation.constraints.NotEmpty;

/**
 * Организация
 */

public class OrganizationView {

    /**
     * Уникальный идентификатор
     */
    @NotEmpty(message = "id cannot be null")
    public Long id;

    /**
     * Название
     */
    @NotEmpty(message = "name cannot be null")
    public String name;

    /**
     * Полное название
     */
    @NotEmpty(message = "fullName cannot be null")
    public String fullName;

    /**
     * Телефон
     */
    public String phone;

    /**
     * Адрес
     */
    @NotEmpty(message = "address cannot be null")
    public String address;

    /**
     * ИНН
     */
    @NotEmpty(message = "inn cannot be null")
    public String inn;

    /**
     * КПП
     */
    @NotEmpty(message = "kpp cannot be null")
    public String kpp;

    /**
     * Активность
     */
    @NotEmpty(message = "isActive cannot be null")
    public Boolean isActive;


    @Override
    public String toString(){
        return "{id:" + id + ";name:" + name + ";full_name:" + fullName + ";address:" + address
                + ";phone:" + phone + ";inn:" + inn + ";kpp:" + kpp + ";is_active:" + isActive + "}";
    }
}
