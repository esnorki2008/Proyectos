DibujarBonus macro Px,Py
mov dh,Py
mov dl,Px
mov bl,11101110b
Cuadro
mov dh,Py
mov dl,Px
dec dl
mov bl,11101110b
Cuadro
mov dh,Py
mov dl,Px
inc dl
mov bl,11101110b
Cuadro
endm 
DibujarObstaculo macro Px,Py
mov dh,Py
mov dl,Px
mov bl,10101010b
Cuadro
mov dh,Py
mov dl,Px
dec dl
mov bl,10101010b
Cuadro
mov dh,Py
mov dl,Px
inc dl
mov bl,10101010b
Cuadro
endm

DibujarCarro macro
local azul,rojo,verde,blanco,ter
;==============Dibujar Carro==========
mov ah,6
mov al,0;Lineas 0 
mov bh,00111011b;Atributos
mov ch,17;Comienzo De Linea
mov cl,4;Comienzo COlumna
mov dh,ch;Fin Del TExto
add dh,5
mov dl,cl;Columna Fin
add dl,71
int 10h

mov ah,6
mov al,0;Lineas 0 
mov bh,00001011b;Atributos
mov ch,19;Comienzo De Linea
mov cl,PosX;Comienzo COlumna
mov dh,ch;Fin Del TExto
add dh,0
mov dl,cl;Columna Fin
add dl,5
int 10h

mov ah,6
mov al,0;Lineas 0 
mov bh,00001011b;Atributos
mov ch,21;Comienzo De Linea
mov cl,PosX;Comienzo COlumna
mov dh,ch;Fin Del TExto
add dh,0
mov dl,cl;Columna Fin
add dl,5
int 10h

mov ah,6
mov al,0;Lineas 0 

cmp Colorcito,0
jz azul
cmp Colorcito,1
jz rojo
cmp Colorcito,2
jz verde
jmp blanco
azul:
mov bh,10011011b;Azul
jmp ter
verde:
mov bh,10101011b;Verde
jmp ter
blanco:
mov bh,11111011b;blanco
jmp ter
rojo:
mov bh,11001011b;rojo
jmp ter
ter:


mov ch,18;Comienzo De Linea
mov cl,PosX;Comienzo COlumna
inc cl
mov dh,ch;Fin Del TExto
add dh,3
mov dl,cl;Columna Fin
add dl,3
int 10h
endm

Cuadro macro
mov ah,2h;Posicionar Cursor
mov bh,0
;mov dh,10;Fila
;mov dl,PosX;Columna
int 10h

mov ah,9;Letras Especiales
mov al,'.';Caracter
;mov bl,10101010b;Atributos
mov bh,0;Numero Pagina
mov cx,1;Cantidad Veces
int 10h
endm