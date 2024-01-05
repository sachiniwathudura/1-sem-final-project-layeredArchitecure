package lk.ijse.cinnamonProduction.model;


import lk.ijse.cinnamonProduction.db.DbConnection;
import lk.ijse.cinnamonProduction.dto.employeeManagementDTo;
import lk.ijse.cinnamonProduction.dto.tm.employeeManagementTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class employeeManagementModel {

    public static boolean deleteEmployee(String empId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM employee WHERE empId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, empId);

        return pstm.executeUpdate() > 0;

    }

    public static boolean updateEmployee(employeeManagementDTo dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE employee SET empName = ?, empAddress = ?,  empTeleNo = ?, empStatus = ? WHERE empId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getEmpName());
        pstm.setString(2, dto.getEmpAddress());
        pstm.setString(3, dto.getEmpTeleNo());
        pstm.setString(4,dto.getEmpStatus() );
        pstm.setString(5, dto.getEmpId());


        return pstm.executeUpdate() > 0;
    }

    public boolean saveEmployee(employeeManagementDTo employeeManagementDTo ) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO employee VALUES(?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, employeeManagementDTo.getEmpId());
        pstm.setString(2, employeeManagementDTo.getEmpName());
        pstm.setString(3, employeeManagementDTo.getEmpAddress());
        pstm.setString(4, employeeManagementDTo.getEmpTeleNo());
        pstm.setString(5, employeeManagementDTo.getEmpStatus());


        return pstm.executeUpdate() > 0;
    }

    public List<employeeManagementDTo> getAllEmployee() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM employee";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<employeeManagementDTo> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String empId = resultSet.getString(1);
            String empName = resultSet.getString(2);
            String empAddress = resultSet.getString(3);
            String empTeleNo = resultSet.getString(4);
            String empStatus = resultSet.getString(5);

            var dto = new employeeManagementDTo(empId,empName,empAddress, empTeleNo,empStatus);
            dtoList.add(dto);
        }
        return dtoList;

    }

    public employeeManagementDTo searchEmployee(String id) throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM employee WHERE empId = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,id);

        ResultSet resultSet = pstm.executeQuery();

        employeeManagementDTo dto = null;
        if(resultSet.next()){

            String empId = resultSet.getString(1);
            String empName = resultSet.getString(2);
            String empAddress = resultSet.getString(3);
            String empTeleNo = resultSet.getString(4);
            String empStatus = resultSet.getString(5);

            dto = new employeeManagementDTo(empId,empName,empAddress,empTeleNo,empStatus);

        }
        return dto;

    }

    public int countEmployee() throws SQLException {
        int count=0;
        Connection connection=DbConnection.getInstance().getConnection();

        String sql="select empId,count(*) as count from employee group by empId";
        PreparedStatement pstm=connection.prepareStatement(sql);
        ResultSet resultSet=pstm.executeQuery();
        while(resultSet.next()){
            count++;
        }
        return  count;
    }
}
