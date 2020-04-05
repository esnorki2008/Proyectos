use Practica;
#=============================Temporal Pais
create temporary table TemporalCountry(Campo1 varchar(100),Campo2 varchar(100),Campo3 varchar(100),
Campo4 varchar(100),Campo5 varchar(100),Campo6 varchar(100),Campo7 varchar(100),Campo8 varchar(100),
Campo9 varchar(100),Campo10 varchar(100),Campo11 varchar(100),Campo12 varchar(100),Campo13 varchar(100),
Campo14 varchar(100),Campo15 varchar(100),Campo16 varchar(100),Campo17 varchar(100),Campo18 varchar(100));
#drop temporary table TemporalCountry;
#==============================Temporal Location
create temporary table TemporalLocation(Campo1 varchar(100),Campo2 varchar(100));
#drop  temporary table TemporalLocation;
#=============================Temporal GeoName
create temporary table TemporalGeoName(geoname_id varchar (200),place_name varchar (200),latitude varchar (200)
,longitude varchar (200),location_type_code varchar (200),location_type_name varchar (200)
,gazetteer_adm_code varchar (200),gazetteer_adm_name varchar (200),location_class varchar (200)
,geographic_exactness varchar (200));
#drop temporary table TemporalGeoName;
#===============================Temporal Project
create temporary table TemporalProject(project_id varchar(200),is_geocoded varchar(200),project_title varchar(200),
start_actual_isodate varchar(200),end_actual_isodate varchar(200),donors varchar(200),donors_iso3 varchar(200),
recipients varchar(200),recipients_iso3 varchar(200),ad_sector_codes varchar(200),ad_sector_names varchar(200),
status varchar(200),transactions_start_year varchar(200),transactions_end_year varchar(200),total_commitments varchar(200),
total_disbursements varchar(200));
#drop temporary table TemporalProject;
#===============================Level_1A 
create  temporary table TemporalLevel_1A(
project_id varchar(200),project_location_id varchar(200),geoname_id varchar(200),
transactions_start_year varchar(200),transactions_end_year varchar(200),
even_split_commitments varchar(200),even_split_disbursements varchar(200)
);
#drop temporary table TemporalLevel_1A;
#===============================Transaction
create  temporary table TemporalTransaction(
transaction_id varchar(200),project_id varchar(200),transaction_isodate varchar(200),transaction_year varchar(200),
transaction_value_code varchar(200),transaction_currency varchar(200),transaction_value varchar(200)
);
select * from TemporalTransaction;
#drop temporary table TemporalTransaction;



LOAD DATA INFILE "/var/lib/mysql-files/country_codes.tsv"
INTO TABLE TemporalCountry 
COLUMNS TERMINATED BY '	'
#OPTIONALLY ENCLOSED BY '"'
#ESCAPED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES
#(Campo1,Campo2,Campo3,Campo4,Campo5,Campo6,Campo7,Campo8,Campo9,Campo10,
#Campo11,Campo12,Campo13,Campo14,Campo15,Campo16,Campo17,Campo18) 
;

#select * from TemporalCountry;

LOAD DATA INFILE "/var/lib/mysql-files/geonames.csv"
INTO TABLE TemporalGeoName 
COLUMNS TERMINATED BY ','
OPTIONALLY ENCLOSED BY '"'
#ESCAPED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES
#(Campo1,Campo2,Campo3,Campo4,Campo5,Campo6,Campo7,Campo8,Campo9,Campo10,
#Campo11,Campo12,Campo13,Campo14,Campo15,Campo16,Campo17,Campo18) 
;
#select count( *) from TemporalGeoName;


LOAD DATA INFILE "/var/lib/mysql-files/level_1a.csv"
INTO TABLE TemporalLevel_1A
COLUMNS TERMINATED BY ','

OPTIONALLY ENCLOSED BY '"'
#ESCAPED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES

#(Campo1,Campo2,Campo3,Campo4,Campo5,Campo6,Campo7,Campo8,Campo9,Campo10,
#Campo11,Campo12,Campo13,Campo14,Campo15,Campo16,Campo17,Campo18) 
;
#select * from TemporalLevel_1A;


LOAD DATA INFILE "/var/lib/mysql-files/locations.csv"
INTO  TABLE  TemporalLocation 
COLUMNS TERMINATED BY '	'
OPTIONALLY ENCLOSED BY '"'
#ESCAPED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES
#(Campo1,Campo2,Campo3,Campo4,Campo5,Campo6,Campo7,Campo8,Campo9,Campo10,
#Campo11,Campo12,Campo13,Campo14,Campo15,Campo16,Campo17,Campo18) 
;

#Drop table TemporalLocation;


LOAD DATA INFILE "/var/lib/mysql-files/projects.csv"
INTO TABLE TemporalProject 
COLUMNS TERMINATED BY ','

OPTIONALLY ENCLOSED BY '"'
#ESCAPED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES

#(Campo1,Campo2,Campo3,Campo4,Campo5,Campo6,Campo7,Campo8,Campo9,Campo10,
#Campo11,Campo12,Campo13,Campo14,Campo15,Campo16,Campo17,Campo18) 
;
#select * from TemporalGeoName;


LOAD DATA INFILE "/var/lib/mysql-files/transactions.csv"
INTO  TABLE  TemporalTransaction 
COLUMNS TERMINATED BY ','
OPTIONALLY ENCLOSED BY '"'
#ESCAPED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES
#(Campo1,Campo2,Campo3,Campo4,Campo5,Campo6,Campo7,Campo8,Campo9,Campo10,
#Campo11,Campo12,Campo13,Campo14,Campo15,Campo16,Campo17,Campo18) 
;

#Drop table TemporalTransaction;






#drop database Practica;
#create database Practica;


use Practica;



/*--==========================Creacion De Tablas==========*/





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
#drop table Currency;
/*TypeStatus*/ 
create table Type_Status(Id_Status int primary key auto_increment,Description varchar(100));
insert into Type_Status values(default,'completion');
insert into Type_Status values(default,'implementation');
#drop table TypeStatus;

/*Project*/
create table Project(Pro_Id int primary key auto_increment,project_id varchar(200),is_geocoded int,project_title varchar(200),start_actual_isodate date null,end_actual_isodate date null,
donors varchar(200),donors_Iso3 varchar(200),recipients int,recipients_iso3 varchar(200),ad_sector_codes varchar(200),ad_sector_names varchar(200),status int,
transactions_start_year int,transactions_end_year int,total_commitments float,total_disbursements float);
alter table Project add foreign key (recipients) references Country_Code(Country_Code);
alter table Project add foreign key (status) references Type_Status(Id_Status);
#Drop table Project;
/*Level_1A*/
create table Level_1A(levelid int primary key auto_increment,project_id int ,
project_location_id varchar(200),geoname_id int,transaction_start_year int,
transaction_end_year int,even_split_commitments varchar(100),
even_split_disbursement varchar(100));
alter table Level_1A add foreign key (project_id) references Project(Pro_Id);
alter table Level_1A add foreign key (Geoname_Id) references Geoname(GeoId);
#drop table Level_1A;

/*Transaction*/
create table Transaction(TraId int primary key auto_increment,transaction_id varchar(200),project_id int,
transaction_isodate date,transaction_year int,transaction_value_code varchar(100),transaction_currency int,transaction_value float);
alter table Transaction add foreign key (transaction_currency) references Currency(Currency_Id);
alter table Transaction add foreign key (project_id) references Project(Pro_Id);
#drop table Transaction;


use Practica;
#===================================Country_Code====================
INSERT INTO Country_Code (
name,iso2,name_name,name_aiddata_code,name_aiddata_name,name_cow_alpha,name_cow_numeric,
name_fao_code,name_fips,name_geoname_id,name_imf_code,
name_iso2,name_iso3,name_iso_numeric,name_oecd_code,name_oecd_name,
name_un_code,name_wb_code
) SELECT * FROM   TemporalCountry where lower(Campo1) != "unspecified";

#select * from Country_Code;

#drop table Country_Code;

#====================================LOCATIONS===========================
INSERT INTO Location (Location_Type_Code,Location_Type_Name)
SELECT * FROM   TemporalLocation ;
 
 #drop table Location;
 #select count(*) from Location;
 #====================================Geonames==========================

INSERT INTO Geoname 
(geoname_id,place_name,latitude,longitude,location_type_code,
location_type_name,gazetteer_adm_code,gazetteer_adm_name,
location_class,geografic_exactness)

select 
CAST(T.geoname_id AS UNSIGNED),T.place_name,
CAST(T.latitude as float),CAST(T.longitude as float),CAST(L.Location_Id AS UNSIGNED),
L.location_type_name,T.gazetteer_adm_code,T.gazetteer_adm_name,
T.location_class ,T.geographic_exactness 
from 
TemporalGeoName as T cross join
Location as L
where  
(T.location_type_code  =L.location_type_code) ;
#Select * from Geoname;
 #================================================Project
INSERT INTO Project 
(project_id ,is_geocoded,project_title,start_actual_isodate,end_actual_isodate,donors,donors_iso3,recipients,recipients_iso3,
ad_sector_codes,ad_sector_names,status,transactions_start_year,transactions_end_year,total_commitments,total_disbursements)
SELECT 
Temp.project_id ,cast(is_geocoded as unsigned ),project_title,if(start_actual_isodate ="",'01/01/2030/',str_to_date(start_actual_isodate ,'%d/%m/%Y')),
if(end_actual_isodate ="",'2050-01-01',str_to_date(end_actual_isodate ,'%d/%m/%Y')),Temp.donors,donors_Iso3 ,
(select country_code from Country_Code c where c.name_iso3=Temp.recipients_iso3 limit 1)
,recipients_iso3 ,ad_sector_codes ,ad_sector_names ,
(select id_status from Type_Status t where t.description=status limit 1),
cast(transactions_start_year as unsigned),cast(transactions_end_year as unsigned),
cast(total_commitments as float),cast(total_disbursements as float)
from TemporalProject Temp;

#==================================================Level_1A
create temporary table DGeo(geoname_id int primary key,codigo int,valor int);
insert into DGeo(geoname_id,codigo) select geoname_id,GeoId from Geoname 
on duplicate key update valor = 1;



create temporary table DPro(project_id varchar(100) primary key,codigo int,valor int);
insert into DPro(project_id,codigo) select project_id,Pro_Id from Project
on duplicate key update valor = 1;

insert into Level_1A(
project_id,project_location_id,geoname_id,transaction_start_year,transaction_end_year,even_split_commitments,even_split_disbursement
)
select 
(select  codigo  from DPro where project_id=T.project_id limit 1)
,T.project_location_id
,(select codigo from DGeo where geoname_id=T.geoname_id limit 1)
,cast(T.transactions_start_year as unsigned)
,cast(T.transactions_end_year as unsigned)
,(T.even_split_commitments)
,(T.even_split_disbursements)
from TemporalLevel_1A T ;


#=======================================Transaction
insert into Transaction(
transaction_id ,project_id ,
transaction_isodate ,
transaction_year ,transaction_value_code ,
transaction_currency ,transaction_value 
)
select T.transaction_id,
(select codigo  from DPro where project_id=T.project_id limit 1),
str_to_date(T.transaction_isodate ,'%d/%m/%Y'),cast(T.transaction_year as unsigned),
T.transaction_value_code,
(Select Currency.Currency_Id from Currency where Currency.Currency_Description=T.transaction_currency),
cast(T.transaction_value as float)
from TemporalTransaction T;

drop temporary table DGeo;
drop temporary table DPro;


