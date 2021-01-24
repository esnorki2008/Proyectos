const express = require('express'),
router = express.Router();

const purchase = require('../controllers/purchaseController')

router.route('/compra').post(purchase.compra)

module.exports = router;