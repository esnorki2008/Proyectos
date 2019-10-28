Imports WindowsApp1.Token
Public Class Grafica
    Private Sub GuardarPagina(Contenido As String, Ruta As String)
        Try
            Dim fileWritter = My.Computer.FileSystem.OpenTextFileWriter(Ruta, False)
            fileWritter.Write(Contenido)
            fileWritter.Close()





        Catch exc As Exception
            MessageBox.Show("El archivo no se puede guardar, o está siendo utilizado por otra aplicación", "My Application",
  MessageBoxButtons.OK, MessageBoxIcon.Asterisk)
        Finally
        End Try

    End Sub
    Private Function CrearSeparaciones(ListToken As List(Of Token))
        Dim Cuerpo = ""
        Dim Asociacion As Boolean = False
        Dim Comilla = Chr(34)
        Dim NombreClase As Boolean = False
        Dim NombreComentario As Boolean = False
        Dim Metodo As Boolean = False
        Dim Atributo As Boolean = False
        Dim Clase As Boolean = False
        Dim Comentario As Boolean = False
        Dim CantidadLlaves = 0
        Dim CantidadDoblePuntos = 0
        Dim ListaDeClases As New List(Of ClaseGrafica)
        Dim Union = {"", "", ""}
        Dim ColorHex = {0, 0, 0}
        Dim Aso = ""
        Dim CantidadDeColores = 0
        Dim HexC = ""



        Dim NCla As Boolean = False
        Dim ContenidoClase = ""
        Dim CabezaClase = ""

        Dim ContenidoComentario = ""
        Dim CabezaComentario = ""
        Dim NCOM As Boolean = False

        For Each Token In ListToken


            Select Case Token.DevolverTodo(1).ToLower
                Case "nombre"
                    If NombreClase Then
                        NCla = True
                    ElseIf Comentario Then
                        NCOM = True
                    End If
                Case "metodos"
                    Atributo = False

                    CantidadLlaves = 0
                    ContenidoClase = ContenidoClase & "| Metodos: "
                    Metodo = True
                Case "atributos"
                    Metodo = False

                    CantidadLlaves = 0
                    ContenidoClase = ContenidoClase & "| Atributos:  "
                    Atributo = True
                Case "asociacion"
                    CantidadDoblePuntos = 0
                    Metodo = False
                    Atributo = False
                    Asociacion = True
                Case "comentario"

                    Metodo = False
                    Atributo = False

                    NombreComentario = True
                    If Comentario = True Then

                        Comentario = False
                    End If
                Case "clase"
                    Metodo = False
                    Atributo = False

                    NombreClase = True
                    If Clase = True Then

                        Clase = False
                    End If
            End Select


            Select Case Token.DevolverTipo
                Case Valor.Numero
                    ColorHex(CantidadDeColores) = Integer.Parse(Token.DevolverTodo(1))
                    CantidadDeColores += 1

                    If CantidadDeColores = 3 Then
                        HexC = "#" & Hexadecimal(ColorHex(0), ColorHex(1), ColorHex(2))
                        CantidadDeColores = 0
                        Console.WriteLine(HexC)
                    End If
                Case Valor.LlaveC
                    CantidadLlaves = CantidadLlaves + 1
                    If CantidadLlaves = 2 Then
                        Dim Color = "  fillcolor=" & Comilla & HexC & Comilla & "  style=" & Comilla & "  filled" & Comilla
                        Cuerpo = Cuerpo & CabezaClase & ContenidoClase & Comilla & vbLf & "  shape = " & Comilla & "record" & Comilla & Color & " ]"
                        ContenidoClase = ""
                        CabezaClase = ""
                    End If


                    If Comentario = True Then
                        CantidadLlaves = 0

                        Dim Color = "  fillcolor=" & Comilla & "yellow" & Comilla & "  style=" & Comilla & "  filled" & Comilla
                        Cuerpo = Cuerpo & CabezaComentario & "  " & ContenidoComentario & "  " & Comilla & vbLf & "  shape = " & Comilla & "record" & Comilla & Color & " ]"

                        ContenidoComentario = ""
                        Comentario = False
                    End If


                    If Asociacion Then
                        CantidadLlaves = 0
                        Cuerpo = Cuerpo & Aso
                        Asociacion = False
                    End If
                Case Valor.Comentario
                    ContenidoComentario = ContenidoComentario & " " & Token.DevolverTodo(1)
                Case Valor.Identificador
                    If NCla Then
                        Dim CA As New ClaseGrafica(Token.DevolverTodo(1), ClaseGrafica.Tipo.Clase)
                        ListaDeClases.Add(CA)

                        NombreClase = False
                        NCla = False
                        Clase = True

                        CabezaClase = vbLf & "  " & Comilla & Token.DevolverTodo(1).ToLower & Comilla & " [label=" & Comilla & " " & Token.DevolverTodo(1)
                    ElseIf NombreComentario Then
                        Dim CA As New ClaseGrafica(Token.DevolverTodo(1), ClaseGrafica.Tipo.Comentario)
                        ListaDeClases.Add(CA)


                        NombreComentario = False
                        Comentario = True
                        CabezaComentario = vbLf & "  " & Comilla & Token.DevolverTodo(1).ToLower & Comilla & " [label=" & Comilla & "  "
                    ElseIf Atributo Then
                        ContenidoClase = ContenidoClase & " " & Token.DevolverTodo(1)
                    ElseIf Metodo Then
                        ContenidoClase = ContenidoClase & " " & Token.DevolverTodo(1)
                    ElseIf Asociacion Then
                        Union(CantidadDoblePuntos) = Token.DevolverTodo(1)

                    Else


                    End If
                Case Valor.Mas
                    ContenidoClase = ContenidoClase & Token.DevolverTodo(1)
                Case Valor.Menos
                    ContenidoClase = ContenidoClase & Token.DevolverTodo(1)
                Case Valor.Numeral
                    ContenidoClase = ContenidoClase & Token.DevolverTodo(1)
                Case Valor.ParentesisA
                    ContenidoClase = ContenidoClase & " \n " & Token.DevolverTodo(1)
                Case Valor.ParentesisC
                    ContenidoClase = ContenidoClase & Token.DevolverTodo(1)
                Case Valor.PuntoComa


                    If Asociacion Then
                        Dim P1 As Boolean = False
                        Dim P2 As Boolean = False



                        If CantidadDoblePuntos = 2 Then


                            For Each item In ListaDeClases



                                If item.RetornarTodo(0).ToString.ToLower.Equals(Union(0).ToLower) Then
                                    P1 = True
                                    Console.WriteLine(item.RetornarTodo(0) & "#" & Union(0))
                                End If

                                If item.RetornarTodo(0).ToString.ToLower.Equals(Union(2).ToLower) Then
                                    P2 = True
                                    Console.WriteLine(item.RetornarTodo(0) & "#" & Union(2))
                                End If

                            Next
                        ElseIf CantidadDoblePuntos = 1 Then


                            For Each item In ListaDeClases



                                If item.RetornarTodo(0).ToString.ToLower.Equals(Union(0).ToLower) Then
                                    P1 = True
                                    Console.WriteLine(item.RetornarTodo(0) & "#" & Union(0))
                                End If


                                If item.RetornarTodo(0).ToString.ToLower.Equals(Union(1).ToLower) Then
                                    Console.WriteLine(item.RetornarTodo(0) & "#" & Union(1))
                                    P2 = True
                                End If

                            Next
                        End If

                        If P1 And P2 Then

                            If CantidadDoblePuntos = 2 Then
                                Console.WriteLine("DosDosPuntos")

                                Aso = Aso & Comilla & Union(0).ToLower & Comilla & "->" & Union(2).ToLower


                                Aso = Aso & " [ arrowhead=" & Comilla & TipoAsociacion(Union(1)) & Comilla & "]"

                            ElseIf CantidadDoblePuntos = 1 Then
                                Console.WriteLine("UnDosPuntos")
                                Aso = Aso & Comilla & Union(0).ToLower & Comilla & "->" & Union(1).ToLower


                                Aso = Aso & "[arrowhead=" & Comilla & "none" & Comilla & "   style=dashed]"
                            End If
                            CantidadDoblePuntos = 0
                        Else

                            CantidadDoblePuntos = 0
                        End If

                    End If
                Case Valor.DoblePunto
                    CantidadDoblePuntos = CantidadDoblePuntos + 1
                    If Metodo Or Atributo Then
                        ContenidoClase = ContenidoClase & " As "
                    End If

            End Select



        Next

        Return Cuerpo
    End Function


    Public Sub ArmarGrafica(ListToken As List(Of Token))
        Dim E = vbLf
        Dim Comilla = Chr(34)

        Dim inicio As String
        inicio = "digraph g {" &
"	graph [" & E &
        "rankdir = " & Comilla & "LR" & Comilla &
    "];" &
    "node [" &
       " fontsize = " & Comilla & "16" & Comilla &
        "shape = " & Comilla & "ellipse" & Comilla &
    "];" &
    "edge [" &
    "];"

        Dim Final As String = "}"

        Dim Grafico = inicio & vbLf & CrearSeparaciones(ListToken) & vbLf & Final
        GuardarPagina(Grafico, "Grafico.Grafico")

    End Sub
    Private Function TipoAsociacion(Text As String)
        Select Case Text.ToLower
            Case "agregacion"
                Return "odiamond"
            Case "composicion"
                Return "diamond"
            Case "herencia"
                Return "onormal"
            Case "asociacionsimple"
                Return "none"
            Case Else
                Return "none"
        End Select

    End Function





    Public Function Hexadecimal(CA As Integer, CB As Integer, CC As Integer)

        If CA > 255 Then CA = 255
        If CB > 255 Then CB = 255
        If CC > 255 Then CC = 255

        Dim Matriz = {Convertir(CA), Convertir(CB), Convertir(CC)}

        Dim R = Matriz(0) & Matriz(1) & Matriz(2)

        Return R
    End Function
    Private Function Convertir(N As Integer)
       
        Dim Num As String = ""
        Dim He As Integer
        If N > 16 Then
            He = N Mod 16
            If N Mod 16 = 0 Then
                Num = Base16(N / 16)
                If N / 16 <> 16 Then
                    Num = Num & "0"
                End If
            Else
                Dim Sac = 1
                Dim Div = N / 16
                While True
                    If Div - Sac < 0 Then
                        Exit While
                    Else
                        Sac += 1
                    End If

                End While
                Sac = Sac - 1

                Num = Base16(Sac) & Base16(N Mod 16)
            End If
        Else
            Return "0" & Base16(N)

        End If

        Return Num
    End Function
    Private Function Base16(Num10 As Integer) As String
        Dim Num As String = ""
        Select Case Num10
            Case 1
                Num = "1"
            Case 2
                Num = "2"
            Case 3
                Num = "3"
            Case 4
                Num = "4"
            Case 5
                Num = "5"
            Case 6
                Num = "6"
            Case 7
                Num = "7"
            Case 8
                Num = "8"
            Case 9
                Num = "9"
            Case 10
                Num = "A"
            Case 11
                Num = "B"
            Case 12
                Num = "C"
            Case 13
                Num = "D"
            Case 14
                Num = "E"
            Case 15
                Num = "F"
            Case 15
                Num = "FF"
        End Select
        Return Num
    End Function
End Class
