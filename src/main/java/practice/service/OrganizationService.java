package practice.service;

import org.springframework.validation.annotation.Validated;
import practice.view.OrganizationView;

import java.util.List;

/**
 * Service для работы с OrganizationView
 */
@Validated
public interface OrganizationService {

    /**
     * Отфильтровать организации по заданным параметрам
     *
     * @param name - имя
     * @param inn - ИНН
     * @param isActive - активность
     * @return
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
