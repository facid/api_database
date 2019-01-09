package ru.bellintegrator.practice.dao.employee;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.model.Country;
import ru.bellintegrator.practice.model.DocumentData;
import ru.bellintegrator.practice.model.DocumentType;
import ru.bellintegrator.practice.model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * DaoImpl для работы с Employee
 */

@Repository
public class EmployeeDaoImpl implements EmployeeDao{
    Logger log = LoggerFactory.getLogger(EmployeeDaoImpl.class);

    private final EntityManager em;

    @Autowired
    public EmployeeDaoImpl(EntityManager em){
        this.em = em;
    }

    /**
     * Отфильтровать работников по заданным параметрам
     *
     * @param employee
     * @return возвращает список из id, firstName, secondName, lastName, position, isIdentified(true)
     */
    @Override
    public List<Employee> filter(Employee employee, String docCode, String citizenshipCode){
        if (employee.getOfficeId() == null){
            throw new IllegalArgumentException("officeId can not be null");
        }

        if (employee.getIsIdentified() == null){
            employee.setIsIdentified(true);
        }

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = builder.createQuery(Employee.class);

        Root<Employee> employeeRoot = criteria.from(Employee.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(
                builder.equal(employeeRoot.get("officeId"), employee.getOfficeId())
        );
        predicates.add(
                builder.equal(employeeRoot.get("isIdentified"), employee.getIsIdentified())
        );

        if (employee.getFirstName() != null){
            predicates.add(
                    builder.equal(employeeRoot.get("firstName"), employee.getFirstName())
            );
        }

        if (employee.getSecondName() != null){
            predicates.add(
                    builder.equal(employeeRoot.get("secondName"), employee.getSecondName())
            );
        }

        if (employee.getMiddleName() != null){
            predicates.add(
                    builder.equal(employeeRoot.get("middleName"), employee.getMiddleName())
            );
        }

        if (employee.getPosition() != null){
            predicates.add(
                    builder.equal(employeeRoot.get("position"), employee.getPosition())
            );
        }

        if (docCode != null){
            Root<DocumentType> docTypeRoot = criteria.from(DocumentType.class);
            Root<DocumentData> docDataRoot = criteria.from(DocumentData.class);

            Predicate code = builder.equal(docTypeRoot.get("docCode"), docCode);
            Predicate docTypeId = builder.equal(docTypeRoot.get("id"), docDataRoot.get("docTypeId"));
            Predicate docDataId = builder.equal(docDataRoot.get("id"), employeeRoot.get("docDataId"));

            predicates.add(builder.and(code, docTypeId, docDataId));
        }

        if (citizenshipCode != null){
            Root<Country> countryRoot = criteria.from(Country.class);

            Predicate code = builder.equal(countryRoot.get("citizenshipCode"), citizenshipCode);
            Predicate id = builder.equal(countryRoot.get("id"), employeeRoot.get("countryId"));

            predicates.add(builder.and(code, id));
        }

        criteria.multiselect(
                employeeRoot.get("id"), employeeRoot.get("firstName"),
                employeeRoot.get("secondName"), employeeRoot.get("middleName"),
                employeeRoot.get("position"), employeeRoot.get("isIdentified")
        );
        criteria.where(predicates.toArray(new Predicate[predicates.size()]));

        TypedQuery<Employee> query = em.createQuery(criteria);
        List<Employee> result = query.getResultList();
        if (result.isEmpty()){
            throw new NoSuchElementException("Employees not found or invalid data entered");
        }

        return result;
    }

    /**
     * Получить данные работника по id
     *
     * @param id - уникальный идентификатор
     * @return
     */
    public Employee getById(Long id){
        if (id == null){
            throw new IllegalArgumentException("id can not be null");
        }

        Employee employee = em.find(Employee.class, id);
        if (employee == null){
            throw new NoSuchElementException("id incorrect, no such record exists");
        }

        return employee;
    }

    /**
     * Обновить данные работника
     *
     * @param employee - объект Employee
     * @param docData - объект DocumentData
     * @param docName - название документа
     * @param citizenshipCode - код страны
     */
    @Override
    public void update(Employee employee, DocumentData docData, String docName, String citizenshipCode){
        if (Strings.isNullOrEmpty(employee.getFirstName()) || Strings.isNullOrEmpty(employee.getPosition())){
            throw new IllegalArgumentException("firstName and position can not be null");
        }

        Employee updateEmployee = getById(employee.getId());

        log.info("dao - employee before update: " + updateEmployee.toString());

        updateEmployee.setFirstName(employee.getFirstName());
        updateEmployee.setPosition(employee.getPosition());

        if (!Strings.isNullOrEmpty(employee.getSecondName())) {
            updateEmployee.setSecondName(employee.getSecondName());
        }

        if (!Strings.isNullOrEmpty(employee.getMiddleName())) {
            updateEmployee.setMiddleName(employee.getMiddleName());
        }

        if (!Strings.isNullOrEmpty(employee.getPhone())) {
            updateEmployee.setPhone(employee.getPhone());
        }

        if (employee.getIsIdentified() != null) {
            updateEmployee.setIsIdentified(employee.getIsIdentified());
        }

        if ((docData != null) || (!Strings.isNullOrEmpty(docName))) {
            DocumentData newDocData = em.find(DocumentData.class, updateEmployee.getDocDataId());
            log.info("dao - docData before update: " + newDocData.toString());

            if (!Strings.isNullOrEmpty(docData.getDocNumber())) {
                newDocData.setDocNumber(docData.getDocNumber());

                log.info("dao - docData after set (if entered docNumber): " + newDocData.toString());
            }

            if (docData.getDocDate() != null) {
                newDocData.setDocDate(docData.getDocDate());

                log.info("dao - docDate after set (if entered docDate): " + newDocData.toString());
            }

            if (!Strings.isNullOrEmpty(docName)) {
                DocumentType newDocType = em.find(DocumentType.class, newDocData.getDocTypeId());

                log.info("dao - docType before set: " + newDocType.toString());

                newDocType.setDocName(docName);
                newDocData.setDocType(newDocType);

                log.info("dao - after set newDocType: " + newDocType.toString() + "and newDocData: " + newDocData.toString());

                updateEmployee.setDocData(newDocData);
            }
        }

        if (!Strings.isNullOrEmpty(citizenshipCode)) {
            Country newCountry = em.find(Country.class, updateEmployee.getCountryId());

            log.info("dao - country before update: " + newCountry.toString());

            newCountry.setCitizenshipCode(citizenshipCode);

            log.info("dao - country after set (if entered citizenshipCode): " + newCountry.toString());

            updateEmployee.setCountry(newCountry);
        }

        log.info("dao - employee after update: " + updateEmployee.toString());

        em.merge(updateEmployee);
    }

    /**
     * Сохранить данные работника
     *
     * @param employee
     * @param docData
     * @param docType
     * @param country
     */
    @Override
    public void save(Employee employee, DocumentData docData, DocumentType docType, Country country){
        if ((employee.getOfficeId() == null) || Strings.isNullOrEmpty(employee.getFirstName()) || Strings.isNullOrEmpty(employee.getPosition())){
            throw new IllegalArgumentException("officeId, firstName, position can not be null");
        }

        if ((!Strings.isNullOrEmpty(docType.getDocName())) || (!Strings.isNullOrEmpty(docType.getDocCode()))){
            log.info("dao - docType before save employee: " + docType.toString());

            docData.setDocType(docType);
            employee.setDocData(docData);
        }

        if ((!Strings.isNullOrEmpty(docData.getDocNumber())) || (docData.getDocDate() != null)){
            employee.setDocData(docData);

            log.info("dao - docData after set (include docNumber or/and docDate): " + docData.toString());
        }

        if (!Strings.isNullOrEmpty(country.getCitizenshipCode())){
            employee.setCountry(country);

            log.info("dao - country after set: " + country.toString());
        }

        em.persist(employee);
    }
}
