package com.compiler;

import com.middleCode.ForFour;
import com.signTable.SignTable;
import com.syntaxAnalysis.RecursiveWay;
import com.wordScanner.WordScanner;

public class Compiler {
	WordScanner wordScanner = new WordScanner();
	RecursiveWay recursiveWay = new RecursiveWay();
	SignTable signTable = new SignTable();
	
	public void excute() {
		recursiveWay.recursive();
		
		if (recursiveWay.isOk() == true) {
			ForFour forFour = new ForFour();
			forFour.forreadWord();
			signTable.display();
		}
	}

	public static void main(String[] args) {
		Compiler compiler = new Compiler();
		compiler.excute();
	}
}
