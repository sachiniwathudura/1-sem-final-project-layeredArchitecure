package lk.ijse.cinnamonProduction.model;

import lk.ijse.cinnamonProduction.db.DbConnection;
import lk.ijse.cinnamonProduction.dto.cinnamonGradesDto;
import lk.ijse.cinnamonProduction.dto.companyDto;
import lk.ijse.cinnamonProduction.dto.tm.companyTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class companyModel {

    public  boolean deleteCompany(String companyId) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM company WHERE companyId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, companyId);

        return pstm.executeUpdate() > 0;
    }

    public  boolean updateCompany(companyTm companyTm) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE company SET companyName = ?, companyEmail = ? WHERE companyId= ? ";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,companyTm.getCompanyName());
        pstm.setString(2,companyTm.getCompanyEmail());
        pstm.setString(3,companyTm.getCompanyId());


        return pstm.executeUpdate() > 0 ;

    }

    public boolean saveCompany(companyDto dto) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO company VALUES(?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,dto.getCompanyId());
        pstm.setString(2,dto.getCompanyName());
        pstm.setString(3,dto.getCompanyEmail());

        return pstm.executeUpdate() > 0 ;

    }


    public List<companyDto> getAllCompany() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM company";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<companyDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String companyId = resultSet.getString(1);
            String companyName = resultSet.getString(2);
            String companyEmail = resultSet.getString(3);

            var dto = new companyDto(companyId,companyName,companyEmail);
            dtoList.add(dto);
        }
        return dtoList;

    }

    public List<companyDto> loadAllCompany() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM company";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<companyDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(new companyDto(
             resultSet.getString(1),
             resultSet.getString(2),
             resultSet.getString(3)
            ));

        }
        return dtoList;

    }

}
