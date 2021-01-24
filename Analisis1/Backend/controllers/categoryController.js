const categoria = require('../db_apis/category')

async function obtenerCategorias(req, res, next){
    try{
        let resultado = await categoria.listarCategorias()
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

module.exports.obtenerCategorias = obtenerCategorias;