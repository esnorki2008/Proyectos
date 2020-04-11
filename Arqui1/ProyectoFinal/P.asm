include Macros.asm ; archivo con los macros


.model small
.stack 500h
.data

Titulo1  db "UNIVERSIDAD DE SAN CARLOS DE GUATEMALA",10,13,"$"   
Titulo2 db "ARQUITECTURA DE COMPUTADORES Y ENSAMBLADORES 1A",10,13,"NOMBRE: ANDHY LIZANDRO SOLIS OSORIO",10,13,"$"
Titulo3 db "CARNET: 201700886",10,13,"SECCION: A",10,13,"$" 

.code

main  proc
xor ax,ax
mov   ax, @data     ;hmm ¿seg?
mov   ds,ax          ;ds = ax = saludo



mov ah,6
mov al,0;Lineas 0 
mov bh,00001011b;Atributos
mov ch,0;Comienzo De Linea
mov cl,0;Comienzo COlumna
mov dh,24;Fin Del TExto
mov dl,79;Columna Fin
int 10h

mov ah,6
mov al,21;Lineas 0 
mov bh,01111011b;Atributos
mov ch,1;Comienzo De Linea
mov cl,4;Comienzo COlumna
mov dh,22;Fin Del TExto
mov dl,75;Columna Fin
int 10h


mov ah,01h
int 21h

.exit
main  endp              ;Termina proceso
end main

