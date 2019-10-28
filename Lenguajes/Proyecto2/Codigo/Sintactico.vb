Imports WindowsApp1.Token
Public Class Sintactico
    Dim NumPre As Integer = 0
    Dim CantidadErroresS0 = 0
    Dim ErrorS0 As Boolean = False
    Dim Pre As Token
    Dim Post As Token
    Dim Lista As List(Of Token)

    Dim Salida As List(Of Token)

    Public Function parsear(List As List(Of Token))

        Lista = List

        Pre = Lista.Item(NumPre)
        NumPre = 0


    




        Dim TK = TokenFuturo(1).DevolverTodo(1).ToLower
        Select Case TK
            Case "clase"
                Clase()
                Asociacion()
            Case "Comentario"
                Comentario()
                Asociacion()
            Case "asociacion"
                Asociacion()
            Case Else
                Console.WriteLine("Error EN Inicio")
        End Select

     

        Return Salida
    End Function
    Private Sub Clase()
        E()
        Parea(Valor.LlaveA)
        M()
        Contenido()
        Contenido()
        Parea(Valor.LlaveC)
        FIn()
    End Sub
    Private Sub Tope()
        Dim TF = TokenFuturo(1)

        Select Case TF.DevolverTodo(1).ToLower
            Case "color"
                C()

            Case "nombre"
                N()
            Case "metodos"
                Metodos()


        End Select
    End Sub
    Private Sub M()
        Dim TF = TokenFuturo(1)

        Select Case TF.DevolverTodo(1).ToLower
            Case "color"
                C()

            Case "nombre"
                N()
            Case "metodos"
                Metodos()


        End Select
      
    End Sub
    Private Sub Contenido()
        Dim TF = TokenFuturo(1)
        Select Case TF.DevolverTodo(1).ToLower
            Case "metodos"

                Metodos()
            Case "atributos"
                Metodos()
            Case Else

        End Select
    End Sub
    Private Sub E()
        Parea(Valor.CorcheteA)
        Parea(Valor.PalabraReservada)
        Parea(Valor.CorcheteC)
    End Sub
    Private Sub N()
        E()
        Parea(Valor.Igual)
        Parea(Valor.Identificador)
        Parea(Valor.PuntoComa)
        M()
    End Sub
    Private Sub C()
        E()
        Parea(Valor.Igual)
        Parea(Valor.Numero)
        Parea(Valor.Coma)
        Parea(Valor.Numero)
        Parea(Valor.Coma)
        Parea(Valor.Numero)
        Parea(Valor.PuntoComa)
        M()
    End Sub
    Private Sub Metodos()
        E()
        Parea(Valor.LlaveA)
        Z()

        Parea(Valor.LlaveC)
        M()
    End Sub
    Private Sub Signo()
        Dim HJ = TokenFuturo(0).DevolverTodo(1)
        Select Case HJ
            Case "+"
                Parea(Valor.Mas)
            Case "-"
                Parea(Valor.Menos)
            Case "#"
                Parea(Valor.Numeral)
            Case Else
                Console.WriteLine("Error  Se Tiene " & Convert.ToString(Pre.getTipoEnString) & "   En Signos")
        End Select

    End Sub
    Private Sub Z()
        Parea(Valor.ParentesisA)
        Signo()
        Parea(Valor.ParentesisC)
        Parea(Valor.Identificador)
        Interna()
    End Sub
    Private Sub Interna()
        Dim TK = TokenFuturo(0).DevolverTodo(1).ToLower

        Select Case TK
            Case ":"
                Parea(Valor.DoblePunto)
                Parea(Valor.Identificador)
                Parea(Valor.PuntoComa)
            Case ";"
                Parea(Valor.PuntoComa)
        End Select

        TK = TokenFuturo(0).DevolverTodo(1).ToLower

        If TK.Equals("(") Then
            Z()
        End If

    End Sub
    Private Sub Comentario()
        E()
        Parea(Valor.LlaveA)
        Conte()
        Parea(Valor.LlaveC)
        Fin()
    End Sub
    Private Sub Conte()
        Dim TF = TokenFuturo(1)

        Select Case TF.DevolverTodo(1).ToLower
            Case "texto"
                E()
                Parea(Valor.Igual)
                Parea(Valor.Comilla)
                Parea(Valor.Comentario)
                Parea(Valor.Comilla)
                Parea(Valor.PuntoComa)
                N()
            Case Else
                N()
                E()
                Parea(Valor.Igual)
                Parea(Valor.Comilla)
                Parea(Valor.Comentario)
                Parea(Valor.Comilla)
                Parea(Valor.PuntoComa)
        End Select
    End Sub
    Private Sub Asociacion()
        E()
        Parea(Valor.LlaveA)
        X()
        Parea(Valor.LlaveC)
        Fin()
    End Sub
    Private Sub X()
        Parea(Valor.Identificador)
        Parea(Valor.DoblePunto)
        Parea(Valor.Identificador)
        Aso()
    End Sub
    Private Sub Aso()
        Dim TK = TokenFuturo(0).DevolverTipo
        Select Case TK
            Case Valor.DoblePunto
                Parea(Valor.DoblePunto)
                Parea(Valor.Identificador)
                Parea(Valor.PuntoComa)
            Case Valor.PuntoComa
                Parea(Valor.PuntoComa)
            
            Case Else
                Console.WriteLine("Error En Aso")
        End Select
        TK = TokenFuturo(0).DevolverTipo
        If TK = Valor.Identificador Then
            X()
        End If
      
    End Sub
    Private Sub Fin()
        Dim TK = TokenFuturo(1).DevolverTodo(1).ToLower
        Select Case TK
            Case "clase"
                Clase()
            Case "comentario"
                Comentario()
        End Select
    End Sub

    Public Function Despues(Indice As Integer)
        Try
            Post = Lista.Item(NumPre + Indice)
            Return Post.DevolverTipo
        Catch ex As Exception
            ' Console.WriteLine("Fuera De Indice? EN DESPUES")
            Return Post.DevolverTipo
        End Try
        Return Post.DevolverTipo
    End Function
    Public Function TokenFuturo(Indice As Integer) As Token
        Try
            Post = Lista.Item(NumPre + Indice)
            Return Post
        Catch ex As Exception
            Console.WriteLine("Fuera De Indice?")
        End Try
        Return Post
    End Function
    Private Sub Parea(Tipo As Valor)

        If Tipo = Pre.DevolverTipo Then

            Dim ToQ As Token = Pre
            Salida = AñadirToken(ToQ, 9)

            NumPre += 1
            Pre = Lista.Item(NumPre)
        ElseIf Tipo.Equals(Valor.Malo) Then
                         NumPre += 1
            Dim ToQ As Token = Pre
            ToQ.ESintac()
            Salida = AñadirToken(ToQ, 9)
        ElseIf Tipo.Equals(Valor.Ultimo) Then
            Console.WriteLine("LLego al Final")
        Else




      

                
                            Dim ToQ As New Token(Valor.Malo, Pre.DevolverTodo(1), Pre.DevolverTodo(2), Pre.DevolverTodo(3))
                            Dim Descripcion = "Error  Se Tiene " & Convert.ToString(Pre.getTipoEnString) & "  Se Esperaba  " & getTipoEnString(Tipo) & "   :   " & Pre.DevolverTodo(1)
                            ToQ.AgregarDescripcion(Descripcion)
                            ToQ.ESintac()

                            Salida = AñadirToken(ToQ, 9)

                Console.WriteLine("Error  Se Tiene " & Convert.ToString(Pre.getTipoEnString) & "  Se Esperaba  " & getTipoEnString(Tipo) & "   :   " & Pre.DevolverTodo(1))

             



        End If
    End Sub
 
    Private Function Sig(Tipo As Valor)

        Dim C = 1
        Dim Tok = Lista.Item(NumPre + C).DevolverTipo

        While Tok <> Valor.Ultimo
            Dim ToPPP = Lista.Item(NumPre - 1).DevolverTipo
            If Tok = Tipo Then
                Return C
            ElseIf Siguiente(Tipo, NumPre + C + 1) Then
                Return -1

            Else
                C += 1
            End If

            Tok = Lista.Item(NumPre + C).DevolverTipo

        End While
        Return -1
    End Function
    Private Function Siguiente(Tipo As Valor, i As Integer)
        Select Case Tipo
            Case Valor.PalabraReservada
                If Despues(i) = Valor.CorcheteC Then

                    Return True
                
                End If
            Case Valor.CorcheteA
                If Despues(i) = Valor.PalabraReservada Then
                    Return True
                

                End If
            Case Valor.CorcheteC
                If Despues(i) = Valor.Igual Or Despues(i) = Valor.LlaveA Then
                    Return True
             
                End If
            Case Valor.Numero
                If Despues(i) = Valor.Coma Or Despues(i) = Valor.PuntoComa Then
                    Return True
               
                End If
            Case Valor.Igual
                If Despues(i) = Valor.Identificador Or Despues(i) = Valor.Comilla Then
                    Return True
                End If
            Case Valor.Identificador
                If Despues(i) = Valor.PuntoComa Or Despues(i) = Valor.DoblePunto Then
                    Return True
                End If


            Case Valor.ParentesisA
                If Despues(i) = Valor.Mas Or Despues(i) = Valor.Menos Or Despues(i) = Valor.Numeral Then
                    Return True
                End If
            Case Valor.ParentesisC
                If Despues(i) = Valor.Identificador Then
                    Return True
                End If
            Case Valor.LlaveA
                If Despues(i) = Valor.Identificador Or Despues(i) = Valor.ParentesisA Then

                    Return True
                End If
            Case Valor.LlaveC
                If Despues(i) = Valor.CorcheteA Or Despues(i) = Valor.LlaveC Then
                    Return True
                End If
            Case Valor.Mas
                If Despues(i) = Valor.CorcheteC Then
                    Return True
                End If
            Case Valor.Menos
                If Despues(i) = Valor.CorcheteC Then
                    Return True
                End If
            Case Valor.Numeral
                If Despues(i) = Valor.CorcheteC Then
                    Return True
                End If
            Case Valor.DoblePunto
                If Despues(i) = Valor.Identificador Then
                    Return True
                End If
            
            Case Valor.PuntoComa
                If Despues(i) = Valor.Identificador Or Despues(i) = Valor.LlaveC Or Despues(i) = Valor.CorcheteA Or Despues(i) = Valor.ParentesisA Then
                    Return True
                End If
            Case Valor.Coma
                If Despues(i) = Valor.Numero Then
                    Return True
                End If
            Case Valor.Comilla
                If Despues(i) = Valor.Comentario Or Despues(i) = Valor.PuntoComa Then
                    Return True
                End If
            Case Valor.Comentario
                If Despues(i) = Valor.Comilla Then
                    Return True
                End If
            Case Else
                ' Console.WriteLine("No se tiene un/una " & Convert.ToString(getTipoEnString(Tipo)))
                Return False









        End Select
        Return False
    End Function
   
    Private Function getTipoEnString(Tipo As Valor) As String
        Select Case Tipo
            Case Valor.PalabraReservada
                Return "PalabraReservada"
            Case Valor.CorcheteA
                Return "CorcheteA"
            Case Valor.CorcheteC
                Return "CorcheteC"
            Case Valor.Numero
                Return "Numero"
            Case Valor.Igual
                Return "Igual"
            Case Valor.Identificador
                Return "Identificador"
            Case Valor.Guion
                Return "Guion"
            Case Valor.ParentesisA
                Return "ParentesisA"
            Case Valor.ParentesisC
                Return "ParentesisC"
            Case Valor.LlaveA
                Return "LlaveA"
            Case Valor.LlaveC
                Return "LlaveC"
            Case Valor.Mas
                Return "Mas"
            Case Valor.Menos
                Return "Menos"
            Case Valor.Numeral
                Return "Numeral"
            Case Valor.DoblePunto
                Return "DoblePunto"
            Case Valor.Punto
                Return "Punto"
            Case Valor.PuntoComa
                Return "PuntoComa"
            Case Valor.Coma
                Return "Coma"
            Case Valor.Comilla
                Return "Comilla"
            Case Valor.Comentario
                Return "Comentario"
            Case Valor.Malo
                Return "Malo"
            Case Valor.Ultimo
                Return "Ultimo"




            Case Else
                Return "Desconocido  "
        End Select
    End Function

    Private Function getTipoErrorEnString(Tipo As Valor) As String
        Select Case Tipo
            Case Valor.PalabraReservada
                Return "PalabraReservada"
            Case Valor.CorcheteA
                Return "CorcheteA"
            Case Valor.CorcheteC
                Return "CorcheteC"
            Case Valor.Numero
                Return "Numero"
            Case Valor.Igual
                Return "Igual"
            Case Valor.Identificador
                Return "Identificador"
            Case Valor.Guion
                Return "Guion"
            Case Valor.ParentesisA
                Return "ParentesisA"
            Case Valor.ParentesisC
                Return "ParentesisC"
            Case Valor.LlaveA
                Return "LlaveA"
            Case Valor.LlaveC
                Return "LlaveC"
            Case Valor.Mas
                Return "Mas"
            Case Valor.Menos
                Return "Menos"
            Case Valor.Numeral
                Return "Numeral"
            Case Valor.DoblePunto
                Return "DoblePunto"
            Case Valor.Punto
                Return "Punto"
            Case Valor.PuntoComa
                Return "PuntoComa"
            Case Valor.Coma
                Return "Coma"
            Case Valor.Comilla
                Return "Comilla"
            Case Valor.Comentario
                Return "Comentario"
            Case Valor.Malo
                Return "Malo"
            Case Valor.Ultimo
                Return "Ultimo"




            Case Else
                Return "Desconocido  "
        End Select
    End Function
    Private Function AñadirToken(Token As Token, A As Integer)

        Static Salida As New List(Of Token)()
        If A = 1 Then
            Salida.Clear()
            For Each Token In Me.Salida
                Salida.Add(Token)
            Next
        Else
            Salida.Add(Token)
        End If





        Return Salida
    End Function

End Class
