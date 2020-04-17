TopPunteo macro 
local lup
mov ah,6
mov al,0;Lineas 0 
mov bh,00001111b;Atributos
mov ch,0;Comienzo De Linea
mov cl,0;Comienzo COlumna
mov dh,24;Fin Del TExto
mov dl,79;Columna Fin
int 10h
mov ah,2h;Posicionar Cursor
mov bh,0
mov dh,0;Fila
mov dl,0;Columna
int 10h

CargarPunteo



Print Top10Puntos
nuevalinea

xor si,si
xor bx,bx
xor ax,ax
lup:
inc ax
push ax
mov Regis8,al
Print8 Regis8
Espacio
Espacio
Espacio

PrintRegistro InformacionPuntos[si+2]
PrintRegistro InformacionPuntos[si+3]
PrintRegistro InformacionPuntos[si+4]
PrintRegistro InformacionPuntos[si+5]
PrintRegistro InformacionPuntos[si+6]
PrintRegistro InformacionPuntos[si+7]
PrintRegistro InformacionPuntos[si+8]

Espacio
Espacio
Espacio

PrintRegistro InformacionPuntos[si+1]

Espacio
Espacio
Espacio

Print8 InformacionPuntos[si+0]

NuevaLinea


pop ax
add si,9
cmp si,90
jnz lup

mov ah,1
int 21h

endm

TopTiempo macro 
local lup
mov ah,6
mov al,0;Lineas 0 
mov bh,00001111b;Atributos
mov ch,0;Comienzo De Linea
mov cl,0;Comienzo COlumna
mov dh,24;Fin Del TExto
mov dl,79;Columna Fin
int 10h
mov ah,2h;Posicionar Cursor
mov bh,0
mov dh,0;Fila
mov dl,0;Columna
int 10h

CargarTiempo



Print Top10Tiempo
nuevalinea

xor si,si
xor bx,bx
xor ax,ax
lup:
inc ax
push ax
mov Regis8,al
Print8 Regis8
Espacio
Espacio
Espacio

PrintRegistro InformacionTiempo[si+2]
PrintRegistro InformacionTiempo[si+3]
PrintRegistro InformacionTiempo[si+4]
PrintRegistro InformacionTiempo[si+5]
PrintRegistro InformacionTiempo[si+6]
PrintRegistro InformacionTiempo[si+7]
PrintRegistro InformacionTiempo[si+8]

Espacio
Espacio
Espacio

PrintRegistro InformacionTiempo[si+1]

Espacio
Espacio
Espacio

Print8 InformacionTiempo[si+0]

NuevaLinea


pop ax
add si,9
cmp si,90
jnz lup

mov ah,1
int 21h

endm