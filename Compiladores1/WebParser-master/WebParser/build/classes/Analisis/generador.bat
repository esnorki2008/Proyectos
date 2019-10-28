SET JFLEX_HOME= C:\libs\jflex-1.6.1

cd C:\Users\lizandro\Desktop\Tareas\Compiladores\Proyecto1Compiladores1\src\Analisis
java -jar C:\lib\lexico.jar scanner.l


pause  




SET CUP_HOME= C:\libs\java-cup-11b

cd C:\Users\lizandro\Desktop\Tareas\Compiladores\Proyecto1Compiladores1\src\Analisis
java -jar C:\lib\Cup.jar -parser parser parser.cup

pause
pause