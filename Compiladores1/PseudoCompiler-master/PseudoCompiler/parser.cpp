/* A Bison parser, made by GNU Bison 2.4.2.  */

/* Skeleton implementation for Bison's Yacc-like parsers in C
   
      Copyright (C) 1984, 1989-1990, 2000-2006, 2009-2010 Free Software
   Foundation, Inc.
   
   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.
   
   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.
   
   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.
   
   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

/* C LALR(1) parser skeleton written by Richard Stallman, by
   simplifying the original so-called "semantic" parser.  */

/* All symbols defined below should begin with yy or YY, to avoid
   infringing on user name space.  This should be done even for local
   variables, as they might otherwise be expanded by user macros.
   There are some unavoidable exceptions within include files to
   define necessary library symbols; they are noted "INFRINGES ON
   USER NAME SPACE" below.  */

/* Identify Bison output.  */
#define YYBISON 1

/* Bison version.  */
#define YYBISON_VERSION "2.4.2"

/* Skeleton name.  */
#define YYSKELETON_NAME "yacc.c"

/* Pure parsers.  */
#define YYPURE 0

/* Push parsers.  */
#define YYPUSH 0

/* Pull parsers.  */
#define YYPULL 1

/* Using locations.  */
#define YYLSP_NEEDED 1



/* Copy the first part of user declarations.  */

/* Line 189 of yacc.c  */
#line 1 "parser.yy"

#include "scanner.h"//se importa el header del analisis sintactico
#include "NodoAST.h"
#include "qdebug.h"
#include <iostream>
#include <stdio.h>
#include <string.h>
#include <graficador.h>

extern int yylineno; //linea actual donde se encuentra el parser (analisis lexico) lo maneja BISON
extern int columna; //columna actual donde se encuentra el parser (analisis lexico) lo maneja BISON
extern char *yytext; //lexema actual donde esta el parser (analisis lexico) lo maneja BISON
extern NodoAST *raiz; // Raiz del arbol

extern NodoAST *VRaiz=nullptr;
extern Graficador *Sali= new Graficador();
int yyerror(const char* mens)
{

    std::cout << mens <<" "<<yytext<< std::endl;
    return 0;
}


/* Line 189 of yacc.c  */
#line 97 "parser.cpp"

/* Enabling traces.  */
#ifndef YYDEBUG
# define YYDEBUG 0
#endif

/* Enabling verbose error messages.  */
#ifdef YYERROR_VERBOSE
# undef YYERROR_VERBOSE
# define YYERROR_VERBOSE 1
#else
# define YYERROR_VERBOSE 1
#endif

/* Enabling the token table.  */
#ifndef YYTOKEN_TABLE
# define YYTOKEN_TABLE 0
#endif


/* Tokens.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
   /* Put the tokens into the symbol table, so that GDB and other debuggers
      know about them.  */
   enum yytokentype {
     entero = 258,
     decimal = 259,
     caracter = 260,
     booleano = 261,
     cadena = 262,
     id = 263,
     suma = 264,
     menos = 265,
     multi = 266,
     division = 267,
     potencia = 268,
     puntocoma = 269,
     si = 270,
     sino = 271,
     igual = 272,
     igualdoble = 273,
     diferente = 274,
     coma = 275,
     menor = 276,
     menorigual = 277,
     mayor = 278,
     mayorigual = 279,
     ordoble = 280,
     anddoble = 281,
     admiracion = 282,
     cora = 283,
     llaa = 284,
     llac = 285,
     corc = 286,
     imprimir = 287,
     show = 288,
     sumadoble = 289,
     restadoble = 290,
     para = 291,
     parc = 292,
     parafor = 293,
     repetir = 294,
     arreglo = 295,
     comenta = 296,
     coment = 297
   };
#endif



#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef union YYSTYPE
{

/* Line 214 of yacc.c  */
#line 30 "parser.yy"

//se especifican los tipo de valores para los no terminales y lo terminales
char TEXT [256];
class NodoAST *Nodo;



/* Line 214 of yacc.c  */
#line 183 "parser.cpp"
} YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
#endif

#if ! defined YYLTYPE && ! defined YYLTYPE_IS_DECLARED
typedef struct YYLTYPE
{
  int first_line;
  int first_column;
  int last_line;
  int last_column;
} YYLTYPE;
# define yyltype YYLTYPE /* obsolescent; will be withdrawn */
# define YYLTYPE_IS_DECLARED 1
# define YYLTYPE_IS_TRIVIAL 1
#endif


/* Copy the second part of user declarations.  */


/* Line 264 of yacc.c  */
#line 208 "parser.cpp"

#ifdef short
# undef short
#endif

#ifdef YYTYPE_UINT8
typedef YYTYPE_UINT8 yytype_uint8;
#else
typedef unsigned char yytype_uint8;
#endif

#ifdef YYTYPE_INT8
typedef YYTYPE_INT8 yytype_int8;
#elif (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
typedef signed char yytype_int8;
#else
typedef short int yytype_int8;
#endif

#ifdef YYTYPE_UINT16
typedef YYTYPE_UINT16 yytype_uint16;
#else
typedef unsigned short int yytype_uint16;
#endif

#ifdef YYTYPE_INT16
typedef YYTYPE_INT16 yytype_int16;
#else
typedef short int yytype_int16;
#endif

#ifndef YYSIZE_T
# ifdef __SIZE_TYPE__
#  define YYSIZE_T __SIZE_TYPE__
# elif defined size_t
#  define YYSIZE_T size_t
# elif ! defined YYSIZE_T && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
#  include <stddef.h> /* INFRINGES ON USER NAME SPACE */
#  define YYSIZE_T size_t
# else
#  define YYSIZE_T unsigned int
# endif
#endif

#define YYSIZE_MAXIMUM ((YYSIZE_T) -1)

#ifndef YY_
# if defined YYENABLE_NLS && YYENABLE_NLS
#  if ENABLE_NLS
#   include <libintl.h> /* INFRINGES ON USER NAME SPACE */
#   define YY_(msgid) dgettext ("bison-runtime", msgid)
#  endif
# endif
# ifndef YY_
#  define YY_(msgid) msgid
# endif
#endif

/* Suppress unused-variable warnings by "using" E.  */
#if ! defined lint || defined __GNUC__
# define YYUSE(e) ((void) (e))
#else
# define YYUSE(e) /* empty */
#endif

/* Identity function, used to suppress warnings about constant conditions.  */
#ifndef lint
# define YYID(n) (n)
#else
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static int
YYID (int yyi)
#else
static int
YYID (yyi)
    int yyi;
#endif
{
  return yyi;
}
#endif

#if ! defined yyoverflow || YYERROR_VERBOSE

/* The parser invokes alloca or malloc; define the necessary symbols.  */

# ifdef YYSTACK_USE_ALLOCA
#  if YYSTACK_USE_ALLOCA
#   ifdef __GNUC__
#    define YYSTACK_ALLOC __builtin_alloca
#   elif defined __BUILTIN_VA_ARG_INCR
#    include <alloca.h> /* INFRINGES ON USER NAME SPACE */
#   elif defined _AIX
#    define YYSTACK_ALLOC __alloca
#   elif defined _MSC_VER
#    include <malloc.h> /* INFRINGES ON USER NAME SPACE */
#    define alloca _alloca
#   else
#    define YYSTACK_ALLOC alloca
#    if ! defined _ALLOCA_H && ! defined _STDLIB_H && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
#     include <stdlib.h> /* INFRINGES ON USER NAME SPACE */
#     ifndef _STDLIB_H
#      define _STDLIB_H 1
#     endif
#    endif
#   endif
#  endif
# endif

# ifdef YYSTACK_ALLOC
   /* Pacify GCC's `empty if-body' warning.  */
#  define YYSTACK_FREE(Ptr) do { /* empty */; } while (YYID (0))
#  ifndef YYSTACK_ALLOC_MAXIMUM
    /* The OS might guarantee only one guard page at the bottom of the stack,
       and a page size can be as small as 4096 bytes.  So we cannot safely
       invoke alloca (N) if N exceeds 4096.  Use a slightly smaller number
       to allow for a few compiler-allocated temporary stack slots.  */
#   define YYSTACK_ALLOC_MAXIMUM 4032 /* reasonable circa 2006 */
#  endif
# else
#  define YYSTACK_ALLOC YYMALLOC
#  define YYSTACK_FREE YYFREE
#  ifndef YYSTACK_ALLOC_MAXIMUM
#   define YYSTACK_ALLOC_MAXIMUM YYSIZE_MAXIMUM
#  endif
#  if (defined __cplusplus && ! defined _STDLIB_H \
       && ! ((defined YYMALLOC || defined malloc) \
	     && (defined YYFREE || defined free)))
#   include <stdlib.h> /* INFRINGES ON USER NAME SPACE */
#   ifndef _STDLIB_H
#    define _STDLIB_H 1
#   endif
#  endif
#  ifndef YYMALLOC
#   define YYMALLOC malloc
#   if ! defined malloc && ! defined _STDLIB_H && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
void *malloc (YYSIZE_T); /* INFRINGES ON USER NAME SPACE */
#   endif
#  endif
#  ifndef YYFREE
#   define YYFREE free
#   if ! defined free && ! defined _STDLIB_H && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
void free (void *); /* INFRINGES ON USER NAME SPACE */
#   endif
#  endif
# endif
#endif /* ! defined yyoverflow || YYERROR_VERBOSE */


#if (! defined yyoverflow \
     && (! defined __cplusplus \
	 || (defined YYLTYPE_IS_TRIVIAL && YYLTYPE_IS_TRIVIAL \
	     && defined YYSTYPE_IS_TRIVIAL && YYSTYPE_IS_TRIVIAL)))

/* A type that is properly aligned for any stack member.  */
union yyalloc
{
  yytype_int16 yyss_alloc;
  YYSTYPE yyvs_alloc;
  YYLTYPE yyls_alloc;
};

/* The size of the maximum gap between one aligned stack and the next.  */
# define YYSTACK_GAP_MAXIMUM (sizeof (union yyalloc) - 1)

/* The size of an array large to enough to hold all stacks, each with
   N elements.  */
# define YYSTACK_BYTES(N) \
     ((N) * (sizeof (yytype_int16) + sizeof (YYSTYPE) + sizeof (YYLTYPE)) \
      + 2 * YYSTACK_GAP_MAXIMUM)

/* Copy COUNT objects from FROM to TO.  The source and destination do
   not overlap.  */
# ifndef YYCOPY
#  if defined __GNUC__ && 1 < __GNUC__
#   define YYCOPY(To, From, Count) \
      __builtin_memcpy (To, From, (Count) * sizeof (*(From)))
#  else
#   define YYCOPY(To, From, Count)		\
      do					\
	{					\
	  YYSIZE_T yyi;				\
	  for (yyi = 0; yyi < (Count); yyi++)	\
	    (To)[yyi] = (From)[yyi];		\
	}					\
      while (YYID (0))
#  endif
# endif

/* Relocate STACK from its old location to the new one.  The
   local variables YYSIZE and YYSTACKSIZE give the old and new number of
   elements in the stack, and YYPTR gives the new location of the
   stack.  Advance YYPTR to a properly aligned location for the next
   stack.  */
# define YYSTACK_RELOCATE(Stack_alloc, Stack)				\
    do									\
      {									\
	YYSIZE_T yynewbytes;						\
	YYCOPY (&yyptr->Stack_alloc, Stack, yysize);			\
	Stack = &yyptr->Stack_alloc;					\
	yynewbytes = yystacksize * sizeof (*Stack) + YYSTACK_GAP_MAXIMUM; \
	yyptr += yynewbytes / sizeof (*yyptr);				\
      }									\
    while (YYID (0))

#endif

/* YYFINAL -- State number of the termination state.  */
#define YYFINAL  34
/* YYLAST -- Last index in YYTABLE.  */
#define YYLAST   483

/* YYNTOKENS -- Number of terminals.  */
#define YYNTOKENS  43
/* YYNNTS -- Number of nonterminals.  */
#define YYNNTS  21
/* YYNRULES -- Number of rules.  */
#define YYNRULES  69
/* YYNRULES -- Number of states.  */
#define YYNSTATES  152

/* YYTRANSLATE(YYLEX) -- Bison symbol number corresponding to YYLEX.  */
#define YYUNDEFTOK  2
#define YYMAXUTOK   297

#define YYTRANSLATE(YYX)						\
  ((unsigned int) (YYX) <= YYMAXUTOK ? yytranslate[YYX] : YYUNDEFTOK)

/* YYTRANSLATE[YYLEX] -- Bison symbol number corresponding to YYLEX.  */
static const yytype_uint8 yytranslate[] =
{
       0,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     1,     2,     3,     4,
       5,     6,     7,     8,     9,    10,    11,    12,    13,    14,
      15,    16,    17,    18,    19,    20,    21,    22,    23,    24,
      25,    26,    27,    28,    29,    30,    31,    32,    33,    34,
      35,    36,    37,    38,    39,    40,    41,    42
};

#if YYDEBUG
/* YYPRHS[YYN] -- Index of the first RHS symbol of rule number YYN in
   YYRHS.  */
static const yytype_uint8 yyprhs[] =
{
       0,     0,     3,     5,     8,     9,    11,    13,    15,    17,
      19,    21,    23,    25,    31,    35,    42,    47,    51,    53,
      58,    64,    68,    70,    74,    76,    77,    79,    81,    83,
      85,    87,    90,    92,    97,   103,   106,   109,   113,   115,
     121,   129,   138,   144,   147,   148,   159,   161,   163,   171,
     175,   179,   183,   187,   191,   195,   199,   202,   206,   210,
     214,   218,   222,   226,   229,   232,   236,   241,   246,   248
};

/* YYRHS -- A `-1'-separated list of the rules' RHS.  */
static const yytype_int8 yyrhs[] =
{
      44,     0,    -1,    45,    -1,    46,    45,    -1,    -1,    47,
      -1,    56,    -1,    57,    -1,    54,    -1,    58,    -1,    53,
      -1,    60,    -1,    62,    -1,     8,     8,    51,    63,    14,
      -1,     8,     8,    14,    -1,     8,    40,     8,    51,    49,
      14,    -1,    28,    63,    31,    48,    -1,    28,    63,    31,
      -1,    48,    -1,    17,    29,    50,    30,    -1,    29,    55,
      30,    20,    50,    -1,    29,    55,    30,    -1,    55,    -1,
      20,     8,    51,    -1,    17,    -1,    -1,     6,    -1,     3,
      -1,     7,    -1,     4,    -1,     8,    -1,     8,    48,    -1,
       5,    -1,     8,    17,    63,    14,    -1,     8,    48,    17,
      63,    14,    -1,    52,    34,    -1,    52,    35,    -1,    63,
      20,    55,    -1,    63,    -1,    32,    36,    63,    37,    14,
      -1,    33,    36,    63,    20,    63,    37,    14,    -1,    15,
      36,    63,    37,    29,    45,    30,    59,    -1,    16,    29,
      45,    30,    59,    -1,    16,    58,    -1,    -1,    38,    36,
      61,    63,    14,    54,    37,    29,    45,    30,    -1,    53,
      -1,    47,    -1,    39,    36,    63,    37,    29,    45,    30,
      -1,    63,    26,    63,    -1,    63,    25,    63,    -1,    63,
      23,    63,    -1,    63,    21,    63,    -1,    63,    22,    63,
      -1,    63,    24,    63,    -1,    63,    19,    63,    -1,    27,
      63,    -1,    63,     9,    63,    -1,    63,    10,    63,    -1,
      63,    12,    63,    -1,    63,    11,    63,    -1,    63,    13,
      63,    -1,    63,    18,    63,    -1,     9,    63,    -1,    10,
      63,    -1,    36,    63,    37,    -1,    63,    36,    63,    37,
      -1,    36,    63,    37,    63,    -1,    52,    -1,    54,    -1
};

/* YYRLINE[YYN] -- source line where rule number YYN was defined.  */
static const yytype_uint16 yyrline[] =
{
       0,   122,   122,   139,   152,   159,   160,   161,   162,   163,
     164,   165,   166,   169,   184,   189,   204,   215,   221,   222,
     224,   231,   232,   237,   246,   252,   258,   259,   260,   261,
     262,   263,   264,   266,   276,   287,   293,   301,   308,   310,
     312,   314,   351,   365,   366,   371,   381,   382,   384,   387,
     388,   389,   390,   391,   392,   393,   394,   395,   396,   397,
     398,   399,   400,   401,   402,   403,   404,   405,   406,   407
};
#endif

#if YYDEBUG || YYERROR_VERBOSE || YYTOKEN_TABLE
/* YYTNAME[SYMBOL-NUM] -- String name of the symbol SYMBOL-NUM.
   First, the terminals, then, starting at YYNTOKENS, nonterminals.  */
static const char *const yytname[] =
{
  "$end", "error", "$undefined", "entero", "decimal", "caracter",
  "booleano", "cadena", "id", "suma", "menos", "multi", "division",
  "potencia", "puntocoma", "si", "sino", "igual", "igualdoble",
  "diferente", "coma", "menor", "menorigual", "mayor", "mayorigual",
  "ordoble", "anddoble", "admiracion", "cora", "llaa", "llac", "corc",
  "imprimir", "show", "sumadoble", "restadoble", "para", "parc", "parafor",
  "repetir", "arreglo", "comenta", "coment", "$accept", "INI", "CORPO",
  "CUERPO", "VAR", "CONTE", "CONTES", "CONTESS", "MAS", "VAL", "ASIG",
  "ALTE", "NIDO", "IMPRI", "SHO", "SIS", "ELS", "PARAS", "ESC", "REPE",
  "OPE", 0
};
#endif

# ifdef YYPRINT
/* YYTOKNUM[YYLEX-NUM] -- Internal token number corresponding to
   token YYLEX-NUM.  */
static const yytype_uint16 yytoknum[] =
{
       0,   256,   257,   258,   259,   260,   261,   262,   263,   264,
     265,   266,   267,   268,   269,   270,   271,   272,   273,   274,
     275,   276,   277,   278,   279,   280,   281,   282,   283,   284,
     285,   286,   287,   288,   289,   290,   291,   292,   293,   294,
     295,   296,   297
};
# endif

/* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
static const yytype_uint8 yyr1[] =
{
       0,    43,    44,    45,    45,    46,    46,    46,    46,    46,
      46,    46,    46,    47,    47,    47,    48,    48,    49,    49,
      50,    50,    50,    51,    51,    51,    52,    52,    52,    52,
      52,    52,    52,    53,    53,    54,    54,    55,    55,    56,
      57,    58,    59,    59,    59,    60,    61,    61,    62,    63,
      63,    63,    63,    63,    63,    63,    63,    63,    63,    63,
      63,    63,    63,    63,    63,    63,    63,    63,    63,    63
};

/* YYR2[YYN] -- Number of symbols composing right hand side of rule YYN.  */
static const yytype_uint8 yyr2[] =
{
       0,     2,     1,     2,     0,     1,     1,     1,     1,     1,
       1,     1,     1,     5,     3,     6,     4,     3,     1,     4,
       5,     3,     1,     3,     1,     0,     1,     1,     1,     1,
       1,     2,     1,     4,     5,     2,     2,     3,     1,     5,
       7,     8,     5,     2,     0,    10,     1,     1,     7,     3,
       3,     3,     3,     3,     3,     3,     2,     3,     3,     3,
       3,     3,     3,     2,     2,     3,     4,     4,     1,     1
};

/* YYDEFACT[STATE-NAME] -- Default rule to reduce with in state
   STATE-NUM when YYTABLE doesn't specify something else to do.  Zero
   means the default is an error.  */
static const yytype_uint8 yydefact[] =
{
       4,    27,    29,    32,    26,    28,    30,     0,     0,     0,
       0,     0,     0,     2,     4,     5,     0,    10,     8,     6,
       7,     9,    11,    12,    25,     0,     0,     0,    31,     0,
       0,     0,     0,     0,     1,     3,    35,    36,    14,    24,
       0,     0,    30,     0,     0,     0,     0,    68,    69,     0,
       0,    25,     0,     0,     0,     0,     0,    47,    46,     0,
       0,    25,     0,    31,    63,    64,    56,     0,     0,     0,
       0,     0,     0,    33,     0,     0,     0,     0,     0,     0,
       0,     0,     0,    17,     0,     0,     0,     0,     0,     0,
       0,     0,    23,    13,    65,    57,    58,    60,    59,    61,
      62,    55,    52,    53,    51,    54,    50,    49,     0,    16,
       0,    18,     0,    34,     4,    39,     0,     0,     4,    67,
      66,     0,    15,     0,     0,     0,     0,     0,     0,    22,
      38,    44,    40,     0,    48,     0,    19,     0,     0,    41,
       4,    21,    37,     4,    43,     0,     0,     0,    45,    20,
      44,    42
};

/* YYDEFGOTO[NTERM-NUM].  */
static const yytype_int16 yydefgoto[] =
{
      -1,    12,    13,    14,    15,    28,   112,   128,    41,    47,
      17,    48,   129,    19,    20,    21,   139,    22,    59,    23,
     130
};

/* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
#define YYPACT_NINF -111
static const yytype_int16 yypact[] =
{
      70,  -111,  -111,  -111,  -111,  -111,    71,   -24,    -5,     3,
       5,     6,    36,  -111,    70,  -111,    -1,  -111,  -111,  -111,
    -111,  -111,  -111,  -111,    18,   128,   128,    35,    27,   128,
     128,   128,    37,   128,  -111,  -111,  -111,  -111,  -111,  -111,
      43,   128,    41,   128,   128,   128,   128,    -1,  -111,   267,
     286,    -7,   128,   147,   167,   314,    71,  -111,  -111,   128,
     187,    -7,   333,  -111,    50,    50,   447,   207,   128,   128,
     128,   128,   128,  -111,   128,   128,   128,   128,   128,   128,
     128,   128,   128,    41,     1,   352,    42,    51,   128,    27,
     371,    52,  -111,  -111,   128,    50,    50,    -8,    -8,    28,
     138,   138,   138,   138,   138,   138,   428,   447,   227,  -111,
      53,  -111,    56,  -111,    70,  -111,   247,   469,    70,   409,
    -111,    86,  -111,    54,    66,    46,    57,   128,    67,  -111,
     390,    82,  -111,    75,  -111,    76,  -111,   128,    -9,  -111,
      70,    80,  -111,    70,  -111,    77,    86,    91,  -111,  -111,
      82,  -111
};

/* YYPGOTO[NTERM-NUM].  */
static const yytype_int8 yypgoto[] =
{
    -111,  -111,   -13,  -111,    78,   -16,  -111,   -34,   -36,     0,
      92,     2,  -110,  -111,  -111,   -15,   -25,  -111,  -111,  -111,
     -22
};

/* YYTABLE[YYPACT[STATE-NUM]].  What to do in state STATE-NUM.  If
   positive, shift that token.  If negative, reduce the rule which
   number is the opposite.  If zero, do what YYDEFACT says.
   If YYTABLE_NINF, syntax error.  */
#define YYTABLE_NINF -1
static const yytype_uint8 yytable[] =
{
      16,    35,    18,    49,    50,    72,     7,    53,    54,    55,
      39,    60,    29,    40,    16,    84,    18,   135,   110,    62,
     143,    64,    65,    66,    67,    92,    63,   142,    82,    26,
      85,    30,    38,    36,    37,    39,    34,    90,    40,    31,
      89,    32,    33,    51,    52,    56,    95,    96,    97,    98,
      99,    61,   100,   101,   102,   103,   104,   105,   106,   107,
     108,    70,    71,    72,    82,   115,   116,   109,   111,    26,
     122,   114,   119,     1,     2,     3,     4,     5,     6,    24,
     132,   118,   121,   133,   131,     7,    82,   134,    25,     1,
       2,     3,     4,     5,    42,    43,    44,   136,   138,    26,
     146,   123,     8,     9,   140,   126,   141,   148,    10,    11,
      57,    27,   149,    45,    16,   127,    18,    16,    16,   125,
      18,   150,    46,   144,    58,   151,     0,   145,     0,     0,
     147,     1,     2,     3,     4,     5,    42,    43,    44,     0,
      16,     0,    18,    16,     0,    18,     0,    68,    69,    70,
      71,    72,     0,     0,     0,    45,    68,    69,    70,    71,
      72,     0,     0,     0,    46,    74,    75,     0,    76,    77,
      78,    79,    80,    81,    82,     0,    68,    69,    70,    71,
      72,     0,     0,    82,    86,    74,    75,     0,    76,    77,
      78,    79,    80,    81,     0,     0,    68,    69,    70,    71,
      72,     0,     0,    82,    87,    74,    75,     0,    76,    77,
      78,    79,    80,    81,     0,     0,    68,    69,    70,    71,
      72,     0,     0,    82,    91,    74,    75,     0,    76,    77,
      78,    79,    80,    81,     0,     0,    68,    69,    70,    71,
      72,     0,     0,    82,    94,    74,    75,     0,    76,    77,
      78,    79,    80,    81,     0,     0,    68,    69,    70,    71,
      72,     0,     0,    82,   120,    74,    75,     0,    76,    77,
      78,    79,    80,    81,     0,     0,    68,    69,    70,    71,
      72,    73,     0,    82,   124,    74,    75,     0,    76,    77,
      78,    79,    80,    81,     0,    68,    69,    70,    71,    72,
       0,     0,     0,    82,    74,    75,     0,    76,    77,    78,
      79,    80,    81,     0,     0,     0,     0,    83,     0,     0,
       0,     0,    82,    68,    69,    70,    71,    72,     0,     0,
       0,     0,    74,    75,    88,    76,    77,    78,    79,    80,
      81,     0,    68,    69,    70,    71,    72,    93,     0,     0,
      82,    74,    75,     0,    76,    77,    78,    79,    80,    81,
       0,    68,    69,    70,    71,    72,   113,     0,     0,    82,
      74,    75,     0,    76,    77,    78,    79,    80,    81,     0,
      68,    69,    70,    71,    72,   117,     0,     0,    82,    74,
      75,     0,    76,    77,    78,    79,    80,    81,     0,    68,
      69,    70,    71,    72,     0,     0,     0,    82,    74,    75,
     137,    76,    77,    78,    79,    80,    81,     0,    68,    69,
      70,    71,    72,     0,     0,     0,    82,    74,    75,     0,
      76,    77,    78,    79,    80,    81,     0,    68,    69,    70,
      71,    72,     0,     0,     0,    82,    74,    75,     0,    76,
      77,    78,    79,     0,    81,     0,    68,    69,    70,    71,
      72,     0,     0,     0,    82,    74,    75,     0,    76,    77,
      78,    79,     1,     2,     3,     4,     5,    42,     0,     0,
       0,     0,     0,    82
};

static const yytype_int16 yycheck[] =
{
       0,    14,     0,    25,    26,    13,    15,    29,    30,    31,
      17,    33,    36,    20,    14,    51,    14,   127,    17,    41,
      29,    43,    44,    45,    46,    61,    42,   137,    36,    28,
      52,    36,    14,    34,    35,    17,     0,    59,    20,    36,
      56,    36,    36,     8,    17,     8,    68,    69,    70,    71,
      72,     8,    74,    75,    76,    77,    78,    79,    80,    81,
      82,    11,    12,    13,    36,    14,    88,    83,    84,    28,
      14,    29,    94,     3,     4,     5,     6,     7,     8,     8,
      14,    29,    29,    37,    30,    15,    36,    30,    17,     3,
       4,     5,     6,     7,     8,     9,    10,    30,    16,    28,
      20,   114,    32,    33,    29,   118,    30,    30,    38,    39,
      32,    40,   146,    27,   114,    29,   114,   117,   118,   117,
     118,    30,    36,   138,    32,   150,    -1,   140,    -1,    -1,
     143,     3,     4,     5,     6,     7,     8,     9,    10,    -1,
     140,    -1,   140,   143,    -1,   143,    -1,     9,    10,    11,
      12,    13,    -1,    -1,    -1,    27,     9,    10,    11,    12,
      13,    -1,    -1,    -1,    36,    18,    19,    -1,    21,    22,
      23,    24,    25,    26,    36,    -1,     9,    10,    11,    12,
      13,    -1,    -1,    36,    37,    18,    19,    -1,    21,    22,
      23,    24,    25,    26,    -1,    -1,     9,    10,    11,    12,
      13,    -1,    -1,    36,    37,    18,    19,    -1,    21,    22,
      23,    24,    25,    26,    -1,    -1,     9,    10,    11,    12,
      13,    -1,    -1,    36,    37,    18,    19,    -1,    21,    22,
      23,    24,    25,    26,    -1,    -1,     9,    10,    11,    12,
      13,    -1,    -1,    36,    37,    18,    19,    -1,    21,    22,
      23,    24,    25,    26,    -1,    -1,     9,    10,    11,    12,
      13,    -1,    -1,    36,    37,    18,    19,    -1,    21,    22,
      23,    24,    25,    26,    -1,    -1,     9,    10,    11,    12,
      13,    14,    -1,    36,    37,    18,    19,    -1,    21,    22,
      23,    24,    25,    26,    -1,     9,    10,    11,    12,    13,
      -1,    -1,    -1,    36,    18,    19,    -1,    21,    22,    23,
      24,    25,    26,    -1,    -1,    -1,    -1,    31,    -1,    -1,
      -1,    -1,    36,     9,    10,    11,    12,    13,    -1,    -1,
      -1,    -1,    18,    19,    20,    21,    22,    23,    24,    25,
      26,    -1,     9,    10,    11,    12,    13,    14,    -1,    -1,
      36,    18,    19,    -1,    21,    22,    23,    24,    25,    26,
      -1,     9,    10,    11,    12,    13,    14,    -1,    -1,    36,
      18,    19,    -1,    21,    22,    23,    24,    25,    26,    -1,
       9,    10,    11,    12,    13,    14,    -1,    -1,    36,    18,
      19,    -1,    21,    22,    23,    24,    25,    26,    -1,     9,
      10,    11,    12,    13,    -1,    -1,    -1,    36,    18,    19,
      20,    21,    22,    23,    24,    25,    26,    -1,     9,    10,
      11,    12,    13,    -1,    -1,    -1,    36,    18,    19,    -1,
      21,    22,    23,    24,    25,    26,    -1,     9,    10,    11,
      12,    13,    -1,    -1,    -1,    36,    18,    19,    -1,    21,
      22,    23,    24,    -1,    26,    -1,     9,    10,    11,    12,
      13,    -1,    -1,    -1,    36,    18,    19,    -1,    21,    22,
      23,    24,     3,     4,     5,     6,     7,     8,    -1,    -1,
      -1,    -1,    -1,    36
};

/* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
   symbol of state STATE-NUM.  */
static const yytype_uint8 yystos[] =
{
       0,     3,     4,     5,     6,     7,     8,    15,    32,    33,
      38,    39,    44,    45,    46,    47,    52,    53,    54,    56,
      57,    58,    60,    62,     8,    17,    28,    40,    48,    36,
      36,    36,    36,    36,     0,    45,    34,    35,    14,    17,
      20,    51,     8,     9,    10,    27,    36,    52,    54,    63,
      63,     8,    17,    63,    63,    63,     8,    47,    53,    61,
      63,     8,    63,    48,    63,    63,    63,    63,     9,    10,
      11,    12,    13,    14,    18,    19,    21,    22,    23,    24,
      25,    26,    36,    31,    51,    63,    37,    37,    20,    48,
      63,    37,    51,    14,    37,    63,    63,    63,    63,    63,
      63,    63,    63,    63,    63,    63,    63,    63,    63,    48,
      17,    48,    49,    14,    29,    14,    63,    14,    29,    63,
      37,    29,    14,    45,    37,    54,    45,    29,    50,    55,
      63,    30,    14,    37,    30,    55,    30,    20,    16,    59,
      29,    30,    55,    29,    58,    45,    20,    45,    30,    50,
      30,    59
};

#define yyerrok		(yyerrstatus = 0)
#define yyclearin	(yychar = YYEMPTY)
#define YYEMPTY		(-2)
#define YYEOF		0

#define YYACCEPT	goto yyacceptlab
#define YYABORT		goto yyabortlab
#define YYERROR		goto yyerrorlab


/* Like YYERROR except do call yyerror.  This remains here temporarily
   to ease the transition to the new meaning of YYERROR, for GCC.
   Once GCC version 2 has supplanted version 1, this can go.  However,
   YYFAIL appears to be in use.  Nevertheless, it is formally deprecated
   in Bison 2.4.2's NEWS entry, where a plan to phase it out is
   discussed.  */

#define YYFAIL		goto yyerrlab
#if defined YYFAIL
  /* This is here to suppress warnings from the GCC cpp's
     -Wunused-macros.  Normally we don't worry about that warning, but
     some users do, and we want to make it easy for users to remove
     YYFAIL uses, which will produce warnings from Bison 2.5.  */
#endif

#define YYRECOVERING()  (!!yyerrstatus)

#define YYBACKUP(Token, Value)					\
do								\
  if (yychar == YYEMPTY && yylen == 1)				\
    {								\
      yychar = (Token);						\
      yylval = (Value);						\
      yytoken = YYTRANSLATE (yychar);				\
      YYPOPSTACK (1);						\
      goto yybackup;						\
    }								\
  else								\
    {								\
      yyerror (YY_("syntax error: cannot back up")); \
      YYERROR;							\
    }								\
while (YYID (0))


#define YYTERROR	1
#define YYERRCODE	256


/* YYLLOC_DEFAULT -- Set CURRENT to span from RHS[1] to RHS[N].
   If N is 0, then set CURRENT to the empty location which ends
   the previous symbol: RHS[0] (always defined).  */

#define YYRHSLOC(Rhs, K) ((Rhs)[K])
#ifndef YYLLOC_DEFAULT
# define YYLLOC_DEFAULT(Current, Rhs, N)				\
    do									\
      if (YYID (N))                                                    \
	{								\
	  (Current).first_line   = YYRHSLOC (Rhs, 1).first_line;	\
	  (Current).first_column = YYRHSLOC (Rhs, 1).first_column;	\
	  (Current).last_line    = YYRHSLOC (Rhs, N).last_line;		\
	  (Current).last_column  = YYRHSLOC (Rhs, N).last_column;	\
	}								\
      else								\
	{								\
	  (Current).first_line   = (Current).last_line   =		\
	    YYRHSLOC (Rhs, 0).last_line;				\
	  (Current).first_column = (Current).last_column =		\
	    YYRHSLOC (Rhs, 0).last_column;				\
	}								\
    while (YYID (0))
#endif


/* YY_LOCATION_PRINT -- Print the location on the stream.
   This macro was not mandated originally: define only if we know
   we won't break user code: when these are the locations we know.  */

#ifndef YY_LOCATION_PRINT
# if defined YYLTYPE_IS_TRIVIAL && YYLTYPE_IS_TRIVIAL
#  define YY_LOCATION_PRINT(File, Loc)			\
     fprintf (File, "%d.%d-%d.%d",			\
	      (Loc).first_line, (Loc).first_column,	\
	      (Loc).last_line,  (Loc).last_column)
# else
#  define YY_LOCATION_PRINT(File, Loc) ((void) 0)
# endif
#endif


/* YYLEX -- calling `yylex' with the right arguments.  */

#ifdef YYLEX_PARAM
# define YYLEX yylex (YYLEX_PARAM)
#else
# define YYLEX yylex ()
#endif

/* Enable debugging if requested.  */
#if YYDEBUG

# ifndef YYFPRINTF
#  include <stdio.h> /* INFRINGES ON USER NAME SPACE */
#  define YYFPRINTF fprintf
# endif

# define YYDPRINTF(Args)			\
do {						\
  if (yydebug)					\
    YYFPRINTF Args;				\
} while (YYID (0))

# define YY_SYMBOL_PRINT(Title, Type, Value, Location)			  \
do {									  \
  if (yydebug)								  \
    {									  \
      YYFPRINTF (stderr, "%s ", Title);					  \
      yy_symbol_print (stderr,						  \
		  Type, Value, Location); \
      YYFPRINTF (stderr, "\n");						  \
    }									  \
} while (YYID (0))


/*--------------------------------.
| Print this symbol on YYOUTPUT.  |
`--------------------------------*/

/*ARGSUSED*/
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_symbol_value_print (FILE *yyoutput, int yytype, YYSTYPE const * const yyvaluep, YYLTYPE const * const yylocationp)
#else
static void
yy_symbol_value_print (yyoutput, yytype, yyvaluep, yylocationp)
    FILE *yyoutput;
    int yytype;
    YYSTYPE const * const yyvaluep;
    YYLTYPE const * const yylocationp;
#endif
{
  if (!yyvaluep)
    return;
  YYUSE (yylocationp);
# ifdef YYPRINT
  if (yytype < YYNTOKENS)
    YYPRINT (yyoutput, yytoknum[yytype], *yyvaluep);
# else
  YYUSE (yyoutput);
# endif
  switch (yytype)
    {
      default:
	break;
    }
}


/*--------------------------------.
| Print this symbol on YYOUTPUT.  |
`--------------------------------*/

#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_symbol_print (FILE *yyoutput, int yytype, YYSTYPE const * const yyvaluep, YYLTYPE const * const yylocationp)
#else
static void
yy_symbol_print (yyoutput, yytype, yyvaluep, yylocationp)
    FILE *yyoutput;
    int yytype;
    YYSTYPE const * const yyvaluep;
    YYLTYPE const * const yylocationp;
#endif
{
  if (yytype < YYNTOKENS)
    YYFPRINTF (yyoutput, "token %s (", yytname[yytype]);
  else
    YYFPRINTF (yyoutput, "nterm %s (", yytname[yytype]);

  YY_LOCATION_PRINT (yyoutput, *yylocationp);
  YYFPRINTF (yyoutput, ": ");
  yy_symbol_value_print (yyoutput, yytype, yyvaluep, yylocationp);
  YYFPRINTF (yyoutput, ")");
}

/*------------------------------------------------------------------.
| yy_stack_print -- Print the state stack from its BOTTOM up to its |
| TOP (included).                                                   |
`------------------------------------------------------------------*/

#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_stack_print (yytype_int16 *yybottom, yytype_int16 *yytop)
#else
static void
yy_stack_print (yybottom, yytop)
    yytype_int16 *yybottom;
    yytype_int16 *yytop;
#endif
{
  YYFPRINTF (stderr, "Stack now");
  for (; yybottom <= yytop; yybottom++)
    {
      int yybot = *yybottom;
      YYFPRINTF (stderr, " %d", yybot);
    }
  YYFPRINTF (stderr, "\n");
}

# define YY_STACK_PRINT(Bottom, Top)				\
do {								\
  if (yydebug)							\
    yy_stack_print ((Bottom), (Top));				\
} while (YYID (0))


/*------------------------------------------------.
| Report that the YYRULE is going to be reduced.  |
`------------------------------------------------*/

#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_reduce_print (YYSTYPE *yyvsp, YYLTYPE *yylsp, int yyrule)
#else
static void
yy_reduce_print (yyvsp, yylsp, yyrule)
    YYSTYPE *yyvsp;
    YYLTYPE *yylsp;
    int yyrule;
#endif
{
  int yynrhs = yyr2[yyrule];
  int yyi;
  unsigned long int yylno = yyrline[yyrule];
  YYFPRINTF (stderr, "Reducing stack by rule %d (line %lu):\n",
	     yyrule - 1, yylno);
  /* The symbols being reduced.  */
  for (yyi = 0; yyi < yynrhs; yyi++)
    {
      YYFPRINTF (stderr, "   $%d = ", yyi + 1);
      yy_symbol_print (stderr, yyrhs[yyprhs[yyrule] + yyi],
		       &(yyvsp[(yyi + 1) - (yynrhs)])
		       , &(yylsp[(yyi + 1) - (yynrhs)])		       );
      YYFPRINTF (stderr, "\n");
    }
}

# define YY_REDUCE_PRINT(Rule)		\
do {					\
  if (yydebug)				\
    yy_reduce_print (yyvsp, yylsp, Rule); \
} while (YYID (0))

/* Nonzero means print parse trace.  It is left uninitialized so that
   multiple parsers can coexist.  */
int yydebug;
#else /* !YYDEBUG */
# define YYDPRINTF(Args)
# define YY_SYMBOL_PRINT(Title, Type, Value, Location)
# define YY_STACK_PRINT(Bottom, Top)
# define YY_REDUCE_PRINT(Rule)
#endif /* !YYDEBUG */


/* YYINITDEPTH -- initial size of the parser's stacks.  */
#ifndef	YYINITDEPTH
# define YYINITDEPTH 200
#endif

/* YYMAXDEPTH -- maximum size the stacks can grow to (effective only
   if the built-in stack extension method is used).

   Do not make this value too large; the results are undefined if
   YYSTACK_ALLOC_MAXIMUM < YYSTACK_BYTES (YYMAXDEPTH)
   evaluated with infinite-precision integer arithmetic.  */

#ifndef YYMAXDEPTH
# define YYMAXDEPTH 10000
#endif



#if YYERROR_VERBOSE

# ifndef yystrlen
#  if defined __GLIBC__ && defined _STRING_H
#   define yystrlen strlen
#  else
/* Return the length of YYSTR.  */
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static YYSIZE_T
yystrlen (const char *yystr)
#else
static YYSIZE_T
yystrlen (yystr)
    const char *yystr;
#endif
{
  YYSIZE_T yylen;
  for (yylen = 0; yystr[yylen]; yylen++)
    continue;
  return yylen;
}
#  endif
# endif

# ifndef yystpcpy
#  if defined __GLIBC__ && defined _STRING_H && defined _GNU_SOURCE
#   define yystpcpy stpcpy
#  else
/* Copy YYSRC to YYDEST, returning the address of the terminating '\0' in
   YYDEST.  */
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static char *
yystpcpy (char *yydest, const char *yysrc)
#else
static char *
yystpcpy (yydest, yysrc)
    char *yydest;
    const char *yysrc;
#endif
{
  char *yyd = yydest;
  const char *yys = yysrc;

  while ((*yyd++ = *yys++) != '\0')
    continue;

  return yyd - 1;
}
#  endif
# endif

# ifndef yytnamerr
/* Copy to YYRES the contents of YYSTR after stripping away unnecessary
   quotes and backslashes, so that it's suitable for yyerror.  The
   heuristic is that double-quoting is unnecessary unless the string
   contains an apostrophe, a comma, or backslash (other than
   backslash-backslash).  YYSTR is taken from yytname.  If YYRES is
   null, do not copy; instead, return the length of what the result
   would have been.  */
static YYSIZE_T
yytnamerr (char *yyres, const char *yystr)
{
  if (*yystr == '"')
    {
      YYSIZE_T yyn = 0;
      char const *yyp = yystr;

      for (;;)
	switch (*++yyp)
	  {
	  case '\'':
	  case ',':
	    goto do_not_strip_quotes;

	  case '\\':
	    if (*++yyp != '\\')
	      goto do_not_strip_quotes;
	    /* Fall through.  */
	  default:
	    if (yyres)
	      yyres[yyn] = *yyp;
	    yyn++;
	    break;

	  case '"':
	    if (yyres)
	      yyres[yyn] = '\0';
	    return yyn;
	  }
    do_not_strip_quotes: ;
    }

  if (! yyres)
    return yystrlen (yystr);

  return yystpcpy (yyres, yystr) - yyres;
}
# endif

/* Copy into YYRESULT an error message about the unexpected token
   YYCHAR while in state YYSTATE.  Return the number of bytes copied,
   including the terminating null byte.  If YYRESULT is null, do not
   copy anything; just return the number of bytes that would be
   copied.  As a special case, return 0 if an ordinary "syntax error"
   message will do.  Return YYSIZE_MAXIMUM if overflow occurs during
   size calculation.  */
static YYSIZE_T
yysyntax_error (char *yyresult, int yystate, int yychar)
{
  int yyn = yypact[yystate];

  if (! (YYPACT_NINF < yyn && yyn <= YYLAST))
    return 0;
  else
    {
      int yytype = YYTRANSLATE (yychar);
      YYSIZE_T yysize0 = yytnamerr (0, yytname[yytype]);
      YYSIZE_T yysize = yysize0;
      YYSIZE_T yysize1;
      int yysize_overflow = 0;
      enum { YYERROR_VERBOSE_ARGS_MAXIMUM = 5 };
      char const *yyarg[YYERROR_VERBOSE_ARGS_MAXIMUM];
      int yyx;

# if 0
      /* This is so xgettext sees the translatable formats that are
	 constructed on the fly.  */
      YY_("syntax error, unexpected %s");
      YY_("syntax error, unexpected %s, expecting %s");
      YY_("syntax error, unexpected %s, expecting %s or %s");
      YY_("syntax error, unexpected %s, expecting %s or %s or %s");
      YY_("syntax error, unexpected %s, expecting %s or %s or %s or %s");
# endif
      char *yyfmt;
      char const *yyf;
      static char const yyunexpected[] = "syntax error, unexpected %s";
      static char const yyexpecting[] = ", expecting %s";
      static char const yyor[] = " or %s";
      char yyformat[sizeof yyunexpected
		    + sizeof yyexpecting - 1
		    + ((YYERROR_VERBOSE_ARGS_MAXIMUM - 2)
		       * (sizeof yyor - 1))];
      char const *yyprefix = yyexpecting;

      /* Start YYX at -YYN if negative to avoid negative indexes in
	 YYCHECK.  */
      int yyxbegin = yyn < 0 ? -yyn : 0;

      /* Stay within bounds of both yycheck and yytname.  */
      int yychecklim = YYLAST - yyn + 1;
      int yyxend = yychecklim < YYNTOKENS ? yychecklim : YYNTOKENS;
      int yycount = 1;

      yyarg[0] = yytname[yytype];
      yyfmt = yystpcpy (yyformat, yyunexpected);

      for (yyx = yyxbegin; yyx < yyxend; ++yyx)
	if (yycheck[yyx + yyn] == yyx && yyx != YYTERROR)
	  {
	    if (yycount == YYERROR_VERBOSE_ARGS_MAXIMUM)
	      {
		yycount = 1;
		yysize = yysize0;
		yyformat[sizeof yyunexpected - 1] = '\0';
		break;
	      }
	    yyarg[yycount++] = yytname[yyx];
	    yysize1 = yysize + yytnamerr (0, yytname[yyx]);
	    yysize_overflow |= (yysize1 < yysize);
	    yysize = yysize1;
	    yyfmt = yystpcpy (yyfmt, yyprefix);
	    yyprefix = yyor;
	  }

      yyf = YY_(yyformat);
      yysize1 = yysize + yystrlen (yyf);
      yysize_overflow |= (yysize1 < yysize);
      yysize = yysize1;

      if (yysize_overflow)
	return YYSIZE_MAXIMUM;

      if (yyresult)
	{
	  /* Avoid sprintf, as that infringes on the user's name space.
	     Don't have undefined behavior even if the translation
	     produced a string with the wrong number of "%s"s.  */
	  char *yyp = yyresult;
	  int yyi = 0;
	  while ((*yyp = *yyf) != '\0')
	    {
	      if (*yyp == '%' && yyf[1] == 's' && yyi < yycount)
		{
		  yyp += yytnamerr (yyp, yyarg[yyi++]);
		  yyf += 2;
		}
	      else
		{
		  yyp++;
		  yyf++;
		}
	    }
	}
      return yysize;
    }
}
#endif /* YYERROR_VERBOSE */


/*-----------------------------------------------.
| Release the memory associated to this symbol.  |
`-----------------------------------------------*/

/*ARGSUSED*/
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yydestruct (const char *yymsg, int yytype, YYSTYPE *yyvaluep, YYLTYPE *yylocationp)
#else
static void
yydestruct (yymsg, yytype, yyvaluep, yylocationp)
    const char *yymsg;
    int yytype;
    YYSTYPE *yyvaluep;
    YYLTYPE *yylocationp;
#endif
{
  YYUSE (yyvaluep);
  YYUSE (yylocationp);

  if (!yymsg)
    yymsg = "Deleting";
  YY_SYMBOL_PRINT (yymsg, yytype, yyvaluep, yylocationp);

  switch (yytype)
    {

      default:
	break;
    }
}

/* Prevent warnings from -Wmissing-prototypes.  */
#ifdef YYPARSE_PARAM
#if defined __STDC__ || defined __cplusplus
int yyparse (void *YYPARSE_PARAM);
#else
int yyparse ();
#endif
#else /* ! YYPARSE_PARAM */
#if defined __STDC__ || defined __cplusplus
int yyparse (void);
#else
int yyparse ();
#endif
#endif /* ! YYPARSE_PARAM */


/* The lookahead symbol.  */
int yychar;

/* The semantic value of the lookahead symbol.  */
YYSTYPE yylval;

/* Location data for the lookahead symbol.  */
YYLTYPE yylloc;

/* Number of syntax errors so far.  */
int yynerrs;



/*-------------------------.
| yyparse or yypush_parse.  |
`-------------------------*/

#ifdef YYPARSE_PARAM
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
int
yyparse (void *YYPARSE_PARAM)
#else
int
yyparse (YYPARSE_PARAM)
    void *YYPARSE_PARAM;
#endif
#else /* ! YYPARSE_PARAM */
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
int
yyparse (void)
#else
int
yyparse ()

#endif
#endif
{


    int yystate;
    /* Number of tokens to shift before error messages enabled.  */
    int yyerrstatus;

    /* The stacks and their tools:
       `yyss': related to states.
       `yyvs': related to semantic values.
       `yyls': related to locations.

       Refer to the stacks thru separate pointers, to allow yyoverflow
       to reallocate them elsewhere.  */

    /* The state stack.  */
    yytype_int16 yyssa[YYINITDEPTH];
    yytype_int16 *yyss;
    yytype_int16 *yyssp;

    /* The semantic value stack.  */
    YYSTYPE yyvsa[YYINITDEPTH];
    YYSTYPE *yyvs;
    YYSTYPE *yyvsp;

    /* The location stack.  */
    YYLTYPE yylsa[YYINITDEPTH];
    YYLTYPE *yyls;
    YYLTYPE *yylsp;

    /* The locations where the error started and ended.  */
    YYLTYPE yyerror_range[2];

    YYSIZE_T yystacksize;

  int yyn;
  int yyresult;
  /* Lookahead token as an internal (translated) token number.  */
  int yytoken;
  /* The variables used to return semantic value and location from the
     action routines.  */
  YYSTYPE yyval;
  YYLTYPE yyloc;

#if YYERROR_VERBOSE
  /* Buffer for error messages, and its allocated size.  */
  char yymsgbuf[128];
  char *yymsg = yymsgbuf;
  YYSIZE_T yymsg_alloc = sizeof yymsgbuf;
#endif

#define YYPOPSTACK(N)   (yyvsp -= (N), yyssp -= (N), yylsp -= (N))

  /* The number of symbols on the RHS of the reduced rule.
     Keep to zero when no symbol should be popped.  */
  int yylen = 0;

  yytoken = 0;
  yyss = yyssa;
  yyvs = yyvsa;
  yyls = yylsa;
  yystacksize = YYINITDEPTH;

  YYDPRINTF ((stderr, "Starting parse\n"));

  yystate = 0;
  yyerrstatus = 0;
  yynerrs = 0;
  yychar = YYEMPTY; /* Cause a token to be read.  */

  /* Initialize stack pointers.
     Waste one element of value and location stack
     so that they stay on the same level as the state stack.
     The wasted elements are never initialized.  */
  yyssp = yyss;
  yyvsp = yyvs;
  yylsp = yyls;

#if defined YYLTYPE_IS_TRIVIAL && YYLTYPE_IS_TRIVIAL
  /* Initialize the default location before parsing starts.  */
  yylloc.first_line   = yylloc.last_line   = 1;
  yylloc.first_column = yylloc.last_column = 1;
#endif

  goto yysetstate;

/*------------------------------------------------------------.
| yynewstate -- Push a new state, which is found in yystate.  |
`------------------------------------------------------------*/
 yynewstate:
  /* In all cases, when you get here, the value and location stacks
     have just been pushed.  So pushing a state here evens the stacks.  */
  yyssp++;

 yysetstate:
  *yyssp = yystate;

  if (yyss + yystacksize - 1 <= yyssp)
    {
      /* Get the current used size of the three stacks, in elements.  */
      YYSIZE_T yysize = yyssp - yyss + 1;

#ifdef yyoverflow
      {
	/* Give user a chance to reallocate the stack.  Use copies of
	   these so that the &'s don't force the real ones into
	   memory.  */
	YYSTYPE *yyvs1 = yyvs;
	yytype_int16 *yyss1 = yyss;
	YYLTYPE *yyls1 = yyls;

	/* Each stack pointer address is followed by the size of the
	   data in use in that stack, in bytes.  This used to be a
	   conditional around just the two extra args, but that might
	   be undefined if yyoverflow is a macro.  */
	yyoverflow (YY_("memory exhausted"),
		    &yyss1, yysize * sizeof (*yyssp),
		    &yyvs1, yysize * sizeof (*yyvsp),
		    &yyls1, yysize * sizeof (*yylsp),
		    &yystacksize);

	yyls = yyls1;
	yyss = yyss1;
	yyvs = yyvs1;
      }
#else /* no yyoverflow */
# ifndef YYSTACK_RELOCATE
      goto yyexhaustedlab;
# else
      /* Extend the stack our own way.  */
      if (YYMAXDEPTH <= yystacksize)
	goto yyexhaustedlab;
      yystacksize *= 2;
      if (YYMAXDEPTH < yystacksize)
	yystacksize = YYMAXDEPTH;

      {
	yytype_int16 *yyss1 = yyss;
	union yyalloc *yyptr =
	  (union yyalloc *) YYSTACK_ALLOC (YYSTACK_BYTES (yystacksize));
	if (! yyptr)
	  goto yyexhaustedlab;
	YYSTACK_RELOCATE (yyss_alloc, yyss);
	YYSTACK_RELOCATE (yyvs_alloc, yyvs);
	YYSTACK_RELOCATE (yyls_alloc, yyls);
#  undef YYSTACK_RELOCATE
	if (yyss1 != yyssa)
	  YYSTACK_FREE (yyss1);
      }
# endif
#endif /* no yyoverflow */

      yyssp = yyss + yysize - 1;
      yyvsp = yyvs + yysize - 1;
      yylsp = yyls + yysize - 1;

      YYDPRINTF ((stderr, "Stack size increased to %lu\n",
		  (unsigned long int) yystacksize));

      if (yyss + yystacksize - 1 <= yyssp)
	YYABORT;
    }

  YYDPRINTF ((stderr, "Entering state %d\n", yystate));

  if (yystate == YYFINAL)
    YYACCEPT;

  goto yybackup;

/*-----------.
| yybackup.  |
`-----------*/
yybackup:

  /* Do appropriate processing given the current state.  Read a
     lookahead token if we need one and don't already have one.  */

  /* First try to decide what to do without reference to lookahead token.  */
  yyn = yypact[yystate];
  if (yyn == YYPACT_NINF)
    goto yydefault;

  /* Not known => get a lookahead token if don't already have one.  */

  /* YYCHAR is either YYEMPTY or YYEOF or a valid lookahead symbol.  */
  if (yychar == YYEMPTY)
    {
      YYDPRINTF ((stderr, "Reading a token: "));
      yychar = YYLEX;
    }

  if (yychar <= YYEOF)
    {
      yychar = yytoken = YYEOF;
      YYDPRINTF ((stderr, "Now at end of input.\n"));
    }
  else
    {
      yytoken = YYTRANSLATE (yychar);
      YY_SYMBOL_PRINT ("Next token is", yytoken, &yylval, &yylloc);
    }

  /* If the proper action on seeing token YYTOKEN is to reduce or to
     detect an error, take that action.  */
  yyn += yytoken;
  if (yyn < 0 || YYLAST < yyn || yycheck[yyn] != yytoken)
    goto yydefault;
  yyn = yytable[yyn];
  if (yyn <= 0)
    {
      if (yyn == 0 || yyn == YYTABLE_NINF)
	goto yyerrlab;
      yyn = -yyn;
      goto yyreduce;
    }

  /* Count tokens shifted since error; after three, turn off error
     status.  */
  if (yyerrstatus)
    yyerrstatus--;

  /* Shift the lookahead token.  */
  YY_SYMBOL_PRINT ("Shifting", yytoken, &yylval, &yylloc);

  /* Discard the shifted token.  */
  yychar = YYEMPTY;

  yystate = yyn;
  *++yyvsp = yylval;
  *++yylsp = yylloc;
  goto yynewstate;


/*-----------------------------------------------------------.
| yydefault -- do the default action for the current state.  |
`-----------------------------------------------------------*/
yydefault:
  yyn = yydefact[yystate];
  if (yyn == 0)
    goto yyerrlab;
  goto yyreduce;


/*-----------------------------.
| yyreduce -- Do a reduction.  |
`-----------------------------*/
yyreduce:
  /* yyn is the number of a rule to reduce with.  */
  yylen = yyr2[yyn];

  /* If YYLEN is nonzero, implement the default value of the action:
     `$$ = $1'.

     Otherwise, the following line sets YYVAL to garbage.
     This behavior is undocumented and Bison
     users should not rely upon it.  Assigning to YYVAL
     unconditionally makes the parser a bit smaller, and it avoids a
     GCC warning that YYVAL may be used uninitialized.  */
  yyval = yyvsp[1-yylen];

  /* Default location.  */
  YYLLOC_DEFAULT (yyloc, (yylsp - yylen), yylen);
  YY_REDUCE_PRINT (yyn);
  switch (yyn)
    {
        case 2:

/* Line 1464 of yacc.c  */
#line 122 "parser.yy"
    {
   // Graficador *Graf = new Graficador();
   
    
    VRaiz=(yyvsp[(1) - (1)].Nodo);
   
   
  //  Graf->Graficar($1);

   // Sali=Graf;
    
    
   // Graficador *Graf = new Graficador();   raiz=$1;


;}
    break;

  case 3:

/* Line 1464 of yacc.c  */
#line 140 "parser.yy"
    {
         /*   $$=$1;
            NodoAST *nodo = new NodoAST(@2.first_line, @2.first_column,"operacion","operacion");
            if($2!=nullptr ){
            nodo->add(*$2);
            $$->add(*nodo);
            }*/

            (yyval.Nodo)=(yyvsp[(2) - (2)].Nodo);
            (yyval.Nodo)->add(*(yyvsp[(1) - (2)].Nodo));
;}
    break;

  case 4:

/* Line 1464 of yacc.c  */
#line 152 "parser.yy"
    {
   /* NodoAST *nodo = new NodoAST(0, 0,"nulo","nulo"); $$=nodo;*/
    NodoAST *nodo = new NodoAST(0, 0,"cuerpo","cuerpo");
    (yyval.Nodo)=nodo;
    
;}
    break;

  case 5:

/* Line 1464 of yacc.c  */
#line 159 "parser.yy"
    {(yyval.Nodo) = (yyvsp[(1) - (1)].Nodo);;}
    break;

  case 6:

/* Line 1464 of yacc.c  */
#line 160 "parser.yy"
    {(yyval.Nodo) = (yyvsp[(1) - (1)].Nodo);;}
    break;

  case 7:

/* Line 1464 of yacc.c  */
#line 161 "parser.yy"
    {(yyval.Nodo) = (yyvsp[(1) - (1)].Nodo);;}
    break;

  case 8:

/* Line 1464 of yacc.c  */
#line 162 "parser.yy"
    {(yyval.Nodo) = (yyvsp[(1) - (1)].Nodo);;}
    break;

  case 9:

/* Line 1464 of yacc.c  */
#line 163 "parser.yy"
    {(yyval.Nodo) = (yyvsp[(1) - (1)].Nodo);;}
    break;

  case 10:

/* Line 1464 of yacc.c  */
#line 164 "parser.yy"
    {(yyval.Nodo) = (yyvsp[(1) - (1)].Nodo);;}
    break;

  case 11:

/* Line 1464 of yacc.c  */
#line 165 "parser.yy"
    {(yyval.Nodo) = (yyvsp[(1) - (1)].Nodo);;}
    break;

  case 12:

/* Line 1464 of yacc.c  */
#line 166 "parser.yy"
    {(yyval.Nodo) = (yyvsp[(1) - (1)].Nodo);;}
    break;

  case 13:

/* Line 1464 of yacc.c  */
#line 169 "parser.yy"
    {
    

    NodoAST *nod = new NodoAST((yylsp[(1) - (5)]).first_line, (yylsp[(1) - (5)]).first_column,"asignacion","asignacion");

    NodoAST *noda = new NodoAST((yylsp[(1) - (5)]).first_line, (yylsp[(1) - (5)]).first_column,(yyvsp[(1) - (5)].TEXT),(yyvsp[(2) - (5)].TEXT));

    (yyvsp[(3) - (5)].Nodo)->add(*noda);

    nod->add(*(yyvsp[(3) - (5)].Nodo));
    nod->add(*(yyvsp[(4) - (5)].Nodo));


(yyval.Nodo)=nod;
;}
    break;

  case 14:

/* Line 1464 of yacc.c  */
#line 184 "parser.yy"
    {NodoAST *nod = new NodoAST((yylsp[(1) - (3)]).first_line, (yylsp[(1) - (3)]).first_column,(yyvsp[(1) - (3)].TEXT),(yyvsp[(2) - (3)].TEXT)); (yyval.Nodo)=nod;;}
    break;

  case 15:

/* Line 1464 of yacc.c  */
#line 189 "parser.yy"
    {

        NodoAST *nod = new NodoAST((yylsp[(1) - (6)]).first_line, (yylsp[(1) - (6)]).first_column,(yyvsp[(2) - (6)].TEXT),"arreglo");
        NodoAST *nods = new NodoAST((yylsp[(1) - (6)]).first_line, (yylsp[(1) - (6)]).first_column,(yyvsp[(1) - (6)].TEXT),(yyvsp[(3) - (6)].TEXT));
        if((yyvsp[(4) - (6)].Nodo)!=nullptr){
        nods->add(*(yyvsp[(4) - (6)].Nodo)); 
        }
        nod->add(*nods);
         nod->add(*(yyvsp[(5) - (6)].Nodo));

         
         
          (yyval.Nodo)=nod;;}
    break;

  case 16:

/* Line 1464 of yacc.c  */
#line 205 "parser.yy"
    {
/*
NodoAST *nodo = new NodoAST(@1.first_line, @1.first_column,"contenidov","contenidov");
nodo->add(*$2);
nodo->add(*$4);
$$=nodo;
*/
(yyvsp[(4) - (4)].Nodo)->add(*(yyvsp[(2) - (4)].Nodo));
(yyval.Nodo)=(yyvsp[(4) - (4)].Nodo);
;}
    break;

  case 17:

/* Line 1464 of yacc.c  */
#line 215 "parser.yy"
    {
        
        NodoAST *nodo = new NodoAST((yylsp[(1) - (3)]).first_line, (yylsp[(1) - (3)]).first_column,"llavev","llavev");  nodo->add(*(yyvsp[(2) - (3)].Nodo)); 
        
         (yyval.Nodo) = nodo;;}
    break;

  case 18:

/* Line 1464 of yacc.c  */
#line 221 "parser.yy"
    {(yyval.Nodo) = (yyvsp[(1) - (1)].Nodo);;}
    break;

  case 19:

/* Line 1464 of yacc.c  */
#line 222 "parser.yy"
    {(yyval.Nodo) = (yyvsp[(3) - (4)].Nodo);;}
    break;

  case 20:

/* Line 1464 of yacc.c  */
#line 225 "parser.yy"
    {
NodoAST *nodo = new NodoAST((yylsp[(2) - (5)]).first_line, (yylsp[(2) - (5)]).first_column,"contenidov","contenidov");
nodo->add(*(yyvsp[(2) - (5)].Nodo));
nodo->add(*(yyvsp[(5) - (5)].Nodo));
(yyval.Nodo)=nodo;
;}
    break;

  case 21:

/* Line 1464 of yacc.c  */
#line 231 "parser.yy"
    {(yyval.Nodo) = (yyvsp[(2) - (3)].Nodo);;}
    break;

  case 22:

/* Line 1464 of yacc.c  */
#line 232 "parser.yy"
    {(yyval.Nodo) = (yyvsp[(1) - (1)].Nodo);;}
    break;

  case 23:

/* Line 1464 of yacc.c  */
#line 238 "parser.yy"
    {
NodoAST *nod = new NodoAST((yylsp[(1) - (3)]).first_line, (yylsp[(1) - (3)]).first_column,"contenidov",(yyvsp[(2) - (3)].TEXT));

(yyvsp[(3) - (3)].Nodo)->add(*nod);  

(yyval.Nodo)=(yyvsp[(3) - (3)].Nodo);

;}
    break;

  case 24:

/* Line 1464 of yacc.c  */
#line 247 "parser.yy"
    {
    NodoAST *nod = new NodoAST((yylsp[(1) - (1)]).first_line, (yylsp[(1) - (1)]).first_column,"asignacionmuchas","asignacionmuchas");
   (yyval.Nodo)=nod;
;}
    break;

  case 25:

/* Line 1464 of yacc.c  */
#line 252 "parser.yy"
    {
    //NodoAST *nod = new NodoAST(0, 0,"nulo","nulo");
    (yyval.Nodo)=nullptr;
;}
    break;

  case 26:

/* Line 1464 of yacc.c  */
#line 258 "parser.yy"
    {(yyval.Nodo) = new NodoAST((yylsp[(1) - (1)]).first_line, (yylsp[(1) - (1)]).first_column,"booleano",(yyvsp[(1) - (1)].TEXT));;}
    break;

  case 27:

/* Line 1464 of yacc.c  */
#line 259 "parser.yy"
    {(yyval.Nodo) = new NodoAST((yylsp[(1) - (1)]).first_line, (yylsp[(1) - (1)]).first_column,"entero",(yyvsp[(1) - (1)].TEXT));;}
    break;

  case 28:

/* Line 1464 of yacc.c  */
#line 260 "parser.yy"
    {(yyval.Nodo) = new NodoAST((yylsp[(1) - (1)]).first_line, (yylsp[(1) - (1)]).first_column,"cadena",(yyvsp[(1) - (1)].TEXT));;}
    break;

  case 29:

/* Line 1464 of yacc.c  */
#line 261 "parser.yy"
    {(yyval.Nodo) = new NodoAST((yylsp[(1) - (1)]).first_line, (yylsp[(1) - (1)]).first_column,"decimal",(yyvsp[(1) - (1)].TEXT));;}
    break;

  case 30:

/* Line 1464 of yacc.c  */
#line 262 "parser.yy"
    {(yyval.Nodo) = new NodoAST((yylsp[(1) - (1)]).first_line, (yylsp[(1) - (1)]).first_column,"identificador",(yyvsp[(1) - (1)].TEXT));;}
    break;

  case 31:

/* Line 1464 of yacc.c  */
#line 263 "parser.yy"
    {(yyval.Nodo) = new NodoAST((yylsp[(1) - (2)]).first_line, (yylsp[(1) - (2)]).first_column,"vectorpos",(yyvsp[(1) - (2)].TEXT)); (yyval.Nodo)->add(*(yyvsp[(2) - (2)].Nodo));;}
    break;

  case 32:

/* Line 1464 of yacc.c  */
#line 264 "parser.yy"
    {(yyval.Nodo) = new NodoAST((yylsp[(1) - (1)]).first_line, (yylsp[(1) - (1)]).first_column,"char",(yyvsp[(1) - (1)].TEXT));;}
    break;

  case 33:

/* Line 1464 of yacc.c  */
#line 267 "parser.yy"
    {
    NodoAST *nod = new NodoAST((yylsp[(1) - (4)]).first_line, (yylsp[(1) - (4)]).first_column,"asignacion","asignacion");
    NodoAST *opp = new NodoAST((yylsp[(1) - (4)]).first_line, (yylsp[(1) - (4)]).first_column,"identificador",(yyvsp[(1) - (4)].TEXT)); 
    nod->add(*opp);

     nod->add(*(yyvsp[(3) - (4)].Nodo)); 
     
     (yyval.Nodo)=nod;
;}
    break;

  case 34:

/* Line 1464 of yacc.c  */
#line 277 "parser.yy"
    {
    NodoAST *nod = new NodoAST((yylsp[(1) - (5)]).first_line, (yylsp[(1) - (5)]).first_column,"asignacion","asignacion");
    NodoAST *opp = new NodoAST((yylsp[(1) - (5)]).first_line, (yylsp[(1) - (5)]).first_column,"asignacionarreglo",(yyvsp[(1) - (5)].TEXT));
     opp->add(*(yyvsp[(2) - (5)].Nodo));
    nod->add(*opp);
    
     nod->add(*(yyvsp[(4) - (5)].Nodo)); 
     (yyval.Nodo)=nod;
;}
    break;

  case 35:

/* Line 1464 of yacc.c  */
#line 288 "parser.yy"
    {
    NodoAST *nod = new NodoAST((yylsp[(1) - (2)]).first_line, (yylsp[(1) - (2)]).first_column,"sumadoble",(yyvsp[(2) - (2)].TEXT)); 
    NodoAST *opp = new NodoAST((yylsp[(1) - (2)]).first_line, (yylsp[(1) - (2)]).first_column,"entero","1"); 
    nod->add(*(yyvsp[(1) - (2)].Nodo)); nod->add(*opp); (yyval.Nodo)=nod;
;}
    break;

  case 36:

/* Line 1464 of yacc.c  */
#line 294 "parser.yy"
    {
    NodoAST *nod = new NodoAST((yylsp[(1) - (2)]).first_line, (yylsp[(1) - (2)]).first_column,"restadoble",(yyvsp[(2) - (2)].TEXT)); 
    NodoAST *opp = new NodoAST((yylsp[(1) - (2)]).first_line, (yylsp[(1) - (2)]).first_column,"entero","1"); 
    nod->add(*(yyvsp[(1) - (2)].Nodo)); nod->add(*opp); (yyval.Nodo)=nod;
;}
    break;

  case 37:

/* Line 1464 of yacc.c  */
#line 302 "parser.yy"
    {
NodoAST *nodo = new NodoAST((yylsp[(2) - (3)]).first_line, (yylsp[(2) - (3)]).first_column,"contenidov","contenidov");
nodo->add(*(yyvsp[(1) - (3)].Nodo));
nodo->add(*(yyvsp[(3) - (3)].Nodo));
(yyval.Nodo)=nodo;
;}
    break;

  case 38:

/* Line 1464 of yacc.c  */
#line 308 "parser.yy"
    {(yyval.Nodo) = (yyvsp[(1) - (1)].Nodo);;}
    break;

  case 39:

/* Line 1464 of yacc.c  */
#line 310 "parser.yy"
    {NodoAST *nod = new NodoAST((yylsp[(1) - (5)]).first_line, (yylsp[(1) - (5)]).first_column,"imprimir","imprimir"); nod->add(*(yyvsp[(3) - (5)].Nodo));  (yyval.Nodo)=nod;;}
    break;

  case 40:

/* Line 1464 of yacc.c  */
#line 312 "parser.yy"
    {NodoAST *nod = new NodoAST((yylsp[(1) - (7)]).first_line, (yylsp[(1) - (7)]).first_column,"show","show"); nod->add(*(yyvsp[(3) - (7)].Nodo)); nod->add(*(yyvsp[(5) - (7)].Nodo)); (yyval.Nodo)=nod;;}
    break;

  case 41:

/* Line 1464 of yacc.c  */
#line 314 "parser.yy"
    {

    NodoAST *nod = new NodoAST((yylsp[(1) - (8)]).first_line, (yylsp[(1) - (8)]).first_column,"si",(yyvsp[(1) - (8)].TEXT)); 

    NodoAST *noda = new NodoAST((yylsp[(1) - (8)]).first_line, (yylsp[(1) - (8)]).first_column,"decision","decision"); 
    
    noda->add(*(yyvsp[(6) - (8)].Nodo));
    if((yyvsp[(8) - (8)].Nodo)!=nullptr){
    noda->add(*(yyvsp[(8) - (8)].Nodo)); 
    }

    
    nod->add(*(yyvsp[(3) - (8)].Nodo));
    nod->add(*noda);
    
    
    //NodoAST *condi = new NodoAST(@1.first_line, @1.first_column,"condicionsi",$1); 


   
    
    
    (yyval.Nodo)=nod;

    
   /* NodoAST *nod = new NodoAST(@1.first_line, @1.first_column,"si",$1); 
    
    NodoAST *condi = new NodoAST(@1.first_line, @1.first_column,"condicionsi",$1); 
    condi->add(*$6);
    
    nod->add(*condi); nod->add(*$8); 
    
    
    $$=nod;*/
    
    ;}
    break;

  case 42:

/* Line 1464 of yacc.c  */
#line 351 "parser.yy"
    {
    
    NodoAST *nod = new NodoAST((yylsp[(1) - (5)]).first_line, (yylsp[(1) - (5)]).first_column,"sino",(yyvsp[(1) - (5)].TEXT)); 



nod->add(*(yyvsp[(3) - (5)].Nodo)); nod->add(*(yyvsp[(5) - (5)].Nodo)); 


 (yyval.Nodo)=nod;



;}
    break;

  case 43:

/* Line 1464 of yacc.c  */
#line 365 "parser.yy"
    {NodoAST *nod = new NodoAST((yylsp[(1) - (2)]).first_line, (yylsp[(1) - (2)]).first_column,"sino",(yyvsp[(1) - (2)].TEXT)); nod->add(*(yyvsp[(2) - (2)].Nodo));  (yyval.Nodo)=nod;;}
    break;

  case 44:

/* Line 1464 of yacc.c  */
#line 366 "parser.yy"
    { 
        NodoAST *nod = new NodoAST(0, 0,"cuerpo","cuerpo"); 
        (yyval.Nodo)=nod;
    ;}
    break;

  case 45:

/* Line 1464 of yacc.c  */
#line 372 "parser.yy"
    {
    NodoAST *nod = new NodoAST((yylsp[(1) - (10)]).first_line, (yylsp[(1) - (10)]).first_column,"parafor",(yyvsp[(1) - (10)].TEXT)); 
   
    NodoAST *condicional = new NodoAST((yylsp[(3) - (10)]).first_line, (yylsp[(3) - (10)]).first_column,"condicionfor","condicionfor"); 
    condicional->add(*(yyvsp[(3) - (10)].Nodo));condicional->add(*(yyvsp[(4) - (10)].Nodo));condicional->add(*(yyvsp[(6) - (10)].Nodo));
    condicional->add(*(yyvsp[(9) - (10)].Nodo));
    nod->add(*condicional);  (yyval.Nodo)=nod;
;}
    break;

  case 46:

/* Line 1464 of yacc.c  */
#line 381 "parser.yy"
    {(yyval.Nodo) = (yyvsp[(1) - (1)].Nodo);;}
    break;

  case 47:

/* Line 1464 of yacc.c  */
#line 382 "parser.yy"
    {(yyval.Nodo) = (yyvsp[(1) - (1)].Nodo);;}
    break;

  case 48:

/* Line 1464 of yacc.c  */
#line 384 "parser.yy"
    {NodoAST *nod = new NodoAST((yylsp[(1) - (7)]).first_line, (yylsp[(1) - (7)]).first_column,"repetir",(yyvsp[(1) - (7)].TEXT)); nod->add(*(yyvsp[(3) - (7)].Nodo)); nod->add(*(yyvsp[(6) - (7)].Nodo)); (yyval.Nodo)=nod;;}
    break;

  case 49:

/* Line 1464 of yacc.c  */
#line 387 "parser.yy"
    {NodoAST *nod = new NodoAST((yylsp[(2) - (3)]).first_line, (yylsp[(2) - (3)]).first_column,"anddoble",(yyvsp[(2) - (3)].TEXT)); nod->add(*(yyvsp[(1) - (3)].Nodo)); nod->add(*(yyvsp[(3) - (3)].Nodo)); (yyval.Nodo)=nod;;}
    break;

  case 50:

/* Line 1464 of yacc.c  */
#line 388 "parser.yy"
    {NodoAST *nod = new NodoAST((yylsp[(2) - (3)]).first_line, (yylsp[(2) - (3)]).first_column,"ordoble",(yyvsp[(2) - (3)].TEXT)); nod->add(*(yyvsp[(1) - (3)].Nodo)); nod->add(*(yyvsp[(3) - (3)].Nodo)); (yyval.Nodo)=nod;;}
    break;

  case 51:

/* Line 1464 of yacc.c  */
#line 389 "parser.yy"
    {NodoAST *nod = new NodoAST((yylsp[(2) - (3)]).first_line, (yylsp[(2) - (3)]).first_column,"mayor",(yyvsp[(2) - (3)].TEXT)); nod->add(*(yyvsp[(1) - (3)].Nodo)); nod->add(*(yyvsp[(3) - (3)].Nodo)); (yyval.Nodo)=nod;;}
    break;

  case 52:

/* Line 1464 of yacc.c  */
#line 390 "parser.yy"
    {NodoAST *nod = new NodoAST((yylsp[(2) - (3)]).first_line, (yylsp[(2) - (3)]).first_column,"menor",(yyvsp[(2) - (3)].TEXT)); nod->add(*(yyvsp[(1) - (3)].Nodo)); nod->add(*(yyvsp[(3) - (3)].Nodo)); (yyval.Nodo)=nod;;}
    break;

  case 53:

/* Line 1464 of yacc.c  */
#line 391 "parser.yy"
    {NodoAST *nod = new NodoAST((yylsp[(2) - (3)]).first_line, (yylsp[(2) - (3)]).first_column,"menorigual",(yyvsp[(2) - (3)].TEXT)); nod->add(*(yyvsp[(1) - (3)].Nodo)); nod->add(*(yyvsp[(3) - (3)].Nodo)); (yyval.Nodo)=nod;;}
    break;

  case 54:

/* Line 1464 of yacc.c  */
#line 392 "parser.yy"
    {NodoAST *nod = new NodoAST((yylsp[(2) - (3)]).first_line, (yylsp[(2) - (3)]).first_column,"mayorigual",(yyvsp[(2) - (3)].TEXT)); nod->add(*(yyvsp[(1) - (3)].Nodo)); nod->add(*(yyvsp[(3) - (3)].Nodo)); (yyval.Nodo)=nod;;}
    break;

  case 55:

/* Line 1464 of yacc.c  */
#line 393 "parser.yy"
    {NodoAST *nod = new NodoAST((yylsp[(2) - (3)]).first_line, (yylsp[(2) - (3)]).first_column,"diferente",(yyvsp[(2) - (3)].TEXT)); nod->add(*(yyvsp[(1) - (3)].Nodo)); nod->add(*(yyvsp[(3) - (3)].Nodo)); (yyval.Nodo)=nod;;}
    break;

  case 56:

/* Line 1464 of yacc.c  */
#line 394 "parser.yy"
    {NodoAST *nod = new NodoAST((yylsp[(2) - (2)]).first_line, (yylsp[(2) - (2)]).first_column,"admiracion",(yyvsp[(1) - (2)].TEXT)); nod->add(*(yyvsp[(2) - (2)].Nodo));  (yyval.Nodo)=nod;;}
    break;

  case 57:

/* Line 1464 of yacc.c  */
#line 395 "parser.yy"
    {NodoAST *nod = new NodoAST((yylsp[(2) - (3)]).first_line, (yylsp[(2) - (3)]).first_column,"suma",(yyvsp[(2) - (3)].TEXT)); nod->add(*(yyvsp[(1) - (3)].Nodo)); nod->add(*(yyvsp[(3) - (3)].Nodo)); (yyval.Nodo)=nod;;}
    break;

  case 58:

/* Line 1464 of yacc.c  */
#line 396 "parser.yy"
    {NodoAST *nod = new NodoAST((yylsp[(2) - (3)]).first_line, (yylsp[(2) - (3)]).first_column,"menos",(yyvsp[(2) - (3)].TEXT)); nod->add(*(yyvsp[(1) - (3)].Nodo)); nod->add(*(yyvsp[(3) - (3)].Nodo)); (yyval.Nodo)=nod;;}
    break;

  case 59:

/* Line 1464 of yacc.c  */
#line 397 "parser.yy"
    {NodoAST *nod = new NodoAST((yylsp[(2) - (3)]).first_line, (yylsp[(2) - (3)]).first_column,"division",(yyvsp[(2) - (3)].TEXT)); nod->add(*(yyvsp[(1) - (3)].Nodo)); nod->add(*(yyvsp[(3) - (3)].Nodo)); (yyval.Nodo)=nod;;}
    break;

  case 60:

/* Line 1464 of yacc.c  */
#line 398 "parser.yy"
    {NodoAST *nod = new NodoAST((yylsp[(2) - (3)]).first_line, (yylsp[(2) - (3)]).first_column,"multi",(yyvsp[(2) - (3)].TEXT)); nod->add(*(yyvsp[(1) - (3)].Nodo)); nod->add(*(yyvsp[(3) - (3)].Nodo)); (yyval.Nodo)=nod;;}
    break;

  case 61:

/* Line 1464 of yacc.c  */
#line 399 "parser.yy"
    {NodoAST *nod = new NodoAST((yylsp[(2) - (3)]).first_line, (yylsp[(2) - (3)]).first_column,"potencia",(yyvsp[(2) - (3)].TEXT)); nod->add(*(yyvsp[(1) - (3)].Nodo)); nod->add(*(yyvsp[(3) - (3)].Nodo)); (yyval.Nodo)=nod;;}
    break;

  case 62:

/* Line 1464 of yacc.c  */
#line 400 "parser.yy"
    {NodoAST *nod = new NodoAST((yylsp[(2) - (3)]).first_line, (yylsp[(2) - (3)]).first_column,"igualdoble",(yyvsp[(2) - (3)].TEXT)); nod->add(*(yyvsp[(1) - (3)].Nodo)); nod->add(*(yyvsp[(3) - (3)].Nodo)); (yyval.Nodo)=nod;;}
    break;

  case 63:

/* Line 1464 of yacc.c  */
#line 401 "parser.yy"
    {(yyval.Nodo) = (yyvsp[(2) - (2)].Nodo);;}
    break;

  case 64:

/* Line 1464 of yacc.c  */
#line 402 "parser.yy"
    { (yyval.Nodo) = new NodoAST((yylsp[(2) - (2)]).first_line, (yylsp[(2) - (2)]).first_column,"negativo",(yyvsp[(1) - (2)].TEXT)); (yyval.Nodo)->add(*(yyvsp[(2) - (2)].Nodo));;}
    break;

  case 65:

/* Line 1464 of yacc.c  */
#line 403 "parser.yy"
    {(yyval.Nodo) = (yyvsp[(2) - (3)].Nodo);;}
    break;

  case 66:

/* Line 1464 of yacc.c  */
#line 404 "parser.yy"
    {NodoAST *nod = new NodoAST((yylsp[(2) - (4)]).first_line, (yylsp[(2) - (4)]).first_column,"multi","*"); nod->add(*(yyvsp[(1) - (4)].Nodo)); nod->add(*(yyvsp[(3) - (4)].Nodo)); (yyval.Nodo)=nod;;}
    break;

  case 67:

/* Line 1464 of yacc.c  */
#line 405 "parser.yy"
    {NodoAST *nod = new NodoAST((yylsp[(2) - (4)]).first_line, (yylsp[(2) - (4)]).first_column,"multi","*"); nod->add(*(yyvsp[(2) - (4)].Nodo)); nod->add(*(yyvsp[(4) - (4)].Nodo)); (yyval.Nodo)=nod;;}
    break;

  case 68:

/* Line 1464 of yacc.c  */
#line 406 "parser.yy"
    {(yyval.Nodo) = (yyvsp[(1) - (1)].Nodo);;}
    break;

  case 69:

/* Line 1464 of yacc.c  */
#line 407 "parser.yy"
    {(yyval.Nodo) = (yyvsp[(1) - (1)].Nodo);;}
    break;



/* Line 1464 of yacc.c  */
#line 2290 "parser.cpp"
      default: break;
    }
  YY_SYMBOL_PRINT ("-> $$ =", yyr1[yyn], &yyval, &yyloc);

  YYPOPSTACK (yylen);
  yylen = 0;
  YY_STACK_PRINT (yyss, yyssp);

  *++yyvsp = yyval;
  *++yylsp = yyloc;

  /* Now `shift' the result of the reduction.  Determine what state
     that goes to, based on the state we popped back to and the rule
     number reduced by.  */

  yyn = yyr1[yyn];

  yystate = yypgoto[yyn - YYNTOKENS] + *yyssp;
  if (0 <= yystate && yystate <= YYLAST && yycheck[yystate] == *yyssp)
    yystate = yytable[yystate];
  else
    yystate = yydefgoto[yyn - YYNTOKENS];

  goto yynewstate;


/*------------------------------------.
| yyerrlab -- here on detecting error |
`------------------------------------*/
yyerrlab:
  /* If not already recovering from an error, report this error.  */
  if (!yyerrstatus)
    {
      ++yynerrs;
#if ! YYERROR_VERBOSE
      yyerror (YY_("syntax error"));
#else
      {
	YYSIZE_T yysize = yysyntax_error (0, yystate, yychar);
	if (yymsg_alloc < yysize && yymsg_alloc < YYSTACK_ALLOC_MAXIMUM)
	  {
	    YYSIZE_T yyalloc = 2 * yysize;
	    if (! (yysize <= yyalloc && yyalloc <= YYSTACK_ALLOC_MAXIMUM))
	      yyalloc = YYSTACK_ALLOC_MAXIMUM;
	    if (yymsg != yymsgbuf)
	      YYSTACK_FREE (yymsg);
	    yymsg = (char *) YYSTACK_ALLOC (yyalloc);
	    if (yymsg)
	      yymsg_alloc = yyalloc;
	    else
	      {
		yymsg = yymsgbuf;
		yymsg_alloc = sizeof yymsgbuf;
	      }
	  }

	if (0 < yysize && yysize <= yymsg_alloc)
	  {
	    (void) yysyntax_error (yymsg, yystate, yychar);
	    yyerror (yymsg);
	  }
	else
	  {
	    yyerror (YY_("syntax error"));
	    if (yysize != 0)
	      goto yyexhaustedlab;
	  }
      }
#endif
    }

  yyerror_range[0] = yylloc;

  if (yyerrstatus == 3)
    {
      /* If just tried and failed to reuse lookahead token after an
	 error, discard it.  */

      if (yychar <= YYEOF)
	{
	  /* Return failure if at end of input.  */
	  if (yychar == YYEOF)
	    YYABORT;
	}
      else
	{
	  yydestruct ("Error: discarding",
		      yytoken, &yylval, &yylloc);
	  yychar = YYEMPTY;
	}
    }

  /* Else will try to reuse lookahead token after shifting the error
     token.  */
  goto yyerrlab1;


/*---------------------------------------------------.
| yyerrorlab -- error raised explicitly by YYERROR.  |
`---------------------------------------------------*/
yyerrorlab:

  /* Pacify compilers like GCC when the user code never invokes
     YYERROR and the label yyerrorlab therefore never appears in user
     code.  */
  if (/*CONSTCOND*/ 0)
     goto yyerrorlab;

  yyerror_range[0] = yylsp[1-yylen];
  /* Do not reclaim the symbols of the rule which action triggered
     this YYERROR.  */
  YYPOPSTACK (yylen);
  yylen = 0;
  YY_STACK_PRINT (yyss, yyssp);
  yystate = *yyssp;
  goto yyerrlab1;


/*-------------------------------------------------------------.
| yyerrlab1 -- common code for both syntax error and YYERROR.  |
`-------------------------------------------------------------*/
yyerrlab1:
  yyerrstatus = 3;	/* Each real token shifted decrements this.  */

  for (;;)
    {
      yyn = yypact[yystate];
      if (yyn != YYPACT_NINF)
	{
	  yyn += YYTERROR;
	  if (0 <= yyn && yyn <= YYLAST && yycheck[yyn] == YYTERROR)
	    {
	      yyn = yytable[yyn];
	      if (0 < yyn)
		break;
	    }
	}

      /* Pop the current state because it cannot handle the error token.  */
      if (yyssp == yyss)
	YYABORT;

      yyerror_range[0] = *yylsp;
      yydestruct ("Error: popping",
		  yystos[yystate], yyvsp, yylsp);
      YYPOPSTACK (1);
      yystate = *yyssp;
      YY_STACK_PRINT (yyss, yyssp);
    }

  *++yyvsp = yylval;

  yyerror_range[1] = yylloc;
  /* Using YYLLOC is tempting, but would change the location of
     the lookahead.  YYLOC is available though.  */
  YYLLOC_DEFAULT (yyloc, (yyerror_range - 1), 2);
  *++yylsp = yyloc;

  /* Shift the error token.  */
  YY_SYMBOL_PRINT ("Shifting", yystos[yyn], yyvsp, yylsp);

  yystate = yyn;
  goto yynewstate;


/*-------------------------------------.
| yyacceptlab -- YYACCEPT comes here.  |
`-------------------------------------*/
yyacceptlab:
  yyresult = 0;
  goto yyreturn;

/*-----------------------------------.
| yyabortlab -- YYABORT comes here.  |
`-----------------------------------*/
yyabortlab:
  yyresult = 1;
  goto yyreturn;

#if !defined(yyoverflow) || YYERROR_VERBOSE
/*-------------------------------------------------.
| yyexhaustedlab -- memory exhaustion comes here.  |
`-------------------------------------------------*/
yyexhaustedlab:
  yyerror (YY_("memory exhausted"));
  yyresult = 2;
  /* Fall through.  */
#endif

yyreturn:
  if (yychar != YYEMPTY)
     yydestruct ("Cleanup: discarding lookahead",
		 yytoken, &yylval, &yylloc);
  /* Do not reclaim the symbols of the rule which action triggered
     this YYABORT or YYACCEPT.  */
  YYPOPSTACK (yylen);
  YY_STACK_PRINT (yyss, yyssp);
  while (yyssp != yyss)
    {
      yydestruct ("Cleanup: popping",
		  yystos[*yyssp], yyvsp, yylsp);
      YYPOPSTACK (1);
    }
#ifndef yyoverflow
  if (yyss != yyssa)
    YYSTACK_FREE (yyss);
#endif
#if YYERROR_VERBOSE
  if (yymsg != yymsgbuf)
    YYSTACK_FREE (yymsg);
#endif
  /* Make sure YYID is used.  */
  return YYID (yyresult);
}



/* Line 1684 of yacc.c  */
#line 410 "parser.yy"


