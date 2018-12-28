package ru.bellintegrator.practice.service.doctype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.dao.doctype.DocumentTypeDao;
import ru.bellintegrator.practice.model.DocumentType;
import ru.bellintegrator.practice.model.mapper.MapperFacade;
import ru.bellintegrator.practice.view.DocumentTypeView;

import java.util.List;

/**
 * Сервис для работы с DocumentTypeView
 */

@Service
public class DocumentTypeServiceImpl implements DocumentTypeService{
    private final DocumentTypeDao dao;
    private final MapperFacade mapperFacade;

    @Autowired
    DocumentTypeServiceImpl(DocumentTypeDao dao, MapperFacade mapperFacade){
        this.dao = dao;
        this.mapperFacade = mapperFacade;
    }

    /**
     * Получить все типы документов
     *
     * @return
     */
    @Override
    public List<DocumentTypeView> getDocTypes(){
        List<DocumentType> documentTypes = dao.getDocTypes();
        return mapperFacade.mapAsList(documentTypes, DocumentTypeView.class);
    }
}
