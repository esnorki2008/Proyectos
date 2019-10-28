const dbusuario = require('../db_apis/Usuario.js');
function jsonusuario(req) {
  const usuario = {        
    nombre: req.body.nombre,
    correo: req.body.correo
  };
 
  return usuario;
} 

async function get(req, res, next) {
  try {
    const context = {};
    let emp = jsonusuario(req);
     
    //context.id = parseInt(req.params.id, 10);
    //console.log(req.params.id);
    let rows; 
    if(req.params.id=="todo"){
     rows=await dbusuario.DevolverTodo();
    }else{
     rows= await dbusuario.find(emp);
    }
    if (req.params.id) {
      /*
      if (rows.length === 1) {
        res.status(200).json(rows[0]);
      } else {
        res.status(404).end();
      }
      */
     res.status(200).json(rows);
    } else {
      res.status(200).json(rows);
    }
  } catch (err) {
    next(err);
  }
}
 
module.exports.get = get;


	

 
async function post(req, res, next) {
  try {
    let usuario = jsonusuario(req);
   
    usuario = await dbusuario.create(usuario);
    
    res.status(201).json(usuario);
  } catch (err) {
    next(err);
  }
}
 
module.exports.post = post;

async function put(req, res, next) {
  try {
    let usuario = jsonusuario(req);
 
    //usuario.nombre = parseInt(req.params.id, 10);
    
    usuario = await dbusuario.update(usuario);
 
    if (usuario !== null) {
      res.status(200).json(usuario);
    } else {
      res.status(404).end();
    }
  } catch (err) {
    next(err);
  }
}
 
module.exports.put = put;


async function del(req, res, next) {
  try {
    //const id = parseInt(req.params.id, 10)
    let usuario=jsonusuario(req);
    
    const success = await dbusuario.delete(usuario);
 
    if (success) {
      //res.status(204).end();
      res.status(200).json(usuario);
    } else {
      res.status(404).end();
    }
  } catch (err) {
    next(err);
  }
  return "hola";
}
 
module.exports.delete = del;