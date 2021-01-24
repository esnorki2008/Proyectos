const db = require('../database')

/*
    Obtener los departamentos a los que pertenece una tienda
*/
async function listarCategorias(){
    let query = `SELECT * FROM categoria;`;
    let result = await db.ejecutarQuery(query);
    return result
}

module.exports.listarCategorias = listarCategorias;