include Macros.asm ; archivo con los macros
include Func.asm
include Eva.asm
include Archivo.asm
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
TituloIngresoIzquierdo db "Ingrese El Rango Izquierdo: ","$"
TituloIngresoDerecho db "Ingrese El Rango Derecho: ","$"
TituloAdvertencia db "Advertencia El Rango Izquierdo Es Mayor Que El Derecho ","$"
TItuloErrorArchivo db "El Archivo Que Se Solicito No Fue Encontrado","$"
TituloIngreseRuta db "Ingrese Una Ruta Para Cargar","$"
TituloArroba db "Error Con La Colocacion De: #  " ,"$"
TituloExtension db "No Se Detecto La Extension .ARQ  " ,"$"
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

RangoMenor db 0
RangoMenorBandera db 0
RangoMayor db 0
RangoMayorBandera db 0
;
RangoEntrada db 0
RangoEntradaBandera db 0
Simbolo db 0
;=============================================Variables==================================================
Max dw 0
Ancho dw 0
Veces dw 0


Ciclo db 0
Registro db 0
Contador db 0



almacenar dw 0

Teclado db 4 dup(0)
longitud db 0
cambio db 0
limite db 0
pushi dw 0
;===============================================Variables Graficas======================================
PantallaInicio dw 0
PantallaPixeles dw 0
;=============================================Para Archivos
handle dw ?
Buffy db 120 dup(0)
tamanio db 119
inputi db 50 dup(0)
random db 0
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




main  proc
xor ax,ax
mov   ax, @data     ;hmm ¿seg?
mov   ds,ax          ;ds = ax = saludo



CargarArchivo

jmp Salitre



;==================================================================================================

;========================================INICIO PROGRAMA================================================
;=============================================Iniciar Variables==================================================
mov ExisteFuncion,0

;=============================================Menu==================================================
IngresarFuncion
;ImprimirFuncion
DerivarFuncion
;ImprimirDerivada
IntegrarFuncion
;ImprimirIntegral
;ImprimirIntegralR

;=========================================Graficar Funcion==================================
mov Max,0000h;Para Saber El Valor Max
mov Ancho,50;Ancho Maximo De La Funcion
mov RangoMayor,100;Evaluacion Del Ciclo



;=======================================Ingresar Rangos De Evaluacion;=============================
Rang:
Print TituloIngresoIzquierdo
NuevaLinea
IngresarRango
xor ax,ax
mov al,RangoEntrada
mov ah,RangoEntradaBandera
mov RangoMenor,al
mov RangoMenorBandera,ah

Print TituloIngresoDerecho
NuevaLinea
IngresarRango
xor ax,ax
mov al,RangoEntrada
mov ah,RangoEntradaBandera
mov RangoMayor,al
mov RangoMayorBandera,ah
;=======Comparaciones============
xor ax,ax


mov al,RangoMayorBandera
cmp al,0;Es Positivo
jz D1
jnz D2

D1:;Mayor Positivo
mov al,RangoMenorBandera
cmp al,0
jz T1
jnz T2
D2:;Mayor Negativo
mov al,RangoMenorBandera
cmp al,0
jz T3
jnz T4



T1:;Mayor Positivo Menor Positivo
mov al,RangoMayor
cmp al,RangoMenor
jnc E1
jc E2 
T2:;Mayor Positivo Menor Negativo
mov al,RangoMayor
cmp al,RangoMenor
jnc E3
jc E4
T3:;Mayor Negativo Menor Positivo
mov al,RangoMayor
cmp al,RangoMenor
jnc E5
jc E6
T4:;Mayor Negativo Menor Negativo
mov al,RangoMayor
cmp al,RangoMenor
jnc E7
jc E8
;Cambio Limite

E1:;MAYOR       Mayor Positivo Menor Positivo

mov al,RangoMayor
sub al,RangoMenor
xor ah,ah
mov pushi,ax;Veces A Ejecutar
xor ax,ax
mov ax,200;No Hay Cambios De Signo
mov cambio,al
mov al,RangoMayor
mov limite,al;Inicio De X
mov ValorXBandera,0;Signo De X


jmp saltito
E2:;MENOR       Mayor Positivo Menor Positivo
jmp adver
E3:;MAYOR       Mayor Positivo Menor Negativo

mov al,RangoMayor
add al,RangoMenor;Expande El Rango
xor ah,ah
mov pushi,ax;Veces A Ejecutar
inc pushi
xor ax,ax
mov al,RangoMayor;No Hay Cambios De Signo
mov cambio,al
mov al,RangoMayor
mov limite,al;Inicio De X
mov ValorXBandera,0;Bandera Inicial


jmp saltito
E4:;MENOR       Mayor Positivo Menor Negativo

mov al,RangoMayor
add al,RangoMenor;Expande El Rango
xor ah,ah
mov pushi,ax;Veces A Ejecutar
inc pushi
xor ax,ax
mov al,RangoMayor;No Hay Cambios De Signo
mov cambio,al
mov al,RangoMayor
mov limite,al;Inicio De X
mov ValorXBandera,0;Bandera Inicial

jmp saltito


E5:;MAYOR       Mayor Negativo Menor Positivo
jmp adver
E6:;MENOR       Mayor Negativo Menor Positivo
jmp adver
E7:;MAYOR       Mayor Negativo Menor Negativo
jmp adver
E8:;MENOR       Mayor Negativo Menor Negativo

mov al,RangoMenor
sub al,RangoMayor
xor ah,ah
mov pushi,ax;Veces A Ejecutar
xor ax,ax
mov ax,200;No Hay Cambios De Signo
mov cambio,al
mov al,RangoMenor
mov limite,al;Inicio De X
mov ValorXBandera,1;Signo De X

jmp saltito





jmp saltito
adver:
print TituloAdvertencia
NuevaLinea
jmp Rang
saltito:

;===========================Funcion De Verdad=================
;Almacenar Valores
mov cx,400
mov Max,cx



mov cx,pushi;l;Iteraciones
Enciclo:
mov jump,cx
xor ax,ax
mov al,limite
mov ValorX,ax;Valor De X
;mov Almacenar,ax
;print16 Almacenar
;mov ValorXBandera,0;Signo De X

xor cx,cx
mov cx,ValorX


;EvaluarFuncion
;EvaluarIntegral
;EvaluarDerivada

xor ax,ax
mov ax,Evaluar
xor cx,cx
mov cl,limite
coordenada
push ax;Guardar Valor

;=====Cambio Signo===========
;dec cambio;SI Es Cero Cambiar Signo
;jnz NoCmb
;cmp ValorXBandera,0
;jz kambio
;mov ValorXBandera,0
;jnz NoCmb
;kambio:
;mov ValorXBandera,1
NoCmb:
;========Disminucion========
dec limite;Incrementar

cmp limite,0
jnz kmb
mov cl,RangoMenor
mov limite,cl
mov ValorXBandera,1
kmb:






mov cx,jump
dec cx
jnz Enciclo




  ; esperar por tecla
mov ah,1h
int 21h

;=============================================Inicio Modo Grafico=========================================
xor bx,bx
mov bx,Pushi;l;Iteraciones

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



















  ; esperar por tecla
mov ah,1h
int 21h
  ; regresar a modo texto
mov ax,0003h
int 10h
; finalizar el programa
mov ax,4c00h
int 21h


Salitre:




.exit
main  endp              ;Termina proceso
end main

