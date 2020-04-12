include Macros.asm ; archivo con los macros
include PJuego.asm
include DJuego.asm
include EJuego.asm
include GJuego.asm

.model small
.stack 500h
.data

Titulo1  db "UNIVERSIDAD DE SAN CARLOS DE GUATEMALA",10,13,"$"   
Titulo2 db "ARQUITECTURA DE COMPUTADORES Y ENSAMBLADORES 1A",10,13,"NOMBRE: ANDHY LIZANDRO SOLIS OSORIO",10,13,"$"
Titulo3 db "CARNET: 201700886",10,13,"SECCION: A",10,13,"$" 

Pausa db "Juego En Pausa Presione, Espacio Para Salir O Presione ESC para Regresar" 
;=============Variables Del Juego=============
JugadorActual db 12 dup(0)
PosX db 0

Punteo dw 0

Obstaculo db 20 dup(0)
ObstaculoY db 20 dup(0)
ObstaculoX db 20 dup(0)

Bonus db 20 dup(0)
BonusY db 20 dup(0)
BonusX db 20 dup(0)

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

Registro dw 0

Rex db 0
;=============Variables De La PantallaJuego==============
PantallaUsuarioActual db 12 dup (0)
PantallaNivelActual db 0 
PantallaPuntosActual db 4 dup(0)
PantallaTiempoActual db 8 dup(0)
Regis16 dw 0
Regis8 db 0
;===============Delay===============
mm dw 0
rr dw 0
.code

main  proc
xor ax,ax
mov   ax, @data     ;hmm ¿seg?
mov   ds,ax          ;ds = ax = saludo




Juego






;CargarPantallaJuego



;xor ax,ax
;mov Regis16,ax

;mov ah, 00h 
;int 16h 

;push ax
;mov Regis8,ah
;Print8 Regis8
;pop ax
;mov Regis8,al
;Print8 Regis8




;cmp al, 'a'          
;jne a 
Exec:
.exit
main  endp              ;Termina proceso
end main

