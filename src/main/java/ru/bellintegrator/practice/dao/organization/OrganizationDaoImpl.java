package ru.bellintegrator.practice.dao.organization;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * DAOImpl для работы с Organization
 */

@Repository
public class OrganizationDaoImpl implements OrganizationDao {
    private final EntityManager em;

    @Autowired
    public OrganizationDaoImpl(EntityManager em){
        this.em = em;
    }

    /**
     * Отфильтровать организации по заданным параметрам
     *
     * @param name
     * @return возвращает список из id, name, isActive(true) организации
     */
    @Override
    public List<Organization> filter(String name, @Nullable String inn, @Nullable Boolean isActive){
        if (Strings.isNullOrEmpty(name)){
            throw new IllegalArgumentException("name can not be null");
        }

        if (isActive == null){
            isActive = true;
        }

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteria = builder.createQuery(Organization.class);
        Root<Organization> root = criteria.from(Organization.class);

        criteria.multiselect(root.get("id"), root.get("name"), root.get("isActive"));
        criteria.where(
                builder.equal(root.get("name"), name),
                builder.equal(root.get("isActive"), isActive)
        );

        if (!Strings.isNullOrEmpty(inn)){
            criteria.where(
                    builder.equal(root.get("inn"), inn)
            );
        }

        TypedQuery<Organization> query = em.createQuery(criteria);

        return query.getResultList();
    }

    /**
     * Получить данные организации по id
     *
     * @param id - уникальный идентификатор
     * @return
     */
    @Override
    public Organization getById(Long id){
        if (id == null){
            throw new IllegalArgumentException("id can not be null");
        }
        return em.find(Organization.class,id);
    }

    /**
     * Обновить данные организации
     *
     * @param organization
     */
    @Override
    public void update(Organization organization){
        Long id = organization.getId();

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Organization> orgRoot = query.from(Organization.class);

        query.select(builder.count(orgRoot));
        query.where(
                builder.equal(orgRoot.get("id"), id)
        );

        Long count = em.createQuery(query).getSingleResult();

        if (count == 0){
            throw new NoSuchElementException("no such record exists");
        }

        em.merge(organization);
    }

    /**
     * Сохранить данные организации
     *
     * @param organization
     */
    @Override
    public void save(Organization organization){
        if (organization == null){
            throw new IllegalArgumentException("organization can not be null");
        }

        em.persist(organization);
    }
}
