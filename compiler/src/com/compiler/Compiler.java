package com.compiler;

import com.middleCode.ForFour;
import com.signTable.SignTable;
import com.syntaxAnalysis.RecursiveWay;
import com.wordScanner.WordScanner;

/**
 * 编译器前端。
 * @author 刘鑫伟
 *
 */
public class Compiler {
	WordScanner wordScanner = new WordScanner();
	RecursiveWay recursiveWay = new RecursiveWay();

	public void excute() {
		recursiveWay.recursive();

		if (recursiveWay.getTures() == 0) {
			ForFour forFour = new ForFour();
			SignTable signTable = new SignTable();
			forFour.forreadWord();
			signTable.display();
		}
		
	}

	public static void main(String[] args) {
		Compiler compiler = new Compiler();
		compiler.excute();
	}
}
