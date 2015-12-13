package com.middleCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * 优先级。
 * 
 * @author Liu, Xinwei
 *
 */
public class Priority {

	private static final String PRIORPATH = "resource/prior.txt";
	//优先级表。
	private HashMap<String, HashMap<String, String>> priorMap = new HashMap<>();
	private ArrayList<Quat> quats = new ArrayList<>();
	private Stack<String> firstStack = new Stack<>();
	private Stack<String> secondStack = new Stack<>();
	
	/**
	 * 初始化优先级表。
	 */
	public void initPriorMap() { 
		try {
			BufferedReader reader = new BufferedReader(new FileReader(
					new File(PRIORPATH)));
			
			HashMap<String, String> colMap = new HashMap<>();
			String line;
			int i = 0;
			while ((line = reader.readLine()) != null) {
				if (++i%5 == 1) {
					colMap = new HashMap<>();
				}
				
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
	
	/*
	 * 逆波兰式。 
	 */
	public void dealConverseExpression() {
		String top;
		Quat quat;
		//待测试表达式。
		String expression = "(1*5+2)/3";
System.out.println(expression);
		firstStack.push("#");
		char[] ch = expression.toCharArray();
		
		String item;
		for (int i=0; i<ch.length; i++) {
			
			item = "" + ch[i];
			if (isOperator(item)) {
				top = firstStack.peek();
				
				if ("(".equals(item)) {
					firstStack.push(item);
				} else if (")".equals(item)) {
					while (!"(".equals(top = firstStack.pop())) {
						if (!"(".equals(top)) {
							secondStack.push(top);
							buildQuat(top);
						}
					}
				} else if ("(".equals(top)) {
					firstStack.push(item);
				} else if (">".equals(priorMap.get(item).get(top))) {
					firstStack.push(item);
				} else {
					top = firstStack.pop();
					secondStack.push(top);
					buildQuat(top);
					firstStack.push(item);
				}
			} else {
				
				secondStack.push(item);
			}
		}
		
		while (!"#".equals(top = firstStack.pop())) {
			secondStack.push(top);
			buildQuat(top);
			
		}
		System.out.println(secondStack.toString());
		for (Quat q : quats) {
			System.out.println(q.toString());
		}
		
	}
	
	/**
	 * 构建四元式。
	 */
	public void buildQuat(String top) {
		Quat quat = new Quat(top);
		for (int j=0; j<3; j++) {
			String secondTop = secondStack.pop();
			
			if (j == 0) {
				quat.setFirst(secondTop);
			} else if (j == 1) {
				quat.setThird(secondTop);
			} else {
				quat.setSecond(secondTop);
				double result = 0;
				if ("+".equals(quat.getFirst())) {
					 result = Double.parseDouble(quat.getSecond()) 
							+ Double.parseDouble(quat.getThird());
				} else if ("-".equals(quat.getFirst())) {
					 result = Double.parseDouble(quat.getSecond()) 
							- Double.parseDouble(quat.getThird());
				} else if ("*".equals(quat.getFirst())) {
					 result = Double.parseDouble(quat.getSecond()) 
							* Double.parseDouble(quat.getThird());
				} else if ("/".equals(quat.getFirst())) {
					 result = Double.parseDouble(quat.getSecond()) 
							/ Double.parseDouble(quat.getThird());
				}
				quat.setFourth("" + result);
				quats.add(quat);
				secondStack.push("" + result);
			}
		}
	}
	
	/**
	 * 是否是操作符。
	 * @param iterm
	 */
	public boolean isOperator(String item) {
		
		if (item.matches("\\+||\\-||\\*||\\/||\\(||\\)")) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		Priority priority =new Priority();
		priority.initPriorMap();
		priority.dealConverseExpression();
	}
}
