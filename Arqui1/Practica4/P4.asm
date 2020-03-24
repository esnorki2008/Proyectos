include Macros.asm ; archivo con los macros

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
handler dw ?
buffer db 10 dup(' ')
Tablero db 64 dup(5)   
PosX db 0
PosY db 0
Modo db 0;Modo 0 Menu Principal 
Registro db 0  
Estado db 0 
b0 db 20 dup("$")
Aux db 0
Teclado db 20 dup("$")
Longitud db 0

Array1 db 5 dup("$")
Array2 db 5 dup("$")

b1 db 20 dup("$")
;Palabras Reservadas

Jugador db 0
Pass db 6 dup(0h)


Tiro db 100 dup(0h)
.code



main  proc
;jmp InicioTablero

;Iniciar Palabras Reservadas

;pass
mov Pass[0h],112
mov Pass[1h],97
mov Pass[2h],115
mov Pass[3h],115
mov Pass[4h],0
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

;NuevaLinea
;PrintArr Teclado
;NuevaLinea
;PrintArr Uno
;NuevaLinea

 
cmp Teclado[0h],49
jz InicioTablero
cmp Teclado[0h],50
jz Cargar
cmp Teclado[0h],51
jz Fin 

jmp Menu:
    


;pop BX
;cmp Bl,64
;mov dl,Bl
;mov ah,2
;int 21h

;jmp Fin



InicioTablero:  ;INICIO DE DIBUJAR TABLERO
mov bh,64;Iniciar Tablero
mov si,00h
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




;Val0 Teclado[0]
Val1 Teclado[1]
mov Registro,dl
mov al,8h
mul Registro
mov Registro,al
;Val1 Teclado[1]
Val0 Teclado[0]
add dl,Registro
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
cmp Registro,0h
jnz NoColocar
Colocar: 
mov al,Jugador
mov Tablero[si],al
;COMER FICHAS



xor ax,ax
mov ax,si
mov ah,Tablero[si]

VerI al,ah

mov bl,dl
NuevaLinea
mov dl,bl
mov ah,2
int 21h
Entrada
























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

Cargar:
Fin:



.exit
main  endp              ;Termina proceso
end main

