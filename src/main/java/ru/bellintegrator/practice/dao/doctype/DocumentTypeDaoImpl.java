package ru.bellintegrator.practice.dao.doctype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.model.DocumentType;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * DaoImpl для работы с документами
 */

@Repository
public class DocumentTypeDaoImpl implements DocumentTypeDao {
    private final EntityManager em;

    @Autowired
    public DocumentTypeDaoImpl(EntityManager em){
        this.em = em;
    }

    /**
     * Получить все типы документов
     *
     * @return
     */
    @Override
    public List<DocumentType> getDocTypes(){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<DocumentType> criteria = builder.createQuery(DocumentType.class);
        Root<DocumentType> root = criteria.from(DocumentType.class);

        criteria.multiselect(root.get("docName"), root.get("docCode"));

        TypedQuery<DocumentType> query = em.createQuery(criteria);
        return query.getResultList();
    }
}
