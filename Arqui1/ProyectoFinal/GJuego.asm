
GenerarBonus macro
local lup,next,delete,salir,equilibrio
xor si,si
mov rex,20
lup:
cmp Bonus[si],1
jz next
;=========No Hay Nada

mov ax,ActualTiempo
cmp ax,BanderaBonus
jz salir

xor bx,bx
xor dx,dx
mov bl,ActualBonus
div bx

cmp dx,0
jnz salir;Hay Residuo

mov Bonus[si],1;Crear Nuevo B
MOV AH,2CH
INT 21H
xor ax,ax
mov bx,dx
xor dx,dx
mov ax,bx
div ActualTiempo
mov ax,dx
xor dx,dx
mov bx,9
div bx;dx tiene lo random
xor ax,ax
mov al,dl
mov dl,8
mul dl
mov dl,al

add dl,5

equilibrio:
mov BonusX[si],dl
;5,74
nop
mov BonusY[si],2

jmp salir
next:
inc si
dec rex
jnz lup
salir:

endm

MostrarBonus macro
local lup,next,delete,salir,evitar



xor si,si
mov rex,20
lup:
cmp Bonus[si],0
jz next
;========Hay Algo
cmp BonusY[si],22
jz delete


cmp BanderaBonusSub,4
jnz evitar
inc BonusY[si]
evitar:
DibujarBonus BonusX[si],BonusY[si]
jmp next
delete:
mov Bonus[si],0
jmp next
next:
inc si
dec rex
jnz lup
mov ax,ActualTiempo
mov BanderaBonus,ax

cmp BanderaBonusSub,4
jnz salir
mov BanderaBonusSub,0
salir:
inc BanderaBonusSub
endm


Generarobstaculo macro
local lup,next,delete,salir,equilibrio
xor si,si
mov rex,20
lup:
cmp obstaculo[si],1
jz next
;=========No Hay Nada

mov ax,ActualTiempo
cmp ax,Banderaobstaculo
jz salir

xor bx,bx
xor dx,dx
mov bl,Actualobstaculo
div bx

cmp dx,0
jnz salir;Hay Residuo

mov obstaculo[si],1;Crear Nuevo B
MOV AH,2CH
INT 21H
xor ax,ax
mov bx,dx
xor dx,dx
mov ax,bx
div ActualTiempo
mov ax,dx
xor dx,dx
mov bx,9
div bx;dx tiene lo random
xor ax,ax
mov al,dl
mov dl,8
mul dl
mov dl,al

add dl,5

mov dh,dl
sub dl,74
neg dl
equilibrio:
mov obstaculoX[si],dl
;5,74
nop
mov obstaculoY[si],2

jmp salir
next:
inc si
dec rex
jnz lup
salir:

endm

Mostrarobstaculo macro
local lup,next,delete,salir,evitar



xor si,si
mov rex,20
lup:
cmp obstaculo[si],0
jz next
;========Hay Algo
cmp obstaculoY[si],22
jz delete


cmp BanderaobstaculoSub,3
jnz evitar
inc obstaculoY[si]
evitar:
Dibujarobstaculo obstaculoX[si],obstaculoY[si]
jmp next
delete:
mov obstaculo[si],0
jmp next
next:
inc si
dec rex
jnz lup
mov ax,ActualTiempo
mov Banderaobstaculo,ax

cmp BanderaobstaculoSub,4
jnz salir
mov BanderaobstaculoSub,0
salir:
inc BanderaobstaculoSub
endm
