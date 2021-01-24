db = require('../database')
var nodemailer = require('nodemailer'); // email sender function 
const users = require('../db_apis/user')
const stores = require('../db_apis/store')

/* 
    Esta función post es la que se utilizá para realizar el 
    registro de los clientes
*/
async function registroTienda(req, res, next){ 
    try{
        const nombre = req.body.nombre
        const apellido = req.body.apellido
        const correo = req.body.correo
        const contraseña = req.body.contrasenia
        const telefono = req.body.telefono
        const idRol = req.body.idRol
        const estado = req.body.estado
        const slogan = req.body.slogan
        const nombreTienda = req.body.nombreTienda
        const imagen = req.body.imagen
        let existe = await users.verificacion(correo)
        console.log(existe) 
        if(existe){
            res.status(400).json({msg:"Ya existe un usuario con ese correo"})
            return
        }
        let insertar = await users.registro(nombre, apellido, correo, contraseña, telefono, idRol, estado)
        if(insertar){
            let idUsuario = await users.obtenerId(correo)
            let resultado = await stores.insertar(nombreTienda, slogan, idUsuario, imagen)
            if(resultado){
                res.status(201).json({msg:"Registro realizado con exito, espere la confirmación"});
            }else{
                res.status(400).end();
            }
        }else{
            res.status(400).end();
        }
    }catch(error){
        return('error');
    }
}

async function actualizarDatosTienda(req, res, next){
    try{
        const id = req.body.id
        const slogan = req.body.slogan
        const nombreTienda = req.body.nombreTienda
        const imagen = req.body.imagen
        const s = req.body.slogan
        console.log(req.body.slogan)
        let resultado = await stores.actualizarDatos(id, nombreTienda, slogan, imagen)
        if(resultado){
            res.status(201).json({msg:"Se actualizaron los datos de manera correcta"});
        }else{
            res.status(400).end();
        }
    }catch(error){
        return('error');
    }
}

async function obtenerTiendas(req, res, next){
    try{
        let resultado = await stores.tiendas()
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

async function aprobarTienda(req, res, next){
    try{
        const idTienda = req.body.idTienda
        console.log(idTienda)
        let resultado = stores.actualizar(idTienda, 1)
        if(resultado){
            let idDueño = await stores.obtenerDueño(idTienda)
            let re = await users.actualizar(idDueño)
            let correo = await users.obtenerCorreo(idDueño)
            let texto = "La tienda que habías solicitado agregar a CCV ha sido aceptada con exito, utiliza tu correo y la contraseña para ingresar al portal. BIENVENIDO!"
            await enviarCorreo(correo.correo, "Tienda autorizada", texto)
            if(re){
                res.status(201).json({msg:"Tienda autorizada exitosamente"});
            }else{
                res.status(400).end();
            }
        }else{
            res.status(400).end();
        }
    }catch(error){
        return('error');
    }
}

async function rechazarTienda(req, res, next){
    try{
        const idTienda = req.body.idTienda
        console.log(idTienda)
        let resultado = stores.actualizar(idTienda, 2)
        let idDueño = await stores.obtenerDueño(idTienda)
        let correo = await users.obtenerCorreo(idDueño)
        let texto = "Lo sentimos, su tienda no cumple con los requisitos para estar en CCV"
        await enviarCorreo(correo.correo, "Tienda rechazada", texto)
        if(resultado){
            res.status(201).json({msg:"Tienda rechazada exitosamente"});
        }else{
            res.status(400).end();
        }
    }catch(error){
        return('error');
    }
}

async function enviarCorreo(correo, asunto, texto){
    try {    
        console.log(correo)
        console.log(asunto)
        console.log(texto)
        var transporter = nodemailer.createTransport({
            service: 'Gmail',
            auth: {
                user: 'correodeproyectosmarco@gmail.com',
                pass: 'Pass05Pro.'
            }
        });
        var mailOptions = {
            from: 'CCV',
            to: correo,
            subject: asunto,
            text: texto
        };
        transporter.sendMail(mailOptions, function(error, info){
            if (error){
                console.log(error);
                return false;
            } else {
                console.log("Correo enviado")
                return true;
            }
        });
    } catch (error) {
        return false;
    }
}

/* 
    Esta función es la utilizada para crear obtener la información de la tienda de un usuario
*/
async function obtenerInfo(req, res, next){ 
    try{
        const id = req.body.id
        let resultado = await stores.obtenerDatosTienda(id)
        if(resultado){
            res.status(201).json(resultado);
        }else{
            res.status(400).end();
        }
    }catch(error){
        return('error');
    }
}

async function agregarDeptoTienda(req, res, next){ 
    try{
        let resultado = await stores.agregarDepartamentoTienda(req.body.idTienda,req.body.idDepartamento)
        if(resultado){
            res.status(201).json({msg:"La tienda se agrego al departamento"});
        }else{
            res.status(400).end();
        }
    }catch(error){
        return('error');
    }
}

/*
    Obtener los departamentos a los que petenece la tienda
*/
async function obtenerDeptoDeTienda(req, res, next){
    try{
        const id = req.body.id
        let resultado = await stores.obtenerDepartamentosDeTienda(id)
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
    Obtener departamentos de tienda
*/
async function eliminarTiendaDepto(req, res, next){
    try{
        const id = req.body.id
        let resultado = await stores.eliminarTiendaDeDepartamento(id, req.body.idDepartamento)
        if(resultado.affectedRows == 1){
            res.status(200).json({msg:"Se removio el departamento"})
        }else{
            res.status(400).end();
        }
    }catch(error){
        return('error');
    }
}

/*
    Obtener departamentos de tienda
*/
async function obtenerTiendasFiltroDepto(req, res, next){
    try{
        const id = req.body.id
        let resultado = await stores.obtenerTiendasporDepartamentos(id, req.body.idDepartamento)
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
    Obtener el listado de tiendas
*/
async function listadoTiendas(req, res, next){
    try{
        const id = req.body.id
        let resultado = await stores.listarTiendas()
        if(resultado){
            res.status(200).json(resultado)
        }else{
            res.status(400).end();
        }
    }catch(error){
        return('error');
    }
}

module.exports.registroTienda = registroTienda;
module.exports.obtenerTiendas = obtenerTiendas;
module.exports.aprobarTienda = aprobarTienda;
module.exports.rechazarTienda = rechazarTienda;
module.exports.enviarCorreo = enviarCorreo;
module.exports.obtenerInfo = obtenerInfo;
module.exports.listadoTiendas = listadoTiendas;
module.exports.actualizarDatosTienda = actualizarDatosTienda;
module.exports.agregarDeptoTienda = agregarDeptoTienda;
module.exports.obtenerDeptoDeTienda = obtenerDeptoDeTienda;
module.exports.eliminarTiendaDepto = eliminarTiendaDepto;
module.exports.obtenerTiendasFiltroDepto = obtenerTiendasFiltroDepto;