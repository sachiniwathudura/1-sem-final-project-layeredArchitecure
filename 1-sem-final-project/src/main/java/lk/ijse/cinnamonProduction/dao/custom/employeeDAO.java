package lk.ijse.cinnamonProduction.dao.custom;

import lk.ijse.cinnamonProduction.dao.CrudDAO;
import lk.ijse.cinnamonProduction.db.DbConnection;
import lk.ijse.cinnamonProduction.entity.employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface employeeDAO extends CrudDAO<employee> {
    public static int countEmployee() throws SQLException, SQLException {
        int count = 0;
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "select empId,count(*) as count from employee group by empId";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            count++;
        }
        return count;
    }
}
