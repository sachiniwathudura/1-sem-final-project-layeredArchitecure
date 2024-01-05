package lk.ijse.cinnamonProduction.model;

import lk.ijse.cinnamonProduction.db.DbConnection;
import lk.ijse.cinnamonProduction.dto.employeeManagementDTo;
import lk.ijse.cinnamonProduction.dto.machineDto;
import lk.ijse.cinnamonProduction.dto.tm.machineTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class machineModel {

    public static boolean deleteMachine(String machineId) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM machineDetails WHERE machineId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, machineId);

        return pstm.executeUpdate() > 0;
    }

    public static boolean updateMachine(machineTm machineTm) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE machineDetails SET machineName = ?, machineStatus = ? WHERE machineId =? ";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,machineTm.getMachineName());
        pstm.setString(2,machineTm.getMachineStatus());
        pstm.setString(3,machineTm.getMachineId());

        return pstm.executeUpdate() >0;
    }

    public boolean savemachine(machineDto dto) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO machineDetails VALUES(?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,dto.getMachineId());
        pstm.setString(2,dto.getMachineName());
        pstm.setString(3,dto.getMachineStatus());

        return pstm.executeUpdate() >0;

    }

    public List<machineDto> getAllMachine() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM machineDetails";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<machineDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String machineId = resultSet.getString(1);
            String machineName = resultSet.getString(2);
            String machineStatus = resultSet.getString(3);

            var dto = new machineDto(machineId,machineName,machineStatus);
            dtoList.add(dto);
        }
        return dtoList;

    }

    public machineDto searchMachine(String id) throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM machineDetails WHERE machineId = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,id);

        ResultSet resultSet = pstm.executeQuery();

        machineDto dto = null;
        if(resultSet.next()){

            String machineId = resultSet.getString(1);
            String machineName = resultSet.getString(2);
            String machineStatus = resultSet.getString(3);

            dto = new machineDto(machineId,machineName,machineStatus);
        }
        return dto;
    }
}
