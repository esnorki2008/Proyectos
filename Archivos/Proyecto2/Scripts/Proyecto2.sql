--====================================Clase Cliente========================================
create table ClaseCliente(CodClaseCliente int primary key,Descripcion varchar(20));
--======================================Usuario===========================================
create table Usuario(CodUsuario int primary key,Nombre varchar(50),Apellido varchar(50),Password varchar(50),
Correo varchar(50),Telefono varchar(20),Fotografia blob,Genero varchar(10),FechaDeNacimiento date,
FechaDeRegistro date,Direccion varchar(100),CreditoDisponible int,CodClaseCliente int);

alter table Usuario add constraint ClaseUsuario foreign key (CodClaseCliente) 
references ClaseCliente(CodClaseCliente);
--====================================Tipo Encargado=======================================
create table TipoEncargado (CodTipoEncargado int primary key,Descripcion varchar(20));
--====================================Encargado============================================
create table Encargado(CodEncargado int primary key,CodTipoEncargado int,Identifiacdor varchar(50),Password varchar(50));

alter table Encargado add constraint ForaneaEncargado foreign key (CodTipoEncargado)
references TipoEncargado(CodTipoEncargado);
--==================================Categoria==============================================
create table Categoria(CodCategoria int primary key,Descripcion varchar(40), CategoriaPadre int);

alter table Categoria add constraint ForaneaPadreCategoria foreign key(CategoriaPadre) 
references Categoria(CodCategoria);
--===================================Producto===============================================
create table Producto(CodProducto int primary key,Nombre varchar(50),Precio int,Cantidad int,UsuarioVendedor int,
CodCategoria int,FechaPublicacion date, Descripcion varchar(250));

alter table Producto add constraint ForaneaProductoCategoria foreign key(CodCategoria)
references Categoria(CodCategoria);

alter table Producto add constraint ForaneaProductoVendedor foreign key(UsuarioVendedor)
references Usuario(CodUsuario);

--==================================Imaagen================================================
create table Imagen(CodImagen int primary key,CodProducto int,Img blob);

alter table Imagen add constraint ForaneaImagenProducto foreign key (CodProducto)
references Producto(CodProducto);
--================================Venta======================================================
create table Caracteristica(CodCaracteristica int primary key,Descripcion varchar(50));
--================================Producto Detalle=========================================
create table ProductoDetalle(CodProducto int ,CodCaracteristica int);

alter table ProductoDetalle add constraint ForaneDetalleProductoProducto foreign key (CodProducto)
references Producto(CodProducto);

alter table ProductoDetalle add constraint ForaneaDetalleProductoCara foreign key(CodCaracteristica)
references Caracteristica(CodCaracteristica);
--==============================Venta==========================================================
create table Venta(CodVenta int primary key,Comprador int,Producto int,Cantidad int,Fecha date);

alter table Venta add constraint ForaneVentaComprador foreign key (Comprador)
references Usuario(CodUsuario);

alter table Venta add constraint ForaneaVentaProducto foreign key(Producto)
references Producto(CodProducto);
--=============================Chat==========================================================
create table Chat (CodChat int primary key,Usuario int,Encargado int,Descripcion varchar(50),FechaInicio date);

alter table Chat add constraint ForaneaChatEncargado foreign key (Encargado)
references Encargado(CodEncargado);

alter table chat add constraint ForaneaChatUsuario foreign key(Usuario)
references Usuario(CodUsuario);
--===========================Mensaje==========================================================
create table Mensaje(CodMensaje int primary key,Contenido varchar(250),Fecha Date);
