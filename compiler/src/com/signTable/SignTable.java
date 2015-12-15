package com.signTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.middleCode.Priority;
import com.wordScanner.WordScanner;

/**
 * 符号表。
 * 
 * @author 刘鑫伟
 *
 */
public class SignTable {

	public static final int INTLEN = 1;
	public static final int REALLEN = 1;
	public static final int CHARLEN = 1;
	public static final int BOOLLEN = 1;

	// 符号表总表。
	private HashMap<String, Synb> synbTable = new HashMap<>();
	// 数组表。
	private List<Array> arrayTable = new ArrayList<>();
	// 结构表。
	private List<Struts> strutsTable = new ArrayList<>();
	// 函数表。
	private List<Function> functionTable = new ArrayList<>();
	// 活动记录表。
	private List<Vall> vallList = new ArrayList<>();
	// 执行状态。
	private String state;

	WordScanner scanner = new WordScanner();
	Priority priority = new Priority();

	public void dealWith() {
		String word;
		String next;
		while (!"".equals(word = read())) {

			if ("program".equals(word)) {
				state = word;
			} else if ("begin".equals(word)) {
				state = word;
			}

			// System.out.println(word);

			if ("var".equals(word)) {
				dealWithBasicDefine();
			} else if (synbTable.containsKey(word)
					&& ":=".equals(next = read())) {
				dealWithUse(word);
			}
		}

		System.out.println(synbTable.toString());
	}

	public void dealWithArray() {

	}

	/**
	 * 处理基本类型声明。
	 */
	public void dealWithBasicDefine() {
		String code = "";
		String word;
		String type = "";
		while (!";".equals(word = read())) {
			if (":".equals(word)) {
				type = read();
				continue;
			}
			code = code + word;
		}
		// System.out.println(code);

		String[] splits = code.split(",");
		if ("integer".equals(type)) {
			for (int i = 0; i < splits.length; i++) {
				synbTable.put(splits[i], new Synb(splits[i], Type.INT, INTLEN));
			}
		} else if ("real".equals(type)) {
			for (int i = 0; i < splits.length; i++) {
				synbTable.put(splits[i],
						new Synb(splits[i], Type.REAL, REALLEN));
			}
		} else if ("char".equals(type)) {
			for (int i = 0; i < splits.length; i++) {
				synbTable.put(splits[i],
						new Synb(splits[i], Type.CHAR, CHARLEN));
			}
		} else if ("bool".equals(type)) {
			for (int i = 0; i < splits.length; i++) {
				synbTable.put(splits[i], new Synb(splits[i], Type.BOOLEAN,
						BOOLLEN));
			}
		}
	}

	/**
	 * 变量使用。
	 */
	public void dealWithUse(String variable) {
		String code = "";
		String word;
		while (!(word = read()).matches("\\;||end")) {
			if (synbTable.containsKey(word)) {
				code = code + synbTable.get(word).getValue();
			} else {
				code = code + word;
			}
		}
		System.out.println(variable + ":=" + code);

		if (isExpression(code)) {
			//setScond有问题。
			priority.dealConverseExpression(code);
			
			String result = priority.getQuats().get(priority.getQuats().size()-1).getFourth();
			synbTable.get(variable).setValue(result);
		} else if (synbTable.containsKey(code)){
			synbTable.get(variable).setValue(synbTable.get(code).getValue());
		} else {
			synbTable.get(variable).setValue(code);;
		}
	}

	/**
	 * 读当前第一个单词。
	 * 
	 * @return 第一个单词。
	 */
	public String read() {
		return scanner.read();
	}

	/**
	 * 判断是不是表达式。
	 * @param code 带判断代码。
	 * @return 
	 */
	public boolean isExpression(String code) {

		if (code.contains("+") || code.contains("-") || code.contains("*")
				|| code.contains("/")) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		SignTable sign = new SignTable();
		sign.dealWith();
	}

}
