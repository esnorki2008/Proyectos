const purchase = require('../db_apis/purchase')
const product = require('../db_apis/product')
const order = require('../db_apis/order')
var nodemailer = require('nodemailer'); // email sender function 
const moment=require('moment');

/* 
    Esta función es la utilizada para aceptar el pedido
*/
async function aceptarPedido(req, res, next){ 
    try{
        const id = req.body.idCompra
        const fecha = moment().format('YYYY-MM-DD hh:mm:ss');
        console.log(fecha)
        let resultado = await order.aceptarPedido(id)
        if(resultado){
            let correo = await order.obtenerCorreo(id)
            console.log(correo);
            let productos = await product.detallesCompraProducto(id)
            console.log(productos)
            let texto = "El pedido aprobado y enviado con exito en la fecha de " + fecha
            await enviarCorreo(correo[0].correo, "Comprobante De Compra", texto,productos)
            res.status(201).json({msg:"Pedido confirmado"});
        }else{
            res.status(400).end();
        }
    }catch(error){
        return('error');
    }
}

/* 
    Esta función es la utilizada para rechazar el pedido
*/
async function rechazarPedido(req, res, next){ 
    try{
        const id = req.body.idCompra
        let resultado = await order.rechazarPedido(id)
        if(resultado){
            res.status(201).json({msg:"Pedido rechazado"});
        }else{
            res.status(400).end();
        }
    }catch(error){
        return('error');
    }
}

async function enviarCorreo(correo, asunto, texto,productos){
    try {
        let cuerpohtml = await crearHtml(productos);    
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
            html:cuerpohtml
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
        console.log(error);
        return false;
    }
}

/*
    Esta funcion lista todos los pedidos en conjunto con el nombre de usuario
*/

async function obtenerPedidos(req, res, next){
    try{
        let resultado = await order.listarPedidos()
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

async function obtenerPedidosEspera(req, res, next){
    try{
        let resultado = await order.listarPedidosEspera()
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

async function obtenerPedidosMesAño(req, res, next){
    try{
        const month = req.body.month
        const year = req.body.year
        let resultado = await order.listarMesAño(month, year)
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

async function obtenerPedidosAño(req, res, next){
    try{
        const year = req.body.year
        let resultado = await order.listarAño(year)
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

async function obtenerPorUsuario(req, res, next){
    try{
        const id = req.body.id
        let resultado = await order.listaPorUsuario(id)
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


async function crearHtml(compras){
    var contenido = "";
    compras.forEach(element => {
        contenido+='     <tr>  '  + 
        '       <td>'+element.nombre+'</td>  '  + 
        '       <td>'+element.precio+'</td>  '  + 
        '       <td>'+element.cantidad+'</td>  '  + 
        '     </tr>  '
    })
        var tablacontenido =  '   <table id="customers">  '  + 
'     <tr>  '  + 
'       <th>Nombre</th>  '  + 
'       <th>Precio</th>  '  + 
'       <th>Cantidad</th>  '  + 
'     </tr>  '  + 
        contenido;
'  </table>  ' ; 




var informacioncorreo =  '   <!DOCTYPE html>  '  + 
'   <html>  '  + 
'     '  + 
'   <head>  '  + 
'       <title></title>  '  + 
'       <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />  '  + 
'       <meta name="viewport" content="width=device-width, initial-scale=1">  '  + 
'       <meta http-equiv="X-UA-Compatible" content="IE=edge" />  '  + 
'       <style type="text/css">  '  + 
'           @media screen {  '  + 
'               @font-face {  '  + 
'                   font-family: \'Lato\';  '  + 
'                   font-style: normal;  '  + 
'                   font-weight: 400;  '  + 
'                   src: local(\'Lato Regular\'), local(\'Lato-Regular\'), url(https://fonts.gstatic.com/s/lato/v11/qIIYRU-oROkIk8vfvxw6QvesZW2xOQ-xsNqO47m55DA.woff) format(\'woff\');  '  + 
'               }  '  + 
'     '  + 
'               @font-face {  '  + 
'                   font-family: \'Lato\';  '  + 
'                   font-style: normal;  '  + 
'                   font-weight: 700;  '  + 
'                   src: local(\'Lato Bold\'), local(\'Lato-Bold\'), url(https://fonts.gstatic.com/s/lato/v11/qdgUG4U09HnJwhYI-uK18wLUuEpTyoUstqEm5AMlJo4.woff) format(\'woff\');  '  + 
'               }  '  + 
'     '  + 
'               @font-face {  '  + 
'                   font-family: \'Lato\';  '  + 
'                   font-style: italic;  '  + 
'                   font-weight: 400;  '  + 
'                   src: local(\'Lato Italic\'), local(\'Lato-Italic\'), url(https://fonts.gstatic.com/s/lato/v11/RYyZNoeFgb0l7W3Vu1aSWOvvDin1pK8aKteLpeZ5c0A.woff) format(\'woff\');  '  + 
'               }  '  + 
'     '  + 
'               @font-face {  '  + 
'                   font-family: \'Lato\';  '  + 
'                   font-style: italic;  '  + 
'                   font-weight: 700;  '  + 
'                   src: local(\'Lato Bold Italic\'), local(\'Lato-BoldItalic\'), url(https://fonts.gstatic.com/s/lato/v11/HkF_qI1x_noxlxhrhMQYELO3LdcAZYWl9Si6vvxL-qU.woff) format(\'woff\');  '  + 
'               }  '  + 
'           }  '  + 
'     '  + 
'           /* CLIENT-SPECIFIC STYLES */  '  + 
'           body,  '  + 
'           table,  '  + 
'           td,  '  + 
'           a {  '  + 
'               -webkit-text-size-adjust: 100%;  '  + 
'               -ms-text-size-adjust: 100%;  '  + 
'           }  '  + 
'     '  + 
'           table,  '  + 
'           td {  '  + 
'               mso-table-lspace: 0pt;  '  + 
'               mso-table-rspace: 0pt;  '  + 
'           }  '  + 
'     '  + 
'           img {  '  + 
'               -ms-interpolation-mode: bicubic;  '  + 
'           }  '  + 
'     '  + 
'           /* RESET STYLES */  '  + 
'           img {  '  + 
'               border: 0;  '  + 
'               height: auto;  '  + 
'               line-height: 100%;  '  + 
'               outline: none;  '  + 
'               text-decoration: none;  '  + 
'           }  '  + 
'     '  + 
'           table {  '  + 
'               border-collapse: collapse !important;  '  + 
'           }  '  + 
'     '  + 
'           body {  '  + 
'               height: 100% !important;  '  + 
'               margin: 0 !important;  '  + 
'               padding: 0 !important;  '  + 
'               width: 100% !important;  '  + 
'           }  '  + 
'     '  + 
'           /* iOS BLUE LINKS */  '  + 
'           a[x-apple-data-detectors] {  '  + 
'               color: inherit !important;  '  + 
'               text-decoration: none !important;  '  + 
'               font-size: inherit !important;  '  + 
'               font-family: inherit !important;  '  + 
'               font-weight: inherit !important;  '  + 
'               line-height: inherit !important;  '  + 
'           }  '  + 
'     '  + 
'           /* MOBILE STYLES */  '  + 
'           @media screen and (max-width:600px) {  '  + 
'               h1 {  '  + 
'                   font-size: 32px !important;  '  + 
'                   line-height: 32px !important;  '  + 
'               }  '  + 
'           }  '  + 
'     '  + 
'           /* ANDROID CENTER FIX */  '  + 
'           div[style*="margin: 16px 0;"] {  '  + 
'               margin: 0 !important;  '  + 
'           }  '  + 
'       </style>  '  + 
'   <style>  '  + 
'   #customers {  '  + 
'     font-family: Arial, Helvetica, sans-serif;  '  + 
'     border-collapse: collapse;  '  + 
'     width: 100%;  '  + 
'   }  '  + 
'     '  + 
'   #customers td, #customers th {  '  + 
'     border: 1px solid #ddd;  '  + 
'     padding: 8px;  '  + 
'   }  '  + 
'     '  + 
'   #customers tr:nth-child(even){background-color: #f2f2f2;}  '  + 
'     '  + 
'   #customers tr:hover {background-color: #ddd;}  '  + 
'     '  + 
'   #customers th {  '  + 
'     padding-top: 12px;  '  + 
'     padding-bottom: 12px;  '  + 
'     text-align: left;  '  + 
'     background-color: #4CAF50;  '  + 
'     color: white;  '  + 
'   }  '  + 
'  </style>  ' +

'   </head>  '  + 
'     '  + 
'   <body style="background-color: #e1e1e1; margin: 0 !important; padding: 0 !important;">  '  + 
'       <!-- HIDDEN PREHEADER TEXT -->  '  + 
'       <div style="display: none; font-size: 1px; color: #fefefe; line-height: 1px; font-family: \'Lato\', Helvetica, Arial, sans-serif; max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden;"> Estamos Muy Agradecidos Que Hayas Realizado Tu Compra Con Nosotros. </div>  '  + 
'       <table border="0" cellpadding="0" cellspacing="0" width="100%">  '  + 
'           <!-- LOGO -->  '  + 
'           <tr>  '  + 
'               <td bgcolor="#1746e0" align="center">  '  + 
'                   <table border="0" cellpadding="0" cellspacing="0" width="100%" style="max-width: 600px;">  '  + 
'                       <tr>  '  + 
'                           <td align="center" valign="top" style="padding: 40px 10px 40px 10px;"> </td>  '  + 
'                       </tr>  '  + 
'                   </table>  '  + 
'               </td>  '  + 
'           </tr>  '  + 
'           <tr>  '  + 
'               <td bgcolor="#1746e0" align="center" style="padding: 0px 10px 0px 10px;">  '  + 
'                   <table border="0" cellpadding="0" cellspacing="0" width="100%" style="max-width: 600px;">  '  + 
'                       <tr>  '  + 
'                           <td bgcolor="#ffffff" align="center" valign="top" style="padding: 40px 20px 20px 20px; border-radius: 4px 4px 0px 0px; color: #111111; font-family: \'Lato\', Helvetica, Arial, sans-serif; font-size: 48px; font-weight: 400; letter-spacing: 4px; line-height: 48px;">  '  + 
'                               <h1 style="font-size: 48px; font-weight: 400; margin: 2;">Compra Exitosa!</h1> <img src=" https://img.icons8.com/clouds/100/000000/handshake.png" width="125" height="120" style="display: block; border: 0px;" />  '  + 
'                           </td>  '  + 
'                       </tr>  '  + 
'                   </table>  '  + 
'               </td>  '  + 
'           </tr>  '  + 
'           <tr>  '  + 
'               <td bgcolor="#e1e1e1" align="center" style="padding: 0px 10px 0px 10px;">  '  + 
'                   <table border="0" cellpadding="0" cellspacing="0" width="100%" style="max-width: 600px;">  '  + 
'                       <tr>  '  + 
'                           <td bgcolor="#ffffff" align="left" style="padding: 20px 30px 40px 30px; color: #666666; font-family: \'Lato\', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;">  '  + 
'                               <p style="margin: 0;">Acontinuacion Encontraras Un Resumen De Tu Compra</p>  '  + 
'                           </td>  '  + 
'                       </tr>  '  + 
'                       <tr>  '  + 
'                           <td bgcolor="#ffffff" align="left">  '  + 
'                               <table width="100%" border="0" cellspacing="0" cellpadding="0">  '  + 
'                                   <tr>  '  + 
'                                       <td bgcolor="#ffffff" align="center" style="padding: 20px 30px 60px 30px;">  '  + 
'                                           <table border="0" cellspacing="0" cellpadding="0">  '  + 
'                                               <tr>  '  + 
'                                                   <!--TABLA CON LA INFORMACION DE LOS PRODUCTOS -->  '  + 
                                                    tablacontenido+
'                                               </tr>  '  + 
'                                           </table>  '  + 
'                                       </td>  '  + 
'                                   </tr>  '  + 
'                               </table>  '  + 
'                           </td>  '  + 
'                       </tr> <!-- COPY -->  '  + 
'     '  + 
'                       <tr>  '  + 
'                           <td bgcolor="#ffffff" align="left" style="padding: 20px 30px 20px 30px; color: #666666; font-family: \'Lato\', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;">  '  + 
'                               <p style="margin: 0;"><a  target="_blank" style="color: #1746e0;">  '  + 
'                                   Esperamos Tu Proxima Compra Con Exito!!  '  + 
'                               </a></p>  '  + 
'                           </td>  '  + 
'                       </tr>  '  + 
'     '  + 
'                       <tr>  '  + 
'                           <td bgcolor="#ffffff" align="left" style="padding: 0px 30px 40px 30px; border-radius: 0px 0px 4px 4px; color: #666666; font-family: \'Lato\', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;">  '  + 
'                               <p style="margin: 0;">Cheers,<br>AYD TEAM 4</p>  '  + 
'                           </td>  '  + 
'                       </tr>  '  + 
'                   </table>  '  + 
'               </td>  '  + 
'           </tr>  '  + 
'           <tr>  '  + 
'               <td bgcolor="#e1e1e1" align="center" style="padding: 30px 10px 0px 10px;">  '  + 
'                   <table border="0" cellpadding="0" cellspacing="0" width="100%" style="max-width: 600px;">  '  + 
'                       <tr>  '  + 
'     '  + 
'                       </tr>  '  + 
'                   </table>  '  + 
'               </td>  '  + 
'           </tr>  '  + 
'           <tr>  '  + 
'               <td bgcolor="#e1e1e1" align="center" style="padding: 0px 10px 0px 10px;">  '  + 
'                   <table border="0" cellpadding="0" cellspacing="0" width="100%" style="max-width: 600px;">  '  + 
'                       <tr>  '  + 
'                           <td bgcolor="#e1e1e1" align="left" style="padding: 0px 30px 30px 30px; color: #666666; font-family: \'Lato\', Helvetica, Arial, sans-serif; font-size: 14px; font-weight: 400; line-height: 18px;"> <br>  '  + 
'                               <p style="margin: 0;">If these emails get annoying, please feel free to <a target="_blank" style="color: #111111; font-weight: 700;">unsubscribe</a>.</p>  '  + 
'                           </td>  '  + 
'                       </tr>  '  + 
'                   </table>  '  + 
'               </td>  '  + 
'           </tr>  '  + 
'       </table>  '  + 
'   </body>  '  + 
'     '  + 
'  </html>  ' ; 

    return informacioncorreo;
}

module.exports.aceptarPedido = aceptarPedido;
module.exports.rechazarPedido = rechazarPedido;
module.exports.obtenerPedidos = obtenerPedidos;
module.exports.obtenerPedidosEspera = obtenerPedidosEspera;
module.exports.obtenerPedidosMesAño = obtenerPedidosMesAño;
module.exports.obtenerPedidosAño = obtenerPedidosAño;
module.exports.obtenerPorUsuario = obtenerPorUsuario;
