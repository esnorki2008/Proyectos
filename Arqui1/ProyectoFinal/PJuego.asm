
Juego macro
local bucle,aum,dis,sali,erro,finito,pausa
;====================================Limpiar Juego
LimpiarJuego
;====================================Actualizar Tiempo
MOV AH,2CH
INT 21H

mov Hora,CH
mov Minuto,CL
mov Segundo,DH

mov ActualBonus,1
mov ActualObstaculo,2
mov BanderaBonus,0
mov BanderaObstaculo,0

mov BanderaBonusSub,0
mov BanderaObstaculoSub,0


mov Punteo,3

mov PosX,34

;====================================Juego Actual
bucle:
mov ah, 11h
int 16h 

jz sali
;====================================Comparar Si Es De Las Flechas
cmp ah,77
jz aum
cmp ah,75
jz dis
cmp ah,01
jz pausa
;=============================Limpiar Atascado
mov ah,10h
int 16h 
jmp sali
aum:
mov ah,10h
int 16h 

cmp Posx,70
jz sali
inc PosX
jmp sali
dis:
mov ah, 10h
int 16h 
cmp Posx,4
jz sali
dec PosX
jmp sali


sali:

CargarPantallaJuego 


mov rr,3 
mov mm,2
D3:
dec rr
jz D1
D2:
dec mm
jnz D2 
jmp D3
D1:

cmp Punteo,0
jz finito

jmp bucle
pausa:
mov ah,6
mov al,0;Lineas 0 
mov bh,00001111b;Atributos
mov ch,0;Comienzo De Linea
mov cl,0;Comienzo COlumna
mov dh,24;Fin Del TExto
mov dl,79;Columna Fin
int 10h
EscribirPausa

mov ah,00
int 16h

mov ah,00
int 16h

cmp ah,01
jz bucle

cmp al,32
jz finito

jmp pausa

finito:
mov ah,6
mov al,0;Lineas 0 
mov bh,00001111b;Atributos
mov ch,0;Comienzo De Linea
mov cl,0;Comienzo COlumna
mov dh,24;Fin Del TExto
mov dl,79;Columna Fin
int 10h
mov ah,2h;Posicionar Cursor
mov bh,0
mov dh,0;Fila
mov dl,0;Columna
int 10h

endm

CargarPantallaJuego macro

mov ah,6
mov al,0;Lineas 0 
mov bh,00001011b;Atributos
mov ch,0;Comienzo De Linea
mov cl,0;Comienzo COlumna
mov dh,24;Fin Del TExto
mov dl,79;Columna Fin
int 10h

mov ah,6
mov al,21;Lineas 0 
mov bh,01111011b;Atributos
mov ch,1;Comienzo De Linea
mov cl,4;Comienzo COlumna
mov dh,22;Fin Del TExto
mov dl,75;Columna Fin
int 10h




EscribirEncabezado
DibujarCarro 
Generarobstaculo
Mostrarobstaculo
GenerarBonus
MostrarBonus
HitboxBonus
HitboxObstaculo
endm


HitboxBonus macro
local next,lup
xor si,si
mov al,20
lup:
cmp Bonus[si],0
jz next
cmp BonusY[si],18
jc next;Si Es Menor Que 18 Siguiente

mov bl,PosX
cmp BonusX[si],bl
jc next

add bl,6
cmp BonusX[si],bl
jnc next


inc Punteo
mov Bonus[si],0

next:
inc si
dec al
jnz lup
endm

HitboxObstaculo macro
local next,lup
xor si,si
mov al,20
lup:
cmp Obstaculo[si],0
jz next
cmp ObstaculoY[si],18
jc next;Si Es Menor Que 18 Siguiente

mov bl,PosX
cmp ObstaculoX[si],bl
jc next

add bl,6
cmp ObstaculoX[si],bl
jnc next


dec Punteo
mov Obstaculo[si],0

next:
inc si
dec al
jnz lup
endm

LimpiarJuego macro
local lup
xor si,si
mov al,20
mov ActualBonus,0
mov ActualObstaculo,0
mov ActualTiempo,0


lup:
mov Obstaculo[si],0
mov ObstaculoX[si],0
mov ObstaculoY[si],0

mov Bonus[si],0 
mov BonusY[si],0
mov BonusX[si],0
inc si
dec al
jnz lup
endm
