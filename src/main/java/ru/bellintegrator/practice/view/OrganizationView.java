package ru.bellintegrator.practice.view;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Организация
 */

public class OrganizationView {
    /**
     * Уникальный идентификатор
     */
    @NotNull(message = "id cannot be null")
    public Long id;

    /**
     * Название
     */
    @Size(max = 10)
    @NotEmpty(message = "name cannot be null")
    public String name;

    /**
     * Полное название
     */
    @Size(max = 50)
    @NotEmpty(message = "fullName cannot be null")
    public String fullName;

    /**
     * Телефон
     */
    @Size(max = 15)
    public String phone;

    /**
     * Адрес
     */
    @NotEmpty(message = "address cannot be null")
    public String address;

    /**
     * ИНН
     */
    @Size(max = 10)
    @NotEmpty(message = "inn cannot be null")
    public String inn;

    /**
     * КПП
     */
    @Size(max = 9)
    @NotEmpty(message = "kpp cannot be null")
    public String kpp;

    /**
     * Активность
     */
    @NotEmpty(message = "isActive cannot be null")
    public Boolean isActive;
}
