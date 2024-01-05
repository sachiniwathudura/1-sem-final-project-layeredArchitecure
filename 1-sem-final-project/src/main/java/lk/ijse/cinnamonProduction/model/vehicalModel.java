package lk.ijse.cinnamonProduction.model;

import lk.ijse.cinnamonProduction.db.DbConnection;
import lk.ijse.cinnamonProduction.dto.machineDto;
import lk.ijse.cinnamonProduction.dto.stockDto;
import lk.ijse.cinnamonProduction.dto.tm.vehicalTm;
import lk.ijse.cinnamonProduction.dto.userLoginDto;
import lk.ijse.cinnamonProduction.dto.vehicalDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class vehicalModel {
    public static boolean saveVehical(vehicalDto vehicalDto) throws SQLException {
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


    public vehicalDto searchVehical(String id) throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM vehical WHERE vehicalNo = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,id);

        ResultSet resultSet = pstm.executeQuery();

        vehicalDto dto = null;
        if(resultSet.next()){

            String vehicalNo = resultSet.getString(1);
            String vehicalStatus = resultSet.getString(2);

            dto = new vehicalDto(vehicalNo,vehicalStatus);
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

    public List<vehicalDto> getAllVehical() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM vehical";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<vehicalDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String vehicalNo = resultSet.getString(1);
            String vehicalStatus = resultSet.getString(2);


            var dto = new vehicalDto(vehicalNo,vehicalStatus);
            dtoList.add(dto);
        }
        return dtoList;

    }
}

