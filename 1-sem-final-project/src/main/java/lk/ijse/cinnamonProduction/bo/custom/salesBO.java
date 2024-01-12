package lk.ijse.cinnamonProduction.bo.custom;

import lk.ijse.cinnamonProduction.bo.SuperBO;
import lk.ijse.cinnamonProduction.dto.salesDto;

import java.sql.SQLException;
import java.util.List;

public interface salesBO extends SuperBO {
    List<salesDto> getAllSales() throws SQLException, ClassNotFoundException;

    boolean saveSales(salesDto dto) throws SQLException, ClassNotFoundException ;

    boolean updateSales(salesDto dto) throws SQLException, ClassNotFoundException ;

    boolean existSales(String id) throws SQLException, ClassNotFoundException ;

    boolean deleteSales(String id) throws SQLException, ClassNotFoundException ;

    String generateIDSales() throws SQLException, ClassNotFoundException ;

    salesDto searchSales(String id) throws SQLException, ClassNotFoundException;
}
