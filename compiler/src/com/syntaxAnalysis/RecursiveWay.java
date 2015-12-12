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
	}// 25

	public void number() {
		if (single.matches("[0-9]")) {
			single = char2();
		}
	}// 26

	public void ID() {
		word = current + "#";
		single = char2();
		while(!"#".equals(single)) {
			chars();
			number();
		}
		current = read();// **
	}// 2

	public void constants() {
		ints();
		real();
	}// 22

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
	}// 24

	private void ints() {
		word = current + "#";
		single = char2();
		for (; !"#".equals(single);) {
			number();
		}
	}// 23

	public void compoundStatement() {
		if ("begin".equals(current)) {
			current = read();
			statementTable();
		} else
			System.out.println("err");
		// current = read();
		if ("end".equals(current)) {
			current = read();
		} else
			System.out.println("err");
	}// 4

	private void statementTable() {
		assignmentStatement();
		while (";".equals(current)) {
			current = read();
			assignmentStatement();
		}
	}// 7号

	private void assignmentStatement() {
		ID();
		// current = read();
		if (":=".equals(current)) {
			current = read();
			word = current + "#";
			AE();
			// current = read();//**
		} else
			System.out.println("err");
	}// 9号

	private void AE() {
		term();
		while ("+".equals(current) || "-".equals(current)) {
			current = read();
			term();
		}
	}// 18

	private void term() {
		factor();
		while("*".equals(current) || "/".equals(current)) {
			current = read();
			factor();
		}
	}// 19

	private void factor() {
		if ("(".equals(current)) {
			current = read();
			AE();
			if (!")".equals(current)) {
				System.out.println("err");
			}
		} else
			quantity();
	}// 20

	private void quantity() {
		word = current;
		single = char2();
		if ("single".matches("[a-zA-Z]")) {
			ID();
		} else
			constant();
	}// 21

	private void constant() {
		current = read();
	}// 22 待解决

	private void VD() {
		if ("var".equals(current)) {
			current = read();
			IDTable();
			// current = read();
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
	}// 10

	private void type() {
		current = read();
	}// 6待解决

	private void compoundStatement1() {
		if ("begin".equals(current)) {
			compoundStatement();
		} else
			assignmentStatement();
	}// 8

	public void while1() {
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
		for (; "and".equals(current);) {
			current = read();
			not();
		}
	}// 14

	private void not() {
		while("not".equals(current)) {
			current = read();
			boolTerm();
		}
	}// 15

	public void IF() {
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
				|| ">=".equals(current)) {
			current = read();
			AE();
		} else
			System.out.println("err");
	}// 17

	public void boollean() {
		if ("(".equals(current)) {
			current = read();
			or();
			if (")".equals(current)) {
				current = read();
			} else
				System.out.println("err");
		} else
			boolTerm();
	}// 16

	public void IDTable() {
		ID();
		// current = read();
		while(",".equals(current)) {
			current = read();
			ID();
		}
	}// 5号

	public void program() {
		current = read();
		if ("program".equals(current)) {
			current = read();
			ID();
			// current = read();///**
			deputyprogram();
		} else
			System.out.println("err");
	}// 1

	private void deputyprogram() {
		VD();
		compoundStatement();
	}// 3

	private String read() {
		return scanner.read();
	}// 词法读取w

	public static void main(String[] args) {
		RecursiveWay gramr = new RecursiveWay();
		gramr.program();
		System.out.println("成功啦！！");
	}// read()为词法分析要读取得词。current为本程序中当前词，通过read()来更新current变量。

}
