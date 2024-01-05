package lk.ijse.cinnamonProduction.bo;

import lk.ijse.cinnamonProduction.dao.SuperDAO;
import lk.ijse.cinnamonProduction.dao.custom.Impl1.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){

    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)?boFactory=
                new BOFactory():boFactory;

        }

        public enum BOTypes{
            CINNAMONGRADES,COMPANY,EMPLOYEE,MACHINE,MERCHANT,SALES,STOCK,VEHICAL
        }
        public SuperDAO getBO( BOTypes botypes){
            switch (botypes){
                case CINNAMONGRADES:
                    return new cinnamonGradesModel();
               // case COMPANY:
                  //  return new companyModel();
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
