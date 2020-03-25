

RemoverAtrapadas macro
local Enciclo,Zero,NoZero
mov Aux,64
xor Ax,Ax
xor Bx,Bx
mov Bx,0
Enciclo:
mov al,Libertad[Bx]
cmp al,00h
jz Zero
jmp NoZero
Zero:
mov Tablero[Bx],0h
Nozero:
inc Bx
dec Aux
jnz Enciclo
endm

DesplazarLiber macro
local Enciclo,Zero,NoZero,Sig
mov Aux,64
mov si,00h
Enciclo:
;mov bl,Libertad[si];

;cmp bl,0h
;jz NoZero
;jnz Sig

NoZero:;Libertad es Cero Cuando Hay Algo

mov bl,Tablero[si]
cmp bl,00h
jnz Zero;HAY ALGO
jmp Sig
Zero:
;Desplazar Valores
push SI
xor bx,bx

mov bx,si
mov bh,Tablero[si]
DesplaIzq bl,bh;Ya Tiene El Valor De SI
DesplaDer bl,bh
DesplaArr bl,bh
DesplaAba bl,bh


pop SI
Sig:


inc si
dec Aux
jnz Enciclo
endm

DesplaAba macro Pos,Valor
local PosVerif,Bloqueado,Tope,Mismo,Fini,Blank,Carrito,NoCarrito
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
Bloqueado:
mov dl,2;Esta Bloqueado
jmp FINI
PosVerif:
mov al,Valor
add Si,8;Aumentar Si, Porque No Es Borde
cmp al,Tablero[Si]
JZ Mismo
cmp Tablero[si],00h
JZ Blank
JNZ Bloqueado;Diferente Simbolo
Mismo:


mov al,Libertad[si];El Abajo
sub si,8 ; El Normal
mov ah,Libertad[si]
cmp al,ah

jc Carrito:
mov Libertad[si],al;No Hay Carry
jnc NoCarrito:
Carrito:
add si,8
mov Libertad[si],ah
NoCarrito:


jmp FINI
Blank:
mov dl,0
jmp FINI
Tope:
mov dl,4
FINI:
endm

DesplaArr macro Pos,Valor
local PosVerif,Bloqueado,Tope,Mismo,Fini,Blank,Carrito,NoCarrito
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
Bloqueado:
mov dl,2;Esta Bloqueado
jmp FINI
PosVerif:
mov al,Valor
sub Si,8;Disminuir Si, Porque No Es Borde
cmp al,Tablero[Si]
JZ Mismo
cmp Tablero[si],00h
JZ Blank
JNZ Bloqueado;Diferente Simbolo
Mismo:

mov al,Libertad[si];El Arriba
add si,8 ; El Normal
mov ah,Libertad[si]
cmp al,ah

jc Carrito:
mov Libertad[si],al;No Hay Carry
jnc NoCarrito:
Carrito:
sub si,8
mov Libertad[si],ah
NoCarrito:


jmp FINI
Blank:
mov dl,0
jmp FINI
Tope:
mov dl,4
FINI:
endm


DesplaDer macro Pos,Valor
local PosVerif,Bloqueado,Tope,Mismo,Fini,Blank,Carrito,NoCarrito
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
Bloqueado:
mov dl,2;Esta Bloqueado
jmp FINI
PosVerif:
mov al,Valor
inc Si;Aumentar Si, Porque No Es Borde
cmp al,Tablero[Si]
JZ Mismo
cmp Tablero[si],00h
JZ Blank
JNZ Bloqueado;Diferente Simbolo
Mismo:

mov al,Libertad[si];El Derecho
dec si ; El Normal
mov ah,Libertad[si]
cmp al,ah

jc Carrito:
mov Libertad[si],al
jnc NoCarrito:
Carrito:
inc si
mov Libertad[si],ah
NoCarrito:

jmp FINI
Blank:
mov dl,0
jmp FINI
Tope:
mov dl,4
FINI:
endm



DesplaIzq macro Pos,Valor
local PosVerif,Bloqueado,Tope,Mismo,Fini,Blank,Carrito,NoCarrito
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
Bloqueado:
mov dl,2;Esta Bloqueado
jmp FINI
PosVerif:
mov al,Valor
dec Si;Disminuir Si, Porque No Es Borde
cmp al,Tablero[Si]
JZ Mismo
cmp Tablero[si],00h
JZ Blank
JNZ Bloqueado;Diferente Simbolo
Mismo:;COMPARAR Mayor
mov al,Libertad[si];El Izquierdo
inc si
mov ah,Libertad[si]
cmp al,ah

jc Carrito:
mov Libertad[si],al
jnc NoCarrito:
Carrito:
dec si
mov Libertad[si],ah
NoCarrito:

mov dl,1
jmp FINI
;MismoSimbolo
Blank:
mov dl,0
jmp FINI
Tope:
mov dl,4
FINI:
endm









CalcLiber macro
local Enciclo,minisu,Sig,ZIZ,ZDE,ZAR,ZAB,NZIZ,NZDE,NZAR,NZAB
mov Aux,64
mov si,00h
mov bh,00h;Limpiar Libertad
minisu:
mov Libertad[si],bh
inc si
dec Aux
jnz minisu


mov Aux,64
mov si,00h
Enciclo:

xor bx,bx
mov bx,si
mov bh,Tablero[si]
cmp bh,00h
jz Sig
push si;Guardar Valor Si
mov Aux1,00h;Limpiar Libertad

VerIz bl,bh;Resultado En dl
cmp dl,0
JZ ZIZ
Jmp NZIZ
ZIZ:
inc Aux1
NZIZ:

VerDe bl,bh
cmp dl,0
JZ ZDE
jmp NZDE
ZDE:
inc Aux1
NZDE:

VerAr bl,bh
cmp dl,0
JZ Zar
Jmp NZAR
ZAR:
inc Aux1
NZAR:



VerAb bl,bh
cmp dl,0
JZ ZAB
Jmp NZAB
ZAB:
inc Aux1
NZAB:




pop si;Retomar Valor Si
mov ch,Aux1
mov Libertad[si],ch
sig:
inc si
dec Aux
jnz Enciclo
endm

PrintLiber macro
local Enciclo,Sig,ZIZ,ZDE,ZAR,ZAB,NZIZ,NZDE,NZAR,NZAB
mov Aux,64
mov si,00h
Enciclo:

mov dl,Libertad[si]
add dl,48
mov ah,2
int 21h

inc si
dec Aux
jnz Enciclo
endm


VerAb macro Pos,Valor
local PosVerif,Bloqueado,Tope,Mismo,Fini,Blank
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
Bloqueado:
mov dl,2;Esta Bloqueado
jmp FINI
PosVerif:
mov al,Valor
add Si,8;Aumentar Si, Porque No Es Borde
cmp al,Tablero[Si]
JZ Mismo
cmp Tablero[si],00h
JZ Blank
JNZ Bloqueado;Diferente Simbolo
Mismo:
mov dl,1;MismoSimbolo
jmp FINI
Blank:
mov dl,0
jmp FINI
Tope:
mov dl,4
FINI:
endm

VerAr macro Pos,Valor
local PosVerif,Bloqueado,Tope,Mismo,Fini,Blank
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
Bloqueado:
mov dl,2;Esta Bloqueado
jmp FINI
PosVerif:
mov al,Valor
sub Si,8;Disminuir Si, Porque No Es Borde
cmp al,Tablero[Si]
JZ Mismo
cmp Tablero[si],00h
JZ Blank
JNZ Bloqueado;Diferente Simbolo
Mismo:
mov dl,1;MismoSimbolo
jmp FINI
Blank:
mov dl,0
jmp FINI
Tope:
mov dl,4
FINI:
endm


VerDe macro Pos,Valor
local PosVerif,Bloqueado,Tope,Mismo,Fini,Blank
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
Bloqueado:
mov dl,2;Esta Bloqueado
jmp FINI
PosVerif:
mov al,Valor
inc Si;Aumentar Si, Porque No Es Borde
cmp al,Tablero[Si]
JZ Mismo
cmp Tablero[si],00h
JZ Blank
JNZ Bloqueado;Diferente Simbolo
Mismo:
mov dl,1;MismoSimbolo
jmp FINI
Blank:
mov dl,0
jmp FINI
Tope:
mov dl,4
FINI:
endm

VerIz macro Pos,Valor
local PosVerif,Bloqueado,Tope,Mismo,Fini,Blank
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
Bloqueado:
mov dl,2;Esta Bloqueado
jmp FINI
PosVerif:
mov al,Valor
dec Si;Disminuir Si, Porque No Es Borde
cmp al,Tablero[Si]
JZ Mismo
cmp Tablero[si],00h
JZ Blank
JNZ Bloqueado;Diferente Simbolo
Mismo:
mov dl,1
jmp FINI
;MismoSimbolo
Blank:
mov dl,0
jmp FINI
Tope:
mov dl,4
FINI:
endm


Print macro Texto
   xor ax,ax
   mov   ax, data     ;hmm ¿seg?
   mov   ds,ax          ;ds = ax = saludo
   mov   ah,09          ;Function(print string)
   mov   dx,Offset Texto         ;DX = String terminated by "$"
   int   21h               ;Interruptions DOS Functions
endm
Val0 macro Char
;65  72
    local salir
    cmp Char,65    
    mov dl,00h
    jz salir
    cmp Char,66
    mov dl,01h
    jz salir
    cmp Char,67
    mov dl,02h
    jz salir
    cmp Char,68
    mov dl,03h
    jz salir
    cmp Char,69
    mov dl,04h
    jz salir
    cmp Char,70
    mov dl,05h
    jz salir
    cmp Char,71
    mov dl,06h
    jz salir
    ;Ultimo O No Reconocido
    mov dl,07h    
    salir:
endm
Val1 macro Char
;49  57
    local salir
    cmp Char,49    
    mov dl,07h
    jz salir
    cmp Char,50
    mov dl,06h
    jz salir
    cmp Char,51
    mov dl,05h
    jz salir
    cmp Char,52
    mov dl,04h
    jz salir
    cmp Char,53
    mov dl,03h
    jz salir
    cmp Char,54
    mov dl,02h
    jz salir
    cmp Char,55
    mov dl,01h
    jz salir
    ;Ultimo O No Reconocido
    mov dl,00h    
    salir:
endm
PrintN macro Num
    xor ax,ax
    mov dl,Num ; print 'A'
    mov ah,06h
    add dl,30h
    int 21h
endm
PrintArr macro Arreglo
    local lup
    mov si,00h
    mov bl,5h
    lup:
    mov dl,Arreglo[si]
    mov ah,2
    int 21h
    inc si
    dec bl
    jnz lup
endm

Comparar macro Texto1,Texto2
local iniciocompa,correcto,incorrecto,fincompa
mov si,0h
mov al,5h
iniciocompa:


mov BL,Texto1[si]
mov BH,Texto2[si]


cmp BL,BH
jnz incorrecto;No Es Igual

inc si
dec al
jnz iniciocompa
jmp correcto;Todos Son Iguales


incorrecto:
xor bx,bx
mov bl,35
jmp fincompa
correcto:
xor bx,bx
mov bl,64
jmp fincompa
fincompa:
push bx
endm

Entrada macro
local Input,FININPUT,reinicio
mov si,00h
mov bl,5h
reinicio:
mov Teclado[si],0h
inc si
dec bl
jnz reinicio
mov Teclado[si],0h

mov bl,5h
mov si,00h
mov ah,1;Entrada en al
Input:
int 21h

cmp al,13
JZ FININPUT
mov Teclado[si],al

dec bl
jz FININPUT

inc si
Jnz Input
FININPUT:


mov ax,si
mov Longitud,al
xor si,si
xor ax,ax
xor dx,dx
endm

NuevaLinea macro
MOV dl, 10
MOV ah, 02h
INT 21h
MOV dl, 13
MOV ah, 02h
INT 21h
endm
Espacio macro
MOV dl, 32
MOV ah, 02h
INT 21h
endm

DivX macro
MOV dl, 45
MOV ah, 02h
INT 21h
MOV dl, 45
MOV ah, 02h
INT 21h
MOV dl, 45
MOV ah, 02h
INT 21h
endm

F0 macro
MOV dl, 32
MOV ah, 02h
INT 21h
MOV dl, 32
MOV ah, 02h
INT 21h

endm


PrintJ macro Num
cmp Num,1h    
jz Jug1
jc SinJug
Jnc Jug2
SinJug:
Print J0
jmp SalSelec
Jug1:
Print J1
jmp SalSelec
Jug2:
Print J2
jmp SalSelec
SalSelec:
endm

Exit macro
    mov   ax,4c00h       ;Function (Quit with exit code (EXIT))
int   21h            ;Interruption DOS Functions
endm

fopen macro filename,handle
lea dx,filename
mov ah,3dh
mov al, 00h
int 21h
mov handle,ax
.if carry?
mov ax,-1
.endif
endm

fwrite macro numbytes,databuffer,handle

mov ah,40h
mov bx,handle
mov cx,numbytes
lea dx,databuffer
int 21h
endm

fread macro numbytes,databuffer,handle

mov ah,3fh
mov bx,handle
mov cx,numbytes
lea dx,databuffer
int 21h
endm

fclose macro handle

mov ah,3eh
mov bx,handle
int 21h
endm