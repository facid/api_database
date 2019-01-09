package ru.bellintegrator.practice.service.employee;

import org.springframework.validation.annotation.Validated;
import ru.bellintegrator.practice.view.EmployeeView;

import java.util.List;

/**
 * Service для работы с EmployeeView
 */
@Validated
public interface EmployeeService {
    /**
     * Отфильтровать работников по заданным параметрам
     *
     * @param view
     * @return возвращает список из id, firstName, secondName, lastName, position, isIdentified(true)
     */
    List<EmployeeView> filter(EmployeeView view);

    /**
     * Получить данные работника по id
     *
     * @param id - уникальный идентификатор
     * @return
     */
    EmployeeView getById(Long id);

    /**
     * Обновить данные работника
     *
     * @param view
     */
    void update(EmployeeView view);

    /**
     * Сохранить данные работника
     *
     * @param view
     */
    void save(EmployeeView view);
}
