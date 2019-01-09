package ru.bellintegrator.practice.controller.office;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.service.office.OfficeService;
import ru.bellintegrator.practice.view.OfficeView;
import ru.bellintegrator.practice.view.ResponseView;

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
     * @param office - уникальный идентификатор организации
     * @return возвращает список из id, name, isActive(true)
     */
    @PostMapping("/list")
    public List<OfficeView> filter(@RequestBody OfficeView office){
        return officeService.filter(office.orgId, office.name, office.phone, office.isActive);
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
    public ResponseView update(@RequestBody OfficeView office){
        officeService.update(office);
        return new ResponseView();
    }

    /**
     * Сохранить данные офиса
     *
     * @param office
     */
    @PostMapping("/save")
    public ResponseView save(@RequestBody OfficeView office){
        officeService.save(office);
        return new ResponseView();
    }
}
