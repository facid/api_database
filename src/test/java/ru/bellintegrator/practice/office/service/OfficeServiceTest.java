package ru.bellintegrator.practice.office.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.bellintegrator.practice.dao.office.OfficeDao;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.model.mapper.MapperFacade;
import ru.bellintegrator.practice.service.office.OfficeService;
import ru.bellintegrator.practice.service.office.OfficeServiceImpl;
import ru.bellintegrator.practice.view.OfficeView;

import java.util.ArrayList;
import java.util.List;

public class OfficeServiceTest {

    @Mock private OfficeDao dao;
    @Mock private MapperFacade mapperFacade;

    private OfficeService service;

    @Before
    public void setup(){
        service = new OfficeServiceImpl(dao, mapperFacade);
    }

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFilter(){
        Long orgId = 1L;
        String testName = "Деловой центр";
        String phone = null;
        Boolean isActive = true;

        Long id = 1L;
        String name = "Деловой центр";

        List<Office> expected = new ArrayList<>();
        Office office = new Office(id, name, isActive);
        expected.add(office);

        List<Office> actual = dao.filter(orgId, testName, phone, isActive);

        List<OfficeView> expectedView = mapperFacade.mapAsList(expected, OfficeView.class);
        List<OfficeView> actualView = mapperFacade.mapAsList(actual, OfficeView.class);

        Assert.assertEquals(expectedView, actualView);
    }

    @Test
    public void testGetById(){
        Long id = 1L;
        String name = "Деловой центр";
        String phone = null;
        String address = "Краснопресненская наб., 14, стр. 1";
        Boolean isActive = true;

        Office expected = new Office(id, name, phone, address, isActive);
        Office actual = dao.getById(id);

        OfficeView expectedView = mapperFacade.map(expected, OfficeView.class);
        OfficeView actualView = mapperFacade.map(actual, OfficeView.class);

        Assert.assertEquals(expectedView, actualView);
    }

    @Test
    public void testUpdate(){
        OfficeView view = new OfficeView();
        view.id = 1L;
        view.name = "Деловой центр";
        view.phone = null;
        view.address = "Краснопресненская наб., 14, стр. 1";
        view.isActive = true;

        Office office = new Office(view.id, view.name, view.phone, view.address, view.isActive);

        dao.update(office);
    }

    @Test
    public void testSave(){
        OfficeView view = new OfficeView();
        view.name = "Москва-сити";
        view.phone = "849533311";
        view.address = "Краснопресненская наб., 14, стр. 1";
        view.isActive = true;

        Office office = new Office(view.name, view.phone, view.address, view.isActive);

        dao.save(office);
    }
}
