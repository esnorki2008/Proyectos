const db = require('../database')

/*
    Consulta a la base de datos llamando al metodo ejecutarQuery
    del archivo database
*/
async function login(correo, contraseña){
    try {
        let query = `SELECT * FROM usuario WHERE correo  = '${correo}' and contraseña = '${contraseña}';`;
        //console.log(query)
        let result = await db.ejecutarQuery(query);
        //console.log(result)
        return result
    } catch (error) {
        console.log(error)
    }
}

module.exports.login = login;