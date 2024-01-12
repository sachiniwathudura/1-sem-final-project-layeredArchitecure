package lk.ijse.cinnamonProduction.bo.custom.Impl1;

import lk.ijse.cinnamonProduction.bo.custom.employeeManageBO;
import lk.ijse.cinnamonProduction.dao.DAOFactory;
import lk.ijse.cinnamonProduction.dao.custom.companyDAO;
import lk.ijse.cinnamonProduction.dao.custom.employeeDAO;
import lk.ijse.cinnamonProduction.dto.employeeManagementDTo;
import lk.ijse.cinnamonProduction.dto.machineDto;
import lk.ijse.cinnamonProduction.entity.employee;
import lk.ijse.cinnamonProduction.entity.machine;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class employeeManageBOImpl implements employeeManageBO {

    employeeDAO EmployeeDAO = (employeeDAO)  DAOFactory.getDaoFactory().
            getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
    public List<employeeManagementDTo> getAllEmployee() throws SQLException, ClassNotFoundException {
        List<employee>employees=EmployeeDAO.getAll();
        List<employeeManagementDTo>employeeDtos=new ArrayList<>();
        for (employee emplo:employees) {
            employeeDtos.add(new employeeManagementDTo(
                    emplo.getEmpId(),
                    emplo.getEmpName(),
                    emplo.getEmpAddress(),
                    emplo.getEmpTeleNo(),
                    emplo.getEmpStatus()

            ));

        }
        return employeeDtos;
    }

    @Override
    public boolean saveEmployee(employeeManagementDTo dto) throws SQLException, ClassNotFoundException {
        return EmployeeDAO.save(new employee(dto.getEmpId(),dto.getEmpName(),dto.getEmpAddress(),dto.getEmpTeleNo(),dto.getEmpStatus()));
    }

    @Override
    public boolean updateEmployee(employeeManagementDTo dto) throws SQLException, ClassNotFoundException {
        return EmployeeDAO.update(new employee(dto.getEmpId(),dto.getEmpName(),dto.getEmpAddress(),dto.getEmpTeleNo(),dto.getEmpStatus()));
    }

    @Override
    public boolean existEmployee(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
       return EmployeeDAO.delete(id);
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public employeeManagementDTo searchEmployee(String id) throws SQLException, ClassNotFoundException {
        employee Employee = EmployeeDAO.search(id);
        employeeManagementDTo EmployeeManagementDTo = new employeeManagementDTo(
                Employee.getEmpId(),
                Employee.getEmpName() ,
                Employee.getEmpAddress(),
                Employee.getEmpTeleNo(),
                Employee.getEmpStatus());
        return EmployeeManagementDTo;
    }
}
