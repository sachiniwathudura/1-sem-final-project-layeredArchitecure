package lk.ijse.cinnamonProduction.dto;

import lombok.*;
@NoArgsConstructor
@Data
@AllArgsConstructor

public class employeeManagementDTo {
    private String empId;
    private String empName;
    private String empAddress;
    private String empTeleNo;
    private String empStatus;


}

  /*  empId varchar (35) primary key,
    empName varchar(100) not null,
        empAddress varchar(100) not null,
        empTeleNo varchar(15),
        empStatus varchar(100),
        userId varchar(35),*/
