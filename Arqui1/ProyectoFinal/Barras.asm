CargarBarras macro
local SigUsuario,SigTiempo,SigPunteo,LupUsuario,LupTiempo,LupPunteo
xor si,si
xor bx,bx
xor ax,ax
xor cx,cx
xor dx,dx

mov dl,20
LupUsuario:




xor bx,bx
mov dh,20
LupTiempo:

SigTiempo:
dec dh
jnz LupTiempo





xor bx,bx
mov dh,20
LupPunteo:

SigTiempo:
dec dh
jnz LupTiempo



SigUsuario:

dec dl
jnz LupUsuario

endm 

GraficarBarras macro
local fin

CargarBarras

mov CantidadBarras,11

mov ValoresBarras[0],1
mov ValoresBarras[1],25
mov ValoresBarras[2],43
mov ValoresBarras[3],58
mov ValoresBarras[4],77
mov ValoresBarras[5],80
mov ValoresBarras[6],80
mov ValoresBarras[7],86
mov ValoresBarras[8],88
mov ValoresBarras[9],91


DibujarBarras


fin:
endm

CrearBarra macro Px,Alto,Ancho,Valor
xor ax,ax
xor bx,bx


mov ah,6
mov al,0;Lineas 0 
ColorBarra Valor
mov ch,Alto;Comienzo De Linea
mov cl,Px;Comienzo COlumna
mov dh,21;Fin Del TExto
mov dl,cl;Columna Fin
add dl,Ancho;El Grosor De La Columna
int 10h

endm



DibujarBarras macro
local lup,siguiente
;70 De Ancho
;20 De Alto



xor si,si
mov ValX,4
lup:

cmp ValoresBarras[si],0
jz siguiente

;==================Largo
mov BarraLargo,2
mov MaxBarraLargo,91;Acutalizar Este Valor
CalcularLargo ValoresBarras[si]
;==================Anchi
mov BarraAncho,1
mov MaxBarraAncho,10;Actualizar Este Valor
CalcularAncho MaxBarraAncho



NuevaLinea
CrearBarra ValX,BarraLargo,BarraAncho,ValoresBarras[si]
EscribirValorBarra ValoresBarras[si],ValX

mov al,BarraAncho
add ValX,al
add ValX,2

siguiente:
inc si
cmp si,20
jnz lup

endm
 
EscribirValorBarra macro Regex,Pos
mov ah,02;Posicionar Cursor
mov bh,0
mov dh,22;PosY
mov dl,Pos;PosX
int 10h

xor ax,ax
mov al,Regex
mov bl,10
div bl
mov bl,al
add bl,48
push ax;GuardarResidio 

mov ah,9;Letras Especiales
mov al,bl;Caracter
xor bx,bx
mov bl,00001111b
mov bh,0;Numero Pagina
mov cx,1;Cantidad Veces
int 10h

mov ah,02;Posicionar Cursor
mov bh,0
mov dh,22;PosY
mov dl,Pos;PosX
inc dl
int 10h

pop ax
mov bl,ah
add bl,48

mov ah,9;Letras Especiales
mov al,bl;Caracter
xor bx,bx
mov bl,00001111b
mov bh,0;Numero Pagina
mov cx,1;Cantidad Veces
int 10h

endm

ColorBarra macro Regex
local rojo,azul,amarillo,verde,blanco,salida



cmp Regex,21
jc rojo

cmp Regex,41
jc azul

cmp Regex,61
jc amarillo

cmp Regex,81
jc verde

jmp blanco

rojo:
mov bh,01001011b;
jmp salida
azul:
mov bh,00011011b;
jmp salida
amarillo:
mov bh,01101011b;
jmp salida
verde:
mov bh,00101011b;
jmp salida
blanco:
mov bh,01111011b;
jmp salida
salida:

endm

CalcularAncho macro Regex
xor ax,ax
xor dx,dx
xor bx,bx

mov al,58
sub al,Regex
xor ah,ah
div Regex

mov BarraAncho,al
endm


CalcularLargo macro Regex
local z2
xor ax,ax
xor dx,dx
xor bx,bx
xor cx,cx

mov al,MaxBarraLargo
div Regex;Dividiendo Entre El ValorActual
push ax;GuardarResiduo
mov bl,al;Guardar El Valor Division
xor ax,ax
mov al,19;Alto Maximo De La Pantalla
div bl
mov cl,al


pop ax
mov bl,0
cmp ah,0
jz z2
;No Es Cero
mov bl,ah;El Residuo
xor ax,ax
xor dx,dx
mov al,MaxBarraLargo
div bl;Dividiendo Por El Residuo


cmp al,0
jz z2
;No Es Cero
mov bl,al
mov al,19
xor ah,ah
xor dx,dx
div bl
mov bl,al
z2:

mov al,cl
sub al,bl
mov ah,19;Es Al Reves
sub ah,al
mov BarraLargo,ah
add BarraLargo,2
endm