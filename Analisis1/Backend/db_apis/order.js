const db = require('../database')

async function insertar(idCompra, direccion){
    let query = `INSERT INTO pedido(idCompra, direccion, estado)`+
                `VALUES (${idCompra}, '${direccion}', 0)`;
    let result = await db.ejecutarInsercion(query);
    return result
}

/*
    Aceptar un pedido para ser enviado
*/
async function aceptarPedido(id){
    let query = `UPDATE pedido SET estado = 1 WHERE id = ${id};`;
    let result = await db.ejecutarInsercion(query);
    return result
}

/*
    Pedido entregado
*/
async function entregarPedido(id){
    let query = `UPDATE pedido SET estado = 2 WHERE id = ${id};`;
    let result = await db.ejecutarInsercion(query);
    return result
}

/*
    Pedido rechazado
*/
async function rechazarPedido(id){
    let query = `UPDATE pedido SET estado = 4 WHERE id = ${id};`;
    let result = await db.ejecutarInsercion(query);
    return result
}

async function obtenerCorreo(id){
    let query = `SELECT u.correo FROM usuario u INNER JOIN compra c on u.id = c.idUsuario INNER JOIN pedido p on c.id = p.idCompra WHERE p.id = ${id};`;
    let result = await db.ejecutarQuery(query);
    return result
}


/*
    Listado de los pedidos
*/
async function listarPedidos(){
    let query =  `SELECT p.id, p.idCompra, CONCAT(u.nombre, " ",u.apellido) AS nombreUsuario, p.direccion, p.estado ` +
    `FROM pedido p ` +
    `INNER JOIN compra c ON c.id = p.idCompra ` +
    `INNER JOIN usuario u ON u.id = c.idUsuario;`;
    let result = await db.ejecutarQuery(query);
    return result
}

/*
    Listado de los pedidos
*/
async function listarPedidosEspera(){
    let query =  `SELECT p.id, p.idCompra, CONCAT(u.nombre, " ",u.apellido) AS nombreUsuario, p.direccion, p.estado ` +
    `FROM pedido p ` +
    `INNER JOIN compra c ON c.id = p.idCompra ` +
    `INNER JOIN usuario u ON u.id = c.idUsuario WHERE p.estado = 0;`;
    let result = await db.ejecutarQuery(query);
    return result
}

/*
    Listado de los pedidos por mes y año
*/
async function listarMesAño(mes, year){
    let query =  `SELECT p.id, p.idCompra, CONCAT(u.nombre, " ",u.apellido) AS nombreUsuario, p.direccion, p.estado, c.total ` +
    `FROM pedido p ` +
    `INNER JOIN compra c ON c.id = p.idCompra ` +
    `INNER JOIN usuario u ON u.id = c.idUsuario WHERE MONTH(fecha) = ${mes} AND YEAR(fecha) = ${year};`;
    let result = await db.ejecutarQuery(query);
    return result
}

/*
    Listado de los pedidos por mes y año
*/
async function listarAño(year){
    let query =  `SELECT p.id, p.idCompra, CONCAT(u.nombre, " ",u.apellido) AS nombreUsuario, p.direccion, p.estado, c.total ` +
    `FROM pedido p ` +
    `INNER JOIN compra c ON c.id = p.idCompra ` +
    `INNER JOIN usuario u ON u.id = c.idUsuario WHERE  YEAR(fecha) = ${year};`;
    let result = await db.ejecutarQuery(query);
    return result
}

/*
    Listado de los pedidos de un usuario
*/
async function listaPorUsuario(id){
    let query =  `SELECT p.id, p.idCompra, CONCAT(u.nombre, " ",u.apellido) AS nombreUsuario, p.direccion, p.estado, c.total,c.fecha ` +
    `FROM pedido p ` +
    `INNER JOIN compra c ON c.id = p.idCompra ` +
    `INNER JOIN usuario u ON u.id = c.idUsuario WHERE u.id = ${id};`;
    let result = await db.ejecutarQuery(query);
    return result
}

module.exports.insertar = insertar;
module.exports.aceptarPedido = aceptarPedido;
module.exports.obtenerCorreo = obtenerCorreo;
module.exports.entregarPedido = entregarPedido;
module.exports.rechazarPedido = rechazarPedido;
module.exports.listarPedidos = listarPedidos;
module.exports.listarPedidosEspera = listarPedidosEspera;
module.exports.listarMesAño = listarMesAño;
module.exports.listarAño = listarAño;
module.exports.listaPorUsuario = listaPorUsuario;