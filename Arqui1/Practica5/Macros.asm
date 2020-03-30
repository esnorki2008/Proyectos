
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
Entrada macro tama
local Input,FININPUT,reinicio
mov si,00h
mov bl,tama
mov longitud,0
reinicio:
mov Teclado[si],0h
inc si
dec bl
jnz reinicio
mov Teclado[si],0h
mov bl,tama
mov si,00h
mov ah,1;Entrada en al
Input:
int 21h
cmp al,13
JZ FININPUT
mov Teclado[si],al
inc si
inc longitud
dec bl
jz FININPUT
Jnz Input
FININPUT:
mov ax,si
xor si,si
xor ax,ax
xor dx,dx
endm

PrintTeclado macro veces
    local salto
    xor cx,cx
    mov cl,veces
    mov si,0
    salto:
    xor ax,ax
    mov dl,Teclado[si] 
    mov ah,02h
    int 21h
    inc si
    loop salto
endm

PrintRegistro macro Num
    xor ax,ax
    mov dl,Num 
    mov ah,02h
    int 21h
endm

PrintN macro Num
    xor ax,ax
    mov dl,Num
    add dl,48 
    mov ah,02h
    int 21h
endm

Print macro Texto
   xor ax,ax
   mov   ax, @data     ;hmm ¿seg?
   mov   ds,ax          ;ds = ax = saludo
   mov   ah,09          ;Function(print string)
   mov   dx,Offset Texto         ;DX = String terminated by "$"
   int   21h               ;Interruptions DOS Functions
endm