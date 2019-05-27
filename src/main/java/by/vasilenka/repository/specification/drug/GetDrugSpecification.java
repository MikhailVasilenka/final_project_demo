package by.vasilenka.repository.specification.drug;

import by.vasilenka.domain.Drug;
import by.vasilenka.domain.Manufacturer;
import by.vasilenka.domain.ReleaseForm;
import by.vasilenka.repository.specification.Specification;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface GetDrugSpecification extends Specification<Drug> {
    default List<Drug> parseResultSet(ResultSet resultSet) throws SQLException {
        List<Drug> drugList = new ArrayList<>();
        while (resultSet.next()) {
            Drug resultDrug = new Drug();
            resultDrug.setId(resultSet.getInt(1));
            resultDrug.setName(resultSet.getString(2));
            resultDrug.setPrescriptionRequired(resultSet.getBoolean(3));
            resultDrug.setPrice(resultSet.getInt(4));
            int manufacturerId = resultSet.getInt(5);
            String manufacturerName = resultSet.getString(6);
            int releaseFormId = resultSet.getInt(7);
            String releaseFormDescription = resultSet.getString(8);
            resultDrug.setAvailableAmount(resultSet.getInt(9));
            resultDrug.setManufacturer(new Manufacturer(manufacturerId,manufacturerName));
            resultDrug.setReleaseForm(new ReleaseForm(releaseFormId,releaseFormDescription));
            drugList.add(resultDrug);
        }
        return drugList;
    }
}
