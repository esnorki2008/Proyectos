Imports WindowsApp1.Token


Public Class Lexico
    Private Salida As New List(Of Token)

    Private Leyendo = ""
    Private X As Integer = 0
    Private Y As Integer = 0
    Private PosicionX = 0
    Private Perdonar As Boolean = False
    Private Identificador As Boolean = False
    Private Numero As Boolean = False
    Private Comentario As Boolean = False
    Private PosicionY = 0

    Private Function AñadirMalo()

        Static Salida As New List(Of Token)()
        If X < 0 Then
            X = 0
        End If
        Dim Tok As New Token(Valor.Malo, Leyendo, X, Y)
        Tok.ELex()
        Salida.Add(Tok)
        Leyendo = ""
        Dim S = Tok
        'Console.WriteLine(S.getTipoEnString + " Con Lexema De " + S.DevolverTodo(1))
        Return Salida
    End Function
    Private Function AñadirToken(ByVal tipo As Valor)
        Static salida = New List(Of Token)
        If X < 0 Then
            X = 0
        End If
        Dim Tok As New Token(tipo, Leyendo, X, Y)
        If tipo = Valor.Malo Then

            Tok.ELex()

        End If

        salida.Add(Tok)      
        Leyendo = ""
        Dim S = Tok
        '   Console.WriteLine(S.getTipoEnString + " Con Lexema De " + S.DevolverTodo(1))
        Return salida
    End Function

    Public Function Analizar(Texto As String)
        Dim Lineas = Texto.Split(Chr(10))

        For Me.PosicionY = 0 To Lineas.Length - 1
            Y = Me.PosicionY
            Dim TextoX = Lineas(Me.PosicionY)
            For Me.PosicionX = 0 To Lineas(Me.PosicionY).Length - 1
                X = Me.PosicionX

                Estados(TextoX(Me.PosicionX), Lineas)

            Next
            Me.PosicionX = 0
        Next

        Return salida
    End Function

    Private Sub Estados(Letra As Char, Texto As String())
      


            Select Case Letra
                Case " "
                Case Chr(11)

                Case "["
                If Comentario Then
                    Leyendo += Letra
                Else
                    Corchetes(Letra, Texto)
                    Numeros(Letra, Texto)
                    Leyendo += Letra
                    X = Me.PosicionX
                    Salida = AñadirToken(Valor.CorcheteA)
                End If
            Case "]"
                If Comentario Then
                    Leyendo += Letra
                Else
                    Corchetes(Letra, Texto)
                    Numeros(Letra, Texto)
                    Leyendo += Letra
                    X = Me.PosicionX
                    Salida = AñadirToken(Valor.CorcheteC)
                End If
            Case "{"
                If Comentario Then
                    Leyendo += Letra
                Else
                    Corchetes(Letra, Texto)
                    Numeros(Letra, Texto)
                    Leyendo += Letra
                    X = Me.PosicionX
                    Salida = AñadirToken(Valor.LlaveA)
                End If
            Case "}"
                If Comentario Then
                    Leyendo += Letra
                Else
                    Corchetes(Letra, Texto)
                    Numeros(Letra, Texto)
                    Leyendo += Letra
                    X = Me.PosicionX
                    Salida = AñadirToken(Valor.LlaveC)
                End If
            Case "("
                If Comentario Then
                    Leyendo += Letra
                Else
                    Corchetes(Letra, Texto)
                    Numeros(Letra, Texto)
                    Leyendo += Letra
                    X = Me.PosicionX
                    Salida = AñadirToken(Valor.ParentesisA)
                End If
            Case ")"
                If Comentario Then
                    Leyendo += Letra
                Else
                    Corchetes(Letra, Texto)
                    Numeros(Letra, Texto)
                    Leyendo += Letra
                    X = Me.PosicionX
                    Salida = AñadirToken(Valor.ParentesisC)
                End If
            Case "="
                If Comentario Then
                    Leyendo += Letra
                Else
                    Corchetes(Letra, Texto)
                    Numeros(Letra, Texto)
                    Leyendo += Letra
                    X = Me.PosicionX
                    Salida = AñadirToken(Valor.Igual)
                End If
            Case "+"
                If Comentario Then
                    Leyendo += Letra
                Else
                    Corchetes(Letra, Texto)
                    Numeros(Letra, Texto)
                    Leyendo += Letra
                    X = Me.PosicionX
                    Salida = AñadirToken(Valor.Mas)
                End If
            Case Chr(9)
            Case "	"
            Case "-"
                If Comentario Then
                    Leyendo += Letra
                Else
                    Corchetes(Letra, Texto)
                    Numeros(Letra, Texto)
                    Leyendo += Letra
                    X = Me.PosicionX
                    Salida = AñadirToken(Valor.Menos)
                End If
            Case "#"
                If Comentario Then
                    Leyendo += Letra
                Else
                    Corchetes(Letra, Texto)
                    Numeros(Letra, Texto)
                    Leyendo += Letra
                    X = Me.PosicionX
                    Salida = AñadirToken(Valor.Numeral)
                End If
            Case ";"
                If Comentario Then
                    Leyendo += Letra
                Else
                    Corchetes(Letra, Texto)
                    Numeros(Letra, Texto)
                    Leyendo += Letra
                    X = Me.PosicionX
                    Salida = AñadirToken(Valor.PuntoComa)
                End If

            Case ","
                If Comentario Then
                    Leyendo += Letra
                Else
                    Corchetes(Letra, Texto)
                    Numeros(Letra, Texto)
                    Leyendo += Letra
                    X = Me.PosicionX
                    Salida = AñadirToken(Valor.Coma)
                End If
            Case ":"
                If Comentario Then
                    Leyendo += Letra
                Else
                    Corchetes(Letra, Texto)
                    Numeros(Letra, Texto)
                    Leyendo += Letra
                    X = Me.PosicionX
                    Salida = AñadirToken(Valor.DoblePunto)
                End If
            Case Chr(147)
                If Perdonar = True Then
                    Perdonar = False
                Else
                    Perdonar = True
                End If
                Comentario = True
                Corchetes(Letra, Texto)
                Numeros(Letra, Texto)
                Leyendo += Letra
                Salida = AñadirToken(Valor.Comilla)
            Case Chr(34)
                If Perdonar = True Then
                    Perdonar = False
                Else

                    Perdonar = True
                End If
                Comentario = True
                Corchetes(Letra, Texto)
                Numeros(Letra, Texto)
                Leyendo += Letra
                Salida = AñadirToken(Valor.Comilla)
            Case Chr(148)
                If Perdonar = True Then
                    Perdonar = False
                Else
                    Perdonar = True
                End If
                Comentario = True
                Corchetes(Letra, Texto)
                Numeros(Letra, Texto)
                Leyendo += Letra
                Salida = AñadirToken(Valor.Comilla)
            Case Else
                Dim S As String = Leyendo
                '  Console.WriteLine(Letra)


                If (Char.IsLetter(Letra) = True) And (Perdonar = False) And (Numero = False) Then
                    Identificador = True
                    Corchetes(Letra, Texto)
                ElseIf (Char.IsNumber(Letra) = True) And Perdonar = False And Identificador = False Then
                    Numero = True

                    Numeros(Letra, Texto)
                ElseIf (Char.IsNumber(Letra) = True) And Perdonar = False And Identificador = True And Numero = False Then

                    Corchetes(Letra, Texto)

                ElseIf (Letra.ToString.Equals("_")) And (Perdonar = False) And S.Length > 1 Then

                    Corchetes(Letra, Texto)
                Else
                    Leyendo += Letra
                    If Perdonar = True Then
                    Else
                        Salida = AñadirToken(Valor.Malo)
                    End If

                End If

        End Select
       
    End Sub
    Private Sub Corchetes(Letra As Char, Texto As String())

      

        If Numero = False Then



            Select Case Letra
                Case " "

                Case Chr(11)



                Case Else
                    If Char.IsLetterOrDigit(Letra) Then

                        Leyendo += Letra

                    ElseIf Letra.ToString.Equals("_") Then
                        Leyendo += Letra

                    Else
                        Identificador = False

                        X = X - Leyendo.length
                        Select Case Leyendo.ToLower

                            Case "clase"
                                Salida = AñadirToken(Valor.PalabraReservada)

                            Case "nombre"
                                Salida = AñadirToken(Valor.PalabraReservada)
                            Case "color"
                                Salida = AñadirToken(Valor.PalabraReservada)
                            Case "texto"
                                Salida = AñadirToken(Valor.PalabraReservada)
                            Case "atributos"
                                Salida = AñadirToken(Valor.PalabraReservada)
                            Case "asociacion"
                                Salida = AñadirToken(Valor.PalabraReservada)
                            Case "metodos"
                                Salida = AñadirToken(Valor.PalabraReservada)
                            Case "comentario"
                                Salida = AñadirToken(Valor.PalabraReservada)
                            Case Else
                                If Leyendo.Equals("") Then
                                ElseIf Comentario = True Then
                                    Identificador = False
                                    AñadirToken(Valor.Comentario)
                                    Comentario = False
                                Else
                                    Identificador = False
                                    Salida = AñadirToken(Valor.Identificador)
                                End If



                        End Select



                    End If


            End Select






        End If


    End Sub
    Private Sub Numeros(Letra As Char, Texto As String())
        Select Case Letra
            Case " "

            Case Chr(11)



            Case Else
                If Char.IsNumber(Letra) Then

                    Leyendo += Letra
                    '   Salida = AñadirToken(Valor.Numero)
                Else




                    If Leyendo.Equals("") Then
                    Else
                        Numero = False

                        Salida = AñadirToken(Valor.Numero)
                    End If





                End If


        End Select









    End Sub


End Class
