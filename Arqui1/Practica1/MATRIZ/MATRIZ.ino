#include "FrequencyTimer2.h"
#include "Simbolos.cpp"

byte col = 0;
byte leds[8][8];
byte Abajo[8][8];
int Veces=0;

int pins[17]= {-1, 2, 3, 4, 5, 6, 7, 8, 9, 22, 24, 26,28,30, 32, 34,36};

int rows[8] = {pins[1], pins[2], pins[5], pins[4], pins[3], pins[6], pins[7], pins[8]};

int cols[8] = {pins[16], pins[15], pins[14], pins[13], pins[12], pins[11], pins[10], pins[9]}; 
 
const int numPatterns =28;
int PosX,PosY,Letra;


byte patterns[numPatterns][8][8] = {
 SPACE,G,UNO,TRES,SPACE,GUION,SPACE,S,E,C,C,I,O,N,SPACE,A,SPACE,GUION,SPACE,P,R,A,C,T,I,C,A,UNO
};
int pattern = 0;
void setup() {
 PosX=PosY=Letra=0;

  // Configuramos los pinso como salida
  for (int i = 1; i <= 16; i++) {
    pinMode(pins[i], OUTPUT);
  }
 
  // inicializamos filas y columnas
  for (int i = 1; i <= 8; i++) {
    digitalWrite(cols[i - 1], LOW);
  }
 
  for (int i = 1; i <= 8; i++) {
    digitalWrite(rows[i - 1], LOW);
  }
 
  clearLeds();
 
  // Apagamos la conmutación del pin 11
  FrequencyTimer2::disable();
  // ratio de refresco en microsegundos
  FrequencyTimer2::setPeriod(2000);
  // Función de desbordamiento, se llama cada ciclo.
  FrequencyTimer2::setOnOverflow(display);
 
  setPattern(pattern);
}
 
void loop() {
    pattern = ++pattern % numPatterns;
    slidePattern(pattern, 100);
}
 
void clearLeds() {
  // Limpia el array
  for (int i = 0; i < 8; i++) {
    for (int j = 0; j < 8; j++) {
      leds[i][j] = 0;
    }
  }
}
 
void setPattern(int pattern) {
  for (int i = 0; i < 8; i++) {
    for (int j = 0; j < 8; j++) {
      leds[i][j] = patterns[pattern][i][j];
    }
  }
}
 void MoverAbajo(int pattern, int del) {
  PosY++;
  if(PosY>223){
    PosY=0;  
  }
    for(int z=0;z<8;z++){
    for (int j = 0; j < 8; j++) {
      for (int i = 0; i < 8; i++) {
        leds[j][i] = patterns[NumLetra(PosY+j)][NumY(PosY+j)][0 + i];    
      }     
    }  
    }
    delay(del);
    //CopiarAbajo();
}

/*void CopiarAbajo(){

 for (int j = 0; j < 7; j++) {
      for (int i = 0; i < 8; i++) {
        Abajo[j][i] = Abajo[j+1][i];
      }
    }
  
  for(int i=0;i<8;i++){
    Abajo[7][i]=leds[0][i];
  } 
  if(Balance>8){
    Balance=0;
    LimpiarMatrizAbajo();  
  }
}*/

void LimpiarMatrizAbajo(){
 
    for(int j=0;j<8;j++){
      for(int i=0;i<8;i++){
        Abajo[j][i]=0; 
       }  
    }
  
}
void MoverArriba(int pattern, int del) {
  PosY--;
  if(PosY<0){
    PosY=223;  
  }
    for(int z=0;z<8;z++){
    for (int j = 0; j < 8; j++) {
      for (int i = 0; i < 8; i++) {
        leds[7-j][i] = patterns[NumLetra(PosY-j)][NumY(PosY-j)][0 + i];    
      }     
    }  
    }
    delay(del);
    //CopiarAbajo();
}
void slidePattern(int Patron, int Delay) {
  
  if(Veces<80){
  Veces++;
  MoverAbajo(Patron,Delay);
  }else if(Veces>81) {
  MoverArriba(Patron,Delay);
    
    Veces--;
  }else{
    MoverArriba(Patron,Delay);
      Veces=160;
      Veces--;
    }
}

// Interrupt routine
void display() {
  digitalWrite(cols[col], LOW);  // Apagamos la columna previa
  col++;
  if (col == 8) {
    col = 0;
  }
  for (int row = 0; row < 8; row++) {
    if (leds[col][7 - row] == 1) {
      digitalWrite(rows[row], LOW);  // Apagamos este led
    }
    else {
      digitalWrite(rows[row], HIGH); // Apagamos este led
    }
  }
  digitalWrite(cols[col], HIGH); // Encendemos toda la columna
}
int NumLetra(int Num){
  if(Num>223)
    return 0;
  else if(Num<0)
    return 27;  
  return Num/8;
}
int NumY(int Num){
  if(Num>223)
    return Num-223;
  else if(Num<=0)
    return Num*-1;
       
  return Num%8;  
}
