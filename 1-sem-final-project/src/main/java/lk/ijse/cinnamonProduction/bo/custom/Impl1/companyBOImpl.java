package lk.ijse.cinnamonProduction.bo.custom.Impl1;

import lk.ijse.cinnamonProduction.bo.custom.companyBO;
import lk.ijse.cinnamonProduction.dao.DAOFactory;
import lk.ijse.cinnamonProduction.dao.custom.companyDAO;
import lk.ijse.cinnamonProduction.dto.companyDto;
import lk.ijse.cinnamonProduction.entity.company;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class companyBOImpl implements companyBO {
    companyDAO CompanyDAO = (companyDAO) DAOFactory.getDaoFactory().
            getDAO(DAOFactory.DAOTypes.COMPANY);


    @Override
    public List<companyDto> getAllCompany() throws SQLException, ClassNotFoundException {

     /*   ArrayList<company> company= (ArrayList<lk.ijse.cinnamonProduction.entity.company>) CompanyDAO.getAll();
        ArrayList<companyDto> companyDTOS=new ArrayList<>();
        for (company Company:company) {
            ((ArrayList<companyDto>) companyDTOS).add(Company.getCompanyId(), Company.getCompanyName(), Company.getCompanyEmail());
        }
        return companyDTOS;*/
        List<company>companies=CompanyDAO.getAll();
        List<companyDto>companyDtos=new ArrayList<>();
        for (company Company:companies) {
            companyDtos.add(new companyDto(Company.getCompanyId(),
                    Company.getCompanyName(),
                    Company.getCompanyEmail()
                   ));

        }
        return companyDtos;

    }

    @Override
    public boolean saveCompany(companyDto dto) throws SQLException, ClassNotFoundException {
        return CompanyDAO.save(new company(dto.getCompanyId(),dto.getCompanyName(),dto.getCompanyEmail()));
    }

    @Override
    public boolean updateCompany(companyDto dto) throws SQLException, ClassNotFoundException {
        return CompanyDAO.update(new company(dto.getCompanyId(),dto.getCompanyName(),dto.getCompanyEmail()));
    }

    @Override
    public boolean existCompany(String id) throws SQLException, ClassNotFoundException {
        return CompanyDAO.exist(id);
    }

    @Override
    public boolean deleteCompany(String id) throws SQLException, ClassNotFoundException {
       return CompanyDAO.delete(id);
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        return CompanyDAO.generateID();
    }

    @Override
    public companyDto searchCompany(String id) throws SQLException, ClassNotFoundException {
        company Company = CompanyDAO.search(id);
        companyDto CompanyDto = new companyDto(Company.getCompanyId(), Company.getCompanyName(), Company.getCompanyEmail());
        return CompanyDto;
    }
}
