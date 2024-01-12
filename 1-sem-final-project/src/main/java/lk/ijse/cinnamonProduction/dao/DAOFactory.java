package lk.ijse.cinnamonProduction.dao;

import lk.ijse.cinnamonProduction.dao.custom.Impl1.*;

public class DAOFactory {

    private static DAOFactory daoFactory;
    private DAOFactory(){
    }
    public static DAOFactory getDaoFactory(){
        return (daoFactory==null)?daoFactory
                =new DAOFactory():daoFactory;
    }

    public enum DAOTypes{
        CINNAMONGRADES,COMPANY,EMPLOYEE,MACHINE,MERCHANT,SALES,STOCK,VEHICAL,USER
    }
    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case CINNAMONGRADES:
                return new cinnamonGradesDAOImpl();
            case COMPANY:
                return  new companyDAOImpl();
            case EMPLOYEE:
                return new employeeManagementDAOImpl();
            case MACHINE:
                return new machineDAOImpl();
            case MERCHANT:
                return new registerMerchantDAOImpl();
            case SALES:
                return new salesDAOImpl();
            case STOCK:
                return new stockDAOImpl();
            case VEHICAL:
                return new vehicalDAOImpl();
            case USER:
                return new userLoginDAOImpl();
            default:
                return null;

        }
    }
}
