package by.vasilenka.service;

import by.vasilenka.domain.Manufacturer;
import by.vasilenka.repository.Repository;
import by.vasilenka.repository.RepositoryFactory;
import by.vasilenka.repository.exception.RepositoryException;
import by.vasilenka.repository.impl.JdbcRepositoryFactory;
import by.vasilenka.repository.impl.ManufacturerRepository;
import by.vasilenka.repository.specification.Specification;
import by.vasilenka.repository.specification.manufacturer.GetAllManufacturers;
import by.vasilenka.repository.specification.manufacturer.GetByManufacturerName;
import by.vasilenka.service.exception.ServiceException;

import java.util.List;

public class ManufacturerService {
    private RepositoryFactory repositoryFactory = JdbcRepositoryFactory.getInstance();
    private Repository<Manufacturer, Integer> manufacturerRepository = repositoryFactory.getRepository(ManufacturerRepository::new);

    public Manufacturer getByName(String name) throws ServiceException {
        Specification<Manufacturer> specification = new GetByManufacturerName();
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(name);
        try {
            List<Manufacturer> manufacturerList = manufacturerRepository.getQuery(manufacturer, specification);
            if (manufacturerList.isEmpty()) {
                return null;
            }
            return manufacturerList.get(0);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public List<Manufacturer> getAllManufacturers() throws ServiceException {
        Specification<Manufacturer> specification = new GetAllManufacturers();
        try {
            return manufacturerRepository.getQuery(new Manufacturer(), specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

}
