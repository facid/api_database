package ru.bellintegrator.practice.dao.employee;

import ru.bellintegrator.practice.model.Country;
import ru.bellintegrator.practice.model.DocumentData;
import ru.bellintegrator.practice.model.DocumentType;
import ru.bellintegrator.practice.model.Employee;

import java.util.List;

/**
 * Dao для работы с Employee
 */

public interface EmployeeDao {

    /**
     * Отфильтровать работников по заданным параметрам
     *
     * @param employee
     * @return возвращает список из id, firstName, secondName, lastName, position, isIdentified(true)
     */
    List<Employee> filter(Employee employee, String docCode, String citizenshipCode);

    /**
     * Получить данные работника по id
     *
     * @param id - уникальный идентификатор
     * @return
     */
    Employee getById(Long id);

    /**
     * Обновить данные работника
     *
     * @param employee - объект Employee
     * @param docData - объект DocumentData
     * @param docName - название документа
     * @param citizenshipCode - код страны
     */
    void update(Employee employee, DocumentData docData, String docName, String citizenshipCode);

    /**
     * Сохранить данные работника
     *
     * @param employee
     * @param docData
     * @param docType
     * @param country
     */
    void save(Employee employee, DocumentData docData, DocumentType docType, Country country);
}
