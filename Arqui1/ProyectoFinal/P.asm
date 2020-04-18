include Macros.asm ; archivo con los macros
include PJuego.asm
include DJuego.asm
include EJuego.asm
include GJuego.asm
include Usuario.asm
include Archivo.asm
include Estadi.asm
include Top.asm
include Barras.asm
include Orden.asm

.model small
.stack 500h
.data
;=======================Titulos
Titulo1  db "UNIVERSIDAD DE SAN CARLOS DE GUATEMALA",10,13,"$"   
Titulo2 db "ARQUITECTURA DE COMPUTADORES Y ENSAMBLADORES 1A",10,13,"NOMBRE: ANDHY LIZANDRO SOLIS OSORIO",10,13,"$"
Titulo3 db "CARNET: 201700886",10,13,"SECCION: A",10,13,"$" 
SinCarga db "No Hay Niveles Cargados","$" 
Top10Puntos db "    Top 10 Puntos","$"
Top10Tiempo db "    Top 10 Tiempos","$"
BanderaCargado db 0

MenuPrincipal1 db "1)Ingresar","$"
MenuPrincipal2 db "2)Registrar","$"
MenuPrincipal3 db "3)Salir","$"

MenuUsuario1 db "1)Iniciar Juego","$"
MenuUsuario2 db "2)Cargar Juego","$"
MenuUsuario3 db "3)Salir","$"


MenuAdmin1 db "1)Top 10 puntos","$"
MenuAdmin2 db "2)Top 10 tiempo","$"
MenuAdmin3 db "3)Salir","$"


TituloUsuario db "Ingrese Credenciales Para Nuevo Usuario","$"
TituloCreado db "El Usuario Fue Creado Exitosamente","$"
TituloErrorArchivo db "El Archivo No Se Pudo Abrir","$"
TituloIngreseRuta db "Ingrese La Ruta Del Archivo .PLY","$"
TituloExtension db "La Extension Debe Ser .PLY","$"

TituloLogin db "Ingrese Sus Credenciales Para Ingresar","$"
TituloBienvenido db "Bienvenido Usuario: ","$"
TItuloLoginMalo db "Credenciales Erroneas","$"

Pausa db "Juego En Pausa Presione, Espacio Para Salir O Presione ESC para Regresar"


RellenoUsu db "Usuario:","$"
RellenoContra db "Contra:","$"
ErrorContra db "Error La Contrasenia No Es Numerica","$"
ErrorUsuario db "El Usuario Ya Fue Creado","$"
AdvertenciaUsuario db "Advertencia Se Alncanzo El Limite De Usuarios","$"

Contra db "Ingrese Contrasenia","$"
User db "Ingrese Nombre De Usuario","$"

MaximoNivelPunteo db 0
MaximoNivelTiempo db 0

ArchivoUsuario db "User.txt",00h
ArchivoPunteo db "Puntos.txt",00h
ArchivoTiempo db "Tiempos.txt",00h
;=============Variables Del Juego=============
JugadorActual db 12 dup(0)
PosX db 0
Handle dw 0
Punteo dw 0
MaximoPunteo dw 0
MaximoTiempo dw 0

Obstaculo db 20 dup(0)
ObstaculoY db 20 dup(0)
ObstaculoX db 20 dup(0)

Bonus db 20 dup(0)
BonusY db 20 dup(0)
BonusX db 20 dup(0)
TiempoMaximo dw 0
ActualBonus db 0
BanderaBonus dw 0
BanderaBonusSub dw 0
ACtualObstaculo db 0
BanderaObstaculo dw 0
BanderaObstaculoSub dw 0
ActualTiempo dw 0
Hora db 0
Minuto db 0
Segundo db 0
Colorcito db 0
Registro dw 0

Rex db 0
Longitud db 0 
Teclado db 25 dup (0)

ValX db 0
TopeX db 0
TopeY db 0
BarraAncho db 0
BarraLargo db 0
MaxBarraLargo db 0
MaxBarraAncho db 0

TecladoTemporal db 10 dup (0)
;=============Almacenamiento Temporal===================
InformacionUsuario db 1100 dup (0)
InformacionTiempo db 200 dup (0)
InformacionPuntos db 200 dup (0)
InformacionNiveles db 500 dup (0);6 Niveles de 17
InformacionRealNiveles db 50 dup (0);6 Niveles de 17
ValoresBarras db 20 dup(0)
CantidadBarras db 0
;=============Variables De La PantallaJuego==============
PantallaUsuarioActual db 12 dup (0)
PantallaNivelActual db 0 
PantallaPuntosActual db 4 dup(0)
PantallaTiempoActual db 8 dup(0)
TiempoNivel dw 0
ChoqueObstaculo db 0
ChoqueBonus db 0
Regis16 dw 0
Regis8 db 0
IndexNivel dw 0
;===============Delay===============
inputi db 50
mm dw 0
rr dw 0
.code




main  proc
xor ax,ax
mov   ax, @data     ;hmm ¿seg?
mov   ds,ax          ;ds = ax = saludo

call M

ArchivoCargarUsuario
CargarPunteo
CargarTiempo
GraficarBarras


jmp Exec

ssa:

mov BanderaCargado,0

LabelPrincipal:
Print Titulo1
Print Titulo2
Print Titulo3
NuevaLinea
Print MenuPrincipal1
NuevaLinea
Print MenuPrincipal2
NuevaLinea
Print MenuPrincipal3
NuevaLinea

mov ah,1
int 21h


cmp al,'1'
jz LabelIngresar
cmp al,'2'
jz LabelRegistrar
cmp al,'3'
jz Exec
jmp LabelPrincipal


LabelIngresar:
NuevaLinea
ArchivoCargarUsuario
LoginUsuario

cmp TecladoTemporal[0],'a'
jnz LabelMenuUsuario
cmp TecladoTemporal[1],'d'
jnz LabelMenuUsuario
cmp TecladoTemporal[2],'m'
jnz LabelMenuUsuario
cmp TecladoTemporal[3],'i'
jnz LabelMenuUsuario
cmp TecladoTemporal[4],'n'
jnz LabelMenuUsuario

jmp LabelMenuAdmin



LabelRegistrar:
NuevaLinea
ArchivoCargarUsuario
CrearUsuario
jmp LabelPrincipal

;==============================================Operaciones Usuario
LabelMenuUsuario:
NuevaLinea
Print Titulo1
Print Titulo2
Print Titulo3
NuevaLinea
Print MenuUsuario1
NuevaLinea
Print MenuUsuario2
NuevaLinea
Print MenuUsuario3
NuevaLinea

mov ah,1
int 21h


cmp al,'1'
jz LabelJuego
cmp al,'2'
jz LabelCargaJuego
cmp al,'3'
jz LabelPrincipal
jmp LabelMenuUsuario


LabelJuego:
cmp BanderaCargado,0
jz NoJugar
Juego
jmp LabelMenuUsuario
NoJugar:
NuevaLinea
Print SinCarga
mov ah,1
int 21h
NuevaLinea
jmp LabelMenuUsuario

LabelCargaJuego:
CargarNivel
jmp LabelMenuUsuario

;===================================================Operaciones Admin
LabelMenuAdmin:
NuevaLinea
Print Titulo1
Print Titulo2
Print Titulo3
NuevaLinea
Print MenuAdmin1
NuevaLinea
Print MenuAdmin2
NuevaLinea
Print MenuAdmin3
NuevaLinea

mov ah,1
int 21h


cmp al,'1'
jz LabelTopPuntos
cmp al,'2'
jz LabelTopTiempos
cmp al,'3'
jz LabelPrincipal
jmp LabelMenuAdmin




LabelTopTiempos:
NuevaLinea
TopTiempo
jmp LabelMenuAdmin


LabelTopPuntos:
NuevaLinea
TopPunteo
jmp LabelMenuAdmin


;cmp al, 'a'          
;jne a 
Exec:
NuevaLinea
.exit
main  endp              ;Termina proceso







M proc
local 

call M

ret
M endp


end main

