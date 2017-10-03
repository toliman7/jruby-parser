// created by jay 1.0.2 (c) 2002-2004 ats@cs.rit.edu
// skeleton Java 1.0 (c) 2002 ats@cs.rit.edu

					// line 2 "Ruby23Parser.y"
/***** BEGIN LICENSE BLOCK *****
 * Version: EPL 1.0/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Common Public
 * License Version 1.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of
 * the License at http://www.eclipse.org/legal/cpl-v10.html
 *
 * Software distributed under the License is distributed on an "AS
 * IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * rights and limitations under the License.
 *
 * Copyright (C) 2008-2009 Thomas E Enebo <enebo@acm.org>
 * 
 * Alternatively, the contents of this file may be used under the terms of
 * either of the GNU General Public License Version 2 or later (the "GPL"),
 * or the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the EPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the EPL, the GPL or the LGPL.
 ***** END LICENSE BLOCK *****/
package org.jrubyparser.parser;

import java.io.IOException;

import org.jrubyparser.ast.ArgsNode;
import org.jrubyparser.ast.ArrayNode;
import org.jrubyparser.ast.AssignableNode;
import org.jrubyparser.ast.BackRefNode;
import org.jrubyparser.ast.BeginNode;
import org.jrubyparser.ast.BlockAcceptingNode;
import org.jrubyparser.ast.BlockArgNode;
import org.jrubyparser.ast.BlockNode;
import org.jrubyparser.ast.BlockPassNode;
import org.jrubyparser.ast.BreakNode;
import org.jrubyparser.ast.CallNode;
import org.jrubyparser.ast.ClassNode;
import org.jrubyparser.ast.ClassVarNode;
import org.jrubyparser.ast.Colon3Node;
import org.jrubyparser.ast.ConstDeclNode;
import org.jrubyparser.ast.DStrNode;
import org.jrubyparser.ast.DSymbolNode;
import org.jrubyparser.ast.DXStrNode;
import org.jrubyparser.ast.DefinedNode;
import org.jrubyparser.ast.DefnNode;
import org.jrubyparser.ast.DefsNode;
import org.jrubyparser.ast.DotNode;
import org.jrubyparser.ast.EnsureNode;
import org.jrubyparser.ast.EvStrNode;
import org.jrubyparser.ast.FCallNode;
import org.jrubyparser.ast.FixnumNode;
import org.jrubyparser.ast.FloatNode;
import org.jrubyparser.ast.ForNode;
import org.jrubyparser.ast.GlobalVarNode;
import org.jrubyparser.ast.HashNode;
import org.jrubyparser.ast.IfNode;
import org.jrubyparser.ast.ImplicitNilNode;
import org.jrubyparser.ast.InstVarNode;
import org.jrubyparser.ast.IterNode;
import org.jrubyparser.ast.LambdaNode;
import org.jrubyparser.ast.ListNode;
import org.jrubyparser.ast.LiteralNode;
import org.jrubyparser.ast.MethodNameNode;
import org.jrubyparser.ast.ModuleNode;
import org.jrubyparser.ast.MultipleAsgnNode;
import org.jrubyparser.ast.NextNode;
import org.jrubyparser.ast.Node;
import org.jrubyparser.ast.NumericNode;
import org.jrubyparser.ast.OpAsgnAndNode;
import org.jrubyparser.ast.OpAsgnNode;
import org.jrubyparser.ast.OpAsgnOrNode;
import org.jrubyparser.ast.OptArgNode;
import org.jrubyparser.ast.PostExeNode;
import org.jrubyparser.ast.PreExe19Node;
import org.jrubyparser.ast.RationalNode;
import org.jrubyparser.ast.RedoNode;
import org.jrubyparser.ast.RegexpNode;
import org.jrubyparser.ast.RescueBodyNode;
import org.jrubyparser.ast.RescueNode;
import org.jrubyparser.ast.RestArgNode;
import org.jrubyparser.ast.RetryNode;
import org.jrubyparser.ast.ReturnNode;
import org.jrubyparser.ast.SClassNode;
import org.jrubyparser.ast.SelfNode;
import org.jrubyparser.ast.StarNode;
import org.jrubyparser.ast.StrNode;
import org.jrubyparser.ast.SymbolNode;
import org.jrubyparser.ast.UnaryCallNode;
import org.jrubyparser.ast.UnnamedRestArgNode;
import org.jrubyparser.ast.UntilNode;
import org.jrubyparser.ast.VAliasNode;
import org.jrubyparser.ast.WhileNode;
import org.jrubyparser.ast.XStrNode;
import org.jrubyparser.ast.YieldNode;
import org.jrubyparser.ast.ZArrayNode;
import org.jrubyparser.ast.ZSuperNode;
import org.jrubyparser.ast.ZYieldNode;
import org.jrubyparser.ast.ILiteralNode;
import org.jrubyparser.IRubyWarnings;
import org.jrubyparser.IRubyWarnings.ID;
import org.jrubyparser.SourcePosition;
import org.jrubyparser.ISourcePositionHolder;
import org.jrubyparser.lexer.LexerSource;
import org.jrubyparser.lexer.Lexer;
import org.jrubyparser.lexer.Lexer.LexState;
import org.jrubyparser.lexer.StrTerm;
import org.jrubyparser.lexer.SyntaxException;
import org.jrubyparser.lexer.SyntaxException.PID;
import org.jrubyparser.lexer.Token;

public class Ruby23Parser implements RubyParser {
    protected ParserSupport19 support;
    protected Lexer lexer;

    public Ruby23Parser() {
        this(new ParserSupport19());
    }

    public Ruby23Parser(ParserSupport19 support) {
        this.support = support;
        lexer = new Lexer("2.3");
        lexer.setParserSupport(support);
        support.setLexer(lexer);
    }

    public void setWarnings(IRubyWarnings warnings) {
        support.setWarnings(warnings);
        lexer.setWarnings(warnings);
    }
					// line 141 "-"
  // %token constants
  public static final int kCLASS = 257;
  public static final int kMODULE = 258;
  public static final int kDEF = 259;
  public static final int kUNDEF = 260;
  public static final int kBEGIN = 261;
  public static final int kRESCUE = 262;
  public static final int kENSURE = 263;
  public static final int kEND = 264;
  public static final int kIF = 265;
  public static final int kUNLESS = 266;
  public static final int kTHEN = 267;
  public static final int kELSIF = 268;
  public static final int kELSE = 269;
  public static final int kCASE = 270;
  public static final int kWHEN = 271;
  public static final int kWHILE = 272;
  public static final int kUNTIL = 273;
  public static final int kFOR = 274;
  public static final int kBREAK = 275;
  public static final int kNEXT = 276;
  public static final int kREDO = 277;
  public static final int kRETRY = 278;
  public static final int kIN = 279;
  public static final int kDO = 280;
  public static final int kDO_COND = 281;
  public static final int kDO_BLOCK = 282;
  public static final int kRETURN = 283;
  public static final int kYIELD = 284;
  public static final int kSUPER = 285;
  public static final int kSELF = 286;
  public static final int kNIL = 287;
  public static final int kTRUE = 288;
  public static final int kFALSE = 289;
  public static final int kAND = 290;
  public static final int kOR = 291;
  public static final int kNOT = 292;
  public static final int kIF_MOD = 293;
  public static final int kUNLESS_MOD = 294;
  public static final int kWHILE_MOD = 295;
  public static final int kUNTIL_MOD = 296;
  public static final int kRESCUE_MOD = 297;
  public static final int kALIAS = 298;
  public static final int kDEFINED = 299;
  public static final int klBEGIN = 300;
  public static final int klEND = 301;
  public static final int k__LINE__ = 302;
  public static final int k__FILE__ = 303;
  public static final int k__ENCODING__ = 304;
  public static final int kDO_LAMBDA = 305;
  public static final int tIDENTIFIER = 306;
  public static final int tFID = 307;
  public static final int tGVAR = 308;
  public static final int tIVAR = 309;
  public static final int tCONSTANT = 310;
  public static final int tCVAR = 311;
  public static final int tLABEL = 312;
  public static final int tCHAR = 313;
  public static final int tUPLUS = 314;
  public static final int tUMINUS = 315;
  public static final int tUMINUS_NUM = 316;
  public static final int tPOW = 317;
  public static final int tCMP = 318;
  public static final int tEQ = 319;
  public static final int tEQQ = 320;
  public static final int tNEQ = 321;
  public static final int tGEQ = 322;
  public static final int tLEQ = 323;
  public static final int tANDOP = 324;
  public static final int tOROP = 325;
  public static final int tMATCH = 326;
  public static final int tNMATCH = 327;
  public static final int tDOT = 328;
  public static final int tDOT2 = 329;
  public static final int tDOT3 = 330;
  public static final int tAREF = 331;
  public static final int tASET = 332;
  public static final int tLSHFT = 333;
  public static final int tRSHFT = 334;
  public static final int tCOLON2 = 335;
  public static final int tCOLON3 = 336;
  public static final int tOP_ASGN = 337;
  public static final int tASSOC = 338;
  public static final int tLPAREN = 339;
  public static final int tLPAREN2 = 340;
  public static final int tRPAREN = 341;
  public static final int tLPAREN_ARG = 342;
  public static final int tLBRACK = 343;
  public static final int tRBRACK = 344;
  public static final int tLBRACE = 345;
  public static final int tLBRACE_ARG = 346;
  public static final int tSTAR = 347;
  public static final int tSTAR2 = 348;
  public static final int tAMPER = 349;
  public static final int tAMPER2 = 350;
  public static final int tTILDE = 351;
  public static final int tPERCENT = 352;
  public static final int tDIVIDE = 353;
  public static final int tPLUS = 354;
  public static final int tMINUS = 355;
  public static final int tLT = 356;
  public static final int tGT = 357;
  public static final int tPIPE = 358;
  public static final int tBANG = 359;
  public static final int tCARET = 360;
  public static final int tLCURLY = 361;
  public static final int tRCURLY = 362;
  public static final int tBACK_REF2 = 363;
  public static final int tSYMBEG = 364;
  public static final int tSTRING_BEG = 365;
  public static final int tXSTRING_BEG = 366;
  public static final int tREGEXP_BEG = 367;
  public static final int tWORDS_BEG = 368;
  public static final int tQWORDS_BEG = 369;
  public static final int tSTRING_DBEG = 370;
  public static final int tSTRING_DVAR = 371;
  public static final int tSTRING_END = 372;
  public static final int tLAMBDA = 373;
  public static final int tLAMBEG = 374;
  public static final int tNTH_REF = 375;
  public static final int tBACK_REF = 376;
  public static final int tSTRING_CONTENT = 377;
  public static final int tINTEGER = 378;
  public static final int tFLOAT = 379;
  public static final int tREGEXP_END = 380;
  public static final int tCOMMENT = 381;
  public static final int tWHITESPACE = 382;
  public static final int tDOCUMENTATION = 383;
  public static final int tLOWEST = 384;
  public static final int tIMAGINARY = 385;
  public static final int tRATIONAL = 386;
  public static final int tSYMBOLS_BEG = 387;
  public static final int tQSYMBOLS_BEG = 388;
  public static final int tDSTAR = 389;
  public static final int tLABEL_END = 390;
  public static final int tSTRING_DEND = 391;
  public static final int tANDDOT = 392;
  public static final int tSTRING = 393;
  public static final int yyErrorCode = 256;

  /** number of final state.
    */
  protected static final int yyFinal = 1;

  /** parser tables.
      Order is mandated by <i>jay</i>.
    */
  protected static final short[] yyLhs = {
//yyLhs 606
    -1,   143,     0,   135,   136,   136,   136,   136,   137,   146,
   137,    36,    35,    37,    37,    37,    37,    43,   147,    43,
   148,    38,    38,    38,    38,    38,    38,    38,    38,    38,
    38,    38,    38,    38,    38,    38,    38,    38,    38,    38,
    38,    38,    38,    38,    31,    31,    39,    39,    39,    39,
    39,    39,    44,    32,    32,    58,    58,   150,   110,   141,
    42,    42,    42,    42,    42,    42,    42,    42,    42,    42,
    42,   111,   111,   122,   122,   112,   112,   112,   112,   112,
   112,   112,   112,   112,   112,    71,    71,    97,    97,   101,
   101,    72,    72,    72,    72,    72,    72,    72,    72,    72,
    77,    77,    77,    77,    77,    77,    77,    77,    77,     6,
     6,    30,    30,    30,     7,     7,     7,     7,     7,   115,
   115,   116,   116,    60,   151,    60,     8,     8,     8,     8,
     8,     8,     8,     8,     8,     8,     8,     8,     8,     8,
     8,     8,     8,     8,     8,     8,     8,     8,     8,     8,
     8,     8,     8,     8,     8,     8,   133,   133,   133,   133,
   133,   133,   133,   133,   133,   133,   133,   133,   133,   133,
   133,   133,   133,   133,   133,   133,   133,   133,   133,   133,
   133,   133,   133,   133,   133,   133,   133,   133,   133,   133,
   133,   133,   133,   133,   133,   133,   133,   133,    40,    40,
    40,    40,    40,    40,    40,    40,    40,    40,    40,    40,
    40,    40,    40,    40,    40,    40,    40,    40,    40,    40,
    40,    40,    40,    40,    40,    40,    40,    40,    40,    40,
    40,    40,    40,    40,    40,    40,    40,    40,    40,    40,
    40,    40,    73,    76,    76,    76,    76,    52,    56,    56,
   125,   125,   125,   125,   125,    50,    50,    50,    50,    50,
   153,    54,   104,   103,   103,    79,    79,    79,    79,    63,
    63,    70,    70,    70,    41,    41,    41,    41,    41,    41,
    41,    41,    41,    41,    41,    41,   154,    41,   155,    41,
    41,    41,    41,    41,    41,    41,    41,    41,    41,    41,
    41,    41,    41,    41,    41,    41,    41,    41,   157,   159,
    41,   160,   161,    41,    41,    41,   162,   163,    41,   164,
    41,   166,   167,    41,   168,    41,   169,    41,   170,   171,
    41,    41,    41,    41,    41,    45,   156,   156,   156,   158,
   158,    48,    48,    46,    46,   124,   124,   126,   126,    84,
    84,   127,   127,   127,   127,   127,   127,   127,   127,   127,
    91,    91,    91,    91,    90,    90,    67,    67,    67,    67,
    67,    67,    67,    67,    67,    67,    67,    67,    67,    67,
    67,    69,    69,    68,    68,    68,   119,   119,   118,   118,
   128,   128,   172,   121,    66,    66,   120,   120,   173,   109,
    57,    57,    57,    57,    23,    23,    23,    23,    23,    23,
    23,    23,    23,   174,   108,   175,   108,    74,    47,    47,
   113,   113,    75,    75,    75,    49,    49,    51,    51,    28,
    28,    28,    15,    16,    16,    16,    16,    17,    18,    19,
    25,    25,    81,    81,    27,    27,    87,    87,    85,    85,
    26,    26,    88,    88,    80,    80,    86,    86,    20,    20,
    21,    21,    24,    24,    22,   176,    22,   177,    22,    61,
    61,    61,    61,     2,     1,     1,     1,     1,    29,    33,
    33,    34,    34,    34,    34,   129,   129,   129,   129,   129,
   130,   130,   130,   130,   130,   130,   130,    55,    55,   114,
   114,    62,    62,    53,   178,    53,    53,    65,    65,    92,
    92,    92,    92,    89,    89,    64,    64,    64,    64,    64,
    64,    64,    64,    64,    64,    64,    64,    64,    64,    64,
   134,   134,   134,   134,     9,     9,   117,   117,    82,    82,
   140,    93,    93,    94,    95,    95,    96,    96,   138,   138,
   139,    59,   123,   102,   102,    83,    83,    11,    11,    13,
    13,    12,    12,   107,   106,   106,    14,   179,    14,   100,
   100,    98,    98,    99,    99,    99,    99,     3,     3,     3,
     4,     4,     4,     4,     5,     5,     5,    10,    10,   142,
   142,   144,   144,   149,   149,   131,   132,   152,   152,   152,
   165,   165,   145,   145,    78,   105,
    }, yyLen = {
//yyLen 606
     2,     0,     2,     2,     1,     1,     3,     2,     1,     0,
     5,     4,     2,     1,     1,     3,     2,     1,     0,     5,
     0,     4,     3,     3,     3,     2,     3,     3,     3,     3,
     3,     4,     1,     3,     3,     6,     5,     5,     5,     5,
     3,     3,     3,     1,     3,     3,     1,     3,     3,     3,
     2,     1,     1,     1,     1,     1,     4,     0,     5,     1,
     2,     3,     4,     5,     4,     5,     2,     2,     2,     2,
     2,     1,     3,     1,     3,     1,     2,     3,     5,     2,
     4,     2,     4,     1,     3,     1,     3,     2,     3,     1,
     3,     1,     1,     4,     3,     3,     3,     3,     2,     1,
     1,     1,     4,     3,     3,     3,     3,     2,     1,     1,
     1,     2,     1,     3,     1,     1,     1,     1,     1,     1,
     1,     1,     1,     1,     0,     4,     1,     1,     1,     1,
     1,     1,     1,     1,     1,     1,     1,     1,     1,     1,
     1,     1,     1,     1,     1,     1,     1,     1,     1,     1,
     1,     1,     1,     1,     1,     1,     1,     1,     1,     1,
     1,     1,     1,     1,     1,     1,     1,     1,     1,     1,
     1,     1,     1,     1,     1,     1,     1,     1,     1,     1,
     1,     1,     1,     1,     1,     1,     1,     1,     1,     1,
     1,     1,     1,     1,     1,     1,     1,     1,     3,     5,
     3,     5,     6,     5,     5,     5,     5,     4,     3,     3,
     3,     3,     3,     3,     3,     3,     3,     4,     2,     2,
     3,     3,     3,     3,     3,     3,     3,     3,     3,     3,
     3,     3,     3,     2,     2,     3,     3,     3,     3,     3,
     6,     1,     1,     1,     2,     4,     2,     3,     1,     1,
     1,     1,     2,     4,     2,     1,     2,     2,     4,     1,
     0,     2,     2,     2,     1,     1,     2,     3,     4,     1,
     1,     3,     4,     2,     1,     1,     1,     1,     1,     1,
     1,     1,     1,     1,     1,     3,     0,     3,     0,     4,
     3,     3,     2,     3,     3,     1,     4,     3,     1,     5,
     4,     3,     2,     1,     2,     2,     6,     6,     0,     0,
     7,     0,     0,     7,     5,     4,     0,     0,     9,     0,
     6,     0,     0,     8,     0,     5,     0,     6,     0,     0,
     9,     1,     1,     1,     1,     1,     1,     1,     2,     1,
     1,     1,     5,     1,     2,     1,     1,     1,     3,     1,
     3,     1,     4,     6,     3,     5,     2,     4,     1,     3,
     4,     2,     2,     1,     2,     0,     6,     8,     4,     6,
     4,     2,     6,     2,     4,     6,     2,     4,     2,     4,
     1,     1,     1,     3,     1,     4,     1,     4,     1,     3,
     1,     1,     0,     3,     4,     1,     3,     3,     0,     5,
     2,     4,     5,     5,     2,     4,     4,     3,     3,     3,
     2,     1,     4,     0,     5,     0,     5,     5,     1,     1,
     6,     0,     1,     1,     1,     2,     1,     2,     1,     1,
     1,     1,     1,     1,     1,     1,     2,     3,     3,     3,
     3,     3,     0,     3,     1,     2,     3,     3,     0,     3,
     3,     3,     3,     3,     0,     3,     0,     3,     0,     2,
     0,     2,     0,     2,     1,     0,     3,     0,     4,     1,
     1,     1,     1,     2,     1,     1,     1,     1,     3,     1,
     2,     1,     1,     1,     1,     1,     1,     1,     1,     1,
     1,     1,     1,     1,     1,     1,     1,     1,     1,     1,
     1,     1,     1,     1,     0,     4,     2,     3,     2,     4,
     2,     2,     1,     2,     0,     6,     8,     4,     6,     4,
     6,     2,     4,     6,     2,     4,     2,     4,     1,     0,
     1,     1,     1,     1,     1,     1,     1,     3,     1,     3,
     1,     2,     1,     2,     1,     3,     1,     3,     1,     1,
     2,     3,     3,     1,     3,     1,     3,     1,     1,     2,
     1,     1,     1,     2,     2,     0,     1,     0,     4,     1,
     2,     1,     3,     3,     2,     4,     2,     1,     1,     1,
     1,     1,     1,     1,     1,     1,     1,     1,     1,     1,
     1,     0,     1,     0,     1,     2,     2,     0,     1,     1,
     1,     1,     1,     2,     0,     0,
    }, yyDefRed = {
//yyDefRed 1040
     1,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     0,     0,   308,   311,     0,     0,     0,   333,   334,     0,
     0,     0,   491,   490,   492,   493,     0,     0,     0,     9,
     0,   495,   494,   496,     0,     0,   487,   486,     0,   489,
   433,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     0,     0,     0,   458,   460,   462,     0,     0,   392,   501,
   502,   481,   482,   484,   483,     0,     0,   434,   430,    59,
   275,     0,   435,   276,   277,     0,   278,   279,   274,   431,
    32,    46,   429,   479,     0,     0,     0,     0,     0,     0,
   282,     0,    54,     0,     0,    85,     0,     4,   280,   281,
     0,     0,    71,     0,     0,     0,     2,     0,     5,     0,
     7,   331,   332,   295,     0,     0,     0,     0,     0,     0,
     0,     0,     0,     0,   112,     0,   335,     0,   283,   497,
   498,     0,   324,   166,   177,   167,   190,   163,   183,   173,
   172,   188,   171,   170,   165,   191,   175,   164,   178,   182,
   184,   176,   169,   185,   192,   187,     0,     0,     0,     0,
   162,   181,   180,   193,   194,   195,   196,   197,   161,   168,
   159,   160,     0,     0,     0,     0,   116,     0,   151,   152,
   148,   129,   130,   131,   138,   135,   137,   132,   133,   153,
   154,   139,   140,   567,   145,   144,   128,   150,   147,   146,
   142,   143,   136,   134,   126,   149,   127,   155,   141,   326,
   117,     0,   566,   118,   186,   179,   189,   174,   156,   157,
   158,   114,   115,   120,   119,   122,     0,   121,   123,     0,
     0,     0,     0,     0,     0,    14,    13,     0,     0,     0,
     0,     0,     0,     0,     0,     0,     0,   600,   601,     0,
     0,     0,   602,     0,     0,     0,     0,     0,   345,   346,
     0,     0,     0,     0,     0,     0,     0,   458,     0,     0,
   255,    69,     0,     0,     0,   571,   259,    70,    68,     0,
    67,     0,     0,   410,    66,     0,   594,     0,     0,    20,
     0,     0,     0,   218,     0,     0,     0,     0,     0,     0,
     0,     0,     0,     0,     0,   243,     0,     0,     0,   569,
     0,     0,     0,     0,     0,     0,     0,     0,     0,   234,
    50,   233,   476,   475,   477,   473,   474,     0,     0,     0,
     0,     0,     0,     0,     0,   305,     0,     0,     0,     0,
     0,   436,   415,   413,   304,     0,     0,     0,     0,     0,
     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     0,     0,     0,     0,     0,     0,     0,     0,   398,   400,
   589,     0,   590,     0,     0,   587,   588,     0,     0,    87,
     0,     0,     0,     0,     0,     0,     3,     0,   404,     0,
   302,     0,   480,     0,   109,     0,   111,     0,   504,   319,
   503,     0,     0,     0,     0,     0,     0,   328,   124,     0,
     0,     0,     0,   285,    12,     0,     0,   337,     0,     0,
     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
   603,     0,     0,     0,     0,     0,     0,   316,   574,   266,
   262,     0,   576,     0,     0,   256,   264,     0,   257,     0,
   297,     0,   261,   251,   250,     0,     0,     0,     0,   301,
    49,    22,    24,    23,     0,     0,     0,     0,     0,     0,
     0,     0,     0,     0,   290,     0,     0,   287,   293,     0,
   598,   244,     0,   246,   570,   294,     0,    89,     0,     0,
     0,     0,     0,   467,   465,   478,   464,   461,   437,   459,
   438,   439,   463,   440,   441,   444,     0,   450,   451,     0,
   535,   532,   531,   530,   533,   540,   548,     0,     0,   558,
   557,   562,   561,   549,     0,     0,     0,     0,   555,   395,
     0,     0,     0,   528,   546,     0,   512,   538,   534,     0,
     0,     0,   446,   447,     0,   452,   453,     0,     0,     0,
    26,    27,    28,    29,    30,    47,    48,     0,     0,     0,
     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     0,     0,     0,     0,     0,     0,     0,     0,   407,     0,
   409,     0,     0,   582,     0,     0,   583,   408,   580,   581,
     0,    40,     0,     0,    45,    44,     0,    41,   265,     0,
     0,     0,     0,     0,    88,    33,    42,   269,     0,    34,
     0,     6,    57,    61,     0,   506,     0,     0,     0,     0,
     0,   113,     0,     0,     0,     0,     0,     0,     0,     0,
     0,     0,   423,     0,     0,   424,     0,     0,   343,    15,
     0,   338,     0,     0,     0,     0,     0,     0,     0,     0,
     0,   315,   340,   309,   339,   312,     0,     0,     0,     0,
     0,     0,     0,   573,     0,     0,     0,   263,   572,   296,
   595,     0,     0,   247,   300,    21,     0,     0,    31,     0,
     0,     0,   289,     0,     0,     0,     0,     0,     0,     0,
     0,     0,     0,   443,   445,   455,     0,     0,   347,     0,
   349,     0,     0,     0,   559,   563,     0,   526,     0,     0,
   393,     0,   521,     0,   524,     0,   510,   550,     0,   511,
   541,   449,   457,   384,     0,   382,     0,   381,     0,     0,
     0,     0,     0,   406,     0,     0,     0,     0,     0,   249,
     0,   405,   248,     0,     0,     0,     0,     0,     0,    86,
     0,     0,     0,     0,   322,     0,     0,   412,   325,   568,
     0,   508,     0,   329,   125,     0,     0,     0,   426,   344,
     0,    11,   428,     0,   341,     0,     0,     0,     0,     0,
     0,     0,   314,     0,     0,     0,     0,     0,     0,   575,
   268,   258,     0,   299,    10,   245,    90,     0,     0,   469,
   470,   471,   466,   472,     0,     0,     0,     0,   537,     0,
     0,   551,   536,     0,   513,     0,     0,     0,     0,   539,
     0,   556,     0,   547,   564,     0,     0,     0,     0,     0,
     0,   380,   544,     0,     0,   363,     0,   553,     0,     0,
     0,     0,     0,    39,     0,    38,     0,    65,     0,   596,
    36,     0,    37,     0,    63,   403,   402,     0,     0,     0,
     0,     0,     0,     0,   505,   320,   507,   327,     0,    19,
     0,     0,     0,   425,     0,     0,     0,   427,     0,   306,
     0,   307,   267,     0,     0,     0,   317,     0,   468,   348,
     0,     0,     0,   350,   394,     0,     0,   527,   397,   396,
     0,   519,     0,   517,     0,   522,   525,   509,     0,     0,
     0,     0,   378,     0,     0,   373,     0,   361,     0,   376,
   383,   362,   416,   414,     0,   399,    35,     0,     0,     0,
     0,     0,     0,     0,     0,     0,     0,     0,   418,   417,
   419,   310,   313,     0,     0,     0,     0,     0,   390,     0,
   388,   391,     0,     0,     0,     0,     0,   292,     0,     0,
   364,   385,     0,     0,   545,     0,     0,     0,   554,    58,
   323,     0,     0,     0,     0,     0,     0,   420,     0,     0,
     0,     0,     0,   387,   520,     0,   515,   518,   523,     0,
     0,     0,   379,     0,   370,     0,   368,   360,     0,   374,
   377,   330,     0,   342,   318,     0,   389,     0,     0,     0,
     0,     0,   516,   372,     0,   366,   369,   375,     0,   367,
    }, yyDgoto = {
//yyDgoto 180
     1,   325,    68,    69,   640,   598,   124,   224,   599,   832,
   387,   535,   536,   537,   211,    70,    71,    72,    73,    74,
   328,   327,   507,    75,   330,    76,    77,   516,    78,    79,
   125,    80,    81,    82,    83,   231,   232,   233,   234,    85,
    86,    87,    88,   235,   239,   294,   794,   959,   795,   787,
   463,   791,   600,   409,   280,    90,   761,    91,    92,   538,
   226,   822,   241,   626,   647,   648,   540,   849,   745,   746,
   617,    94,    95,   272,   439,   654,   304,   242,   236,   465,
   334,   332,   541,   542,   719,   338,   340,    98,    99,   727,
   932,   980,   834,   544,   852,   853,   545,   100,   466,   275,
   311,   498,   854,   455,   276,   456,   736,   546,   400,   379,
   633,   101,   102,   422,   243,   227,   228,   547,   969,   829,
   730,   335,   301,   857,   260,   467,   720,   721,   970,   244,
   245,   460,   755,   213,   548,   106,   107,   108,   549,   550,
   551,   131,   413,     2,   250,   251,   291,   420,   474,   461,
   773,   650,   491,   281,   303,   486,   428,   253,   673,   805,
   254,   806,   681,   963,   637,   429,   634,   883,   414,   416,
   649,   888,   336,   593,   559,   558,   712,   711,   636,   415,
    }, yySindex = {
//yySindex 1040
     0,     0, 15178, 15591, 18255, 18654, 19190, 19512, 15316, 17723,
 17723, 10344,     0,     0, 18388, 15724, 15724,     0,     0, 15724,
  -191,  -170,     0,     0,     0,     0,    98, 19404,   220,     0,
  -120,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     0, 17856, 17856,   511,   -57, 15454, 17723, 16260, 16659,  3941,
 17856, 17989, 19297,     0,     0,     0,   234,   253,     0,     0,
     0,     0,     0,     0,     0,   281,   293,     0,     0,     0,
     0,   -30,     0,     0,     0,  -144,     0,     0,     0,     0,
     0,     0,     0,     0,  1838,   346,  5826,     0,    59,   527,
     0,    34,     0,    43,   319,     0,   322,     0,     0,     0,
 18521,   333,     0,    85,     0,     0,     0,   157,     0,  -114,
     0,     0,     0,     0,  -191,  -170,    62,   220,     0,     0,
   511, 17723,   -96, 15316,     0,   133,     0,   627,     0,     0,
     0,  -114,     0,     0,     0,     0,     0,     0,     0,     0,
     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     0,    34,     0,     0,     0,     0,     0,     0,     0,     0,
     0,     0,     0,     0,     0,     0,   389,     0,     0, 15861,
 15316,   185,   217,   157,  1838,     0,     0,   143,   346,   129,
   645,   167,   433,   176,     0,     0,   129,     0,     0,   157,
   258,   520,     0, 17723, 17723,   240,   688,     0,     0,     0,
   303,     0,     0, 17856, 17856, 17856, 17856,     0, 17856,  5826,
     0,     0,   284,   575,   584,     0,     0,     0,     0,  4448,
     0, 15724, 15724,     0,     0,  3434,     0, 17723,   -73,     0,
 16792,   287, 15316,     0,   767,   326,   352,   386, 15454,   347,
     0,   220,   346,   220,   395,     0,   245,   267,   284,     0,
   267,   387,   441, 18787,   939,     0,   709,     0,     0,     0,
     0,     0,     0,     0,     0,     0,     0,   888,  1180,  1195,
   255,   384,  1269,   390,   315,     0,  2251,   403,  1272,   405,
   331,     0,     0,     0,     0, 17723, 17723, 17723, 17723, 15861,
 17723, 17723, 17856, 17856, 17856, 17856, 17856, 17856, 17856, 17856,
 17856, 17856, 17856, 17856, 17856, 17856, 17856, 17856, 17856, 17856,
 17856, 17856, 17856, 17856, 17856, 17856, 17856, 17856,     0,     0,
     0,  2801,     0, 15724,  3308,     0,     0, 20033, 17989,     0,
 16925, 15454,  4955,   734, 16925, 17989,     0,  6392,     0,   438,
     0,   454,     0,   346,     0,     0,     0,   157,     0,     0,
     0,  3815, 15724,  4322, 15316, 17723,  2876,     0,     0,  1838,
   432, 17058,   531,     0,     0, 15040,   386,     0, 15316,   540,
  5464, 15724,  5971, 17856, 17856, 17856, 15316,   258, 17191,   556,
     0,   135,   135,     0,  6847, 15724, 19679,     0,     0,     0,
     0,   291,     0, 17856, 15994,     0,     0, 16393,     0,   220,
     0,   482,     0,     0,     0,   782,   786,   220,   232,     0,
     0,     0,     0,     0, 19512, 17723,  5826, 15178,   476,  5464,
  5971, 17856, 17856,   220,     0,     0,   220,     0,     0, 16526,
     0,     0, 16659,     0,     0,     0,     0,     0,   802, 19738,
 15724, 19797, 18787,     0,     0,     0,     0,     0,     0,     0,
     0,     0,     0,     0,     0,     0,   163,     0,     0,   815,
     0,     0,     0,     0,     0,     0,     0,  1466,  2939,     0,
     0,     0,     0,     0,   788,   545,   547,   816,     0,     0,
  -137,   822,   832,     0,     0,   843,     0,     0,     0,   589,
   855, 17856,     0,     0,   222,     0,     0,   881,  -179,  -179,
     0,     0,     0,     0,     0,     0,     0,   326,  2456,  2456,
  2456,  2456,  1571,  1571,  6786,  6487,  2456,  2456,  2977,  2977,
  1473,  1473,   326,  2405,   326,   326,   453,   453,  1571,  1571,
  2042,  2042,  4837,  -179,   579,     0,   583,  -170,     0,     0,
     0,   220,   587,     0,   590,  -170,     0,     0,     0,     0,
  -170,     0,  5826, 17856,     0,     0,  5383,     0,     0,   861,
   885,   220, 18787,   891,     0,     0,     0,     0,     0,     0,
  5890,     0,     0,     0,   157,     0, 17723, 15316,     0,     0,
  -170,     0,   220,  -170,   668,   232,  2939,   157, 15316, 19620,
 19512, 15178,     0,     0,   598,     0, 15316,   678,     0,     0,
   382,     0,   608,   617,   220,   630,   631,  5383,   531,   684,
   302,     0,     0,     0,     0,     0,     0,     0,   220,     0,
     0, 17723, 17856,     0, 17856,   284,   584,     0,     0,     0,
     0, 15994, 16393,     0,     0,     0,   232,   599,     0,   326,
  5826,     0,     0,   267, 18787,     0,     0,   220,     0,     0,
   802, 15316,  1081,     0,     0,     0,  1466,   526,     0,   920,
     0,   220,   220, 17856,     0,     0,  3455,     0, 15316, 15316,
     0,  2939,     0,  2939,     0,   932,     0,     0,   351,     0,
     0,     0,     0,     0,  1638,     0, 15316,     0, 15316,   912,
 15316, 17989, 17989,     0,   438,   634,   641, 17989, 17989,     0,
   438,     0,     0,    59,  -144,     0, 17856, 17989, 17324,     0,
   802, 18787, 17856,  -179,     0,   157,   710,     0,     0,     0,
   220,     0,   723,     0,     0,   629, 18920,   129,     0,     0,
 15316,     0,     0, 17723,     0,   733, 17856, 17856,   671, 17856,
 17856,   745,     0, 17457, 15316, 15316, 15316,     0,   135,     0,
     0,     0,   967,     0,     0,     0,     0,     0,   650,     0,
     0,     0,     0,     0,   220,  1104,   971,  1575,     0,   676,
   961,     0,     0,   978,     0,   760,   664,   983,   988,     0,
   989,     0,   978,     0,     0,   855,   977, 19053,   991,   220,
   995,     0,     0,  1006,  1007,     0,   695,     0,   855,   791,
   702, 17856,   803,     0,  5826,     0,  5826,     0, 17989,     0,
     0,  5826,     0,  5826,     0,     0,     0,  5826, 17856,     0,
   802,  5826, 15316, 15316,     0,     0,     0,     0,  2876,     0,
   768,   974,     0,     0,     0,     0, 15316,     0,   129,     0,
 17856,     0,     0,   340,   806,   825,     0, 16393,     0,     0,
  1041,  1104,  1087,     0,     0,  1528,  3455,     0,     0,     0,
  3455,     0,  2939,     0,  3455,     0,     0,     0, 19053,   794,
  1171,  3871,     0,   736,  3366,     0,  1013,     0,  3366,     0,
     0,     0,     0,     0,  5826,     0,     0,  5826,     0,   744,
   853, 15316,     0, 19856, 15724, 19915,   185, 15316,     0,     0,
     0,     0,     0, 15316,  1104,  1041,  1104,  1063,     0,   296,
     0,     0,   978,  1075,   978,   978,  1171,     0, 19974,  1076,
     0,     0,  1079,  1082,     0,   855,  1092,  1076,     0,     0,
     0,   873,     0,     0,   220,     0,     0,     0,   382,   874,
  1041,  1104,  1528,     0,     0,  3455,     0,     0,     0,     0,
     0,  3871,     0,  3871,     0,  3366,     0,     0,  3871,     0,
     0,     0,     0,     0,     0,  1041,     0,   978,  1076,  1098,
  1076,  1076,     0,     0,  3871,     0,     0,     0,  1076,     0,
    }, yyRindex = {
//yyRindex 1040
     0,     0,   309,     0,     0,     0,     0,     0,  1350,     0,
     0,   854,     0,     0,     0,  8381,  8512,     0,     0,  8621,
  5246,  4739,     0,     0,     0,     0, 18122,     0, 17590,     0,
     0,     0,     0,     0,  2573,  3725,     0,     0,  2711,     0,
     0,     0,     0,     0,     0,   189,   123,   801,   789,    37,
     0,     0,  1313,     0,     0,     0,  1536,   344,     0,     0,
     0,     0,     0,     0,     0,  1622,   358,     0,     0,     0,
     0,  7792,     0,     0,     0,  7901,     0,     0,     0,     0,
     0,     0,     0,     0,   103, 12443, 14782,  8032, 14846,     0,
     0, 14886,     0,  8992,     0,     0,     0,     0,     0,     0,
   260,     0,     0,     0,  2172,  8861,     0,    56,     0, 16127,
     0,     0,     0,     0,  8141,  7072,     0,   808,  9212,  9350,
     0,     0,     0,   189,     0,     0,     0,     0,     0,     0,
     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     0,     0,     0,     0,     0,     0,  1287,  1420,  1483,  1552,
     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     0,     0,  1776,  2239,  2310,  2396,     0,  2894,     0,     0,
     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     0,     0,     0,     0,     0,     0, 12754,     0,     0,     0,
   113,    54,     0,  1401,  1213,     0,     0,     0, 14978,     0,
     0,  7552,     0,     0,  7176,  7312,     0,     0,     0,   854,
     0,   875,     0,     0,     0,     0,     0,   417,     0,     0,
     0,   945,  1326,     0,     0,     0,     0,     0,     0, 14189,
     0,     0, 14417, 14539, 14539,     0,     0,     0,     0,   811,
     0,     0,   203,     0,     0,   811,     0,     0,     0,     0,
     0,     0,   106,     0,     0, 10501,  8272,  9101,   189,     0,
   164,   811,   236,   811,     0,     0,   826,   826,     0,     0,
   793,     0,     0,     0,     0,   475,   289,   949,  1192,     0,
     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     0,     0,     0,     0,     0,     0,  -130,     0,     0,     0,
     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     0,     0,     0,    51,     0,     0,     0,     0,     0,     0,
     0,   189,   624,   649,     0,     0,     0,    65,     0, 14576,
     0,     0,     0,   201,     0,  9792,     0,     0,     0,     0,
     0,     0,    51,     0,  1350,     0,   290,     0,     0,  1246,
     0,   118,   505,     0,     0,  1440,  7661,     0,  1409,  9930,
     0,    51,     0,     0,     0,     0,   715,     0,     0,     0,
     0,     0,     0,  1359,     0,    51,     0,     0,     0,     0,
     0,     0,     0,     0,     0,     0,     0,     0,     0,   811,
     0,     0,     0,     0,     0,    71,    71,   811,   811,     0,
     0,     0,     0,     0,     0,     0, 13243,   106,     0,     0,
     0,     0,     0,   811,     0,   100,   811,     0,     0,   828,
     0,     0,  -154,     0,     0,     0,  1194,     0,   781,     0,
    51,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     0,     0,     0,     0,     0,     0,     0,     0,   219,     0,
     0,     0,     0,     0,    68,    67,     0,   223,     0,     0,
     0,   223,   223,     0,     0,   246,     0,     0,     0,     0,
   246,   147,     0,     0,     0,     0,     0,     0, 10073, 10211,
     0,     0,     0,     0,     0,     0,     0, 10634, 12427, 12535,
 12620, 12716, 11982, 12090, 12812, 13113, 12930, 13026,  6983, 13203,
 11416, 11524, 10742, 11645, 10850, 10971, 11187, 11308, 12198, 12319,
 11753, 11861,  1102, 10073,  5607,  4095,  6251, 16127,     0,  4232,
     0,   836,  5744,     0,  6114,  5109,     0,     0,     0,     0,
  6612,     0, 13339,     0,     0,     0,  1806,     0,     0,     0,
     0,   811,     0,   787,     0,     0,     0,     0, 12237,     0,
 14247,     0,     0,     0,     0,     0,     0,  1350,  9502,  9640,
     0,     0,   836,  7421,     0,   811,   242,     0,  1350,     0,
     0,   106,     0,   585,   555,     0,   611,   902,     0,     0,
   902,     0,  3081,  4602,   836,  3218,  3588, 14332,   902,     0,
     0,     0,     0,     0,     0,     0,  1059,  1290,   836,  1663,
  1972,     0,     0,     0,     0, 14454, 14539,     0,     0,     0,
     0,   194,   205,     0,     0,     0,   811,     0,     0, 11079,
 13424,   132,     0,   826,     0,   821,   721,   836,   860,  1008,
   817,   106,     0,     0,     0,     0,     0,   249,     0,   259,
     0,   811,    45,     0,     0,     0,     0,     0,   150,   106,
     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     0,     0,     0,     0,    30,     0,   150,     0,   106,     0,
   150,     0,     0,     0, 14631,  8752,     0,     0,     0,     0,
 14672,     0,     0, 14942, 14727,  2120,     0,     0,     0,     0,
   833,     0,     0, 10211,     0,     0,     0,     0,     0,     0,
   811,     0,     0,     0,     0,     0,     0,     0,     0,     0,
   150,     0,     0,     0,     0,     0,     0,     0,  6740,     0,
     0,     0,     0,     0,  1071,   150,   150,   493,     0,     0,
     0,     0,    71,     0,     0,     0,     0,  1044,     0,     0,
     0,     0,     0,     0,   811,     0,   261,     0,     0,     0,
  -138,     0,     0,   223,     0,     0,     0,   223,   223,     0,
   223,     0,   223,     0,     0,   246,    97,     0,   112,    30,
   112,     0,     0,   114,   112,     0,     0,     0,   114,     0,
     0,     0,     0,     0, 13509,     0, 13594,     0,     0,     0,
     0, 13679,     0, 13764,     0,     0,     0, 13849,     0,  2313,
   878, 13934,   106,  1350,     0,     0,     0,     0,   290,     0,
     0,     0,   224,     0,   551,   966,  1350,     0,     0,     0,
     0,     0,     0,   902,     0,     0,     0,   214,     0,     0,
   263,     0,   266,     0,     0,     0,     0,     0,     0,     0,
     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
   119,     0,     0,     0,   125,     0,     0,     0,     0,     0,
     0,     0,     0,     0, 14019,     0,     0, 14104, 11900,     0,
     0,  1350,   972,     0,    51,     0,    54,  1409,     0,     0,
     0,     0,     0,   150,     0,   271,     0,   274,     0,   -10,
     0,     0,   223,   223,   223,   223,   137,     0,     0,   112,
     0,     0,   112,   112,     0,   114,   112,   112,     0,     0,
     0,     0,   667,   213,   836,  1088,  1124,     0,   902,     0,
   282,     0,     0,     0,     0,     0,     0,     0,     0,   849,
  1134,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     0,     0,  1106,     0,     0,   283,     0,   223,   112,   112,
   112,   112,     0,     0,     0,     0,     0,     0,   112,     0,
    }, yyGindex = {
//yyGindex 180
     0,     0,    26,     0,  -282,     0,   -74,    15,    44,  -326,
   975,     0,     0,   -77,     0,     0,     0,  1119,     0,     0,
   924,  1140,  1211,     0,     0,     0,     0,   867,     0,    52,
  1198,  -374,   -24,     0,   116,   152,  -380,     0,    38,   900,
  1920,    25,    47,   774,    -3,     9,  -407,     0,   208,     0,
  1148,     0,    74,     0,   -16,  1202,   601,     0,     0,  -648,
     0,     0,   784,     0,  -279,   328,     0,     0,     0,  -475,
  -209,   -91,    21,  1716,  -406,     0,     0,  1261,    -2,    72,
     0,     0,  5928,   483,  -671,     0,     0,     0,     0,    31,
   865,   473,  -310,   487,   294,     0,     0,     0,    53,  -382,
     0,  -257,   292,  -238,  -405,     0,  -506,  1306,   -69,   455,
  -640,  1215,   141,   275,    35,     0,   -23,  -637,     0,  -662,
     0,     0,  -160,  -783,     0,  -348,  -706,   516,   233,  1069,
  1308,   447,  -527,     0,  -781,  -337,     0,    39,     0,  1414,
     0,   696,   -87,     0,   491,    10,     0,     0,     0,   -25,
     0,     0,  -234,     0,     0,     0,  -224,     0,  -410,     0,
     0,     0,     0,     0,     0,    75,     0,     0,     0,     0,
     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
    };
    protected static final short[] yyTable = Ruby23YyTables.yyTable();
    protected static final short[] yyCheck = Ruby23YyTables.yyCheck();

  /** maps symbol value to printable name.
      @see #yyExpecting
    */
  protected static final String[] yyNames = {
    "end-of-file",null,null,null,null,null,null,null,null,null,"'\\n'",
    null,null,null,null,null,null,null,null,null,null,null,null,null,null,
    null,null,null,null,null,null,null,"' '",null,null,null,null,null,
    null,null,null,null,null,null,"','",null,null,null,null,null,null,
    null,null,null,null,null,null,null,"':'","';'",null,"'='",null,"'?'",
    null,null,null,null,null,null,null,null,null,null,null,null,null,null,
    null,null,null,null,null,null,null,null,null,null,null,null,null,
    "'['",null,null,null,null,null,null,null,null,null,null,null,null,
    null,null,null,null,null,null,null,null,null,null,null,null,null,null,
    null,null,null,null,null,null,null,null,null,null,null,null,null,null,
    null,null,null,null,null,null,null,null,null,null,null,null,null,null,
    null,null,null,null,null,null,null,null,null,null,null,null,null,null,
    null,null,null,null,null,null,null,null,null,null,null,null,null,null,
    null,null,null,null,null,null,null,null,null,null,null,null,null,null,
    null,null,null,null,null,null,null,null,null,null,null,null,null,null,
    null,null,null,null,null,null,null,null,null,null,null,null,null,null,
    null,null,null,null,null,null,null,null,null,null,null,null,null,null,
    null,null,null,null,null,null,null,null,null,null,null,null,null,null,
    null,null,null,null,null,null,null,null,null,null,null,null,null,
    "kCLASS","kMODULE","kDEF","kUNDEF","kBEGIN","kRESCUE","kENSURE",
    "kEND","kIF","kUNLESS","kTHEN","kELSIF","kELSE","kCASE","kWHEN",
    "kWHILE","kUNTIL","kFOR","kBREAK","kNEXT","kREDO","kRETRY","kIN",
    "kDO","kDO_COND","kDO_BLOCK","kRETURN","kYIELD","kSUPER","kSELF",
    "kNIL","kTRUE","kFALSE","kAND","kOR","kNOT","kIF_MOD","kUNLESS_MOD",
    "kWHILE_MOD","kUNTIL_MOD","kRESCUE_MOD","kALIAS","kDEFINED","klBEGIN",
    "klEND","k__LINE__","k__FILE__","k__ENCODING__","kDO_LAMBDA",
    "tIDENTIFIER","tFID","tGVAR","tIVAR","tCONSTANT","tCVAR","tLABEL",
    "tCHAR","tUPLUS","tUMINUS","tUMINUS_NUM","tPOW","tCMP","tEQ","tEQQ",
    "tNEQ","tGEQ","tLEQ","tANDOP","tOROP","tMATCH","tNMATCH","tDOT",
    "tDOT2","tDOT3","tAREF","tASET","tLSHFT","tRSHFT","tCOLON2","tCOLON3",
    "tOP_ASGN","tASSOC","tLPAREN","tLPAREN2","tRPAREN","tLPAREN_ARG",
    "tLBRACK","tRBRACK","tLBRACE","tLBRACE_ARG","tSTAR","tSTAR2","tAMPER",
    "tAMPER2","tTILDE","tPERCENT","tDIVIDE","tPLUS","tMINUS","tLT","tGT",
    "tPIPE","tBANG","tCARET","tLCURLY","tRCURLY","tBACK_REF2","tSYMBEG",
    "tSTRING_BEG","tXSTRING_BEG","tREGEXP_BEG","tWORDS_BEG","tQWORDS_BEG",
    "tSTRING_DBEG","tSTRING_DVAR","tSTRING_END","tLAMBDA","tLAMBEG",
    "tNTH_REF","tBACK_REF","tSTRING_CONTENT","tINTEGER","tFLOAT",
    "tREGEXP_END","tCOMMENT","tWHITESPACE","tDOCUMENTATION","tLOWEST",
    "tIMAGINARY","tRATIONAL","tSYMBOLS_BEG","tQSYMBOLS_BEG","tDSTAR",
    "tLABEL_END","tSTRING_DEND","tANDDOT","tSTRING",
    };

  /** printable rules for debugging.
    */
  protected static final String [] yyRule = {
    "$accept : program",
    "$$1 :",
    "program : $$1 top_compstmt",
    "top_compstmt : top_stmts opt_terms",
    "top_stmts : none",
    "top_stmts : top_stmt",
    "top_stmts : top_stmts terms top_stmt",
    "top_stmts : error top_stmt",
    "top_stmt : stmt",
    "$$2 :",
    "top_stmt : klBEGIN $$2 tLCURLY top_compstmt tRCURLY",
    "bodystmt : compstmt opt_rescue opt_else opt_ensure",
    "compstmt : stmts opt_terms",
    "stmts : none",
    "stmts : stmt_or_begin",
    "stmts : stmts terms stmt_or_begin",
    "stmts : error stmt",
    "stmt_or_begin : stmt",
    "$$3 :",
    "stmt_or_begin : kBEGIN $$3 tLCURLY top_compstmt tRCURLY",
    "$$4 :",
    "stmt : kALIAS fitem $$4 fitem",
    "stmt : kALIAS tGVAR tGVAR",
    "stmt : kALIAS tGVAR tBACK_REF",
    "stmt : kALIAS tGVAR tNTH_REF",
    "stmt : kUNDEF undef_list",
    "stmt : stmt kIF_MOD expr_value",
    "stmt : stmt kUNLESS_MOD expr_value",
    "stmt : stmt kWHILE_MOD expr_value",
    "stmt : stmt kUNTIL_MOD expr_value",
    "stmt : stmt kRESCUE_MOD stmt",
    "stmt : klEND tLCURLY compstmt tRCURLY",
    "stmt : command_asgn",
    "stmt : mlhs '=' command_call",
    "stmt : var_lhs tOP_ASGN command_call",
    "stmt : primary_value '[' opt_call_args rbracket tOP_ASGN command_call",
    "stmt : primary_value call_op tIDENTIFIER tOP_ASGN command_call",
    "stmt : primary_value call_op tCONSTANT tOP_ASGN command_call",
    "stmt : primary_value tCOLON2 tCONSTANT tOP_ASGN command_call",
    "stmt : primary_value tCOLON2 tIDENTIFIER tOP_ASGN command_call",
    "stmt : backref tOP_ASGN command_call",
    "stmt : lhs '=' mrhs",
    "stmt : mlhs '=' mrhs_arg",
    "stmt : expr",
    "command_asgn : lhs '=' command_call",
    "command_asgn : lhs '=' command_asgn",
    "expr : command_call",
    "expr : expr kAND expr",
    "expr : expr kOR expr",
    "expr : kNOT opt_nl expr",
    "expr : tBANG command_call",
    "expr : arg",
    "expr_value : expr",
    "command_call : command",
    "command_call : block_command",
    "block_command : block_call",
    "block_command : block_call dot_or_colon operation2 command_args",
    "$$5 :",
    "cmd_brace_block : tLBRACE_ARG $$5 opt_block_param compstmt tRCURLY",
    "fcall : operation",
    "command : fcall command_args",
    "command : fcall command_args cmd_brace_block",
    "command : primary_value call_op operation2 command_args",
    "command : primary_value call_op operation2 command_args cmd_brace_block",
    "command : primary_value tCOLON2 operation2 command_args",
    "command : primary_value tCOLON2 operation2 command_args cmd_brace_block",
    "command : kSUPER command_args",
    "command : kYIELD command_args",
    "command : kRETURN call_args",
    "command : kBREAK call_args",
    "command : kNEXT call_args",
    "mlhs : mlhs_basic",
    "mlhs : tLPAREN mlhs_inner rparen",
    "mlhs_inner : mlhs_basic",
    "mlhs_inner : tLPAREN mlhs_inner rparen",
    "mlhs_basic : mlhs_head",
    "mlhs_basic : mlhs_head mlhs_item",
    "mlhs_basic : mlhs_head tSTAR mlhs_node",
    "mlhs_basic : mlhs_head tSTAR mlhs_node ',' mlhs_post",
    "mlhs_basic : mlhs_head tSTAR",
    "mlhs_basic : mlhs_head tSTAR ',' mlhs_post",
    "mlhs_basic : tSTAR mlhs_node",
    "mlhs_basic : tSTAR mlhs_node ',' mlhs_post",
    "mlhs_basic : tSTAR",
    "mlhs_basic : tSTAR ',' mlhs_post",
    "mlhs_item : mlhs_node",
    "mlhs_item : tLPAREN mlhs_inner rparen",
    "mlhs_head : mlhs_item ','",
    "mlhs_head : mlhs_head mlhs_item ','",
    "mlhs_post : mlhs_item",
    "mlhs_post : mlhs_post ',' mlhs_item",
    "mlhs_node : user_variable",
    "mlhs_node : keyword_variable",
    "mlhs_node : primary_value '[' opt_call_args rbracket",
    "mlhs_node : primary_value call_op tIDENTIFIER",
    "mlhs_node : primary_value tCOLON2 tIDENTIFIER",
    "mlhs_node : primary_value call_op tCONSTANT",
    "mlhs_node : primary_value tCOLON2 tCONSTANT",
    "mlhs_node : tCOLON3 tCONSTANT",
    "mlhs_node : backref",
    "lhs : user_variable",
    "lhs : keyword_variable",
    "lhs : primary_value '[' opt_call_args rbracket",
    "lhs : primary_value call_op tIDENTIFIER",
    "lhs : primary_value tCOLON2 tIDENTIFIER",
    "lhs : primary_value call_op tCONSTANT",
    "lhs : primary_value tCOLON2 tCONSTANT",
    "lhs : tCOLON3 tCONSTANT",
    "lhs : backref",
    "cname : tIDENTIFIER",
    "cname : tCONSTANT",
    "cpath : tCOLON3 cname",
    "cpath : cname",
    "cpath : primary_value tCOLON2 cname",
    "fname : tIDENTIFIER",
    "fname : tCONSTANT",
    "fname : tFID",
    "fname : op",
    "fname : reswords",
    "fsym : fname",
    "fsym : symbol",
    "fitem : fsym",
    "fitem : dsym",
    "undef_list : fitem",
    "$$6 :",
    "undef_list : undef_list ',' $$6 fitem",
    "op : tPIPE",
    "op : tCARET",
    "op : tAMPER2",
    "op : tCMP",
    "op : tEQ",
    "op : tEQQ",
    "op : tMATCH",
    "op : tNMATCH",
    "op : tGT",
    "op : tGEQ",
    "op : tLT",
    "op : tLEQ",
    "op : tNEQ",
    "op : tLSHFT",
    "op : tRSHFT",
    "op : tDSTAR",
    "op : tPLUS",
    "op : tMINUS",
    "op : tSTAR2",
    "op : tSTAR",
    "op : tDIVIDE",
    "op : tPERCENT",
    "op : tPOW",
    "op : tBANG",
    "op : tTILDE",
    "op : tUPLUS",
    "op : tUMINUS",
    "op : tAREF",
    "op : tASET",
    "op : tBACK_REF2",
    "reswords : k__LINE__",
    "reswords : k__FILE__",
    "reswords : k__ENCODING__",
    "reswords : klBEGIN",
    "reswords : klEND",
    "reswords : kALIAS",
    "reswords : kAND",
    "reswords : kBEGIN",
    "reswords : kBREAK",
    "reswords : kCASE",
    "reswords : kCLASS",
    "reswords : kDEF",
    "reswords : kDEFINED",
    "reswords : kDO",
    "reswords : kELSE",
    "reswords : kELSIF",
    "reswords : kEND",
    "reswords : kENSURE",
    "reswords : kFALSE",
    "reswords : kFOR",
    "reswords : kIN",
    "reswords : kMODULE",
    "reswords : kNEXT",
    "reswords : kNIL",
    "reswords : kNOT",
    "reswords : kOR",
    "reswords : kREDO",
    "reswords : kRESCUE",
    "reswords : kRETRY",
    "reswords : kRETURN",
    "reswords : kSELF",
    "reswords : kSUPER",
    "reswords : kTHEN",
    "reswords : kTRUE",
    "reswords : kUNDEF",
    "reswords : kWHEN",
    "reswords : kYIELD",
    "reswords : kIF_MOD",
    "reswords : kUNLESS_MOD",
    "reswords : kWHILE_MOD",
    "reswords : kUNTIL_MOD",
    "reswords : kRESCUE_MOD",
    "arg : lhs '=' arg",
    "arg : lhs '=' arg kRESCUE_MOD arg",
    "arg : var_lhs tOP_ASGN arg",
    "arg : var_lhs tOP_ASGN arg kRESCUE_MOD arg",
    "arg : primary_value '[' opt_call_args rbracket tOP_ASGN arg",
    "arg : primary_value call_op tIDENTIFIER tOP_ASGN arg",
    "arg : primary_value call_op tCONSTANT tOP_ASGN arg",
    "arg : primary_value tCOLON2 tIDENTIFIER tOP_ASGN arg",
    "arg : primary_value tCOLON2 tCONSTANT tOP_ASGN arg",
    "arg : tCOLON3 tCONSTANT tOP_ASGN arg",
    "arg : backref tOP_ASGN arg",
    "arg : arg tDOT2 arg",
    "arg : arg tDOT3 arg",
    "arg : arg tPLUS arg",
    "arg : arg tMINUS arg",
    "arg : arg tSTAR2 arg",
    "arg : arg tDIVIDE arg",
    "arg : arg tPERCENT arg",
    "arg : arg tPOW arg",
    "arg : tUMINUS_NUM simple_numeric tPOW arg",
    "arg : tUPLUS arg",
    "arg : tUMINUS arg",
    "arg : arg tPIPE arg",
    "arg : arg tCARET arg",
    "arg : arg tAMPER2 arg",
    "arg : arg tCMP arg",
    "arg : arg tGT arg",
    "arg : arg tGEQ arg",
    "arg : arg tLT arg",
    "arg : arg tLEQ arg",
    "arg : arg tEQ arg",
    "arg : arg tEQQ arg",
    "arg : arg tNEQ arg",
    "arg : arg tMATCH arg",
    "arg : arg tNMATCH arg",
    "arg : tBANG arg",
    "arg : tTILDE arg",
    "arg : arg tLSHFT arg",
    "arg : arg tRSHFT arg",
    "arg : arg tANDOP arg",
    "arg : arg tOROP arg",
    "arg : kDEFINED opt_nl arg",
    "arg : arg '?' arg opt_nl ':' arg",
    "arg : primary",
    "arg_value : arg",
    "aref_args : none",
    "aref_args : args trailer",
    "aref_args : args ',' assocs trailer",
    "aref_args : assocs trailer",
    "paren_args : tLPAREN2 opt_call_args rparen",
    "opt_paren_args : none",
    "opt_paren_args : paren_args",
    "opt_call_args : none",
    "opt_call_args : call_args",
    "opt_call_args : args ','",
    "opt_call_args : args ',' assocs ','",
    "opt_call_args : assocs ','",
    "call_args : command",
    "call_args : args opt_block_arg",
    "call_args : assocs opt_block_arg",
    "call_args : args ',' assocs opt_block_arg",
    "call_args : block_arg",
    "$$7 :",
    "command_args : $$7 call_args",
    "block_arg : tAMPER arg_value",
    "opt_block_arg : ',' block_arg",
    "opt_block_arg : none_block_pass",
    "args : arg_value",
    "args : tSTAR arg_value",
    "args : args ',' arg_value",
    "args : args ',' tSTAR arg_value",
    "mrhs_arg : mrhs",
    "mrhs_arg : arg_value",
    "mrhs : args ',' arg_value",
    "mrhs : args ',' tSTAR arg_value",
    "mrhs : tSTAR arg_value",
    "primary : literal",
    "primary : strings",
    "primary : xstring",
    "primary : regexp",
    "primary : words",
    "primary : qwords",
    "primary : symbols",
    "primary : qsymbols",
    "primary : var_ref",
    "primary : backref",
    "primary : tFID",
    "primary : kBEGIN bodystmt kEND",
    "$$8 :",
    "primary : tLPAREN_ARG $$8 rparen",
    "$$9 :",
    "primary : tLPAREN_ARG expr $$9 rparen",
    "primary : tLPAREN compstmt tRPAREN",
    "primary : primary_value tCOLON2 tCONSTANT",
    "primary : tCOLON3 tCONSTANT",
    "primary : tLBRACK aref_args tRBRACK",
    "primary : tLBRACE assoc_list tRCURLY",
    "primary : kRETURN",
    "primary : kYIELD tLPAREN2 call_args rparen",
    "primary : kYIELD tLPAREN2 rparen",
    "primary : kYIELD",
    "primary : kDEFINED opt_nl tLPAREN2 expr rparen",
    "primary : kNOT tLPAREN2 expr rparen",
    "primary : kNOT tLPAREN2 rparen",
    "primary : fcall brace_block",
    "primary : method_call",
    "primary : method_call brace_block",
    "primary : tLAMBDA lambda",
    "primary : kIF expr_value then compstmt if_tail kEND",
    "primary : kUNLESS expr_value then compstmt opt_else kEND",
    "$$10 :",
    "$$11 :",
    "primary : kWHILE $$10 expr_value do $$11 compstmt kEND",
    "$$12 :",
    "$$13 :",
    "primary : kUNTIL $$12 expr_value do $$13 compstmt kEND",
    "primary : kCASE expr_value opt_terms case_body kEND",
    "primary : kCASE opt_terms case_body kEND",
    "$$14 :",
    "$$15 :",
    "primary : kFOR for_var kIN $$14 expr_value do $$15 compstmt kEND",
    "$$16 :",
    "primary : kCLASS cpath superclass $$16 bodystmt kEND",
    "$$17 :",
    "$$18 :",
    "primary : kCLASS tLSHFT expr $$17 term $$18 bodystmt kEND",
    "$$19 :",
    "primary : kMODULE cpath $$19 bodystmt kEND",
    "$$20 :",
    "primary : kDEF fname $$20 f_arglist bodystmt kEND",
    "$$21 :",
    "$$22 :",
    "primary : kDEF singleton dot_or_colon $$21 fname $$22 f_arglist bodystmt kEND",
    "primary : kBREAK",
    "primary : kNEXT",
    "primary : kREDO",
    "primary : kRETRY",
    "primary_value : primary",
    "then : term",
    "then : kTHEN",
    "then : term kTHEN",
    "do : term",
    "do : kDO_COND",
    "if_tail : opt_else",
    "if_tail : kELSIF expr_value then compstmt if_tail",
    "opt_else : none",
    "opt_else : kELSE compstmt",
    "for_var : lhs",
    "for_var : mlhs",
    "f_marg : f_norm_arg",
    "f_marg : tLPAREN f_margs rparen",
    "f_marg_list : f_marg",
    "f_marg_list : f_marg_list ',' f_marg",
    "f_margs : f_marg_list",
    "f_margs : f_marg_list ',' tSTAR f_norm_arg",
    "f_margs : f_marg_list ',' tSTAR f_norm_arg ',' f_marg_list",
    "f_margs : f_marg_list ',' tSTAR",
    "f_margs : f_marg_list ',' tSTAR ',' f_marg_list",
    "f_margs : tSTAR f_norm_arg",
    "f_margs : tSTAR f_norm_arg ',' f_marg_list",
    "f_margs : tSTAR",
    "f_margs : tSTAR ',' f_marg_list",
    "block_args_tail : f_block_kwarg ',' f_kwrest opt_f_block_arg",
    "block_args_tail : f_block_kwarg opt_f_block_arg",
    "block_args_tail : f_kwrest opt_f_block_arg",
    "block_args_tail : f_block_arg",
    "opt_block_args_tail : ',' block_args_tail",
    "opt_block_args_tail :",
    "block_param : f_arg ',' f_block_optarg ',' f_rest_arg opt_block_args_tail",
    "block_param : f_arg ',' f_block_optarg ',' f_rest_arg ',' f_arg opt_block_args_tail",
    "block_param : f_arg ',' f_block_optarg opt_block_args_tail",
    "block_param : f_arg ',' f_block_optarg ',' f_arg opt_block_args_tail",
    "block_param : f_arg ',' f_rest_arg opt_block_args_tail",
    "block_param : f_arg ','",
    "block_param : f_arg ',' f_rest_arg ',' f_arg opt_block_args_tail",
    "block_param : f_arg opt_block_args_tail",
    "block_param : f_block_optarg ',' f_rest_arg opt_block_args_tail",
    "block_param : f_block_optarg ',' f_rest_arg ',' f_arg opt_block_args_tail",
    "block_param : f_block_optarg opt_block_args_tail",
    "block_param : f_block_optarg ',' f_arg opt_block_args_tail",
    "block_param : f_rest_arg opt_block_args_tail",
    "block_param : f_rest_arg ',' f_arg opt_block_args_tail",
    "block_param : block_args_tail",
    "opt_block_param : none",
    "opt_block_param : block_param_def",
    "block_param_def : tPIPE opt_bv_decl tPIPE",
    "block_param_def : tOROP",
    "block_param_def : tPIPE block_param opt_bv_decl tPIPE",
    "opt_bv_decl : opt_nl",
    "opt_bv_decl : opt_nl ';' bv_decls opt_nl",
    "bv_decls : bvar",
    "bv_decls : bv_decls ',' bvar",
    "bvar : tIDENTIFIER",
    "bvar : f_bad_arg",
    "$$23 :",
    "lambda : $$23 f_larglist lambda_body",
    "f_larglist : tLPAREN2 f_args opt_bv_decl tRPAREN",
    "f_larglist : f_args",
    "lambda_body : tLAMBEG compstmt tRCURLY",
    "lambda_body : kDO_LAMBDA compstmt kEND",
    "$$24 :",
    "do_block : kDO_BLOCK $$24 opt_block_param compstmt kEND",
    "block_call : command do_block",
    "block_call : block_call dot_or_colon operation2 opt_paren_args",
    "block_call : block_call dot_or_colon operation2 opt_paren_args brace_block",
    "block_call : block_call dot_or_colon operation2 command_args do_block",
    "method_call : fcall paren_args",
    "method_call : primary_value call_op operation2 opt_paren_args",
    "method_call : primary_value tCOLON2 operation2 paren_args",
    "method_call : primary_value tCOLON2 operation3",
    "method_call : primary_value call_op paren_args",
    "method_call : primary_value tCOLON2 paren_args",
    "method_call : kSUPER paren_args",
    "method_call : kSUPER",
    "method_call : primary_value '[' opt_call_args rbracket",
    "$$25 :",
    "brace_block : tLCURLY $$25 opt_block_param compstmt tRCURLY",
    "$$26 :",
    "brace_block : kDO $$26 opt_block_param compstmt kEND",
    "case_body : kWHEN args then compstmt cases",
    "cases : opt_else",
    "cases : case_body",
    "opt_rescue : kRESCUE exc_list exc_var then compstmt opt_rescue",
    "opt_rescue :",
    "exc_list : arg_value",
    "exc_list : mrhs",
    "exc_list : none",
    "exc_var : tASSOC lhs",
    "exc_var : none",
    "opt_ensure : kENSURE compstmt",
    "opt_ensure : none",
    "literal : numeric",
    "literal : symbol",
    "literal : dsym",
    "strings : string",
    "string : tCHAR",
    "string : tSTRING",
    "string : string1",
    "string : string string1",
    "string1 : tSTRING_BEG string_contents tSTRING_END",
    "xstring : tXSTRING_BEG xstring_contents tSTRING_END",
    "regexp : tREGEXP_BEG regexp_contents tREGEXP_END",
    "words : tWORDS_BEG ' ' tSTRING_END",
    "words : tWORDS_BEG word_list tSTRING_END",
    "word_list :",
    "word_list : word_list word ' '",
    "word : string_content",
    "word : word string_content",
    "symbols : tSYMBOLS_BEG ' ' tSTRING_END",
    "symbols : tSYMBOLS_BEG symbol_list tSTRING_END",
    "symbol_list :",
    "symbol_list : symbol_list word ' '",
    "qwords : tQWORDS_BEG ' ' tSTRING_END",
    "qwords : tQWORDS_BEG qword_list tSTRING_END",
    "qsymbols : tQSYMBOLS_BEG ' ' tSTRING_END",
    "qsymbols : tQSYMBOLS_BEG qsym_list tSTRING_END",
    "qword_list :",
    "qword_list : qword_list tSTRING_CONTENT ' '",
    "qsym_list :",
    "qsym_list : qsym_list tSTRING_CONTENT ' '",
    "string_contents :",
    "string_contents : string_contents string_content",
    "xstring_contents :",
    "xstring_contents : xstring_contents string_content",
    "regexp_contents :",
    "regexp_contents : regexp_contents string_content",
    "string_content : tSTRING_CONTENT",
    "$$27 :",
    "string_content : tSTRING_DVAR $$27 string_dvar",
    "$$28 :",
    "string_content : tSTRING_DBEG $$28 compstmt tRCURLY",
    "string_dvar : tGVAR",
    "string_dvar : tIVAR",
    "string_dvar : tCVAR",
    "string_dvar : backref",
    "symbol : tSYMBEG sym",
    "sym : fname",
    "sym : tIVAR",
    "sym : tGVAR",
    "sym : tCVAR",
    "dsym : tSYMBEG xstring_contents tSTRING_END",
    "numeric : simple_numeric",
    "numeric : tUMINUS_NUM simple_numeric",
    "simple_numeric : tINTEGER",
    "simple_numeric : tFLOAT",
    "simple_numeric : tRATIONAL",
    "simple_numeric : tIMAGINARY",
    "user_variable : tIDENTIFIER",
    "user_variable : tIVAR",
    "user_variable : tGVAR",
    "user_variable : tCONSTANT",
    "user_variable : tCVAR",
    "keyword_variable : kNIL",
    "keyword_variable : kSELF",
    "keyword_variable : kTRUE",
    "keyword_variable : kFALSE",
    "keyword_variable : k__FILE__",
    "keyword_variable : k__LINE__",
    "keyword_variable : k__ENCODING__",
    "var_ref : user_variable",
    "var_ref : keyword_variable",
    "var_lhs : user_variable",
    "var_lhs : keyword_variable",
    "backref : tNTH_REF",
    "backref : tBACK_REF",
    "superclass : term",
    "$$29 :",
    "superclass : tLT $$29 expr_value term",
    "superclass : error term",
    "f_arglist : tLPAREN2 f_args rparen",
    "f_arglist : f_args term",
    "args_tail : f_kwarg ',' f_kwrest opt_f_block_arg",
    "args_tail : f_kwarg opt_f_block_arg",
    "args_tail : f_kwrest opt_f_block_arg",
    "args_tail : f_block_arg",
    "opt_args_tail : ',' args_tail",
    "opt_args_tail :",
    "f_args : f_arg ',' f_optarg ',' f_rest_arg opt_args_tail",
    "f_args : f_arg ',' f_optarg ',' f_rest_arg ',' f_arg opt_args_tail",
    "f_args : f_arg ',' f_optarg opt_args_tail",
    "f_args : f_arg ',' f_optarg ',' f_arg opt_args_tail",
    "f_args : f_arg ',' f_rest_arg opt_args_tail",
    "f_args : f_arg ',' f_rest_arg ',' f_arg opt_args_tail",
    "f_args : f_arg opt_args_tail",
    "f_args : f_optarg ',' f_rest_arg opt_args_tail",
    "f_args : f_optarg ',' f_rest_arg ',' f_arg opt_args_tail",
    "f_args : f_optarg opt_args_tail",
    "f_args : f_optarg ',' f_arg opt_args_tail",
    "f_args : f_rest_arg opt_args_tail",
    "f_args : f_rest_arg ',' f_arg opt_args_tail",
    "f_args : args_tail",
    "f_args :",
    "f_bad_arg : tCONSTANT",
    "f_bad_arg : tIVAR",
    "f_bad_arg : tGVAR",
    "f_bad_arg : tCVAR",
    "f_norm_arg : f_bad_arg",
    "f_norm_arg : tIDENTIFIER",
    "f_arg_item : f_norm_arg",
    "f_arg_item : tLPAREN f_margs rparen",
    "f_arg : f_arg_item",
    "f_arg : f_arg ',' f_arg_item",
    "f_label : tLABEL",
    "f_kw : f_label arg_value",
    "f_kw : f_label",
    "f_block_kw : tLABEL primary_value",
    "f_block_kwarg : f_block_kw",
    "f_block_kwarg : f_block_kwarg ',' f_block_kw",
    "f_kwarg : f_kw",
    "f_kwarg : f_kwarg ',' f_kw",
    "kwrest_mark : tPOW",
    "kwrest_mark : tDSTAR",
    "f_kwrest : kwrest_mark tIDENTIFIER",
    "f_opt : f_norm_arg '=' arg_value",
    "f_block_opt : tIDENTIFIER '=' primary_value",
    "f_block_optarg : f_block_opt",
    "f_block_optarg : f_block_optarg ',' f_block_opt",
    "f_optarg : f_opt",
    "f_optarg : f_optarg ',' f_opt",
    "restarg_mark : tSTAR2",
    "restarg_mark : tSTAR",
    "f_rest_arg : restarg_mark tIDENTIFIER",
    "f_rest_arg : restarg_mark",
    "blkarg_mark : tAMPER2",
    "blkarg_mark : tAMPER",
    "f_block_arg : blkarg_mark tIDENTIFIER",
    "opt_f_block_arg : ',' f_block_arg",
    "opt_f_block_arg :",
    "singleton : var_ref",
    "$$30 :",
    "singleton : tLPAREN2 $$30 expr rparen",
    "assoc_list : none",
    "assoc_list : assocs trailer",
    "assocs : assoc",
    "assocs : assocs ',' assoc",
    "assoc : arg_value tASSOC arg_value",
    "assoc : tLABEL arg_value",
    "assoc : tSTRING_BEG string_contents tLABEL_END arg_value",
    "assoc : tDSTAR arg_value",
    "operation : tIDENTIFIER",
    "operation : tCONSTANT",
    "operation : tFID",
    "operation2 : tIDENTIFIER",
    "operation2 : tCONSTANT",
    "operation2 : tFID",
    "operation2 : op",
    "operation3 : tIDENTIFIER",
    "operation3 : tFID",
    "operation3 : op",
    "dot_or_colon : tDOT",
    "dot_or_colon : tCOLON2",
    "call_op : tDOT",
    "call_op : tANDDOT",
    "opt_terms :",
    "opt_terms : terms",
    "opt_nl :",
    "opt_nl : '\\n'",
    "rparen : opt_nl tRPAREN",
    "rbracket : opt_nl tRBRACK",
    "trailer :",
    "trailer : '\\n'",
    "trailer : ','",
    "term : ';'",
    "term : '\\n'",
    "terms : term",
    "terms : terms ';'",
    "none :",
    "none_block_pass :",
    };

  /** debugging support, requires the package <tt>jay.yydebug</tt>.
      Set to <tt>null</tt> to suppress debugging messages.
    */
  protected jay.yydebug.yyDebug yydebug;

  /** index-checked interface to {@link #yyNames}.
      @param token single character or <tt>%token</tt> value.
      @return token name or <tt>[illegal]</tt> or <tt>[unknown]</tt>.
    */
  public static final String yyName (int token) {
    if (token < 0 || token > yyNames.length) return "[illegal]";
    String name;
    if ((name = yyNames[token]) != null) return name;
    return "[unknown]";
  }


  /** computes list of expected tokens on error by tracing the tables.
      @param state for which to compute the list.
      @return list of token names.
    */
  protected String[] yyExpecting (int state) {
    int token, n, len = 0;
    boolean[] ok = new boolean[yyNames.length];

    if ((n = yySindex[state]) != 0)
      for (token = n < 0 ? -n : 0;
           token < yyNames.length && n+token < yyTable.length; ++ token)
        if (yyCheck[n+token] == token && !ok[token] && yyNames[token] != null) {
          ++ len;
          ok[token] = true;
        }
    if ((n = yyRindex[state]) != 0)
      for (token = n < 0 ? -n : 0;
           token < yyNames.length && n+token < yyTable.length; ++ token)
        if (yyCheck[n+token] == token && !ok[token] && yyNames[token] != null) {
          ++ len;
          ok[token] = true;
        }

    String result[] = new String[len];
    for (n = token = 0; n < len;  ++ token)
      if (ok[token]) result[n++] = yyNames[token];
    return result;
  }

  /** the generated parser, with debugging messages.
      Maintains a dynamic state and value stack.
      @param yyLex scanner.
      @param ayydebug debug message writer implementing <tt>yyDebug</tt>, or <tt>null</tt>.
      @return result of the last reduction, if any.
    */
  public Object yyparse (Lexer yyLex, Object ayydebug)
				throws java.io.IOException {
    this.yydebug = (jay.yydebug.yyDebug)ayydebug;
    return yyparse(yyLex);
  }

  /** initial size and increment of the state/value stack [default 256].
      This is not final so that it can be overwritten outside of invocations
      of {@link #yyparse}.
    */
  protected int yyMax;

  /** executed at the beginning of a reduce action.
      Used as <tt>$$ = yyDefault($1)</tt>, prior to the user-specified action, if any.
      Can be overwritten to provide deep copy, etc.
      @param first value for <tt>$1</tt>, or <tt>null</tt>.
      @return first.
    */
  protected Object yyDefault (Object first) {
    return first;
  }

  /** the generated parser.
      Maintains a dynamic state and value stack.
      @param yyLex scanner.
      @return result of the last reduction, if any.
    */
  public Object yyparse (Lexer yyLex) throws java.io.IOException {
    if (yyMax <= 0) yyMax = 256;			// initial size
    int yyState = 0, yyStates[] = new int[yyMax];	// state stack
    Object yyVal = null, yyVals[] = new Object[yyMax];	// value stack
    int yyToken = -1;					// current input
    int yyErrorFlag = 0;				// #tokens to shift

    yyLoop: for (int yyTop = 0;; ++ yyTop) {
      if (yyTop >= yyStates.length) {			// dynamically increase
        int[] i = new int[yyStates.length+yyMax];
        System.arraycopy(yyStates, 0, i, 0, yyStates.length);
        yyStates = i;
        Object[] o = new Object[yyVals.length+yyMax];
        System.arraycopy(yyVals, 0, o, 0, yyVals.length);
        yyVals = o;
      }
      yyStates[yyTop] = yyState;
      yyVals[yyTop] = yyVal;
      if (yydebug != null) yydebug.push(yyState, yyVal);

      yyDiscarded: for (;;) {	// discarding a token does not change stack
        int yyN;
        if ((yyN = yyDefRed[yyState]) == 0) {	// else [default] reduce (yyN)
          if (yyToken < 0) {
//            yyToken = yyLex.advance() ? yyLex.token() : 0;
            yyToken = yyLex.nextToken();
            if (yydebug != null)
              yydebug.lex(yyState, yyToken, yyName(yyToken), yyLex.value());
          }
          if ((yyN = yySindex[yyState]) != 0 && (yyN += yyToken) >= 0
              && yyN < yyTable.length && yyCheck[yyN] == yyToken) {
            if (yydebug != null)
              yydebug.shift(yyState, yyTable[yyN], yyErrorFlag-1);
            yyState = yyTable[yyN];		// shift to yyN
            yyVal = yyLex.value();
            yyToken = -1;
            if (yyErrorFlag > 0) -- yyErrorFlag;
            continue yyLoop;
          }
          if ((yyN = yyRindex[yyState]) != 0 && (yyN += yyToken) >= 0
              && yyN < yyTable.length && yyCheck[yyN] == yyToken)
            yyN = yyTable[yyN];			// reduce (yyN)
          else
            switch (yyErrorFlag) {
  
            case 0:
              support.yyerror("syntax error", yyExpecting(yyState), yyNames[yyToken]);
              if (yydebug != null) yydebug.error("syntax error");
  
            case 1: case 2:
              yyErrorFlag = 3;
              do {
                if ((yyN = yySindex[yyStates[yyTop]]) != 0
                    && (yyN += yyErrorCode) >= 0 && yyN < yyTable.length
                    && yyCheck[yyN] == yyErrorCode) {
                  if (yydebug != null)
                    yydebug.shift(yyStates[yyTop], yyTable[yyN], 3);
                  yyState = yyTable[yyN];
                  yyVal = yyLex.value();
                  continue yyLoop;
                }
                if (yydebug != null) yydebug.pop(yyStates[yyTop]);
              } while (-- yyTop >= 0);
              if (yydebug != null) yydebug.reject();
              support.yyerror("irrecoverable syntax error");
  
            case 3:
              if (yyToken == 0) {
                if (yydebug != null) yydebug.reject();
                support.yyerror("irrecoverable syntax error at end-of-file");
              }
              if (yydebug != null)
                yydebug.discard(yyState, yyToken, yyName(yyToken),
  							yyLex.value());
              yyToken = -1;
              continue yyDiscarded;		// leave stack alone
            }
        }
        int yyV = yyTop + 1-yyLen[yyN];
        if (yydebug != null)
          yydebug.reduce(yyState, yyStates[yyV-1], yyN, yyRule[yyN], yyLen[yyN]);
        ParserState state = states[yyN];
        if (state == null) {
            yyVal = yyDefault(yyV > yyTop ? null : yyVals[yyV]);
        } else {
            yyVal = state.execute(support, lexer, yyVal, yyVals, yyTop);
        }
//        switch (yyN) {
// ACTIONS_END
//        }
        yyTop -= yyLen[yyN];
        yyState = yyStates[yyTop];
        int yyM = yyLhs[yyN];
        if (yyState == 0 && yyM == 0) {
          if (yydebug != null) yydebug.shift(0, yyFinal);
          yyState = yyFinal;
          if (yyToken < 0) {
            yyToken = yyLex.nextToken();
//            yyToken = yyLex.advance() ? yyLex.token() : 0;
            if (yydebug != null)
               yydebug.lex(yyState, yyToken,yyName(yyToken), yyLex.value());
          }
          if (yyToken == 0) {
            if (yydebug != null) yydebug.accept(yyVal);
            return yyVal;
          }
          continue yyLoop;
        }
        if ((yyN = yyGindex[yyM]) != 0 && (yyN += yyState) >= 0
            && yyN < yyTable.length && yyCheck[yyN] == yyState)
          yyState = yyTable[yyN];
        else
          yyState = yyDgoto[yyM];
        if (yydebug != null) yydebug.shift(yyStates[yyTop], yyState);
        continue yyLoop;
      }
    }
  }

static ParserState[] states = new ParserState[606];
static {
states[1] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                  lexer.setState(LexState.EXPR_BEG);
                  support.initTopLocalVariables();
    return yyVal;
  }
};
states[2] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
  /* ENEBO: Removed !compile_for_eval which probably is to reduce warnings*/
                  if (((Node)yyVals[0+yyTop]) != null) {
                      /* last expression should not be void */
                      if (((Node)yyVals[0+yyTop]) instanceof BlockNode) {
                          support.checkUselessStatement(((BlockNode)yyVals[0+yyTop]).getLast());
                      } else {
                          support.checkUselessStatement(((Node)yyVals[0+yyTop]));
                      }
                  }
                  support.getResult().setAST(support.addRootNode(((Node)yyVals[0+yyTop]), support.getPosition(((Node)yyVals[0+yyTop]))));
    return yyVal;
  }
};
states[3] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                  if (((Node)yyVals[-1+yyTop]) instanceof BlockNode) {
                      support.checkUselessStatements(((BlockNode)yyVals[-1+yyTop]));
                  }
                  yyVal = ((Node)yyVals[-1+yyTop]);
    return yyVal;
  }
};
states[5] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.newline_node(((Node)yyVals[0+yyTop]), support.getPosition(((Node)yyVals[0+yyTop]), true));
    return yyVal;
  }
};
states[6] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.appendToBlock(((Node)yyVals[-2+yyTop]), support.newline_node(((Node)yyVals[0+yyTop]), support.getPosition(((Node)yyVals[0+yyTop]), true)));
    return yyVal;
  }
};
states[7] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((Node)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[9] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    if (support.isInDef() || support.isInSingle()) {
                        support.yyerror("BEGIN in method");
                    }
    return yyVal;
  }
};
states[10] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    support.getResult().addBeginNode(new PreExe19Node(((Token)yyVals[-4+yyTop]).getPosition(), support.getCurrentScope(), ((Node)yyVals[-1+yyTop])));
                    yyVal = null;
    return yyVal;
  }
};
states[11] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                  Node node = ((Node)yyVals[-3+yyTop]);

                  if (((RescueBodyNode)yyVals[-2+yyTop]) != null) {
                      node = new RescueNode(support.getPosition(((Node)yyVals[-3+yyTop])), ((Node)yyVals[-3+yyTop]), ((RescueBodyNode)yyVals[-2+yyTop]), ((Node)yyVals[-1+yyTop]));
                  } else if (((Node)yyVals[-1+yyTop]) != null) {
                      support.warn(ID.ELSE_WITHOUT_RESCUE, support.getPosition(((Node)yyVals[-3+yyTop])), "else without rescue is useless");
                      node = support.appendToBlock(((Node)yyVals[-3+yyTop]), ((Node)yyVals[-1+yyTop]));
                  }
                  if (((Node)yyVals[0+yyTop]) != null) {
                      node = new EnsureNode(support.getPosition(((Node)yyVals[-3+yyTop])), node, ((Node)yyVals[0+yyTop]));
                  }

                  yyVal = node;
    return yyVal;
  }
};
states[12] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    if (((Node)yyVals[-1+yyTop]) instanceof BlockNode) {
                        support.checkUselessStatements(((BlockNode)yyVals[-1+yyTop]));
                    }
                    yyVal = ((Node)yyVals[-1+yyTop]);
    return yyVal;
  }
};
states[14] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.newline_node(((Node)yyVals[0+yyTop]), support.getPosition(((Node)yyVals[0+yyTop]), true));
    return yyVal;
  }
};
states[15] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.appendToBlock(((Node)yyVals[-2+yyTop]), support.newline_node(((Node)yyVals[0+yyTop]), support.getPosition(((Node)yyVals[0+yyTop]), true)));
    return yyVal;
  }
};
states[16] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((Node)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[17] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((Node)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[18] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                   support.yyerror("BEGIN is permitted only at toplevel");
    return yyVal;
  }
};
states[19] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                   yyVal = new BeginNode(support.union(((Token)yyVals[-4+yyTop]), ((Token)yyVals[-2+yyTop])), ((Node)yyVals[-3+yyTop]));
    return yyVal;
  }
};
states[20] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    lexer.setState(LexState.EXPR_FNAME);
    return yyVal;
  }
};
states[21] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.newAlias(support.union(((Token)yyVals[-3+yyTop]), ((Node)yyVals[0+yyTop])), ((Node)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[22] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new VAliasNode(((Token)yyVals[-2+yyTop]).getPosition(), (String) ((Token)yyVals[-1+yyTop]).getValue(), (String) ((Token)yyVals[0+yyTop]).getValue());
    return yyVal;
  }
};
states[23] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new VAliasNode(((Token)yyVals[-2+yyTop]).getPosition(), (String) ((Token)yyVals[-1+yyTop]).getValue(), "$" + ((BackRefNode)yyVals[0+yyTop]).getType());
    return yyVal;
  }
};
states[24] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    support.yyerror("can't make alias for the number variables");
    return yyVal;
  }
};
states[25] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((Node)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[26] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new IfNode(support.union(((Node)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop])), support.getConditionNode(((Node)yyVals[0+yyTop])), ((Node)yyVals[-2+yyTop]), null);
    return yyVal;
  }
};
states[27] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new IfNode(support.union(((Node)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop])), support.getConditionNode(((Node)yyVals[0+yyTop])), null, ((Node)yyVals[-2+yyTop]));
    return yyVal;
  }
};
states[28] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    if (((Node)yyVals[-2+yyTop]) != null && ((Node)yyVals[-2+yyTop]) instanceof BeginNode) {
                        yyVal = new WhileNode(support.getPosition(((Node)yyVals[-2+yyTop])), support.getConditionNode(((Node)yyVals[0+yyTop])), ((BeginNode)yyVals[-2+yyTop]).getBodyNode(), false);
                    } else {
                        yyVal = new WhileNode(support.getPosition(((Node)yyVals[-2+yyTop])), support.getConditionNode(((Node)yyVals[0+yyTop])), ((Node)yyVals[-2+yyTop]), true);
                    }
    return yyVal;
  }
};
states[29] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    if (((Node)yyVals[-2+yyTop]) != null && ((Node)yyVals[-2+yyTop]) instanceof BeginNode) {
                        yyVal = new UntilNode(support.getPosition(((Node)yyVals[-2+yyTop])), support.getConditionNode(((Node)yyVals[0+yyTop])), ((BeginNode)yyVals[-2+yyTop]).getBodyNode(), false);
                    } else {
                        yyVal = new UntilNode(support.getPosition(((Node)yyVals[-2+yyTop])), support.getConditionNode(((Node)yyVals[0+yyTop])), ((Node)yyVals[-2+yyTop]), true);
                    }
    return yyVal;
  }
};
states[30] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    Node body = ((Node)yyVals[0+yyTop]);
                    yyVal = new RescueNode(support.getPosition(((Node)yyVals[-2+yyTop])), ((Node)yyVals[-2+yyTop]), new RescueBodyNode(support.getPosition(((Node)yyVals[-2+yyTop])), null, body, null), null);
    return yyVal;
  }
};
states[31] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    if (support.isInDef() || support.isInSingle()) {
                        support.warn(ID.END_IN_METHOD, ((Token)yyVals[-3+yyTop]).getPosition(), "END in method; use at_exit");
                    }
                    yyVal = new PostExeNode(support.union(((Token)yyVals[-3+yyTop]), ((Token)yyVals[0+yyTop])), ((Node)yyVals[-1+yyTop]));
    return yyVal;
  }
};
states[33] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    support.checkExpression(((Node)yyVals[0+yyTop]));
                    ((MultipleAsgnNode)yyVals[-2+yyTop]).setValueNode(((Node)yyVals[0+yyTop]));
                    yyVal = ((MultipleAsgnNode)yyVals[-2+yyTop]);
    return yyVal;
  }
};
states[34] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    support.checkExpression(((Node)yyVals[0+yyTop]));

                    SourcePosition pos = support.union(((AssignableNode)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop]));
                    String asgnOp = (String) ((Token)yyVals[-1+yyTop]).getValue();
                    if (asgnOp.equals("||")) {
                        ((AssignableNode)yyVals[-2+yyTop]).setValueNode(((Node)yyVals[0+yyTop]));
                        yyVal = new OpAsgnOrNode(pos, support.gettable2(((AssignableNode)yyVals[-2+yyTop])), ((AssignableNode)yyVals[-2+yyTop]));
                    } else if (asgnOp.equals("&&")) {
                        ((AssignableNode)yyVals[-2+yyTop]).setValueNode(((Node)yyVals[0+yyTop]));
                        yyVal = new OpAsgnAndNode(pos, support.gettable2(((AssignableNode)yyVals[-2+yyTop])), ((AssignableNode)yyVals[-2+yyTop]));
                    } else {
                        ((AssignableNode)yyVals[-2+yyTop]).setValueNode(support.getOperatorCallNode(support.gettable2(((AssignableNode)yyVals[-2+yyTop])), asgnOp, ((Node)yyVals[0+yyTop])));
                        ((AssignableNode)yyVals[-2+yyTop]).setPosition(pos);
                        yyVal = ((AssignableNode)yyVals[-2+yyTop]);
                    }
    return yyVal;
  }
};
states[35] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
  /* FIXME: arg_concat logic missing for opt_call_args*/
                    yyVal = support.new_opElementAsgnNode(support.union(((Node)yyVals[-5+yyTop]), ((Node)yyVals[0+yyTop])), ((Node)yyVals[-5+yyTop]), (String) ((Token)yyVals[-1+yyTop]).getValue(), ((Node)yyVals[-3+yyTop]), ((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[36] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.newOpAsgn(support.getPosition(((Node)yyVals[-4+yyTop])), ((Node)yyVals[-4+yyTop]), (String) ((Token)yyVals[-3+yyTop]).getValue(), ((Node)yyVals[0+yyTop]), (String) ((Token)yyVals[-2+yyTop]).getValue(), (String) ((Token)yyVals[-1+yyTop]).getValue());
    return yyVal;
  }
};
states[37] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.newOpAsgn(support.getPosition(((Node)yyVals[-4+yyTop])), ((Node)yyVals[-4+yyTop]), (String) ((Token)yyVals[-3+yyTop]).getValue(), ((Node)yyVals[0+yyTop]), (String) ((Token)yyVals[-2+yyTop]).getValue(), (String) ((Token)yyVals[-1+yyTop]).getValue());
    return yyVal;
  }
};
states[38] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    support.yyerror("can't make alias for the number variables");
                    yyVal = null;
    return yyVal;
  }
};
states[39] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.newOpAsgn(support.getPosition(((Node)yyVals[-4+yyTop])), ((Node)yyVals[-4+yyTop]), (String) ((Token)yyVals[-3+yyTop]).getValue(), ((Node)yyVals[0+yyTop]), (String) ((Token)yyVals[-2+yyTop]).getValue(), (String) ((Token)yyVals[-1+yyTop]).getValue());
    return yyVal;
  }
};
states[40] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    support.backrefAssignError(((Node)yyVals[-2+yyTop]));
    return yyVal;
  }
};
states[41] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.node_assign(((Node)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[42] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    ((AssignableNode)yyVals[-2+yyTop]).setValueNode(((Node)yyVals[0+yyTop]));
                    yyVal = ((MultipleAsgnNode)yyVals[-2+yyTop]);
                    ((MultipleAsgnNode)yyVals[-2+yyTop]).setPosition(support.getPosition(((MultipleAsgnNode)yyVals[-2+yyTop])));
    return yyVal;
  }
};
states[44] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    support.checkExpression(((Node)yyVals[0+yyTop]));
                    yyVal = support.node_assign(((Node)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[45] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    support.checkExpression(((Node)yyVals[0+yyTop]));
                    yyVal = support.node_assign(((Node)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[47] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.newAndNode(((Token)yyVals[-1+yyTop]).getPosition(), ((Node)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[48] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.newOrNode(((Token)yyVals[-1+yyTop]).getPosition(), ((Node)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[49] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.getOperatorCallNode(((Token)yyVals[-2+yyTop]), support.getConditionNode(((Node)yyVals[0+yyTop])));
                    ((CallNode)yyVal).setName("!");
    return yyVal;
  }
};
states[50] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.getOperatorCallNode(((Token)yyVals[-1+yyTop]), support.getConditionNode(((Node)yyVals[0+yyTop])));
    return yyVal;
  }
};
states[52] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    support.checkExpression(((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[56] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_call(((Node)yyVals[-3+yyTop]), ((Token)yyVals[-1+yyTop]), ((Node)yyVals[0+yyTop]), null);
    return yyVal;
  }
};
states[57] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    support.pushBlockScope();
    return yyVal;
  }
};
states[58] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new IterNode(((Token)yyVals[-4+yyTop]).getPosition(), ((ArgsNode)yyVals[-2+yyTop]), ((Node)yyVals[-1+yyTop]), support.getCurrentScope());
                    support.popCurrentScope();
    return yyVal;
  }
};
states[59] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_fcall(((Token)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[60] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    support.frobnicate_fcall_args(((FCallNode)yyVals[-1+yyTop]), ((Node)yyVals[0+yyTop]), null);
                    yyVal = ((FCallNode)yyVals[-1+yyTop]);
    return yyVal;
  }
};
states[61] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    support.frobnicate_fcall_args(((FCallNode)yyVals[-2+yyTop]), ((Node)yyVals[-1+yyTop]), ((IterNode)yyVals[0+yyTop]));
                    yyVal = ((FCallNode)yyVals[-2+yyTop]);
    return yyVal;
  }
};
states[62] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_call(((Node)yyVals[-3+yyTop]), ((Token)yyVals[-1+yyTop]), ((Node)yyVals[0+yyTop]), null);
    return yyVal;
  }
};
states[63] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_call(((Node)yyVals[-4+yyTop]), ((Token)yyVals[-2+yyTop]), ((Node)yyVals[-1+yyTop]), ((IterNode)yyVals[0+yyTop])); 
    return yyVal;
  }
};
states[64] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_call(((Node)yyVals[-3+yyTop]), ((Token)yyVals[-1+yyTop]), ((Node)yyVals[0+yyTop]), null);
    return yyVal;
  }
};
states[65] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_call(((Node)yyVals[-4+yyTop]), ((Token)yyVals[-2+yyTop]), ((Node)yyVals[-1+yyTop]), ((IterNode)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[66] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_super(((Node)yyVals[0+yyTop]), ((Token)yyVals[-1+yyTop])); /* .setPosFrom($2);*/
    return yyVal;
  }
};
states[67] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_yield(support.union(((Token)yyVals[-1+yyTop]), ((Node)yyVals[0+yyTop])), ((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[68] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new ReturnNode(support.union(((Token)yyVals[-1+yyTop]), ((Node)yyVals[0+yyTop])), support.ret_args(((Node)yyVals[0+yyTop]), ((Node)yyVals[0+yyTop]).getPosition()));
    return yyVal;
  }
};
states[69] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new BreakNode(support.union(((Token)yyVals[-1+yyTop]), ((Node)yyVals[0+yyTop])), support.ret_args(((Node)yyVals[0+yyTop]), ((Node)yyVals[0+yyTop]).getPosition()));
    return yyVal;
  }
};
states[70] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new NextNode(support.union(((Token)yyVals[-1+yyTop]), ((Node)yyVals[0+yyTop])), support.ret_args(((Node)yyVals[0+yyTop]), ((Node)yyVals[0+yyTop]).getPosition()));
    return yyVal;
  }
};
states[72] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((Node)yyVals[-1+yyTop]);
                    ((Node)yyVal).setPosition(support.union(((Token)yyVals[-2+yyTop]), ((Token)yyVals[0+yyTop])));
    return yyVal;
  }
};
states[73] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((MultipleAsgnNode)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[74] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    SourcePosition pos = support.union(((Token)yyVals[-2+yyTop]), ((Token)yyVals[0+yyTop]));
                    yyVal = new MultipleAsgnNode(pos, support.newArrayNode(pos, ((Node)yyVals[-1+yyTop])), null, null);
    return yyVal;
  }
};
states[75] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new MultipleAsgnNode(((ListNode)yyVals[0+yyTop]).getPosition(), ((ListNode)yyVals[0+yyTop]), null, null);
    return yyVal;
  }
};
states[76] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new MultipleAsgnNode(((ListNode)yyVals[-1+yyTop]).getPosition(), ((ListNode)yyVals[-1+yyTop]).add(((Node)yyVals[0+yyTop])), null, null);
    return yyVal;
  }
};
states[77] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new MultipleAsgnNode(((ListNode)yyVals[-2+yyTop]).getPosition(), ((ListNode)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop]), (ListNode) null);
    return yyVal;
  }
};
states[78] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new MultipleAsgnNode(((ListNode)yyVals[-4+yyTop]).getPosition(), ((ListNode)yyVals[-4+yyTop]), ((Node)yyVals[-2+yyTop]), ((ListNode)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[79] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new MultipleAsgnNode(((ListNode)yyVals[-1+yyTop]).getPosition(), ((ListNode)yyVals[-1+yyTop]), new StarNode(lexer.getPosition()), null);
    return yyVal;
  }
};
states[80] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new MultipleAsgnNode(((ListNode)yyVals[-3+yyTop]).getPosition(), ((ListNode)yyVals[-3+yyTop]), new StarNode(lexer.getPosition()), ((ListNode)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[81] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new MultipleAsgnNode(((Token)yyVals[-1+yyTop]).getPosition(), null, ((Node)yyVals[0+yyTop]), null);
    return yyVal;
  }
};
states[82] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new MultipleAsgnNode(((Token)yyVals[-3+yyTop]).getPosition(), null, ((Node)yyVals[-2+yyTop]), ((ListNode)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[83] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                      yyVal = new MultipleAsgnNode(((Token)yyVals[0+yyTop]).getPosition(), null, new StarNode(lexer.getPosition()), null);
    return yyVal;
  }
};
states[84] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                      yyVal = new MultipleAsgnNode(((Token)yyVals[-2+yyTop]).getPosition(), null, new StarNode(lexer.getPosition()), ((ListNode)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[86] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((Node)yyVals[-1+yyTop]);
                    ((Node)yyVal).setPosition(support.union(((Token)yyVals[-2+yyTop]), ((Token)yyVals[0+yyTop])));
    return yyVal;
  }
};
states[87] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.newArrayNode(((Node)yyVals[-1+yyTop]).getPosition(), ((Node)yyVals[-1+yyTop]));
    return yyVal;
  }
};
states[88] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((ListNode)yyVals[-2+yyTop]).add(((Node)yyVals[-1+yyTop]));
    return yyVal;
  }
};
states[89] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.newArrayNode(((Node)yyVals[0+yyTop]).getPosition(), ((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[90] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((ListNode)yyVals[-2+yyTop]).add(((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[91] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.assignable(((Token)yyVals[0+yyTop]), null);
    return yyVal;
  }
};
states[92] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.assignable(((Token)yyVals[0+yyTop]), null);
    return yyVal;
  }
};
states[93] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.aryset(((Node)yyVals[-3+yyTop]), ((Node)yyVals[-1+yyTop]));
    return yyVal;
  }
};
states[94] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.attrset(((Node)yyVals[-2+yyTop]), (String) ((Token)yyVals[0+yyTop]).getValue());
    return yyVal;
  }
};
states[95] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.attrset(((Node)yyVals[-2+yyTop]), (String) ((Token)yyVals[0+yyTop]).getValue());
    return yyVal;
  }
};
states[96] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.attrset(((Node)yyVals[-2+yyTop]), (String) ((Token)yyVals[0+yyTop]).getValue());
    return yyVal;
  }
};
states[97] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    if (support.isInDef() || support.isInSingle()) {
                        support.yyerror("dynamic constant assignment");
                    }

                    SourcePosition position = support.union(((Node)yyVals[-2+yyTop]), ((Token)yyVals[0+yyTop]));

                    yyVal = new ConstDeclNode(position, null, support.new_colon2(position, ((Node)yyVals[-2+yyTop]), (String) ((Token)yyVals[0+yyTop]).getValue()), null);
    return yyVal;
  }
};
states[98] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    if (support.isInDef() || support.isInSingle()) {
                        support.yyerror("dynamic constant assignment");
                    }

                    SourcePosition position = support.union(((Token)yyVals[-1+yyTop]), ((Token)yyVals[0+yyTop]));

                    yyVal = new ConstDeclNode(position, null, support.new_colon3(position, (String) ((Token)yyVals[0+yyTop]).getValue()), null);
    return yyVal;
  }
};
states[99] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    support.backrefAssignError(((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[100] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.assignable(((Token)yyVals[0+yyTop]), null);
    return yyVal;
  }
};
states[101] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.assignable(((Token)yyVals[0+yyTop]), null);
    return yyVal;
  }
};
states[102] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.aryset(((Node)yyVals[-3+yyTop]), ((Node)yyVals[-1+yyTop]));
    return yyVal;
  }
};
states[103] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.attrset(((Node)yyVals[-2+yyTop]), (String) ((Token)yyVals[0+yyTop]).getValue());
    return yyVal;
  }
};
states[104] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.attrset(((Node)yyVals[-2+yyTop]), (String) ((Token)yyVals[0+yyTop]).getValue());
    return yyVal;
  }
};
states[105] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.attrset(((Node)yyVals[-2+yyTop]), (String) ((Token)yyVals[0+yyTop]).getValue());
    return yyVal;
  }
};
states[106] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    if (support.isInDef() || support.isInSingle()) {
                        support.yyerror("dynamic constant assignment");
                    }

                    SourcePosition position = support.union(((Node)yyVals[-2+yyTop]), ((Token)yyVals[0+yyTop]));

                    yyVal = new ConstDeclNode(position, null, support.new_colon2(position, ((Node)yyVals[-2+yyTop]), (String) ((Token)yyVals[0+yyTop]).getValue()), null);
    return yyVal;
  }
};
states[107] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    if (support.isInDef() || support.isInSingle()) {
                        support.yyerror("dynamic constant assignment");
                    }

                    SourcePosition position = support.union(((Token)yyVals[-1+yyTop]), ((Token)yyVals[0+yyTop]));

                    yyVal = new ConstDeclNode(position, null, support.new_colon3(position, (String) ((Token)yyVals[0+yyTop]).getValue()), null);
    return yyVal;
  }
};
states[108] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    support.backrefAssignError(((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[109] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    support.yyerror("class/module name must be CONSTANT");
    return yyVal;
  }
};
states[111] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_colon3(((Token)yyVals[-1+yyTop]).getPosition(), (String) ((Token)yyVals[0+yyTop]).getValue());
    return yyVal;
  }
};
states[112] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_colon2(((Token)yyVals[0+yyTop]).getPosition(), null, (String) ((Token)yyVals[0+yyTop]).getValue());
    return yyVal;
  }
};
states[113] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_colon2(support.getPosition(((Node)yyVals[-2+yyTop])), ((Node)yyVals[-2+yyTop]), (String) ((Token)yyVals[0+yyTop]).getValue());
    return yyVal;
  }
};
states[117] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                   lexer.setState(LexState.EXPR_ENDFN);
                   yyVal = ((Token)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[118] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                   lexer.setState(LexState.EXPR_ENDFN);
                   yyVal = ((Token)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[119] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new LiteralNode(((Token)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[120] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new LiteralNode(((Token)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[121] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((LiteralNode)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[122] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((Node)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[123] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.newUndef(((Node)yyVals[0+yyTop]).getPosition(), ((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[124] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    lexer.setState(LexState.EXPR_FNAME);
    return yyVal;
  }
};
states[125] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.appendToBlock(((Node)yyVals[-3+yyTop]), support.newUndef(((Node)yyVals[-3+yyTop]).getPosition(), ((Node)yyVals[0+yyTop])));
    return yyVal;
  }
};
states[198] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.node_assign(((Node)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop]));
                    /* FIXME: Consider fixing node_assign itself rather than single case*/
                    ((Node)yyVal).setPosition(support.union(((Node)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop])));
    return yyVal;
  }
};
states[199] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    SourcePosition position = ((Token)yyVals[-1+yyTop]).getPosition();
                    Node body = ((Node)yyVals[0+yyTop]);
                    yyVal = support.node_assign(((Node)yyVals[-4+yyTop]), new RescueNode(position, ((Node)yyVals[-2+yyTop]), new RescueBodyNode(position, null, body, null), null));
    return yyVal;
  }
};
states[200] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    support.checkExpression(((Node)yyVals[0+yyTop]));

                    SourcePosition pos = support.union(((AssignableNode)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop]));
                    String asgnOp = (String) ((Token)yyVals[-1+yyTop]).getValue();
                    if (asgnOp.equals("||")) {
                        ((AssignableNode)yyVals[-2+yyTop]).setValueNode(((Node)yyVals[0+yyTop]));
                        yyVal = new OpAsgnOrNode(pos, support.gettable2(((AssignableNode)yyVals[-2+yyTop])), ((AssignableNode)yyVals[-2+yyTop]));
                    } else if (asgnOp.equals("&&")) {
                        ((AssignableNode)yyVals[-2+yyTop]).setValueNode(((Node)yyVals[0+yyTop]));
                        yyVal = new OpAsgnAndNode(pos, support.gettable2(((AssignableNode)yyVals[-2+yyTop])), ((AssignableNode)yyVals[-2+yyTop]));
                    } else {
                        ((AssignableNode)yyVals[-2+yyTop]).setValueNode(support.getOperatorCallNode(support.gettable2(((AssignableNode)yyVals[-2+yyTop])), asgnOp, ((Node)yyVals[0+yyTop])));
                        ((AssignableNode)yyVals[-2+yyTop]).setPosition(pos);
                        yyVal = ((AssignableNode)yyVals[-2+yyTop]);
                    }
    return yyVal;
  }
};
states[201] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    support.checkExpression(((Node)yyVals[-2+yyTop]));
                    SourcePosition pos = support.union(((Token)yyVals[-1+yyTop]), ((Node)yyVals[0+yyTop]));
                    Node body = ((Node)yyVals[0+yyTop]);
                    Node rescue = new RescueNode(pos, ((Node)yyVals[-2+yyTop]), new RescueBodyNode(((Token)yyVals[-1+yyTop]).getPosition(), null, body, null), null);

                    pos = support.union(((AssignableNode)yyVals[-4+yyTop]), ((Node)yyVals[-2+yyTop]));
                    String asgnOp = (String) ((Token)yyVals[-3+yyTop]).getValue();
                    if (asgnOp.equals("||")) {
                        ((AssignableNode)yyVals[-4+yyTop]).setValueNode(rescue);
                        yyVal = new OpAsgnOrNode(pos, support.gettable2(((AssignableNode)yyVals[-4+yyTop])), ((AssignableNode)yyVals[-4+yyTop]));
                    } else if (asgnOp.equals("&&")) {
                        ((AssignableNode)yyVals[-4+yyTop]).setValueNode(rescue);
                        yyVal = new OpAsgnAndNode(pos, support.gettable2(((AssignableNode)yyVals[-4+yyTop])), ((AssignableNode)yyVals[-4+yyTop]));
                    } else {
                        ((AssignableNode)yyVals[-4+yyTop]).setValueNode(support.getOperatorCallNode(support.gettable2(((AssignableNode)yyVals[-4+yyTop])), asgnOp, rescue));
                        ((AssignableNode)yyVals[-4+yyTop]).setPosition(pos);
                        yyVal = ((AssignableNode)yyVals[-4+yyTop]);
                    }
    return yyVal;
  }
};
states[202] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
  /* FIXME: arg_concat missing for opt_call_args*/
                    yyVal = support.new_opElementAsgnNode(support.union(((Node)yyVals[-5+yyTop]), ((Node)yyVals[0+yyTop])), ((Node)yyVals[-5+yyTop]), (String) ((Token)yyVals[-1+yyTop]).getValue(), ((Node)yyVals[-3+yyTop]), ((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[203] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.newOpAsgn(support.getPosition(((Node)yyVals[-4+yyTop])), ((Node)yyVals[-4+yyTop]), (String) ((Token)yyVals[-3+yyTop]).getValue(), ((Node)yyVals[0+yyTop]), (String) ((Token)yyVals[-2+yyTop]).getValue(), (String) ((Token)yyVals[-1+yyTop]).getValue());
    return yyVal;
  }
};
states[204] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.newOpAsgn(support.getPosition(((Node)yyVals[-4+yyTop])), ((Node)yyVals[-4+yyTop]), (String) ((Token)yyVals[-3+yyTop]).getValue(), ((Node)yyVals[0+yyTop]), (String) ((Token)yyVals[-2+yyTop]).getValue(), (String) ((Token)yyVals[-1+yyTop]).getValue());
    return yyVal;
  }
};
states[205] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.newOpAsgn(support.getPosition(((Node)yyVals[-4+yyTop])), ((Node)yyVals[-4+yyTop]), (String) ((Token)yyVals[-3+yyTop]).getValue(), ((Node)yyVals[0+yyTop]), (String) ((Token)yyVals[-2+yyTop]).getValue(), (String) ((Token)yyVals[-1+yyTop]).getValue());
    return yyVal;
  }
};
states[206] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    support.yyerror("constant re-assignment");
    return yyVal;
  }
};
states[207] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    support.yyerror("constant re-assignment");
    return yyVal;
  }
};
states[208] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    support.backrefAssignError(((Node)yyVals[-2+yyTop]));
    return yyVal;
  }
};
states[209] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    support.checkExpression(((Node)yyVals[-2+yyTop]));
                    support.checkExpression(((Node)yyVals[0+yyTop]));
    
                    boolean isLiteral = ((Node)yyVals[-2+yyTop]) instanceof FixnumNode && ((Node)yyVals[0+yyTop]) instanceof FixnumNode;
                    yyVal = new DotNode(support.union(((Node)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop])), ((Node)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop]), false, isLiteral);
    return yyVal;
  }
};
states[210] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    support.checkExpression(((Node)yyVals[-2+yyTop]));
                    support.checkExpression(((Node)yyVals[0+yyTop]));

                    boolean isLiteral = ((Node)yyVals[-2+yyTop]) instanceof FixnumNode && ((Node)yyVals[0+yyTop]) instanceof FixnumNode;
                    yyVal = new DotNode(support.union(((Node)yyVals[-2+yyTop]),  ((Node)yyVals[0+yyTop])), ((Node)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop]), true, isLiteral);
    return yyVal;
  }
};
states[211] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.getOperatorCallNode(((Node)yyVals[-2+yyTop]), "+", ((Node)yyVals[0+yyTop]), lexer.getPosition());
    return yyVal;
  }
};
states[212] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.getOperatorCallNode(((Node)yyVals[-2+yyTop]), "-", ((Node)yyVals[0+yyTop]), lexer.getPosition());
    return yyVal;
  }
};
states[213] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.getOperatorCallNode(((Node)yyVals[-2+yyTop]), "*", ((Node)yyVals[0+yyTop]), lexer.getPosition());
    return yyVal;
  }
};
states[214] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.getOperatorCallNode(((Node)yyVals[-2+yyTop]), "/", ((Node)yyVals[0+yyTop]), lexer.getPosition());
    return yyVal;
  }
};
states[215] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.getOperatorCallNode(((Node)yyVals[-2+yyTop]), "%", ((Node)yyVals[0+yyTop]), lexer.getPosition());
    return yyVal;
  }
};
states[216] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.getOperatorCallNode(((Node)yyVals[-2+yyTop]), "**", ((Node)yyVals[0+yyTop]), lexer.getPosition());
    return yyVal;
  }
};
states[217] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.getOperatorCallNode(((NumericNode)yyVals[-2+yyTop]), "**", ((Node)yyVals[0+yyTop]), lexer.getPosition());
    return yyVal;
  }
};
states[218] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.getUnaryCallNode(((Node)yyVals[0+yyTop]), ((Token)yyVals[-1+yyTop]));
    return yyVal;
  }
};
states[219] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.getUnaryCallNode(((Node)yyVals[0+yyTop]), ((Token)yyVals[-1+yyTop]));
    return yyVal;
  }
};
states[220] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.getOperatorCallNode(((Node)yyVals[-2+yyTop]), "|", ((Node)yyVals[0+yyTop]), lexer.getPosition());
    return yyVal;
  }
};
states[221] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.getOperatorCallNode(((Node)yyVals[-2+yyTop]), "^", ((Node)yyVals[0+yyTop]), lexer.getPosition());
    return yyVal;
  }
};
states[222] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.getOperatorCallNode(((Node)yyVals[-2+yyTop]), "&", ((Node)yyVals[0+yyTop]), lexer.getPosition());
    return yyVal;
  }
};
states[223] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.getOperatorCallNode(((Node)yyVals[-2+yyTop]), "<=>", ((Node)yyVals[0+yyTop]), lexer.getPosition());
    return yyVal;
  }
};
states[224] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.getOperatorCallNode(((Node)yyVals[-2+yyTop]), ">", ((Node)yyVals[0+yyTop]), lexer.getPosition());
    return yyVal;
  }
};
states[225] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.getOperatorCallNode(((Node)yyVals[-2+yyTop]), ">=", ((Node)yyVals[0+yyTop]), lexer.getPosition());
    return yyVal;
  }
};
states[226] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.getOperatorCallNode(((Node)yyVals[-2+yyTop]), "<", ((Node)yyVals[0+yyTop]), lexer.getPosition());
    return yyVal;
  }
};
states[227] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.getOperatorCallNode(((Node)yyVals[-2+yyTop]), "<=", ((Node)yyVals[0+yyTop]), lexer.getPosition());
    return yyVal;
  }
};
states[228] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.getOperatorCallNode(((Node)yyVals[-2+yyTop]), "==", ((Node)yyVals[0+yyTop]), lexer.getPosition());
    return yyVal;
  }
};
states[229] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.getOperatorCallNode(((Node)yyVals[-2+yyTop]), "===", ((Node)yyVals[0+yyTop]), lexer.getPosition());
    return yyVal;
  }
};
states[230] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.getOperatorCallNode(((Node)yyVals[-2+yyTop]), "!=", ((Node)yyVals[0+yyTop]), lexer.getPosition());
    return yyVal;
  }
};
states[231] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.getMatchNode(((Node)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop]));
                  /* ENEBO
                        $$ = match_op($1, $3);
                        if (nd_type($1) == NODE_LIT && TYPE($1->nd_lit) == T_REGEXP) {
                            $$ = reg_named_capture_assign($1->nd_lit, $$);
                        }
                  */
    return yyVal;
  }
};
states[232] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.getOperatorCallNode(((Node)yyVals[-2+yyTop]), "!~", ((Node)yyVals[0+yyTop]), lexer.getPosition());
    return yyVal;
  }
};
states[233] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.getOperatorCallNode(support.union(((Token)yyVals[-1+yyTop]), ((Node)yyVals[0+yyTop])), support.getConditionNode(((Node)yyVals[0+yyTop])), "!");
    return yyVal;
  }
};
states[234] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.getOperatorCallNode(support.union(((Token)yyVals[-1+yyTop]), ((Node)yyVals[0+yyTop])), ((Node)yyVals[0+yyTop]), "~");
    return yyVal;
  }
};
states[235] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.getOperatorCallNode(((Node)yyVals[-2+yyTop]), "<<", ((Node)yyVals[0+yyTop]), lexer.getPosition());
    return yyVal;
  }
};
states[236] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.getOperatorCallNode(((Node)yyVals[-2+yyTop]), ">>", ((Node)yyVals[0+yyTop]), lexer.getPosition());
    return yyVal;
  }
};
states[237] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.newAndNode(((Token)yyVals[-1+yyTop]).getPosition(), ((Node)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[238] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.newOrNode(((Token)yyVals[-1+yyTop]).getPosition(), ((Node)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[239] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new DefinedNode(((Token)yyVals[-2+yyTop]).getPosition(), ((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[240] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new IfNode(support.getPosition(((Node)yyVals[-5+yyTop])), support.getConditionNode(((Node)yyVals[-5+yyTop])), ((Node)yyVals[-3+yyTop]), ((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[241] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((Node)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[242] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    support.checkExpression(((Node)yyVals[0+yyTop]));
                    yyVal = ((Node)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[244] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((Node)yyVals[-1+yyTop]);
    return yyVal;
  }
};
states[245] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.arg_append(((Node)yyVals[-3+yyTop]), new HashNode(((ListNode)yyVals[-1+yyTop]).getPosition(), ((ListNode)yyVals[-1+yyTop])));
    return yyVal;
  }
};
states[246] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.newArrayNode(((ListNode)yyVals[-1+yyTop]).getPosition(), new HashNode(((ListNode)yyVals[-1+yyTop]).getPosition(), ((ListNode)yyVals[-1+yyTop])));
    return yyVal;
  }
};
states[247] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    SourcePosition pos = support.union(((Token)yyVals[-2+yyTop]), ((Token)yyVals[0+yyTop]));
                    
                    if (((Node)yyVals[-1+yyTop]) == null) {
                        yyVal = new ArrayNode(pos);
                    } else {
                        yyVal = ((Node)yyVals[-1+yyTop]);
                        ((Node)yyVal).setPosition(pos);
                    }
    return yyVal;
  }
};
states[252] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((Node)yyVals[-1+yyTop]);
    return yyVal;
  }
};
states[253] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.arg_append(((Node)yyVals[-3+yyTop]), new HashNode(((ListNode)yyVals[-1+yyTop]).getPosition(), ((ListNode)yyVals[-1+yyTop])));
    return yyVal;
  }
};
states[254] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.newArrayNode(((ListNode)yyVals[-1+yyTop]).getPosition(), new HashNode(((ListNode)yyVals[-1+yyTop]).getPosition(), ((ListNode)yyVals[-1+yyTop])));
    return yyVal;
  }
};
states[255] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.newArrayNode(support.getPosition(((Node)yyVals[0+yyTop])), ((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[256] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.arg_blk_pass(((Node)yyVals[-1+yyTop]), ((BlockPassNode)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[257] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.newArrayNode(((ListNode)yyVals[-1+yyTop]).getPosition(), new HashNode(((ListNode)yyVals[-1+yyTop]).getPosition(), ((ListNode)yyVals[-1+yyTop])));
                    yyVal = support.arg_blk_pass((Node)yyVal, ((BlockPassNode)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[258] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.arg_append(((Node)yyVals[-3+yyTop]), new HashNode(((ListNode)yyVals[-1+yyTop]).getPosition(), ((ListNode)yyVals[-1+yyTop])));
                    yyVal = support.arg_blk_pass((Node)yyVal, ((BlockPassNode)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[259] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
    return yyVal;
  }
};
states[260] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = Long.valueOf(lexer.getCmdArgumentState().begin());
    return yyVal;
  }
};
states[261] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    lexer.getCmdArgumentState().reset(((Long)yyVals[-1+yyTop]).longValue());
                    yyVal = ((Node)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[262] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new BlockPassNode(support.union(((Token)yyVals[-1+yyTop]), ((Node)yyVals[0+yyTop])), ((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[263] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((BlockPassNode)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[265] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    SourcePosition pos = ((Node)yyVals[0+yyTop]) == null ? lexer.getPosition() : ((Node)yyVals[0+yyTop]).getPosition();
                    yyVal = support.newArrayNode(pos, ((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[266] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.newSplatNode(support.union(((Token)yyVals[-1+yyTop]), ((Node)yyVals[0+yyTop])), ((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[267] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    Node node = support.splat_array(((Node)yyVals[-2+yyTop]));

                    if (node != null) {
                        yyVal = support.list_append(node, ((Node)yyVals[0+yyTop]));
                    } else {
                        yyVal = support.arg_append(((Node)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop]));
                    }
    return yyVal;
  }
};
states[268] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    Node node = null;

                    /* FIXME: lose syntactical elements here (and others like this)*/
                    if (((Node)yyVals[0+yyTop]) instanceof ArrayNode &&
                        (node = support.splat_array(((Node)yyVals[-3+yyTop]))) != null) {
                        yyVal = support.list_concat(node, ((Node)yyVals[0+yyTop]));
                    } else {
                        yyVal = support.arg_concat(support.getPosition(((Node)yyVals[-3+yyTop])), ((Node)yyVals[-3+yyTop]), ((Node)yyVals[0+yyTop]));
                    }
    return yyVal;
  }
};
states[269] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                     yyVal = ((Node)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[270] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                     yyVal = ((Node)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[271] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    Node node = support.splat_array(((Node)yyVals[-2+yyTop]));

                    if (node != null) {
                        yyVal = support.list_append(node, ((Node)yyVals[0+yyTop]));
                    } else {
                        yyVal = support.arg_append(((Node)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop]));
                    }
    return yyVal;
  }
};
states[272] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    Node node = null;

                    if (((Node)yyVals[0+yyTop]) instanceof ArrayNode &&
                        (node = support.splat_array(((Node)yyVals[-3+yyTop]))) != null) {
                        yyVal = support.list_concat(node, ((Node)yyVals[0+yyTop]));
                    } else {
                        yyVal = support.arg_concat(support.union(((Node)yyVals[-3+yyTop]), ((Node)yyVals[0+yyTop])), ((Node)yyVals[-3+yyTop]), ((Node)yyVals[0+yyTop]));
                    }
    return yyVal;
  }
};
states[273] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.newSplatNode(support.union(((Token)yyVals[-1+yyTop]), ((Node)yyVals[0+yyTop])), ((Node)yyVals[0+yyTop]));  
    return yyVal;
  }
};
states[280] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                     yyVal = ((ListNode)yyVals[0+yyTop]); /* FIXME: Why complaining without $$ = $1;*/
    return yyVal;
  }
};
states[281] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                     yyVal = ((ListNode)yyVals[0+yyTop]); /* FIXME: Why complaining without $$ = $1;*/
    return yyVal;
  }
};
states[284] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                     yyVal = support.new_fcall(((Token)yyVals[0+yyTop]), null, null);
    return yyVal;
  }
};
states[285] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new BeginNode(support.union(((Token)yyVals[-2+yyTop]), ((Token)yyVals[0+yyTop])), ((Node)yyVals[-1+yyTop]));
    return yyVal;
  }
};
states[286] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    lexer.setState(LexState.EXPR_ENDARG);
    return yyVal;
  }
};
states[287] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = null; /*FIXME: Should be implicit nil?*/
    return yyVal;
  }
};
states[288] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    lexer.setState(LexState.EXPR_ENDARG); 
    return yyVal;
  }
};
states[289] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    support.warning(ID.GROUPED_EXPRESSION, ((Token)yyVals[-3+yyTop]).getPosition(), "(...) interpreted as grouped expression");
                    yyVal = ((Node)yyVals[-2+yyTop]);
                    ((Node)yyVal).setPosition(support.union(((Token)yyVals[-3+yyTop]), ((Token)yyVals[0+yyTop])));
    return yyVal;
  }
};
states[290] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    SourcePosition pos = support.union(((Token)yyVals[-2+yyTop]), ((Token)yyVals[0+yyTop]));
                    Node value = ((Node)yyVals[-1+yyTop]) == null ? new ImplicitNilNode(pos) : ((Node)yyVals[-1+yyTop]);
                    yyVal = value;
                    ((ISourcePositionHolder)yyVal).setPosition(pos);
    return yyVal;
  }
};
states[291] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_colon2(support.union(((Node)yyVals[-2+yyTop]), ((Token)yyVals[0+yyTop])), ((Node)yyVals[-2+yyTop]), (String) ((Token)yyVals[0+yyTop]).getValue());
    return yyVal;
  }
};
states[292] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_colon3(support.union(((Token)yyVals[-1+yyTop]), ((Token)yyVals[0+yyTop])), (String) ((Token)yyVals[0+yyTop]).getValue());
    return yyVal;
  }
};
states[293] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    SourcePosition position = support.union(((Token)yyVals[-2+yyTop]), ((Token)yyVals[0+yyTop]));
                    if (((Node)yyVals[-1+yyTop]) == null) {
                        yyVal = new ZArrayNode(position); /* zero length array */
                    } else {
                        yyVal = ((Node)yyVals[-1+yyTop]);
                        ((ISourcePositionHolder)yyVal).setPosition(position);
                    }
    return yyVal;
  }
};
states[294] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new HashNode(support.union(((Token)yyVals[-2+yyTop]), ((Token)yyVals[0+yyTop])), ((ListNode)yyVals[-1+yyTop]));
    return yyVal;
  }
};
states[295] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new ReturnNode(((Token)yyVals[0+yyTop]).getPosition(), null);
    return yyVal;
  }
};
states[296] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_yield(support.union(((Token)yyVals[-3+yyTop]), ((Token)yyVals[0+yyTop])), ((Node)yyVals[-1+yyTop]));
    return yyVal;
  }
};
states[297] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new ZYieldNode(support.union(((Token)yyVals[-2+yyTop]), ((Token)yyVals[0+yyTop])));
    return yyVal;
  }
};
states[298] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new ZYieldNode(((Token)yyVals[0+yyTop]).getPosition());
    return yyVal;
  }
};
states[299] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new DefinedNode(support.union(((Token)yyVals[-4+yyTop]), ((Token)yyVals[0+yyTop])), ((Node)yyVals[-1+yyTop]));
    return yyVal;
  }
};
states[300] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.getOperatorCallNode(((Token)yyVals[-3+yyTop]), support.getConditionNode(((Node)yyVals[-1+yyTop])));
                    ((ISourcePositionHolder)yyVal).setPosition(support.union(((Token)yyVals[-3+yyTop]), ((Token)yyVals[0+yyTop])));
                    ((CallNode)yyVal).setName("!");
    return yyVal;
  }
};
states[301] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.getOperatorCallNode(((Token)yyVals[-2+yyTop]), null);

                    ((ISourcePositionHolder)yyVal).setPosition(support.union(((Token)yyVals[-2+yyTop]), ((Token)yyVals[0+yyTop])));
                    ((CallNode)yyVal).setName("!");
    return yyVal;
  }
};
states[302] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    support.frobnicate_fcall_args(((FCallNode)yyVals[-1+yyTop]), null, ((IterNode)yyVals[0+yyTop]));
                    yyVal = ((FCallNode)yyVals[-1+yyTop]);                    
    return yyVal;
  }
};
states[304] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    if (((Node)yyVals[-1+yyTop]) != null && 
                          ((BlockAcceptingNode)yyVals[-1+yyTop]).getIter() instanceof BlockPassNode) {
                        throw new SyntaxException(PID.BLOCK_ARG_AND_BLOCK_GIVEN, ((Node)yyVals[-1+yyTop]).getPosition(), lexer.getCurrentLine(), "Both block arg and actual block given.");
                    }
                    ((BlockAcceptingNode)yyVals[-1+yyTop]).setIter(((IterNode)yyVals[0+yyTop]));
                    yyVal = ((Node)yyVals[-1+yyTop]);
                    ((Node)yyVal).setPosition(support.union(((Node)yyVals[-1+yyTop]), ((IterNode)yyVals[0+yyTop])));
    return yyVal;
  }
};
states[305] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((LambdaNode)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[306] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new IfNode(support.union(((Token)yyVals[-5+yyTop]), ((Token)yyVals[0+yyTop])), support.getConditionNode(((Node)yyVals[-4+yyTop])), ((Node)yyVals[-2+yyTop]), ((Node)yyVals[-1+yyTop]));
    return yyVal;
  }
};
states[307] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new IfNode(support.union(((Token)yyVals[-5+yyTop]), ((Token)yyVals[0+yyTop])), support.getConditionNode(((Node)yyVals[-4+yyTop])), ((Node)yyVals[-1+yyTop]), ((Node)yyVals[-2+yyTop]));
    return yyVal;
  }
};
states[308] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    lexer.getConditionState().begin();
    return yyVal;
  }
};
states[309] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    lexer.getConditionState().end();
    return yyVal;
  }
};
states[310] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    Node body = ((Node)yyVals[-1+yyTop]);
                    yyVal = new WhileNode(support.union(((Token)yyVals[-6+yyTop]), ((Token)yyVals[0+yyTop])), support.getConditionNode(((Node)yyVals[-4+yyTop])), body);
    return yyVal;
  }
};
states[311] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                  lexer.getConditionState().begin();
    return yyVal;
  }
};
states[312] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                  lexer.getConditionState().end();
    return yyVal;
  }
};
states[313] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    Node body = ((Node)yyVals[-1+yyTop]);
                    yyVal = new UntilNode(support.union(((Token)yyVals[-6+yyTop]), ((Token)yyVals[0+yyTop])), support.getConditionNode(((Node)yyVals[-4+yyTop])), body);
    return yyVal;
  }
};
states[314] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.newCaseNode(support.union(((Token)yyVals[-4+yyTop]), ((Token)yyVals[0+yyTop])), ((Node)yyVals[-3+yyTop]), ((Node)yyVals[-1+yyTop]));
    return yyVal;
  }
};
states[315] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.newCaseNode(support.union(((Token)yyVals[-3+yyTop]), ((Token)yyVals[0+yyTop])), null, ((Node)yyVals[-1+yyTop]));
    return yyVal;
  }
};
states[316] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    lexer.getConditionState().begin();
    return yyVal;
  }
};
states[317] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    lexer.getConditionState().end();
    return yyVal;
  }
};
states[318] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                      /* ENEBO: Lots of optz in 1.9 parser here*/
                    yyVal = new ForNode(support.union(((Token)yyVals[-8+yyTop]), ((Token)yyVals[0+yyTop])), ((Node)yyVals[-7+yyTop]), ((Node)yyVals[-1+yyTop]), ((Node)yyVals[-4+yyTop]), support.getCurrentScope());
    return yyVal;
  }
};
states[319] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    if (support.isInDef() || support.isInSingle()) {
                        support.yyerror("class definition in method body");
                    }
                    support.pushLocalScope();
    return yyVal;
  }
};
states[320] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    Node body = ((Node)yyVals[-1+yyTop]);

                    yyVal = new ClassNode(support.union(((Token)yyVals[-5+yyTop]), ((Token)yyVals[0+yyTop])), ((Colon3Node)yyVals[-4+yyTop]), support.getCurrentScope(), body, ((Node)yyVals[-3+yyTop]));
                    support.popCurrentScope();
    return yyVal;
  }
};
states[321] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = Boolean.valueOf(support.isInDef());
                    support.setInDef(false);
    return yyVal;
  }
};
states[322] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = Integer.valueOf(support.getInSingle());
                    support.setInSingle(0);
                    support.pushLocalScope();
    return yyVal;
  }
};
states[323] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new SClassNode(support.union(((Token)yyVals[-7+yyTop]), ((Token)yyVals[0+yyTop])), ((Node)yyVals[-5+yyTop]), support.getCurrentScope(), ((Node)yyVals[-1+yyTop]));
                    support.popCurrentScope();
                    support.setInDef(((Boolean)yyVals[-4+yyTop]).booleanValue());
                    support.setInSingle(((Integer)yyVals[-2+yyTop]).intValue());
    return yyVal;
  }
};
states[324] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    if (support.isInDef() || support.isInSingle()) { 
                        support.yyerror("module definition in method body");
                    }
                    support.pushLocalScope();
    return yyVal;
  }
};
states[325] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    Node body = ((Node)yyVals[-1+yyTop]);

                    yyVal = new ModuleNode(support.union(((Token)yyVals[-4+yyTop]), ((Token)yyVals[0+yyTop])), ((Colon3Node)yyVals[-3+yyTop]), support.getCurrentScope(), body);
                    support.popCurrentScope();
    return yyVal;
  }
};
states[326] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    support.setInDef(true);
                    support.pushLocalScope();
    return yyVal;
  }
};
states[327] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    Node body = ((Node)yyVals[-1+yyTop]);

                    yyVal = new DefnNode(support.union(((Token)yyVals[-5+yyTop]), ((Token)yyVals[0+yyTop])), new MethodNameNode(((Token)yyVals[-4+yyTop]).getPosition(), (String) ((Token)yyVals[-4+yyTop]).getValue()), ((ArgsNode)yyVals[-2+yyTop]), support.getCurrentScope(), body);
                    support.popCurrentScope();
                    support.setInDef(false);
    return yyVal;
  }
};
states[328] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    lexer.setState(LexState.EXPR_FNAME);
    return yyVal;
  }
};
states[329] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    support.setInSingle(support.getInSingle() + 1);
                    support.pushLocalScope();
                    lexer.setState(LexState.EXPR_ENDFN); /* force for args */
    return yyVal;
  }
};
states[330] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    Node body = ((Node)yyVals[-1+yyTop]);

                    yyVal = new DefsNode(support.union(((Token)yyVals[-8+yyTop]), ((Token)yyVals[0+yyTop])), ((Node)yyVals[-7+yyTop]), new MethodNameNode(((Token)yyVals[-4+yyTop]).getPosition(), (String) ((Token)yyVals[-4+yyTop]).getValue()), ((ArgsNode)yyVals[-2+yyTop]), support.getCurrentScope(), body);
                    support.popCurrentScope();
                    support.setInSingle(support.getInSingle() - 1);
    return yyVal;
  }
};
states[331] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new BreakNode(((Token)yyVals[0+yyTop]).getPosition(), null);
    return yyVal;
  }
};
states[332] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new NextNode(((Token)yyVals[0+yyTop]).getPosition(), null);
    return yyVal;
  }
};
states[333] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new RedoNode(((Token)yyVals[0+yyTop]).getPosition());
    return yyVal;
  }
};
states[334] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new RetryNode(((Token)yyVals[0+yyTop]).getPosition());
    return yyVal;
  }
};
states[335] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    support.checkExpression(((Node)yyVals[0+yyTop]));
                    yyVal = ((Node)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[342] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new IfNode(((Token)yyVals[-4+yyTop]).getPosition(), support.getConditionNode(((Node)yyVals[-3+yyTop])), ((Node)yyVals[-1+yyTop]), ((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[344] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((Node)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[346] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
    return yyVal;
  }
};
states[347] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                     yyVal = support.assignable(((Token)yyVals[0+yyTop]), null);
    return yyVal;
  }
};
states[348] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((Node)yyVals[-1+yyTop]);
                    ((Node)yyVal).setPosition(support.union(((Token)yyVals[-2+yyTop]), ((Token)yyVals[0+yyTop])));
    return yyVal;
  }
};
states[349] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.newArrayNode(((Node)yyVals[0+yyTop]).getPosition(), ((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[350] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((ListNode)yyVals[-2+yyTop]).add(((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[351] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new MultipleAsgnNode(((ListNode)yyVals[0+yyTop]).getPosition(), ((ListNode)yyVals[0+yyTop]), null, null);
    return yyVal;
  }
};
states[352] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new MultipleAsgnNode(((ListNode)yyVals[-3+yyTop]).getPosition(), ((ListNode)yyVals[-3+yyTop]), support.assignable(((Token)yyVals[0+yyTop]), null), null);
    return yyVal;
  }
};
states[353] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new MultipleAsgnNode(((ListNode)yyVals[-5+yyTop]).getPosition(), ((ListNode)yyVals[-5+yyTop]), support.assignable(((Token)yyVals[-2+yyTop]), null), ((ListNode)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[354] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new MultipleAsgnNode(((ListNode)yyVals[-2+yyTop]).getPosition(), ((ListNode)yyVals[-2+yyTop]), new StarNode(lexer.getPosition()), null);
    return yyVal;
  }
};
states[355] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new MultipleAsgnNode(((ListNode)yyVals[-4+yyTop]).getPosition(), ((ListNode)yyVals[-4+yyTop]), new StarNode(lexer.getPosition()), ((ListNode)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[356] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new MultipleAsgnNode(((Token)yyVals[-1+yyTop]).getPosition(), null, support.assignable(((Token)yyVals[0+yyTop]), null), null);
    return yyVal;
  }
};
states[357] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new MultipleAsgnNode(((Token)yyVals[-3+yyTop]).getPosition(), null, support.assignable(((Token)yyVals[-2+yyTop]), null), ((ListNode)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[358] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new MultipleAsgnNode(((Token)yyVals[0+yyTop]).getPosition(), null, new StarNode(lexer.getPosition()), null);
    return yyVal;
  }
};
states[359] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new MultipleAsgnNode(((Token)yyVals[-2+yyTop]).getPosition(), null, null, ((ListNode)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[360] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_args_tail(((ListNode)yyVals[-3+yyTop]).getPosition(), ((ListNode)yyVals[-3+yyTop]), ((Token)yyVals[-1+yyTop]), ((BlockArgNode)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[361] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_args_tail(((ListNode)yyVals[-1+yyTop]).getPosition(), ((ListNode)yyVals[-1+yyTop]), null, ((BlockArgNode)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[362] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_args_tail(((Token)yyVals[-1+yyTop]).getPosition(), null, ((Token)yyVals[-1+yyTop]), ((BlockArgNode)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[363] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_args_tail(((BlockArgNode)yyVals[0+yyTop]).getPosition(), null, null, ((BlockArgNode)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[364] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((ArgsTailHolder)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[365] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_args_tail(lexer.getPosition(), null, null, null);
    return yyVal;
  }
};
states[366] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_args(support.union(((ListNode)yyVals[-5+yyTop]), ((ArgsTailHolder)yyVals[0+yyTop])), ((ListNode)yyVals[-5+yyTop]), ((ListNode)yyVals[-3+yyTop]), ((RestArgNode)yyVals[-1+yyTop]), null, ((ArgsTailHolder)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[367] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_args(support.union(((ListNode)yyVals[-7+yyTop]), ((ArgsTailHolder)yyVals[0+yyTop])), ((ListNode)yyVals[-7+yyTop]), ((ListNode)yyVals[-5+yyTop]), ((RestArgNode)yyVals[-3+yyTop]), ((ListNode)yyVals[-1+yyTop]), ((ArgsTailHolder)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[368] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_args(support.union(((ListNode)yyVals[-3+yyTop]), ((ArgsTailHolder)yyVals[0+yyTop])), ((ListNode)yyVals[-3+yyTop]), ((ListNode)yyVals[-1+yyTop]), null, null, ((ArgsTailHolder)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[369] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_args(support.union(((ListNode)yyVals[-5+yyTop]), ((ArgsTailHolder)yyVals[0+yyTop])), ((ListNode)yyVals[-5+yyTop]), ((ListNode)yyVals[-3+yyTop]), null, ((ListNode)yyVals[-1+yyTop]), ((ArgsTailHolder)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[370] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_args(support.union(((ListNode)yyVals[-3+yyTop]), ((ArgsTailHolder)yyVals[0+yyTop])), ((ListNode)yyVals[-3+yyTop]), null, ((RestArgNode)yyVals[-1+yyTop]), null, ((ArgsTailHolder)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[371] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    RestArgNode rest = new UnnamedRestArgNode(((ListNode)yyVals[-1+yyTop]).getPosition(), null, support.getCurrentScope().addVariable("*"));
                    yyVal = support.new_args(((ListNode)yyVals[-1+yyTop]).getPosition(), ((ListNode)yyVals[-1+yyTop]), null, rest, null, (ArgsTailHolder) null);
    return yyVal;
  }
};
states[372] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_args(support.union(((ListNode)yyVals[-5+yyTop]), ((ArgsTailHolder)yyVals[0+yyTop])), ((ListNode)yyVals[-5+yyTop]), null, ((RestArgNode)yyVals[-3+yyTop]), ((ListNode)yyVals[-1+yyTop]), ((ArgsTailHolder)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[373] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_args(support.union(((ListNode)yyVals[-1+yyTop]), ((ArgsTailHolder)yyVals[0+yyTop])), ((ListNode)yyVals[-1+yyTop]), null, null, null, ((ArgsTailHolder)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[374] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_args(support.union(((ListNode)yyVals[-3+yyTop]), ((ArgsTailHolder)yyVals[0+yyTop])), null, ((ListNode)yyVals[-3+yyTop]), ((RestArgNode)yyVals[-1+yyTop]), null, ((ArgsTailHolder)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[375] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_args(support.union(((ListNode)yyVals[-5+yyTop]), ((ArgsTailHolder)yyVals[0+yyTop])), null, ((ListNode)yyVals[-5+yyTop]), ((RestArgNode)yyVals[-3+yyTop]), ((ListNode)yyVals[-1+yyTop]), ((ArgsTailHolder)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[376] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_args(support.union(((ListNode)yyVals[-1+yyTop]), ((ArgsTailHolder)yyVals[0+yyTop])), null, ((ListNode)yyVals[-1+yyTop]), null, null, ((ArgsTailHolder)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[377] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_args(support.union(((ListNode)yyVals[-3+yyTop]), ((ArgsTailHolder)yyVals[0+yyTop])), null, ((ListNode)yyVals[-3+yyTop]), null, ((ListNode)yyVals[-1+yyTop]), ((ArgsTailHolder)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[378] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_args(support.union(((RestArgNode)yyVals[-1+yyTop]), ((ArgsTailHolder)yyVals[0+yyTop])), null, null, ((RestArgNode)yyVals[-1+yyTop]), null, ((ArgsTailHolder)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[379] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_args(support.union(((RestArgNode)yyVals[-3+yyTop]), ((ArgsTailHolder)yyVals[0+yyTop])), null, null, ((RestArgNode)yyVals[-3+yyTop]), ((ListNode)yyVals[-1+yyTop]), ((ArgsTailHolder)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[380] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_args(((ArgsTailHolder)yyVals[0+yyTop]).getPosition(), null, null, null, null, ((ArgsTailHolder)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[381] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
    /* was $$ = null;*/
                    yyVal = support.new_args(lexer.getPosition(), null, null, null, null, (ArgsTailHolder) null);
    return yyVal;
  }
};
states[382] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    lexer.commandStart = true;
                    yyVal = ((ArgsNode)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[383] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_args(support.union(((Token)yyVals[-2+yyTop]), ((Token)yyVals[0+yyTop])), null, null, null, null, (BlockArgNode) null);
                    ((ArgsNode)yyVal).setShadow(((ListNode)yyVals[-1+yyTop]));
    return yyVal;
  }
};
states[384] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_args(((Token)yyVals[0+yyTop]).getPosition(), null, null, null, null, (BlockArgNode) null);
    return yyVal;
  }
};
states[385] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    ((Node)yyVals[-2+yyTop]).setPosition(support.union(((Token)yyVals[-3+yyTop]), ((Token)yyVals[0+yyTop])));
                    ((ArgsNode)yyVals[-2+yyTop]).setShadow(((ListNode)yyVals[-1+yyTop]));
                    yyVal = ((ArgsNode)yyVals[-2+yyTop]);
    return yyVal;
  }
};
states[386] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = null;
    return yyVal;
  }
};
states[387] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((ListNode)yyVals[-1+yyTop]);
    return yyVal;
  }
};
states[388] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new ArrayNode(((Node)yyVals[0+yyTop]).getPosition(), ((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[389] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((ListNode)yyVals[-2+yyTop]).add(((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[390] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_bv(((Token)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[391] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = null;
    return yyVal;
  }
};
states[392] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    support.pushBlockScope();
                    yyVal = lexer.getLeftParenBegin();
                    lexer.setLeftParenBegin(lexer.incrementParenNest());
    return yyVal;
  }
};
states[393] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new LambdaNode(support.union(((ArgsNode)yyVals[-1+yyTop]), ((Node)yyVals[0+yyTop])), ((ArgsNode)yyVals[-1+yyTop]), ((Node)yyVals[0+yyTop]), support.getCurrentScope());
                    support.popCurrentScope();
                    lexer.setLeftParenBegin(((Integer)yyVals[-2+yyTop]));
    return yyVal;
  }
};
states[394] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((ArgsNode)yyVals[-2+yyTop]);
                    ((ISourcePositionHolder)yyVal).setPosition(support.union(((Token)yyVals[-3+yyTop]), ((Token)yyVals[0+yyTop])));
    return yyVal;
  }
};
states[395] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((ArgsNode)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[396] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((Node)yyVals[-1+yyTop]);
    return yyVal;
  }
};
states[397] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((Node)yyVals[-1+yyTop]);
    return yyVal;
  }
};
states[398] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    support.pushBlockScope();
    return yyVal;
  }
};
states[399] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new IterNode(support.union(((Token)yyVals[-4+yyTop]), ((Token)yyVals[0+yyTop])), ((ArgsNode)yyVals[-2+yyTop]), ((Node)yyVals[-1+yyTop]), support.getCurrentScope());
                    support.popCurrentScope();
    return yyVal;
  }
};
states[400] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    /* Workaround for JRUBY-2326 (MRI does not enter this production for some reason)*/
                    if (((Node)yyVals[-1+yyTop]) instanceof YieldNode) {
                        throw new SyntaxException(PID.BLOCK_GIVEN_TO_YIELD, ((Node)yyVals[-1+yyTop]).getPosition(), lexer.getCurrentLine(), "block given to yield");
                    }
                    if (((BlockAcceptingNode)yyVals[-1+yyTop]).getIter() instanceof BlockPassNode) {
                        throw new SyntaxException(PID.BLOCK_ARG_AND_BLOCK_GIVEN, ((Node)yyVals[-1+yyTop]).getPosition(), lexer.getCurrentLine(), "Both block arg and actual block given.");
                    }
                    ((BlockAcceptingNode)yyVals[-1+yyTop]).setIter(((IterNode)yyVals[0+yyTop]));
                    yyVal = ((Node)yyVals[-1+yyTop]);
                    ((Node)yyVal).setPosition(support.union(((Node)yyVals[-1+yyTop]), ((IterNode)yyVals[0+yyTop])));
    return yyVal;
  }
};
states[401] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_call(((Node)yyVals[-3+yyTop]), ((Token)yyVals[-1+yyTop]), ((Node)yyVals[0+yyTop]), null);
    return yyVal;
  }
};
states[402] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_call(((Node)yyVals[-4+yyTop]), ((Token)yyVals[-2+yyTop]), ((Node)yyVals[-1+yyTop]), ((IterNode)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[403] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_call(((Node)yyVals[-4+yyTop]), ((Token)yyVals[-2+yyTop]), ((Node)yyVals[-1+yyTop]), ((IterNode)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[404] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    support.frobnicate_fcall_args(((FCallNode)yyVals[-1+yyTop]), ((Node)yyVals[0+yyTop]), null);
                    yyVal = ((FCallNode)yyVals[-1+yyTop]);
    return yyVal;
  }
};
states[405] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_call(((Node)yyVals[-3+yyTop]), ((Token)yyVals[-1+yyTop]), ((Node)yyVals[0+yyTop]), null);
    return yyVal;
  }
};
states[406] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_call(((Node)yyVals[-3+yyTop]), ((Token)yyVals[-1+yyTop]), ((Node)yyVals[0+yyTop]), null);
    return yyVal;
  }
};
states[407] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_call(((Node)yyVals[-2+yyTop]), ((Token)yyVals[0+yyTop]), null, null);
    return yyVal;
  }
};
states[408] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_call(((Node)yyVals[-2+yyTop]), new Token("call", ((Node)yyVals[-2+yyTop]).getPosition()), ((Node)yyVals[0+yyTop]), null);
    return yyVal;
  }
};
states[409] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_call(((Node)yyVals[-2+yyTop]), new Token("call", ((Node)yyVals[-2+yyTop]).getPosition()), ((Node)yyVals[0+yyTop]), null);
    return yyVal;
  }
};
states[410] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_super(((Node)yyVals[0+yyTop]), ((Token)yyVals[-1+yyTop]));
    return yyVal;
  }
};
states[411] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new ZSuperNode(((Token)yyVals[0+yyTop]).getPosition());
    return yyVal;
  }
};
states[412] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    if (((Node)yyVals[-3+yyTop]) instanceof SelfNode) {
                      yyVal = support.new_fcall(new Token("[]", support.union(((Node)yyVals[-3+yyTop]), ((Token)yyVals[0+yyTop]))), ((Node)yyVals[-1+yyTop]), null);
                    } else {
                      yyVal = support.new_call(((Node)yyVals[-3+yyTop]), new Token("[]", support.union(((Node)yyVals[-3+yyTop]), ((Token)yyVals[0+yyTop]))), ((Node)yyVals[-1+yyTop]), null);
                    }
    return yyVal;
  }
};
states[413] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    support.pushBlockScope();
    return yyVal;
  }
};
states[414] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new IterNode(support.union(((Token)yyVals[-4+yyTop]), ((Token)yyVals[0+yyTop])), ((ArgsNode)yyVals[-2+yyTop]), ((Node)yyVals[-1+yyTop]), support.getCurrentScope());
                    support.popCurrentScope();
    return yyVal;
  }
};
states[415] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    support.pushBlockScope();
    return yyVal;
  }
};
states[416] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new IterNode(support.union(((Token)yyVals[-4+yyTop]), ((Token)yyVals[0+yyTop])), ((ArgsNode)yyVals[-2+yyTop]), ((Node)yyVals[-1+yyTop]), support.getCurrentScope());
                    support.popCurrentScope();
    return yyVal;
  }
};
states[417] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.newWhenNode(support.union(((Token)yyVals[-4+yyTop]), support.unwrapNewlineNode(((Node)yyVals[-1+yyTop]))), ((Node)yyVals[-3+yyTop]), ((Node)yyVals[-1+yyTop]), ((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[420] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    Node node;
                    if (((Node)yyVals[-3+yyTop]) != null) {
                        node = support.appendToBlock(support.node_assign(((Node)yyVals[-3+yyTop]), new GlobalVarNode(((Token)yyVals[-5+yyTop]).getPosition(), "$!")), ((Node)yyVals[-1+yyTop]));
                        if (((Node)yyVals[-1+yyTop]) != null) {
                            node.setPosition(support.unwrapNewlineNode(((Node)yyVals[-1+yyTop])).getPosition());
                        }
                    } else {
                        node = ((Node)yyVals[-1+yyTop]);
                    }
                    Node body = node;
                    yyVal = new RescueBodyNode(((Token)yyVals[-5+yyTop]).getPosition(), ((Node)yyVals[-4+yyTop]), body, ((RescueBodyNode)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[421] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = null; 
    return yyVal;
  }
};
states[422] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.newArrayNode(((Node)yyVals[0+yyTop]).getPosition(), ((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[423] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.splat_array(((Node)yyVals[0+yyTop]));
                    if (yyVal == null) yyVal = ((Node)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[425] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((Node)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[427] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((Node)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[429] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((NumericNode)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[430] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    /* FIXME: We may be intern'ing more than once.*/
                    yyVal = new SymbolNode(((Token)yyVals[0+yyTop]).getPosition(), ((String) ((Token)yyVals[0+yyTop]).getValue()).intern());
    return yyVal;
  }
};
states[432] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((Node)yyVals[0+yyTop]) instanceof EvStrNode ? new DStrNode(((Node)yyVals[0+yyTop]).getPosition()).add(((Node)yyVals[0+yyTop])) : ((Node)yyVals[0+yyTop]);
                    /*
                    NODE *node = $1;
                    if (!node) {
                        node = NEW_STR(STR_NEW0());
                    } else {
                        node = evstr2dstr(node);
                    }
                    $$ = node;
                    */
    return yyVal;
  }
};
states[433] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new StrNode(((Token)yyVals[-1+yyTop]).getPosition(), (String) ((Token)yyVals[0+yyTop]).getValue());
    return yyVal;
  }
};
states[434] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((Node)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[435] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((Node)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[436] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.literal_concat(((Node)yyVals[-1+yyTop]).getPosition(), ((Node)yyVals[-1+yyTop]), ((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[437] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((Node)yyVals[-1+yyTop]);

                    ((ISourcePositionHolder)yyVal).setPosition(support.union(((Token)yyVals[-2+yyTop]), ((Token)yyVals[0+yyTop])));
                    int extraLength = ((String) ((Token)yyVals[-2+yyTop]).getValue()).length() - 1;

                    /* We may need to subtract addition offset off of first */
                    /* string fragment (we optimistically take one off in*/
                    /* ParserSupport.literal_concat).  Check token length*/
                    /* and subtract as neeeded.*/
                    if ((((Node)yyVals[-1+yyTop]) instanceof DStrNode) && extraLength > 0) {
                      Node strNode = ((DStrNode)((Node)yyVals[-1+yyTop])).get(0);
                    }
    return yyVal;
  }
};
states[438] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    SourcePosition position = support.union(((Token)yyVals[-2+yyTop]), ((Token)yyVals[0+yyTop]));

                    if (((Node)yyVals[-1+yyTop]) == null) {
                        yyVal = new XStrNode(position, null);
                    } else if (((Node)yyVals[-1+yyTop]) instanceof StrNode) {
                        yyVal = new XStrNode(position, ((StrNode)yyVals[-1+yyTop]).getValue());
                    } else if (((Node)yyVals[-1+yyTop]) instanceof DStrNode) {
                        yyVal = new DXStrNode(position, ((DStrNode)yyVals[-1+yyTop]));

                        ((Node)yyVal).setPosition(position);
                    } else {
                        yyVal = new DXStrNode(position).add(((Node)yyVals[-1+yyTop]));
                    }
    return yyVal;
  }
};
states[439] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.newRegexpNode(support.union(((Token)yyVals[-2+yyTop]), ((RegexpNode)yyVals[0+yyTop])), ((Node)yyVals[-1+yyTop]), (RegexpNode) ((RegexpNode)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[440] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new ZArrayNode(support.union(((Token)yyVals[-2+yyTop]), ((Token)yyVals[0+yyTop])));
    return yyVal;
  }
};
states[441] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((ListNode)yyVals[-1+yyTop]);
    return yyVal;
  }
};
states[442] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new ArrayNode(lexer.getPosition());
    return yyVal;
  }
};
states[443] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                     yyVal = ((ListNode)yyVals[-2+yyTop]).add(((Node)yyVals[-1+yyTop]) instanceof EvStrNode ? new DStrNode(((ListNode)yyVals[-2+yyTop]).getPosition()).add(((Node)yyVals[-1+yyTop])) : ((Node)yyVals[-1+yyTop]));
    return yyVal;
  }
};
states[445] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                     yyVal = support.literal_concat(support.getPosition(((Node)yyVals[-1+yyTop])), ((Node)yyVals[-1+yyTop]), ((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[446] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new ArrayNode(lexer.getPosition());
    return yyVal;
  }
};
states[447] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((ListNode)yyVals[-1+yyTop]);
    return yyVal;
  }
};
states[448] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new ArrayNode(lexer.getPosition());
    return yyVal;
  }
};
states[449] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((ListNode)yyVals[-2+yyTop]).add(((Node)yyVals[-1+yyTop]) instanceof EvStrNode ? new DSymbolNode(((ListNode)yyVals[-2+yyTop]).getPosition()).add(((Node)yyVals[-1+yyTop])) : support.asSymbol(((ListNode)yyVals[-2+yyTop]).getPosition(), ((Node)yyVals[-1+yyTop])));
    return yyVal;
  }
};
states[450] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                     yyVal = new ZArrayNode(support.union(((Token)yyVals[-2+yyTop]), ((Token)yyVals[0+yyTop])));
    return yyVal;
  }
};
states[451] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((ListNode)yyVals[-1+yyTop]);
                    ((ISourcePositionHolder)yyVal).setPosition(support.union(((Token)yyVals[-2+yyTop]), ((Token)yyVals[0+yyTop])));
    return yyVal;
  }
};
states[452] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new ZArrayNode(support.union(((Token)yyVals[-2+yyTop]), ((Token)yyVals[0+yyTop])));
    return yyVal;
  }
};
states[453] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((ListNode)yyVals[-1+yyTop]);
                    ((ISourcePositionHolder)yyVal).setPosition(support.union(((Token)yyVals[-2+yyTop]), ((Token)yyVals[0+yyTop])));
    return yyVal;
  }
};
states[454] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new ArrayNode(lexer.getPosition());
    return yyVal;
  }
};
states[455] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((ListNode)yyVals[-2+yyTop]).add(((Node)yyVals[-1+yyTop]));
    return yyVal;
  }
};
states[456] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new ArrayNode(lexer.getPosition());
    return yyVal;
  }
};
states[457] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((ListNode)yyVals[-2+yyTop]).add(support.asSymbol(((ListNode)yyVals[-2+yyTop]).getPosition(), ((Node)yyVals[-1+yyTop])));
    return yyVal;
  }
};
states[458] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new StrNode(((Token)yyVals[0+yyTop]).getPosition(), "");
    return yyVal;
  }
};
states[459] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.literal_concat(((Node)yyVals[-1+yyTop]).getPosition(), ((Node)yyVals[-1+yyTop]), ((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[460] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = null;
    return yyVal;
  }
};
states[461] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.literal_concat(support.getPosition(((Node)yyVals[-1+yyTop])), ((Node)yyVals[-1+yyTop]), ((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[462] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = null;
    return yyVal;
  }
};
states[463] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
    /* FIXME: mri is different here.*/
                    yyVal = support.literal_concat(support.getPosition(((Node)yyVals[-1+yyTop])), ((Node)yyVals[-1+yyTop]), ((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[464] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((Node)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[465] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = lexer.getStrTerm();
                    lexer.setStrTerm(null);
                    lexer.setState(LexState.EXPR_BEG);
    return yyVal;
  }
};
states[466] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    lexer.setStrTerm(((StrTerm)yyVals[-1+yyTop]));
                    yyVal = new EvStrNode(support.union(((Token)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop])), ((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[467] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                   yyVal = lexer.getStrTerm();
                   lexer.getConditionState().stop();
                   lexer.getCmdArgumentState().stop();
                   lexer.setStrTerm(null);
                   lexer.setState(LexState.EXPR_BEG);
    return yyVal;
  }
};
states[468] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                   lexer.getConditionState().restart();
                   lexer.getCmdArgumentState().restart();
                   lexer.setStrTerm(((StrTerm)yyVals[-2+yyTop]));

                   yyVal = support.newEvStrNode(support.union(((Token)yyVals[-3+yyTop]), ((Node)yyVals[-1+yyTop])), ((Node)yyVals[-1+yyTop]));
    return yyVal;
  }
};
states[469] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                     yyVal = new GlobalVarNode(((Token)yyVals[0+yyTop]).getPosition(), (String) ((Token)yyVals[0+yyTop]).getValue());
    return yyVal;
  }
};
states[470] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                     yyVal = new InstVarNode(((Token)yyVals[0+yyTop]).getPosition(), (String) ((Token)yyVals[0+yyTop]).getValue());
    return yyVal;
  }
};
states[471] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                     yyVal = new ClassVarNode(((Token)yyVals[0+yyTop]).getPosition(), (String) ((Token)yyVals[0+yyTop]).getValue());
    return yyVal;
  }
};
states[473] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                     lexer.setState(LexState.EXPR_END);
                     yyVal = ((Token)yyVals[0+yyTop]);
                     ((ISourcePositionHolder)yyVal).setPosition(support.union(((Token)yyVals[-1+yyTop]), ((Token)yyVals[0+yyTop])));
    return yyVal;
  }
};
states[478] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                     lexer.setState(LexState.EXPR_END);

                     /* DStrNode: :"some text #{some expression}"*/
                     /* StrNode: :"some text"*/
                     /* EvStrNode :"#{some expression}"*/
                     /* Ruby 1.9 allows empty strings as symbols*/
                     if (((Node)yyVals[-1+yyTop]) == null) {
                         yyVal = new SymbolNode(((Token)yyVals[-2+yyTop]).getPosition(), "");
                     } else if (((Node)yyVals[-1+yyTop]) instanceof DStrNode) {
                         yyVal = new DSymbolNode(support.union(((Token)yyVals[-2+yyTop]), ((Token)yyVals[0+yyTop])), ((DStrNode)yyVals[-1+yyTop]));
                     } else if (((Node)yyVals[-1+yyTop]) instanceof StrNode) {
                         yyVal = new SymbolNode(support.union(((Token)yyVals[-2+yyTop]), ((Token)yyVals[0+yyTop])), ((StrNode)yyVals[-1+yyTop]).getValue().toString().intern());
                     } else {
                         SourcePosition position = support.union(((Node)yyVals[-1+yyTop]), ((Token)yyVals[0+yyTop]));

                         /* We substract one since tsymbeg is longer than one*/
                         /* and we cannot union it directly so we assume quote*/
                         /* is one character long and subtract for it.*/
                         position.adjustStartOffset(-1);
                         ((Node)yyVals[-1+yyTop]).setPosition(position);

                         yyVal = new DSymbolNode(support.union(((Token)yyVals[-2+yyTop]), ((Token)yyVals[0+yyTop])));
                         ((DSymbolNode)yyVal).add(((Node)yyVals[-1+yyTop]));
                     }
    return yyVal;
  }
};
states[479] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((NumericNode)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[480] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.negateNumeric(((NumericNode)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[481] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((Node)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[482] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((FloatNode)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[483] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((RationalNode)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[484] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((Node)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[490] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new Token("nil", Tokens.kNIL, ((Token)yyVals[0+yyTop]).getPosition());
    return yyVal;
  }
};
states[491] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new Token("self", Tokens.kSELF, ((Token)yyVals[0+yyTop]).getPosition());
    return yyVal;
  }
};
states[492] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new Token("true", Tokens.kTRUE, ((Token)yyVals[0+yyTop]).getPosition());
    return yyVal;
  }
};
states[493] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new Token("false", Tokens.kFALSE, ((Token)yyVals[0+yyTop]).getPosition());
    return yyVal;
  }
};
states[494] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new Token("__FILE__", Tokens.k__FILE__, ((Token)yyVals[0+yyTop]).getPosition());
    return yyVal;
  }
};
states[495] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new Token("__LINE__", Tokens.k__LINE__, ((Token)yyVals[0+yyTop]).getPosition());
    return yyVal;
  }
};
states[496] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new Token("__ENCODING__", Tokens.k__ENCODING__, ((Token)yyVals[0+yyTop]).getPosition());
    return yyVal;
  }
};
states[497] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.gettable(((Token)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[498] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.gettable(((Token)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[499] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.assignable(((Token)yyVals[0+yyTop]), null);
    return yyVal;
  }
};
states[500] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.assignable(((Token)yyVals[0+yyTop]), null);
    return yyVal;
  }
};
states[501] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((Node)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[502] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((Node)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[503] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = null;
    return yyVal;
  }
};
states[504] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                   lexer.setState(LexState.EXPR_BEG);
    return yyVal;
  }
};
states[505] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((Node)yyVals[-1+yyTop]);
    return yyVal;
  }
};
states[506] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                   yyVal = null;
    return yyVal;
  }
};
states[507] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((ArgsNode)yyVals[-1+yyTop]);
                    ((ISourcePositionHolder)yyVal).setPosition(support.union(((Token)yyVals[-2+yyTop]), ((Token)yyVals[0+yyTop])));
                    lexer.setState(LexState.EXPR_BEG);
                    lexer.commandStart = true;
    return yyVal;
  }
};
states[508] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((ArgsNode)yyVals[-1+yyTop]);
                    lexer.setState(LexState.EXPR_BEG);
                    lexer.commandStart = true;
    return yyVal;
  }
};
states[509] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_args_tail(support.union(((ListNode)yyVals[-3+yyTop]), ((BlockArgNode)yyVals[0+yyTop])), ((ListNode)yyVals[-3+yyTop]), ((Token)yyVals[-1+yyTop]), ((BlockArgNode)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[510] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_args_tail(support.union(((ListNode)yyVals[-1+yyTop]), ((BlockArgNode)yyVals[0+yyTop])), ((ListNode)yyVals[-1+yyTop]), null, ((BlockArgNode)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[511] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_args_tail(support.union(((Token)yyVals[-1+yyTop]), ((BlockArgNode)yyVals[0+yyTop])), null, ((Token)yyVals[-1+yyTop]), ((BlockArgNode)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[512] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_args_tail(((BlockArgNode)yyVals[0+yyTop]).getPosition(), null, null, ((BlockArgNode)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[513] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((ArgsTailHolder)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[514] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_args_tail(lexer.getPosition(), null, null, null);
    return yyVal;
  }
};
states[515] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_args(support.union(((ListNode)yyVals[-5+yyTop]), ((ArgsTailHolder)yyVals[0+yyTop])), ((ListNode)yyVals[-5+yyTop]), ((ListNode)yyVals[-3+yyTop]), ((RestArgNode)yyVals[-1+yyTop]), null, ((ArgsTailHolder)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[516] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_args(support.union(((ListNode)yyVals[-7+yyTop]), ((ArgsTailHolder)yyVals[0+yyTop])), ((ListNode)yyVals[-7+yyTop]), ((ListNode)yyVals[-5+yyTop]), ((RestArgNode)yyVals[-3+yyTop]), ((ListNode)yyVals[-1+yyTop]), ((ArgsTailHolder)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[517] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_args(support.union(((ListNode)yyVals[-3+yyTop]), ((ArgsTailHolder)yyVals[0+yyTop])), ((ListNode)yyVals[-3+yyTop]), ((ListNode)yyVals[-1+yyTop]), null, null, ((ArgsTailHolder)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[518] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_args(support.union(((ListNode)yyVals[-5+yyTop]), ((ArgsTailHolder)yyVals[0+yyTop])), ((ListNode)yyVals[-5+yyTop]), ((ListNode)yyVals[-3+yyTop]), null, ((ListNode)yyVals[-1+yyTop]), ((ArgsTailHolder)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[519] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_args(support.union(((ListNode)yyVals[-3+yyTop]), ((ArgsTailHolder)yyVals[0+yyTop])), ((ListNode)yyVals[-3+yyTop]), null, ((RestArgNode)yyVals[-1+yyTop]), null, ((ArgsTailHolder)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[520] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_args(support.union(((ListNode)yyVals[-5+yyTop]), ((ArgsTailHolder)yyVals[0+yyTop])), ((ListNode)yyVals[-5+yyTop]), null, ((RestArgNode)yyVals[-3+yyTop]), ((ListNode)yyVals[-1+yyTop]), ((ArgsTailHolder)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[521] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_args(support.union(((ListNode)yyVals[-1+yyTop]), ((ArgsTailHolder)yyVals[0+yyTop])), ((ListNode)yyVals[-1+yyTop]), null, null, null, ((ArgsTailHolder)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[522] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_args(support.union(((ListNode)yyVals[-3+yyTop]), ((ArgsTailHolder)yyVals[0+yyTop])), null, ((ListNode)yyVals[-3+yyTop]), ((RestArgNode)yyVals[-1+yyTop]), null, ((ArgsTailHolder)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[523] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_args(support.union(((ListNode)yyVals[-5+yyTop]), ((ArgsTailHolder)yyVals[0+yyTop])), null, ((ListNode)yyVals[-5+yyTop]), ((RestArgNode)yyVals[-3+yyTop]), ((ListNode)yyVals[-1+yyTop]), ((ArgsTailHolder)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[524] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_args(support.union(((ListNode)yyVals[-1+yyTop]), ((ArgsTailHolder)yyVals[0+yyTop])), null, ((ListNode)yyVals[-1+yyTop]), null, null, ((ArgsTailHolder)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[525] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_args(support.union(((ListNode)yyVals[-3+yyTop]), ((ArgsTailHolder)yyVals[0+yyTop])), null, ((ListNode)yyVals[-3+yyTop]), null, ((ListNode)yyVals[-1+yyTop]), ((ArgsTailHolder)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[526] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_args(support.union(((RestArgNode)yyVals[-1+yyTop]), ((ArgsTailHolder)yyVals[0+yyTop])), null, null, ((RestArgNode)yyVals[-1+yyTop]), null, ((ArgsTailHolder)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[527] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_args(support.union(((RestArgNode)yyVals[-3+yyTop]), ((ArgsTailHolder)yyVals[0+yyTop])), null, null, ((RestArgNode)yyVals[-3+yyTop]), ((ListNode)yyVals[-1+yyTop]), ((ArgsTailHolder)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[528] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_args(((ArgsTailHolder)yyVals[0+yyTop]).getPosition(), null, null, null, null, ((ArgsTailHolder)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[529] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.new_args(lexer.getPosition(), null, null, null, null, (ArgsTailHolder) null);
    return yyVal;
  }
};
states[530] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    support.yyerror("formal argument cannot be a constant");
    return yyVal;
  }
};
states[531] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    support.yyerror("formal argument cannot be an instance variable");
    return yyVal;
  }
};
states[532] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    support.yyerror("formal argument cannot be a global variable");
    return yyVal;
  }
};
states[533] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    support.yyerror("formal argument cannot be a class variable");
    return yyVal;
  }
};
states[535] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.formal_argument(((Token)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[536] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.arg_var(((Token)yyVals[0+yyTop]));
  /*
                    $$ = new ArgAuxiliaryNode($1.getPosition(), (String) $1.getValue(), 1);
  */
    return yyVal;
  }
};
states[537] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((Node)yyVals[-1+yyTop]);
                    ((Node)yyVal).setPosition(support.union(((Token)yyVals[-2+yyTop]), ((Token)yyVals[0+yyTop])));
                    /*            {
            ID tid = internal_id();
            arg_var(tid);
            if (dyna_in_block()) {
                $2->nd_value = NEW_DVAR(tid);
            }
            else {
                $2->nd_value = NEW_LVAR(tid);
            }
            $$ = NEW_ARGS_AUX(tid, 1);
            $$->nd_next = $2;*/
    return yyVal;
  }
};
states[538] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new ArrayNode(((Node)yyVals[0+yyTop]).getPosition(), ((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[539] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    ((ListNode)yyVals[-2+yyTop]).add(((Node)yyVals[0+yyTop]));
                    yyVal = ((ListNode)yyVals[-2+yyTop]);
    return yyVal;
  }
};
states[540] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((Token)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[541] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    support.arg_var(support.formal_argument(((Token)yyVals[-1+yyTop])));
                    yyVal = support.keyword_arg(support.union(((Token)yyVals[-1+yyTop]), ((Node)yyVals[0+yyTop])), support.assignable(((Token)yyVals[-1+yyTop]), ((Node)yyVals[0+yyTop])));
    return yyVal;
  }
};
states[542] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    /* FIXME: null is a node type in main parser*/
                    yyVal = support.keyword_arg(((Token)yyVals[0+yyTop]).getPosition(), support.assignable(((Token)yyVals[0+yyTop]), null));
    return yyVal;
  }
};
states[543] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    support.arg_var(support.formal_argument(((Token)yyVals[-1+yyTop])));
                    yyVal = support.keyword_arg(support.union(((Token)yyVals[-1+yyTop]), ((Node)yyVals[0+yyTop])), support.assignable(((Token)yyVals[-1+yyTop]), ((Node)yyVals[0+yyTop])));
    return yyVal;
  }
};
states[544] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new ArrayNode(((Node)yyVals[0+yyTop]).getPosition(), ((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[545] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((ListNode)yyVals[-2+yyTop]).add(((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[546] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new ArrayNode(((Node)yyVals[0+yyTop]).getPosition(), ((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[547] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((ListNode)yyVals[-2+yyTop]).add(((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[548] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((Token)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[549] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((Token)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[550] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((Token)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[551] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    support.arg_var(support.formal_argument(((Token)yyVals[-2+yyTop])));
                    yyVal = new OptArgNode(support.union(((Token)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop])), support.assignable(((Token)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop])));
    return yyVal;
  }
};
states[552] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    support.arg_var(support.formal_argument(((Token)yyVals[-2+yyTop])));
                    yyVal = new OptArgNode(support.union(((Token)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop])), support.assignable(((Token)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop])));
    return yyVal;
  }
};
states[553] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new BlockNode(support.getPosition(((Node)yyVals[0+yyTop]))).add(((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[554] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.appendToBlock(((ListNode)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[555] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new BlockNode(support.getPosition(((Node)yyVals[0+yyTop]))).add(((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[556] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.appendToBlock(((ListNode)yyVals[-2+yyTop]), ((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[559] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    if (!support.is_local_id(((Token)yyVals[0+yyTop]))) {
                        support.yyerror("rest argument must be local variable");
                    }
                    
                    yyVal = new RestArgNode(support.union(((Token)yyVals[-1+yyTop]), ((Token)yyVals[0+yyTop])), support.arg_var(support.shadowing_lvar(((Token)yyVals[0+yyTop]))));
    return yyVal;
  }
};
states[560] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new UnnamedRestArgNode(((Token)yyVals[0+yyTop]).getPosition(), "", support.getCurrentScope().addVariable("*"));
    return yyVal;
  }
};
states[563] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    if (!support.is_local_id(((Token)yyVals[0+yyTop]))) {
                        support.yyerror("block argument must be local variable");
                    }
                    
                    yyVal = new BlockArgNode(support.union(((Token)yyVals[-1+yyTop]), ((Token)yyVals[0+yyTop])), support.arg_var(support.shadowing_lvar(((Token)yyVals[0+yyTop]))));
    return yyVal;
  }
};
states[564] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((BlockArgNode)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[565] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = null;
    return yyVal;
  }
};
states[566] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    if (!(((Node)yyVals[0+yyTop]) instanceof SelfNode)) {
                        support.checkExpression(((Node)yyVals[0+yyTop]));
                    }
                    yyVal = ((Node)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[567] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    lexer.setState(LexState.EXPR_BEG);
    return yyVal;
  }
};
states[568] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    if (((Node)yyVals[-1+yyTop]) == null) {
                        support.yyerror("can't define single method for ().");
                    } else if (((Node)yyVals[-1+yyTop]) instanceof ILiteralNode) {
                        support.yyerror("can't define single method for literals.");
                    }
                    support.checkExpression(((Node)yyVals[-1+yyTop]));
                    yyVal = ((Node)yyVals[-1+yyTop]);
                    ((Node)yyVal).setPosition(support.union(((Token)yyVals[-3+yyTop]), ((Token)yyVals[0+yyTop])));
    return yyVal;
  }
};
states[569] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = new ArrayNode(lexer.getPosition());
    return yyVal;
  }
};
states[570] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((ListNode)yyVals[-1+yyTop]);
    return yyVal;
  }
};
states[572] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((ListNode)yyVals[-2+yyTop]).addAll(((ListNode)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[573] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    SourcePosition pos;
                    if (((Node)yyVals[-2+yyTop]) == null && ((Node)yyVals[0+yyTop]) == null) {
                        pos = ((Token)yyVals[-1+yyTop]).getPosition();
                    } else {
                        pos = ((Node)yyVals[-2+yyTop]).getPosition();
                    }

                    yyVal = support.newArrayNode(pos, ((Node)yyVals[-2+yyTop])).add(((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[574] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    SourcePosition pos = ((Token)yyVals[-1+yyTop]).getPosition();
                    yyVal = support.newArrayNode(pos, new SymbolNode(pos, (String) ((Token)yyVals[-1+yyTop]).getValue())).add(((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[575] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                      SourcePosition pos = ((Token)yyVals[-3+yyTop]).getPosition();
                      yyVal = support.newArrayNode(pos, new SymbolNode(pos, ((StrNode) ((Node)yyVals[-2+yyTop])).getValue())).add(((Node)yyVals[0+yyTop]));
    return yyVal;
  }
};
states[576] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = support.newArrayNode(((Token)yyVals[-1+yyTop]).getPosition(), ((Node)yyVals[0+yyTop])).add(null);
    return yyVal;
  }
};
states[595] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((Token)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[596] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    yyVal = ((Token)yyVals[0+yyTop]);
    return yyVal;
  }
};
states[604] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                      yyVal = null;
    return yyVal;
  }
};
states[605] = new ParserState() {
  public Object execute(ParserSupport support, Lexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                  yyVal = null;
    return yyVal;
  }
};
}
					// line 2279 "Ruby23Parser.y"

    /** The parse method use an lexer stream and parse it to an AST node 
     * structure
     */
    public ParserResult parse(ParserConfiguration configuration, LexerSource source) throws IOException {
        support.reset();
        support.setConfiguration(configuration);
        support.setResult(new ParserResult());
        
        lexer.reset();
        lexer.setSource(source);

        Object debugger = null;
        if (configuration.isDebug()) {
            try {
                Class yyDebugAdapterClass = Class.forName("jay.yydebug.yyDebugAdapter");
                debugger = yyDebugAdapterClass.newInstance();
            } catch (IllegalAccessException iae) {
                // ignore, no debugger present
            } catch (InstantiationException ie) {
                // ignore, no debugger present
            } catch (ClassNotFoundException cnfe) {
                // ignore, no debugger present
            }
        }
        //yyparse(lexer, new jay.yydebug.yyAnim("JRuby", 9));
        yyparse(lexer, debugger);
        
        return support.getResult();
    }
}
					// line 8840 "-"
