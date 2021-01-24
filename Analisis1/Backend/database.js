mysql = require('mysql')
const IP = '35.232.201.214'
const USER = 'marco'
const PASSWORD = 'GRP4_2020_3'
const DATABASE = 'comercial_online'
//sudo mysql
var db = mysql.createConnection({
    host: IP,
    user: USER,
    password: PASSWORD,
    database: DATABASE
})

db.connect(function(error){
    if(error){
       throw error;
    }else{
       console.log('Conexion correcta.');
    }
});

function ejecutarQuery(query){
    let conection;
    console.log(query)
    return new Promise(async (resolve, reject) =>{
        try{
            db.query(query,function(err, data, fields){
                if (err) throw err;
                resolve(data)
            })
        }catch(error){
            reject(error)
        }
    })
}

function ejecutarInsercion(query){
    console.log(query)
    return new Promise(async (resolve, reject) =>{
        try{
            db.query(query,function(err, data, fields){
                if (err) throw err;
                resolve(true)
            })
        }catch(error){
            reject(false)
        }
    })
}

module.exports.ejecutarQuery = ejecutarQuery;
module.exports.ejecutarInsercion = ejecutarInsercion;