package ru.bellintegrator.practice.organization.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.bellintegrator.practice.dao.organization.OrganizationDao;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.model.mapper.MapperFacade;
import ru.bellintegrator.practice.service.organization.OrganizationService;
import ru.bellintegrator.practice.service.organization.OrganizationServiceImpl;
import ru.bellintegrator.practice.view.OrganizationView;

import java.util.ArrayList;
import java.util.List;

public class OrganizationServiceTest {

    @Mock private OrganizationDao dao;
    @Mock private MapperFacade mapperFacade;

    private OrganizationService service;

    @Before
    public void setup(){
        service = new OrganizationServiceImpl(dao, mapperFacade);
    }

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFilter(){
        String name = "Сбербанк";
        String inn = null;
        Boolean isActive = true;

        Long id = 2L;

        List<Organization> expected = new ArrayList<>();
        Organization organization = new Organization(id, name, isActive);
        expected.add(organization);

        List<Organization> actual = dao.filter(name, inn, isActive);

        List<OrganizationView> viewExpected = mapperFacade.mapAsList(expected, OrganizationView.class);
        List<OrganizationView> viewActual = mapperFacade.mapAsList(actual, OrganizationView.class);

        Assert.assertEquals(viewExpected, viewActual);
    }

    @Test
    public void testGetById(){
        Long id = 2L;
        String name = "Сбербанк";
        String fullName = "ПАО Сбербанк";
        String address = "ул. Кутузовская, д.4";
        String phone = null;
        String inn = "1234567891";
        String kpp = "123456788";
        Boolean isActive = true;

        Organization expected = new Organization(id, name, fullName, address, phone, inn, kpp, isActive);
        Organization actual = dao.getById(id);

        OrganizationView viewExpected = mapperFacade.map(expected, OrganizationView.class);
        OrganizationView viewActual = mapperFacade.map(actual, OrganizationView.class);

        Assert.assertEquals(viewExpected, viewActual);
    }

    @Test
    public void testUpdate(){
        OrganizationView view = new OrganizationView();

        view.id = 2L;
        view.name = "Сбертех";
        view.fullName = "Сбербанк Технологии";
        view.address = "ул. Грефа, д.8";
        view.phone = "88005553535";
        view.inn = "1234567198";
        view.kpp = "123456887";
        view.isActive = true;

        Organization organization = new Organization(
                view.id, view.name, view.fullName,
                view.address, view.phone, view.inn,
                view.kpp, view.isActive
        );

        dao.update(organization);
    }

    @Test
    public void testSave(){
        OrganizationView view = new OrganizationView();

        view.name = "Новые технологии";
        view.fullName = "ЗАО Новые технологии";
        view.address = "ул. Новых технологий, 33, стр.2";
        view.phone = null;
        view.inn = "1987654321";
        view.kpp = "546231789";
        view.isActive = true;

        Organization organization = new Organization(
                view.name, view.fullName, view.address,
                view.phone, view.inn, view.kpp, view.isActive
        );

        dao.save(organization);
    }
}
