Quicksort macro

endm


Burbuja macro
local lup1,lup2,cambio,asar
xor ax,ax
xor bx,bx
xor si,si
xor dx,dx


lup1:

xor bx,bx
lup2:

mov al,ValoresBarras[bx]
cmp al,ValoresBarras[bx+1]
jnc pasar
jz pasar

cambio: 

mov ah,ValoresBarras[bx+1]
mov ValoresBarras[bx+1],al
mov ValoresBarras[bx],ah

pasar:

inc bx
cmp bx,19
jnz lup2

inc si
cmp si,20
jnz lup1

DibujarBarras
endm