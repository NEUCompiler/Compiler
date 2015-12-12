package com.syntaxAnalysis;

import com.wordScanner.WordScanner;

/**
 * 递归下降语法分析。
 * 
 * @author Xiao, Hui; Liu, Xinwei
 *
 */
public class RecursiveWay {
	String current = "";
	String word;
	String single = "";
	int type2 = 0;//类型说明，如果是整形为1，实数型为2，字符型为3

	WordScanner scanner = new WordScanner();

	// public String real2(){
	// if (word.indexOf(".") == 0) {
	// word = word.substring(1);
	// return ".";
	// } else {
	//
	// }
	// String[] splits = word.split(".");
	//
	// return splits[0];
	//
	// }

	public String char2() {
		char ch = word.charAt(0);
		word = word.substring(1);
		return "" + ch;
	}// 拆开词函数

	public void chars() {
		if (single.matches("[A-Za-z]")) {
			single = char2();
		}
	}// 25判断是否是字符；是就再取后续字符

	public void number() {
		if (single.matches("[0-9]")) {
			single = char2();
		}
	}// 26判断是否是数字，是就再取后续字符

	public void ID() {
		word = current + "#";
		single = char2();
		while(!"#".equals(single)) {
			chars();
			if(!"#".equals(single)) {
			number();
			}
		}
		current = read();
	}// 2判断是否是符合标识符定义，a型,abc型,abc123型，

	private void real() {
		word = current + "#";
		single = char2();
		while(!"#".equals(single)) {
			ints();
			if (".".equals(single)) {
				ints();
			} else
				System.out.println("err");
		}
	}// 24判断是否符合实数的定义，如3.4对，3.错;

	private void ints() {
		word = current + "#";
		single = char2();
		for (; !"#".equals(single);) {
			number();
		}
	}// 23判断是否符合整形数的定义,

	public void compoundStatement() {
		if ("begin".equals(current)) {
			current = read();
			while(!"end".equals(current)){
				if("while".equals(current)){
					whiles();
				}
				else if("if".equals(current)){
					ifs();
				}
				else statementTable();
			}
		} else 
			System.out.println("err");
		current = read();
		if(".".equals(current)) {
				System.out.println("成功啦！！");
			}else
				System.out.println("err");
	}// 4复合语句的判断，主体部分**，


	private void statementTable() {
		assignmentStatement();
		while (";".equals(current)) {
			current = read();
			assignmentStatement();
		}
	}// 7号语句表的判断，主体部分*，

	private void assignmentStatement() {
		ID();
		if (":=".equals(current)) {
			current = read();
			word = current + "#";
			AE();
		} else
			System.out.println("err");
	}// 9号 赋值语句的判断，

	private void AE() {
		term();
		while ("+".equals(current) || "-".equals(current)) {
			current = read();
			term();
		}
	}// 18算术表达式的判断，

	private void term() {
		factor();
		while("*".equals(current) || "/".equals(current)) {
			current = read();
			factor();
		}
	}// 19算术表达式中因子的判断

	private void factor() {
		if ("(".equals(current)) {
			current = read();
			AE();
			if (!")".equals(current)) {
				System.out.println("err");
			}
		} else
			quantity();
	}// 20  <20 因子factor> -> <21 算术量quantity> | ( <18 算术表达式AE> )

	private void quantity() {
		word = current;
		single = char2();
		if (single.matches("[A-Za-z]")) {
			ID();
		} else
			constant();
	}// 21  <21 算术量quantity> -> <2 标识符ID> | <22 常数constant>

	private void constant() {
		if(type2 == 1){
			ints();
		}
		else real();
		current = read();
	}// 22  有两条路线，如果前面定义的是整形变量，那么走ints(),如果定义的是实数，走real(),

	private void VD() {
		if ("var".equals(current)) {
			current = read();
			IDTable();
			if (":".equals(current)) {
				current = read();
				type();
				if (";".equals(current)) {
					current = read();
				} else
					System.out.println("err");
			} else
				System.out.println("err");
		} else
			System.out.println("err");
	}// 10  <10 变量说明VD>-> var <5 标识符表IDTable> ：<6 类型type> 

	private void type() {
		if("integer".equals(current)||"int".equals(current)) {
			current = read();
		   type2 = 1;	
		}//如果是整形的话
		else if("real".equals(current)) {
			current = read();
			type2 =2 ;
		}//如果是实数型
		else if("char".equals(current)) {
			current = read();
			type2 = 3;
		}//如果是字符型
		else System.out.println("类型关键字错误");
	}// 6确定变量的类型，以便后面算数表达式的检查；

	private void compoundStatement1() {
		if ("begin".equals(current)) {
			current = read();
			statementTable();
			if("end".equals(current)){
				current = read();
				if(";".equals(current)){
				}else 
					System.out.println("err");	
			}else 
				System.out.println("关键字错误");
		} else
			assignmentStatement();
		if(";".equals(current)){
			current = read();
		}else 
			System.out.println("err");
	}// 8

	public void whiles() {
		or();
		if ("do".equals(current)) {
			current = read();
			compoundStatement1();
		} else
			System.out.println("err");
	}// 11

	private void or() {
		and();
		while ("or".equals(current)) {
			current = read();
			and();
		}
	}// 12

	private void and() {
		not();
		while("and".equals(current)) {
			current = read();
			not();
		}
	}// 14

	private void not() {
		if("not".equals(current)) {
			current = read();
			boolTerm();
		}else booleans();	
	}// 15

	public void ifs() {
		or();
		if ("then".equals(current)) {
			current = read();
			compoundStatement1();
			if ("else".equals(current)) {
				current = read();
				compoundStatement1();
			} else
				System.out.println("err");
		} else
			System.out.println("err");
	}// 13

	public void boolTerm() {
		AE();
		if ("<".equals(current) || ">".equals(current) || "<=".equals(current)
				|| ">=".equals(current)||"==".equals(current)) {
			current = read();
			AE();
		} else
			System.out.println("err");
	}// 17 <17 boolTerm>-><18 AE>w2<18 AE>

	public void booleans() {
		if ("(".equals(current)) {
			current = read();
			or();
			if (")".equals(current)) {
				current = read();
			} else
				System.out.println("err");
		} else{
			current = read();
			boolTerm();
		}
	}// 16  <16 booleans>-><17 boolTerm>|(<12 or>)

	public void IDTable() {
		ID();
		while(",".equals(current)) {
			current = read();
			ID();
		}
	}// 5号  <5 标识符表IDTable> -><2 标识符ID> {， <2 标识符ID>}

	public void program() {
		current = read();
		if ("program".equals(current)) {
			current = read();
			ID();
			deputyprogram();
		} else
			System.out.println("err");
	}// 1  程序入口，<1 程序program> ->program <2 标识符ID> <3 分程序deputyProgram>

	private void deputyprogram() {
		VD();
		compoundStatement();
	}// 3  <3 分程序deputyProgram> -><10 变量说明VD> <4 复合语句compoundStatement>

	private String read() {
		return scanner.read();
	}// 词法读取w

	public static void main(String[] args) {
		RecursiveWay gramr = new RecursiveWay();
		gramr.program();
	}// read()为词法分析要读取得词。current为本程序中当前词，通过read()来更新current变量。

}
