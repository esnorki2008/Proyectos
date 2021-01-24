const db = require('../database')

/*
    Insertar la tienda que esta solicitando ingresar al ccv
*/
async function insertar(idUsuario, total, fecha){
    let query = `INSERT INTO compra(idUsuario, total, fecha)`+
                `VALUES (${idUsuario}, ${total}, '${fecha}')`;
    let result = await db.ejecutarInsercion(query);
    return result
}
 
async function obtenerCompra(idUsuario, total, fecha){
    let query = `SELECT id FROM compra WHERE idUsuario = ${idUsuario} AND total = ${total} AND fecha = '${fecha}'`
    let id = await db.ejecutarQuery(query);
    console.log(id)
    return id
}

async function insertarDetalleCompra(idCompra, idP, cantidad){
    let query = `INSERT INTO detalle_compra(idCompra, idProducto, cantidad) `+
        `VALUES (${idCompra},${idP},${cantidad});`
    let result = await db.ejecutarInsercion(query);
    return result
}

module.exports.insertar = insertar;
module.exports.obtenerCompra = obtenerCompra;
module.exports.insertarDetalleCompra = insertarDetalleCompra;