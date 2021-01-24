var nodemailer = require('nodemailer'); // email sender function 

exports.sendEmail = function(req, res){
    try {
        console.log(req.body);
    
    var transporter = nodemailer.createTransport({
        service: 'Gmail',
        auth: {
            user: 'correodeproyectosmarco@gmail.com',
            pass: 'Pass05Pro'
        }
    });
    var texto = req.body.texto + "";
    var mailOptions = {
        from: 'CCV',
        to: req.body.correo,
        subject: req.body.asunto,
        text: req.body.texto
    };
    transporter.sendMail(mailOptions, function(error, info){
        if (error){
            console.log(error);
            res.send(500, err.message);
        } else {
            console.log("Correo enviado");
            res.status(200).jsonp(req.body);
        }
    });
    } catch (error) {
        
    }
};

async function enviarCorreo(correo, asunto, texto){
    try {
        console.log(req.body);
    
    var transporter = nodemailer.createTransport({
        service: 'Gmail',
        auth: {
            user: 'correodeproyectosmarco@gmail.com',
            pass: 'Pass05Pro'
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
            return true;
        }
    });
    } catch (error) {
        return false;
    }
}

module.exports.enviarCorreo = enviarCorreo;