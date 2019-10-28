Public Class Token
    Enum Valor
        PalabraReservada
        CorcheteA
        CorcheteC
        Numero
        Igual
        Identificador       
        Guion
        ParentesisA
        ParentesisC
        LlaveA
        LlaveC
        Mas
        Menos
        Numeral
        DoblePunto
        Punto
        PuntoComa
        Coma
        Comilla
        Comentario
        Malo
        Ultimo
    End Enum
    Private Lexema As String
    Private PosicionX As Integer
    Private PosicionY As Integer
    Private EstadoLexico As Boolean = True
    Private EstadoSintactico As Boolean = True
    Private Tipo As Valor
    Private DescripcionE As String = "Es Un Símbolo No Reconocido"
    Public Sub ESintac()
        EstadoSintactico = False
    End Sub
    Public Sub AgregarDescripcion(DE As String)
        DescripcionE = DE
    End Sub
    Public Function DevolverDescripcion()
        Return DescripcionE
    End Function
    Public Sub ELex()
        EstadoLexico = False
    End Sub
    Public Function DevolverEstadoSintactico()
        Return EstadoSintactico
    End Function
    Public Function CambiarTipo()
        Return Tipo = Valor.Malo
    End Function
    Public Sub New(ByVal tipo As Valor, ByVal Lexema As String, PosicionX As Integer, PosicionY As Integer)
        Me.Tipo = tipo
        Me.Lexema = Lexema
        Me.PosicionX = PosicionX
        Me.PosicionY = PosicionY
    End Sub
    Public Function DevolverTodo() As String()
        Dim Vector = {getTipoEnString(), Lexema, Convert.ToString(PosicionX), Convert.ToString(PosicionY)}
        Return Vector
    End Function
    Public Function DevolverTipo() As Valor
        Dim Vector = Tipo
        Return Vector
    End Function
    Public Function getTipoEnString() As String
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
End Class
