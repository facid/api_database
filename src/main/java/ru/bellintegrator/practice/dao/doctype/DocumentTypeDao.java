package ru.bellintegrator.practice.dao.doctype;

import ru.bellintegrator.practice.model.DocumentType;

import java.util.List;

/**
 * Dao для работы с DocumentType
 */

public interface DocumentTypeDao {

    /**
     * Получить все типы документов
     *
     * @return
     */
    List<DocumentType> getDocTypes();
}
