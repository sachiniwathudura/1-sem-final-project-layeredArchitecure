package lk.ijse.cinnamonProduction.bo.custom;

import lk.ijse.cinnamonProduction.bo.SuperBO;
import lk.ijse.cinnamonProduction.dto.registerMerchantDto;

import java.sql.SQLException;
import java.util.List;

public interface merchantBO extends SuperBO {

    List<registerMerchantDto> getAllMerchant() throws SQLException, ClassNotFoundException;

    boolean saveMerchant(registerMerchantDto dto) throws SQLException, ClassNotFoundException ;

    boolean updateMerchant(registerMerchantDto dto) throws SQLException, ClassNotFoundException ;

    boolean existMerchant(String id) throws SQLException, ClassNotFoundException ;

    boolean deleteMerchant(String id) throws SQLException, ClassNotFoundException ;

    String generateID() throws SQLException, ClassNotFoundException ;

    registerMerchantDto searchMerchant(String id) throws SQLException, ClassNotFoundException;

}
