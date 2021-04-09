const utils = require('../utils/utils')
let medicion = {data:""};
let copia = "";
module.exports.ntime = async function(request, response, next){
    try {
        if(medicion.data != "")
            copia = request.body.data
        medicion.data = request.body.data
        response = utils.formarResponse(response, `Medicion Guardada`, 200)
    } catch (error) {
        console.log(error)
        response = utils.formarResponse(response, `Error al registrar, campos invalidos`, 404)
    }
}
module.exports.gtime = async function(request, response, next){
    try {
        if(medicion.data == ""){
            if(copia != ""){
                medicion.data = copia
                copia = ""
            }
        }
        let tempo = medicion;
        medicion = {data:""}
        response = utils.formarResponse(response, tempo, 200)
    } catch (error) {
        console.log(error)
        response = utils.formarResponse(response, `Error al registrar, campos invalidos`, 404)
    }
}