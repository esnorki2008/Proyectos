CargarArchivo macro
local Cancelar,limp,salir,ingre,arroba,nada,malo,bueno,outp
Print TituloIngreseRuta
NuevaLinea

Ruta
cmp ax,1
jz Cancelar

extension
cmp ax,1
jz Cancelar


;CreateFile inputi
OpenFile inputi,handle
cmp ax,1
jz Cancelar
;Leer Todo

;Inicio
;WriteFile handle,HtmlIni,57


xor si,si
xor ax,ax
mov al,119
limp:

mov inputi[si],0

inc si
dec al
jnz limp

ReadFile handle,buffy,119
CloseFile handle

mov cl,buffy[0]
PrintRegistro cl

Cancelar:
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

cmp al,35
jz arroba
mov inputi[si],al;Mover Letra
inc si;Si No Es Arroba
jmp nada
arroba:
inc bl;Cantidad De Arrobas
nada:
jmp ingre 

salir:


cmp bl,4
jz bueno
jnz malo


malo:
Print TituloArroba
NuevaLinea
mov ax,1
jmp outp
bueno:
NuevaLinea
mov ax,0
jmp outp
outp:
mov bx,si



endm

extension macro
local limp,finito,malo,comprobar,H1,H2,H3

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



cmp inputi[si],65
jz H1
cmp inputi[si],97
jz H1

jmp malo
H1:
inc si;Primera Letra


cmp inputi[si],82
jz H2
cmp inputi[si],114
jz H2

jmp malo


H2:
inc si;Primera Letra
cmp inputi[si],81
jz H3
cmp inputi[si],113
jz H3

jmp malo
H3:
mov ax,0
jmp finito
malo:
Print TituloExtension
mov ax,1
finito:
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
mov ax,1
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