package ru.bellintegrator.practice.dao.office;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.model.Office;

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
 * DAOImpl для работы с Office
 */

@Repository
public class OfficeDaoImpl implements OfficeDao {
    Logger log = LoggerFactory.getLogger(OfficeDaoImpl.class);

    private final EntityManager em;

    @Autowired
    public OfficeDaoImpl(EntityManager em){
        this.em = em;
    }

    /**
     * Отфильтровать офисы по заданным параметрам
     *
     * @param orgId - уникальный идентификатор организации
     * @param name - имя
     * @param phone - телефон
     * @param isActive - активность
     * @return возвращает список из id, name, isActive(true)
     */
    @Override
    public List<Office> filter(Long orgId, String name, String phone, Boolean isActive){
        if (orgId == null){
            throw new IllegalArgumentException("orgId can not be null");
        }

        if (isActive == null){
            isActive = true;
        }

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteria = builder.createQuery(Office.class);
        Root<Office> officeRoot = criteria.from(Office.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(
                builder.equal(officeRoot.get("orgId"), orgId)
        );
        predicates.add(
                builder.equal(officeRoot.get("isActive"), isActive)
        );

        if (!Strings.isNullOrEmpty(name)){
            predicates.add(
                    builder.equal(officeRoot.get("name"), name)
            );
        }

        if (!Strings.isNullOrEmpty(phone)){
            predicates.add(
                    builder.equal(officeRoot.get("phone"), phone)
            );
        }

        criteria.multiselect(officeRoot.get("id"), officeRoot.get("name"), officeRoot.get("isActive"));
        criteria.where(predicates.toArray(new Predicate[predicates.size()]));

        TypedQuery<Office> query = em.createQuery(criteria);
        List<Office> result = query.getResultList();
        if (result.isEmpty()){
            throw new NoSuchElementException("Offices not found or invalid data entered");
        }

        return result;
    }

    /**
     * Получить данные офиса по id
     *
     * @param id - уникальный идентификатор
     * @return
     */
    @Override
    public Office getById(Long id){
        if (id == null){
            throw new IllegalArgumentException("id can not be null");
        }

        Office office = em.find(Office.class, id);
        if (office == null){
            throw new NoSuchElementException("id incorrect, no such record exists");
        }

        return office;
    }

    /**
     * Обновить данные офиса
     *
     * @param office
     */
    @Override
    public void update(Office office){
        if (Strings.isNullOrEmpty(office.getName()) || Strings.isNullOrEmpty(office.getAddress())){
            throw new IllegalArgumentException("name, address can not be null");
        }

        Office updateOffice = getById(office.getId());

        log.info("dao - office before update: " + updateOffice.toString());

        updateOffice.setName(office.getName());
        updateOffice.setAddress(office.getAddress());

        if (!Strings.isNullOrEmpty(office.getPhone())) {
            updateOffice.setPhone(office.getPhone());
        }

        if (office.getIsActive() != null) {
            updateOffice.setIsActive(office.getIsActive());
        }

        log.info("dao - office after update: " + updateOffice.toString());

        em.merge(updateOffice);
    }

    /**
     * Сохранить данные офиса
     *
     * @param office
     */
    @Override
    public void save(Office office){
        if (office.getOrgId() == null){
            throw new IllegalArgumentException("orgId can not be null");
        }

        em.persist(office);
    }
}
