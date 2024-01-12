package lk.ijse.cinnamonProduction.bo.custom;

import lk.ijse.cinnamonProduction.bo.SuperBO;
import lk.ijse.cinnamonProduction.dto.cinnamonGradesDto;
import lk.ijse.cinnamonProduction.dto.companyDto;

import java.sql.SQLException;
import java.util.List;

public interface companyBO extends SuperBO {
    List<companyDto> getAllCompany() throws SQLException, ClassNotFoundException;

    boolean saveCompany(companyDto dto) throws SQLException, ClassNotFoundException ;

    boolean updateCompany(companyDto dto) throws SQLException, ClassNotFoundException ;

    boolean existCompany(String id) throws SQLException, ClassNotFoundException ;

    boolean deleteCompany(String id) throws SQLException, ClassNotFoundException ;

    String generateID() throws SQLException, ClassNotFoundException ;

    companyDto searchCompany(String id) throws SQLException, ClassNotFoundException;

}
