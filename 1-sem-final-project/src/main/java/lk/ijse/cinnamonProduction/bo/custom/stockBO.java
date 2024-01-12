package lk.ijse.cinnamonProduction.bo.custom;

import lk.ijse.cinnamonProduction.bo.SuperBO;
import lk.ijse.cinnamonProduction.dto.stockDto;

import java.sql.SQLException;
import java.util.List;

public interface stockBO extends SuperBO {
    List<stockDto> getAllStock() throws SQLException, ClassNotFoundException;

    boolean saveStock(stockDto dto) throws SQLException, ClassNotFoundException ;

    boolean updateStock(stockDto dto) throws SQLException, ClassNotFoundException ;

    boolean existStock(String id) throws SQLException, ClassNotFoundException ;

    boolean deleteStock(String id) throws SQLException, ClassNotFoundException ;

    String generateID() throws SQLException, ClassNotFoundException ;

    stockDto searchStock(String id) throws SQLException, ClassNotFoundException;
}
