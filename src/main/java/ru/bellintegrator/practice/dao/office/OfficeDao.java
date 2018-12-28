package ru.bellintegrator.practice.dao.office;

import org.springframework.lang.Nullable;
import ru.bellintegrator.practice.model.Office;

import java.util.List;

/**
 * DAO для работы с Office
 */

public interface OfficeDao {

    /**
     * Отфильтровать офисы по заданным параметрам
     *
     * @param orgId - уникальный идентификатор организации
     * @param name - имя
     * @param phone - телефон
     * @param isActive - активность
     * @return возвращает список из id, name, isActive(true)
     */
    List<Office> filter(Long orgId, @Nullable String name, @Nullable String phone, @Nullable Boolean isActive);

    /**
     * Получить данные офиса по id
     *
     * @param id - уникальный идентификатор
     * @return
     */
    Office getById(Long id);

    /**
     * Обновить данные офиса
     *
     * @param office
     */
    void update(Office office);

    /**
     * Сохранить данные офиса
     *
     * @param office
     */
    void save(Office office);
}
