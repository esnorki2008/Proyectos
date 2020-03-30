IngresarFuncion  MACRO 
local subrutina

;PrintTeclado longitud
;PrintN longitud
xor cx,cx
mov cl,5
NuevaLinea
subrutina:
Print cof
mov Registro,cl
dec Registro
PrintN Registro


mov Registro,2
Entrada Registro;Devuelve La Longitud De La Cadena

NuevaLinea

loop subrutina
ENDM