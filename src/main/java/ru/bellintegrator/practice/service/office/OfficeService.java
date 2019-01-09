package ru.bellintegrator.practice.service.office;

import org.springframework.validation.annotation.Validated;
import ru.bellintegrator.practice.view.OfficeView;

import java.util.List;

/**
 * Service для работы с OfficeView
 */

@Validated
public interface OfficeService {

    /**
     * Отфильтровать офисы по заданным параметрам
     *
     * @param orgId - уникальный идентификатор организации
     * @param name - имя
     * @param phone - телефон
     * @param isActive - активность
     * @return возвращает список из id, name, isActive(true)
     */
    List<OfficeView> filter(Long orgId, String name, String phone, Boolean isActive);

    /**
     * Получить данные офиса по id
     *
     * @param id - уникальный идентификатор
     * @return
     */
    OfficeView getById(Long id);

    /**
     * Обновить данные офиса
     *
     * @param view
     */
    void update(OfficeView view);

    /**
     * Сохранить данные офиса
     *
     * @param view
     */
    void save(OfficeView view);
}
