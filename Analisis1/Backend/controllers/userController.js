db = require('../database')
const users = require('../db_apis/user')

/* 
    Esta función post es la que se utilizá para realizar el 
    registro de los clientes
*/
async function post(req, res, next){ 
    try{
        const nombre = req.body.nombre
        const apellido = req.body.apellido
        const correo = req.body.correo
        const contraseña = req.body.contrasenia
        const telefono = req.body.telefono
        const idRol = req.body.idRol
        const estado = req.body.estado
        let existe = await users.verificacion(correo)
        console.log(existe) 
        if(existe){
            res.status(400).json({msg:"Ya existe un usuario con ese correo"})
            return
        }
        let insertar = await users.registro(nombre, apellido, correo, contraseña, telefono, idRol, estado)
        if(insertar){
            res.status(201).json({msg:"Registro realizado con exito"});
        }else{
            res.status(400).end();
        }
    }catch(error){
        return('error');
    }
}

/*
    Modificar usuario
*/
async function modificar(req, res, next){ 
    try{
        const id = req.body.id
        const nombre = req.body.nombre
        const apellido = req.body.apellido
        const correo = req.body.correo
        const contraseña = req.body.contrasenia
        const telefono = req.body.telefono
        const idRol = req.body.idRol
        const estado = req.body.estado
        console.log(req.body)
        let insertar = await users.modificar(id, nombre, apellido, correo, contraseña, telefono, idRol, estado)
        if(insertar){
            res.status(201).json({msg:"Datos actualizados con exito"});
        }else{
            res.status(400).end();
        }
    }catch(error){
        return('error');
    }
}
module.exports.post = post;
module.exports.modificar = modificar;