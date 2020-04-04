use Practica;
#=============================Temporal Pais
create temporary table TemporalCountry(Campo1 varchar(100),Campo2 varchar(100),Campo3 varchar(100),
Campo4 varchar(100),Campo5 varchar(100),Campo6 varchar(100),Campo7 varchar(100),Campo8 varchar(100),
Campo9 varchar(100),Campo10 varchar(100),Campo11 varchar(100),Campo12 varchar(100),Campo13 varchar(100),
Campo14 varchar(100),Campo15 varchar(100),Campo16 varchar(100),Campo17 varchar(100),Campo18 varchar(100));
#drop temporary table TemporalCountry;
#==============================Temporal Location
create temporary table TemporalLocation(Campo1 varchar(100),Campo2 varchar(100));
#drop  temporary table TemporalLocations;
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

