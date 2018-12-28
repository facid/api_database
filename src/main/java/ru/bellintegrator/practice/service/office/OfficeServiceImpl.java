package ru.bellintegrator.practice.service.office;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.office.OfficeDao;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.model.mapper.MapperFacade;
import ru.bellintegrator.practice.view.OfficeView;

import java.util.List;

/**
 * ServiceImpl для работы с OfficeView
 */

@Service
public class OfficeServiceImpl implements OfficeService {
    private final Logger logger = LoggerFactory.getLogger(OfficeServiceImpl.class);

    private final OfficeDao dao;
    private final MapperFacade mapperFacade;

    @Autowired
    public OfficeServiceImpl (OfficeDao dao, MapperFacade mapperFacade){
        this.dao = dao;
        this.mapperFacade = mapperFacade;
    }

    /**
     * Отфильтровать офисы по заданным параметрам
     *
     * @param orgId - уникальный идентификатор организации
     * @param name - имя
     * @param phone - телефон
     * @param isActive - активность
     * @return возвращает список из id, name, isActive(true)
     */
    @Override
    @Transactional
    public List<OfficeView> filter(Long orgId, @Nullable String name, @Nullable String phone, @Nullable Boolean isActive){

        List<Office> offices = dao.filter(orgId, name, phone, isActive);

        return mapperFacade.mapAsList(offices, OfficeView.class);
    }

    /**
     * Получить данные офиса по id
     *
     * @param id - уникальный идентификатор
     * @return
     */
    @Override
    @Transactional
    public OfficeView getById(Long id){
        logger.info("service - before dao.getById()");

        Office office = dao.getById(id);

        logger.info("service - after dao.getById()" + office.toString());
        return mapperFacade.map(office, OfficeView.class);
    }

    /**
     * Обновить данные офиса
     *
     * @param view
     */
    @Override
    @Transactional
    public void update(OfficeView view){
        Office office = new Office(
                view.id, view.name, view.phone,
                view.address, view.isActive
        );

        logger.info("service - before dao.update()");

        dao.update(office);

        logger.info("service - after dao.update()" + office.toString());

    }

    /**
     * Сохранить данные офиса
     *
     * @param view
     */
    @Override
    @Transactional
    public void save(OfficeView view){
        Office office = new Office(view.name, view.address, view.phone, view.isActive);

        logger.info("service - before dao.save()");

        dao.save(office);

        logger.info("service - after dao.save()" + office.toString());
    }
}
