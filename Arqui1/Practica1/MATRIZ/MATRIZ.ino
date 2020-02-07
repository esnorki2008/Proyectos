#include "FrequencyTimer2.h"
#include "Simbolos.cpp"
byte col = 0;
byte leds[8][8];
byte Arriba[8][8];
byte Abajo[8][8]; 
int pins[17]= {-1, 2, 3, 4, 5, 6, 7, 8, 9, 22, 24, 26,28,30, 32, 34,36};

int rows[8] = {pins[1], pins[2], pins[5], pins[4], pins[3], pins[6], pins[7], pins[8]};

int cols[8] = {pins[16], pins[15], pins[14], pins[13], pins[12], pins[11], pins[10], pins[9]}; 
 
const int numPatterns =6;

/*
byte patterns[numPatterns][8][8] = {
  A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,X,Y,Z,CORAZON,CORAZON,CORAZON
};
*/
byte patterns[numPatterns][8][8] = {
 SPACE,A,B,C,D,E
};

/*byte patterns[numPatterns][8][8] = {
 C,I,E,N,C,I,A,SPACE,Y,SPACE,T,E,C,N,O,L,O,G,I,A,SPACE,DOS,O,UNO,CINCO,SPACE
};*/
int pattern = 0;
 
void setup() {
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
 
void slidePattern(int pattern, int del) {
  for (int l = 0; l < 8; l++) {
    for (int j = 0; j < 7; j++) {
      for (int i = 0; i < 8; i++) {
        leds[j][i] = leds[j+1][i];
      }
    }
    for (int j = 0; j < 8; j++) {
      leds[7][j] = patterns[pattern][j][0 + l];
    }
    delay(del);
  }
}
void MoverArriba(int pattern, int del) {
  for (int l = 0; l < 8; l++) {
    for (int i = 0; i < 7; i++) {
      for (int j = 0; j < 8; j++) {
        leds[i][j] = leds[i+1][j];
      }
    }
    for (int j = 0; j < 8; j++) {
      leds[7][j] = patterns[pattern][j][0 + l];
    }
    delay(del);
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
