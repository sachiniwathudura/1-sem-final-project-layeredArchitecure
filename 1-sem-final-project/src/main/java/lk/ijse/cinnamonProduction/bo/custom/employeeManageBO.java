package lk.ijse.cinnamonProduction.bo.custom;

import lk.ijse.cinnamonProduction.bo.SuperBO;
import lk.ijse.cinnamonProduction.dto.employeeManagementDTo;

import java.sql.SQLException;
import java.util.List;

public interface employeeManageBO extends SuperBO {
    List<employeeManagementDTo> getAllEmployee() throws SQLException, ClassNotFoundException;

    boolean saveEmployee(employeeManagementDTo dto) throws SQLException, ClassNotFoundException ;

    boolean updateEmployee(employeeManagementDTo dto) throws SQLException, ClassNotFoundException ;

    boolean existEmployee(String id) throws SQLException, ClassNotFoundException ;

     boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException ;

    String generateID() throws SQLException, ClassNotFoundException ;

    employeeManagementDTo searchEmployee(String id) throws SQLException, ClassNotFoundException;


}
