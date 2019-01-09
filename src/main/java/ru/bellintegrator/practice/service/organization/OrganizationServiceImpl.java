package ru.bellintegrator.practice.service.organization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.organization.OrganizationDao;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.model.mapper.MapperFacade;
import ru.bellintegrator.practice.view.OrganizationView;

import java.util.List;

/**
 * ServiceImpl для работы с OrganizationView
 */

@Service
public class OrganizationServiceImpl implements OrganizationService {
    private final Logger logger = LoggerFactory.getLogger(OrganizationServiceImpl.class);

    private final OrganizationDao dao;
    private final MapperFacade mapperFacade;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao dao, MapperFacade mapperFacade){
        this.dao = dao;
        this.mapperFacade = mapperFacade;
    }

    /**
     * Отфильтровать организации по заданным параметрам
     *
     * @param name
     * @return возвращает список из id, name, isActive(true) организации
     */
    @Override
    @Transactional
    public List<OrganizationView> filter(String name, String inn, Boolean isActive){
        logger.info("service - before dao.filter()");

        List<Organization> organizations = dao.filter(name, inn, isActive);

        logger.info("service - after dao.filter(): " + organizations.toString());

        return mapperFacade.mapAsList(organizations, OrganizationView.class);
    }

    /**
     * Получить данные организации по id
     *
     * @param id - уникальный идентификатор организации
     * @return
     */
    @Override
    @Transactional
    public OrganizationView getById(Long id){
        logger.info("service - before dao.getById()");

        Organization organization = dao.getById(id);

        logger.info("service - after dao.getById(): " + organization.toString());
        return mapperFacade.map(organization, OrganizationView.class);
    }

    /**
     * Обновить данные организации
     *
     * @param view
     */
    @Override
    @Transactional
    public void update(OrganizationView view){
        Organization organization = mapperFacade.map(view, Organization.class);

        logger.info("service - before dao.update()");

        dao.update(organization);

        logger.info("service - after dao.update()");
    }

    /**
     * Сохранить данные организации
     *
     * @param view
     */
    @Override
    @Transactional
    public void save(OrganizationView view){
        Organization organization = mapperFacade.map(view, Organization.class);

        logger.info("service - before dao.save()" + organization.toString());

        dao.save(organization);

        logger.info("service - after dao.save():");
    }
}
