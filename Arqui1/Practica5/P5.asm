include Macros.asm ; archivo con los macros
include Func.asm
.model small
.stack 500h
.data

   
;=============================================Mensajes==================================================
Titulo1  db "UNIVERSIDAD DE SAN CARLOS DE GUATEMALA",10,13,"$"   
Titulo2 db "ARQUITECTURA DE COMPUTADORES Y ENSAMBLADORES 1",10,13,"NOMBRE: ANDHY LIZANDRO SOLIS OSORIO",10,13,"$"
Titulo3 db "CARNET: 201700886",10,13,"SECCION: B",10,13,"$" 
TituloRango db "Ingrese Rango","$"
Cof db "Coeficiente de X","$"
TituloFuncion db "f(x) =","$"
TituloSinFuncion db "No Existe Funcion En El Sistema","$"
TituloDerivada db "f'(x) =","$"
TituloIntegral db "F(x) =","$"
;=============================================Almcenar Funciones==================================================
Funcion db 5 dup(0)
FuncionBandera db 5 dup(0)
ExisteFuncion db 0
Derivada db 4 dup(0)
DerivadaBandera db 4 dup(0)
Integral db 6 dup(0)
IntegralBandera db 6 dup(0)
Decimal1 db 6 dup(0)
Decimal2 db 6 dup(0)
Evaluar dw 0 
EvaluarBandera db 0
ValorX dw 0
ValorXBandera db 0
Jump dw 0

RangoMenor dw 0
RangoMenorBandera db 0
RangoMayor dw 0
RangoMayorBandera db 0
;=============================================Variables==================================================
Max dw 0
Ancho dw 0



Ciclo db 0
Registro db 0
Contador db 0



almacenar dw 0

Teclado db 4 dup(0)
longitud db 0
;===============================================Variables Graficas======================================
PantallaInicio dw 0
PantallaPixeles dw 0
.code


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
local saltare,fin,NegaY,SalNe,PasarX,NegaX

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
add ax,160;Posicionar A La Mitad
pop cx
mov bx,ax;Conservar Ax
xor ax,ax;100*ValorY/MaxValor
mov ax,cx;ValorY
mov cx,100
mul cx;100*ValorY
mov cx,Ancho;MaxValor
div cx;100*ValorY/MaxValor
mov cx,ax
mov ax,bx;Recuperar Valor

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




main  proc


xor ax,ax
mov   ax, @data     ;hmm ¿seg?
mov   ds,ax          ;ds = ax = saludo



;========================================INICIO PROGRAMA================================================
;=============================================Iniciar Variables==================================================
mov ExisteFuncion,0

;=============================================Menu==================================================
IngresarFuncion
;ImprimirFuncion
;DerivarFuncion
;ImprimirDerivada
;IntegrarFuncion
;ImprimirIntegral
;ImprimirIntegralR

;=========================================Graficar Funcion==================================
mov Max,0000h;Para Saber El Valor Max
mov Ancho,50;Ancho Maximo De La Funcion
mov RangoMayor,50
;====================================Calcular Valor Maximo===========================
mov cx,RangoMayor;l;Iteraciones
Maximus:
mov jump,cx
mov ValorX,cx;Valor De X
mov ValorXBandera,0;Signo De X
xor cx,cx
mov cx,ValorX
EvaluarFuncion;Evaluar Para Encontrar El Maximo
xor ax,ax
mov ax,Evaluar
cmp Max,ax
jnc PasarMax
mov Max,ax;El Max Actual Es Menor
PasarMax:
mov cx,jump
dec cx
jnz Maximus
;===========================Funcion De Verdad=================
;Almacenar Valores
mov cx,400
mov Max,cx
mov cx,RangoMayor;l;Iteraciones
Enciclo:
mov jump,cx
mov ValorX,cx;Valor De X
mov ValorXBandera,0;Signo De X
xor cx,cx
mov cx,ValorX
EvaluarFuncion
xor ax,ax
mov ax,Evaluar
mov cx,jump
coordenada
push ax;Guardar Valor

mov cx,jump
dec cx
jnz Enciclo




  ; esperar por tecla
mov ah,1h
int 21h

;=============================================Inicio Modo Grafico=========================================
xor bx,bx
mov bx,RangoMayor;l;Iteraciones

mov ax,0013h
int 10h
mov ax, 0A000h
mov ds, ax  ; DS = A000h (memoria de graficos).

mov dx,6;Color 
MiniSi:
mov Jump,bx
xor ax,ax
pop ax;Sacar El Valor Almacenado


mov di,ax
mov cx,1;320 en X        200 en Y
Pintar 


mov bx,Jump
dec bx
jnz MiniSi
mov dx,9;Color 
PintarPlano
;===============================================================================

















Salitre:

  ; esperar por tecla
mov ah,1h
int 21h
  ; regresar a modo texto
mov ax,0003h
int 10h
; finalizar el programa
mov ax,4c00h
int 21h







.exit
main  endp              ;Termina proceso
end main

