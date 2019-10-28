Friend Class Operaciones
    Inherits Datos
    Private X As Integer = 0
    Private Xi As Integer = 0
    Private Y As Integer = 0
    Private Yi As Integer = 0
    'Private XE As Integer = 0
    Private NumG As Integer = 0
    Private Corchetes As String = ""
    Private Antiguos1 As Integer = 0
    Private Antiguos2 As Integer = 0
    Private Abri As Boolean = False



    Public Sub New()
    End Sub

    Public Sub DatosLimpios(Info As String)
        Antiguos1 = 0
        Antiguos2 = 0
        Abri = False

        Cuarto = True
        Limpiar()


        Dim partido = Info.Split(Chr(13))
        Dim Retorno As String = ""
        Dim Compro = {1, 1, 1, 1, 0}


        For j = 0 To partido.Length - 1
            Y = j
            For i = 0 To partido(j).Length - 1
                X = i
                Dim Letras = partido(j).ToCharArray
                Dim Ltr = Letras(i).ToString
                Select Case Ltr
                    Case Chr(13).ToString
                    Case " "
                    Case Chr(12).ToString
                    Case Chr(10).ToString
                    Case Chr(9).ToString
                    Case "G"
                        If Compro(0) = 1 Then
                            Compro(0) = 0
                        Else
                            Retorno = Retorno + Ltr
                        End If
                    Case "="
                    Case "{"
                        If Compro(2) = 1 Then
                            Compro(2) = 0
                        Else
                            Corchetes = ""
                            Retorno = Retorno + Ltr
                        End If
                    Case "}"
                        If Compro(3) = 1 Then
                            Retorno = Retorno + Ltr

                            '                            
                            If Corchetes.Split(",").Length >= 2 Then
                                ' Console.WriteLine(Corchetes)
                                Produccion(Corchetes)
                            Else
                                '  Console.WriteLine("555")
                            End If

                            Corchetes = ""
                        Else
                            NumG = NumG + 1
                            Compro(0) = 1
                            Compro(3) = 1
                            Compro(2) = 1
                        End If

                    Case "["
                        Abri = True

                        If Compro(4) = 0 Then
                            Compro(4) = 1
                        End If


                        Xi = X
                        Yi = Y
                    Case "]"
                        Abri = False
                        '  Console.WriteLine(Retorno)
                        Compro(1) = 0
                        Dim Mtr = {Xi, Yi, X, Y, Retorno}

                        Select Case Compro(4)
                            Case 1
                                Compro(4) = 2
                                PonerVecto1(Mtr)
                            Case 2
                                Compro(4) = 3
                                PonerVecto2(Mtr)
                            Case 3
                                Compro(4) = 4
                                PonerVecto3(Mtr)
                            Case 4
                                If Inicial(Retorno) Then
                                    If Cuarto Then
                                        PonerVecto4(Mtr)
                                    Else
                                        Console.WriteLine("Ambiguedad en el estado inicial")
                                    End If

                                Else
                                    Mtr = {Xi, Yi, X, Y, Retorno}
                                    PonerVecto5(Mtr)
                                    Console.WriteLine("Error en 4")
                                End If
                                Compro(4) = 0

                        End Select



                        Retorno = ""
                    Case ","
                        If Compro(1) = 1 Then
                            Retorno = Retorno + Ltr
                            Corchetes = Corchetes + Ltr
                        Else
                            Compro(1) = 1
                        End If
                    Case Else
                        If Abri = True Then


                            Select Case Compro(4)
                                Case 1
                                    Retorno = Terminales(Retorno, Ltr)

                                Case 2
                                    Retorno = Retorno + Ltr

                                    If NoTerminales(Retorno) Then
                                    Else
                                        Dim Mel = Retorno.Split(",")
                                        Dim Si As Boolean = False





                                        If X <> Antiguos1 And Y <> Antiguos2 Then
                                            '  MsgBox(Antiguos1 & "    " & Antiguos2 & "   " & X & "   " & Y)

                                            Dim Mtr = {Xi, Yi, X, Y, Mel(0)}
                                            PonerVecto5(Mtr)
                                        End If

                                        Antiguos1 = X
                                        Antiguos2 = Y






                                    End If
                                Case 3
                                    Retorno = Retorno + Ltr
                                    Corchetes = Corchetes + Ltr
                                Case 4
                                    Retorno = Retorno + Ltr
                                Case 0
                                    Dim Mtr = {Xi, Yi, X, Y, Ltr}
                                    PonerVecto5(Mtr)
                            End Select
                        Else
                            Dim Mtr = {Xi, Yi, X, Y, Ltr}
                            PonerVecto5(Mtr)
                        End If




                End Select


            Next
            X = 0
        Next




    End Sub

    Private Function Terminales(R As String, T As String)
        Dim O = R + T
        Return O
    End Function
    Private Function NoTerminales(Lt As String)
        '  


        Dim Letra = Lt
        '  For i = 0 To Lt.Length - 1
        '      Letra = Letra + Lt(i)
        '  Next
        ' XE = Lt.Length


        Dim Lin = Letra.Split(",")


        For j = 0 To Lin.Length - 1
            For i = 0 To Lin(j).Length - 1
                Dim Pal = Lin(j).ToCharArray

                '   Console.WriteLine("         " & Pal(i))

                If Char.IsLetterOrDigit(Pal(i)) And i > 0 Then

                ElseIf Pal(i).ToString.Equals("_") And i = 1 Then

                ElseIf Char.IsLetter(Pal(i)) And i = 0 Then
                    Else



                        '  MsgBox((Pal(i)) & "     " & i)
                        ' MsgBox(Lin(j))
                        Return False

                End If
            Next
        Next
        Return True
    End Function
    Private Function Produccion(Pal As String)
        Dim Ingreso = Pal.Split(",")
        Dim Verifi = {0, 0, 0}

        '  Return False







        For j = 0 To DarVecto1.length - 2
            Dim Arra = DarVecto1(j)
            Dim Segundo = DarVecto2(j)

            Dim Partir2 = Segundo(4).ToString.Split(",")
            Dim Partir = Arra(4).ToString.Split(",")


            For i = 0 To Partir2.Length - 1



                Select Case Ingreso.Length

                    Case 3
                        If Partir2(i).ToString.Equals(Ingreso(0)) Then
                            Verifi(0) = 1
                        End If

                        If Partir2(i).ToString.Equals(Ingreso(2)) Then
                            Verifi(2) = 1
                        End If


                    Case 2

                        If Partir2(i).ToString.Equals(Ingreso(0)) Then
                            Verifi(0) = 1
                            Verifi(1) = 1
                        End If

                        If Partir2(i).ToString.Equals(Ingreso(1)) Then
                            Verifi(2) = 1
                            Verifi(1) = 1
                        End If



                End Select

            Next

        Next


        If Ingreso.Length = 3 Then


            For j = 0 To DarVecto1.length - 2
                Dim Arra = DarVecto1(j)



                Dim Partir = Arra(4).ToString.Split(",")


                For i = 0 To Partir.Length - 1

                    If Partir(i).ToString.Equals(Ingreso(1)) Then
                        Verifi(1) = 1
                    End If



                Next

            Next


        End If


        If Verifi(0) = 1 And Verifi(1) = 1 And Verifi(2) = 1 Then
            Return True
        Else
            ' Console.WriteLine(Verifi(0) & "," & Verifi(1) & "," & Verifi(2))
            Dim Mtr = {Xi, Yi, X, Y, Pal}
            PonerVecto5(Mtr)



            Return False
        End If

    End Function
    Private Function Inicial(Pal As String)
        Dim Ingreso = Pal


        '  Return False







        For j = 0 To DarVecto1.length - 2
            Dim Arra = DarVecto2(j)


            Dim Partir = Arra(4).ToString.Split(",")


            For i = 0 To Partir.Length - 1




                If Partir(i).ToString.Equals(Ingreso) Then
                    Return True
                End If




            Next

        Next
        Return False
    End Function


    'ReporteXHTML
    Public Sub IniciarPagina()
        Dim Arc = New Arcaico
        Arc.Guardar(Pagina(), "Pagina.html")
        Process.Start("Pagina.html")
    End Sub
    'Metodos Pagina
    Private Function Pagina()
        Dim InicioPagina = "<html><head><title></title></head><body>"
        Dim Cuerpo = ""
        Dim Titulo As String


        If DarVecto5.length = 1 Then
            Titulo = "<h1>Tokens Correctos</h1>"
            Cuerpo = HacerTablas()

        Else
            Titulo = "<h1>Errores</h1>"
            Cuerpo = ErroresHtml()
        End If


        InicioPagina = InicioPagina & Titulo & Cuerpo & "</body></html>"
        Return InicioPagina
    End Function
    Public Function HacerTablas()
        Dim CuerpoTabla As String = ""
        Dim Tabla = "<table border=1>"
        Dim Contador As Integer = 0
        Tabla = Tabla + " <tr> <th>No</th><th>Simbolo</th>  <th>Terminal/NoTerminal</th> <th>Uso</th>  </tr>"







        For i = 0 To DarVecto1().length - 2
            Dim A = DarVecto1(i)
            Dim Terminal = A(4).ToString.Split(",")
            'Console.WriteLine(A(0) & "  " & A(1) & "  " & A(2) & "  " & A(3) & "  " & A(4) & "  ")





            For k = 0 To Terminal.Length - 1
                CuerpoTabla = CuerpoTabla + "<tr>"
                CuerpoTabla = CuerpoTabla + "
"






                Contador = Contador + 1
                CuerpoTabla = CuerpoTabla & "<td>" & Contador & "</td>" & "<td>" & Terminal(k) & "</td>" & "<td>" & "Terminal" & "</td>" & "<td>" & ComprobacionesHtml(Terminal(k)) & "</td> "




                CuerpoTabla = CuerpoTabla + "</tr>"

            Next

        Next













        For i = 0 To DarVecto2().length - 2
            Dim A = DarVecto2(i)
            Dim Terminal = A(4).ToString.Split(",")
            'Console.WriteLine(A(0) & "  " & A(1) & "  " & A(2) & "  " & A(3) & "  " & A(4) & "  ")





            For k = 0 To Terminal.Length - 1
                CuerpoTabla = CuerpoTabla + "<tr>"
                CuerpoTabla = CuerpoTabla + "
"







                Contador = Contador + 1
                CuerpoTabla = CuerpoTabla & "<td>" & Contador & "</td>" & "<td>" & Terminal(k) & "</td>" & "<td>" & "No Terminal" & "</td>" & "<td>" & ComprobacionesHtml(Terminal(k)) & "</td>"



                CuerpoTabla = CuerpoTabla + "</tr>"
            Next

        Next











        Tabla = Tabla & CuerpoTabla & "</table>"
        Return Tabla
    End Function
    Private Function ComprobacionesHtml(Lt As String)
        Dim Devolver As String = ""



        For j = 0 To DarVecto3.length - 2
            Dim Arra = DarVecto3(j)
            Dim Partir = Arra(4).ToString.Split("}")

            For i = 0 To Partir.Length - 1

                Dim Charsi = Partir(i).ToCharArray
                For p = 0 To Partir(i).Length - 1




                    If Charsi(p).ToString.Equals(Lt.ToString) Then

                        ' Console.WriteLine(Charsi(p).ToString)
                        Dim Party As String = ""

                        For ty = 0 To Charsi.Length - 1
                            If ty = 0 And Charsi(0).ToString.Equals(",") Then
                            Else
                                Party = Party + Charsi(ty)
                            End If
                        Next


                        Devolver = Devolver & Party & "}"
                        Party = ""
                        Devolver = Devolver + "
 <br>"

                        'Exit For
                    End If


                Next
            Next



        Next


        For j = 0 To DarVecto4.length - 2
            Dim Arra = DarVecto4(j)
            Dim Partir = Arra(4).ToString.Split("}")

            For i = 0 To Partir.Length - 1

                Dim Charsi = Partir(i).ToCharArray
                For p = 0 To Partir(i).Length - 1
                    If Charsi(p).ToString.Equals(Lt.ToString) Then

                        '  Console.WriteLine(Charsi(p).ToString)

                        Devolver = Devolver & "[" & Partir(i) & "]"
                        Devolver = Devolver + "
"

                        Exit For
                    End If


                Next
            Next



        Next

        Return Devolver
    End Function
    Private Function ErroresHtml()
        Dim CuerpoTabla As String = ""
        Dim Tabla = "<table border=1>"
        Dim Contador As Integer = 0
        Tabla = Tabla + " <tr> <th>No</th><th>Erro</th>  <th>Columna</th> <th>Línea</th>  </tr>"







        For i = 0 To DarVecto5().length - 2
            Dim A = DarVecto5(i)
            Dim Terminal = A(4).ToString
            'Console.WriteLine(A(0) & "  " & A(1) & "  " & A(2) & "  " & A(3) & "  " & A(4) & "  ")






            CuerpoTabla = CuerpoTabla + "<tr>"
                CuerpoTabla = CuerpoTabla + "
"






                Contador = Contador + 1
            CuerpoTabla = CuerpoTabla & "<td>" & Contador & "</td>" & "<td>" & Terminal & "</td>" & "<td>" & A(0) & "</td>" & "<td>" & A(1) & "</td> "




            CuerpoTabla = CuerpoTabla + "</tr>"

            Next










        Tabla = Tabla & CuerpoTabla & "</table>"


        Return Tabla


    End Function


    'GraficaGraficada
    Public Sub IniciarGrafico()
        If DarVecto5.length = 1 Then
            Dim Arc = New Arcaico
            Arc.Guardar(HacerGrafico, "Grafico.Grafico")
            Dim CmC As String = "dot -Tpng Grafico.Grafico -o Grafico.png"
            Shell("cmd.exe /k" & CmC, 0)


            Try
                Dim Forro = New Form1()

                Forro.Timer1().Start()

            Catch ex As Exception
                MsgBox("El archivo no se ha podido crear vuelva a intentarlo")
            End Try

        Else
            MsgBox("El Archivo Tiene Errores, Revise El Reporte Para Más Detalles")

        End If

    End Sub
    'Metodos Grafico
    Private Function HacerGrafico()





        Dim Cabeza = ("
        digraph Automata_INFinito{
        rankdir = LR;
        Size =" & C4 & "8,5" & C4)


        Dim Salida As String = ""
        Salida = (Cabeza & GraficoInicial() & "
" & CrearGrafico())


        Return Salida
    End Function
    Private Function CrearGrafico()



        Dim Temp As String = ""
        For j = 0 To DarVecto4.length - 2
            Dim Resuelta = DarVecto3(j)
            'For i = 0 To Resuelta.length - 1

            Dim Copia = Resuelta(4)
            For q = 0 To Copia.length - 1

                If Copia(q).ToString.Equals("{") Then
                Else
                    ' Console.WriteLine(Copia(q))
                    Temp = Temp + Copia(q)
                End If


            Next



            '            Next
        Next

        Dim Partir = Temp.Split("}")


        Dim Cuerpa As String = ""




        For i = 0 To Partir.Length - 1
            Dim Prueba = Partir(i)
            Dim FInal As String = ""

            For m = 0 To Partir(i).Length - 1


                If Prueba(m).ToString.Equals(",") And m = 0 Then

                Else
                    FInal = FInal + Prueba(m)


                End If


            Next

            Partir(i) = FInal
        Next




        For j = 0 To Partir.Length - 1


            Dim Rebanar = Partir(j).Split(",")







            Select Case Rebanar.Length
                Case 2
                    Cuerpa = Cuerpa & Rebanar(0) & "->" & Rebanar(1) & "[ label =" & C4 & "Epsilon" & C4 & "];"
                    Cuerpa = Cuerpa & "
"

                Case 3
                    Cuerpa = Cuerpa & Rebanar(0) & "->" & Rebanar(2) & "[ label =" & C4 & Rebanar(1) & C4 & "];"
                    Cuerpa = Cuerpa & "
"
                Case Else

            End Select





        Next
        '  Console.WriteLine(Cuerpa)
        Cuerpa = "node [shape = circle];" & Cuerpa & "}"

        Return Cuerpa


    End Function
    Private Function GraficoInicial()
        Dim Salida As String = "node [shape = doublecircle, color = blue];"

        For i = 0 To DarVecto4.length - 2
            Dim Tempo = DarVecto4(i)
            Console.WriteLine(Tempo(4))
            Salida = Salida & Tempo(4) & ";"
        Next

        Return Salida
    End Function



    Private ReadOnly C4 = Chr(34)
    Private Cuarto As Boolean = True
End Class
