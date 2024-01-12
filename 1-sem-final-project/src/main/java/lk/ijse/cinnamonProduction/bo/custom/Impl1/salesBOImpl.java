package lk.ijse.cinnamonProduction.bo.custom.Impl1;

import lk.ijse.cinnamonProduction.bo.custom.salesBO;
import lk.ijse.cinnamonProduction.dao.DAOFactory;
import lk.ijse.cinnamonProduction.dao.custom.salesDAO;
import lk.ijse.cinnamonProduction.dto.machineDto;
import lk.ijse.cinnamonProduction.dto.salesDto;
import lk.ijse.cinnamonProduction.entity.machine;
import lk.ijse.cinnamonProduction.entity.sales;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class salesBOImpl implements salesBO {
    salesDAO SalesDAO = (salesDAO) DAOFactory.
            getDaoFactory().getDAO(DAOFactory.DAOTypes.SALES);
    @Override
    public List<salesDto> getAllSales() throws SQLException, ClassNotFoundException {
        List<sales>sales=SalesDAO.getAll();
        List<salesDto>salesDtos=new ArrayList<>();
        for (sales sl:sales) {
            salesDtos.add(new salesDto(
                    sl.getSalesNo(),
                    sl.getDate()

            ));
        }
        return salesDtos;
    }

    @Override
    public boolean saveSales(salesDto dto) throws SQLException, ClassNotFoundException {
        return SalesDAO.save(new sales(dto.getSalesNo(), dto.getDate()));
    }

    @Override
    public boolean updateSales(salesDto dto) throws SQLException, ClassNotFoundException {
        return SalesDAO.update(new sales(dto.getSalesNo(), dto.getDate()));
    }

    @Override
    public boolean existSales(String id) throws SQLException, ClassNotFoundException {
        return SalesDAO.exist(id);
    }

    @Override
    public boolean deleteSales(String id) throws SQLException, ClassNotFoundException {
       return SalesDAO.delete(id);
    }

    @Override
    public String generateIDSales() throws SQLException, ClassNotFoundException {
        return SalesDAO.generateID();
    }

    @Override
    public salesDto searchSales(String id) throws SQLException, ClassNotFoundException {
        sales Sales = SalesDAO.search(id);
        salesDto SalesDto = new salesDto(
                Sales.getSalesNo(),
                Sales.getDate());
        return SalesDto;
    }
}
