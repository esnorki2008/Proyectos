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
 #================================================Project
 INSERT INTO Project 
 (project_id ,is_geocoded,project_title,start_actual_isodate,end_actual_isodate,donors,donors_iso3,recipients,recipients_iso3,
 ad_sector_codes,ad_sector_names,status,transactions_start_year,transactions_end_year,total_commitments,total_disbursements)
SELECT 
TemporalProject.project_id ,cast(is_geocoded as unsigned ),project_title,if(start_actual_isodate ="",'01/01/2030/',str_to_date(start_actual_isodate ,'%d/%m/%Y')),
if(end_actual_isodate ="",'2050-01-01',str_to_date(start_actual_isodate ,'%d/%m/%Y')),TemporalProject.donors,donors_Iso3 ,Country_Code.Country_Code,
recipients_iso3 ,ad_sector_codes ,ad_sector_names ,Type_Status.Id_Status,
cast(transactions_start_year as unsigned),cast(transactions_end_year as unsigned),
cast(total_commitments as float),cast(total_disbursements as float)
from TemporalProject,Country_Code, Type_Status
where (lower(TemporalProject.status) = lower(Type_Status.Description)) AND
        (TemporalProject.recipients_iso3 = Country_Code.name_iso3);


select count(*) from Geoname;

select count(geoname_id ),geoname_id  from Geoname group by geoname_id ;
