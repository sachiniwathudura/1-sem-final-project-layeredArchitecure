package lk.ijse.cinnamonProduction.bo.custom.Impl1;

import lk.ijse.cinnamonProduction.bo.custom.userLoginBO;
import lk.ijse.cinnamonProduction.dao.DAOFactory;
import lk.ijse.cinnamonProduction.dao.custom.userLoginDAO;
import lk.ijse.cinnamonProduction.dto.userLoginDto;
import lk.ijse.cinnamonProduction.entity.userLogin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class userLoginBOImpl implements userLoginBO {
    userLoginDAO UserLoginDAO = (userLoginDAO) DAOFactory.
            getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public List<userLoginDto> getAllUser() throws SQLException, ClassNotFoundException {
        List<userLogin>userLogins=UserLoginDAO.getAll();
        List<userLoginDto>userLoginDtos=new ArrayList<>();
        for (userLogin loin:userLogins) {
            userLoginDtos.add(new userLoginDto(
                    loin.getUserId(),
                    loin.getUserName(),
                    loin.getUserEmail(),
                    loin.getPassword()
                    ));
        }
        return userLoginDtos;
    }

    @Override
    public boolean saveUser(userLoginDto dto) throws SQLException, ClassNotFoundException {
        return UserLoginDAO.save(new userLogin(dto.getUserId(),dto.getUserName(),dto.getUserEmail(),dto.getPassword()));
    }

    @Override
    public boolean updateUser(userLoginDto dto) throws SQLException, ClassNotFoundException {
        return UserLoginDAO.update(new userLogin(dto.getUserId(),dto.getUserName(),dto.getUserEmail(),dto.getPassword()));
    }

    @Override
    public boolean existUser(String id) throws SQLException, ClassNotFoundException {
        return UserLoginDAO.exist(id);
    }

    @Override
    public boolean deleteUser(String id) throws SQLException, ClassNotFoundException {
        return  UserLoginDAO.delete(id);
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        return UserLoginDAO.generateID();
    }

    @Override
    public userLoginDto searchUser(String id) throws SQLException, ClassNotFoundException {
        userLogin userLogin=UserLoginDAO.search(id);
        userLoginDto userLoginDto=new userLoginDto(userLogin.getUserId(),userLogin.getUserName(),userLogin.getUserEmail(),userLogin.getPassword());
        return userLoginDto;
    }
}
