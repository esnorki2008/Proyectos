EncenderNota macro 
mov ax,si;Frecuencia
out 42h,al;
mov al,ah
out 42h,al

mov al,61h
mov al,11b
out 61h,al
mov ah,86h
mov cx,5h
mov dx,5h
int 15h
nop
endm


ApagarNota macro
mov al,61h
out 61h,al
endm

SonidoBarra macro Regex
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

mov si,100
jmp salida
azul:
mov si,300
jmp salida
amarillo:
mov si,500
jmp salida
verde:
mov si,700
jmp salida
blanco:
mov si,900
jmp salida
salida:


EncenderNota
ApagarNota

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

EstablecerTiempos macro
local Cero,Uno,Dos,Tres,Cuatro,Cinco,Seis,Siete,Ocho,Nueve,Fin
mov mm,2
Print TituloTiempoBarras
NuevaLinea

mov ah,1
int 21h

cmp al,48
jz Cero
cmp al,49
jz Uno
cmp al,50
jz Dos
cmp al,51
jz Tres
cmp al,52
jz Cuatro
cmp al,53
jz Cinco
cmp al,54
jz Seis
cmp al,55
jz Siete
cmp al,56
jz Ocho
jmp Nueve

Cero:
mov rr,21
jmp Fin
Uno:
mov rr,19
jmp Fin
Dos:
mov rr,17
jmp Fin
Tres:
mov rr,15
jmp Fin
Cuatro:
mov rr,13
jmp Fin
Cinco:
mov rr,11
jmp Fin
Seis:
mov rr,9
jmp Fin
Siete:
mov rr,7
jmp Fin
Ocho:
mov rr,5
jmp Fin
Nueve:
mov rr,3
jmp Fin

Fin:



endm