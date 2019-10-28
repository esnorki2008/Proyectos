Public Class Colores
    Private Inicio As Integer
    Private Fin As Integer
    Private Co As Color
    Private Texto As String

    Public Sub New(ByVal Co As Color, ByVal Inicio As Integer, Fin As Integer, Texto As String)
        Me.Inicio = Inicio
        Me.Fin = Fin
        Me.Co = Co
        Me.Texto = Texto
    End Sub
    Public Function Retorno()
        Dim A = {Inicio, Fin, Co, Texto}
        Return A
    End Function
End Class
