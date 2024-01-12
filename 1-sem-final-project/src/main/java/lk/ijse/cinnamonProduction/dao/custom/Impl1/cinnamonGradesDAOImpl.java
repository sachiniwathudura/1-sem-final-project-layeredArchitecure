package lk.ijse.cinnamonProduction.dao.custom.Impl1;

import lk.ijse.cinnamonProduction.dao.SQLUtil;
import lk.ijse.cinnamonProduction.dao.custom.cinnamonGradesDAO;
//import lk.ijse.cinnamonProduction.dto.cinnamonGrades;
//import lk.ijse.cinnamonProduction.dto.tm.cinnamonGradesTm;
import lk.ijse.cinnamonProduction.dto.cinnamonGradesDto;
import lk.ijse.cinnamonProduction.entity.cinnamonGrades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class cinnamonGradesDAOImpl implements cinnamonGradesDAO {

    @Override
    public List<cinnamonGrades> getAll() throws SQLException, ClassNotFoundException {
       // public List<cinnamonGrades> getAllCinnamonGrades() throws SQLException {

          //  Connection connection = DbConnection.getInstance().getConnection();

           // String sql = "SELECT * FROM cinnamonGrade";
           // PreparedStatement pstm = connection.prepareStatement(sql);

            List<cinnamonGrades> dtoList = new ArrayList<>();

            ResultSet resultSet = SQLUtil.execute("SELECT * FROM cinnamonGrade");

            while (resultSet.next()) {
                String cGradeId = resultSet.getString(1);
                String cGradeName = resultSet.getString(2);
                double c1KgPrice = Double.parseDouble(resultSet.getString(3));

                var dto = new cinnamonGrades(cGradeId, cGradeName, c1KgPrice);
                dtoList.add(dto);
            }
            return dtoList;
        }


    @Override
    public boolean save(cinnamonGrades entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO cinnamonGrade VALUES(?, ?, ?)",entity.getCGradeId(),entity.getCGradeName(),entity.getC1KgPrice());
    }

    @Override
    public boolean update(cinnamonGrades entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE cinnamonGrade SET cGradeName = ?, c1KgPrice = ? WHERE cGradeId ? ",entity.getCGradeName(),entity.getCGradeId(),entity.getCGradeId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException{
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
       boolean isDeleted=  SQLUtil.execute("DELETE FROM cinnamonGrade WHERE cGradeId = ?",id);

        return isDeleted;
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public cinnamonGrades search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet=SQLUtil.execute("SELECT * FROM cinnamonGrade WHERE cGradeId = ?",id);
        cinnamonGrades entity= null;
        if (resultSet.next()) {
            String cGradeId = resultSet.getString(1);
            String cGradeName = resultSet.getString(2);
            Double c1KgPrice = Double.valueOf(resultSet.getString(3));

            entity= new cinnamonGrades(cGradeId, cGradeName, c1KgPrice);
        }
        return entity;

    }

  /*  @Override
    public boolean update(cinnamonGrades entity) throws SQLException, ClassNotFoundException {
       // Connection connection = DbConnection.getInstance().getConnection();
        /*String sql = "UPDATE cinnamonGrade SET cGradeName = ?, c1KgPrice = ? WHERE cGradeId ? ";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, cinnamonGradesTm.getCGradeId());
        pstm.setString(2, cinnamonGradesTm.getCGradeName());
        pstm.setDouble(3, cinnamonGradesTm.getC1KgPrice());

        return pstm.executeUpdate() > 0;
        return SQLUtil.execute("UPDATE cinnamonGrade SET cGradeName = ?, c1KgPrice = ? WHERE cGradeId ? ",entity.getCGradeName(),entity.getC1KgPrice(),entity.getCGradeId());

    }
   @Override
    public static boolean delete(String cGradeId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM cinnamonGrade WHERE cGradeId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, cGradeId);
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
    }*/

}



