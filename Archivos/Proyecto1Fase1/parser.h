/* A Bison parser, made by GNU Bison 3.3.2.  */

/* Bison interface for Yacc-like parsers in C

   Copyright (C) 1984, 1989-1990, 2000-2015, 2018-2019 Free Software Foundation,
   Inc.

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

/* Undocumented macros, especially those whose name start with YY_,
   are private implementation details.  Do not rely on them.  */

#ifndef YY_YY_PARSER_H_INCLUDED
# define YY_YY_PARSER_H_INCLUDED
/* Debug traces.  */
#ifndef YYDEBUG
# define YYDEBUG 0
#endif
#if YYDEBUG
extern int yydebug;
#endif

/* Token type.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
  enum yytokentype
  {
    entero = 258,
    cadena = 259,
    identificador = 260,
    letra = 261,
    comentario = 262,
    direccion = 263,
    igual = 264,
    mkdisk = 265,
    rmdisk = 266,
    fdisk = 267,
    mount = 268,
    unmount = 269,
    rep = 270,
    exec = 271,
    size = 272,
    fit = 273,
    unit = 274,
    path = 275,
    typep = 276,
    deletep = 277,
    name = 278,
    add = 279,
    id = 280,
    fs = 281,
    usr = 282,
    pwd = 283,
    logout = 284,
    login = 285,
    mkfs = 286,
    mkgrp = 287,
    rmgrp = 288,
    grp = 289,
    mkusr = 290,
    rmusr = 291,
    chmodp = 292,
    ugo = 293,
    r = 294,
    p = 295,
    cont = 296,
    mkfile = 297,
    cat = 298,
    file = 299,
    rem = 300,
    edit = 301,
    ren = 302,
    mkdirp = 303,
    cp = 304,
    mv = 305,
    find = 306,
    chownp = 307,
    chgrp = 308,
    pausep = 309,
    recovery = 310,
    loss = 311
  };
#endif

/* Value type.  */
#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED

union YYSTYPE
{
#line 109 "sintactico.y" /* yacc.c:1921  */

//se especifican los tipo de valores para los no terminales y lo terminales
char TEXT [256];
struct STRCREAR   *SCREAR;
struct STRFORMATO *SFORMA;
struct STRMONTAR  *SMONTA;
struct STRREPORTE *SREPOR;
//FASE2
struct STRSEXT* SEXT;
struct STRSINGRE* SINGRE;
struct STRSUSR* SUSR;
struct STRSPER* SPER;
struct STRSARCH* SARCH;
struct STRSREM* SREM;
struct STRSEDIT* SEDIT;
struct STRSREN* SREN;
struct STRSDIR* SDIR;
struct STRSCOP* SCOP;
struct STRSMOV* SMOV;
struct STRSFIN* SFIN;
struct STRSCHOW* SCHOW;
struct STRSCHG* SCHG;

#line 139 "parser.h" /* yacc.c:1921  */
};

typedef union YYSTYPE YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define YYSTYPE_IS_DECLARED 1
#endif


extern YYSTYPE yylval;

int yyparse (void);

#endif /* !YY_YY_PARSER_H_INCLUDED  */
