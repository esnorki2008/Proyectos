const express = require('express'),
router = express.Router();

const categoryController = require('../controllers/categoryController')

router.route('/listar').get(categoryController.obtenerCategorias)
module.exports = router;