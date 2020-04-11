Reporte macro
CreateFile RutaReporte
OpenFile RutaReporte,handle


WriteFile handle,Titulo1,40
WriteFile handle,Titulo2,86
WriteFile handle,Titulo3,31



WriteFile handle,TituloTiempo,14

;=====================
;TIEMPO
MOV AH,2AH
INT 21H



xor ax,ax

;La Fecha
mov al,dl
mov dl,10
div dl

add al,48
add ah,48
mov Fecha[0],al
mov Fecha[1],ah
mov Fecha[2],47





xor ax,ax
mov al,dh
mov dl,10
div dl
add al,48
add ah,48

mov Fecha[3],al
mov Fecha[4],ah

mov Fecha[5],47

mov Fecha[6],50 
mov Fecha[7],48
mov Fecha[8],50
mov Fecha[9],48
;Hora
mov Fecha[10],45
MOV AH,2CH
INT 21H




mov Fecha[11],CH
mov Fecha[14],CL
mov Fecha[17],DH

xor ax,ax
mov al,Fecha[11]
mov dl,10
div dl
add ah,48
add al,48
mov Fecha[11],al
mov Fecha[12],ah
mov Fecha[13],58

xor ax,ax
mov al,Fecha[14]
mov dl,10
div dl
add ah,48
add al,48
mov Fecha[14],al
mov Fecha[15],ah
mov Fecha[16],58


xor ax,ax
mov al,Fecha[17]
mov dl,10
div dl
add ah,48
add al,48
mov Fecha[17],al
mov Fecha[18],ah
mov Fecha[19],32
;======================
WriteFile handle,Fecha,20
WriteFile handle,Novo,2

WriteFile handle,Rep1,23
;Funcion Normal
WriteFile handle,Rep2,18
WriteFile handle,Rep5,5
EscribirFuncion
WriteFile handle,Sello,40
WriteFile handle,Novo,2
;Derivada
WriteFile handle,Rep3,18
WriteFile handle,Rep6,6
EscribirDerivada
WriteFile handle,Sello,40
WriteFile handle,Novo,2
;Integral
WriteFile handle,Rep4,18
WriteFile handle,Rep7,5
EscribirIntegral
WriteFile handle,Sello,50
WriteFile handle,Novo,2

CloseFile handle
endm





EscribirIntegral macro
local salto,Signo,Zero,fin,Error,zzz


mov al,80
xor si,si
zzz:

mov Sello[si],32

inc si
dec al
jnz zzz




mov al,00h
cmp ExisteFuncion,al
jz Error



xor ax,ax
xor si,si
mov si,0
mov cl,5
mov bx,0
mov Registro,5
salto:


inc bx
mov Sello[bx],32

cmp Funcion[si],0
jz zero
mov ch,43
cmp IntegralBandera[si],0;La Bandera Indica Cero
jz Signo
mov ch,45;Es Menos
jmp Signo
Signo:

inc bx
mov Sello[bx],ch

inc bx
mov Sello[bx],32

mov ch,Funcion[si]

inc bx
add ch,48
mov Sello[bx],ch


mov ch,47
inc bx
mov Sello[bx],ch


mov ch,Registro
inc bx
add ch,48
mov Sello[bx],ch



cmp Registro,00h
;jz Zero
mov ch,42
inc bx
mov Sello[bx],ch
mov ch,120
inc bx
mov Sello[bx],ch


mov ch,Registro

inc bx
add ch,48
mov Sello[bx],ch

Zero:

dec Registro
inc si
dec cl
jnz salto
jz fin
Error:
Print TituloSinFuncion
fin:

inc bx
mov Sello[bx],32

mov ch,43
inc bx
mov Sello[bx],ch
inc bx
mov Sello[bx],32
mov ch,99
inc bx
mov Sello[bx],ch

NuevaLinea

endm



EscribirDerivada macro
local salto,Signo,Zero,fin,Error,zzz


mov al,80
xor si,si
zzz:

mov Sello[si],32

inc si
dec al
jnz zzz



mov al,00h
cmp ExisteFuncion,al
jz Error


xor ax,ax
xor si,si
mov si,0
mov cl,4
mov bx,0

mov Registro,4
salto:


inc bx
mov Sello[bx],32

cmp Derivada[si],0
jz zero
mov ch,43
cmp DerivadaBandera[si],0;La Bandera Indica Cero
jz Signo
mov ch,45;Es Menos
jmp Signo
Signo:
inc bx
mov Sello[bx],ch
inc bx
mov Sello[bx],32
mov ch,Derivada[si]

xor ax,ax
mov al,ch
mov ch,10
div ch
mov Contador,al
mov ch,ah

inc bx
mov dl,Contador
add dl,48
mov Sello[bx],dl

inc bx
add ch,48
mov Sello[bx],ch



cmp Registro,01h
jz Zero
mov ch,42
inc bx
mov Sello[bx],ch
mov ch,Derivada[si]
mov ch,120
inc bx
mov Sello[bx],ch
mov ch,Derivada[si]


mov ch,Registro
dec ch
inc bx
add ch,48
mov Sello[bx],ch
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


EscribirFuncion macro
local salto,Signo,Zero,fin,Error,zzz
mov al,80
xor si,si
zzz:

mov Sello[si],32

inc si
dec al
jnz zzz



mov al,00h
cmp ExisteFuncion,al
jz Error



xor ax,ax
xor si,si
mov si,0
mov cl,5
mov Registro,4
mov bx,0
salto:

cmp Funcion[si],0
jz zero
inc bx
mov Sello[bx],32
mov ch,43
cmp FuncionBandera[si],0;La Bandera Indica Cero
jz Signo
mov ch,45;Es Menos
jmp Signo
Signo:
inc bx
mov Sello[bx],ch
Espacio
mov ch,Funcion[si]
inc bx
add ch,48
mov Sello[bx],ch

cmp Registro,00h
jz Zero
mov ch,42
inc bx
mov Sello[bx],ch
mov ch,120
inc bx
mov Sello[bx],ch


mov ch,Registro
add ch,48
inc bx
mov Sello[bx],ch

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