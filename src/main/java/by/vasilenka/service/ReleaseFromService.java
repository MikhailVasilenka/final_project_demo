package by.vasilenka.service;

import by.vasilenka.domain.ReleaseForm;
import by.vasilenka.repository.Repository;
import by.vasilenka.repository.RepositoryFactory;
import by.vasilenka.repository.exception.RepositoryException;
import by.vasilenka.repository.impl.JdbcRepositoryFactory;
import by.vasilenka.repository.impl.ReleaseFormRepository;
import by.vasilenka.repository.specification.Specification;
import by.vasilenka.repository.specification.release_form.GetAllReleaseForms;
import by.vasilenka.repository.specification.release_form.GetByDescription;
import by.vasilenka.service.exception.ServiceException;

import java.util.List;

public class ReleaseFromService {
    private RepositoryFactory repositoryFactory = JdbcRepositoryFactory.getInstance();
    private Repository<ReleaseForm, Integer> releaseFormRepository = repositoryFactory.getRepository(ReleaseFormRepository::new);

    public ReleaseForm getByDescription(String description) throws ServiceException {
        Specification<ReleaseForm> specification = new GetByDescription();
        try {
            ReleaseForm releaseForm = new ReleaseForm();
            releaseForm.setDescription(description);
            List<ReleaseForm> releaseFormList = releaseFormRepository.getQuery(releaseForm, specification);
            if (releaseFormList.isEmpty()) {
                return null;
            }
            return releaseFormList.get(0);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public List<ReleaseForm> getAllReleaseForms() throws ServiceException {
        Specification<ReleaseForm> specification = new GetAllReleaseForms();
        try {
            return releaseFormRepository.getQuery(new ReleaseForm(), specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}

