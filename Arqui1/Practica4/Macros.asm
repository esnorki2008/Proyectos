Print macro Texto
   mov   ax, @data     ;hmm ¿seg?
   mov   ds,ax          ;ds = ax = saludo
   mov   ah,09          ;Function(print string)
   lea   dx,Offset Texto         ;DX = String terminated by "$"
   int   21h               ;Interruptions DOS Functions
endm
PrintN macro Num
    mov dl,Num ; print 'A'
    mov ah,06h
    add dl,30h
    int 21h
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