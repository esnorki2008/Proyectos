IntegrarFuncion macro
local error,fin,lup,Limpiar,fin,Uno
xor si,si
xor ax,ax
mov al,6
mov ah,0
limpiar:

mov Integral[si],ah
mov IntegralBandera[si],ah
mov Decimal1[si],ah
mov Decimal2[si],ah

inc si
dec al
jnz limpiar

mov al,00h
cmp ExisteFuncion,al
jz Error;No Hay Funcion

xor ax,ax
xor cx,cx
xor si,si
mov cl,5
lup:



xor ax,ax
mov al,Funcion[si]
mov ch,cl


div ch;Division
mov Integral[si],al ;Guardar Derivada




mov al,FuncionBandera[si]
mov IntegralBandera[si],al;Copiar Signo
inc si
dec cl
jnz lup
jmp fin


error:
Print TituloSinFuncion
fin:
endm

ImprimirIntegralR macro
local salto,Signo,Zero,fin,Error

mov al,00h
cmp ExisteFuncion,al
jz Error

mov Registro,5
xor si,si
salto:
mov al,Integral[si]
mov ciclo,al
PrintN ciclo

inc si
dec Registro
jnz salto

Error:
NuevaLinea

endm


ImprimirIntegral macro
local salto,Signo,Zero,fin,Error

mov al,00h
cmp ExisteFuncion,al
jz Error


NuevaLinea
print TituloIntegral
xor ax,ax
xor si,si
mov si,0
mov cl,5
mov Registro,5
salto:



Espacio
cmp Funcion[si],0
jz zero
mov ch,43
cmp IntegralBandera[si],0;La Bandera Indica Cero
jz Signo
mov ch,45;Es Menos
jmp Signo
Signo:
PrintRegistro ch;Imprmir Signo
Espacio

mov ch,Funcion[si]
PrintN ch;Imprimiendo 1
mov ch,47
PrintRegistro ch;Imprimiendo Barra/
mov ch,Registro

PrintN ch;Imprimir Potencia





cmp Registro,00h
;jz Zero
mov ch,42
PrintRegistro ch
mov ch,120
PrintRegistro ch


mov ch,Registro

PrintN ch;Imprimiendo Potenci

Zero:

dec Registro
inc si
dec cl
jnz salto
jz fin
Error:
Print TituloSinFuncion
fin:

Espacio
mov ch,43
PrintRegistro ch
Espacio
mov ch,99
PrintRegistro ch

NuevaLinea

endm




DerivarFuncion macro
local error,fin,lup,Limpiar,fin
xor si,si
xor ax,ax
mov al,4
mov ah,0
limpiar:

mov Derivada[si],ah
mov DerivadaBandera[si],ah

inc si
dec al
jnz limpiar


mov al,00h
cmp ExisteFuncion,al
jz Error;No Hay Funcion

xor ax,ax
xor cx,cx
xor si,si
mov cl,4
lup:

mov al,cl;Moviendo Para Multiplicar
mov ch,Funcion[si]
mul ch

mov Derivada[si],al ;Guardar Derivada
mov al,FuncionBandera[si]
mov DerivadaBandera[si],al;Copiar Signo


inc si
dec cl
jnz lup
jmp fin

error:
Print TituloSinFuncion
fin:

endm


ImprimirDerivada macro
local salto,Signo,Zero,fin,Error

mov al,00h
cmp ExisteFuncion,al
jz Error


NuevaLinea
print TituloDerivada
xor ax,ax
xor si,si
mov si,0
mov cl,4
mov Registro,4
salto:

Espacio
cmp Derivada[si],0
jz zero
mov ch,43
cmp DerivadaBandera[si],0;La Bandera Indica Cero
jz Signo
mov ch,45;Es Menos
jmp Signo
Signo:
PrintRegistro ch
Espacio
mov ch,Derivada[si]

xor ax,ax
mov al,ch
mov ch,10
div ch
mov Contador,al
mov ch,ah
PrintNZ Contador;Imprimiendo Derivada
PrintN ch



cmp Registro,01h
jz Zero
mov ch,42
PrintRegistro ch
mov ch,120
PrintRegistro ch


mov ch,Registro
dec ch
PrintN ch;Imprimiendo Potenci

Zero:

dec Registro
inc si
dec cl
jnz salto
jz fin
Error:
Print TituloSinFuncion
fin:
NuevaLinea


endm





IngresarFuncion  MACRO 
local limp,subrutina,uno,dos,salidanum,malnum,negar,posit
xor cx,cx
mov cl,5
xor si,si
limp:;Limpiar Variables
mov ch,0
mov FuncionBandera[si],ch
mov Funcion[si],ch
inc si
dec cl
jnz limp

xor cx,cx
xor si,si
mov si,0000h
mov cl,5
NuevaLinea
subrutina:
mov almacenar,si

Print cof
mov Registro,cl
dec Registro
PrintN Registro

mov Registro,58
PrintRegistro Registro


mov Registro,2
Entrada Registro;Devuelve La Longitud De La Cadena
mov al,Teclado[0]
mov Registro,al
cmp longitud,1; Ver la longitud de la cadena
jz uno
cmp Teclado[0],43;Es Positiva
jz posit
cmp Teclado[0],45; Es Negativa
jz negar
jmp malnum

posit:
mov al,Teclado[1]
mov Registro,al
jmp uno
negar:
mov al,Teclado[1]
mov Registro,al
mov si,almacenar
mov FuncionBandera[si],1
uno:;Ingresando El Numero

EsNumero Registro

jnz malnum;No Es Un Numero
xor ax,ax;Es Correcto
mov al,Registro
mov si,almacenar
mov Funcion[si],al
jmp salidanum
malnum:
mov si,almacenar
NuevaLinea
jmp subrutina ; El signo o los numeros estan mal

salidanum:
NuevaLinea
mov si,almacenar
inc si
dec cl
jnz subrutina
mov ExisteFuncion,1;Indicar Que Existe Funcion
ENDM


IngresarRango  MACRO 
local fin,inicio,uno,dos,tres,N2,N3,SN2,SN3,NN2,NN3
inicio:
Print TituloRango
xor ax,ax
xor cx,cx
xor si,si
mov RangoEntrada,0;GUardar Valores
mov RangoEntradaBandera,0


mov al,3
mov Registro,al;Cantidad De Caracteres Maximos
Entrada Registro;Devuelve La Longitud De La Cadena
mov al,Longitud

cmp al,0
jz inicio;No Hay Texto



cmp al,1
jz uno;uno
cmp al,2
jz dos;dos
cmp al,3
jz tres;tres

;=======================Un Caracter================
uno:
mov al,Teclado[0]




mov Registro,al
EsNumero Registro
jnz inicio;No Es Un Numero
mov al,Registro



mov RangoEntrada,al;Es Solo Un Numero 
mov ah,0
mov RangoEntradaBandera,ah
jmp fin
;=======================Dos Caracter================
dos:
xor si,si;Poner en 0
mov ah,0
mov RangoEntradaBandera,ah;Iniciar Positivo
mov al,Teclado[si]



cmp Teclado[si],43
jz SN2;Es Positivo
cmp Teclado[si],45;Es Negativo
jnz NN2;Es Numero
mov ah,1
mov RangoEntradaBandera,ah;Negar ENtrada
SN2:
inc si;Incrementar Si
NN2:;Si Es Numero SI es 0

mov al,Teclado[si]
mov Registro,al
EsNumero Registro
jnz inicio;No Es Un Numero
mov al,Registro
mov RangoEntrada,al;Es Un Numero 
inc si;Aumentar Si
cmp si,2
jz fin;Ya Se Alcanzo el limite
xor ah,ah;
mov bl,10
mul bl;Multiplicar Decena
xor bx,bx
mov RangoEntrada,al;Actualizar A Decena


mov al,Teclado[si]
mov Registro,al
EsNumero Registro
jnz inicio;No Es Un Numero
mov al,Registro
mov ah,RangoEntrada
add ah,al
mov RangoEntrada,ah;Actualizar 


jmp fin
;=======================Tres Caracter================
tres:
mov Simbolo,0
xor si,si;Poner en 0
mov ah,0
mov RangoEntradaBandera,ah;Iniciar Positivo
mov al,Teclado[si]



cmp Teclado[si],43
jz SN3;Es Positivo
cmp Teclado[si],45;Es Negativo
jnz NN3;Es Numero
mov ah,1
mov RangoEntradaBandera,ah;Negar ENtrada
SN3:
mov Simbolo,1
inc si;Incrementar Si
NN3:;Si Es Numero SI es 0

mov al,Teclado[si]
mov Registro,al
EsNumero Registro
jnz inicio;No Es Un Numero
mov al,Registro
mov RangoEntrada,al;Es Un Numero 
inc si;Aumentar Si
xor ah,ah;
mov bl,10
mul bl;Multiplicar Decena
xor bx,bx
mov RangoEntrada,al;Actualizar A Decena


mov al,Teclado[si]
mov Registro,al
EsNumero Registro
jnz inicio;No Es Un Numero
mov al,Registro
mov ah,RangoEntrada
add ah,al
mov RangoEntrada,ah;Actualizar 

inc si
cmp si,3
jz fin;Ya Se Alcanzo el limite
cmp Simbolo,0
jz fin;Ya Se Alcanzo el limite
;===============Falta El Ultimo Digito============

xor ah,ah;
mov al,RangoEntrada
mov bl,10
mul bl;Multiplicar Decena
xor bx,bx
mov RangoEntrada,al;Actualizar 


mov al,Teclado[si]
mov Registro,al
EsNumero Registro
jnz inicio;No Es Un Numero
mov al,Registro
mov ah,RangoEntrada
add ah,al
mov RangoEntrada,ah;Actualizar 

jmp fin

fin:
NuevaLinea
ENDM




ImprimirFuncion macro
local salto,Signo,Zero,fin,Error

mov al,00h
cmp ExisteFuncion,al
jz Error


NuevaLinea
print TituloFuncion
xor ax,ax
xor si,si
mov si,0
mov cl,5
mov Registro,4
salto:

cmp Funcion[si],0
jz zero
Espacio
mov ch,43
cmp FuncionBandera[si],0;La Bandera Indica Cero
jz Signo
mov ch,45;Es Menos
jmp Signo
Signo:
PrintRegistro ch
Espacio
mov ch,Funcion[si]
PrintN ch;Imprimiendo Funcion

cmp Registro,00h
jz Zero
mov ch,42
PrintRegistro ch
mov ch,120
PrintRegistro ch


mov ch,Registro
PrintN ch;Imprimiendo Potenci

Zero:

dec Registro
inc si
dec cl
jnz salto
jz fin
Error:
Print TituloSinFuncion
fin:
NuevaLinea


endm




EsNumero macro Num; Bandera de Zero Encendida si es correcto el numero
local bueno,malo,fin


xor ax,ax

cmp Num,48
jz Bueno
cmp Num,49
jz Bueno
cmp Num,50
jz Bueno
cmp Num,51
jz Bueno
cmp Num,52
jz Bueno
cmp Num,53
jz Bueno
cmp Num,54
jz Bueno
cmp Num,55
jz Bueno
cmp Num,56
jz Bueno
cmp Num,57
jz Bueno
jmp Malo
Bueno:
mov al,0
sub Num,48
cmp al,0
jmp fin
Malo:
mov al,1
cmp al,0
jmp fin
fin:
endm


EsSimbolo macro Num; Bandera de Zero Encendida si es correcto el numero
local bueno,malo,fin


xor ax,ax

cmp Num,42
jz Bueno
cmp Num,43
jz Bueno
cmp Num,45
jz Bueno
cmp Num,47
jz Bueno


jmp Malo
Bueno:
mov al,0
sub Num,48
cmp al,0
jmp fin
Malo:
mov al,1
cmp al,0
jmp fin
fin:
endm