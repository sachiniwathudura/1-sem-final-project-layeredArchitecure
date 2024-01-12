package lk.ijse.cinnamonProduction.dao.custom.Impl1;


import lk.ijse.cinnamonProduction.dao.SQLUtil;
import lk.ijse.cinnamonProduction.dao.custom.employeeDAO;
//import lk.ijse.cinnamonProduction.dto.employee;
import lk.ijse.cinnamonProduction.dto.cinnamonGradesDto;
import lk.ijse.cinnamonProduction.entity.employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class employeeManagementDAOImpl implements employeeDAO {

  /*  public static boolean deleteEmployee(String empId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM employee WHERE empId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, empId);

        return pstm.executeUpdate() > 0;

    }

    public static boolean updateEmployee(employee dto) throws SQLException {
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

    public boolean saveEmployee(employee employeeManagementDTo ) throws SQLException {

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

    public List<employee> getAllEmployee() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM employee";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<employee> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String empId = resultSet.getString(1);
            String empName = resultSet.getString(2);
            String empAddress = resultSet.getString(3);
            String empTeleNo = resultSet.getString(4);
            String empStatus = resultSet.getString(5);

            var dto = new employee(empId,empName,empAddress, empTeleNo,empStatus);
            dtoList.add(dto);
        }
        return dtoList;

    }

    public employee searchEmployee(String id) throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM employee WHERE empId = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,id);

        ResultSet resultSet = pstm.executeQuery();

        employee dto = null;
        if(resultSet.next()){

            String empId = resultSet.getString(1);
            String empName = resultSet.getString(2);
            String empAddress = resultSet.getString(3);
            String empTeleNo = resultSet.getString(4);
            String empStatus = resultSet.getString(5);

            dto = new employee(empId,empName,empAddress,empTeleNo,empStatus);

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
    }*/

    @Override
    public List<employee> getAll() throws SQLException, ClassNotFoundException {
        List<employee> dtoList = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM employee");

        while (resultSet.next()) {
            String empId = resultSet.getString(1);
            String empName = resultSet.getString(2);
            String empAddress = resultSet.getString(3);
            String empTeleNo = resultSet.getString(4);
            String empStatus = resultSet.getString(5);

            var dto = new employee(empId,empName,empAddress, empTeleNo,empStatus);
            dtoList.add(dto);
        }
        return dtoList;

    }

    @Override
    public boolean save(employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO employee VALUES(?, ?, ?, ?, ?)",
                entity.getEmpId(),entity.getEmpName(),entity.getEmpAddress(),entity.getEmpTeleNo(),entity.getEmpStatus());
    }

    @Override
    public boolean update(employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE employee SET empName = ?, empAddress = ?,  empTeleNo = ?, empStatus = ? WHERE empId=?",
                entity.getEmpName(),entity.getEmpAddress(),entity.getEmpTeleNo(),entity.getEmpStatus(),entity.getEmpId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        boolean isDeleted=SQLUtil.execute("DELETE FROM employee WHERE empId = ?",id);
        return isDeleted;
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public employee search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM employee WHERE empId = ?");

        employee entity = null;
        if(resultSet.next()){

            String empId = resultSet.getString(1);
            String empName = resultSet.getString(2);
            String empAddress = resultSet.getString(3);
            String empTeleNo = resultSet.getString(4);
            String empStatus = resultSet.getString(5);

            entity = new employee(empId,empName,empAddress,empTeleNo,empStatus);

        }
        return entity;

    }
}
