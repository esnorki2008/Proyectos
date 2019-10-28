
Public Class Form1

    Private Dt = New Operaciones()
    'Private Dt = New Operaciones()


    Private Sub TabPage2_Click(sender As Object, e As EventArgs) Handles TabPage2.Click

    End Sub

    Private Sub Button4_Click(sender As Object, e As EventArgs) Handles Button4.Click

        Dim Arc = New Arcaico()
        Arc.Guardar(TextBox1.Text, "Data.lfp")


        MessageBox.Show("Archivo Guardado", "Guardar", MessageBoxButtons.OK, MessageBoxIcon.Asterisk)


    End Sub

    Private Sub TextBox1_TextChanged(sender As Object, e As EventArgs) Handles TextBox1.TextChanged

    End Sub

    Private Sub Button3_Click(sender As Object, e As EventArgs) Handles Button3.Click
        Dim Arc = New Arcaico()
        Arc.GuardarComo(TextBox1.Text)

    End Sub

    Private Sub Button9_Click(sender As Object, e As EventArgs) Handles Button9.Click
        Dt.DatosLimpios(TextBox1.Text)
        MessageBox.Show("Analizado", "Guardar", MessageBoxButtons.OK, MessageBoxIcon.Asterisk)
    End Sub

    Private Sub Button1_Click(sender As Object, e As EventArgs) Handles Button1.Click
        End

    End Sub

    Private Sub Button2_Click(sender As Object, e As EventArgs) Handles Button2.Click
        Dim Arc = New Arcaico()
        TextBox1.Text = Arc.Abir()
    End Sub

    Private Sub Button6_Click(sender As Object, e As EventArgs) Handles Button6.Click

        Dt.IniciarPagina()
    End Sub

    Private Sub Button7_Click(sender As Object, e As EventArgs) Handles Button7.Click
        Dt.IniciarGrafico()
    End Sub

    Private Sub Button5_Click(sender As Object, e As EventArgs) Handles Button5.Click
        MsgBox("Andhy Lizandro Solís Osorio     201700886  Versión 9999999999.999")
    End Sub

    Private Sub Button8_Click(sender As Object, e As EventArgs) Handles Button8.Click
        Process.Start("ManualUsuario.PDF")
    End Sub

    Private Sub Button10_Click(sender As Object, e As EventArgs) Handles Button10.Click
        Process.Start("ManualTecnico.PDF")
    End Sub

    Private Sub Timer1_Tick(sender As Object, e As EventArgs) Handles Timer1.Tick
        Process.Start("Grafico.png")
        Timer1.Stop()
    End Sub
End Class
