package ru.bellintegrator.practice.dao.country;

import ru.bellintegrator.practice.model.Country;

import java.util.List;

/**
 * DAO для работы с Country
 */

public interface CountryDao {

    /**
     * Получить все страны
     *
     * @return
     */
    List<Country> getCountries();
}
