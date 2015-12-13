package com.middleCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
				if (++i%7 == 1) {
					colMap = new HashMap<>();
				}
				
				String[] splits = line.split(" ");
				colMap.put(splits[1], splits[2]);
				priorMap.put(splits[0], colMap);
				System.out.println(priorMap.toString());
			}
			
			reader.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		System.out.println(priorMap.toString());
	}
	
	
	/**
	 * 璁＄畻琛ㄨ揪寮忋��
	 * @param expression 寰呰绠楃殑琛ㄨ揪寮忋��
	 * @return 琛ㄨ揪寮忕粨鏋溿��
 	 */
	public String CalculateExpression(String expression) {
		//杩愮畻绗︽爤銆�
		Stack<String> operatorStack = new Stack<>();
		//鎿嶄綔鏁版爤銆�
		Stack<String> operandStack = new Stack<>();
		String result = "";
		
		operandStack.push("#");
		operatorStack.push("#");
		
		
				
		return result;
	}
	
	public static void main(String[] args) {
		new Priority().initPriorMap();
	}
}
