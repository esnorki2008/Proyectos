const express = require('express'),
router = express.Router();

const loginController = require('../controllers/loginController')

router.post('/', loginController.post);

router.route('/login').post(loginController.post)

module.exports = router;