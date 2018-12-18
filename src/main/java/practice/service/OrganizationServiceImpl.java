package practice.service;

import ma.glasnost.orika.MapperFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import practice.dao.organization.OrganizationDao;
import practice.dao.organization.OrganizationDaoImpl;
import practice.model.Organization;
import practice.view.OrganizationView;

import java.util.List;

/**
 * ServiceImpl для работы с OrganizationView
 */
@Service
public class OrganizationServiceImpl implements OrganizationService{
    private final Logger logger = LoggerFactory.getLogger(OrganizationDaoImpl.class);

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
     * @param name - имя
     * @param inn - ИНН
     * @param isActive - активность
     * @return возвращает список из id, name, isActive(true) организации
     */
    @Override
    @Transactional
    public List<OrganizationView> filter(String name, String inn, Boolean isActive){
        logger.info("service - before dao.filter()");

        List<Organization> org = dao.filter(name, inn, isActive);

        logger.info("service - after dao.filter(): " + org.toString());

        return mapperFacade.mapAsList(org, OrganizationView.class);
    }

    /**
     * Получить данные организации по id
     *
     * @param id - уникальный идентификатор организации
     * @return возвращает данные организации
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
     * @param view - объект OrganizationView
     */
    @Override
    @Transactional
    public void update(OrganizationView view){
        Organization organization = new Organization(
                view.id, view.name, view.fullName, view.inn, view.kpp,
                view.address, view.phone, view.isActive);

        logger.info("service - before dao.update(): " + organization.toString());

        dao.update(organization);

        logger.info("service - after dao.update(): " + organization.toString());
    }

    /**
     * Сохранить данные организации
     *
     * @param view - объект OrganizationView
     */
    @Override
    @Transactional
    public void save(OrganizationView view){
        Organization organization = new Organization(
                view.name, view.fullName, view.inn, view.kpp,
                view.address, view.phone, view.isActive);

        logger.info("service - before dao.save()");

        dao.save(organization);

        logger.info("service - after dao.save(): " + organization.toString());
    }
}
