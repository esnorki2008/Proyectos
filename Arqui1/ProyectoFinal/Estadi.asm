NuevoPunteo macro
local evitar
xor ax,ax
mov al,InformacionPuntos[81]
cmp MaximoPunteo,ax
jz evitar
jc evitar
;========Nuevo Punteo Top
xor ax,ax
mov ax,MaximoPunteo
mov InformacionPuntos[81],al
mov al,MaximoNivelPunteo
mov InformacionPuntos[82],al
mov al,PantallaUsuarioActual[0]
mov InformacionPuntos[83],al
mov al,PantallaUsuarioActual[1]
mov InformacionPuntos[84],al
mov al,PantallaUsuarioActual[2]
mov InformacionPuntos[85],al
mov al,PantallaUsuarioActual[3]
mov InformacionPuntos[86],al
mov al,PantallaUsuarioActual[4]
mov InformacionPuntos[87],al
mov al,PantallaUsuarioActual[5]
mov InformacionPuntos[88],al
mov al,PantallaUsuarioActual[6]
mov InformacionPuntos[89],al

evitar:
endm

GuardarPunteo macro
local ArchivoAbierto,lup

OpenFileS ArchivoPunteo,handle
cmp ax,1
jnz ArchivoAbierto
CreateFile ArchivoPunteo
OpenFileS ArchivoPunteo,handle
ArchivoAbierto:
WriteFile handle,InformacionPuntos,90
CloseFile handle
endm

CargarPunteo macro
local ArchivoAbierto,lup,nada,lip

OpenFileS ArchivoPunteo,handle
cmp ax,1
jnz ArchivoAbierto
CreateFile ArchivoPunteo
OpenFileS ArchivoPunteo,handle
CloseFile handle
mov ax,90
xor si,si
lip:
mov InformacionPuntos[si],0
inc si
dec ax
jnz lip
jmp nada
ArchivoAbierto:
ReadFile handle,InformacionPuntos,90
CloseFile handle
nada:
endm




OrdenarPunteo macro
local lup1,lup2,sig,jalar,cambiar
xor ax,ax
mov ax,15




lup1:





xor bx,bx
lup2:
cmp InformacionPuntos[bx],0;EstaVacia
jz cambiar
mov cl,InformacionPuntos[bx+0+9]
cmp InformacionPuntos,cl
jc cambiar
jmp sig



cambiar:
mov cl,InformacionPuntos[bx+0+9]
mov ch,InformacionPuntos[bx+0]
mov InformacionPuntos[bx+0],cl
mov InformacionPuntos[bx+0+9],ch

mov cl,InformacionPuntos[bx+1+9]
mov ch,InformacionPuntos[bx+1]
mov InformacionPuntos[bx+1],cl
mov InformacionPuntos[bx+1+9],ch

mov cl,InformacionPuntos[bx+2+9]
mov ch,InformacionPuntos[bx+2]
mov InformacionPuntos[bx+2],cl
mov InformacionPuntos[bx+2+9],ch

mov cl,InformacionPuntos[bx+3+9]
mov ch,InformacionPuntos[bx+3]
mov InformacionPuntos[bx+3],cl
mov InformacionPuntos[bx+3+9],ch

mov cl,InformacionPuntos[bx+4+9]
mov ch,InformacionPuntos[bx+4]
mov InformacionPuntos[bx+4],cl
mov InformacionPuntos[bx+4+9],ch

mov cl,InformacionPuntos[bx+5+9]
mov ch,InformacionPuntos[bx+5]
mov InformacionPuntos[bx+5],cl
mov InformacionPuntos[bx+5+9],ch

mov cl,InformacionPuntos[bx+6+9]
mov ch,InformacionPuntos[bx+6]
mov InformacionPuntos[bx+6],cl
mov InformacionPuntos[bx+6+9],ch

mov cl,InformacionPuntos[bx+7+9]
mov ch,InformacionPuntos[bx+7]
mov InformacionPuntos[bx+7],cl
mov InformacionPuntos[bx+7+9],ch

mov cl,InformacionPuntos[bx+8+9]
mov ch,InformacionPuntos[bx+8]
mov InformacionPuntos[bx+8],cl
mov InformacionPuntos[bx+8+9],ch

jmp sig
sig:




add bx,9
cmp bx,81
jnz lup2




dec ax
jnz lup1
endm



NuevoTiempo macro
local evitar
xor ax,ax
mov al,InformacionTiempo[81]
cmp MaximoTiempo,ax
jz evitar
jc evitar
;========Nuevo Tiempo Top
xor ax,ax
mov ax,MaximoTiempo
mov InformacionTiempo[81],al
mov al,MaximoNivelTiempo
mov InformacionTiempo[82],al
mov al,PantallaUsuarioActual[0]
mov InformacionTiempo[83],al
mov al,PantallaUsuarioActual[1]
mov InformacionTiempo[84],al
mov al,PantallaUsuarioActual[2]
mov InformacionTiempo[85],al
mov al,PantallaUsuarioActual[3]
mov InformacionTiempo[86],al
mov al,PantallaUsuarioActual[4]
mov InformacionTiempo[87],al
mov al,PantallaUsuarioActual[5]
mov InformacionTiempo[88],al
mov al,PantallaUsuarioActual[6]
mov InformacionTiempo[89],al

evitar:
endm

GuardarTiempo macro
local ArchivoAbierto,lup

OpenFileS ArchivoTiempo,handle
cmp ax,1
jnz ArchivoAbierto
CreateFile ArchivoTiempo
OpenFileS ArchivoTiempo,handle
ArchivoAbierto:
WriteFile handle,InformacionTiempo,90
CloseFile handle
endm

CargarTiempo macro
local ArchivoAbierto,lup,nada,lip

OpenFileS ArchivoTiempo,handle
cmp ax,1
jnz ArchivoAbierto
CreateFile ArchivoTiempo
OpenFileS ArchivoTiempo,handle
CloseFile handle
mov ax,90
xor si,si
lip:
mov InformacionTiempo[si],0
inc si
dec ax
jnz lip
jmp nada
ArchivoAbierto:
ReadFile handle,InformacionTiempo,90
CloseFile handle
nada:
endm




OrdenarTiempo macro
local lup1,lup2,sig,jalar,cambiar
xor ax,ax
mov ax,15




lup1:





xor bx,bx
lup2:
cmp InformacionTiempo[bx],0;EstaVacia
jz cambiar
mov cl,InformacionTiempo[bx+0+9]
cmp InformacionTiempo,cl
jc cambiar
jmp sig



cambiar:
mov cl,InformacionTiempo[bx+0+9]
mov ch,InformacionTiempo[bx+0]
mov InformacionTiempo[bx+0],cl
mov InformacionTiempo[bx+0+9],ch

mov cl,InformacionTiempo[bx+1+9]
mov ch,InformacionTiempo[bx+1]
mov InformacionTiempo[bx+1],cl
mov InformacionTiempo[bx+1+9],ch

mov cl,InformacionTiempo[bx+2+9]
mov ch,InformacionTiempo[bx+2]
mov InformacionTiempo[bx+2],cl
mov InformacionTiempo[bx+2+9],ch

mov cl,InformacionTiempo[bx+3+9]
mov ch,InformacionTiempo[bx+3]
mov InformacionTiempo[bx+3],cl
mov InformacionTiempo[bx+3+9],ch

mov cl,InformacionTiempo[bx+4+9]
mov ch,InformacionTiempo[bx+4]
mov InformacionTiempo[bx+4],cl
mov InformacionTiempo[bx+4+9],ch

mov cl,InformacionTiempo[bx+5+9]
mov ch,InformacionTiempo[bx+5]
mov InformacionTiempo[bx+5],cl
mov InformacionTiempo[bx+5+9],ch

mov cl,InformacionTiempo[bx+6+9]
mov ch,InformacionTiempo[bx+6]
mov InformacionTiempo[bx+6],cl
mov InformacionTiempo[bx+6+9],ch

mov cl,InformacionTiempo[bx+7+9]
mov ch,InformacionTiempo[bx+7]
mov InformacionTiempo[bx+7],cl
mov InformacionTiempo[bx+7+9],ch

mov cl,InformacionTiempo[bx+8+9]
mov ch,InformacionTiempo[bx+8]
mov InformacionTiempo[bx+8],cl
mov InformacionTiempo[bx+8+9],ch

jmp sig
sig:




add bx,9
cmp bx,81
jnz lup2




dec ax
jnz lup1
endm