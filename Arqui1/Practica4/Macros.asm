Print macro Texto
   xor ax,ax
   mov   ax, data     ;hmm ¿seg?
   mov   ds,ax          ;ds = ax = saludo
   mov   ah,09          ;Function(print string)
   mov   dx,Offset Texto         ;DX = String terminated by "$"
   int   21h               ;Interruptions DOS Functions
endm
Val0 macro Char
;65  72
    local salir
    cmp Char,65    
    mov dl,00h
    jz salir
    cmp Char,66
    mov dl,01h
    jz salir
    cmp Char,67
    mov dl,02h
    jz salir
    cmp Char,68
    mov dl,03h
    jz salir
    cmp Char,69
    mov dl,04h
    jz salir
    cmp Char,70
    mov dl,05h
    jz salir
    cmp Char,71
    mov dl,06h
    jz salir
    ;Ultimo O No Reconocido
    mov dl,07h    
    salir:
endm
Val1 macro Char
;49  57
    local salir
    cmp Char,49    
    mov dl,07h
    jz salir
    cmp Char,50
    mov dl,06h
    jz salir
    cmp Char,51
    mov dl,05h
    jz salir
    cmp Char,52
    mov dl,04h
    jz salir
    cmp Char,53
    mov dl,03h
    jz salir
    cmp Char,54
    mov dl,02h
    jz salir
    cmp Char,55
    mov dl,01h
    jz salir
    ;Ultimo O No Reconocido
    mov dl,00h    
    salir:
endm
PrintN macro Num
    xor ax,ax
    mov dl,Num ; print 'A'
    mov ah,06h
    add dl,30h
    int 21h
endm
PrintArr macro Arreglo
    local lup
    mov si,00h
    mov bl,5h
    lup:
    mov dl,Arreglo[si]
    mov ah,2
    int 21h
    inc si
    dec bl
    jnz lup
endm

Comparar macro Texto1,Texto2
local iniciocompa,correcto,incorrecto,fincompa
mov si,0h
mov al,5h
iniciocompa:


mov BL,Texto1[si]
mov BH,Texto2[si]


cmp BL,BH
jnz incorrecto;No Es Igual

inc si
dec al
jnz iniciocompa
jmp correcto;Todos Son Iguales


incorrecto:
xor bx,bx
mov bl,35
jmp fincompa
correcto:
xor bx,bx
mov bl,64
jmp fincompa
fincompa:
push bx
endm

Entrada macro
local Input,FININPUT,reinicio
mov si,00h
mov bl,5h
reinicio:
mov Teclado[si],0h
inc si
dec bl
jnz reinicio
mov Teclado[si],0h

mov bl,5h
mov si,00h
mov ah,1;Entrada en al
Input:
int 21h

cmp al,13
JZ FININPUT
mov Teclado[si],al

dec bl
jz FININPUT

inc si
Jnz Input
FININPUT:


mov ax,si
mov Longitud,al
xor si,si
xor ax,ax
xor dx,dx
endm

NuevaLinea macro
MOV dl, 10
MOV ah, 02h
INT 21h
MOV dl, 13
MOV ah, 02h
INT 21h
endm
Espacio macro
MOV dl, 32
MOV ah, 02h
INT 21h
endm

DivX macro
MOV dl, 45
MOV ah, 02h
INT 21h
MOV dl, 45
MOV ah, 02h
INT 21h
MOV dl, 45
MOV ah, 02h
INT 21h
endm

F0 macro
MOV dl, 32
MOV ah, 02h
INT 21h
MOV dl, 32
MOV ah, 02h
INT 21h

endm


PrintJ macro Num
cmp Num,1h    
jz Jug1
jc SinJug
Jnc Jug2
SinJug:
Print J0
jmp SalSelec
Jug1:
Print J1
jmp SalSelec
Jug2:
Print J2
jmp SalSelec
SalSelec:
endm

Exit macro
    mov   ax,4c00h       ;Function (Quit with exit code (EXIT))
int   21h            ;Interruption DOS Functions
endm

fopen macro filename,handle
lea dx,filename
mov ah,3dh
mov al, 00h
int 21h
mov handle,ax
.if carry?
mov ax,-1
.endif
endm

fwrite macro numbytes,databuffer,handle

mov ah,40h
mov bx,handle
mov cx,numbytes
lea dx,databuffer
int 21h
endm

fread macro numbytes,databuffer,handle

mov ah,3fh
mov bx,handle
mov cx,numbytes
lea dx,databuffer
int 21h
endm

fclose macro handle

mov ah,3eh
mov bx,handle
int 21h
endm