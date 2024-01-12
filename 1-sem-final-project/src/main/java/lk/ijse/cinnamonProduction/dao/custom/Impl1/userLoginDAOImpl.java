package lk.ijse.cinnamonProduction.dao.custom.Impl1;

import lk.ijse.cinnamonProduction.dao.SQLUtil;
import lk.ijse.cinnamonProduction.dao.custom.userLoginDAO;
import lk.ijse.cinnamonProduction.dto.cinnamonGradesDto;
import lk.ijse.cinnamonProduction.entity.userLogin;
//import lk.ijse.cinnamonProduction.dto.userLogin;

import java.sql.SQLException;
import java.util.List;

public class userLoginDAOImpl implements userLoginDAO {

    /*public static boolean saveUser(userLogin userLoginDto) throws SQLException {
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


    public static boolean updateUser(userLogin userLoginDto) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE user SET userName = ?, userEmail = ?, password = ? WHERE userId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, userLoginDto.getUserName());
        pstm.setString(2, userLoginDto.getUserEmail());
        pstm.setString(3, userLoginDto.getPassword());
        pstm.setString(4, userLoginDto.getUserId());

        return pstm.executeUpdate() > 0;
    }*/

    @Override
    public List<userLogin> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(userLogin entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO user VALUES(?, ?, ?, ?)",
                entity.getUserId(),entity.getUserName(),entity.getUserEmail(),entity.getPassword());
    }

    @Override
    public boolean update(userLogin entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE user SET userName = ?, userEmail = ?, password = ? WHERE userId = ?",
                entity.getUserName(),entity.getUserEmail(),entity.getPassword(),entity.getUserId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("DELETE FROM user WHERE userId = ?",id);
        return false;
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public userLogin search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
