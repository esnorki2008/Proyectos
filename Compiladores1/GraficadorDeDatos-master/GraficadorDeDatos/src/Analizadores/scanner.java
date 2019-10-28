/* The following code was generated by JFlex 1.7.0 */

package Analizadores; // Paquete donde estará nuestro scanner
import java_cup.runtime.Symbol; 
import Nodos.*;
import java.util.LinkedList;

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.7.0
 * from the specification file <tt>scanner.l</tt>
 */
public class scanner implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\12\1\7\1\32\1\12\1\2\22\0\1\12\1\0\1\6"+
    "\5\0\1\17\1\20\1\3\1\26\1\14\1\27\1\5\1\1\12\4"+
    "\1\16\1\15\1\0\1\24\1\0\1\31\1\0\32\10\1\23\1\0"+
    "\1\25\1\30\1\11\1\0\32\10\1\21\1\0\1\22\7\0\1\32"+
    "\53\0\1\10\15\0\1\13\3\0\1\10\u1f54\0\1\10\17\0\1\32"+
    "\1\32\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\udfe6\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\1\4\1\5\1\1\1\6"+
    "\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16"+
    "\1\17\1\20\1\21\1\22\1\23\1\24\1\25\2\0"+
    "\1\26\1\0\1\27\1\30\4\0\1\30";

  private static int [] zzUnpackAction() {
    int [] result = new int[34];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\33\0\66\0\33\0\33\0\121\0\154\0\207"+
    "\0\33\0\33\0\33\0\33\0\33\0\33\0\33\0\33"+
    "\0\33\0\33\0\33\0\33\0\33\0\33\0\33\0\242"+
    "\0\275\0\330\0\154\0\33\0\242\0\363\0\u010e\0\u0129"+
    "\0\u0144\0\33";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[34];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\5\1\6\1\2\1\7\1\4"+
    "\1\10\1\2\1\4\1\11\1\12\1\13\1\14\1\15"+
    "\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25"+
    "\1\26\1\27\35\0\1\30\1\0\1\31\33\0\1\6"+
    "\1\32\25\0\6\33\2\34\23\33\4\0\1\10\3\0"+
    "\2\10\21\0\2\35\1\0\4\35\1\0\23\35\1\36"+
    "\1\37\31\36\4\0\1\32\26\0\3\36\1\40\27\36"+
    "\1\0\1\37\1\0\1\41\27\0\1\36\1\42\1\36"+
    "\1\40\27\36\1\0\1\42\31\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[351];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\1\1\2\11\3\1\17\11\2\0\1\1"+
    "\1\0\1\11\1\1\4\0\1\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[34];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true iff the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true iff the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */
    /*Area de declaración de codigo java. 
    *Funciones y variables necesarias
    *
    */
    String nombre;
	public String Errores="";
    public void imprimir(String cadena)
    {
    	System.out.println(cadena);
    }
	

   public LinkedList<GraficaBarra> GB = new LinkedList<GraficaBarra>();
   public LinkedList<GraficaLineas> GL = new LinkedList<GraficaLineas>();
   public LinkedList<NodoGaleria> NG = new LinkedList<NodoGaleria>();
   public LinkedList<VarGlobales> VG = new LinkedList<VarGlobales>();

    GraficaLineas ObjetoLineas = new GraficaLineas();
    GraficaBarra ObjetoBarras = new GraficaBarra();
	NodoGaleria ObjetoGaleria = new NodoGaleria();
	VarGlobales ObjetoGlobal = new VarGlobales();
	
    boolean Embargo=false;
    boolean Pnt=false;
    boolean Barras = false;
    boolean Lineas = false;
    boolean XYLineas = false;
	
	boolean Galo=false;
	boolean PuXY=false;
	boolean EjX = false;
	boolean EjY = false;
	
    String Nombre="";
	String Color=""; 
	String Grosor="";
	String Puntos="";
    
	String PuntosXY="";
	String EjeX="";
	String EjeY="";
	String Galer="";
	
	
    int Estado = -1;
	

	
	private void VarGlo(String S) {
        switch (Estado) {
            case 1:
                ObjetoBarras.Id=S;
                break;
				}
	}
	
	
      private void AsignacionesBarras(String S) {
        switch (Estado) {
            case 1:
                ObjetoBarras.Id=S;
                break;
             case 16:
               
                break;
             case 2:
                 ObjetoBarras.Titulo=S;
                break;
             case 14:               
				
                break;
             case 4:
                 ObjetoBarras.TituloX=S;
                break;
             case 15:
                
                break;
             case 5:
                  ObjetoBarras.TituloY=S;
                break;     

        }
		Estado=-1;
    }

    private void AsignacionesLineas(String S) {
        switch (Estado) {
            case 1:
                ObjetoLineas.ID=S;
                break;
            case 3:
                ObjetoLineas.Titulo=S;
                break;
            case 4:
                ObjetoLineas.TituloX=S;
                break;
            case 5:
                ObjetoLineas.TituloY=S;
                break;

        }
        Estado=-1;
    }
    
        private void XYLin(String S) {
            switch (Estado) {
            case 6:
                Nombre=S;
                break;
            case 7:
                Color=S;
                break;
            case 8:
                Grosor=S;
                break;
            case 9:
                Puntos=S;
                break;

        }
        Estado=-1;
        }

	private void LlaveC(){
	if(Galo){
	 Galo=false;
	}
	if(Embargo){
	//	VG.add(ObjetoGlobal);
	//	ObjetoGlobal=new VarGlobales();
		Embargo=false;
	}
	
		if(XYLineas==false && PuXY==false){

	  if (Barras) {
               ObjetoBarras.PuntosXY=PuntosXY;
               ObjetoBarras.EjeX=EjeX;
               ObjetoBarras.EjeY=EjeY;
			   EjeX="";
			   EjeY="";
			   PuntosXY="";
            GB.add(ObjetoBarras);
        }
        if (Lineas) {
			
		        //  System.out.println(ObjetoLineas.ID+" "+ObjetoLineas.Titulo+" "+ObjetoLineas.TituloX+" "+ObjetoLineas.TituloY);

            GL.add(ObjetoLineas);
        }
            Barras=false;
			Lineas=false;
            Estado=-1;
            ObjetoLineas = new GraficaLineas();
            ObjetoBarras = new GraficaBarra();
        }
       }
	   
	   
	   private void PR(){
		if(XYLineas){
		//System.out.println(Nombre +" "+Color+" "+ Grosor+" "+ Puntos);
                ObjetoLineas.CrearXYLine(Nombre, Color, Grosor, Puntos);
                Nombre="";
                Color="";
                Grosor="";
                Puntos="";
        }
                XYLineas=false;
	   }
	
	     private int Retornos(String S) {
        
               
        
        

        switch (S) {
            case "string":
                Estado = 0;
                return sym.string;

            case "id":
                Estado = 1;
                return sym.idt;
            case "int":
                Estado = 2;
                return sym.ink;
            case "titulo":
                Estado = 3;
                return sym.titulo;
            case "titulox":
                Estado = 4;
                return sym.titulox;
            case "tituloy":
                Estado = 5;
                return sym.tituloy;
            case "nombre":
                Estado = 6;
                return sym.nombre;
            case "color":
                Estado = 7;
                return sym.color;
            case "grosor":
                Estado = 8;
                return sym.grosor;
            case "puntos":
                Estado = 9;
                return sym.puntos;
            case "definirglobales":
				Embargo=true;
                Estado = 10;
                return sym.globales;
            case "definirxyline":
				
                XYLineas=true;
                Estado = 11;
                return sym.definirxyline;
            case "graficabarras":
                Estado = 12;
                Barras=true;

                return sym.graficabarras;
            case "graficalineas":
                Estado = 13;
                Lineas=true;
				

                return sym.graficalineas;
            case "ejex":
				EjX=true;
                Estado = 14;
                return sym.ejex;
            case "ejey":
				EjY=true;
                Estado = 15;
                return sym.ejey;
            case "puntosxy":
				PuXY=true;
                Estado = 16;
                return sym.puntosxy;
            case "galeria":
				Galo=true;
                Estado = 17;
                return sym.galeria;
            default:
              //  System.out.println(S+"  DEFAULT");
                    if (Barras) {
                        AsignacionesBarras(S);
                    }
                    if (Lineas) {
					
						if(Pnt){Puntos=Puntos+yytext();}

						//System.out.println(S+"  DEB");
                        AsignacionesLineas(S);
                    }
                    if(XYLineas){
                        XYLin(S);
                    }
					if(Barras){
						 if(EjX){EjeX=EjeX+yytext();}
						 if(EjY){EjeY=EjeY+yytext();}
					}
					
                break;

        }
		
		
		
		if(Embargo){ObjetoGlobal.Nombre=S;}
		if(Galo){
	 Galer=Galer+S;
	}
        return sym.id;
    }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public scanner(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x110000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 136) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      /* If numRead == requested, we might have requested to few chars to
         encode a full Unicode character. We assume that a Reader would
         otherwise never return half characters. */
      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      yychar+= zzMarkedPosL-zzStartRead;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':  // fall through
        case '\u000C':  // fall through
        case '\u0085':  // fall through
        case '\u2028':  // fall through
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
          { return new java_cup.runtime.Symbol(sym.EOF); }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { Errores="Error lexico: no se ha reconocido el caracter encontrado  "+yytext()+ " Linea:"+(yyline+1)+" Columna:"+(yycolumn+1);

        System.err.println("Error lexico: "+yytext()+ " Linea:"+(yyline+1)+" Columna:"+(yycolumn+1));
            } 
            // fall through
          case 25: break;
          case 2: 
            { if(Pnt){Puntos=Puntos+yytext();}
if(Barras){	 if(EjY){EjeY=EjeY+yytext();}		}
if(Embargo){ObjetoGlobal.Tipo="Num"; ObjetoGlobal.ValorString=ObjetoGlobal.ValorString+yytext();}
return new Symbol(sym.div,yycolumn,yyline,yytext());
            } 
            // fall through
          case 26: break;
          case 3: 
            { /*Ignorar*/
            } 
            // fall through
          case 27: break;
          case 4: 
            { if(Pnt){Puntos=Puntos+yytext();}
if(Barras){	 if(EjY){EjeY=EjeY+yytext();}		}
if(Embargo){ObjetoGlobal.Tipo="Num"; ObjetoGlobal.ValorString=ObjetoGlobal.ValorString+yytext();}
return new Symbol(sym.multi,yycolumn,yyline,yytext());
            } 
            // fall through
          case 28: break;
          case 5: 
            { if(Embargo){ObjetoGlobal.Tipo="Num"; ObjetoGlobal.ValorString=ObjetoGlobal.ValorString+yytext();}



if(Barras){
 if(PuXY){PuntosXY=PuntosXY+yytext();}
 if(EjY){EjeY=EjeY+yytext();}
}
if(Pnt){Puntos=Puntos+yytext();}else{Grosor=yytext();}			return new Symbol(sym.entero,yycolumn,yyline,yytext());
            } 
            // fall through
          case 29: break;
          case 6: 
            { return new Symbol(Retornos(yytext()),yycolumn,yyline,yytext());
            } 
            // fall through
          case 30: break;
          case 7: 
            { XYLineas=true; return new Symbol(sym.prea,yycolumn,yyline,yytext());
            } 
            // fall through
          case 31: break;
          case 8: 
            { if(Galo){
	 Galer=Galer+yytext();
	}


if(Barras){
 if(PuXY){PuntosXY=PuntosXY+yytext();}
 if(EjX){EjeX=EjeX+yytext();}
 if(EjY){EjeY=EjeY+yytext();}
}

if(XYLineas){Puntos=Puntos+yytext();} return new Symbol(sym.coma,yycolumn,yyline,yytext());
            } 
            // fall through
          case 32: break;
          case 9: 
            { if(Embargo){
		VG.add(ObjetoGlobal);
		ObjetoGlobal=new VarGlobales();
		
	}

if(Galo){
 ObjetoGaleria.Galeria=Galer;
	 NG.add(ObjetoGaleria);
	 ObjetoGaleria = new NodoGaleria();
	 Galer="";
	}

return new Symbol(sym.puntocoma,yycolumn,yyline,yytext());
            } 
            // fall through
          case 33: break;
          case 10: 
            { return new Symbol(sym.dospuntos,yycolumn,yyline,yytext());
            } 
            // fall through
          case 34: break;
          case 11: 
            { return new Symbol(sym.para,yycolumn,yyline,yytext());
            } 
            // fall through
          case 35: break;
          case 12: 
            { return new Symbol(sym.parc,yycolumn,yyline,yytext());
            } 
            // fall through
          case 36: break;
          case 13: 
            { if(Barras){
 if(PuXY){PuntosXY=PuntosXY+yytext();}

}
if(XYLineas){Puntos=Puntos+yytext();}	return new Symbol(sym.llaa,yycolumn,yyline,yytext());
            } 
            // fall through
          case 37: break;
          case 14: 
            { if(Barras){
 if(PuXY){PuntosXY=PuntosXY+yytext();}

}
if(XYLineas){Puntos=Puntos+yytext();}	LlaveC();	return new Symbol(sym.llac,yycolumn,yyline,yytext());
            } 
            // fall through
          case 38: break;
          case 15: 
            { if(Barras){
 if(PuXY){PuntosXY=PuntosXY+yytext();}
 if(EjX){EjeX=EjeX+yytext();}
 if(EjY){EjeY=EjeY+yytext();}
}
if(XYLineas){Pnt=true; Puntos=Puntos+yytext();}		return new Symbol(sym.cora,yycolumn,yyline,yytext());
            } 
            // fall through
          case 39: break;
          case 16: 
            { return new Symbol(sym.igual,yycolumn,yyline,yytext());
            } 
            // fall through
          case 40: break;
          case 17: 
            { if(Barras){
if(PuXY){PuntosXY=PuntosXY+yytext();}
 if(EjX){EjeX=EjeX+yytext();}
 if(EjY){EjeY=EjeY+yytext();}

PuXY=false;
EjX=false;
EjY=false;

 
 
}
if(XYLineas){
Pnt=false; Puntos=Puntos+yytext();} 		return new Symbol(sym.corc,yycolumn,yyline,yytext());
            } 
            // fall through
          case 41: break;
          case 18: 
            { if(Pnt){Puntos=Puntos+yytext();}
if(Barras){	 if(EjY){EjeY=EjeY+yytext();}		}
if(Embargo){ObjetoGlobal.Tipo="Num"; ObjetoGlobal.ValorString=ObjetoGlobal.ValorString+yytext();}
return new Symbol(sym.suma,yycolumn,yyline,yytext());
            } 
            // fall through
          case 42: break;
          case 19: 
            { if(Pnt){Puntos=Puntos+yytext();}
if(Barras){	 if(EjY){EjeY=EjeY+yytext();}		}
if(Embargo){ObjetoGlobal.Tipo="Num"; ObjetoGlobal.ValorString=ObjetoGlobal.ValorString+yytext();}
return new Symbol(sym.resta,yycolumn,yyline,yytext());
            } 
            // fall through
          case 43: break;
          case 20: 
            { return new Symbol(sym.potencia,yycolumn,yyline,yytext());
            } 
            // fall through
          case 44: break;
          case 21: 
            { PR(); return new Symbol(sym.prec,yycolumn,yyline,yytext());
            } 
            // fall through
          case 45: break;
          case 22: 
            { return new Symbol(sym.decimal,yycolumn,yyline,yytext());
            } 
            // fall through
          case 46: break;
          case 23: 
            { if(Embargo){ObjetoGlobal.Tipo="Cad"; ObjetoGlobal.ValorString=yytext();}

					String S=yytext();
					//System.out.println(S+"  "+Lineas+"	"+XYLineas);
					
					if (Barras) {
                        AsignacionesBarras(S);
                    }
                    if (Lineas && XYLineas==false) {
						//System.out.println(S+ "  Lins");
                        AsignacionesLineas(S);
                    }
                    if(XYLineas){
						//System.out.println(S+ "  XYYYLins");
                        XYLin(S);
                    }
					if(EjX){
						EjeX=EjeX+yytext();
                    }
	
	if(Galo){
	 Galer=Galer+yytext();
	}

return new Symbol(sym.cadena,yycolumn,yyline,yytext());
            } 
            // fall through
          case 47: break;
          case 24: 
            { /*Se ignora.*/
            } 
            // fall through
          case 48: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
