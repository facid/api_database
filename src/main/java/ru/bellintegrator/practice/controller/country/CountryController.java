package ru.bellintegrator.practice.controller.country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.service.country.CountryService;
import ru.bellintegrator.practice.view.CountryView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Контроллер для управления информацией о гражданстве
 */

@RestController
@RequestMapping(value = "api", produces = APPLICATION_JSON_VALUE)
public class CountryController {
    private final CountryService service;

    @Autowired
    public CountryController(CountryService service){
        this.service = service;
    }

    /**
     * Получить все типы документов
     *
     * @return
     */
    @GetMapping(value = "/countries")
    public List<CountryView> getCountries(){
        return service.getCountries();
    }
}
