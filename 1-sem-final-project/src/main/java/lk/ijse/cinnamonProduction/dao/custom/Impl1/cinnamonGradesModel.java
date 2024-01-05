package lk.ijse.cinnamonProduction.dao.custom.Impl1;

import lk.ijse.cinnamonProduction.dao.custom.cinnamonGradesDAO;
import lk.ijse.cinnamonProduction.db.DbConnection;
//import lk.ijse.cinnamonProduction.dto.cinnamonGrades;
//import lk.ijse.cinnamonProduction.dto.tm.cinnamonGradesTm;
import lk.ijse.cinnamonProduction.entity.cinnamonGrades;
import lk.ijse.cinnamonProduction.viewTDM.cinnamonGradesTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class cinnamonGradesModel implements cinnamonGradesDAO {

    @Override
    public boolean update(cinnamonGradesTm cinnamonGradesTm) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE cinnamonGrade SET cGradeName = ?, c1KgPrice = ? WHERE cGradeId ? ";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, cinnamonGradesTm.getCGradeId());
        pstm.setString(2, cinnamonGradesTm.getCGradeName());
        pstm.setDouble(3, cinnamonGradesTm.getC1KgPrice());

        return pstm.executeUpdate() > 0;
    }
    @Override
    public static boolean delete(String cGradeId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM cinnamonGrade WHERE cGradeId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, cGradeId);

        return pstm.executeUpdate() > 0;
    }
    @Override
    public boolean save(cinnamonGrades dto) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO cinnamonGrade VALUES(?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getCGradeId());
        pstm.setString(2, dto.getCGradeName());
        pstm.setDouble(3, dto.getC1KgPrice());

        return pstm.executeUpdate() > 0;
    }

    public List<cinnamonGrades> getAllCinnamonGrades() throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM cinnamonGrade";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<cinnamonGrades> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String cGradeId = resultSet.getString(1);
            String cGradeName = resultSet.getString(2);
            double c1KgPrice = Double.parseDouble(resultSet.getString(3));

            var dto = new cinnamonGrades(cGradeId, cGradeName, c1KgPrice);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public cinnamonGrades searchCgrade(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM cinnamonGrade WHERE cGradeId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();

        cinnamonGrades dto = null;
        if (resultSet.next()) {
            String cGradeId = resultSet.getString(1);
            String cGradeName = resultSet.getString(2);
            Double c1KgPrice = Double.valueOf(resultSet.getString(3));

            dto = new cinnamonGrades(cGradeId, cGradeName, c1KgPrice);
        }
        return dto;
    }

}



