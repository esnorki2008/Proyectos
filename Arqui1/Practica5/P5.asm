include Macros.asm ; archivo con los macros
include Func.asm
.model small
.stack 500h
.data

   

Titulo1  db "UNIVERSIDAD DE SAN CARLOS DE GUATEMALA",10,13,"$"   
Titulo2 db "ARQUITECTURA DE COMPUTADORES Y ENSAMBLADORES 1",10,13,"NOMBRE: ANDHY LIZANDRO SOLIS OSORIO",10,13,"$"
Titulo3 db "CARNET: 201700886",10,13,"SECCION: B",10,13,"$" 

Cof db "Coeficiente de X","$"

X0 db 0
X1 db 0
X2 db 0
X3 db 0
X4 db 0
X5 db 0

Registro db 0
contador db 0

Teclado db 4 dup(0)
longitud db 0
.code


main  proc

IngresarFuncion










.exit
main  endp              ;Termina proceso
end main

