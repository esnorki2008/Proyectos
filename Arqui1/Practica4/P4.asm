include Macros.asm ; archivo con los macros

.model small
.stack 100h
.data
   ;A1 db 1,2,3,4,5,6,7,8
   

Titulo1  db "UNIVERSIDAD DE SAN CARLOS DE GUATEMALA",10,13,"FACULTAD DE INGENIERIA","CIENCIAS Y SISTEMAS",10,13,"$"
Titulo2 db "ARQUITECTURA DE COMPUTADORES Y ENSAMBLADORES 1",10,13,"NOMBRE: ANDHY LIZANDRO SOLIS OSORIO",10,13,"$"
Tiutlo3 db "CARNET: 201602634",10,13,"SECCION: B",10,13,"$" 


Fila db 32,9,196,196,196,9,196,196,196,9,196,196,196,9,196,196,196,9,196,196,196,9,196,196,196,9,196,196,196,9,196,196,196,9,"$"

Tabulador db 09,"$"
NuevaLinea db 10,13,"$"
J0 db 32,"$"
J1 db "FB","$"
J2 db "FN","$" 
DibX db "---","$"
Diby db "|","$"

IniMenu   db "Menu", "$"
    
    
   
    
   
   Lectura db ?
   file db 'c:\arc.txt','00h' ;ojo con el 00h es importante
   handler dw ?
   buffer db 10 dup(' ')
Tablero db 81 dup(0h)
   
   PosX db 0
   PosY db 0
   
   Registro db 0  
   
.code
 

main  proc   

;Cabecera De Juego  
;Print Titulo1
;Print Titulo2
;Print Titulo3

jmp Impre

mov ah,81;Iniciar Tablero
mov si,offset Tablero
Iniciar:
mov [si],0h
inc si
dec ah 
jnz Iniciar


Impre:
Print Fila        
        
;Imprimir Tablero


mov PosY,9h
mov si,offset Tablero
TabY:                     
mov PosX,9h
TabX:
;PrintN [si];Imprimir Numero Tablero
;Print Tabulador
inc si  

mov bl,PosX
dec PosX   
jnz TabX
dec PosY

     
jnz TabY

  
  

   
   
           ;Inicia proceso
;fopen file, handler
;fread 10,buffer,handler
;fclose handler ; siempre se debe cerrar un archivo
;ov   ax, @data     ;hmm ¿seg?
;mov   ds,ax          ;ds = ax = saludo

   






.exit
main  endp              ;Termina proceso
end main

