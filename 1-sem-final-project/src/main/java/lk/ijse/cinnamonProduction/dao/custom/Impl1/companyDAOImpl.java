package lk.ijse.cinnamonProduction.dao.custom.Impl1;

import lk.ijse.cinnamonProduction.dao.SQLUtil;
import lk.ijse.cinnamonProduction.dao.custom.companyDAO;
import lk.ijse.cinnamonProduction.dto.cinnamonGradesDto;
import lk.ijse.cinnamonProduction.entity.cinnamonGrades;
import lk.ijse.cinnamonProduction.entity.company;
//import lk.ijse.cinnamonProduction.dto.company;
//import lk.ijse.cinnamonProduction.dto.tm.companyTm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class companyDAOImpl implements companyDAO {
    @Override
    public List<company> getAll() throws SQLException, ClassNotFoundException {
        // Connection connection = DbConnection.getInstance().getConnection();

        // String sql = "SELECT * FROM company";
        //PreparedStatement pstm = connection.prepareStatement(sql);

        List<company> dtoList = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM company");

        while (resultSet.next()) {
            String companyId = resultSet.getString(1);
            String companyName = resultSet.getString(2);
            String companyEmail = resultSet.getString(3);

            var dto = new company(companyId, companyName, companyEmail);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public boolean save(company entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO company VALUES(?, ?, ?)",
                entity.getCompanyId(), entity.getCompanyName(), entity.getCompanyEmail());
    }

    @Override
    public boolean update(company entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE company SET companyName = ?, companyEmail = ? WHERE companyId= ?",
                entity.getCompanyName(), entity.getCompanyEmail(), entity.getCompanyId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("DELETE FROM company WHERE companyId = ?", id);

        return false;
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public company search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}

   /* public  boolean deleteCompany(String companyId) throws SQLException {

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

    public boolean saveCompany(company dto) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO company VALUES(?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,dto.getCompanyId());
        pstm.setString(2,dto.getCompanyName());
        pstm.setString(3,dto.getCompanyEmail());

        return pstm.executeUpdate() > 0 ;

    }


    public List<company> getAllCompany() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM company";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<company> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String companyId = resultSet.getString(1);
            String companyName = resultSet.getString(2);
            String companyEmail = resultSet.getString(3);

            var dto = new company(companyId,companyName,companyEmail);
            dtoList.add(dto);
        }
        return dtoList;

    }

    public List<company> loadAllCompany() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM company";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<company> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(new company(
             resultSet.getString(1),
             resultSet.getString(2),
             resultSet.getString(3)
            ));

        }
        return dtoList;

    }


}*/
