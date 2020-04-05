#¿Cuantos Proyectos Iniciaron En El 2011?
select count(*) from Project where transactions_start_year=2011;
#¿Cuál Fue El Proyecto De Mayor Duración?
select project_id as ProjectID,project_title as Nombre,( DATEDIFF(end_actual_isodate,start_actual_isodate) ) as DuracionDias,
start_actual_isodate as FechaInicio,end_actual_isodate as FechaFin
 from Project where Date(end_actual_isodate) != '2050-01-01' group by project_id,project_title,start_actual_isodate,end_actual_isodate  order by 3 desc limit 1;
#¿Cuál Fue El Proyecto De Menor Duración?
select project_id as ProjectID,project_title as Nombre,( DATEDIFF(end_actual_isodate,start_actual_isodate) ) as DuracionDias,
start_actual_isodate as FechaInicio,end_actual_isodate as FechaFin
 from Project where Date(end_actual_isodate) != '2050-01-01' group by project_id,project_title,start_actual_isodate,end_actual_isodate  order by 3 asc limit 1;
#Top 5 países con más proyectos asignados
SELECT C.name
FROM Country_Code C
INNER JOIN Project P ON C.Country_Code=P.recipients;

select recipients from Project;

select count(*) from Project group by recipients_iso3 order by 1 desc limit 5;

