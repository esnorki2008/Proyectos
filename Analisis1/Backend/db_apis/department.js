const db = require('../database')

/*
    Consulta para obtener los departamentos de la base
*/
async function obtenerDepartamentos(correo){
    let query = `SELECT * FROM departamento;`;
    let result = await db.ejecutarQuery(query);
    return result
}

/*
    Consulta para registrar un departemento en la base
*/
async function nuevoDepartamento(nombre){
    let query = `INSERT INTO departamento (departamento) VALUES ('${nombre}');`
    let result = await db.ejecutarInsercion(query);
    return result
}

/*
    Consulta para modificar un departemento en la base
*/
async function modificarDepartamento(id, nombre){
    let query = `UPDATE departamento SET departamento = '${nombre}' WHERE id = '${id}';`
    let result = await db.ejecutarInsercion(query);
    return result
}

module.exports.obtenerDepartamentos = obtenerDepartamentos;
module.exports.nuevoDepartamento = nuevoDepartamento;
module.exports.modificarDepartamento = modificarDepartamento;