Public Class Arcaico
    Public Sub Guardar(Ruta As String, Archivo As String)

        Try
            Dim fileWritter = My.Computer.FileSystem.OpenTextFileWriter(Archivo, False)
            fileWritter.Write(Ruta)
            fileWritter.Close()





        Catch exc As Exception
            MessageBox.Show("El archivo no se puede abrir, o está siendo utilizado por otra aplicación", "My Application",
  MessageBoxButtons.OK, MessageBoxIcon.Asterisk)
        Finally
        End Try

    End Sub

    Public Sub GuardarComo(Ruta As String)
        Try
            Dim RutaArchivo = Application.StartupPath
            'Dim fileDefault = ""

            Dim abrir As New SaveFileDialog
            abrir.InitialDirectory = RutaArchivo
            abrir.Filter = "Todos|*.lfp"

            ' If File.Exists(Path.Combine(rutaDefault, fileDefault)) Then
            'Nombre de archivo que se cargará por defecto, si existe en la ruta
            '  abrir.FileName = fileDefault
            '   End If
            Console.WriteLine()






            If abrir.ShowDialog = DialogResult.OK Then

                Dim fileWritter = My.Computer.FileSystem.OpenTextFileWriter(abrir.FileName, False)
                fileWritter.Write(Ruta)
                ' Console.WriteLine(Ruta)
                fileWritter.Close()
                MessageBox.Show("Archivo Guardado", "Guardar Como", MessageBoxButtons.OK, MessageBoxIcon.Asterisk)
            End If

        Catch exc As Exception
            MessageBox.Show("El archivo no se puede guardar, o no tiene los permisos necesarios", "My Application",
      MessageBoxButtons.OK, MessageBoxIcon.Asterisk)
        Finally
        End Try
    End Sub

    Public Function Abir()
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




                Dim fileReader = My.Computer.FileSystem.OpenTextFileReader(abrir.FileName)


                ' Dim F = My.Computer.FileSystem.WriteAllText(fileWritter, "5", False)


                ' My.Computer.FileSystem.OpenTextFileReader("Prueba.txt")
                Dim T = fileReader.ReadToEnd
                fileReader.Close()
                Dim Archivo = abrir.FileName.ToString
                Return T
                MessageBox.Show("Datos CargadosExitosamente", "Abrir", MessageBoxButtons.OK, MessageBoxIcon.Asterisk)

            End If


        Catch exc As Exception
            MessageBox.Show("El archivo no se puede abrir, o está siendo utilizado por otra aplicación", "My Application",
  MessageBoxButtons.OK, MessageBoxIcon.Asterisk)
        Finally
        End Try
        Return ""
    End Function
End Class
