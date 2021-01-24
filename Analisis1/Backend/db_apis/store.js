const db = require('../database')

/*
    Insertar la tienda que esta solicitando ingresar al ccv
*/

async function insertar(nombre, slogan, idUsuario, imagen){
    console.log(idUsuario)
    let query = `INSERT INTO tienda(nombre, slogan, idUsuario,imagen, estado)`+
                `VALUES ('${nombre}', '${slogan}', ${idUsuario.id}, '${imagen}', 0)`;
    let result = await db.ejecutarInsercion(query);
    return result
}

/*
    Obtiene todas las tiendas que estan pendientes de ser aceptadas por el administrador
    del sistema.
*/
async function tiendas(){
    let query = `SELECT t.id, t.nombre, t.slogan, t.estado, u.id as idU, u.nombre as nombreU, u.apellido, u.correo, u.telefono FROM tienda t INNER JOIN usuario u on t.idUsuario = u.id WHERE t.estado = 0;`;
    let result = await db.ejecutarQuery(query);
    return result;
}

/*
    Actualizar el estado de una tienda, en caso de ser aprobada o rechazada
*/
async function actualizar(id, estado){
    let query = `UPDATE tienda SET estado = ${estado} WHERE id = ${id}`
    let result = await db.ejecutarInsercion(query);
    return result;
}

/*
    Actualizar los datos de la tienda
*/
async function actualizarDatos(id, nombre, slogan, imagen){
    console.log(slogan)
    let query = `UPDATE tienda SET nombre = '${nombre}', slogan = '${slogan}', imagen = '${imagen}' WHERE id = ${id}`
    let result = await db.ejecutarInsercion(query);
    return result;
}

/*
    Obtención del dueño de una tienda por medio de su id
*/
async function obtenerDueño(id){
    let query = `SELECT idUsuario FROM tienda WHERE id = ${id}`
    let result = await db.ejecutarQuery(query);
    return result[0]
}

async function obtenerDatosTienda(idUsuario){
    let query = `SELECT * FROM tienda WHERE idUsuario = ${idUsuario};`
    let result = await db.ejecutarQuery(query);
    return result[0]
}

async function agregarDepartamentoTienda(idTienda, idDepartamento){
    let query = `INSERT INTO departamento_tienda(idDepartamento, idTienda) VALUES(${idDepartamento},${idTienda});`
    let result = await db.ejecutarInsercion(query);
    return result;
}

/*
    Obtener los departamentos a los que pertenece una tienda
*/
async function obtenerDepartamentosDeTienda(id){
    let query = `SELECT * FROM departamento_tienda WHERE idTienda = ${id};`;
    let result = await db.ejecutarQuery(query);
    return result
}

/*
    Eliminar una tienda de un departamento
*/
async function eliminarTiendaDeDepartamento(id, idDepto){
    let query = `DELETE FROM departamento_tienda WHERE idTienda = ${id} AND idDepartamento = ${idDepto};`;
    let result = await db.ejecutarQuery(query);
    return result
}

/*
    Obtener las tiendas de un departamento
*/
async function obtenerTiendasporDepartamentos(id){
    let query = `SELECT t.id, t.nombre, t.slogan, t.imagen imagen FROM departamento_tienda INNER JOIN tienda t on departamento_tienda.idTienda = t.id WHERE idDepartamento = ${id};`;
    let result = await db.ejecutarQuery(query);
    return result
}

async function listarTiendas(){
    let query = `SELECT t.id, t.nombre as nombreTienda, t.slogan, t.imagen as imagenTienda, u.id as idUsuario, u.nombre as nombreUsuario, u.correo FROM tienda t INNER JOIN usuario u on t.idUsuario = u.id WHERE t.estado = 1`
    let result = await db.ejecutarQuery(query);
    return result
}

module.exports.insertar = insertar;
module.exports.tiendas = tiendas;
module.exports.actualizar = actualizar;
module.exports.actualizarDatos = actualizarDatos;
module.exports.listarTiendas = listarTiendas;
module.exports.obtenerDueño = obtenerDueño;
module.exports.obtenerDatosTienda = obtenerDatosTienda;
module.exports.agregarDepartamentoTienda = agregarDepartamentoTienda;
module.exports.obtenerDepartamentosDeTienda = obtenerDepartamentosDeTienda;
module.exports.eliminarTiendaDeDepartamento = eliminarTiendaDeDepartamento;
module.exports.obtenerTiendasporDepartamentos = obtenerTiendasporDepartamentos;