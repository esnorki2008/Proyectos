EvaluarFuncion macro
local lup,Resultado,Negativo,Fin,Elevar,Bucle,Esqui,NoNegarEntrada


mov al,ValorXBandera
cmp al,1
jnz NoNegarEntrada
mov ax,ValorX;Negar
neg ax
mov ValorX,ax
NoNegarEntrada:


xor ax,ax
xor bx,bx;Bx esta libre
xor cx,cx;Cx esta libre
xor dx,dx
xor si,si

mov ciclo,5
mov Registro,3;veces A Elevar

mov Evaluar,ax;Restaurar Evaluar
lup:;Ciclco


xor bx,bx
xor ax,ax



mov bx,ValorX;El Valor A Elevar



mov ax,bx

mov dl,Registro;Cuantas Veces Elevar
mov Contador,dl


cmp Contador,0;Verificar Que No Es Cero
jz Bucle

Elevar:
mul bx;Elevando
dec Contador
jnz Elevar


dec Registro;Disminuir Potencia
Bucle:;Salida De Elevar


xor bx,bx
mov bl,Funcion[si];Coeficiente
cmp FuncionBandera[si],0;Comparar Coeficiente
jz Resultado;No Es Negativo
neg bx;Pasar A Negativo
Resultado:



cmp ciclo,1;Si Se Eleva a 0
jnz Esqui
mov ax,0001;elevado a la 0
Esqui:
;ax Potencia
;bx coeficiente
mul bx;Resultado




mov bx,Evaluar
add bx,ax;Sumar Valores
mov Evaluar,bx;Guardar Para Siguiente Iteracion



inc si
dec ciclo
jnz lup
mov EvaluarBandera,0;Valor Defecto

mov ax,Evaluar
mov bx,8000h        
cmp bx,ax
jz Negativo
jc Negativo
jmp Fin;No Es Negativo
Negativo:
mov EvaluarBandera,1;Si es Negativo La Vuelve 1
Neg ax
jmp Fin

Fin:



mov Evaluar,ax
;Valor Numerico En Evaular
;Signo En Evaluar Bandera

endm


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