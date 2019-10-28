const express = require('express');
const router = new express.Router();
const usuario = require('../controllers/Usuario.js');


router.route('/usuario/:id?')
  .get(usuario.get)
  .post(usuario.post)
  .put(usuario.put)
  .delete(usuario.delete)  
  ;
 /*
router.route('/')
.get(usuario.get)
.post(usuario.post)
.put(usuario.put)
.delete(usuario.delete)  
;  
*/
module.exports = router;