package lk.ijse.cinnamonProduction.dao.custom.Impl1;

import lk.ijse.cinnamonProduction.dao.SQLUtil;
import lk.ijse.cinnamonProduction.dao.custom.satockDAO;
import lk.ijse.cinnamonProduction.dto.cinnamonGradesDto;
import lk.ijse.cinnamonProduction.entity.stock;
//import lk.ijse.cinnamonProduction.dto.stock;
//import lk.ijse.cinnamonProduction.dto.tm.stockTm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class stockDAOImpl implements satockDAO {

    /*public static boolean saveItem(stockTm stockDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO stock VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, stockDto.getItemId());
        pstm.setString(2, stockDto.getItemName());
        pstm.setDouble(3, stockDto.getItemQty());
        pstm.setString(4, stockDto.getItemCategory());

        return pstm.executeUpdate() > 0;

    }

    public static boolean updateItem(stockTm stockDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE stock SET itemName = ?, itemQty = ?, itemCategory = ? WHERE itemId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, stockDto.getItemName());
        pstm.setDouble(2, stockDto.getItemQty());
        pstm.setString(3, stockDto.getItemCategory());
        pstm.setString(4, stockDto.getItemId());

        return pstm.executeUpdate() > 0;

    }

    public static boolean deleteItem(String itemId) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM stock WHERE itemId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, itemId);

        return pstm.executeUpdate() > 0;
    }

    public List<stock> getAllStock() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM stock";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<stock> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String itemId = resultSet.getString(1);
            String itemName= resultSet.getString(2);
            double itemQty= Double.parseDouble(resultSet.getString(3));
            String itemCategory= resultSet.getString(4);

            var dto = new stock(itemId,itemName,itemQty,itemCategory);
            dtoList.add(dto);
        }
        return dtoList;

    }

    public stock searchStock(String id) throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM stock WHERE itemId = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,id);

        ResultSet resultSet = pstm.executeQuery();

        stock dto = null;
        if(resultSet.next()){

            String itemId = resultSet.getString(1);
            String itemName = resultSet.getString(2);
            double itemQty  = Double.parseDouble(resultSet.getString(3));
            String itemCategory = resultSet.getString(4);

            dto = new stock(itemId,itemName,itemQty,itemCategory);
        }
        return dto;
    }*/

    @Override
    public List<stock> getAll() throws SQLException, ClassNotFoundException {
       // String sql = "SELECT * FROM stock";
      //  PreparedStatement pstm = connection.prepareStatement(sql);

        List<stock> dtoList = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM stock");

        while (resultSet.next()) {
            String itemId = resultSet.getString(1);
            String itemName= resultSet.getString(2);
            double itemQty= Double.parseDouble(resultSet.getString(3));
            String itemCategory= resultSet.getString(4);

            var dto = new stock(itemId,itemName,itemQty,itemCategory);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public boolean save(stock entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO stock VALUES(?, ?, ?, ?)",
                entity.getItemId(),entity.getItemName(),entity.getItemQty(),entity.getItemCategory());
    }

    @Override
    public boolean update(stock entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE stock SET itemName = ?, itemQty = ?, itemCategory = ? WHERE itemId = ?",
                entity.getItemName(),entity.getItemQty(),entity.getItemCategory(),entity.getItemId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("DELETE FROM stock WHERE itemId = ?",id);
        return false;
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public stock search(String id) throws SQLException, ClassNotFoundException {
        //String sql = "SELECT * FROM stock WHERE itemId = ?";

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM stock WHERE itemId = ?",id);

        stock entity = null;
        if(resultSet.next()){

            String itemId = resultSet.getString(1);
            String itemName = resultSet.getString(2);
            double itemQty  = Double.parseDouble(resultSet.getString(3));
            String itemCategory = resultSet.getString(4);

            entity = new stock(itemId,itemName,itemQty,itemCategory);
        }
        return entity;
    }
}
