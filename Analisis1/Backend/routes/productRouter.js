const express = require('express'),
router = express.Router();

const productController = require('../controllers/productController')

router.route('/create').post(productController.crearProducto)
router.route('/list').get(productController.obtenerProductos)
router.route('/listTienda').post(productController.obtenerProductosTienda)
router.route('/update').post(productController.modificarProducto)
module.exports = router;