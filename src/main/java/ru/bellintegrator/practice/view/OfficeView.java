package ru.bellintegrator.practice.view;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Офис
 */

public class OfficeView {
    /**
     * Уникальный идентификатор
     */
    @NotNull(message = "id can not be null")
    public Long id;

    /**
     * Уникальный идентификатор организации
     */
    @NotNull(message = "orgId can not be null")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public Long orgId;

    /**
     * Название
     */
    @Size(max = 50)
    @NotEmpty(message = "name can not be null")
    public String name;

    /**
     * Адрес
     */
    @Size(max = 50)
    @NotEmpty(message = "address can not be null")
    public String address;

    /**
     * Телефон
     */
    @Size(max = 15)
    public String phone;

    /**
     * Активность
     */
    @NotEmpty(message = "isActive can not be null")
    public Boolean isActive;
}
