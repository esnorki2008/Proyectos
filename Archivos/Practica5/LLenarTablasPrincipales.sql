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
 #select * from Location;
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
 
 
 