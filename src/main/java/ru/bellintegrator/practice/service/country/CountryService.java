package ru.bellintegrator.practice.service.country;

import org.springframework.validation.annotation.Validated;
import ru.bellintegrator.practice.view.CountryView;

import java.util.List;

/**
 * Сервис для работы с CountryView
 */

@Validated
public interface CountryService {

    /**
     * Получить все страны
     *
     * @return
     */
    List<CountryView> getCountries();
}
