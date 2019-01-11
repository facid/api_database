package ru.bellintegrator.practice.employee.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.bellintegrator.practice.dao.employee.EmployeeDao;
import ru.bellintegrator.practice.model.Country;
import ru.bellintegrator.practice.model.DocumentData;
import ru.bellintegrator.practice.model.DocumentType;
import ru.bellintegrator.practice.model.Employee;
import ru.bellintegrator.practice.model.mapper.MapperFacade;
import ru.bellintegrator.practice.service.employee.EmployeeService;
import ru.bellintegrator.practice.service.employee.EmployeeServiceImpl;
import ru.bellintegrator.practice.view.EmployeeView;

import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceTest {

    @Mock
    private EmployeeDao dao;
    @Mock private MapperFacade mapperFacade;

    private EmployeeService service;

    @Before
    public void setup(){
        service = new EmployeeServiceImpl(dao, mapperFacade);
    }

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFilter(){
        Long id = 4L;
        Long officeId = 3L;
        String firstName = "Алина";
        String middleName = "";
        String secondName = "Александрова";
        String position = "Менеджер";
        String phone = "89051012021";
        Boolean isIdentified = true;
        String docCode = null;
        String citizenshipCode = null;

        List<Employee> expected = new ArrayList<>();
        Employee employee = new Employee(id, firstName, middleName, secondName, position, isIdentified);
        expected.add(employee);

        Employee employeeTest = new Employee(officeId, firstName, middleName, secondName, position, phone, isIdentified);
        List<Employee> actual = dao.filter(employee, docCode, citizenshipCode);
        actual.add(employeeTest);

        List<EmployeeView> expectedView = mapperFacade.mapAsList(expected, EmployeeView.class);
        List<EmployeeView> actualView = mapperFacade.mapAsList(actual, EmployeeView.class);

        Assert.assertEquals(expectedView, actualView);
    }

    @Test
    public void getByIdTest(){
        Long id = 4L;
        String firstName = "Алина";
        String middleName = "";
        String secondName = "Александрова";
        String position = "Менеджер";
        String phone = "89051012021";
        Boolean isIdentified = true;

        Employee expected = new Employee(id, firstName, middleName, secondName, position, phone, isIdentified);
        Employee actual = dao.getById(id);

        EmployeeView expectedView = mapperFacade.map(expected, EmployeeView.class);
        EmployeeView actualView = mapperFacade.map(actual, EmployeeView.class);

        Assert.assertEquals(expectedView, actualView);
    }

    @Test
    public void updateTest(){
        EmployeeView view = new EmployeeView();
        view.id = 4L;
        view.firstName = "Алина";
        view.middleName = null;
        view.secondName = "Александрова";
        view.position = "HR";
        view.phone = "89112358788";
        view.isIdentified = true;

        String docName = null;
        String citizenshipCode = null;


        Employee employee = new Employee(view.id, view.firstName, view.middleName, view.secondName,
                view.position, view.phone, view.isIdentified);

        DocumentData docData = new DocumentData();


        dao.update(employee, docData, docName, citizenshipCode);
    }

    @Test
    public void saveTest(){
        EmployeeView view = new EmployeeView();
        view.id = 4L;
        view.firstName = "Виктория";
        view.middleName = null;
        view.secondName = null;
        view.position = "Менеджер";
        view.phone = null;
        view.isIdentified = true;

        Employee employee = new Employee(view.id, view.firstName, view.middleName, view.secondName,
                view.position, view.phone, view.isIdentified);
        DocumentData docData = new DocumentData();
        DocumentType docType = new DocumentType();
        Country country = new Country();

        dao.save(employee, docData, docType, country);
    }
}
