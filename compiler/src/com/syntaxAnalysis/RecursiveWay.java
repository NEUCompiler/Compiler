package com.syntaxAnalysis;

import com.wordScanner.WordScanner;

/**
 * 递归下降语法分析。
 * @author Xiao, Hui; Liu, Xinwei
 *
 */
public class RecursiveWay {
	String current = "";
	
	public void ID(){
		
	}
	//2号语法
	
	public void compoundStatement(){
		do {
		statementTable(); 
		}
		while(current == "") ;
	}
	//4陷入循环，待解决，为4号语法,curent在这没什么意义
	
	private void statementTable() {
		assignmentStatement();
		for ( ;current == ";";){
			current= read() ;
			assignmentStatement();
		}	
	}//7号
	
	private void assignmentStatement() {
		ID();
		if (current == "="){
			current = read ();
			AE();
		}
		else System.out.println("err");
	}//9号
	
	private void AE() {
		term();
		for(;current == "w0";){
			current = read();
			term();
		}
	}//18

	private void term() {
		factor();
		for(;current == "w1";){
			current = read();
			factor();
		}
	}//19
	
	private void factor() {
		if(current == "("){
			current = read ();
			AE();
			if( current != ")"){
				System.out.println("err");
			}
		}
		else quantity();	
	}//20
	
	private void quantity() {
		ID();
		constant();
	}//这里是产生了两个分支函数，待解决 21号
	
	private void constant() {
		
	}//22 待解决
	
	private void VD(){
		if(current == "var"){
			IDTable();
			current = read();
			if(current == ":"){
				current = read();
				type();
			}
			else {
				System.out.println("err");
			}
		}
		else{
			System.out.println("err");
		}	
	}//10
	
	private void type() {
		
	}//6待解决
	
	private void compoundStatement1(){
		if(current == "begin"){
			compoundStatement();
		}
		else assignmentStatement();
	}//8
	
	public void while1(){
		or();
		if(current == "do"){
			current = read();
			compoundStatement1();
		}
		else System.out.println("err");
	}//11
	
	private void or() {
		and();
		for(;current == "or";){
			current = read();
			and();
		}	
	}//12

	private void and() {
		not();
		for(;current == "and";){
			current = read();
			not();
		}	
	}//14

	private void not() {
		for(;current == "not";){
			current = read();
			boolTerm();
		}	
	}//15
	
	public void IF(){
		or();
		if(current == "then"){
			current = read();
			compoundStatement1();
			if( current == "else"){
				current = read();
				compoundStatement1();
			}else System.out.println("err");
		}else System.out.println("err");	
	}//13
	
	public void boolTerm(){
		AE();
		if(current == "w2"){
			current = read();
			AE();
		}else System.out.println("err");
	}//17
	
	public void boollean(){
		if(current == "("){
			current = read();
			or();
			if(current == ")"){
				current = read();
			}else System.out.println("err");
		}
		else boolTerm();
	}//16

	public void IDTable(){
		ID();
		for (;read() == ",";){
			current = read();
			ID();
		}
	}//5号
	
	public void program(){
		if(current == "program"){
			current = read();
			ID();
			deputyprogram();
		}else System.out.println("err");
	}//1
	
	private void deputyprogram() {
		VD();
		compoundStatement();
	}//3
	
	private String read() {
		 String w = null ;
		return w ;
	}//词法读取w
	
	public static void main(String[] args){
		RecursiveWay gramr = new RecursiveWay();
		
	}
	

}
