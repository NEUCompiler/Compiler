package com.middleCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Stack;

import javax.swing.text.StyledEditorKit.ForegroundAction;



/**
 * 四元式。
 * 
 * @author Yuan, Xiao; Liu, Xinwei
 *
 */
public class FourEquation {
	public static int TRUE=1;
	public static int FALSE=0;
	public static int OK=1;
	public static int ERROR=0;
	public static int INFEASIBLE=-1;
	public static int OVERFLOW =-2;

	private static final String PRIORPATH = "resource/prior.txt";
	//运算符优先关系表。
	private HashMap<String, HashMap<String, String>> priorMap = new HashMap<>();
	Stack<String> stack = new Stack<>();
	//运算符栈。
	Stack<String> operatorStack = new Stack<>();
	//操作数栈。
	Stack<String> operandStack = new Stack<>();
	
	{
		operatorStack.push("#");
	}
	
	
	
	

	//构造一个空栈
	public void buildStack() {
		Stack<String> stack = new Stack<>();
	}

	//判断是否为空栈
	public boolean isStackEmpty(){
		return stack.isEmpty();
		
	
	}
	//用e返回S的顶元素
	public String getTop() {
		String top = stack.pop();
		stack.push(top);
		return top;
	}
	//插入e为新的顶元素
	public void insertIterm(String iterm) {
		
		stack.push(iterm);
	}
	//删除S的顶元素，并用e返回其值
	public String pop() {
		return stack.pop();
	}
	 

	//从栈底到栈顶依次对S的每个元素调用函数Visit（），一旦失败操作无效

	//输出元素e

	//判断输入的某个字符是否是运算符
	
	char Precede(char a, char b){
		int i=0,j=0;
	    char pre[][]={         
		/*运算符之间的优先级制作成一张表格*/
	        {'>','>','<','<','<','>','>'},
	        {'>','>','<','<','<','>','>'},
	        {'>','>','>','>','<','>','>'},
	        {'>','>','>','>','<','>','>'},
	        {'<','<','<','<','<','=','0'},
	        {'>','>','>','>','0','>','>'},
	        {'<','<','<','<','<','0','='}};
	    switch(a){
	        case '+': i=0; break;
	        case '-': i=1; break;
	        case '*': i=2; break;
	        case '/': i=3; break;
	        case '(': i=4; break;
	        case ')': i=5; break;
	        case '#': i=6; break;
	    }
	    switch(b){
	        case '+': j=0; break;
	        case '-': j=1; break;
	        case '*': j=2; break;
	        case '/': j=3; break;
	        case '(': j=4; break;
	        case ')': j=5; break;
	        case '#': j=6; break;
	    }
	    return pre[i][j];
	}

	/*进行实际的运算
	 *a，b中分别以整数的形式存放两个待运算的操作数
	 *theta中存放代表操作符的字符
	 *结果以整数的形式返回
	 */
	public int Operate(int a, char theta, int b) {
	    int i,j,result=0;
	    i=a;
	    j=b;

	    switch(theta)   {
	        case '+': result = i + j; break;
	        case '-': result = i - j; break;
	        case '*': result = i * j; break;
	        case '/': result = i / j; break;
	    }
	    return result;
	}
	
	
	//表达式的计算函数
	public int EvaluateExpression(){
		String expression ="";
		
//		while()
		
		return 0;
		
	}	
	
	/*
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
				System.out.println(priorMap.toString());
			}
			
			reader.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		System.out.println(priorMap.toString());
		
		System.out.println(priorMap.get("+").get("*"));
	}
	

	/**
	 * 计算表达式。
	 * @param expression 待计算的表达式。
	 * @return 表达式结果。
 	 */
	public String calculateExpression(String expression) {

		String result = "";
		
		operandStack.push("#");
		operatorStack.push("#");
		String top = operandStack.pop();
		
		return result;
	}
	
	public static void main(String[] args) {
		FourEquation equation =  new FourEquation();
		equation.calculateExpression("1+2");
	}
}
/*
 * 例子
 * b:=(2+1*2)*5+a
 * printf(*,1,2,t1);
 * printf(+,2,t1,t2);
 * printf(*,t2,5,t3);
 * printf(+,t3,a,t4);
 * printf(:=,t4, ,t5);

 */


