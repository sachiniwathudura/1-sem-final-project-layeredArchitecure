package lk.ijse.cinnamonProduction.bo.custom.Impl1;

import lk.ijse.cinnamonProduction.bo.custom.cinnamonGradesBO;
import lk.ijse.cinnamonProduction.dao.DAOFactory;
import lk.ijse.cinnamonProduction.dao.custom.cinnamonGradesDAO;
import lk.ijse.cinnamonProduction.dto.cinnamonGradesDto;
import lk.ijse.cinnamonProduction.entity.cinnamonGrades;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class cinnamonGradesBOImpl implements cinnamonGradesBO {

    cinnamonGradesDAO CinnamonGradesDAO =
           (cinnamonGradesDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CINNAMONGRADES);
    @Override
    public List<cinnamonGradesDto> getAllCinnamonGrades() throws SQLException, ClassNotFoundException {
        List<cinnamonGrades>cinnamonGrades=CinnamonGradesDAO.getAll();
        List<cinnamonGradesDto>cinnamonGradesDtos=new ArrayList<>();
        for (cinnamonGrades cinaman:cinnamonGrades) {
            cinnamonGradesDtos.add(new cinnamonGradesDto(
                    cinaman.getCGradeId(),
                    cinaman.getCGradeName(),
                    cinaman.getC1KgPrice()
            ));
        }
        return cinnamonGradesDtos;
    }

    @Override
    public boolean saveCinnamonGrades(cinnamonGradesDto dto) throws SQLException, ClassNotFoundException {
        return CinnamonGradesDAO.save(new cinnamonGrades(
                        dto.getCGradeId(),
                        dto.getCGradeName(),
                        dto.getC1KgPrice()));
    }

    @Override
    public boolean updateCinnamonGrades(cinnamonGradesDto dto) throws SQLException, ClassNotFoundException {
        return CinnamonGradesDAO.update(new cinnamonGrades
                (dto.getCGradeId(),dto.getCGradeName(),dto.getC1KgPrice()));
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return CinnamonGradesDAO.exist(id);
    }

    @Override
    public boolean deleteCinnamonGrades(String id) throws SQLException, ClassNotFoundException {

        return CinnamonGradesDAO.delete(id);
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        return CinnamonGradesDAO.generateID();
    }

    @Override
    public cinnamonGradesDto searchCinnamonGrades(String id) throws SQLException, ClassNotFoundException {
        cinnamonGrades CinnamonGrades = CinnamonGradesDAO.search(id);
        cinnamonGradesDto cinnamonGradesDto=new cinnamonGradesDto(
                CinnamonGrades.getCGradeId(),
                CinnamonGrades.getCGradeName(),
                CinnamonGrades.getC1KgPrice());
        return cinnamonGradesDto;
    }
}
