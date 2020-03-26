CalcularTerreno macro
local Enciclo,minisu,sig,A1,A2,A3,A4,B1,B2,B3,B4,C1,C2,C3,C4,Limp,NoLimp
mov Aux,64
mov si,00h
mov bh,00h;Limpiar Terreno
minisu:
mov Terreno[si],bh
inc si
dec Aux
jnz minisu

;HASTA AQUI

mov Aux,64
mov si,00h
Enciclo:

xor bx,bx
;Usar Bx
push si;Guardar Valor Si
mov bx,si


cmp Tablero[si],00h
jnz Limp;No Es Libre
jz NoLimp
Limp:
mov dl,0
jmp Sig
NoLimp:
mov FBlanca,0 
mov FNegra,0

TerIz bl;dl salida
cmp dl,0;Es Vacia
jz A1
cmp dl,4;Es Pared
jz A1
cmp dl,2;Es Negra
jz B1
inc FBlanca
jmp A1
B1:
inc FNegra



A1:
TerDe bl
cmp dl,0;Es Vacia
jz A2
cmp dl,4;Es Pared
jz A2
cmp dl,2;Es Negra
jz B2
inc FBlanca
jmp A2
B2:
inc FNegra


A2:
TerAr bl
cmp dl,0;Es Vacia
jz A3
cmp dl,4;Es Pared
jz A3
cmp dl,2;Es Negra
jz B3
inc FBlanca
jmp A3
B3:
inc FNegra

A3:

TerAb bl
cmp dl,0;Es Vacia
jz A4
cmp dl,4;Es Pared
jz A4
cmp dl,2;Es Negra
jz B4
inc FBlanca
jmp A4
B4:
inc FNegra

A4:




;Hay Mas Terreno
cmp FBlanca,0
jz C1
jnz C2

C1:                     ;Blanca 0
cmp FNegra,0
jnz C3

mov dl,0;Ninguna
jmp sig

C2:                     ;Blanca 1
cmp FNegra,0
jnz C4

mov dl,1;Solo Blanca
jmp sig

C3:;Solo Negro

mov dl,2
jmp sig


C4:;Negro Blanco

mov dl,0


sig:

pop si;Retomar Valor Si
;xor bh,bh
;mov si,bx
mov Terreno[si],dl

inc si
dec Aux
jnz Enciclo


endm


PrintTerreno macro
local Enciclo,Sig,ZIZ,ZDE,ZAR,ZAB,NZIZ,NZDE,NZAR,NZAB
mov Aux,64
mov si,00h
Enciclo:

mov dl,Terreno[si]
add dl,48
mov ah,2
int 21h

inc si
dec Aux
jnz Enciclo
endm

TerAb macro Pos


local PosVerif,Tope,Blan,Negr,Blank,Fini
xor ax,ax
xor si,si
mov al,Pos
mov Si,ax
;Esta En EL Borde
cmp Si,56
jz Tope
cmp Si,57
jz Tope
cmp Si,58
jz Tope
cmp Si,59
jz Tope
cmp Si,60
jz Tope
cmp Si,61
jz Tope
cmp Si,62
jz Tope
cmp Si,63
jz Tope
jmp PosVerif

PosVerif:
add si,8
cmp Tablero[si],00h
JZ Blank
cmp Tablero[si],01h
JZ Blan
cmp Tablero[si],02h
JZ Negr


Blan:
mov dl,1
jmp FINI

Negr:
mov dl,2
jmp FINI

Blank:
mov dl,0
jmp FINI
Tope:
mov dl,4
Fini:

endm



TerAr macro Pos


local PosVerif,Tope,Blan,Negr,Blank,Fini
xor ax,ax
xor si,si
mov al,Pos
mov Si,ax
;Esta En EL Borde
cmp Si,0
jz Tope
cmp Si,1
jz Tope
cmp Si,2
jz Tope
cmp Si,3
jz Tope
cmp Si,4
jz Tope
cmp Si,5
jz Tope
cmp Si,6
jz Tope
cmp Si,7
jz Tope
jmp PosVerif

PosVerif:
sub si,8
cmp Tablero[si],00h
JZ Blank
cmp Tablero[si],01h
JZ Blan
cmp Tablero[si],02h
JZ Negr


Blan:
mov dl,1
jmp FINI

Negr:
mov dl,2
jmp FINI

Blank:
mov dl,0
jmp FINI
Tope:
mov dl,4
Fini:

endm

TerDe macro Pos


local PosVerif,Tope,Blan,Negr,Blank,Fini
xor ax,ax
xor si,si
mov al,Pos
mov Si,ax
;Esta En EL Borde
cmp Si,7
jz Tope
cmp Si,15
jz Tope
cmp Si,23
jz Tope
cmp Si,31
jz Tope
cmp Si,39
jz Tope
cmp Si,47
jz Tope
cmp Si,55
jz Tope
cmp Si,63
jz Tope
jmp PosVerif

PosVerif:
inc si
cmp Tablero[si],00h
JZ Blank
cmp Tablero[si],01h
JZ Blan
cmp Tablero[si],02h
JZ Negr


Blan:
mov dl,1
jmp FINI

Negr:
mov dl,2
jmp FINI

Blank:
mov dl,0
jmp FINI
Tope:
mov dl,4
Fini:

endm

TerIz macro Pos


local PosVerif,Tope,Blan,Negr,Blank,Fini
xor ax,ax
xor si,si
mov al,Pos
mov Si,ax
;Esta En EL Borde
cmp Si,0
jz Tope
cmp Si,8
jz Tope
cmp Si,16
jz Tope
cmp Si,24
jz Tope
cmp Si,32
jz Tope
cmp Si,40
jz Tope
cmp Si,48
jz Tope
cmp Si,56
jz Tope
jmp PosVerif

PosVerif:
dec si
cmp Tablero[si],00h
JZ Blank
cmp Tablero[si],01h
JZ Blan
cmp Tablero[si],02h
JZ Negr


Blan:
mov dl,1
jmp FINI

Negr:
mov dl,2
jmp FINI

Blank:
mov dl,0
jmp FINI
Tope:
mov dl,4
FINI:

endm


ReporteIntermedio macro
local Report,B,N,V,S
CreateFile ReporteNormal,handle


OpenFile ReporteNormal,handle
;Inicio
WriteFile handle,HtmlIni,57
;Tabla
WriteFile handle,HtmlTabla,39
;Cuerpo



mov Si,00h
mov ReporteY,8


ReporteMaster:
WriteFile handle,HtmlTr,4
mov ReporteX,8
Report:

cmp Tablero[si],00h
jz V
cmp Tablero[si],01h
jz B
cmp Tablero[si],02h
jz N
B:
WriteFile handle,HtmlBlanca,32
jmp S
N:
WriteFile handle,HtmlNegra,31
jmp S
V:
WriteFile handle,HtmlVacio,30
jmp S

S:
inc si
dec ReporteX
jnz Report

WriteFile handle,HtmlFTr,5
dec ReporteY
jnz ReporteMaster

;Fin Cuerpo
WriteFile handle,HtmlFinTabla,8
;Cerrar
WriteFile handle,HtmlFin,15


;TIEMPO
MOV AH,2AH
INT 21H



xor ax,ax

;La Fecha
mov al,dl
mov dl,10
div dl

add al,48
add ah,48
mov Fecha[0],al
mov Fecha[1],ah
mov Fecha[2],47





xor ax,ax
mov al,dh
mov dl,10
div dl
add al,48
add ah,48

mov Fecha[3],al
mov Fecha[4],ah

mov Fecha[5],47

mov Fecha[6],50 
mov Fecha[7],48
mov Fecha[8],50
mov Fecha[9],48
;Hora
mov Fecha[10],45
MOV AH,2CH
INT 21H




mov Fecha[11],CH
mov Fecha[14],CL
mov Fecha[17],DH

xor ax,ax
mov al,Fecha[11]
mov dl,10
div dl
add ah,48
add al,48
mov Fecha[11],al
mov Fecha[12],ah
mov Fecha[13],58

xor ax,ax
mov al,Fecha[14]
mov dl,10
div dl
add ah,48
add al,48
mov Fecha[14],al
mov Fecha[15],ah
mov Fecha[16],58


xor ax,ax
mov al,Fecha[17]
mov dl,10
div dl
add ah,48
add al,48
mov Fecha[17],al
mov Fecha[18],ah
mov Fecha[19],32


WriteFile handle,HtmlH1,4

WriteFile handle,Fecha,20

WriteFile handle,HtmlFH1,5

CloseFile handle

endm

ConvertirDia macro 


mov ah,0
mov dl,10
div dl
or ax,3030h



endm

OpenFile macro buffer,handler
mov AX,@data
mov DS,AX
mov ah,3dh
mov al,02h
lea dx,buffer
int 21h
;jc Error1 ; db con mensaje que debe existir en doc maestro
mov handler,ax
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
;======================== MACRO CREAR ARCHIVO (any extensio) ===================
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