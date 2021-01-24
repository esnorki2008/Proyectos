db = require('../database')
const logi = require('../db_apis/login')


/* 
    Esta función post es la encargada de validar los datos que se 
    estan recibiendo y consultar que existan dentro de la base de datos
    llamando a db_apis/login para realizar la consulta
*/
async function post(req, res, next){ 
    try{
        const correo = req.body.correo
        const contraseña = req.body.contrasenia
        let result = await logi.login(correo, contraseña) 
        console.log(result)
        if(result.error){
            res.status(400).end();
        }else{
            if(result[0]){
                res.status(201).json(result[0]);
            }else{
                res.status(400).json({msg:"Credenciales invalidas"});
            }
        }
    }catch(error){
        return('error');
    }
}

module.exports.post = post;