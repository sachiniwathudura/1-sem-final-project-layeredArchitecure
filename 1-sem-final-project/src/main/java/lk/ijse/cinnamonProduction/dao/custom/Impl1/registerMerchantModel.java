package lk.ijse.cinnamonProduction.dao.custom.Impl1;

import lk.ijse.cinnamonProduction.dao.custom.merchantDAO;
import lk.ijse.cinnamonProduction.db.DbConnection;
import lk.ijse.cinnamonProduction.entity.registerMerchant;
import lk.ijse.cinnamonProduction.viewTDM.registerMerchantTm;
//import lk.ijse.cinnamonProduction.dto.registerMerchant;
//import lk.ijse.cinnamonProduction.dto.tm.registerMerchantTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class registerMerchantModel implements merchantDAO {
    public static boolean deleteMerchant(String merchantId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM merchant WHERE merchantId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, merchantId);

        return pstm.executeUpdate() > 0;
    }

    public boolean saveMerchant(registerMerchant registerMerchantDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO merchant VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, registerMerchantDto.getMerchantId());
        pstm.setString(2, registerMerchantDto.getMerchantName());
        pstm.setString(3, registerMerchantDto.getHomeNo());
        pstm.setString(4, registerMerchantDto.getStreet());
        pstm.setString(5, registerMerchantDto.getTown());
        pstm.setString(6, registerMerchantDto.getMerchantTeleNo());
        pstm.setString(7, registerMerchantDto.getMerchantCategory());

        return pstm.executeUpdate() > 0;

    }

    public static boolean updateMerchant(registerMerchantTm registerMerchantDto ) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE merchant SET merchantName = ?, merchantTeleNo = ?, homeNo = ?, street = ?, town = ?,merchantCategory = ? WHERE merchantId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, registerMerchantDto.getMerchantName());
        pstm.setString(2, registerMerchantDto.getMerchantTeleNo());
        pstm.setString(3, registerMerchantDto.getHomeNo());
        pstm.setString(4, registerMerchantDto.getStreet());
        pstm.setString(5, registerMerchantDto.getTown());
        pstm.setString(6, registerMerchantDto.getMerchantCategory());
        pstm.setString(7, registerMerchantDto.getMerchantId());

        return pstm.executeUpdate() > 0;



    }

    public List<registerMerchant> getAllMerchant() throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM merchant";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<registerMerchant> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String merchantId = resultSet.getString(1);
            String merchantName = resultSet.getString(2);
            String merchantTeleno = resultSet.getString(3);
            String homeno = resultSet.getString(4);
            String street = resultSet.getString(5);
            String town = resultSet.getString(6);
            String merchantCategory = resultSet.getString(7);



            var dto = new registerMerchant(merchantId, merchantName, merchantTeleno, homeno, street,town, merchantCategory);
            dtoList.add(dto);
        }
        return dtoList;

    }

    public registerMerchant searchMerchant(String id) throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM merchant WHERE merchantId = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,id);

        ResultSet resultSet = pstm.executeQuery();

        registerMerchant dto = null;
        if(resultSet.next()){

            String merchantId = resultSet.getString(1);
            String merchantName = resultSet.getString(2);
            String homeNo = resultSet.getString(3);
            String street = resultSet.getString(4);
            String town = resultSet.getString(5);
            String merchantCategory = resultSet.getString(6);
            String merchantTeleNo = resultSet.getString(7);

            dto = new registerMerchant(merchantId,merchantName,merchantTeleNo,homeNo,street,town,merchantCategory);
        }
        return dto;
    }
}
