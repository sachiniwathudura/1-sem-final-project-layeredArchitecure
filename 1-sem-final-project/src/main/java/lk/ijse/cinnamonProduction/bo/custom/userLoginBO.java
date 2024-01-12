package lk.ijse.cinnamonProduction.bo.custom;

import lk.ijse.cinnamonProduction.bo.SuperBO;
import lk.ijse.cinnamonProduction.dto.userLoginDto;

import java.sql.SQLException;
import java.util.List;

public interface userLoginBO extends SuperBO {
    List<userLoginDto> getAllUser() throws SQLException, ClassNotFoundException;

    boolean saveUser(userLoginDto dto) throws SQLException, ClassNotFoundException ;

    boolean updateUser(userLoginDto dto) throws SQLException, ClassNotFoundException ;

    boolean existUser(String id) throws SQLException, ClassNotFoundException ;

    boolean deleteUser(String id) throws SQLException, ClassNotFoundException ;

    String generateID() throws SQLException, ClassNotFoundException ;

    userLoginDto searchUser(String id) throws SQLException, ClassNotFoundException;
}
