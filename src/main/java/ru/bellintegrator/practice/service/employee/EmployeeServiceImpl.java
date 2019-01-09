package ru.bellintegrator.practice.service.employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.employee.EmployeeDao;
import ru.bellintegrator.practice.model.Country;
import ru.bellintegrator.practice.model.DocumentData;
import ru.bellintegrator.practice.model.DocumentType;
import ru.bellintegrator.practice.model.Employee;
import ru.bellintegrator.practice.model.mapper.MapperFacade;
import ru.bellintegrator.practice.view.EmployeeView;

import java.util.List;

/**
 * ServiceImpl для работы с EmployeeView
 */

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeDao dao;
    private final MapperFacade mapperFacade;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao dao, MapperFacade mapperFacade){
        this.dao = dao;
        this.mapperFacade = mapperFacade;
    }

    /**
     * Отфильтровать работников по заданным параметрам
     *
     * @param view
     * @return возвращает список из id, firstName, secondName, lastName, position, isIdentified(true)
     */
    @Override
    @Transactional
    public List<EmployeeView> filter(EmployeeView view){
        Employee employee = mapperFacade.map(view, Employee.class);

        logger.info("service - before dao.filter()");

        List<Employee> employees = dao.filter(employee, view.docCode, view.citizenshipCode);

        logger.info("service - after dao.filter" + employees);
        return mapperFacade.mapAsList(employees, EmployeeView.class);
    }

    /**
     * Получить данные работника по id
     *
     * @param id - уникальный идентификатор
     * @return
     */
    @Override
    @Transactional
    public EmployeeView getById(Long id){
        logger.info("service - before dao.getById()");

        Employee employee = dao.getById(id);

        logger.info("service - after dao.getById(): " + employee.toString());
        return mapperFacade.map(employee, EmployeeView.class);
    }

    /**
     * Обновить данные работника
     *
     * @param view
     */
    @Override
    @Transactional
    public void update(EmployeeView view){
        Employee employee = mapperFacade.map(view, Employee.class);
        DocumentData docData = mapperFacade.map(view, DocumentData.class);

        logger.info("service - before dao.update()");

        dao.update(employee, docData, view.docName, view.citizenshipCode);

        logger.info("service - after dao.update()");
    }

    /**
     * Сохранить данные работника
     *
     * @param view
     */
    @Override
    @Transactional
    public void save(EmployeeView view){
        Employee employee = mapperFacade.map(view, Employee.class);
        DocumentData docData = mapperFacade.map(view, DocumentData.class);
        DocumentType docType = mapperFacade.map(view, DocumentType.class);
        Country country = mapperFacade.map(view, Country.class);

        logger.info("service - employee before dao.save(): " + employee.toString());

        dao.save(employee, docData, docType, country);

        logger.info("service - after dao.save()");
    }
}
