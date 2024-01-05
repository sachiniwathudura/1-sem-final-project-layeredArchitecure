package lk.ijse.cinnamonProduction.dao.custom.Impl1;

import lk.ijse.cinnamonProduction.dao.custom.salesDAO;
import lk.ijse.cinnamonProduction.db.DbConnection;
import lk.ijse.cinnamonProduction.entity.sales;
import lk.ijse.cinnamonProduction.viewTDM.salesTm;
//import lk.ijse.cinnamonProduction.dto.sales;
//import lk.ijse.cinnamonProduction.dto.tm.salesTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class salesModel implements salesDAO {
    public static boolean saveSales(sales salesDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO sales VALUES(?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, salesDto.getSalesNo());
        pstm.setString(2, String.valueOf(salesDto.getDate()));
     //   pstm.setString(2, String.valueOf(salesDto.getDate()));


        return pstm.executeUpdate() > 0;

    }

    public static boolean updateSales(salesTm salesTm) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE sales SET date = ?WHERE salesNo = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, salesTm.getSalesNo());
        pstm.setString(2, String.valueOf(salesTm.getDate()));


        return pstm.executeUpdate() > 0;

    }

    public List<sales> getAllSales() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM sales";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<sales> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String date = resultSet.getString(1);
            String salesNo= resultSet.getString(2);

            var dto = new sales(date,salesNo);
            dtoList.add(dto);
        }
        return dtoList;

    }

    public sales searchSales(String id) throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM machineDetails WHERE machineId = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,id);

        ResultSet resultSet = pstm.executeQuery();

        sales dto = null;
        if(resultSet.next()){

            String salesNo= resultSet.getString(1);
            String date = resultSet.getString(2);


            dto = new sales(salesNo,date);
        }
        return dto;
    }
}
