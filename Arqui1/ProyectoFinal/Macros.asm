Print16 macro Regis
local zero,noz

mov bx,4
xor ax,ax
mov ax,Regis
mov cx,10
zero:
xor dx,dx
div cx
push dx
dec bx
jnz zero


xor bx,4
noz:
pop dx
PrintN dl
dec bx
jnz noz

endm

Print8 macro Regis
local zero,noz

mov bx,2
xor ax,ax
mov al,Regis
mov cx,10
zero:
xor dx,dx
div cx
push dx
dec bx
jnz zero


xor bx,2
noz:
pop dx
PrintN dl
dec bx
jnz noz

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

PrintTecladoTemporal macro veces
    local salto
    xor cx,cx
    mov cl,veces
    mov si,0
    salto:
    xor ax,ax
    mov dl,TecladoTemporal[si] 
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
    mov dl,Num
    xor ax,ax    
    add dl,48 
    mov ah,02h
    int 21h
endm
PrintNZ macro Num
    local zero
    xor ax,ax
    mov al,Num
    cmp al,0
    jz zero
    xor ax,ax
    mov dl,Num
    add dl,48 
    mov ah,02h
    int 21h
    zero:
endm
Print macro Texto
   xor ax,ax
   mov   ax, @data     ;hmm ¿seg?
   mov   ds,ax          ;ds = ax = saludo
   mov   ah,09          ;Function(print string)
   mov   dx,Offset Texto         ;DX = String terminated by "$"
   int   21h               ;Interruptions DOS Functions
endm
EsNumero macro Registro
local malo,bueno,fin
cmp Registro,48
jz bueno
cmp Registro,49
jz bueno
cmp Registro,50
jz bueno
cmp Registro,51
jz bueno
cmp Registro,52
jz bueno
cmp Registro,53
jz bueno
cmp Registro,54
jz bueno
cmp Registro,55
jz bueno
cmp Registro,56
jz bueno
cmp Registro,57
jz bueno
jmp malo
malo:
mov bl,0
cmp bl,1
jmp fin
bueno:
mov bl,0
cmp bl,0
jmp fin
fin:
endm


;====================================Calcular Valor Maximo===========================
;mov cx,Veces;l;Iteraciones
;Maximus:
;mov jump,cx
;mov ValorX,cx;Valor De X
;mov ValorXBandera,0;Signo De X
;xor cx,cx
;mov cx,ValorX
;EvaluarFuncion;Evaluar Para Encontrar El Maximo
;xor ax,ax
;mov ax,Evaluar
;cmp Max,ax
;jnc PasarMax
;mov Max,ax;El Max Actual Es Menor
;PasarMax:
;mov cx,jump
;dec cx
;jnz Maximus