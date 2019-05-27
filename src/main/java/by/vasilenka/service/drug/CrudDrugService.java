package by.vasilenka.service.drug;

import by.vasilenka.domain.Drug;
import by.vasilenka.repository.exception.RepositoryException;
import by.vasilenka.repository.specification.Specification;
import by.vasilenka.repository.specification.drug.GetAllDrugs;
import by.vasilenka.repository.specification.drug.GetDrugById;
import by.vasilenka.repository.specification.drug.GetDrugsByName;
import by.vasilenka.service.exception.ServiceException;

import java.util.List;

public class CrudDrugService extends DrugService {
    public void update(Drug drug) throws ServiceException {
        try {
             drugRepository.update(drug);
        } catch (RepositoryException e) {
            throw new ServiceException("Can't add drug", e);
        }
    }
    public Drug getById(int id) throws ServiceException {
        Drug drug = new Drug();
        drug.setId(id);
        try {
            List<Drug> drugList = drugRepository.getQuery(drug, new GetDrugById());
            if (drugList.isEmpty()) {
                return null;
            }
            return drugList.get(0);
        } catch (RepositoryException e) {
            throw new ServiceException("Cant't get drug ", e);
        }
    }
    public Drug add(Drug drug) throws ServiceException {
        try {
            return drugRepository.add(drug);
        } catch (RepositoryException e) {
            throw new ServiceException("Can't add drug", e);
        }

    }

    public void delete(int id) throws ServiceException {
        Drug drug = new Drug();
        drug.setId(id);
        try {
            drugRepository.delete(drug);
        } catch (RepositoryException e) {
            throw new ServiceException("Can't delete drug", e);
        }
    }

    public List<Drug> getAll() throws ServiceException {
        Specification<Drug> spec = new GetAllDrugs();
        try {
            return drugRepository.getQuery(new Drug(), spec);
        } catch (RepositoryException e) {
            throw new ServiceException("Can't get all drugs", e);
        }
    }

    public List<Drug> getByName(String name) throws ServiceException {
        Drug drug = new Drug();
        drug.setName(name);
        Specification<Drug> spec = new GetDrugsByName();
        try {
            return drugRepository.getQuery(drug, spec);
        } catch (RepositoryException e) {
            throw new ServiceException("Can't get drug", e);
        }
    }
}
