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