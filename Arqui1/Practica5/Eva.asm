EvaluarIntegral macro
local lup,Resultado,Negativo,Fin,Elevar,Bucle,Esqui,NoNegarEntrada,finaliz


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

mov ciclo,6
mov Registro,4;veces A Elevar

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
mov bl,Integral[si];Coeficiente
cmp IntegralBandera[si],0;Comparar Coeficiente
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
finaliz:
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
;=================================================DERIVADA================================================
EvaluarDerivada macro
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

mov ciclo,4
mov Registro,2;veces A Elevar

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
mov bl,Derivada[si];Coeficiente
cmp DerivadaBandera[si],0;Comparar Coeficiente
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
;=================================================FUNCION=================================================
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


Pintar macro 
local ciclo_1






;xor di,di;Posicion De Inicio
;mov di,PantallaInicio
ciclo_1:
mov [di], dx ; poner color en A000:DI
inc di
inc bx

loop ciclo_1


;xor ax,ax
;mov   ax, @data     ;hmm ¿seg?
;mov   ds,ax          ;ds = ax = saludo
endm



PintarPlano macro
local lup
mov ax,200
mov bx,160
;=============Para Pintar=================
;Pintar Y
lup:

push ax
push bx

mov di,bx
mov cx,1;320 en X        200 en Y
Pintar 

pop bx
pop ax

add bx,320

dec ax
jnz lup;Ciclo


mov di,7D00h
mov cx,320;320 en X        200 en Y
Pintar 


endm

coordenada macro 
local saltare,fin,NegaY,SalNe,PasarX,NegaX,Arr,Birr,Negativo,Esq

cmp ConstanteBandera,0
jz Arr
neg Constante;La Constante Es Negativa
Arr:


cmp EvaluarBandera,0
jz Birr
neg ax
Birr:;AX y Constante Estan Con Sus Respectivos Signos
xor bx,bx
mov bl,Constante
add ax,bx;OPeracion


mov bx,8000h        
cmp bx,ax
jz Negativo
jc Negativo
jmp Esq;No Es Negativo
Negativo:
mov EvaluarBandera,1;Si es Negativo La Vuelve 1
Neg ax
jmp Esq

Esq:


cmp Max,ax;
jc saltare

push cx
xor cx,cx
xor dx,dx
mov cx,ax;Guardar
;100*ValorY/MaxValor
mov dx,90
mul dx;Multiplicar por 100

xor dx,dx
mov bx,Max
cmp Max,0
jz saltare
div bx;Dividir Valor Maximo AX resultado

inc ax

cmp EvaluarBandera,1
jz SalNe
;===================Y Es Positiva==========
mov dx,100; Los Pixeles Son Al Reves
sub dx,ax
mov ax,dx
jmp SalNe
;========================Y Ajustado================
SalNe:
mov dx,320
mul dx;Multiplicar Filas
;========================Y Es Negativa============
cmp EvaluarBandera,0
jz NegaY
add ax,7D00h;Se Suma La Mitad
NegaY:



;============================Desplazamiento En X===========================
add ax,159;Posicionar A La Mitad
pop cx
;mov bx,ax;Conservar Ax
;xor ax,ax;100*ValorY/MaxValor
;mov ax,cx;ValorY
;mov cx,100
;mul cx;100*ValorY
;mov cx,Ancho;MaxValor
;div cx;100*ValorY/MaxValor
;mov cx,ax
;mov ax,bx;Recuperar Valor

cmp ValorXBandera,1
jz NegaX;X Negativo
;============================X Positivo===========


add ax,cx;Sumar Desplazamiento
jmp PasarX
;============================X Negativo================
NegaX:
sub ax,cx
PasarX:
inc ax

jmp fin
saltare:
mov ax,0
fin:

endm