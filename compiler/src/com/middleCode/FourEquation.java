package com.middleCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Stack;

/**
 * 四元式。
 * 
 * @author Yuan, Xiao; Liu, Xinwei
 *
 */
public class FourEquation {

	private static final String PRIORPATH = "resource/prior.txt";
	//运算符优先关系表。
	private HashMap<String, HashMap<String, String>> priorMap = new HashMap<>();
	
	/**
	 * 初始化优先关系表。
	 */
	public void initPriorMap() { 
		try {
			BufferedReader reader = new BufferedReader(new FileReader(
					new File(PRIORPATH)));
			
			HashMap<String, String> colMap = new HashMap<>();
			String line;
			while ((line = reader.readLine()) != null) {
				String[] splits = line.split(" ");
				colMap.put(splits[1], splits[2]);
				priorMap.put(splits[0], colMap);
			}
			
			reader.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		System.out.println(priorMap.toString());
	}
	
	
	/**
	 * 计算表达式。
	 * @param expression 待计算的表达式。
	 * @return 表达式结果。
 	 */
	public String CalculateExpression(String expression) {
		//运算符栈。
		Stack<String> operatorStack = new Stack<>();
		//操作数栈。
		Stack<String> operandStack = new Stack<>();
		String result = "";
		
		operandStack.push("#");
		operatorStack.push("#");
		
		
				
		return result;
	}
	
	public static void main(String[] args) {
		new FourEquation().initPriorMap();
	}
}
