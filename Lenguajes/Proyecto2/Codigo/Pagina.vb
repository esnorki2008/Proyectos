Imports WindowsApp1.Token

Public Class Pagina
    Private CantidadB As Integer = 0
    Private CantidadM As Integer = 0
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

    Public Sub IniciarPagina(listaTokens As List(Of Token), Cs As String)

        GuardarPagina(CrearPagina(listaTokens, Cs), "Pagina.html")
        Process.Start("Pagina.html")
    End Sub

    Private Function CrearPagina(listaTokens As List(Of Token), Cs As String)
        Dim Estado As Boolean = True
        Dim Pagina As String
        Dim Cabeza = "<html><head><title>PaginaDeReportes</title>" & Cs & "</head><body>"
        Dim CuerpoB As String = "<center><H1>Reporte De Tokens</h1></center><table border=""1"" id=""mitabla""> <th>Número </th><th>Tipo</th><th>Lexema </th><th>Columna </th><th>Linea </th>"
        Dim CuerpoM As String = "<center><H1>Reporte De Errores</h1></center><table border=""1"" id=""mitabla""><th>Número </th><th>Tipo</th><th>Error</th><th>Columna </th><th>Linea </th><th>Descripcion</th>"
        Dim Cola = "</table></body></html>"
        Dim css = Cs


        For Each cosa In listaTokens
            If cosa.DevolverTipo = Valor.Malo Then
                CantidadM = CantidadM + 1
                CuerpoM = CuerpoM & LineaMala(cosa)
                Estado = False
            Else
                CantidadB = CantidadB + 1
                CuerpoB = CuerpoB & LineaBuena(cosa)

            End If
        Next

        If Estado Then
            Pagina = Cabeza & CuerpoB & Cola
        Else
            Pagina = Cabeza & CuerpoM & Cola
        End If

        Return Pagina
    End Function


    Public Function LineaMala(Toke As Token)
        Dim Str As String
        Dim Tipo As String
        If Toke.DevolverEstadoSintactico Then
            Tipo = "Error Lexico"
        Else
            Tipo = "Error Sintactico"
        End If
        Console.WriteLine(Toke.DevolverEstadoSintactico.ToString)
        Dim Err As String
        Err = Toke.DevolverTodo(1)
        Dim Columna = Toke.DevolverTodo(2)
        Dim Linea = Toke.DevolverTodo(3)
        Dim Descripcion = Toke.DevolverDescripcion
        If Toke.DevolverDescripcion.Equals("Es Un Símbolo No Reconocido") Then
            Descripcion = Toke.DevolverTodo(1) & "  Es Un Símbolo No Reconocido"
        End If
        Str = "<tr>" &
                 " <th>" & CantidadM & "</th>" &
                " <th>" & Tipo & "</th>" &
                 " <th>" & Err & "</th>" &
                  " <th>" & Columna & "</th>" &
                 " <th>" & Linea & "</th>" &
                  " <th>" & Descripcion & "</th>" &
              "</tr>"

        Return Str
    End Function


    Public Function LineaBuena(Toke As Token)
        Dim Str As String
        Dim Tipo As String
        Tipo = Toke.getTipoEnString
        Dim Err As String
        Err = Toke.DevolverTodo(1)
        Dim Columna = Toke.DevolverTodo(2)
        Dim Linea = Toke.DevolverTodo(3)

        
        Str = "<tr>" &
                 " <th>" & CantidadB & "</th>" &
                " <th>" & Tipo & "</th>" &
                 " <th>" & Err & "</th>" &
                  " <th>" & Columna & "</th>" &
                 " <th>" & Linea & "</th>" &
                "</tr>"

        Return Str
    End Function
End Class
