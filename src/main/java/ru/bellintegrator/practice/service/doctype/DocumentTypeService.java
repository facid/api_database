package ru.bellintegrator.practice.service.doctype;

import org.springframework.validation.annotation.Validated;
import ru.bellintegrator.practice.view.DocumentTypeView;

import java.util.List;

/**
 * Сервис для работы с DocumentTypeView
 */

@Validated
public interface DocumentTypeService {

    /**
     * Получить все типы документов
     *
     * @return
     */
    List<DocumentTypeView> getDocTypes();
}
