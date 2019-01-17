package ru.bellintegrator.practice.view;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Работник
 */

public class EmployeeView {
    /**
     * Уникальный идентификатор
     */
    @NotNull(message = "id can not be null")
    public Long id;

    /**
     * Уникальный идентификатор офиса
     */
    @NotNull(message = "officeId can not be null")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public Long officeId;

    /**
     * Название документа
     */
    @Size(max = 50)
    @NotEmpty(message = "docName can not be null")
    public String docName;

    /**
     * Код документа
     */
    @Size(max = 2)
    @NotEmpty(message = "docCode can not be null")
    public String docCode;

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
     * Код гражданства
     */
    @Size(max = 3)
    @NotEmpty(message = "citizenshipCode can not be null")
    public String citizenshipCode;

    /**
     * Имя
     */
    @Size(max = 50)
    @NotEmpty(message = "firstName can not be null")
    public String firstName;

    /**
     * Фамилия
     */
    @Size(max = 50)
    public String secondName;

    /**
     * Отчество
     */
    @Size(max = 50)
    public String middleName;

    /**
     * Должность
     */
    @Size(max = 50)
    @NotEmpty(message = "position can not be null")
    public String position;

    /**
     * Телефон
     */
    @Size(max = 15)
    public String phone;

    /**
     * Объект связи Employee с DocumentData
     */
    public DocumentDataView docData;

    /**
     * Объект связи Employee с Country
     */
    public CountryView country;

    /**
     * Идентификация
     */
    @NotEmpty(message = "isIdentified can not be null")
    public Boolean isIdentified;
}
