const categoria = require('../db_apis/category')
const producto = require('../db_apis/product')

/* 
    Esta función es la utilizada para crear productos
*/
async function crearProducto(req, res, next){ 
    try{
        const nombre = req.body.nombre
        const precio = req.body.precio
        const imagen = req.body.imagen
        const cantidad = req.body.cantidad
        const descripcion = req.body.descripcion
        const idTienda = req.body.idTienda
        const idCategoria = req.body.idCategoria
        let resultado = await producto.insertar(nombre, precio, imagen, cantidad, descripcion, idTienda, idCategoria)
        if(resultado){
            res.status(201).json({msg:"Producto creado con éxito"});
        }else{
            res.status(400).end();
        }
    }catch(error){
        return('error');
    }
}

async function obtenerProductos(req, res, next){
    try{
        let resultado = await producto.listarProductos()
        console.log(resultado)
        if(resultado){
            res.status(200).json(resultado)
        }else{
            res.status(400).end();
        }
    }catch(error){
        return('error');
    }
}

async function obtenerProductosTienda(req, res, next){
    try{
        let resultado = await producto.listarProductosporTienda(req.body.id)
        console.log(resultado)
        if(resultado){
            res.status(200).json(resultado)
        }else{
            res.status(400).end();
        }
    }catch(error){
        return('error');
    }
}

/* 
    Esta función es la utilizada para modificar productos
*/
async function modificarProducto(req, res, next){ 
    try{
        const id = req.body.id
        const nombre = req.body.nombre
        const precio = req.body.precio
        const imagen = req.body.imagen
        const cantidad = req.body.cantidad
        const descripcion = req.body.descripcion
        const idTienda = req.body.idTienda
        const idCategoria = req.body.idCategoria
        console.log(req.body)
        let resultado = await producto.modificarProducto(id, nombre, precio, imagen, cantidad, descripcion, idCategoria)
        if(resultado){
            res.status(201).json({msg:"Producto modificado con éxito"});
        }else{
            res.status(400).end();
        }
    }catch(error){
        return('error');
    }
}

module.exports.crearProducto = crearProducto;
module.exports.obtenerProductos = obtenerProductos;
module.exports.modificarProducto = modificarProducto;
module.exports.obtenerProductosTienda = obtenerProductosTienda;