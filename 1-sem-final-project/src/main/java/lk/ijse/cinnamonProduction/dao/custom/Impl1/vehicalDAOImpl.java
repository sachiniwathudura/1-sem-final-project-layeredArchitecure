package lk.ijse.cinnamonProduction.dao.custom.Impl1;

import lk.ijse.cinnamonProduction.dao.SQLUtil;
import lk.ijse.cinnamonProduction.dao.custom.vehicalDAO;
import lk.ijse.cinnamonProduction.dto.cinnamonGradesDto;
import lk.ijse.cinnamonProduction.entity.vehical;
//import lk.ijse.cinnamonProduction.dto.tm.vehicalTm;
//import lk.ijse.cinnamonProduction.dto.vehical;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class vehicalDAOImpl implements vehicalDAO {
   /* public static boolean saveVehical(vehical vehicalDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = " INSERT INTO vehical VALUES(?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, vehicalDto.getVehicalNo());
        pstm.setString(2, vehicalDto.getVehicalStatus());


        return pstm.executeUpdate() > 0;
    }

    public static boolean deleteVehicl(String vehicalNo) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM vehical WHERE vehicalNo= ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, vehicalNo);

        return pstm.executeUpdate() > 0;
    }


    public vehical searchVehical(String id) throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM vehical WHERE vehicalNo = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,id);

        ResultSet resultSet = pstm.executeQuery();

        vehical dto = null;
        if(resultSet.next()){

            String vehicalNo = resultSet.getString(1);
            String vehicalStatus = resultSet.getString(2);

            dto = new vehical(vehicalNo,vehicalStatus);
        }
        return dto;
    }

    public boolean updateVehical(vehicalTm tm) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE vehical SET  vehicalStatus= ? WHERE vehicalNo = ? ";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, tm.getVehicalNo());
        pstm.setString(2, tm.getVehicalStatus());

        return pstm.executeUpdate() > 0;
    }

    public List<vehical> getAllVehical() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM vehical";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<vehical> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String vehicalNo = resultSet.getString(1);
            String vehicalStatus = resultSet.getString(2);


            var dto = new vehical(vehicalNo,vehicalStatus);
            dtoList.add(dto);
        }
        return dtoList;

    }*/

    @Override
    public List<vehical> getAll() throws SQLException, ClassNotFoundException {
       // String sql = "SELECT * FROM vehical";
       // PreparedStatement pstm = connection.prepareStatement(sql);

        List<vehical> dtoList = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM vehical");

        while (resultSet.next()) {
            String vehicalNo = resultSet.getString(1);
            String vehicalStatus = resultSet.getString(2);


            var dto = new vehical(vehicalNo,vehicalStatus);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public boolean save(vehical entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO vehical VALUES(?, ?)",
                entity.getVehicalNo(),entity.getVehicalStatus());
    }

    @Override
    public boolean update(vehical entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE vehical SET  vehicalStatus= ? WHERE vehicalNo = ?",
                entity.getVehicalStatus(),entity.getVehicalNo());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
       boolean isDeleted= SQLUtil.execute("DELETE FROM vehical WHERE vehicalNo= ?",id);
        return isDeleted;
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public vehical search(String id) throws SQLException, ClassNotFoundException {
        
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM vehical WHERE vehicalNo = ?",id);

        vehical entity = null;
        if(resultSet.next()){

            String vehicalNo = resultSet.getString(1);
            String vehicalStatus = resultSet.getString(2);

            entity = new vehical(vehicalNo,vehicalStatus);
        }
        return entity;
    }
}

