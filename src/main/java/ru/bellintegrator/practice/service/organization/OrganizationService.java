package ru.bellintegrator.practice.service.organization;

import org.springframework.validation.annotation.Validated;
import ru.bellintegrator.practice.view.OrganizationView;

import java.util.List;

/**
 * Service для работы с OrganizationView
 */

@Validated
public interface OrganizationService {

    /**
     * Отфильтровать организации по заданным параметрам
     *
     * @param name
     * @return возвращает список из id, name, isActive(true)
     */
    List<OrganizationView> filter(String name, String inn, Boolean isActive);

    /**
     * Получить данные организации по id
     *
     * @param id - уникальный идентификатор
     * @return
     */
    OrganizationView getById(Long id);

    /**
     * Обновить данные организации
     *
     * @param view
     */
    void update(OrganizationView view);

    /**
     * Сохранить организацию
     *
     * @param view
     */
    void save(OrganizationView view);

}
