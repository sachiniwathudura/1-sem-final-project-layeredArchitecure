package lk.ijse.cinnamonProduction.bo;

import lk.ijse.cinnamonProduction.bo.custom.Impl1.*;
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
            CINNAMONGRADES,COMPANY,EMPLOYEE,MACHINE,MERCHANT,SALES,STOCK,VEHICAL,USER
        }
        public SuperBO getBO( BOTypes botypes){
            switch (botypes){
                case CINNAMONGRADES:
                    return new cinnamonGradesBOImpl();
                case COMPANY:
                    return new companyBOImpl();
                case EMPLOYEE:
                    return new employeeManageBOImpl();
                case MACHINE:
                    return new machineBOImpl();
                case MERCHANT:
                    return new merchantBOImpl();
                case SALES:
                    return new salesBOImpl();
                case STOCK:
                    return new stockBOImpl();
                case VEHICAL:
                    return new  vehicalBOImpl();
                case USER:
                    return new userLoginBOImpl();
                default:
                    return null;

            }
        }

    }
