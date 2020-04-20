ReporteTopTiempo macro
local lup,limp
xor si,si
limp:

mov ArchivoDIta[si],32

inc si
cmp si,400
jnz limp

xor si,si
xor bx,bx
xor ax,ax
xor cx,cx
lup:
inc ax
push bx
push ax

xor ah,ah
mov cl,10
div cl
add al,48
add ah,48
mov ArchivoDIta[bx+0],al
mov ArchivoDIta[bx+1],ah
mov ArchivoDIta[bx+2],32
mov ArchivoDIta[bx+3],32
mov ArchivoDIta[bx+4],32

mov al,InformacionTiempo[si+2]
LimpiarAl
mov ArchivoDIta[bx+5],al
mov al,InformacionTiempo[si+3]
LimpiarAl
mov ArchivoDIta[bx+6],al
mov al,InformacionTiempo[si+4]
LimpiarAl
mov ArchivoDIta[bx+7],al
mov al,InformacionTiempo[si+5]
LimpiarAl
mov ArchivoDIta[bx+8],al
mov al,InformacionTiempo[si+6]
LimpiarAl
mov ArchivoDIta[bx+9],al
mov al,InformacionTiempo[si+7]
LimpiarAl
mov ArchivoDIta[bx+10],al
mov al,InformacionTiempo[si+8]
LimpiarAl
mov ArchivoDIta[bx+11],al

mov ArchivoDIta[bx+12],32
mov ArchivoDIta[bx+13],32
mov ArchivoDIta[bx+14],32

mov al,InformacionTiempo[si+1]
LimpiarAl
mov ArchivoDIta[bx+15],al


mov ArchivoDIta[bx+16],32
mov ArchivoDIta[bx+17],32
mov ArchivoDIta[bx+18],32

xor ah,ah
mov al,InformacionTiempo[si+0]
mov cl,10
div cl

add al,48
add ah,48
mov ArchivoDIta[bx+19],al
mov ArchivoDIta[bx+20],ah
mov ArchivoDIta[bx+21],13

pop ax
pop bx
add bx,22
add si,9
cmp si,90
jnz lup



CreateFile ReporteTiempo
OpenFileS ReporteTiempo,handle
WriteFile handle,ArchivoDIta,300
CloseFile handle

endm
LimpiarAl macro
local salida
cmp al,0
jnz salida
mov al,32
salida:
endm
ReporteTopPunteo macro
local lup,limp
xor si,si
limp:

mov ArchivoDIta[si],32

inc si
cmp si,400
jnz limp

xor si,si
xor bx,bx
xor ax,ax
xor cx,cx
lup:
inc ax
push bx
push ax

xor ah,ah
mov cl,10
div cl
add al,48
add ah,48
mov ArchivoDIta[bx+0],al
mov ArchivoDIta[bx+1],ah
mov ArchivoDIta[bx+2],32
mov ArchivoDIta[bx+3],32
mov ArchivoDIta[bx+4],32

mov al,InformacionPuntos[si+2]
LimpiarAl
mov ArchivoDIta[bx+5],al
mov al,InformacionPuntos[si+3]
LimpiarAl
mov ArchivoDIta[bx+6],al
mov al,InformacionPuntos[si+4]
LimpiarAl
mov ArchivoDIta[bx+7],al
mov al,InformacionPuntos[si+5]
LimpiarAl
mov ArchivoDIta[bx+8],al
mov al,InformacionPuntos[si+6]
LimpiarAl
mov ArchivoDIta[bx+9],al
mov al,InformacionPuntos[si+7]
LimpiarAl
mov ArchivoDIta[bx+10],al
mov al,InformacionPuntos[si+8]
LimpiarAl
mov ArchivoDIta[bx+11],al

mov ArchivoDIta[bx+12],32
mov ArchivoDIta[bx+13],32
mov ArchivoDIta[bx+14],32

mov al,InformacionPuntos[si+1]
LimpiarAl
mov ArchivoDIta[bx+15],al


mov ArchivoDIta[bx+16],32
mov ArchivoDIta[bx+17],32
mov ArchivoDIta[bx+18],32

xor ah,ah
mov al,InformacionPuntos[si+0]
mov cl,10
div cl

add al,48
add ah,48
mov ArchivoDIta[bx+19],al
mov ArchivoDIta[bx+20],ah
mov ArchivoDIta[bx+21],13

pop ax
pop bx
add bx,22
add si,9
cmp si,90
jnz lup



CreateFile ReportePuntos
OpenFileS ReportePuntos,handle
WriteFile handle,ArchivoDIta,300
CloseFile handle

endm

TopPunteo macro 
local lup,barra,barra2,limp
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
ArchivoCargarUsuario
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

xor si,si
limp:
mov ValoresBarras,0
inc si
cmp si,20
jnz limp


ReporteTopPunteo

barra:
mov ah,1
int 21h
cmp al,32
jnz barra




;CargarPunteo
CargarBarrasPunteo
GraficarBarras

EscribirTopPuntos

barra2:
mov ah,1
int 21h
cmp al,32
jnz barra2

InicializarOdenamiento

endm



LLamarOrdenamiento macro
local Burbuja,Quicksrot,Shellsort,BAsc,BDesc,QAsc,QDesc,SAsc,SDesc,Saliq
CargarLetreroFinal
mov ActualTiempo,0
MOV AH,2CH
INT 21H
mov Hora,CH
mov Minuto,CL
mov Segundo,DH


cmp TipoOrden,0
jz Burbuja
cmp TipoOrden,1
jz Quicksrot
jmp Shellsort
Burbuja:
mov LetreroFinal[14],'B'
mov LetreroFinal[15],'U'
mov LetreroFinal[16],'B'
mov LetreroFinal[17],'B'
mov LetreroFinal[18],'L'
mov LetreroFinal[19],'E'
mov LetreroFinal[20],'S'
mov LetreroFinal[21],'O'
mov LetreroFinal[22],'R'
mov LetreroFinal[23],'T'

cmp Orientacion,1
jz BAsc
jmp BDesc

BAsc:
call BurbujaAscendente
jmp Saliq
BDesc:
call BurbujaDescendente
jmp Saliq


Quicksrot:
mov LetreroFinal[14],'Q'
mov LetreroFinal[15],'U'
mov LetreroFinal[16],'I'
mov LetreroFinal[17],'C'
mov LetreroFinal[18],'K'
mov LetreroFinal[19],'S'
mov LetreroFinal[20],'O'
mov LetreroFinal[21],'R'
mov LetreroFinal[22],'T'
mov LetreroFinal[23],32

cmp Orientacion,1
jz QAsc
jmp QDesc
QAsc:
Call CargaValores
mov RegistroSort3,0
mov RegistroSort4,19
call QSAscendente
jmp Saliq
QDesc:
Call CargaValores
mov RegistroSort3,0
mov RegistroSort4,19
call QSDescendente
jmp Saliq


Shellsort:
mov LetreroFinal[14],'S'
mov LetreroFinal[15],'H'
mov LetreroFinal[16],'E'
mov LetreroFinal[17],'L'
mov LetreroFinal[18],'L'
mov LetreroFinal[19],'S'
mov LetreroFinal[20],'O'
mov LetreroFinal[21],'R'
mov LetreroFinal[22],'T'
mov LetreroFinal[23],32
cmp Orientacion,1
jz SAsc
jmp SDesc
SAsc:
call SSAscendente
jmp Saliq
SDesc:
call SSDescendente
jmp Saliq

Saliq:
endm

InicializarOdenamiento macro
local Obs,Oqs,Oss,OSig,desce,ascen,OrSig,barrita
NuevaLinea
Print TituloOrden1
NuevaLinea
Print TituloOrden2
NuevaLinea
Print TituloOrden3
NuevaLinea
Print TituloOrden4
NuevaLinea
mov ah,1
int 21h

cmp al,49
jz Obs
cmp al,50
jz Oqs
cmp al,51
jz Oss

Obs:
mov TipoOrden,0
jmp OSig
Oqs:
mov TipoOrden,1
jmp OSig
Oss:
mov TipoOrden,2
jmp OSig
OSig:

EstablecerTiempos

NuevaLinea
Print TituloMovimiento1
NuevaLinea
Print TituloMovimiento2
NuevaLinea
Print TituloMovimiento3
mov ah,1
int 21h



cmp al,49
jz desce
cmp al,50
jz ascen


desce:
mov Orientacion,0
jmp OrSig
ascen:
mov Orientacion,1
jmp OrSig
OrSig:

CargarLetreroFinal
LLamarOrdenamiento
barrita:
mov ah,1
int 21h
cmp al,32
jnz barrita
endm


TopTiempo macro 
local lup,barra,barra2,limp
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

ArchivoCargarUsuario
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

ReporteTopTiempo

barra:
mov ah,1
int 21h
cmp al,32
jnz barra

xor si,si
limp:
mov ValoresBarras,0
inc si
cmp si,20
jnz limp

;CargarTiempo
CargarBarrasTiempo
GraficarBarras

EscribirTopTiempo

barra2:
mov ah,1
int 21h
cmp al,32
jnz barra2

InicializarOdenamiento
endm

EscribirTopPuntos macro 
EscribirCaracter 0,20,'T'
EscribirCaracter 0,21,'o'
EscribirCaracter 0,22,'p'
EscribirCaracter 0,23,32
EscribirCaracter 0,24,'1'
EscribirCaracter 0,25,'0'
EscribirCaracter 0,26,32
EscribirCaracter 0,27,'P'
EscribirCaracter 0,28,'o'
EscribirCaracter 0,29,'r'
EscribirCaracter 0,30,32
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
EscribirCaracter 0,23,32
EscribirCaracter 0,24,'1'
EscribirCaracter 0,25,'0'
EscribirCaracter 0,26,32
EscribirCaracter 0,27,'P'
EscribirCaracter 0,28,'o'
EscribirCaracter 0,29,'r'
EscribirCaracter 0,30,32
EscribirCaracter 0,31,'T'
EscribirCaracter 0,32,'i'
EscribirCaracter 0,33,'e'
EscribirCaracter 0,34,'m'
EscribirCaracter 0,35,'p'
EscribirCaracter 0,36,'o'
endm

EscribirLetreroFinal macro
local enci
xor bx,bx
mov al,Velocidad
mov LetreroFinal[52],al
enci:
push bx
EscribirCaracter 0,bl,LetreroFinal[bx]
pop bx
inc bx
cmp bx,53
jnz enci
endm

CargarLetreroFinal macro
mov LetreroFinal[0],'O'
mov LetreroFinal[1],'R'
mov LetreroFinal[2],'D'
mov LetreroFinal[3],'E'
mov LetreroFinal[4],'N'
mov LetreroFinal[5],'A'
mov LetreroFinal[6],'M'
mov LetreroFinal[7],'I'
mov LetreroFinal[8],'E'
mov LetreroFinal[9],'N'
mov LetreroFinal[10],'T'
mov LetreroFinal[11],'O'
mov LetreroFinal[12],58
mov LetreroFinal[13],32 

mov LetreroFinal[24],32
mov LetreroFinal[25],32
mov LetreroFinal[26],32
mov LetreroFinal[27],'T'
mov LetreroFinal[28],'I'
mov LetreroFinal[29],'E'
mov LetreroFinal[30],'M'
mov LetreroFinal[31],'P'
mov LetreroFinal[32],'O'
mov LetreroFinal[33],58
mov LetreroFinal[34],'0'
mov LetreroFinal[35],'0'
mov LetreroFinal[36],'0'
mov LetreroFinal[37],'0'
mov LetreroFinal[38],32
mov LetreroFinal[39],32
mov LetreroFinal[40],32
mov LetreroFinal[41],'V'
mov LetreroFinal[42],'E'
mov LetreroFinal[43],'L'
mov LetreroFinal[44],'O'
mov LetreroFinal[45],'C'
mov LetreroFinal[46],'I'
mov LetreroFinal[47],'D'
mov LetreroFinal[48],'A'
mov LetreroFinal[49],'D'
mov LetreroFinal[50],58
mov LetreroFinal[51],32
push ax
mov al,Velocidad
mov LetreroFinal[52],al
pop ax
endm