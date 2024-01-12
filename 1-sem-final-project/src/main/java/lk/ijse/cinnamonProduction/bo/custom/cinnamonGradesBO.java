package lk.ijse.cinnamonProduction.bo.custom;

import lk.ijse.cinnamonProduction.bo.SuperBO;
import lk.ijse.cinnamonProduction.dto.cinnamonGradesDto;
import lk.ijse.cinnamonProduction.entity.cinnamonGrades;

import java.sql.SQLException;
import java.util.List;

public interface cinnamonGradesBO extends SuperBO {
    List<cinnamonGradesDto> getAllCinnamonGrades() throws SQLException, ClassNotFoundException;

    boolean saveCinnamonGrades(cinnamonGradesDto dto) throws SQLException, ClassNotFoundException ;

    boolean updateCinnamonGrades(cinnamonGradesDto dto) throws SQLException, ClassNotFoundException ;

    boolean exist(String id) throws SQLException, ClassNotFoundException ;

    boolean deleteCinnamonGrades(String id) throws SQLException, ClassNotFoundException ;

    String generateID() throws SQLException, ClassNotFoundException ;

    cinnamonGradesDto searchCinnamonGrades(String id) throws SQLException, ClassNotFoundException;
}
