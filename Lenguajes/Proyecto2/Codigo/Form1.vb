Imports WindowsApp1.Token
Imports WindowsApp1.Colores
Imports WindowsApp1.ClaseGrafica
Imports System.Text

Public Class Form1
    Private Y As Integer = 0
    Private X As Integer = 0
    Private salida As List(Of Token)

    Private Sub Pr()
        salida = New List(Of Token)

        salida.Add(New Token(Valor.PalabraReservada, "dds", 2, 3))


        Dim S = salida.Item(0)


        Console.WriteLine(S.getTipoEnString)
    End Sub

    Private Sub Button1_Click(sender As Object, e As EventArgs) Handles Button1.Click
        Dim AnalizadorLexico As New Lexico()
        Dim ListaTokensDelLexico As List(Of Token) = AnalizadorLexico.Analizar(RTB.Text)
        Dim Lista As New List(Of Token)
        For Each item In ListaTokensDelLexico
            If item.DevolverTipo = Valor.Malo Then
            Else
                Lista.Add(item)
            End If
        Next


        Dim Ag As New Token(Valor.Ultimo, " ", -1, -1)
        Lista.Add(Ag)

        Dim AnalizadorSintactico As New Sintactico()
        Dim ListaTokensDelSintactico As List(Of Token) = AnalizadorSintactico.parsear(Lista)

        Dim i = 0

        Dim ListaColorear As New List(Of Token)

        For Each item In ListaTokensDelSintactico



            If item.DevolverEstadoSintactico Then

            Else
                i = 0
                While True
                    Dim Tok = ListaTokensDelLexico.Item(i)

                    If item.DevolverTodo(2).Equals(Tok.DevolverTodo(2)) And item.DevolverTodo(3).Equals(Tok.DevolverTodo(3)) And item.DevolverTipo = Valor.Malo Then
                        Dim Tik As New Token(Valor.Malo, Tok.DevolverTodo(1), Tok.DevolverTodo(2), Tok.DevolverTodo(3))
                        If item.DevolverEstadoSintactico Then
                        Else
                            Tik.ESintac()
                            Tik.AgregarDescripcion(item.DevolverDescripcion)
                        End If
                        ListaTokensDelLexico(i) = Tik
                        Console.WriteLine(ListaTokensDelLexico(i).getTipoEnString & "  oooooo  " & ListaTokensDelLexico(i).DevolverTodo(1))
                        Exit While
                    Else
                        If Tok.DevolverTipo = Valor.Ultimo Then
                            Exit While
                        Else
                            i = i + 1
                        End If

                    End If


                End While

            End If


        Next

        salida = ListaTokensDelLexico

        RTB.Clear()
        Colorear(ListaTokensDelLexico)


    End Sub






    Private Sub Button4_Click(sender As Object, e As EventArgs) Handles Button4.Click
        Dim AnalizadorLexico As New Lexico()
        Dim listaTokens As List(Of Token) = AnalizadorLexico.Analizar(RTB.Text)
        Dim Lista = listaTokens
        Dim Ag As New Token(Valor.Ultimo, " ", -1, -1)
        Lista.Add(Ag)
        RTB.Clear()
        Colorear(Lista)

        Y = 0
        X = 0

        For Each item In Lista
            If item.DevolverTipo = Valor.Malo Then
                Console.WriteLine(item.DevolverTodo(1))
            End If

        Next

    End Sub



    Private Sub Colorear(Lista As List(Of Token))
        Dim Lis As New List(Of Colores)


        For Each Token In Lista
            Dim Texto = Token.DevolverTodo(1)
            Dim Tipo = Token.DevolverTipo








            RTB.Text = RTB.Text + Texto
            Dim Largo = Texto.Length
            '   RTB.Select(RTB.TextLength - Largo, Largo)

            Dim C As Color = Color.Black


            '  Console.WriteLine(RTB.TextLength - Largo)
            Select Case Tipo
                Case Valor.PalabraReservada

                    C = Color.Yellow

                Case Valor.CorcheteA

                    C = Color.Red
                Case Valor.CorcheteC
                    C = Color.Red

                Case Valor.ParentesisA
                    C = Color.Red
                Case Valor.ParentesisC
                    C = Color.Red
                Case Valor.LlaveA
                    RTB.Text = RTB.Text & Chr(10)
                    Largo = Largo + 1
                    C = Color.Red
                Case Valor.LlaveC
                    RTB.Text = RTB.Text & Chr(10)
                    Largo = Largo + 1
                    C = Color.Red
                Case Valor.Mas
                    C = Color.Green
                Case Valor.Menos
                    C = Color.Green
                Case Valor.Numeral
                    C = Color.Green
                Case Valor.DoblePunto
                    C = Color.Black
                Case Valor.Punto
                    C = Color.Black
                Case Valor.PuntoComa
                    C = Color.Black
                    RTB.Text = RTB.Text & Chr(10)
                    Largo = Largo + 1
                Case Valor.Coma

                    C = Color.Black
                Case Valor.Comilla
                    C = Color.Purple

                Case Valor.Comentario
                    C = Color.Purple
                Case Valor.Malo
                    C = Color.Blue

                Case Valor.Ultimo
                Case Valor.Identificador
                Case Valor.Igual
                Case Valor.Numero

                    'Return "Ultimo"



                Case Else



            End Select
            ' Console.WriteLine(C.ToString)
            Dim Col As New Colores(C, RTB.TextLength - Largo, Largo, Texto)
            Lis.Add(Col)
        Next

        For Each ColorNuebo In Lis


            RTB.Select(ColorNuebo.Retorno(0), ColorNuebo.Retorno(1))

            RTB.SelectionColor = ColorNuebo.Retorno(2)


        Next

        'RTB.Text = RTB.Text & Chr(10)
    End Sub



    Private Sub Button3_Click_1(sender As Object, e As EventArgs) Handles Button3.Click
        Dim Pag As New Pagina()
        Pag.IniciarPagina(salida, TextBox2.Text)
    End Sub

    Private Sub Button5_Click(sender As Object, e As EventArgs) Handles Button5.Click
        Dim AnalizadorLexico As New Lexico()
        Dim ListaTokensDelLexico As List(Of Token) = AnalizadorLexico.Analizar(RTB.Text)
        HacerGraphviz(ListaTokensDelLexico)


    End Sub
    Private Sub HacerGraphviz(ListaTokensDelLexico As List(Of Token))
        Dim GR As New Grafica
        GR.ArmarGrafica(ListaTokensDelLexico)
        Dim CmC As String = "dot -Tpng Grafico.Grafico -o Grafico.png  exit"
        Shell("cmd.exe /k " & CmC)
        Timer1.Start()
    End Sub

    Private Sub Timer1_Tick(sender As Object, e As EventArgs) Handles Timer1.Tick
        Process.Start("Grafico.png")
        Timer1.Stop()
    End Sub

    Private Sub TextBox1_TextChanged(sender As Object, e As EventArgs) Handles TextBox1.TextChanged
        Console.WriteLine(Asc(TextBox1.Text(0)))
    End Sub

    Private Sub Button6_Click(sender As Object, e As EventArgs)


    End Sub

    Private Sub MenúArchivoToolStripMenuItem_Click(sender As Object, e As EventArgs) Handles MenúArchivoToolStripMenuItem.Click

    End Sub



    Private Sub ColorearToolStripMenuItem_Click(sender As Object, e As EventArgs) Handles ColorearToolStripMenuItem.Click
        Dim Pag As New Pagina()
        Pag.IniciarPagina(salida, TextBox2.Text)
    End Sub

    Private Sub MenúAnalizarToolStripMenuItem_Click(sender As Object, e As EventArgs) Handles MenúAnalizarToolStripMenuItem.Click
        Dim AnalizadorLexico As New Lexico()
        Dim ListaTokensDelLexico As List(Of Token) = AnalizadorLexico.Analizar(RTB.Text)
        Dim Lista As New List(Of Token)
        For Each item In ListaTokensDelLexico
            If item.DevolverTipo = Valor.Malo Then
            Else
                Lista.Add(item)
            End If
        Next


        Dim Ag As New Token(Valor.Ultimo, " ", -1, -1)
        Lista.Add(Ag)

        Dim AnalizadorSintactico As New Sintactico()
        Dim ListaTokensDelSintactico As List(Of Token) = AnalizadorSintactico.parsear(Lista)

        Dim i = 0

        Dim ListaColorear As New List(Of Token)

        For Each item In ListaTokensDelSintactico



            If item.DevolverEstadoSintactico Then

            Else
                i = 0
                While True
                    Dim Tok = ListaTokensDelLexico.Item(i)

                    If item.DevolverTodo(2).Equals(Tok.DevolverTodo(2)) And item.DevolverTodo(3).Equals(Tok.DevolverTodo(3)) And item.DevolverTipo = Valor.Malo Then
                        Dim Tik As New Token(Valor.Malo, Tok.DevolverTodo(1), Tok.DevolverTodo(2), Tok.DevolverTodo(3))
                        If item.DevolverEstadoSintactico Then
                        Else
                            Tik.ESintac()
                            Tik.AgregarDescripcion(item.DevolverDescripcion)
                        End If
                        ListaTokensDelLexico(i) = Tik
                        Console.WriteLine(ListaTokensDelLexico(i).getTipoEnString & "  oooooo  " & ListaTokensDelLexico(i).DevolverTodo(1))
                        Exit While
                    Else
                        If Tok.DevolverTipo = Valor.Ultimo Then
                            Exit While
                        Else
                            i = i + 1
                        End If

                    End If


                End While

            End If


        Next

        salida = ListaTokensDelLexico

        RTB.Clear()
        Colorear(ListaTokensDelLexico)

    End Sub

    Private Sub Button2_Click(sender As Object, e As EventArgs) Handles Button2.Click

    End Sub

    Private Sub GraficaToolStripMenuItem_Click(sender As Object, e As EventArgs) Handles GraficaToolStripMenuItem.Click
        Dim AnalizadorLexico As New Lexico()
        Dim ListaTokensDelLexico As List(Of Token) = AnalizadorLexico.Analizar(RTB.Text)
        HacerGraphviz(salida)
    End Sub

    Private Sub AyudaToolStripMenuItem_Click(sender As Object, e As EventArgs) Handles AyudaToolStripMenuItem.Click

    End Sub

    Private Sub SToolStripMenuItem_Click(sender As Object, e As EventArgs) Handles SToolStripMenuItem.Click
        MsgBox("Andhy Lizandro Solís Osorio 201700886")
    End Sub


    Private Sub GuardarComoToolStripMenuItem_Click(sender As Object, e As EventArgs) Handles GuardarComoToolStripMenuItem.Click
        Try
            Dim RutaArchivp = Application.StartupPath
            'Dim fileDefault = ""

            Dim abrir As New SaveFileDialog
            abrir.InitialDirectory = RutaArchivp
            abrir.Filter = "Todos|*.lfp"


            Console.WriteLine()

            Dim Ruta = RTB.Text




            If abrir.ShowDialog = DialogResult.OK Then

                Dim fileWritter = My.Computer.FileSystem.OpenTextFileWriter(abrir.FileName, False)
                fileWritter.Write(Ruta)
                ' Console.WriteLine(Ruta)
                fileWritter.Close()
                MessageBox.Show("Archivo Guardado", "Guardar Como", MessageBoxButtons.OK, MessageBoxIcon.Asterisk)
            End If

        Catch exc As Exception
            MessageBox.Show("El archivo no se puede abrir, o está siendo utilizado por otra aplicación", "My Application",
      MessageBoxButtons.OK, MessageBoxIcon.Asterisk)
        Finally
        End Try
    End Sub
    Private Archivo As String = ""
    Private Sub AbrirToolStripMenuItem_Click(sender As Object, e As EventArgs) Handles AbrirToolStripMenuItem.Click
        Try
            ' Dim rutaDefault = Environment.GetFolderPath(Environment.SpecialFolder.Desktop)
            Dim Ruta = Application.StartupPath
            'Dim fileDefault = ""

            Dim abrir As New OpenFileDialog
            abrir.InitialDirectory = Ruta
            abrir.Filter = "Todos|*.lfp"

            ' If File.Exists(Path.Combine(rutaDefault, fileDefault)) Then
            'Nombre de archivo que se cargará por defecto, si existe en la ruta
            '  abrir.FileName = fileDefault
            '   End If
            Console.WriteLine()
            If abrir.ShowDialog = DialogResult.OK Then
                Dim utf8 As New UTF8Encoding()


                Dim fileReader = My.Computer.FileSystem.OpenTextFileReader(abrir.FileName, System.Text.Encoding.Default)
                '   Dim OP = fileReader.ReadToEnd
                Dim T = fileReader.ReadToEnd

                ' Dim F = My.Computer.FileSystem.WriteAllText(fileWritter, "5", False)


                ' My.Computer.FileSystem.OpenTextFileReader("Prueba.txt")
                
                fileReader.Close()
                Archivo = abrir.FileName.ToString
                RTB.Text = T

                MessageBox.Show("Datos CargadosExitosamente", "Abrir", MessageBoxButtons.OK, MessageBoxIcon.Asterisk)
            End If


        Catch exc As Exception
            MessageBox.Show("El archivo no se puede abrir, o está siendo utilizado por otra aplicación", "My Application",
  MessageBoxButtons.OK, MessageBoxIcon.Asterisk)
        Finally
        End Try
        TextBox3.Text = Archivo
        TextBox4.Text = Archivo
    End Sub

    Private Sub GuardarToolStripMenuItem_Click(sender As Object, e As EventArgs) Handles GuardarToolStripMenuItem.Click
        Try
            Dim fileWritter = My.Computer.FileSystem.OpenTextFileWriter(Archivo, False)
            fileWritter.Write(RTB.Text)
            fileWritter.Close()

            MessageBox.Show("Archivo Guardado", "Guardar", MessageBoxButtons.OK, MessageBoxIcon.Asterisk)



        Catch exc As Exception
            MessageBox.Show("El archivo no se puede abrir, o está siendo utilizado por otra aplicación", "My Application",
  MessageBoxButtons.OK, MessageBoxIcon.Asterisk)
        Finally
        End Try
    End Sub

    Private Sub SalirToolStripMenuItem_Click(sender As Object, e As EventArgs) Handles SalirToolStripMenuItem.Click
        End
    End Sub

    Private Sub DToolStripMenuItem_Click(sender As Object, e As EventArgs) Handles DToolStripMenuItem.Click
        Process.Start("ManualUsuario.pdf")
    End Sub

    Private Sub ManualTecnicoToolStripMenuItem_Click(sender As Object, e As EventArgs) Handles ManualTecnicoToolStripMenuItem.Click
        Process.Start("ManualTecnico.pdf")
    End Sub
End Class
