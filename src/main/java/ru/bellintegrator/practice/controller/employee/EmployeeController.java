package ru.bellintegrator.practice.controller.employee;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.service.employee.EmployeeService;
import ru.bellintegrator.practice.view.EmployeeView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Контроллер для управления информацией о работниках
 */

@RestController
@RequestMapping(value = "api/user", produces = APPLICATION_JSON_VALUE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    /**
     * Отфильтровать работников по заданным параметрам
     *
     * @param employee
     * @return
     */
    @PostMapping("/list")
    public List<EmployeeView> filter(@RequestBody EmployeeView employee){
        return employeeService.filter(employee);
    }

    /**
     * Получить данные работника по id
     *
     * @param id - уникальный идентификатор
     * @return
     */
    @GetMapping("/{id}")
    public EmployeeView getById(@PathVariable("id") Long id){
        return employeeService.getById(id);
    }

    /**
     * Обновить данные работника
     *
     * @param employee
     */
    @PostMapping("/update")
    public void update(@RequestBody EmployeeView employee){
        employeeService.update(employee);
    }

    /**
     * Обновить данные работника
     *
     * @param employee
     */
    @PostMapping("/save")
    public void save(@RequestBody EmployeeView employee){
        employeeService.save(employee);
    }
}
