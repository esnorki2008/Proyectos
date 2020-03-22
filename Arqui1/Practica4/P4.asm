include Macros.asm ; archivo con los macros

.model small
.stack 100h
.data
   ;A1 db 1,2,3,4,5,6,7,8
   Tablero db 64 dup(?)

   Titulo   db "Practica 4", "$"
   IniMenu   db "Menu", "$"
   Lectura db ?
   file db 'c:\arc.txt','00h' ;ojo con el 00h es importante
   handler dw ?
   buffer db 10 dup(' ')

   
.code
 
Proxy proc
;Print IniMenu 
Proxy endp 

main  proc   
  
mov ah,8
mov si,offset Tablero
Iniciar:

mov [si],ah
inc si
dec ah 
jnz Iniciar


mov ah,8
mov si,offset Tablero
Iniciar2:

mov al, [si]
inc si
dec ah
jnz Iniciar2


   ;Print [si]
   ;inc si
   ;PrintN [si]
   ;inc si
   ;PrintN [si]
   
   
   
   
           ;Inicia proceso
   ;fopen file, handler
   ;fread 10,buffer,handler
   ;fclose handler ; siempre se debe cerrar un archivo
 
   ;ov   ax, @data     ;hmm ¿seg?
   ;mov   ds,ax          ;ds = ax = saludo

   






.exit
main  endp              ;Termina proceso
end main

