const database = require('../services/database.js');

const oracledb = require('oracledb');

const baseQuery=`select * from usuario where  nombre = :nombre and correo=:correo`; 
async function find(emp) {
  let query = baseQuery;
  const usuario = Object.assign({}, emp);  
  const result = await database.simpleExecute(query,usuario);
  return result.rows;
} 
module.exports.find = find;
async function DevolverTodo(){
  let query='select * from usuario'
  const result = await database.simpleExecute(query);
  return result.rows;
}
module.exports.DevolverTodo=DevolverTodo;


const createSql =
 `insert into usuario values(:nombre,:correo)`; 
async function create(emp) {
  const usuario = Object.assign({}, emp);
  /*
    usuario.nombre = {
    dir: oracledb.BIND_OUT,
    type: oracledb.DB_TYPE_VARCHAR
  }
  */
 console.log(usuario);
  const result = await database.simpleExecute(createSql, usuario);
 // employee.employee_id = result.outBinds.employee_id[0];
  return usuario;
}
 
module.exports.create = create;

const updateSql =
 `update usuario
  set nombre = :nombre,
    correo = :correo
  where nombre = :nombre`;
 
async function update(emp) {

  const employee = Object.assign({}, emp);
  const result = await database.simpleExecute(updateSql, employee);  
  if (result.rowsAffected && result.rowsAffected === 1) {
    return employee;
  } else {
    return null;
  }
}
 
module.exports.update = update;

	


const deleteSql1 =
 `begin
 
    delete from job_history
    where employee_id = :employee_id;
 
    delete from employees
    where employee_id = :employee_id;
 
    :rowcount := sql%rowcount;
 
  end;`
const deleteSql= 'delete from usuario where nombre =:nombre';
async function del(Usu) {
  /*
  const binds = {
    employee_id: id,
    rowcount: {
      dir: oracledb.BIND_OUT,
      type: oracledb.NUMBER
    }
  }
  */
 const binds = {
  nombre: Usu.nombre,
 }

  const result = await database.simpleExecute(deleteSql, binds);
 


  if(result.rowsAffected==0)
    return null;
  else 
    return Usu;


}
 
module.exports.delete = del;