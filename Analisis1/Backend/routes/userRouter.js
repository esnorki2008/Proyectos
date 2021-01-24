const express = require('express'),
router = express.Router();

const userController = require('../controllers/userController')

router.route('/clientRegister').post(userController.post)
router.route('/update').post(userController.modificar)

module.exports = router;