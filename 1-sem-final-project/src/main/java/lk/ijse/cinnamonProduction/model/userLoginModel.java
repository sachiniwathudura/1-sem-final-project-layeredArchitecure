package lk.ijse.cinnamonProduction.model;

import lk.ijse.cinnamonProduction.db.DbConnection;
import lk.ijse.cinnamonProduction.dto.userLoginDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;

public class userLoginModel {

    public static boolean saveUser(userLoginDto userLoginDto) throws SQLException {
          Connection connection = DbConnection.getInstance().getConnection();
          String sql = " INSERT INTO user VALUES(?, ?, ?, ?)";
          PreparedStatement pstm = connection.prepareStatement(sql);

          pstm.setString(1, userLoginDto.getUserId());
          pstm.setString(2, userLoginDto.getUserName());
          pstm.setString(3, userLoginDto.getUserEmail());
          pstm.setString(3, userLoginDto.getPassword());

          return pstm.executeUpdate() > 0;
           }



    public static boolean deleteUser(String userId) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM user WHERE userId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, userId);

        return pstm.executeUpdate() > 0;
    }


    public static boolean updateUser(userLoginDto userLoginDto) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE user SET userName = ?, userEmail = ?, password = ? WHERE userId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, userLoginDto.getUserName());
        pstm.setString(2, userLoginDto.getUserEmail());
        pstm.setString(3, userLoginDto.getPassword());
        pstm.setString(4, userLoginDto.getUserId());

        return pstm.executeUpdate() > 0;
    }
}
