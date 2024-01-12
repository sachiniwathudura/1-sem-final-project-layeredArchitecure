package lk.ijse.cinnamonProduction.bo.custom;

import lk.ijse.cinnamonProduction.bo.SuperBO;
import lk.ijse.cinnamonProduction.dto.vehicalDto;

import java.sql.SQLException;
import java.util.List;

public interface vehicalBO extends SuperBO {
    List<vehicalDto> getAllVehical() throws SQLException, ClassNotFoundException;

    boolean saveVehical(vehicalDto dto) throws SQLException, ClassNotFoundException ;

    boolean updateVehical(vehicalDto dto) throws SQLException, ClassNotFoundException ;

    boolean existVehical(String id) throws SQLException, ClassNotFoundException ;

    boolean deleteVehical(String id) throws SQLException, ClassNotFoundException ;

    String generateID() throws SQLException, ClassNotFoundException ;

    vehicalDto searchVehical(String id) throws SQLException, ClassNotFoundException;
}
