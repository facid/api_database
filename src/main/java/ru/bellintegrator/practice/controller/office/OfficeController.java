package ru.bellintegrator.practice.controller.office;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.service.office.OfficeService;
import ru.bellintegrator.practice.view.OfficeView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Контроллер для управления информацией об офисах
 */

@RestController
@RequestMapping(value = "api/office", produces = APPLICATION_JSON_VALUE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfficeController {

    private final OfficeService officeService;

    @Autowired
    public OfficeController (OfficeService officeService){
        this.officeService = officeService;
    }

    /**
     * Отфильтровать офисы по заданным параметрам
     *
     * @param view - уникальный идентификатор организации
     * @return возвращает список из id, name, isActive(true)
     */
    @PostMapping("/list")
    public List<OfficeView> filter(@RequestBody OfficeView view){
        return officeService.filter(view.orgId, view.name, view.phone, view.isActive);
    }

    /**
     * Получить данные офиса по id
     *
     * @param id - уникальный идентификатор
     * @return
     */
    @GetMapping("/{id}")
    public OfficeView getById(@PathVariable("id") Long id){
        return officeService.getById(id);
    }

    /**
     * Обновить данные офиса
     *
     * @param office
     */
    @PostMapping("/update")
    public void update(OfficeView office){
        officeService.update(office);
    }

    /**
     * Сохранить данные офиса
     *
     * @param office
     */
    @PostMapping("/save")
    public void save(OfficeView office){
        officeService.save(office);
    }
}
