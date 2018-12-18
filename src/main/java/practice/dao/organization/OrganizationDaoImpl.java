package practice.dao.organization;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import practice.model.Organization;

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
public class OrganizationDaoImpl implements OrganizationDao{
    private final EntityManager em;

    @Autowired
    public OrganizationDaoImpl(EntityManager em){
        this.em = em;
    }

    /**
     * Отфильтровать организации по заданным параметрам
     *
     * @param name - имя
     * @param inn - ИНН
     * @param isActive - активность
     * @return возвращает список из id, name, isActive(true) организации
     */
    @Override
    public List<Organization> filter(String name, String inn, Boolean isActive){

        if ((Strings.isNullOrEmpty(name)) || (Strings.isNullOrEmpty(inn)) || (isActive == null)){
            throw new IllegalArgumentException("name, inn, isActive can not be null");
        }

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteriaQuery = criteriaBuilder.createQuery(Organization.class);
        Root<Organization> orgRoot = criteriaQuery.from(Organization.class);

        criteriaQuery.multiselect(orgRoot.get("id"), orgRoot.get("name"), orgRoot.get("isActive"));
        criteriaQuery.where(
                criteriaBuilder.equal(orgRoot.get("name"), name),
                criteriaBuilder.equal(orgRoot.get("inn"), inn),
                criteriaBuilder.equal(orgRoot.get("isActive"), isActive)
        );

        TypedQuery<Organization> resultQuery = em.createQuery(criteriaQuery);
        return resultQuery.getResultList();
    }

    /**
     * Получить данные организации по id
     *
     * @param id - уникальный идентификатор организации
     * @return возвращает данные организации
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
     * @param organization - объект организации
     */
    @Override
    public void update(Organization organization){
        Long id = organization.getId();

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Organization> orgRoot = query.from(Organization.class);

        query.select(builder.count(orgRoot));
        query.where(builder.equal(orgRoot.get("id"), id));

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
