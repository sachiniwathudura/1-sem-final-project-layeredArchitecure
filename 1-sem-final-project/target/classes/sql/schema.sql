create database if not exists cinnamonProduction;

use cinnamonProduction;

create table user(
                     userId varchar(35) primary key,
                     userName varchar(155) not null,
                     userTeleNo varchar(15) not null
);

create table merchant(
                         merchantId varchar(35) primary key,
                         merchantName varchar(155) not null,
                         merchantTeleNo varchar(15) not null,

                         homeNo varchar(30) not null,
                         street varchar(100) not null,
                         town varchar(100) not null,
                             merchantCategory varchar(100)
);

create table appointment(
                            appoinmentNo varchar(35) primary key,
                            date date not null,
                            time double not null,

                            merchantId varchar(35),

                            foreign key(merchantId) references merchant(merchantId) on update cascade on delete cascade
);

create table registration(
                             regId varchar (35) primary key,
                             date date not null,
                             userId varchar(35),
                             merchantId varchar(35),
                             foreign key(userId) references user (userId) on update cascade on delete cascade,
                             foreign key(merchantId) references merchant(merchantId) on update cascade on delete cascade
);
create table cinnamonGrade(
                              cGradeId varchar(100) primary key,
                              cGradeName varchar(100) not null,
                              c1KgPrice double not null

);

create table stock (
    itemId       varchar(100) primary key,
    itemName     varchar(100) not null,
    itemQty      int(10) not null,
    itemCategory varchar(100) not null



);

create table stockDetail(
                            merchantId varchar(35),
                            itemId varchar(100),
                            foreign key(merchantId) references merchant(merchantId) on update cascade on delete cascade,
                            foreign key(itemId) references stock(itemId) on update cascade on delete cascade
);

create table machineDetails(
                               machineId varchar(35) primary key,
                               machineName varchar(100),
                               machineStatus varchar(100)

);

create table vehical(
                        vehicalNo varchar(35) primary key,
                        vehicalStatus varchar(100)

);

create table sales(
                      salesNo varchar(35) primary key,
                      date date not null

);

create table company(
                        companyId varchar(35) primary key,
                        companyName varchar(100),
                        companyEmail varchar(100)


);

create table companyDetails(
                     companyId varchar(35),
                     salesNo varchar(35),
                     foreign key(companyId) references company(companyId) on update cascade on delete cascade,
                     foreign key(salesNo) references sales(salesNo) on update cascade on delete cascade
);

alter table user add password varchar(35) not null;
alter table user add email varchar(100) not null;


create table employee(
                          empId varchar (35) primary key,
                          empName varchar(100) not null,
                          empAddress varchar(100) not null,
                          empTeleNo varchar(15),
                          empStatus varchar(100)

);

drop table machineDetails;

create table machineDetails(
                               machineId varchar(35) primary key,
                               machineName varchar(100),
                               machineStatus varchar(100)
);


create table stockDetail(
                            merchantId varchar(35),
                            itemId varchar(100),
                            QtyOnStock double(5,3),
                            foreign key(merchantId) references merchant(merchantId) on update cascade on delete cascade,
                            foreign key(itemId) references stock(itemId) on update cascade on delete cascade
);


