const department = require('../db_apis/department')

/* 
    Esta función es la utilizada para crear departamentos
*/
async function crearDepartamento(req, res, next){ 
    try{
        const nombre = req.body.nombre
        let resultado = await department.nuevoDepartamento(nombre)
        if(resultado){
            res.status(201).json({msg:"Departamento creado con éxito"});
        }else{
            res.status(400).end();
        }
    }catch(error){
        return('error');
    }
}

/*
    Esta funcion es utilizada para listar los departamentos
*/
async function obtenerDepartamento(req, res, next){
    try{
        let resultado = await department.obtenerDepartamentos()
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
    Esta funcion es utilizada para modificar un departamento
*/
async function modificarDepartamento(req, res, next){
    try{
        const nombre = req.body.nombre
        const id = req.body.id
        let resultado = await department.modificarDepartamento(id, nombre)
        console.log(resultado)
        if(resultado){
            res.status(200).json({msg:"Departamento actualizado con exito"})
        }else{
            res.status(400).end();
        }
    }catch(error){
        return('error');
    }
}

module.exports.crearDepartamento = crearDepartamento;
module.exports.obtenerDepartamento = obtenerDepartamento;
module.exports.modificarDepartamento = modificarDepartamento;