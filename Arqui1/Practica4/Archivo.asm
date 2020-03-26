



ReporteIntermedio macro
local Report,B,N,V,S
CreateFile ReporteNormal,handle


OpenFile ReporteNormal,handle
;Inicio
WriteFile handle,HtmlIni,57
;Tabla
WriteFile handle,HtmlTabla,39
;Cuerpo



mov Si,00h
mov ReporteY,8


ReporteMaster:
WriteFile handle,HtmlTr,4
mov ReporteX,8
Report:

cmp Tablero[si],00h
jz V
cmp Tablero[si],01h
jz B
cmp Tablero[si],02h
jz N
B:
WriteFile handle,HtmlBlanca,32
jmp S
N:
WriteFile handle,HtmlNegra,31
jmp S
V:
WriteFile handle,HtmlVacio,30
jmp S

S:
inc si
dec ReporteX
jnz Report

WriteFile handle,HtmlFTr,5
dec ReporteY
jnz ReporteMaster

;Fin Cuerpo
WriteFile handle,HtmlFinTabla,8
;Cerrar
WriteFile handle,HtmlFin,15


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


WriteFile handle,HtmlH1,4

WriteFile handle,Fecha,20

WriteFile handle,HtmlFH1,5

CloseFile handle

endm

ConvertirDia macro 


mov ah,0
mov dl,10
div dl
or ax,3030h



endm

OpenFile macro buffer,handler
mov AX,@data
mov DS,AX
mov ah,3dh
mov al,02h
lea dx,buffer
int 21h
;jc Error1 ; db con mensaje que debe existir en doc maestro
mov handler,ax
endm
;============== MACRO CERRAR ARCHIVO==============
CloseFile macro handler
;mov checkopenfile,1
mov AX,@data
mov DS,AX
mov ah,3eh
mov bx,handler
int 21h
;jc Error2 ; db con mensaje que debe existir en doc maestro
endm

;=========== MACRO LEER ARCHIVO===========
ReadFile macro handler,buffer,numbytes
mov AX,@data
mov DS,AX
mov ah,3fh
mov bx,handler
mov cx,numbytes ; numero maximo de bytes a leer(para proyectos hacerlo gigante) 
lea dx,buffer
int 21h
;jc Error4 ; db con mensaje que debe existir en doc maestro
endm

; pendiente el de crear escribir
;======================== MACRO CREAR ARCHIVO (any extensio) ===================
CreateFile macro buffer,handler
mov AX,@data
mov DS,AX
mov ah,3ch
mov cx,00h
lea dx,buffer
int 21h
;jc Error4
mov bx,ax
mov ah,3eh
int 21h
endm
; ========================= MACRO ESCRIBIR EN ARCHIVO YA CREADO =================

WriteFile macro handler,buffer,numbytes
mov AX,@data
mov DS,AX
mov ah,40h
mov bx,handler
mov cx,numbytes
lea dx, buffer
int 21h
endm