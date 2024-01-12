package lk.ijse.cinnamonProduction.bo.custom;

import lk.ijse.cinnamonProduction.bo.SuperBO;
import lk.ijse.cinnamonProduction.dto.machineDto;

import java.sql.SQLException;
import java.util.List;

public interface machineBO extends SuperBO {
    List<machineDto> getAllMachine() throws SQLException, ClassNotFoundException;

    boolean saveMachine(machineDto dto) throws SQLException, ClassNotFoundException ;

    boolean updateMachine(machineDto dto) throws SQLException, ClassNotFoundException ;

    boolean existMachine(String id) throws SQLException, ClassNotFoundException ;

    boolean deleteMachine(String id) throws SQLException, ClassNotFoundException ;

    String generateID() throws SQLException, ClassNotFoundException ;

    machineDto searchMachine(String id) throws SQLException, ClassNotFoundException;
}
