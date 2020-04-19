CalcularMaxBarraLargo macro
local lupi,pasar
xor bx,bx
lupi:
mov al,ValoresBarras[bx]
cmp MaxBarraLargo,al
jz pasar
jnc pasar
mov MaxBarraLargo,al
pasar:
inc bx
cmp bx,20
jnz lupi
endm

CalcularMaxBarraAncho macro 
local lupi,pasar
xor bx,bx
lupi:
mov al,ValoresBarras[bx]
cmp al,0
jz pasar
inc MaxBarraAncho
pasar:
inc bx
cmp bx,20
jnz lupi
endm



GraficarBarras macro
local fin
mov MaxBarraLargo,0
mov MaxBarraAncho,0

;mov ValoresBarras[0],18
;mov ValoresBarras[1],25
;mov ValoresBarras[2],26
;mov ValoresBarras[3],2
;mov ValoresBarras[4],27
;mov ValoresBarras[5],17
;mov ValoresBarras[6],12
;mov ValoresBarras[8],25
;mov ValoresBarras[9],14

CalcularMaxBarraLargo
CalcularMaxBarraAncho


DibujarBarrasSolas
mov ah,02;Posicionar Cursor
mov bh,0
mov dh,23;PosY
mov dl,5;PosX
int 10h

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



ActuTiempo macro
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
endm


DibujarBarras macro
local lup,siguiente
;70 De Ancho
;20 De Alto
ActuTiempo

mov ah,6
mov al,0;Lineas 0 
mov bh,00001111b;Atributos
mov ch,0;Comienzo De Linea
mov cl,0;Comienzo COlumna
mov dh,24;Fin Del TExto
mov dl,79;Columna Fin
int 10h


xor si,si
mov ValX,4
lup:

cmp ValoresBarras[si],0
jz siguiente

;==================Largo
CalcularLargo ValoresBarras[si]
;==================Anchi
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

push ax
mov al,bl
xor ah,ah
mov bl,2
div bl
mov bl,al
pop ax

sub al,bl
mov ah,19;Es Al Reves
sub ah,al
mov BarraLargo,ah
add BarraLargo,2
endm

CargarBarrasPunteo macro
local SigUsuario,SigPunteo,SigPunteo,LupUsuario,LupPunteo,pasar,cambiar,lup

xor bx,bx
mov bx,20
lup:
mov ValoresBarras[bx],0
dec bx
jnz lup

xor si,si
xor bx,bx
xor ax,ax
xor cx,cx
xor dx,dx

Mov Regis16,0

mov dl,20
LupUsuario:



cmp InformacionUsuario[si],0
jz SigUsuario


xor bx,bx
mov dh,20
LupPunteo:

cmp InformacionPuntos[bx],0
jz SigPunteo
cmp InformacionPuntos[bx+1],0
jz SigPunteo



mov al,InformacionPuntos[bx+2]
cmp InformacionUsuario[si+0],al
jnz SigPunteo

mov al,InformacionPuntos[bx+3]
cmp InformacionUsuario[si+1],al
jnz SigPunteo

mov al,InformacionPuntos[bx+4]
cmp InformacionUsuario[si+2],al
jnz SigPunteo
mov al,InformacionPuntos[bx+5]
cmp InformacionUsuario[si+3],al
jnz SigPunteo

mov al,InformacionPuntos[bx+6]
cmp InformacionUsuario[si+4],al
jnz SigPunteo


mov al,InformacionPuntos[bx+7]
cmp InformacionUsuario[si+5],al
jnz SigPunteo

mov al,InformacionPuntos[bx+8]
cmp InformacionUsuario[si+6],al
jnz SigPunteo

;======================Son Iguales
push si
mov si,Regis16
mov al,InformacionPuntos[bx+0]
cmp ValoresBarras[si],al
jc cambiar
jmp pasar
cambiar:
mov ValoresBarras[si],al
jmp pasar
pasar:
pop si


SigPunteo:
add bx,9
dec dh
jnz LupPunteo



SigUsuario:

inc Regis16
add si,11
dec dl
jnz LupUsuario

endm 
CargarBarrasTiempo macro
local SigUsuario,SigTiempo,SigPunteo,LupUsuario,LupTiempo,pasar,cambiar,lup

xor bx,bx
mov bx,20
lup:
mov ValoresBarras[bx],0
dec bx
jnz lup

xor si,si
xor bx,bx
xor ax,ax
xor cx,cx
xor dx,dx

Mov Regis16,0

mov dl,20
LupUsuario:



cmp InformacionUsuario[si],0
jz SigUsuario


xor bx,bx
mov dh,20
LupTiempo:

cmp InformacionTiempo[bx],0
jz SigTiempo
cmp InformacionTiempo[bx+1],0
jz SigTiempo



mov al,InformacionTiempo[bx+2]
cmp InformacionUsuario[si+0],al
jnz SigTiempo

mov al,InformacionTiempo[bx+3]
cmp InformacionUsuario[si+1],al
jnz SigTiempo

mov al,InformacionTiempo[bx+4]
cmp InformacionUsuario[si+2],al
jnz SigTiempo

mov al,InformacionTiempo[bx+5]
cmp InformacionUsuario[si+3],al
jnz SigTiempo




mov al,InformacionTiempo[bx+6]
cmp InformacionUsuario[si+4],al
jnz SigTiempo


mov al,InformacionTiempo[bx+7]
cmp InformacionUsuario[si+5],al
jnz SigTiempo

mov al,InformacionTiempo[bx+8]
cmp InformacionUsuario[si+6],al
jnz SigTiempo



;======================Son Iguales
push si
mov si,Regis16
mov al,InformacionTiempo[bx+0]
cmp ValoresBarras[si],al
jc cambiar
jmp pasar
cambiar:
mov ValoresBarras[si],al
jmp pasar
pasar:
pop si


SigTiempo:
add bx,9
dec dh
jnz LupTiempo



SigUsuario:

inc Regis16
add si,11
dec dl
jnz LupUsuario

endm 

DibujarBarrasSolas macro
local lup,siguiente
;70 De Ancho
;20 De Alto


mov ah,6
mov al,0;Lineas 0 
mov bh,00001111b;Atributos
mov ch,0;Comienzo De Linea
mov cl,0;Comienzo COlumna
mov dh,24;Fin Del TExto
mov dl,79;Columna Fin
int 10h


xor si,si
mov ValX,4
lup:

cmp ValoresBarras[si],0
jz siguiente

;==================Largo
CalcularLargo ValoresBarras[si]
;==================Anchi
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