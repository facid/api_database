package ru.bellintegrator.practice.view;

import javax.validation.constraints.NotEmpty;

/**
 * Офис
 */

public class OfficeView {

    /**
     * Уникальный идентификатор
     */
    @NotEmpty(message = "id can not be null")
    public Long id;

    /**
     * Уникальный идентификатор организации
     */
    @NotEmpty(message = "orgId can not be null")
    public Long orgId;

    /**
     * Название
     */
    @NotEmpty(message = "name can not be null")
    public String name;

    /**
     * Адрес
     */
    @NotEmpty(message = "address can not be null")
    public String address;

    /**
     * Телефон
     */
    public String phone;

    /**
     * Активность
     */
    @NotEmpty(message = "isActive can not be null")
    public Boolean isActive;

    @Override
    public String toString(){
        return "{id:" + id + ";orgId" + orgId + ";name:" + name + ";address:"
                + address + ";phone:" + phone + ";is_active:" + isActive + "}";
    }
}
