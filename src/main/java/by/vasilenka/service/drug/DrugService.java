package by.vasilenka.service.drug;

import by.vasilenka.domain.Manufacturer;
import by.vasilenka.domain.ReleaseForm;
import by.vasilenka.repository.Repository;
import by.vasilenka.repository.RepositoryFactory;
import by.vasilenka.repository.impl.DrugRepository;
import by.vasilenka.repository.impl.JdbcRepositoryFactory;
import by.vasilenka.domain.Drug;
import by.vasilenka.repository.impl.ManufacturerRepository;
import by.vasilenka.repository.impl.ReleaseFormRepository;

public abstract class DrugService {
    protected RepositoryFactory repositoryFactory = JdbcRepositoryFactory.getInstance();
    protected Repository<Drug, Integer> drugRepository = repositoryFactory.getRepository(DrugRepository::new);
    protected Repository<Drug, Integer> drugTransactionalRepository = repositoryFactory.getTransactionalRepository(DrugRepository::new);
    protected Repository<Manufacturer, Integer> manufacturerTransactionalRepository = repositoryFactory.getTransactionalRepository(ManufacturerRepository::new);
    protected Repository<ReleaseForm, Integer> releaseFormTransactionalRepository = repositoryFactory.getTransactionalRepository(ReleaseFormRepository::new);

    public DrugService() {
    }
}
