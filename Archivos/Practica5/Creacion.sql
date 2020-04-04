#drop database Practica;
create database Practica;


use Practica;



/*--==========================Creacion De Tablas==========*/
/*Transaction*/
create table Transaction(Transaction_Id int primary key auto_increment,Transaction_Code varchar(100),Transaction_Isodate date,Transaction_Year int,Transaction_Value_Code varchar(100),Transaction_Currency_Id int,Transaction_Value int,Project_Id1 int);
/*Level_1A*/
create table Level_1A(Project_Id int primary key auto_increment,Project_Geoname_Id varchar(100),Geoname_Id int,Transaction_Start_Year int,Transaction_End_Year int,
Even_Split_Commitments float,Even_Split_disbursement float);




/*Country_Code*/
create table Country_Code(Country_Code int primary key auto_increment not null,name varchar(100),iso2 varchar(100),name_name varchar(100),name_aiddata_code varchar(100),name_aiddata_name varchar(100),name_cow_alpha varchar(100),name_cow_numeric varchar(100),
name_fao_code varchar(100),name_fips varchar(100),name_geoname_id varchar(100),name_imf_code varchar(100),
name_iso2 varchar(100),name_iso3 varchar(100),name_iso_numeric varchar(100),name_oecd_code varchar(100),name_oecd_name varchar(100),
name_un_code varchar(100),name_wb_code varchar(100));
#Drop table Country_Code;
/*Location*/
create table Location(Location_Id int primary key auto_increment,Location_Type_Code varchar(100),Location_Type_Name varchar(100));
#drop table Location;
/*Geoname*/
create table Geoname(GeoId int primary key auto_increment,geoname_id int ,place_name varchar(100),latitude float,longitude float,location_type_code int,location_type_name varchar(100),gazetteer_adm_code varchar(100),gazetteer_adm_name varchar(200),location_class varchar(100),geografic_exactness varchar(100));
alter table Geoname add foreign key (location_type_code) references Location(Location_id);
#drop table Geoname;
/*Currency*/
create table Currency (Currency_Id int primary key auto_increment,Currency_Description varchar(20));
insert into Currency values(default,'USD');
/*TypeStatus*/ 
create table Type_Status(Id_Status int primary key auto_increment,Description varchar(100));
insert into Type_Status values(default,'completion');
insert into Type_Status values(default,'implementation');


/*Project*/
create table Project(Pro_Id int primary key auto_increment,project_id varchar(200),is_geocoded int,project_title varchar(200),start_actual_isodate date null,end_actual_isodate date null,
donors varchar(200),donors_Iso3 varchar(200),recipients int,recipients_iso3 varchar(200),ad_sector_codes varchar(200),ad_sector_names varchar(200),status int,
transactions_start_year int,transactions_end_year int,total_commitments float,total_disbursements float);

alter table Project add foreign key (recipients) references Country_Code(Country_Code);
alter table Project add foreign key (status) references Type_Status(Id_Status);
#Drop table Project;

/*--==========================Constraint=====================*/
/*==============LLaves Foraneas========*/
/*Transaction*/
alter table Transaction add foreign key (Transaction_Currency_Id) references Currency(Currency_Id);
alter table Transaction add foreign key (Project_Id1) references Project(Project_Id);
/*Level_1A*/
alter table Level_1A add foreign key (Project_Id) references Project(Project_Id);
alter table Level_1A add foreign key (Geoname_Id) references Geoname(Geoname_Id);
