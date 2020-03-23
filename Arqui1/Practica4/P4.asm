include Macros.asm ; archivo con los macros

.model small
.stack 100h
.data
   ;A1 db 1,2,3,4,5,6,7,8
   

Titulo1  db "UNIVERSIDAD DE SAN CARLOS DE GUATEMALA",10,13,"FACULTAD DE INGENIERIA","CIENCIAS Y SISTEMAS",10,13,"$"    
Titulo2 db "ARQUITECTURA DE COMPUTADORES Y ENSAMBLADORES 1",10,13,"NOMBRE: ANDHY LIZANDRO SOLIS OSORIO",10,13,"$"
Tiutlo3 db "CARNET: 201602634",10,13,"SECCION: B",10,13,"$" 


Fila1 db 32,32,32,32,45,45,45,32,32,45,45,45,32,32,45,45,45,32,32,45,45,45,32,32,45,45,45,32,32,45,45,45,32,32,45,45,45,32,32,45,45,45,32,32,"$"
Fila0 db 32,32,179,32,32,32,32,179,32,32,32,32,179,32,32,32,32,179,32,32,32,32,179,32,32,32,32,179,32,32,32,32,179,32,32,32,32,179,32,32,32,32,32,179,"$"

Tabulador db 09,"$"
NuevaLinea db 10,13,"$"
Espacio db 32,"$"
J0 db 32,32,"$"
J1 db "FB","$"
J2 db "FN","$" 
DibX db "---","$"
DibY db "|","$"

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



mov ah,81;Iniciar Tablero
mov si,offset Tablero
Iniciar:
mov [si],1h
inc si
dec ah 
jnz Iniciar





mov PosY,9h
mov si,offset Tablero
inc si
mov bl,[si] 
PrintJ bl 
 
       
Print NuevaLinea
        
;Imprimir Tablero

   


;Imprimir NuevaFila
PrintN PosY
Print Espacio
Print J1
Print DibX
inc si  


Print J1
Print DibX
inc si

Print J1
Print DibX
inc si

Print J1
Print DibX
inc si

Print J1
Print DibX
inc si

Print J1
Print DibX
inc si

Print J1
Print DibX
inc si

Print J1
Print DibX
inc si

Print J1
inc si




dec PosY

TabY:
Print NuevaLinea 
Print Fila0           
Print NuevaLinea

;Imprimir NuevaFila
PrintN PosY
Print Espacio
Print J1
Print DibX
inc si  


Print J1
Print DibX
inc si

Print J1
Print DibX
inc si

Print J1
Print DibX
inc si

Print J1
Print DibX
inc si

Print J1
Print DibX
inc si

Print J1
Print DibX
inc si

Print J1
Print DibX
inc si

Print J1
inc si





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

