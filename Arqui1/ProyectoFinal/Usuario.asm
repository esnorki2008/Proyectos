LoginUsuario macro
local Pedir,Fin,UsuarioNoExiste,MalaContra,lup
pedir:
NuevaLinea
Print TituloLogin
NuevaLinea
Print User
NuevaLinea
mov Rex,7
Entrada Rex
NuevaLinea
ExisteUsuario Rex;0 No Existe
cmp Rex,0
jz UsuarioNoExiste
xor ax,ax
dec Rex
mov al,Rex
mov si,ax;Posicion Correcta
push si
ClonarUsuario
Print Contra
NuevaLinea
mov Rex,4
Entrada Rex
NuevaLinea
pop si
mov al,Teclado[0]
cmp al,InformacionUsuario[si+7]
jnz MalaContra
mov al,Teclado[1]
cmp al,InformacionUsuario[si+8]
jnz MalaContra
mov al,Teclado[2]
cmp al,InformacionUsuario[si+9]
jnz MalaContra
mov al,Teclado[3]
cmp al,InformacionUsuario[si+10]
jnz MalaContra
jmp Fin
MalaContra:
Print TituloLoginMalo
mov ah,1
int 21h
jmp Pedir
UsuarioNoExiste:
Print TituloLoginMalo
mov ah,1
int 21h
jmp Pedir
Fin:
Print TituloBienvenido
mov Rex,7
PrintTecladoTemporal Rex

mov al,TecladoTemporal[0] 
mov PantallaUsuarioActual[0],al

mov al,TecladoTemporal[1] 
mov PantallaUsuarioActual[1],al

mov al,TecladoTemporal[2] 
mov PantallaUsuarioActual[2],al

mov al,TecladoTemporal[3] 
mov PantallaUsuarioActual[3],al

mov al,TecladoTemporal[4] 
mov PantallaUsuarioActual[4],al

mov al,TecladoTemporal[5] 
mov PantallaUsuarioActual[5],al

mov al,TecladoTemporal[6] 
mov PantallaUsuarioActual[6],al

mov ah,1
int 21h
NuevaLinea
ArchivoGuardarUsuario
endm

CrearUsuario MACRO 
local Pedir,Fin,UsuarioExiste,MalaContra,lup,mali
pedir:
NuevaLinea
Print TituloUsuario
NuevaLinea

Print User
NuevaLinea
mov Rex,7
Entrada Rex
NuevaLinea

ExisteUsuario Rex;0 No Existe
cmp Rex,0
jnz UsuarioExiste

ClonarUsuario

mali:

Print Contra
NuevaLinea
mov Rex,4
Entrada Rex
NuevaLinea


xor si,si
xor ax,ax
mov al,4
lup:

EsNumero Teclado[si]
jnz MalaContra

inc si
dec al
jnz lup
jmp Fin

MalaContra:
Print ErrorContra
mov ah,1
int 21h
jmp mali
UsuarioExiste:
Print ErrorUsuario
mov ah,1
int 21h
jmp Pedir
Fin:

Print TituloCreado
ColocarUsuario
NuevaLinea
Print RellenoUsu
mov Rex,7
PrintTecladoTemporal Rex
NuevaLinea
mov Rex,4
Print RellenoContra
PrintTeclado Rex
mov ah,1
int 21h
ArchivoGuardarUsuario
NuevaLinea
ENDM

ColocarUsuario macro
local lup,Fin,Siguiente

xor ax,ax
xor si,si
xor bx,bx

mov al,99
lup:
mov bl,0
cmp InformacionUsuario[si],bl
jnz Siguiente;Esta Ocupado

mov bl,TecladoTemporal[0]
mov InformacionUsuario[si+0],bl
mov bl,TecladoTemporal[1]
mov InformacionUsuario[si+1],bl
mov bl,TecladoTemporal[2]
mov InformacionUsuario[si+2],bl
mov bl,TecladoTemporal[3]
mov InformacionUsuario[si+3],bl
mov bl,TecladoTemporal[4]
mov InformacionUsuario[si+4],bl
mov bl,TecladoTemporal[5]
mov InformacionUsuario[si+5],bl
mov bl,TecladoTemporal[6]
mov InformacionUsuario[si+6],bl



mov bl,Teclado[0]
mov InformacionUsuario[si+7],bl
mov bl,Teclado[1]
mov InformacionUsuario[si+8],bl
mov bl,Teclado[2]
mov InformacionUsuario[si+9],bl
mov bl,Teclado[3]
mov InformacionUsuario[si+10],bl

jmp Fin
Siguiente:
add si,11
dec al
jnz lup

NuevaLinea
Print AdvertenciaUsuario
NuevaLinea

Fin:
endm

ClonarUsuario macro
mov al,Teclado[0]
mov TecladoTemporal[0],al
mov al,Teclado[1]
mov TecladoTemporal[1],al
mov al,Teclado[2]
mov TecladoTemporal[2],al
mov al,Teclado[3]
mov TecladoTemporal[3],al
mov al,Teclado[4]
mov TecladoTemporal[4],al
mov al,Teclado[5]
mov TecladoTemporal[5],al
mov al,Teclado[6]
mov TecladoTemporal[6],al
endm

ExisteUsuario macro Regex
local lup,NoExiste,Existe,Fin,Siguiente
xor ax,ax
xor si,si
xor bx,bx
mov al,99
lup:
mov bl,Teclado[0]
cmp InformacionUsuario[si],bl
jnz Siguiente
mov bl,Teclado[1]
cmp InformacionUsuario[si+1],bl
jnz Siguiente
mov bl,Teclado[2]
cmp InformacionUsuario[si+2],bl
jnz Siguiente
mov bl,Teclado[3]
cmp InformacionUsuario[si+3],bl
jnz Siguiente
mov bl,Teclado[4]
cmp InformacionUsuario[si+4],bl
jnz Siguiente
mov bl,Teclado[5]
cmp InformacionUsuario[si+5],bl
jnz Siguiente
mov bl,Teclado[6]
cmp InformacionUsuario[si+6],bl
jnz Siguiente
jmp Existe
Siguiente:
add si,11
dec al
jnz lup
jmp NoExiste
NoExiste:
mov Regex,0
jmp Fin
Existe:
mov ax,si
mov Regex,al
inc Regex;Desfasar 1
jmp Fin
Fin:
endm