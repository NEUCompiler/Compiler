package com.compiler;

import com.middleCode.ForFour;
import com.signTable.SignTable;
import com.syntaxAnalysis.RecursiveWay;
import com.wordScanner.WordScanner;

public class Compiler {
	WordScanner wordScanner = new WordScanner();
	RecursiveWay recursiveWay = new RecursiveWay();
	ForFour forFour = new ForFour();
	SignTable signTable = new SignTable();
	
	public void excute() {
		recursiveWay.recursive();
		
		if (recursiveWay.isOk() == true) {
			forFour.forreadWord();
			signTable.display();
		}
	}

	public static void main(String[] args) {
		Compiler compiler = new Compiler();
		compiler.excute();
	}
}
