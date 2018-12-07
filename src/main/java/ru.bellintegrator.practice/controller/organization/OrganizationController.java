package ru.bellintegrator.practice.controller.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.service.OrganizationService;
import ru.bellintegrator.practice.view.OrganizationView;
import ru.bellintegrator.practice.view.ResponseView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Контроллер для управления информацией об организациях
 */

@RestController
@RequestMapping(value = "api/organization", produces = APPLICATION_JSON_VALUE)
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController (OrganizationService organizationService){
        this.organizationService = organizationService;
    }

    /**
     * Отфильтровать организации по заданным параметрам
     *
     * @param name - имя организации
     * @param inn - ИНН организации
     * @param isActive - активность организации
     * @return возвращает список из id, name, isActive(true) организации
     */
    @PostMapping("/list")
    public List<OrganizationView> filter(@RequestBody String name, String inn, boolean isActive){
        return organizationService.filter(name, inn, isActive);
    }

    /**
     * Получить данные организации по id
     *
     * @param id - уникальный идентификатор организации
     * @return возвращает список данных организации
     */
    @GetMapping("/{id}")
    public List<OrganizationView> getById(@PathVariable("id") long id){
        return organizationService.getById(id);
    }

    /**
     * Обновить данные организации
     *
     * @param organization - объект организации
     * @return возвращает "success", если обновление прошло успешно
     */
    @PostMapping("/update")
    public ResponseView update(@RequestBody OrganizationView organization){
        return organizationService.update(organization);
    }

    /**
     * Сохранить данные организации
     *
     * @param organization
     * @return возвращает "success", если сохранение прошло успешно
     */
    @PostMapping("/save")
    public ResponseView save(@RequestBody OrganizationView organization){
        return organizationService.save(organization);
    }
}
