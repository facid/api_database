package ru.bellintegrator.practice.view;

import javax.validation.constraints.NotEmpty;

/**
 * Работник
 */

public class EmployeeView {

    /**
     * Уникальный идентификатор
     */
    @NotEmpty(message = "id can not be null")
    public Long id;

    /**
     * Уникальный идентификатор офиса
     */
    @NotEmpty(message = "officeId can not be null")
    public Long officeId;

    /**
     * Уникальный идентификатор данных документа
     */
    @NotEmpty(message = "docDataId can not be null")
    public Long docDataId;

    /**
     * Уникальный идентификатор гражданства
     */
    @NotEmpty(message = "countryId can not be null")
    public Long countryId;

    /**
     * Имя
     */
    @NotEmpty(message = "firstName can not be null")
    public String firstName;

    /**
     * Отчество
     */
    public String secondName;

    /**
     * Фамилия
     */
    public String lastName;

    /**
     * Должность
     */
    @NotEmpty(message = "position can not be null")
    public String position;

    /**
     * Телефон
     */
    public String phone;

    /**
     * Идентификация
     */
    @NotEmpty(message = "isIdentified can not be null")
    public Boolean isIdentified;

    @Override
    public String toString(){
        return "{id:" + id + ";officeId:" + officeId + ";docDataId:" + docDataId + ";countryId:" + countryId
                + ";firstName:" + firstName + ";secondName:" + secondName + ";lastName:" + lastName
                + ";position:" + position + ";phone:" + phone + ";isIdentified:" + isIdentified + "}";
    }
}
