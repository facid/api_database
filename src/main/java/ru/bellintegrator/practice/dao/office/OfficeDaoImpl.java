package ru.bellintegrator.practice.dao.office;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * DAOImpl для работы с Office
 */

@Repository
public class OfficeDaoImpl implements OfficeDao {
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
    public List<Office> filter(Long orgId, @Nullable String name, @Nullable String phone, @Nullable Boolean isActive){

        if (orgId == null){
            throw new IllegalArgumentException("orgId can not be null");
        }

        if (isActive == null){
            isActive = true;
        }

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteria = builder.createQuery(Office.class);
        Root<Organization> organizationRoot = criteria.from(Organization.class);
        Root<Office> officeRoot = criteria.from(Office.class);

        Predicate join = builder.equal(officeRoot.get("orgId"), organizationRoot.get("id"));
        Predicate id = builder.equal(officeRoot.get("orgId"), orgId);

        criteria.multiselect(officeRoot.get("id"), officeRoot.get("name"), officeRoot.get("isActive"));
        criteria.where(
                builder.and(join, id),
                builder.equal(officeRoot.get("isActive"), isActive)
        );

        if (!Strings.isNullOrEmpty(name)){
            criteria.where(
                    builder.equal(officeRoot.get("name"), name)
            );
        }

        if (!Strings.isNullOrEmpty(phone)){
            criteria.where(
                    builder.equal(officeRoot.get("phone"), phone)
            );
        }

        if ((!Strings.isNullOrEmpty(name)) & (!Strings.isNullOrEmpty(phone))){
            criteria.where(
                    builder.equal(officeRoot.get("name"), name),
                    builder.equal(officeRoot.get("phone"), phone)
            );
        }

        TypedQuery<Office> query = em.createQuery(criteria);

        return query.getResultList();
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
        return em.find(Office.class,id);
    }

    /**
     * Обновить данные офиса
     *
     * @param office
     */
    @Override
    public void update(Office office){
        Long id = office.getId();

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Office> officeRoot = query.from(Office.class);

        query.select(builder.count(officeRoot));
        query.where(
                builder.equal(officeRoot.get("id"), id)
        );

        Long count = em.createQuery(query).getSingleResult();

        if (count == 0){
            throw new NoSuchElementException("no such record exists");
        }

        em.merge(office);
    }

    /**
     * Сохранить данные офиса
     *
     * @param office
     */
    @Override
    public void save(Office office){
        if (office == null){
            throw new IllegalArgumentException("organization can not be null");
        }

        em.persist(office);
    }
}
