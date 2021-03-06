package ru.bellintegrator.practice.controller.organization;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.service.organization.OrganizationService;
import ru.bellintegrator.practice.view.OrganizationView;
import ru.bellintegrator.practice.view.ResponseView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Контроллер для управления информацией об организациях
 */

@RestController
@RequestMapping(value = "api/organization", produces = APPLICATION_JSON_VALUE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrganizationController {
    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController (OrganizationService organizationService){
        this.organizationService = organizationService;
    }

    /**
     * Отфильтровать организации по заданным параметрам
     *
     * @param view
     * @return возвращает список из id, name, isActive(true) организации
     */
    @PostMapping("/list")
    public List<OrganizationView> filter(@RequestBody OrganizationView view){
        return organizationService.filter(view.name, view.inn, view.isActive);
    }

    /**
     * Получить данные организации по id
     *
     * @param id - уникальный идентификатор
     * @return
     */
    @GetMapping("/{id}")
    public OrganizationView getById(@PathVariable("id") Long id){
        return organizationService.getById(id);
    }

    /**
     * Обновить данные организации
     *
     * @param organization
     */
    @PostMapping("/update")
    public ResponseView update(@RequestBody OrganizationView organization){
        organizationService.update(organization);
        return new ResponseView();
    }

    /**
     * Сохранить данные организации
     *
     * @param organization
     */
    @PostMapping("/save")
    public ResponseView save(@RequestBody OrganizationView organization){
        organizationService.save(organization);
        return new ResponseView();
    }
}
