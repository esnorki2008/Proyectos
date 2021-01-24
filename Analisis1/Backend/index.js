const express = require('express')
app = express()
cors = require('cors')
bodyParser = require('body-parser')

// sever port
var server = {
  port: 4040
};
// use the modules
app.use(cors())
app.use(bodyParser.json());

// routers
const loginRouter = require('./routes/loginRouter');
const userRouter = require('./routes/userRouter');
const storeRouter = require('./routes/storeRouter');
const departmentRouter = require('./routes/departmentRouter');
const imageRouter = require('./routes/imageRouter')
const productRouter = require('./routes/productRouter')
const categoryRouter = require('./routes/categoryRouter')
const purchaseRouter = require('./routes/purchaseRouter')
const orderRouter = require('./routes/orderRouter')

// use router
app.use('/login', loginRouter);
app.use('/user', userRouter);
app.use('/store', storeRouter);
app.use('/department', departmentRouter);
app.use('/image', imageRouter);
app.use('/product', productRouter);
app.use('/category', categoryRouter);
app.use('/purchase', purchaseRouter);
app.use('/order', orderRouter);
app.use(express.static('imagenes'));
app.listen(server.port,'0.0.0.0', () => console.log(`Server started, listening on port: ${server.port}`));