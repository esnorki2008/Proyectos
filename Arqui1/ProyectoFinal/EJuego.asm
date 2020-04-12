EscribirEncabezado macro
local lup1,lup2
mov Registro,12
xor si,si

mov PantallaUsuarioActual[0],'s'
mov PantallaUsuarioActual[4],'q'
mov PantallaUsuarioActual[11],'a'


mov PantallaNivelActual,'1'

lup1:

mov dx,si
add dl,2;Columna
mov Rex,dl
EscribirCaracter 0,Rex,PantallaUsuarioActual[si]

inc si
dec Registro
jnz lup1
;Ultima Posicion De Los Nombres Es 14

EscribirCaracter 0,20,'N'
EscribirCaracter 0,21,PantallaNivelActual

ActualizarPuntos
EscribirCaracter 0,30,PantallaPuntosActual[0]
EscribirCaracter 0,31,PantallaPuntosActual[1]
EscribirCaracter 0,32,PantallaPuntosActual[2]
EscribirCaracter 0,33,PantallaPuntosActual[3]

ActualizarTiempo
EscribirCaracter 0,40,PantallaTiempoActual[0]
EscribirCaracter 0,41,PantallaTiempoActual[1]
EscribirCaracter 0,42,PantallaTiempoActual[2]
EscribirCaracter 0,43,PantallaTiempoActual[3]
EscribirCaracter 0,44,PantallaTiempoActual[4]
EscribirCaracter 0,45,PantallaTiempoActual[5]
EscribirCaracter 0,46,PantallaTiempoActual[6]
EscribirCaracter 0,47,PantallaTiempoActual[7]

endm



EscribirPausa macro
local lup1
mov Registro,72
xor si,si
lup1:

mov dx,si
add dl,2;Columna
mov Rex,dl
EscribirCaracterP 10,Rex,Pausa[si]

inc si
dec Registro
jnz lup1

endm




MeterTiempo macro
local zero,noz

mov bx,4
xor ax,ax
mov ax,ActualTiempo
mov cx,10
zero:
xor dx,dx
div cx
push dx
dec bx
jnz zero


xor bx,4
xor si,si
noz:
pop dx
add dl,48
mov  PantallaTiempoActual[si],dl
inc si
dec bx
jnz noz
endm

ActualizarTiempo macro
local aum,salir,M,S
MOV AH,2CH
INT 21H

cmp Hora,CH
jz M
jnz aum 
M:
cmp Minuto,CL
jz S
jnz aum
S:
cmp Segundo,DH
jz salir
jnz aum
aum:
inc ActualTiempo
jmp salir
salir:

mov Hora,ch
mov Minuto,cl
mov Segundo,dh

MeterTiempo
endm


ActualizarPuntos macro
local zero,noz

mov bx,4
xor ax,ax
mov ax,Punteo
mov cx,10
zero:
xor dx,dx
div cx
push dx
dec bx
jnz zero


xor bx,4
xor si,si
noz:
pop dx
add dl,48
mov  PantallaPuntosActual[si],dl
inc si
dec bx
jnz noz

endm


EscribirCaracter macro Px,Py,Carac

mov ah,2h;Posicionar Cursor
mov bh,0
mov dh,Px;Fila
mov dl,Py;Columna
int 10h

mov ah,9;Letras Especiales
mov al,Carac;Caracter
mov bl,00000111b;Atributos
mov bh,0;Numero Pagina
mov cx,1;Cantidad Veces
int 10h

endm

EscribirCaracterP macro Px,Py,Carac

mov ah,2h;Posicionar Cursor
mov bh,0
mov dh,Px;Fila
mov dl,Py;Columna
int 10h

mov ah,9;Letras Especiales
mov al,Carac;Caracter
mov bl,10000111b;Atributos
mov bh,0;Numero Pagina
mov cx,1;Cantidad Veces
int 10h

endm