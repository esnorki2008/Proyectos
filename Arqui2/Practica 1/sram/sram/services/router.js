const express = require('express');
const router = new express.Router();

const realtime = require('../controllers/realtime');

router.route('/tiemporeal').post(realtime.ntime)
router.route('/tiemporeal').get(realtime.gtime)

module.exports = router