package by.vasilenka.repository.specification.prescription;

import by.vasilenka.domain.Prescription;
import by.vasilenka.repository.specification.Specification;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface GetPrescriptionSpecification extends Specification<Prescription> {
    default List<Prescription> parseResultSet(ResultSet resultSet) throws SQLException {
        List<Prescription> prescriptionList = new ArrayList<>();
        while (resultSet.next()) {
            Prescription resultPrescription = new Prescription();
            resultPrescription.setId(resultSet.getInt(1));
            resultPrescription.setDescription(resultSet.getString(2));
            resultPrescription.setIssueDate(resultSet.getDate(3));
            resultPrescription.setValidityDate(resultSet.getDate(4));
            resultPrescription.setDrugId(resultSet.getInt(5));
            resultPrescription.setDoctorId(resultSet.getInt(6));
            resultPrescription.setUserId(resultSet.getInt(7));
            prescriptionList.add(resultPrescription);
        }
        return prescriptionList;
    }

}
