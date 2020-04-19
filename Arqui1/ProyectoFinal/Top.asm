TopPunteo macro 
local lup,barra,barra2
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


barra:
mov ah,1
int 21h
cmp al,32
jnz barra

CargarBarrasPunteo
GraficarBarras
EscribirTopPuntos

barra2:
mov ah,1
int 21h
cmp al,32
jnz barra2
endm

TopTiempo macro 
local lup,barra,barra2
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

barra:
mov ah,1
int 21h
cmp al,32
jnz barra

CargarBarrasTiempo
GraficarBarras
EscribirTopTiempo

barra2:
mov ah,1
int 21h
cmp al,32
jnz barra2

endm

EscribirTopPuntos macro 
EscribirCaracter 0,20,'T'
EscribirCaracter 0,21,'o'
EscribirCaracter 0,22,'p'
EscribirCaracter 0,23,' '
EscribirCaracter 0,24,'1'
EscribirCaracter 0,25,'0'
EscribirCaracter 0,26,' '
EscribirCaracter 0,27,'P'
EscribirCaracter 0,28,'o'
EscribirCaracter 0,29,'r'
EscribirCaracter 0,30,' '
EscribirCaracter 0,31,'P'
EscribirCaracter 0,32,'u'
EscribirCaracter 0,33,'n'
EscribirCaracter 0,34,'t'
EscribirCaracter 0,35,'u'
EscribirCaracter 0,36,'a'
EscribirCaracter 0,37,'c'
EscribirCaracter 0,38,'i'
EscribirCaracter 0,39,'o'
EscribirCaracter 0,40,'n'
endm

EscribirTopTiempo macro 
EscribirCaracter 0,20,'T'
EscribirCaracter 0,21,'o'
EscribirCaracter 0,22,'p'
EscribirCaracter 0,23,' '
EscribirCaracter 0,24,'1'
EscribirCaracter 0,25,'0'
EscribirCaracter 0,26,' '
EscribirCaracter 0,27,'P'
EscribirCaracter 0,28,'o'
EscribirCaracter 0,29,'r'
EscribirCaracter 0,30,' '
EscribirCaracter 0,31,'T'
EscribirCaracter 0,32,'i'
EscribirCaracter 0,33,'e'
EscribirCaracter 0,34,'m'
EscribirCaracter 0,35,'p'
EscribirCaracter 0,36,'o'
endm