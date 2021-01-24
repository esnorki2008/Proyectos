const express = require('express'),
router = express.Router();
const multer = require('multer');

const almacenar = multer.diskStorage({
    destination: function(req,file, cb){
        cb(null, './imagenes/');
    },
    filename: function(req,file, cb){
        cb(null, (Math.round(Math.random() * (5000 - 1) + 1)) +file.originalname);
    }
})

const subir = multer({storage: almacenar});

router.route('/subirImagen')
    .post(subir.single('fotografia'), (req, res)=>{
        res.json({'ruta': req.file.filename});
    });
module.exports = router;