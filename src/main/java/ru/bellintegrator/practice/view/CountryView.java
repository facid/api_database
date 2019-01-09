package ru.bellintegrator.practice.view;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Гражданство
 */

public class CountryView {
    /**
     * Уникальный идентификатор
     */
    @JsonIgnore
    @NotNull(message = "id can not be null")
    public Long id;

    /**
     * Название
     */
    @Size(max = 50)
    @NotEmpty(message = "citizenshipName can not be null")
    public String citizenshipName;

    /**
     * Код
     */
    @Size(max = 3)
    @NotEmpty(message = "citizenshipCode can not be null")
    public String citizenshipCode;
}
