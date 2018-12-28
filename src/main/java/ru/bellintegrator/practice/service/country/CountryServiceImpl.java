package ru.bellintegrator.practice.service.country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.dao.country.CountryDao;
import ru.bellintegrator.practice.model.Country;
import ru.bellintegrator.practice.model.mapper.MapperFacade;
import ru.bellintegrator.practice.view.CountryView;

import java.util.List;

/**
 * Сервис для работы с CountryView
 */

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryDao dao;
    private final MapperFacade mapperFacade;

    @Autowired
    public CountryServiceImpl(CountryDao dao, MapperFacade mapperFacade){
        this.dao = dao;
        this.mapperFacade = mapperFacade;
    }

    /**
     * Получить все страны
     *
     * @return
     */
    @Override
    public List<CountryView> getCountries(){
        List<Country> countries = dao.getCountries();
        return mapperFacade.mapAsList(countries, CountryView.class);
    }
}
