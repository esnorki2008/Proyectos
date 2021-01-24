const db = require('../database')

async function insertar(nombre, precio, imagen, cantidad, descripcion, idTienda, idCategoria){
    let query = `INSERT INTO producto(nombre, precio, imagen, cantidad, descripcion, idTienda, idCategoria)`+
                `VALUES ('${nombre}', ${precio}, '${imagen}', ${cantidad}, '${descripcion}', ${idTienda}, ${idCategoria})`;
    let result = await db.ejecutarInsercion(query);
    return result
}

/*
    Listar los productos que estan en la base
*/
async function listarProductos(){
    let query = `SELECT p.id, p.nombre, p.precio, p.imagen, p.cantidad, p.descripcion, t.nombre as nombreTienda, c.nombre as nombreCategoria FROM producto p`+
    ` INNER JOIN categoria c on p.idCategoria = c.id INNER JOIN tienda t on p.idTienda = t.id;`;
    let result = await db.ejecutarQuery(query);
    return result
}

async function listarProductosporTienda(id){
    let query = `SELECT p.id, p.nombre, p.precio, p.imagen, p.cantidad, p.descripcion, t.nombre as nombreTienda, c.nombre as nombreCategoria FROM producto p`+
    ` INNER JOIN categoria c on p.idCategoria = c.id INNER JOIN tienda t on p.idTienda = t.id WHERE p.idTienda = ${id};`;
    let result = await db.ejecutarQuery(query);
    return result
}

async function modificarProducto(id, nombre, precio, imagen, cantidad, descripcion, idCategoria){
    let query = `UPDATE producto SET nombre = '${nombre}', precio = ${precio}, imagen = '${imagen}', cantidad = ${cantidad}, descripcion = '${descripcion}', idCategoria = ${idCategoria} WHERE id = ${id};`
    let result = await db.ejecutarInsercion(query);
    return result
}

/*
    Verificar cantidad
*/
async function verificar(id, solicitud){
    let query = `SELECT EXISTS(SELECT * FROM producto WHERE id = ${id} and cantidad >= ${solicitud}) as existe;`
    let result = await db.ejecutarQuery(query);
    return result
}

/*
    Actualizar cantidad
*/
async function actualizarCantidad(id, cantidad){
    let query = `UPDATE producto SET cantidad = cantidad - ${cantidad} WHERE id = ${id};`
    let result = await db.ejecutarInsercion(query);
    return result
}

/*
    Verificar cantidad
*/
async function detallesCompraProducto(id){
    let query = `SELECT p.nombre, p.precio, dc.cantidad FROM producto p INNER JOIN detalle_compra dc on p.id = dc.idProducto INNER JOIN pedido p2 on dc.idCompra = p2.idCompra WHERE p2.id = ${id} ;`
    let result = await db.ejecutarQuery(query);
    return result
}


module.exports.insertar = insertar;
module.exports.listarProductos = listarProductos;
module.exports.listarProductosporTienda = listarProductosporTienda;
module.exports.modificarProducto = modificarProducto;
module.exports.verificar = verificar;
module.exports.actualizarCantidad = actualizarCantidad;
module.exports.detallesCompraProducto = detallesCompraProducto;