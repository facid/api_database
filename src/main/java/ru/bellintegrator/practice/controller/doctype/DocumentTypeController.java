package ru.bellintegrator.practice.controller.doctype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.service.doctype.DocumentTypeService;
import ru.bellintegrator.practice.view.DocumentTypeView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Контроллер для управления информацией о типах документа
 */

@RestController
@RequestMapping(value = "api", produces = APPLICATION_JSON_VALUE)
public class DocumentTypeController {
    private final DocumentTypeService service;

    @Autowired
    public DocumentTypeController(DocumentTypeService service){
        this.service = service;
    }

    /**
     * Получить все типы документов
     *
     * @return
     */
    @GetMapping(value = "/docs")
    public List<DocumentTypeView> getDocTypes(){
        return service.getDocTypes();
    }
}
