package lk.ijse.cinnamonProduction.bo.custom.Impl1;

import lk.ijse.cinnamonProduction.bo.custom.machineBO;
import lk.ijse.cinnamonProduction.dao.DAOFactory;
import lk.ijse.cinnamonProduction.dao.custom.employeeDAO;
import lk.ijse.cinnamonProduction.dao.custom.machineDAO;
import lk.ijse.cinnamonProduction.dto.companyDto;
import lk.ijse.cinnamonProduction.dto.machineDto;
import lk.ijse.cinnamonProduction.entity.company;
import lk.ijse.cinnamonProduction.entity.machine;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class machineBOImpl implements machineBO {
    machineDAO MachineDAO = (machineDAO)  DAOFactory.getDaoFactory().
            getDAO(DAOFactory.DAOTypes.MACHINE);

    @Override
    public List<machineDto> getAllMachine() throws SQLException, ClassNotFoundException {
        List<machine>machines=MachineDAO.getAll();
        List<machineDto>machineDtos=new ArrayList<>();
        for (machine macines:machines) {
            machineDtos.add(new machineDto(
                    macines.getMachineId(),
                    macines.getMachineName(),
                    macines.getMachineStatus()

            ));
        }
        return machineDtos;
    }

    @Override
    public boolean saveMachine(machineDto dto) throws SQLException, ClassNotFoundException {
        return MachineDAO.save(new machine(dto.getMachineId(),dto.getMachineName(),dto.getMachineStatus()));
    }

    @Override
    public boolean updateMachine(machineDto dto) throws SQLException, ClassNotFoundException {
        return MachineDAO.update(new machine(dto.getMachineId(),dto.getMachineName(),dto.getMachineStatus()));
    }

    @Override
    public boolean existMachine(String id) throws SQLException, ClassNotFoundException {
        return MachineDAO.exist(id);
    }

    @Override
    public boolean deleteMachine(String id) throws SQLException, ClassNotFoundException {
       return MachineDAO.delete(id);
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        return MachineDAO.generateID();
    }

    @Override
    public machineDto searchMachine(String id) throws SQLException, ClassNotFoundException {
        machine Machine = MachineDAO.search(id);
        machineDto MachineDto = new machineDto(Machine.getMachineId(), Machine.getMachineName(), Machine.getMachineStatus());
        return  MachineDto;

    }
}
