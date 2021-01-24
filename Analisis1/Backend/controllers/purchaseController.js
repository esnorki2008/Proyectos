const purchase = require('../db_apis/purchase')
const product = require('../db_apis/product')
const order = require('../db_apis/order')
const moment=require('moment');

/* 
    Esta función es la utilizada para crear productos
*/
async function compra(req, res, next){ 
    try{
        const idUsuario = req.body.idUsuario
        const total = req.body.total
        console.log('hola')
        const fecha = moment().format('YYYY-MM-DD hh:mm:ss');
        console.log(fecha)
        const productos = req.body.productos
        const direccion = req.body.direccion
        let e = false
        for (const x in productos) {
            console.log(productos[x]);
            const res = await product.verificar(productos[x].id, productos[x].cantidad)
            if(res[0].existe == 0){
                e = true;
                break
            }
        }
        if(e){
            res.status(400).json({msg:"No hay stock"});
            return
        }
        let resultado = await purchase.insertar(idUsuario, total, fecha)
        if(resultado){
            let idCompra = await purchase.obtenerCompra(idUsuario, total, fecha)
            for (const x in productos) {
                purchase.insertarDetalleCompra(idCompra[0].id, productos[x].id, productos[x].cantidad)
                product.actualizarCantidad(productos[x].id, productos[x].cantidad)
            }
            await order.insertar(idCompra[0].id, direccion)
            res.status(201).json({msg:"Compra realizada con exito"});
        }else{
            res.status(400).end();
        }
    }catch(error){
        return('error');
    }
}

module.exports.compra = compra;