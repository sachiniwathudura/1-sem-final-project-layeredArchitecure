package lk.ijse.cinnamonProduction.dao.custom.Impl1;

import lk.ijse.cinnamonProduction.dao.SQLUtil;
import lk.ijse.cinnamonProduction.dao.custom.salesDAO;
import lk.ijse.cinnamonProduction.dto.cinnamonGradesDto;
import lk.ijse.cinnamonProduction.entity.cinnamonGrades;
import lk.ijse.cinnamonProduction.entity.sales;
//import lk.ijse.cinnamonProduction.dto.sales;
//import lk.ijse.cinnamonProduction.dto.tm.salesTm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class salesDAOImpl implements salesDAO {
   /* public static boolean saveSales(sales salesDto) throws SQLException {
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
    }*/

    @Override
    public List<sales> getAll() throws SQLException, ClassNotFoundException {

        List<sales> dtoList = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM sales");

        while (resultSet.next()) {
            String date = resultSet.getString(1);
            String salesNo= resultSet.getString(2);

            var entity = new sales(date,salesNo);
            dtoList.add(entity);
        }
        return dtoList;
    }

    @Override
    public boolean save(sales entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO sales VALUES(?, ?)",entity.getSalesNo(),entity.getDate());
    }

    @Override
    public boolean update(sales entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE sales SET date = ?WHERE salesNo = ?",entity.getDate(),entity.getSalesNo());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {

        return false;
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public sales search(String id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM machineDetails WHERE machineId = ?",id);

        sales entity = null;
        if(resultSet.next()){

            String salesNo= resultSet.getString(1);
            String date = resultSet.getString(2);


            entity = new sales(salesNo,date);
        }
        return entity;
    }
}
