const express = require('express'),
router = express.Router();

const orderController = require('../controllers/orderController')

router.route('/aceptar').post(orderController.aceptarPedido)
router.route('/rechazar').post(orderController.rechazarPedido)
router.route('/listar').get(orderController.obtenerPedidos)
router.route('/listarEspera').get(orderController.obtenerPedidosEspera)
router.route('/reporteMes').post(orderController.obtenerPedidosMesAño)
router.route('/reporteYear').post(orderController.obtenerPedidosAño)
router.route('/usuario').post(orderController.obtenerPorUsuario)

module.exports = router;