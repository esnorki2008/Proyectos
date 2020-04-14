
CargarPuntajes macro
endm


ArchivoGuardarUsuario macro
local ArchivoAbierto
OpenFileS ArchivoUsuario,handle
cmp ax,1
jnz ArchivoAbierto
CreateFile ArchivoUsuario
OpenFileS ArchivoUsuario,handle
ArchivoAbierto:
mov InformacionUsuario[0],'a'
mov InformacionUsuario[1],'d'
mov InformacionUsuario[2],'m'
mov InformacionUsuario[3],'i'
mov InformacionUsuario[4],'n'
mov InformacionUsuario[7],'1'
mov InformacionUsuario[8],'2'
mov InformacionUsuario[9],'3'
mov InformacionUsuario[10],'4'
WriteFile handle,InformacionUsuario,1000
CloseFile handle
endm


ArchivoCargarUsuario macro
local ArchivoAbierto
OpenFileS ArchivoUsuario,handle
cmp ax,1
jnz ArchivoAbierto
CreateFile ArchivoUsuario
OpenFileS ArchivoUsuario,handle
ArchivoAbierto:
mov InformacionUsuario[0],'a'
mov InformacionUsuario[1],'d'
mov InformacionUsuario[2],'m'
mov InformacionUsuario[3],'i'
mov InformacionUsuario[4],'n'
mov InformacionUsuario[7],'1'
mov InformacionUsuario[8],'2'
mov InformacionUsuario[9],'3'
mov InformacionUsuario[10],'4'
ReadFile handle,InformacionUsuario,891
CloseFile handle
endm





CargarNivel macro
local Cancelar,limp,salir,ingre,arroba,nada,malo,bueno,outp,limp,recor
NuevaLinea
Print TituloIngreseRuta
NuevaLinea

Ruta
cmp ax,1
jz Cancelar

extension
cmp ax,1
jz Cancelar

OpenFile inputi,handle
cmp ax,1
jz Cancelar

xor si,si
xor ax,ax
mov ax,500
limp:

mov InformacionNiveles[si],0

inc si
dec ax
jnz limp


xor si,si
xor ax,ax
mov ax,42
limp2:

mov InformacionRealNiveles[si],0

inc si
dec ax
jnz limp2

ReadFile handle,InformacionNiveles,500
CloseFile handle


cmp ax,1
jz Cancelar


xor si,si
xor ax,ax
mov cx,500
recor:

PrintRegistro InformacionNiveles[si]

inc si
dec cx
jnz recor

NivelesParser;=========================Cargar Niveles A Memoria

NuevaLinea



Cancelar:
NuevaLinea
mov ah,1;Entrada en al
int 21h
endm

NivelesParser macro
local fin,lup1
xor si,si
xor dx,dx
xor bx,bx
lup1:

ParserNivel
cmp si,400
jz fin

ParserTiempoNivel
ParserTiempoObstaculos
ParserTiempoPremio
ParserPuntosObstaculo
ParserPuntosPremios
ParserColor


cmp InformacionNiveles[si],0
jnz lup1
fin:
endm

ParserNivel macro
local lop1,lup2,fin
lup1:
inc si
cmp InformacionNiveles[si],59 
jnz lup1

cmp si,400
jz fin

lup2:
inc si
mov al,InformacionNiveles[si]
mov InformacionRealNiveles[bx],al
inc bx
inc si
fin:
endm

ParserTiempoNivel macro
local fin
inc si;quitar el puntocoma
xor ax,ax


;============Colocar Primer Numero
mov al,InformacionNiveles[si]
sub al,48
mov InformacionRealNiveles[bx],al
inc si
;===========Solo Hay Uno


cmp InformacionNiveles[si],59 
jz fin
;===========Hay Otro



mov dl,10
xor ah,ah
mul dl
mov dl,al

mov al,InformacionNiveles[si]
sub al,48
add al,dl
mov InformacionRealNiveles[bx],al
inc si
fin:
inc bx
endm

ParserTiempoObstaculos macro
local fin
inc si;quitar el puntocoma
xor ax,ax
;============Colocar Primer Numero
mov al,InformacionNiveles[si]
sub al,48
mov InformacionRealNiveles[bx],al
inc si
;===========Solo Hay Uno
cmp InformacionNiveles[si],59 
jz fin
;===========Hay Otro
mov dl,10
xor ah,ah
mul dl
mov dl,al

mov al,InformacionNiveles[si]
sub al,48
add al,dl
mov InformacionRealNiveles[bx],al
inc si
fin:
inc bx
endm

ParserTiempoPremio macro
local fin
inc si;quitar el puntocoma
xor ax,ax
;============Colocar Primer Numero
mov al,InformacionNiveles[si]
sub al,48
mov InformacionRealNiveles[bx],al
inc si
;===========Solo Hay Uno
cmp InformacionNiveles[si],59 
jz fin
;===========Hay Otro
mov dl,10
xor ah,ah
mul dl
mov dl,al

mov al,InformacionNiveles[si]
sub al,48
add al,dl
mov InformacionRealNiveles[bx],al
inc si
fin:
inc bx
endm

ParserColor macro 
local fin,lup1
inc si;quitar el puntocoma
xor ax,ax
push bx
xor bx,bx
lup1:



cmp InformacionNiveles[si],13
jz fin
cmp InformacionNiveles[si],0
jz fin
cmp InformacionNiveles[si],10
jz fin

mov al,InformacionNiveles[si]
mov TecladoTemporal[bx],al

inc bx
inc si
jmp lup1
fin:
pop bx
Colores
mov InformacionRealNiveles[bx],al
inc si
inc bx
endm

Colores macro 
local rojo,verde,blanco,fin
;PrintRegistro TecladoTemporal[0]
;PrintRegistro TecladoTemporal[1]

mov al,0;Color Azul
rojo:
cmp TecladoTemporal[0],'r'
jnz verde
cmp TecladoTemporal[1],'o'
jnz verde
mov al,1;Color Rojo
jmp fin
verde:
cmp TecladoTemporal[0],'v'
jnz blanco
cmp TecladoTemporal[1],'e'
jnz blanco
mov al,2;Color verde
jmp fin
blanco:
cmp TecladoTemporal[0],'b'
jnz fin
cmp TecladoTemporal[1],'l'
jnz fin
mov al,3;Color blanco
jmp fin
fin:
endm

ParserPuntosPremios macro
local fin
inc si;quitar el puntocoma
xor ax,ax
;============Colocar Primer Numero
mov al,InformacionNiveles[si]
sub al,48
mov InformacionRealNiveles[bx],al
inc si
;===========Solo Hay Uno
cmp InformacionNiveles[si],59 
jz fin
;===========Hay Otro
mov dl,10
xor ah,ah
mul dl
mov dl,al

mov al,InformacionNiveles[si]
sub al,48
add al,dl
mov InformacionRealNiveles[bx],al
inc si
fin:
inc bx
endm

ParserPuntosObstaculo macro
local fin
inc si;quitar el puntocoma
xor ax,ax
;============Colocar Primer Numero
mov al,InformacionNiveles[si]
sub al,48
mov InformacionRealNiveles[bx],al
inc si
;===========Solo Hay Uno
cmp InformacionNiveles[si],59 
jz fin
;===========Hay Otro
mov dl,10
xor ah,ah
mul dl
mov dl,al

mov al,InformacionNiveles[si]
sub al,48
add al,dl
mov InformacionRealNiveles[bx],al
inc si
fin:
inc bx
endm



Ruta macro
local limp,salir,ingre,arroba,nada,malo,bueno,outp
mov AX,@data
mov DS,AX
xor si,si
xor ax,ax
mov al,50
limp:
mov inputi[si],0
inc si
dec al
jnz limp
xor si,si
xor ax,ax
xor bx,bx
ingre:
mov ah,1;Entrada en al
int 21h
cmp al,13
jz salir
mov inputi[si],al;Mover Letra
inc si;Si No Es Arroba
jmp ingre 
salir:
mov ax,0
mov bx,si
endm

extension macro
local limp,finito,malo,comprobar,H1,H2,H3,H4

xor si,si
xor ax,ax
mov al,40
limp:
cmp inputi[si],46
jz comprobar


inc si
dec al
jnz limp
jmp malo
comprobar:
inc si;Primera Letra



cmp inputi[si],80
jz H1
cmp inputi[si],112
jz H1

jmp malo
H1:
inc si;Primera Letra


cmp inputi[si],76
jz H2
cmp inputi[si],108
jz H2

jmp malo


H2:
inc si;Primera Letra
cmp inputi[si],65
jz H3
cmp inputi[si],97
jz H3
jmp malo
H3:
inc si;Primera Letra
cmp inputi[si],89
jz H3
cmp inputi[si],121
jz H3
H4:
mov ax,0
jmp finito
malo:
Print TituloExtension
mov ax,1
finito:
endm


OpenFileS macro buffer,handler
local erro,fini
mov AX,@data
mov DS,AX
mov ah,3dh
mov al,02h
lea dx,buffer
int 21h
jc Erro ; db con mensaje que debe existir en doc maestro
mov handler,ax
mov ax,0
jmp fini
erro:
;Print TItuloErrorArchivo
mov ax,1
fini:
endm
OpenFile macro buffer,handler
local erro,fini
mov AX,@data
mov DS,AX
mov ah,3dh
mov al,02h
lea dx,buffer
int 21h
jc Erro ; db con mensaje que debe existir en doc maestro
mov handler,ax
mov ax,0
jmp fini
erro:
Print TItuloErrorArchivo
mov ax,1
fini:
endm
;============== MACRO CERRAR ARCHIVO==============
CloseFile macro handler
;mov checkopenfile,1
mov AX,@data
mov DS,AX
mov ah,3eh
mov bx,handler
int 21h
;jc Error2 ; db con mensaje que debe existir en doc maestro
endm

;=========== MACRO LEER ARCHIVO===========
ReadFile macro handler,buffer,numbytes
mov AX,@data
mov DS,AX
mov ah,3fh
mov bx,handler
mov cx,numbytes ; numero maximo de bytes a leer(para proyectos hacerlo gigante) 
lea dx,buffer
int 21h
;jc Error4 ; db con mensaje que debe existir en doc maestro
endm

; pendiente el de crear escribir
;======================== MACRO CREAR ARCHIVO (any extension) ===================
CreateFile macro buffer,handler
mov AX,@data
mov DS,AX
mov ah,3ch
mov cx,00h
lea dx,buffer
int 21h
;jc Error4
mov bx,ax
mov ah,3eh
int 21h
endm
; ========================= MACRO ESCRIBIR EN ARCHIVO YA CREADO =================

WriteFile macro handler,buffer,numbytes
mov AX,@data
mov DS,AX
mov ah,40h
mov bx,handler
mov cx,numbytes
lea dx, buffer
int 21h
endm