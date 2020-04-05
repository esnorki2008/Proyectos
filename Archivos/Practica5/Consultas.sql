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
SELECT C.name as Pais, count(C.Country_Code) as ProyectosAsignados
FROM Country_Code C
INNER JOIN Project P ON C.Country_Code=P.recipients  group by C.Country_Code
order by 2 desc limit 5
;

#Top 5 países con menos proyectos asignados
SELECT C.name as Pais, count(C.Country_Code) as ProyectosAsignados
FROM Country_Code C
INNER JOIN Project P ON C.Country_Code=P.recipients  group by C.Country_Code
order by 2 asc limit 5
;

# Cual Fue El Proyecto Mas Costoso ?
SELECT P.project_id as IdProyecto, P.project_title as Titulo,
P.total_commitments as TotalDado,P.total_disbursements as TotalDesembolsado, 
P.total_commitments - P.total_disbursements as TotalRestante,
C.name as PaisProyecto
FROM Project P
INNER JOIN  Country_Code C on C.Country_Code=P.recipients
group by P.project_id, P.project_title,P.total_commitments,P.total_disbursements,C.name
order by 3 desc limit 1;


# Cual Fue El Proyecto Menos Costoso ?
SELECT P.project_id as IdProyecto, P.project_title as Titulo,
P.total_commitments as TotalDado,P.total_disbursements as TotalDesembolsado, 
P.total_commitments - P.total_disbursements as TotalRestante,
C.name as PaisProyecto
FROM Project P
INNER JOIN  Country_Code C on C.Country_Code=P.recipients
group by P.project_id, P.project_title,P.total_commitments,P.total_disbursements,C.name
order by 3 asc limit 1;


#Top 5 De Proyectos Mas Costosos 

SELECT P.project_id as IdProyecto, P.project_title as Titulo,
P.total_commitments as TotalDado,P.total_disbursements as TotalDesembolsado, 
P.total_commitments - P.total_disbursements as TotalRestante,
C.name as PaisProyecto
FROM Project P
INNER JOIN  Country_Code C on C.Country_Code=P.recipients
group by P.project_id, P.project_title,P.total_commitments,P.total_disbursements,C.name
order by 3 desc limit 5;

#Costo Total De Todos Los Proyectos
SELECT SUM(P.total_commitments) as TotalDadoATodosLosProyectos, 
SUM(P.total_disbursements) as TotalGastadoDeTodosLosProyectos,
SUM(P.total_commitments-P.total_disbursements) as TotalRestanteDeTodosLosProyectos
FROM Project P;

#Top 3 de las Transacciones Mas Costosas Ri-East Africa Public Health Laboratory Networking Project 
SELECT P.project_id as IdProyecto, P.project_title as NombreProyecto,
T.transaction_isodate as Fecha,T.transaction_id as CorrelativoTransaccion,
T.transaction_value as ValorTransaccion
FROM Project P
INNER JOIN  Transaction T on T.project_id =P.Pro_Id ,
Inner join Currency.hola on t.d
where P.project_id='P111556'
order by 5 desc limit 3 ;



