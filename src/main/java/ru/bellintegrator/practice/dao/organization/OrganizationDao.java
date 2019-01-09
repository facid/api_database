package ru.bellintegrator.practice.dao.organization;

import ru.bellintegrator.practice.model.Organization;

import java.util.List;

/**
 * DAO для работы с Organization
 */

public interface OrganizationDao {

    /**
     * Отфильтровать организации по заданным параметрам
     *
     * @param name - название
     * @param inn - ИНН
     * @param isActive - активность
     * @return возвращает список из id, name, isActive(true)
     */
    List<Organization> filter(String name, String inn, Boolean isActive);

    /**
     * Получить данные организации по id
     *
     * @param id - уникальный идентификатор
     * @return
     */
    Organization  getById(Long id);

    /**
     * Обновить данные организации
     *
     * @param organization
     */
    void update(Organization organization);

    /**
     * Сохранить организацию
     *
     * @param organization
     */
    void save(Organization organization);
}
