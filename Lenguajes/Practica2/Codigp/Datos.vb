Public Class Datos
    Private Vecto1(0) As Object
    Private Vecto2(0) As Object
    Private Vecto3(0) As Object
    Private Vecto4(0) As Object
    Private Vecto5(0) As Object
    Public Sub PonerVecto1(te As Object)
        Vecto1(Vecto1.Length - 1) = te
        ReDim Preserve Vecto1(Vecto1.Length)
    End Sub
    Public Sub PonerVecto2(te As Object)
        Vecto2(Vecto2.Length - 1) = te
        ReDim Preserve Vecto2(Vecto2.Length)
    End Sub
    Public Sub PonerVecto3(te As Object)
        Vecto3(Vecto3.Length - 1) = te
        ReDim Preserve Vecto3(Vecto3.Length)
    End Sub
    Public Sub PonerVecto4(te As Object)
        Vecto4(Vecto4.Length - 1) = te
        ReDim Preserve Vecto4(Vecto4.Length)
    End Sub
    Public Sub PonerVecto5(te As Object)
        Vecto5(Vecto5.Length - 1) = te
        ReDim Preserve Vecto5(Vecto5.Length)
    End Sub



    Public Function DarVecto1()
        Return Vecto1

    End Function
    Public Function DarVecto2()
        Return Vecto2

    End Function
    Public Function DarVecto3()
        Return Vecto3

    End Function
    Public Function DarVecto4()
        Return Vecto4

    End Function
    Public Function DarVecto5()
        Return Vecto5

    End Function

    Public Sub Limpiar()
        ReDim Vecto1(0)
        ReDim Vecto2(0)
        ReDim Vecto3(0)
        ReDim Vecto4(0)
        ReDim Vecto5(0)

    End Sub
End Class
