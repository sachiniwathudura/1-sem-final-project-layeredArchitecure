package lk.ijse.cinnamonProduction.bo.custom.Impl1;

import lk.ijse.cinnamonProduction.bo.custom.machineBO;
import lk.ijse.cinnamonProduction.bo.custom.merchantBO;
import lk.ijse.cinnamonProduction.dao.DAOFactory;
import lk.ijse.cinnamonProduction.dao.custom.merchantDAO;
import lk.ijse.cinnamonProduction.dto.companyDto;
import lk.ijse.cinnamonProduction.dto.machineDto;
import lk.ijse.cinnamonProduction.dto.registerMerchantDto;
import lk.ijse.cinnamonProduction.entity.company;
import lk.ijse.cinnamonProduction.entity.registerMerchant;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class merchantBOImpl implements merchantBO {

    merchantDAO MerchantDAO = (merchantDAO) DAOFactory.
            getDaoFactory().getDAO(DAOFactory.DAOTypes.MERCHANT);


    @Override
    public List<registerMerchantDto> getAllMerchant() throws SQLException, ClassNotFoundException {
        List<registerMerchant>list=MerchantDAO.getAll();
        List<registerMerchantDto>registerMerchantDtos=new ArrayList<>();
        for (registerMerchant rl:list) {
            registerMerchantDtos.add(new registerMerchantDto(
                    rl.getMerchantId(),
                    rl.getMerchantName(),
                    rl.getHomeNo(),
                    rl.getStreet(),
                    rl.getTown(),
                    rl.getMerchantTeleNo(),
                    rl.getMerchantCategory()
            ));

        }
        return registerMerchantDtos;
    }

    @Override
    public boolean saveMerchant(registerMerchantDto dto) throws SQLException, ClassNotFoundException {
        return MerchantDAO.save(new registerMerchant(dto.getMerchantId(),dto.getMerchantName(),dto.getHomeNo(),dto.getStreet(),dto.getTown(),dto.getMerchantTeleNo(),dto.getMerchantCategory()));
    }

    @Override
    public boolean updateMerchant(registerMerchantDto dto) throws SQLException, ClassNotFoundException {
        return MerchantDAO.update(new registerMerchant(dto.getMerchantId(),dto.getMerchantName(),dto.getHomeNo(),dto.getStreet(),dto.getTown(),dto.getMerchantTeleNo(),dto.getMerchantCategory()));
    }

    @Override
    public boolean existMerchant(String id) throws SQLException, ClassNotFoundException {
        return MerchantDAO.exist(id);
    }

    @Override
    public boolean deleteMerchant(String id) throws SQLException, ClassNotFoundException {
       return MerchantDAO.delete(id);
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        return MerchantDAO.generateID();
    }

    @Override
    public registerMerchantDto searchMerchant(String id) throws SQLException, ClassNotFoundException {

        registerMerchant Merchant = MerchantDAO.search(id);
        registerMerchantDto RegisterMerchantDto = new registerMerchantDto(Merchant.getMerchantId(),Merchant.getMerchantName(), Merchant.getHomeNo(), Merchant.getStreet(), Merchant.getTown(), Merchant.getMerchantTeleNo(), Merchant.getMerchantCategory());
        return RegisterMerchantDto;
    }
}
