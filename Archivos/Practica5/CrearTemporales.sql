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