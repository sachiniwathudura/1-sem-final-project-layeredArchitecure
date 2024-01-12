package lk.ijse.cinnamonProduction.bo.custom.Impl1;

import lk.ijse.cinnamonProduction.bo.custom.stockBO;
import lk.ijse.cinnamonProduction.dao.DAOFactory;
import lk.ijse.cinnamonProduction.dao.custom.satockDAO;
import lk.ijse.cinnamonProduction.dto.stockDto;
import lk.ijse.cinnamonProduction.entity.stock;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class stockBOImpl implements stockBO {
    satockDAO StockDAO = (satockDAO) DAOFactory.
            getDaoFactory().getDAO(DAOFactory.DAOTypes.STOCK);
    @Override
    public List<stockDto> getAllStock() throws SQLException, ClassNotFoundException {
        List<stock>stocks=StockDAO.getAll();
        List<stockDto>stockDtos=new ArrayList<>();
        for (stock st:stocks) {
            stockDtos.add(new stockDto(st.getItemId(),st.getItemName(),st.getItemQty(),st.getItemCategory()));
        }
        return stockDtos;
    }

    @Override
    public boolean saveStock(stockDto dto) throws SQLException, ClassNotFoundException {
        return StockDAO.save(new stock(dto.getItemId(),dto.getItemName(),dto.getItemQty(),dto.getItemCategory()));
    }

    @Override
    public boolean updateStock(stockDto dto) throws SQLException, ClassNotFoundException {
        return StockDAO.update(new stock(dto.getItemId(),dto.getItemName(),dto.getItemQty(),dto.getItemCategory()));
    }

    @Override
    public boolean existStock(String id) throws SQLException, ClassNotFoundException {
        return StockDAO.exist(id);
    }

    @Override
    public boolean deleteStock(String id) throws SQLException, ClassNotFoundException {
        return StockDAO.delete(id);
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        return StockDAO.generateID();
    }

    @Override
    public stockDto searchStock(String id) throws SQLException, ClassNotFoundException {
     //   stock Stock = new StockDAO.search(id);
        stock stock=StockDAO.search(id);
        stockDto stockDto=new stockDto(
                stock.getItemId(),
                stock.getItemName(),
                stock.getItemQty(),
                stock.getItemCategory());
        return  stockDto;
    }
}
