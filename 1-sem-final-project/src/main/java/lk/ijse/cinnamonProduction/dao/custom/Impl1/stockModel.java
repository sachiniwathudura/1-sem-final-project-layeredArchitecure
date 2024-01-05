package lk.ijse.cinnamonProduction.dao.custom.Impl1;

import lk.ijse.cinnamonProduction.dao.custom.satockDAO;
import lk.ijse.cinnamonProduction.db.DbConnection;
import lk.ijse.cinnamonProduction.entity.stock;
import lk.ijse.cinnamonProduction.viewTDM.stockTm;
//import lk.ijse.cinnamonProduction.dto.stock;
//import lk.ijse.cinnamonProduction.dto.tm.stockTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class stockModel implements satockDAO {

    public static boolean saveItem(stockTm stockDto) throws SQLException {
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
    }
}
