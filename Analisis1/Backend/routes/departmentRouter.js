const express = require('express'),
router = express.Router();

const departmentController = require('../controllers/departmentController')

router.route('/createDepartment').post(departmentController.crearDepartamento)
router.route('/listar').get(departmentController.obtenerDepartamento)
router.route('/updateDepartment').post(departmentController.modificarDepartamento)
module.exports = router;