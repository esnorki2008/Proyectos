include Macros.asm ; archivo con los macros
include Archivo.asm
.model small
.stack 500h
.data
   ;A1 db 1,2,3,4,5,6,7,8
   

Titulo1  db "UNIVERSIDAD DE SAN CARLOS DE GUATEMALA",10,13,"$"
;Titulo1  db "UNIVERSIDAD DE SAN CARLOS DE GUATEMALA",10,13,"FACULTAD DE INGENIERIA","CIENCIAS Y SISTEMAS",10,13,"$"    
Titulo2 db "ARQUITECTURA DE COMPUTADORES Y ENSAMBLADORES 1",10,13,"NOMBRE: ANDHY LIZANDRO SOLIS OSORIO",10,13,"$"
Titulo3 db "CARNET: 201700886",10,13,"SECCION: B",10,13,"$" 
Ocupado db "La Casilla Esta Ocupada","$" 
Turno1 db "Turno BLANCAS","$"
Turno2 db "Turno NEGRAS","$"




TituCargar db "Opcion Cargar, Ingrese Ruta","$"
TituSalir db "Saliendo Del Programa","$"

TituSave db "Opcion Guardar, Ingrese Ruta","$"
TituPass db "Pasando Turno ","$"
TituExi db "Regresando Al Menu","$"
TituShow db "Reporte Generado","$"


TituloSuicidio db "No Se Puede Colocar,Seria Suicidio","$"




Fila1 db 32,32,32,32,45,45,45,32,32,45,45,45,32,32,45,45,45,32,32,45,45,45,32,32,45,45,45,32,32,45,45,45,32,32,45,45,45,32,32,45,45,45,32,32,"$"
Fila0 db 32,32,179,32,32,32,32,179,32,32,32,32,179,32,32,32,32,179,32,32,32,32,179,32,32,32,32,179,32,32,32,32,179,32,32,32,32,179,"$"
FilaF0 db 32,32,"A",32,32,32,32,"B",32,32,32,32,"C",32,32,32,32,"D",32,32,32,32,"E",32,32,32,32,"F",32,32,32,32,"G",32,32,32,32,"H","$"


J0 db 32,32,"$"
J1 db "FB","$"
J2 db "FN","$" 
DibX db "---","$"
DibY db "|","$"

Menu0   db "Menu Del Juego", "$"
Menu1 db "1) IniciarJuego", "$"
Menu2 db "2) CargarJuego", "$"
Menu3 db "3) Salir", "$" 
              
Lectura db ?
file db 'c:\arc.txt','00h' ;ojo con el 00h es importante


ReporteX db 0
ReporteY db 0
Conti db 0

PassBlanca db 0
PassNegra db 0
ContadorSupremo db 0

Terreno db 64 dup(0)

nl db 0
nh db 0

zl db 0
xl db 0
xh db 0

Fecha db 20 dup(0)
handle dw ?
Guardado db 'SAVE.ARQ',00h
ReporteNormal db 'Show.html',00h
ReporteFinal db 'Final.html',00h

TeBla db "Terreno Fichas Blancas: ","$"
TeNeg db "Terreno FIchas Negras: ","$"

MsgBla db "Gano Fichas Blancas","$"
MsgNeg db "Gano FIchas Negras","$"
MsgEmp db "Empate, Como Fichas Blancas Empiezan Ganan Las Negras","$"

HtmlFNeg db "<H1>Ganaron Las Fichas Negras</H1>"
HtmlFBla db "<H1>Ganaron Las Fichas Blancas</H1>"
HtmlFEmp db "<H1>Empate, Fichas Blancas Empiezan Ganan Las Negras</H1>"



HtmlIni db "<HTML><HEAD><title> Reporte Simple </title></HEAD> <BODY>","$"
HtmlTabla db "<table cellspacing='0' cellpadding='0'>","$"
HtmlFinTabla db "</table>","$"
HtmlFin db "</BODY> </HTML>","$"

HtmlTr db "<tr>","$" 
HtmlFTr db "</tr>","$" 


HtmlNegra db "<td><img src='Negras.png'></td>","$" 
HtmlBlanca db "<td><img src='Blancas.png'></td>","$"


TerrenoNegro db "<td><img src='NegrasT.png'></td>","$" 
TerrenoBlanco db "<td><img src='BlancasT.png'></td>","$" 
TerrenoVacio db "<td><img src='VacioT.png'></td>","$" 

HtmlVacio db "<td><img src='Vacio.png'></td>","$" 

HtmlH1 db "<h1>","$"
HtmlFH1 db "</h1>","$"

Salida db 65 dup(0)














buffer db 10 dup(' ')
Tablero db 64 dup(5)
Libertad db 64 dup(5)     
PosX db 0
PosY db 0
Modo db 0;Modo 0 Menu Principal 
Registro db 0  
Estado db 0 
b0 db 20 dup("$")
Aux db 0
Aux1 db 0
Aux2 db 0 
Aux3 db 0
Aux4 db 0
Aux5 db 0
Aux6 db 0
Teclado db 20 dup("$")
Longitud db 0

FBlanca db 0
FNegra db 0

Array1 db 20 dup("$")
Array2 db 20 dup("$")

b1 db 20 dup("$")
;Palabras Reservadas

Jugador db 0


Tiro db 100 dup(0h)
.code



main  proc
;jmp InicioTablero

;Iniciar Palabras Reservadas

;pass








Print Titulo1
Print Titulo2
Print Titulo3
NuevaLinea
Menu:
;Imprimir Menu
Print Menu0
NuevaLinea
Print Menu1
NuevaLinea
Print Menu2
NuevaLinea
Print Menu3
NuevaLinea

Entrada




NuevaLinea

;NuevaLinea
;PrintArr Uno
;NuevaLinea


 
cmp Teclado[0h],49
jz InicioTablero
cmp Teclado[0h],50
jz Cargar
cmp Teclado[0h],51
jz Fin 

jmp Menu
    


;pop BX
;cmp Bl,64
;mov dl,Bl
;mov ah,2
;int 21h

;jmp Fin



InicioTablero:  ;INICIO DE DIBUJAR TABLERO
mov bh,64;Iniciar Tablero
mov si,00h

mov PassNegra,0
mov PassBlanca,0

Iniciar:
mov al,0h
mov Tablero[si],al
inc si
dec bh
jnz Iniciar
mov Jugador,1h ;Empezando Con Jugador1 
JuegoTablero:



NuevaLinea
cmp Jugador,1h
jz TJ1
jnz TJ2
TJ1:
print Turno1
jmp FinTJ
TJ2:
print Turno2
FinTJ:
NuevaLinea

           


mov si,000h
mov PosY,8


TablaY:

mov PosX,8
PrintN PosY
Espacio



TablaX:
PrintJ Tablero[si]
inc si

dec PosX
jz FinTableroX
DivX


FinTableroX:
jnz TablaX


NuevaLinea

mov bl,PosY
dec bl
jz FinTableroY


Print Fila0
jmp FinalConte
FinTableroY:
NuevaLinea
Print FilaF0
NuevaLinea

FinalConte:
dec PosY
NuevaLinea
jnz TablaY 


Entrada


Pass
cmp bl,64
jz CPass


Save
cmp bl,64
jz CSave


Show
cmp bl,64
jz CShow


Exi
cmp bl,64
jz CExi

;Val0 Teclado[0]
Val1 Teclado[1]
mov Registro,dl
mov al,8h
mul Registro
mov Registro,al
;Val1 Teclado[1]
Val0 Teclado[0]
add dl,Registro

mov Aux6,dl

xor dh,dh 


;mov ah,2
;int 21h


;mov dl,Teclado[1]
;Val Teclado[1]
;mov ah,2
;int 21h

mov si,dx
mov al,Tablero[si]
mov Registro,al

cmp Registro,0h ;Ver Si ESta Libre
jnz NoColocar


push si

xor bx,bx 
mov bl,Aux6

mov bh,jugador
mov Tablero[si],bh
Suicidio Jugador;Respuesta En DL

pop si

cmp dl,1
jz Suic





Colocar: 
mov al,Jugador
xor bx,bx
mov bx,si
mov Aux5,bl;Almcaenando Si
mov Tablero[si],al
;COMER FICHAS




;Calcu
CalcLiber



mov Aux4,64
Desp:             
desplazarLiber;Mover Las Libertades Grupales
dec Aux4
jnz Desp





xor bx,bx  ;Hacer InMortal A La Nueva
mov bl,Aux5
mov Libertad[bx],4

mov Conti,0

change:           

RemoverAtrapadas           

;PrintLiber
NuevaLinea
;PrintTerreno   





;CAMBIAR DE JUGADOR


cmp Jugador,1h
jz CJ1
Jnz Cj2
CJ1:
mov Jugador,2h
jmp CJF
CJ2:
mov Jugador,1h
CJF:




jmp JuegoTablero

NoColocar:
Print Ocupado 
Entrada
jmp JuegoTablero

Suic:
mov Tablero[si],0
Print TituloSuicidio
Entrada
jmp JuegoTablero

CSave:
Print TituSave

mov si,00h
mov bl,64
CopyP:
mov bh,Tablero[si]
mov Salida[si],bh
inc si
dec bl
jnz CopyP
mov bh,Jugador
mov Salida[64],bh

CreateFile Guardado,handle


OpenFile Guardado,handle
WriteFile handle,Salida,65
CloseFile handle






NuevaLinea 
jmp JuegoTablero 










CExi:
print TituExi
NuevaLinea 
jmp Menu
CShow:
Print TituShow
NuevaLinea 

ReporteIntermedio

Entrada
jmp JuegoTablero


CPass:
cmp Conti,0
jnz cls ;Tenia Antes Un Jugador
;No Tenia PASS ANTERIOR

mov PassBlanca,0
mov PassBlanca,0

cls:


mov Conti,1

cmp Jugador,1
jz PB
jmp PN
PB:
mov PassBlanca,1
jmp FP
PN:
mov PassNegra,1
FP:


cmp PassBlanca,1
jz SAB
jmp SAPA

SAB:
cmp PassNegra,1
jz TERMIP


SAPA:
Print TituPass
NuevaLinea


jmp Change 




TERMIP:

RemoverMuerta  
CalcularTerreno
mov Aux4,64
DespTerreno:             
desplazarTerreno;Mover Las Libertades Grupales
dec Aux4
jnz DespTerreno
GenerarReporteFinal
jmp Menu





       
       
       
       
       
       
       
       
Cargar:
OpenFile Guardado,handle
ReadFile handle,Salida,65
CloseFile handle


mov si,00h
mov bl,64
CopyP1:
mov bh,Salida[si]
mov Tablero[si],bh
inc si
dec bl
jnz CopyP1
mov bh,Salida[64]
mov Jugador,bh


Print TituCargar
NuevaLinea  
jmp JuegoTablero


Fin:
Print TituSalir
NuevaLinea 

.exit
main  endp              ;Termina proceso
end main

