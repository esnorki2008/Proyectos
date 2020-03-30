include Macros.asm ; archivo con los macros
include Func.asm
.model small
.stack 500h
.data

   
;=============================================Mensajes==================================================
Titulo1  db "UNIVERSIDAD DE SAN CARLOS DE GUATEMALA",10,13,"$"   
Titulo2 db "ARQUITECTURA DE COMPUTADORES Y ENSAMBLADORES 1",10,13,"NOMBRE: ANDHY LIZANDRO SOLIS OSORIO",10,13,"$"
Titulo3 db "CARNET: 201700886",10,13,"SECCION: B",10,13,"$" 
Cof db "Coeficiente de X","$"
TituloFuncion db "f(x) =","$"
TituloSinFuncion db "No Existe Funcion En El Sistema","$"
TituloDerivada db "f'(x) =","$"
TituloIntegral db "F(x) =","$"
;=============================================Almcenar Funciones==================================================
Funcion db 5 dup(0)
FuncionBandera db 5 dup(0)
ExisteFuncion db 0
Derivada db 4 dup(0)
DerivadaBandera db 4 dup(0)
Integral db 6 dup(0)
IntegralBandera db 6 dup(0)
Decimal1 db 6 dup(0)
Decimal2 db 6 dup(0)
;=============================================Variables==================================================

Ciclo db 0
Registro db 0
Contador db 0



almacenar dw 0

Teclado db 4 dup(0)
longitud db 0
.code


main  proc
;=============================================Iniciar Variables==================================================
mov ExisteFuncion,0

;=============================================Menu==================================================
IngresarFuncion
ImprimirFuncion
DerivarFuncion
ImprimirDerivada
IntegrarFuncion






.exit
main  endp              ;Termina proceso
end main

