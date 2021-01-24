const express = require('express'),
router = express.Router();

const storeController = require('../controllers/storeController')

router.route('/storeRegister').post(storeController.registroTienda)
router.route('/pendingStores').get(storeController.obtenerTiendas)
router.route('/authorize').post(storeController.aprobarTienda)
router.route('/reject').post(storeController.rechazarTienda)
router.route('/info').post(storeController.obtenerInfo)
router.route('/addDepartment').post(storeController.agregarDeptoTienda)
router.route('/getDepartments').post(storeController.obtenerDeptoDeTienda)
router.route('/deleteDepartment').post(storeController.eliminarTiendaDepto)
router.route('/update').post(storeController.actualizarDatosTienda)
router.route('/filtrar').post(storeController.obtenerTiendasFiltroDepto)
router.route('/list').get(storeController.listadoTiendas)
module.exports = router;