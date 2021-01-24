--###################### ROL ######################
CREATE TABLE rol (id  int not null  primary key AUTO_INCREMENT, nombre varchar(30) not null);
--###################### USUARIO ######################
CREATE TABLE usuario(id int not null primary key AUTO_INCREMENT, nombre varchar(50) not null
    , apellido varchar(50) not null, correo varchar(70) not null unique, 
    contraseña varchar(50) not null,telefono varchar(50) not null,
    idRol int not null,estado int not null, 
    FOREIGN KEY (idRol) REFERENCES rol(id));
--###################### DEPARTAMENTO ######################
CREATE TABLE departamento  (id int not null primary key AUTO_INCREMENT, departamento varchar(100));
--###################### TIENDA ######################
CREATE TABLE tienda (id int not null primary key AUTO_INCREMENT, nombre varchar(50),
    slogan varchar(150), idUsuario int not null,
    estado int, FOREIGN KEY (idUsuario) REFERENCES usuario(id));
--###################### DEPARTAMENTO_TIENDA ######################
CREATE TABLE departamento_tienda  (idDepartamento int not null, idTienda int not null,
    FOREIGN KEY (idTienda) REFERENCES tienda(id),
    FOREIGN KEY (idDepartamento) REFERENCES departamento(id));
--###################### CATEGORIA ######################
CREATE TABLE categoria(id int not null primary key AUTO_INCREMENT, nombre varchar(50) not null,
    idCategoriaPadre int , 
    FOREIGN KEY (idCategoriaPadre) REFERENCES categoria(id));
--###################### PRODUCTO ######################
CREATE TABLE producto (id int not null primary key AUTO_INCREMENT, nombre varchar(50) not null,
    precio float not null, imagen varchar(300), cantidad int not null,
    descripcion varchar(300), idTienda int not null, idCategoria int not null,
    FOREIGN KEY (idTienda) REFERENCES tienda(id), 
    FOREIGN KEY (idCategoria) REFERENCES categoria(id));
--###################### COMPRA ######################
CREATE TABLE compra (id int not null primary key AUTO_INCREMENT, idUsuario int not null,
    total float not null, fecha date not null,
    FOREIGN KEY (idUsuario) REFERENCES usuario(id));
--###################### DETALLE COMPRA ######################
CREATE TABLE detalle_compra (idCompra int not null,idProducto int not null,
    cantidad int not null,FOREIGN KEY (idCompra) REFERENCES compra(id),
    FOREIGN KEY (idProducto) REFERENCES producto(id) );
--###################### PEDIDO ######################
CREATE TABLE pedido (id int not null primary key AUTO_INCREMENT,idCompra int not null,
    direccion varchar(150), estado int, 
    FOREIGN KEY (idCompra) REFERENCES compra(id));



--############################ DATOS INICIALES #################
insert into rol (nombre) values('administrador');
insert into rol (nombre) values('cliente');
insert into rol (nombre) values('encargado');


insert into usuario (nombre,apellido,correo,contraseña,telefono,idRol,estado)
values ('administrador','del sistema','admin@ccv.com','admmin','telefono',1,1);



use comercial_online;