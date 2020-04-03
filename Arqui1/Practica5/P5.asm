include Macros.asm ; archivo con los macros
include Func.asm
include Eva.asm
include Archivo.asm
include Reporte.asm

.model small
.stack 500h
.data

   
;=============================================Mensajes==================================================
Titulo1  db "UNIVERSIDAD DE SAN CARLOS DE GUATEMALA",10,13,"$"   
Titulo2 db "ARQUITECTURA DE COMPUTADORES Y ENSAMBLADORES 1A",10,13,"NOMBRE: ANDHY LIZANDRO SOLIS OSORIO",10,13,"$"
Titulo3 db "CARNET: 201700886",10,13,"SECCION: A",10,13,"$" 

Rep1 db "Reporte Practica No.5",10,13,"$"
Rep2 db "Funcion Original",10,13,"$"
Rep3 db "Funcion Derivada",10,13,"$"
Rep4 db "Funcion Integral",10,13,"$"

Rep5 db "f(x)=","$"
Rep6 db "f'(x)=","$"
Rep7 db "F(X)=","$"

Novo db 10,13,"$"

Menu1 db "1) Ingresar Funcion f(x)","$"
Menu2 db "2) Funcion En Memoria","$"
Menu3 db "3) Derivada f'(x)","$"
Menu4 db "4) Integral F(x)","$"
Menu5 db "5) Graficar Funciones","$"
Menu6 db "6) Reporte","$"
Menu7 db "7) Modo Calculadora","$"
Menu8 db "8) Salir","$"

SubMenu1 db "1.Graficar Original f(x)","$"
SubMenu2 db "2.Graficar Derivada f'(x)","$"
SubMenu3 db "3.Graficar Integral F(x)","$"
SubMenu4 db "4.Graficar Regresar","$"

TituloMalo db "El Archivo Presenta Errores, No Se Puede Evaluar","$"
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
TituloAdios db "Saliendo Del Programa ","$"
TituloC db "Ingrese Constante C ","$"
TituloTiempo db "Fecha Y Hora",10,13,"$"
ReporteGenerado db "Se Genero El Reporte ","$"
RutaReporte db "Reporte.txt",0
;=============================================Almcenar Funciones==================================================
Funcion db 5 dup(0)
Lin db 0
Latch db 0
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

Sello db 100 dup(0)
Fecha db 30 dup(0)
Constante db 0
ConstanteBandera db 0
TipoGrafica db 0

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







main  proc
xor ax,ax
mov   ax, @data     ;hmm ¿seg?
mov   ds,ax          ;ds = ax = saludo


Print Titulo1
Print Titulo2
Print Titulo3 
;==================================================================================================
;=============================================Iniciar Variables==================================================
mov ExisteFuncion,0
;========================================INICIO PROGRAMA================================================
InicioPrograma:
NuevaLinea
Print Menu1
NuevaLinea
Print Menu2
NuevaLinea
Print Menu3
NuevaLinea
Print Menu4
NuevaLinea
Print Menu5
NuevaLinea
Print Menu6
NuevaLinea
Print Menu7
NuevaLinea
Print Menu8
NuevaLinea

mov Registro,1
Entrada Registro;Teclado
NuevaLinea

cmp Teclado[0],49
jz Opcion1
cmp Teclado[0],50
jz Opcion2
cmp Teclado[0],51
jz Opcion3
cmp Teclado[0],52
jz Opcion4
cmp Teclado[0],53
jz Opcion5
cmp Teclado[0],54
jz Opcion6
cmp Teclado[0],55
jz Opcion7
cmp Teclado[0],56
jz Opcion8

jmp InicioPrograma








;=============================================Menu==================================================
;=====================================Opcion1 ===============================================
Opcion1:
IngresarFuncion
DerivarFuncion
IntegrarFuncion
jmp InicioPrograma
;=====================================Opcion2 ===============================================
Opcion2:
ImprimirFuncion
jmp InicioPrograma
;=====================================Opcion3 ===============================================
Opcion3:
ImprimirDerivada
jmp InicioPrograma
;=====================================Opcion4 ===============================================
Opcion4:
ImprimirIntegral
;ImprimirIntegralR
jmp InicioPrograma
;=====================================Opcion5===============================================
Opcion5:
NuevaLinea
Print SubMenu1
NuevaLinea
Print SubMenu2
NuevaLinea
Print SubMenu3
NuevaLinea
Print SubMenu4
NuevaLinea

mov Registro,1
Entrada Registro;Teclado
NuevaLinea

mov Constante,0
mov TipoGrafica,0

cmp Teclado[0],49
jz SubOpcion1
cmp Teclado[0],50
jz SubOpcion2
cmp Teclado[0],51
jz SubOpcion3
cmp Teclado[0],52
jz SubOpcion4
jmp Opcion5;Regresar Al SubMenu

SubOpcion1:
mov Constante,0
mov TipoGrafica,1
jmp Grafiquita
SubOpcion2:
mov Constante,0
mov TipoGrafica,2
jmp Grafiquita
SubOpcion3:
Print TituloC
NuevaLinea
IngresarRango
xor ax,ax
mov al,RangoEntrada
mov ah,RangoEntradaBandera


mov Constante,al
mov ConstanteBandera,ah

mov TipoGrafica,3
jmp Grafiquita

SubOpcion4:
jmp InicioPrograma
;=====================================Opcion6===============================================
Opcion6:
Reporte
Print ReporteGenerado
mov Registro,1
Entrada Registro;Teclado

jmp InicioPrograma
;=====================================Opcion7===============================================
Opcion7:
CargarArchivo
jmp InicioPrograma
;=====================================Opcion8===============================================
Opcion8:
jmp Salitre

Grafiquita:
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

cmp TipoGrafica,1
jz FNormal
cmp TipoGrafica,2
jz FDerivada
jmp FIntegral
FNormal:
EvaluarFuncion
mov Constante,0
mov ConstanteBandera,0
jmp FinEval
FDerivada:
EvaluarDerivada
mov Constante,0
mov ConstanteBandera,0
jmp FinEval
FIntegral:
EvaluarIntegral
FinEval:

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


jmp Opcion5

Salitre:

PrintN TituloAdios


.exit
main  endp              ;Termina proceso
end main

