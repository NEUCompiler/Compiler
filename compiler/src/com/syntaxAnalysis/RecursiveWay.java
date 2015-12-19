package com.syntaxAnalysis;

import com.wordScanner.WordScanner;

/**
 * 递归下降语法分析。
 * 
 * @author Xiao, Hui; Liu, Xinwei
 *
 */
public class RecursiveWay {
	String current;
	String kind;
	String word;
	String single = "";
	static int tures = 0;// 说明多少个错误。
	int type2 = 0;// 类型说明，如果是整形为1，实数型为2，字符型为3

	WordScanner scanner = new WordScanner();

	// 拆开词函数
	public String char2() {
		char ch = word.charAt(0);
		word = word.substring(1);
		return "" + ch;
	}

	public void read() {
		String currents;
		currents = scanner.read();
		String[] splits = currents.split("\\|");
		current = splits[0];
		kind = splits[1];
	}

	// 1 程序入口，<1 程序program> ->program <2 标识符ID> <3 分程序deputyProgram>
	public void program() {
		read();
		if ("program".equals(current)) {
			read();
			ID();
			deputyprogram();
		} else {
			System.out.println("关键字错误" + ":" + current + " " + "应该为program");
			tures++;
		}
	}

	// 2判断是否是符合标识符定义，a型,abc型,abc123型
	public void ID() {
		if (!"i".equals(kind)) {
			System.out.println("标识符错误" + ":" + current);
		}
		read();
	}

	// 3 <3 分程序deputyProgram> -><10 变量说明VD> <4 复合语句compoundStatement>
	private void deputyprogram() {
		VD();
		compoundStatement();
	}

	// 4复合语句的判断，主体部分**，里面是主体架构，从begin 到最后的end.
	public void compoundStatement() {
		if ("begin".equals(current)) {
			read();
			while (!"end".equals(current)) {
				if ("while".equals(current)) {
					whiles();
				} else if ("if".equals(current)) {
					ifs();
				} else
					statementTable();
			}
		} else {
			System.out.println("关键字错误" + ":" + current + " " + "应该为begin");
			tures++;
		}
		read();
		if (!".".equals(current)) {
			System.out.println("结尾符号错误" + ":" + current + " " + "应该为.");
			tures++;
		}
	}

	// 5号 <5 标识符表IDTable> -><2 标识符ID> {， <2 标识符ID>}
	public void IDTable() {
		ID();
		while (",".equals(current)) {
			read();
			ID();
		}
	}

	// 6确定变量的类型，以便后面算数表达式的检查；
	private void type() {
		if ("k".equals(kind)) {
			if ("integer".equals(current) || "int".equals(current)) {
				read();
				type2 = 1;
			}// 如果是整形的话
			else if ("real".equals(current)) {
				read();
				type2 = 2;
			}// 如果是实数型
			else if ("char".equals(current)) {
				read();
				type2 = 3;
			}// 如果是字符型
		} else {
			System.out.println("关键字类型错误" + ":" + current + " "
					+ "应该为integer或real或char");
			tures++;
		}
	}

	// 7号语句表的判断，主体部分*，主要的部分语句，和赋值语句联系紧密
	private void statementTable() {
		assignmentStatement();
		while (";".equals(current)) {
			read();
			assignmentStatement();
		}
	}

	// 8 <8 compoundStatement1复合语句2> -> begin<7 statementTable语句表>end;|<9
	// assignmentStatement赋值语句>;
	// 这里是主要处理whlie,if的复合语句；
	private void compoundStatement1() {
		if ("begin".equals(current)) {
			read();
			statementTable();
			if ("end".equals(current)) {
				read();
				if (";".equals(current)) {
				} else {
					System.out.println("符号错误" + ":" + current + " " + "应该为;");
					tures++;
				}
			} else {
				System.out.println("关键字错误" + ":" + current + " " + "应该为end");
				tures++;
			}
		} else
			assignmentStatement();
		if (";".equals(current)) {
			read();
		} else {
			System.out.println("符号错误" + ":" + current + " " + "应该为;");
			tures++;
		}
	}

	// 9号 赋值语句的判断，给语句赋值
	private void assignmentStatement() {
		ID();
		if (":=".equals(current)) {
			read();
			word = current + "#";
			AE();
		} else {
			System.out.println("符号错误" + ":" + current + " " + "应该为:=");
			tures++;
		}
	}

	// 10 <10 变量说明VD>-> var <5 标识符表IDTable> ：<6 类型type>
	private void VD() {
		if ("var".equals(current)) {
			read();
			IDTable();
			if (":".equals(current)) {
				read();
				type();
				if (";".equals(current)) {
					read();
				} else {
					System.out.println("符号错误" + ":" + current + " " + "应该为;");
					tures++;
				}
			} else {
				System.out.println("符号错误" + ":" + current + " " + "应该为;");
				tures++;
			}
		} else {
			System.out.println("关键字错误" + ":" + current + " " + "应该为var");
			tures++;
		}
	}

	// 11 <11 while>-><12 or> do <8 compoundStatement1复合语句>
	public void whiles() {
		or();
		if ("do".equals(current)) {
			read();
			compoundStatement1();
		} else {
			System.out.println("关键字错误" + ":" + current + " " + "应该为do");
			tures++;
		}
	}

	// 12 <12 or>-><14 and>{or<14 and>}
	private void or() {
		and();
		while ("or".equals(current)) {
			read();
			and();
		}
	}

	// 13 <13 if> -> <12 or>then<8 compoundStatement1>else<8 compoundStatement1>
	public void ifs() {
		or();
		if ("then".equals(current)) {
			read();
			compoundStatement1();
			if ("else".equals(current)) {
				read();
				compoundStatement1();
			} else {
				System.out.println("关键字错误" + ":" + current + " " + "应该为else");
				tures++;
			}
		} else {
			System.out.println("关键字错误" + ":" + current + " " + "应该为then");
			tures++;
		}
	}

	// 14 <14 and>-><15 not>{and<15 not>}
	private void and() {
		not();
		while ("and".equals(current)) {
			read();
			not();
		}
	}

	// 15 <15 not>->not<16 booleans>|<16 booleans>
	private void not() {
		if ("not".equals(current)) {
			read();
			boolTerm();
		} else
			booleans();
	}

	// 16 <16 booleans>-><17 boolTerm>|(<12 or>)
	public void booleans() {
		if ("(".equals(current)) {
			read();
			or();
			if (")".equals(current)) {
				read();
			} else {
				System.out.println("算术表达式错误" + ":" + current + " " + "应该为)");
				tures++;
			}
		} else {
			read();
			boolTerm();
		}
	}

	// 17 <17 boolTerm>-><18 AE>w2<18 AE>
	public void boolTerm() {
		AE();
		if ("<".equals(current) || ">".equals(current) || "<=".equals(current)
				|| ">=".equals(current) || "==".equals(current)) {
			read();
			AE();
		} else {
			System.out.println("语句表达错误" + ":" + current + " " + "应该为");
			tures++;
		}
	}

	// 18算术表达式的判断，主要的算术表达式：如：a:=b+2;
	private void AE() {
		term();
		while ("+".equals(current) || "-".equals(current)) {
			read();
			term();
		}
	}

	// 19算术表达式中因子的判断,如：b*2
	private void term() {
		factor();
		while ("*".equals(current) || "/".equals(current)) {
			read();
			factor();
		}
	}

	// 20 <20 因子factor> -> <21 算术量quantity> | ( <18 算术表达式AE> )
	private void factor() {
		if ("(".equals(current)) {
			read();
			AE();
			if (")".equals(current)) {
				read();
			} else {
				System.out.println("算术表达式错误" + ":" + current + " " + "应该为)");
				tures++;
			}
		} else if (")".equals(current)) {
			System.out.println("算术表达式错误" + ":" + current + " " + "应该为(");
			tures++;
			read();
			AE();
			if (")".equals(current)) {
				read();
			} else {
				System.out.println("算术表达式错误" + ":" + current + " " + "应该为)");
				tures++;
			}
		} else
			quantity();
	}

	// 21 <21 算术量quantity> -> <2 标识符ID> | <22 常数constant>
	private void quantity() {
		word = current;
		single = char2();
		if (single.matches("[A-Za-z]")) {
			ID();
		} else if (single.matches("[0-9]")) {
			constant();
		} else {
			System.out.println("算术表达式中的变量或数据错误" + ":" + current + " " + "应该为");
			tures++;
			read();
			quantity();
		}
	}

	// 22 有两条路线，如果前面定义的是整形变量，那么走ints(),如果定义的是实数，走real(),
	private void constant() {
		if ("c".equals(kind)) {
			if (type2 == 1) {
				ints();
			} else
				real();
		} else {
			System.out.println("数据错误" + ":" + current);
		}
		read();
	}

	// 23判断是否符合整形数的定义,
	private void ints() {
		word = current + "#";
		single = char2();
		for (; !"#".equals(single);) {
			number();
			if (".".equals(single)) {
				System.out.println("原定义为整形" + ":" + current + " " + "应该为整形");
				tures++;
			}
		}
	}

	// 24判断是否符合实数的定义，如3.4对，3.错;
	private void real() {
		word = current + "#";
		single = char2();
		while (!"#".equals(single)) {
			ints();
			if (".".equals(single)) {
				ints();
			} else {
				System.out.println("实数拼写错误" + ":" + current + " " + "应该为");
				tures++;
			}
		}
	}

	// 25判断是否是字符；是就再取后续字符
	public void chars() {
		if (single.matches("[A-Za-z]")) {
			single = char2();
		}
	}

	// 26判断是否是数字，是就再取后续字符
	public void number() {
		if (single.matches("[0-9]")) {
			single = char2();
		} else if (".".equals(single)) {
			single = char2();
			if ("#".equals(single)) {
				read();
				System.out.println("实数拼写错误" + ":" + current + " " + "应该为");
				tures++;
				read();
			}
		}
	}

	public static int getTures() {
		return tures;
	}

	/**
	 * 入口函数。
	 */
	public void recursive() {
		program();
		if (tures == 0) {
			System.out.println("Perfect!!");
			System.out.println("\nToken:\n" + scanner.getToken());
		} else {
			System.out.println("There have " + tures + " errors");
		}
		System.out.println();
	}

	// read()为词法分析要读取得词。current为本程序中当前词，通过read()来更新current变量。
	public static void main(String[] args) {
		RecursiveWay gramr = new RecursiveWay();
		gramr.program();
		if (tures == 0) {
			System.out.println("Perfect!!");
		} else {
			System.out.println("There have " + tures + " errors");
		}
	}

}
