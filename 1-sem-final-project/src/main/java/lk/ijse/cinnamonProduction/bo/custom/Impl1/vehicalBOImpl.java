package lk.ijse.cinnamonProduction.bo.custom.Impl1;

import lk.ijse.cinnamonProduction.bo.custom.vehicalBO;
import lk.ijse.cinnamonProduction.dao.DAOFactory;
import lk.ijse.cinnamonProduction.dao.custom.vehicalDAO;
import lk.ijse.cinnamonProduction.dto.vehicalDto;
import lk.ijse.cinnamonProduction.entity.vehical;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class vehicalBOImpl implements vehicalBO {
    vehicalDAO VehicalDAO =  (vehicalDAO) DAOFactory.
            getDaoFactory().getDAO(DAOFactory.DAOTypes.VEHICAL);
    @Override
    public List<vehicalDto> getAllVehical() throws SQLException, ClassNotFoundException {
        List<vehical>vehicals=VehicalDAO.getAll();
        List<vehicalDto>vehicalDtos=new ArrayList<>();
        for (vehical ve:vehicals) {
            vehicalDtos.add(new vehicalDto(ve.getVehicalNo(),
                    ve.getVehicalStatus()
                    ));
        }
        return vehicalDtos;
    }

    @Override
    public boolean saveVehical(vehicalDto dto) throws SQLException, ClassNotFoundException {
        return VehicalDAO.save(new vehical(dto.getVehicalNo(),dto.getVehicalStatus()));
    }

    @Override
    public boolean updateVehical(vehicalDto dto) throws SQLException, ClassNotFoundException {
        return VehicalDAO.update(new vehical(dto.getVehicalNo(),dto.getVehicalStatus()));
    }

    @Override
    public boolean existVehical(String id) throws SQLException, ClassNotFoundException {
        return VehicalDAO.exist(id);
    }

    @Override
    public boolean deleteVehical(String id) throws SQLException, ClassNotFoundException {
      return VehicalDAO.delete(id);
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        return VehicalDAO.generateID();
    }

    @Override
    public vehicalDto searchVehical(String id) throws SQLException, ClassNotFoundException {
        vehical vehical=VehicalDAO.search(id);
        vehicalDto vehicalDto=new vehicalDto(vehical.getVehicalNo(),vehical.getVehicalStatus());
        return vehicalDto;
    }
}
