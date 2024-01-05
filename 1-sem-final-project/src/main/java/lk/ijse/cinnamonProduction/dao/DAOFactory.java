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
        CINNAMONGRADES,COMPANY,EMPLOYEE,MACHINE,MERCHANT,SALES,STOCK,VEHICAL
    }
    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case CINNAMONGRADES:
                return new cinnamonGradesModel();
            case COMPANY:
                return  new companyModel();
            case EMPLOYEE:
                return new employeeManagementModel();
            case MACHINE:
                return new machineModel();
            case MERCHANT:
                return new registerMerchantModel();
            case SALES:
                return new salesModel();
            case STOCK:
                return new stockModel();
            case VEHICAL:
                return new  vehicalModel();
            default:
                return null;

        }
    }
}
