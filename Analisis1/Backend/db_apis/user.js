const db = require('../database')

/*
    Consulta la base de datos para saber si existe un usuario
    registrado con el correo enviado
*/
async function verificacion(correo){
    try{
        let query = `SELECT * FROM usuario WHERE correo  = '${correo}';`;
        let result = await db.ejecutarQuery(query);
        if(result.length > 0) return true
        return false
    }catch (error) {
        console.log(error)
    }
}

/*
    Consulta para registrar un usuario en la base
*/
async function registro(nombre, apellido, correo, contraseña, telefono, idRol, estado){
    let query = `INSERT INTO usuario (nombre, apellido, correo, contraseña, telefono, idRol, estado) `
                +`VALUES ('${nombre}', '${apellido}', '${correo}', '${contraseña}', ${telefono}, ${idRol}, ${estado});`
    let result = await db.ejecutarInsercion(query);
    return result
}

/*
    Consulta para obtener el dueño de la ultima tienda registrada para aprobación
*/
async function obtenerId(correo){
    let query = `SELECT id FROM usuario WHERE correo  = '${correo}';`;
    let result = await db.ejecutarQuery(query);
    return result[0]
}

/*
    Metodo para actualizar el estado de un usuario y permitirle ingresar al sistema
    por medio del login, en caso de ser dueño de tienda
*/
async function actualizar(id){
    let query = `UPDATE usuario SET estado = 1 WHERE id = ${id.idUsuario}`
    let result = await db.ejecutarInsercion(query);
    return result;
}

/*
    Metodo para obtener el correo de un usuario por medio de su id
*/
async function obtenerCorreo(id){
    let query = `SELECT correo FROM usuario WHERE id = ${id.idUsuario}`
    let result = await db.ejecutarQuery(query);
    return result[0]
}

/*
    Consulta para editar un usuario en la base
*/
async function modificar(id, nombre, apellido, correo, contraseña, telefono, idRol, estado){
    let query = `UPDATE usuario SET nombre ='${nombre}', apellido = '${apellido}', correo = '${correo}', contraseña = '${contraseña}',telefono = ${telefono}, idRol = ${idRol}, estado = ${estado} WHERE id = ${id};`
    let result = await db.ejecutarInsercion(query);
    return result;
}

module.exports.verificacion = verificacion;
module.exports.registro = registro;
module.exports.obtenerId = obtenerId;
module.exports.actualizar = actualizar;
module.exports.modificar = modificar;
module.exports.obtenerCorreo = obtenerCorreo;