package com.compiler;

import com.signTable.SignTable;
import com.syntaxAnalysis.RecursiveWay;
import com.wordScanner.WordScanner;

public class Compiler {
	WordScanner wordScanner = new WordScanner();
	RecursiveWay recursiveWay = new RecursiveWay();
	SignTable signTable = new SignTable();
	
	public void excute() {
		recursiveWay.program();
		signTable.display();
	}

	public static void main(String[] args) {
		new Compiler().excute();
	}
}
