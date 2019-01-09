package ru.bellintegrator.practice.dao.organization;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    Logger log = LoggerFactory.getLogger(OrganizationDaoImpl.class);

    private final EntityManager em;

    @Autowired
    public OrganizationDaoImpl(EntityManager em){
        this.em = em;
    }

    /**
     * Отфильтровать организации по заданным параметрам
     *
     * @param name - название
     * @param inn - ИНН
     * @param isActive - активность
     * @return возвращает список из id, name, isActive(true) организации
     */
    @Override
    public List<Organization> filter(String name, String inn, Boolean isActive){
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

        if (!Strings.isNullOrEmpty(inn)){
            criteria.where(
                    builder.equal(root.get("name"), name),
                    builder.equal(root.get("inn"), inn),
                    builder.equal(root.get("isActive"), isActive)
            );
        } else {
            criteria.where(
                    builder.equal(root.get("name"), name),
                    builder.equal(root.get("isActive"), isActive)
            );
        }

        TypedQuery<Organization> query = em.createQuery(criteria);
        List<Organization> result = query.getResultList();
        if (result.isEmpty()){
            throw new NoSuchElementException("Organization not found or invalid data entered");
        }

        return result;
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

        Organization organization = em.find(Organization.class, id);
        if (organization == null){
            throw new NoSuchElementException("id incorrect, no such record exists");
        }

        return organization;
    }

    /**
     * Обновить данные организации
     *
     * @param organization
     */
    @Override
    public void update(Organization organization){
        if (
                Strings.isNullOrEmpty(organization.getName()) ||
                Strings.isNullOrEmpty(organization.getFullName()) ||
                Strings.isNullOrEmpty(organization.getInn()) ||
                Strings.isNullOrEmpty(organization.getKpp()) ||
                Strings.isNullOrEmpty(organization.getAddress())
        ){
            throw new IllegalArgumentException("name, fullName, inn, kpp, address can not be null");
        }

        try {
            long testInn = Long.parseLong(organization.getInn());
            long testKpp = Long.parseLong(organization.getKpp());

        } catch (NumberFormatException e){
            log.warn("inn or kpp incorrect" + e);
            throw e;
        }

        Organization updateOrganization = getById(organization.getId());

        log.info("dao - organization before update: " + updateOrganization.toString());

        updateOrganization.setName(organization.getName());
        updateOrganization.setFullName(organization.getFullName());
        updateOrganization.setInn(organization.getInn());
        updateOrganization.setKpp(organization.getKpp());
        updateOrganization.setAddress(organization.getAddress());

        if (!Strings.isNullOrEmpty(organization.getPhone())) {
            updateOrganization.setPhone(organization.getPhone());
        }

        if (organization.getIsActive() != null) {
            updateOrganization.setIsActive(organization.getIsActive());
        }

        log.info("dao - organization after update: " + updateOrganization.toString());

        em.merge(updateOrganization);
    }

    /**
     * Сохранить данные организации
     *
     * @param organization
     */
    @Override
    public void save(Organization organization){
        if (
                Strings.isNullOrEmpty(organization.getName()) ||
                Strings.isNullOrEmpty(organization.getFullName()) ||
                Strings.isNullOrEmpty(organization.getInn()) ||
                Strings.isNullOrEmpty(organization.getKpp()) ||
                Strings.isNullOrEmpty(organization.getAddress())
        ){
            throw new IllegalArgumentException("name, fullName, inn, kpp, address can not be null");
        }

        try {
            long testInn = Long.parseLong(organization.getInn());
            long testKpp = Long.parseLong(organization.getKpp());

        } catch (NumberFormatException e){
            log.warn("inn or kpp incorrect" + e);
            throw e;
        }

        em.persist(organization);
    }
}
