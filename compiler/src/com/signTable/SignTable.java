package com.signTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

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
	private Stack<ArrayList<Vall>> vallStack = new Stack<>();
	private ArrayList<Vall> globalList = new ArrayList<>();

	// 执行状态。
	private Vall vall;
	private String preWord;
	private String state;
	private String code;

	WordScanner scanner = new WordScanner();
	Priority priority = new Priority();

	public void dealWith() {
		String word;
		if ("while".equals(state)) {
			code = scanner.getCode();
		} else if ("whileEnd".equals(state)) {

		}

		while (!"".equals(word = readWord())) {

			// System.out.println(word);

			if ("var".equals(word)) {
				dealWithBasicDefine();
			} else if ("if".equals(preWord)) {
				state = "if";
				preWord = null;
				String code = getBooleanExpression(word);
				dealWithIf(judgeBooleanExpression(code));
			} else if ("else".equals(word)) {
				return;
			} else if ("while".equals(word)) {
				dealWithWhile(word);
			} else if (synbTable.containsKey(word) && ":=".equals(readWord())) {
				dealWithUse(word);
			}

		}

		if ("while".equals(state)) {
			scanner.setCode(code);
		}
		// System.out.println(synbTable.toString());
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
		while (!";".equals(word = readWord())) {
			if (":".equals(word)) {
				type = readWord();
				continue;
			}
			code = code + word;

			if (!",".equals(word)) {
				buildVallList("var " + word + "|" + word);
			}
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
		while (!(word = readWord()).matches("\\;||end||if||while")) {
			code = code + replaceVariable(word);
		}
		preWord = word;
		// System.out.println(variable + ":=" + code);

		if (isExpression(code)) {
			// setScond有问题。
			String result = calculateExpression(code);
			synbTable.get(variable).setValue(result);
		} else if (synbTable.containsKey(code)) {
			synbTable.get(variable).setValue(synbTable.get(code).getValue());
		} else {
			synbTable.get(variable).setValue(code);
			;
		}

		buildVallList(variable + ":=" + code + "|" + variable);
	}

	/**
	 * if语句的处理。
	 * 
	 * @param isOK
	 */
	public void dealWithIf(Boolean isOK) {
		String word;
		String code;

		if (isOK) {
			dealWith();
			while (!(word = readWord()).matches("\\;||end||if||while"))
				;
		} else {
			while (!"else".equals((word = readWord())))
				;
			dealWith();
		}
	}

	public void dealWithWhile(String word) {
		state = "while";
		word = readWord();
		String code = getBooleanExpression(word);
		while (judgeBooleanExpression(code)) {
			dealWith();
		}
		while (!"end".equals(readWord()))
			;
	}

	/**
	 * 获得boolean表达式。
	 */
	public String getBooleanExpression(String variable) {
		String word = "";
		String code = variable;
		// if (synbTable.containsKey(code)) {
		// code = synbTable.get(code).getValue();
		// }

		if ("if".equals(state)) {
			while (!"then".equals(word = readWord())) {
				code = code + word;
			}
		} else if ("while".equals(state)) {
			while (!"do".equals(word = readWord())) {
				code = code + word;
			}
		}
		return code;
	}

	/**
	 * 判断boolean表达式。
	 * 
	 * @param variable
	 * @return
	 */
	public Boolean judgeBooleanExpression(String code) {
		Boolean isOK = true;
		String left = "";
		String right = "";
		double leftResult;
		double rightResult;

		String[] splits = null;

		if (code.contains("==")) {
			splits = code.split("==");
			left = splits[0];
			right = splits[1];
			leftResult = Double.parseDouble(calculateExpression(left));
			rightResult = Double.parseDouble(calculateExpression(right));
			isOK = leftResult == rightResult;
		} else if (code.contains(">=")) {
			splits = code.split(">=");
			left = splits[0];
			right = splits[1];
			leftResult = Double.parseDouble(calculateExpression(left));
			rightResult = Double.parseDouble(calculateExpression(right));
			isOK = leftResult >= rightResult;
		} else if (code.contains("<=")) {
			splits = code.split("<=");
			left = splits[0];
			right = splits[1];
			leftResult = Double.parseDouble(calculateExpression(left));
			rightResult = Double.parseDouble(calculateExpression(right));
			isOK = leftResult <= rightResult;
		} else if (code.contains("<")) {
			splits = code.split("<");
			left = splits[0];
			right = splits[1];
			leftResult = Double.parseDouble(calculateExpression(left));
			rightResult = Double.parseDouble(calculateExpression(right));
			isOK = leftResult < rightResult;
		} else if (code.contains(">")) {
			splits = code.split(">");
			left = splits[0];
			right = splits[1];
			leftResult = Double.parseDouble(calculateExpression(left));
			rightResult = Double.parseDouble(calculateExpression(right));
			isOK = leftResult > rightResult;
		}

		return isOK;
	}

	/**
	 * 读当前第一个单词。
	 * 
	 * @return 第一个单词。
	 */
	public String readWord() {
		return scanner.readWord();
	}

	/**
	 * 判断是不是表达式。
	 * 
	 * @param code
	 *            带判断代码。
	 * @return
	 */
	public boolean isExpression(String code) {

		if (code.contains("+") || code.contains("-") || code.contains("*")
				|| code.contains("/")) {
			return true;
		}
		return false;
	}

	/**
	 * 计算表达式。
	 * 
	 * @param expression
	 * @return
	 */
	public String calculateExpression(String expression) {
		String result;
		expression = replaceVariableForExpression(expression);
		priority.dealConverseExpression(expression);
		result = priority.getQuats().get(priority.getQuats().size() - 1)
				.getFourth();
		return result;
	}

	/**
	 * 替换变量。
	 * 
	 * @param word
	 * @return
	 */
	public String replaceVariable(String word) {
		if (synbTable.containsKey(word)) {
			word = synbTable.get(word).getValue();
		}

		return word;
	}

	/**
	 * 将带变量的表达式转换为数字表达式。
	 * 
	 * @param code
	 * @return
	 */
	public String replaceVariableForExpression(String expression) {
		String result = "";
		WordScanner wordScanner = new WordScanner();
		ArrayList<String> words = wordScanner.readWord(expression);

		for (String word : words) {
			result = result + replaceVariable(word);
		}

		return result;
	}

	/**
	 * 构建活动记录。
	 * 
	 * @param word
	 */
	public void buildVallList(String word) {
		vall = new Vall();
		String[] splits = word.split("\\|");
		String action = splits[0];
		word = splits[1];

		vall.setName(word);
		vall.setAction(action);
		if (synbTable.containsKey(word)) {
			vall.setValue(synbTable.get(word).getValue());

			if (synbTable.get(word).getType() == Type.FUNCTION) {

			} else {
				vall.setBelongs("global");
			}

		} else {
			vall.setBelongs("global");
			vall.setValue("null");
		}

		globalList.add(vall);
	}

	/**
	 * 将单个活动记录表压入栈。
	 */
	public void pushVallListToStack() {
		vallStack.push(globalList);
	}

	/**
	 * 输出表。
	 */
	public void display() {
		ArrayList<Vall> list;

		pushVallListToStack();
		System.out.println("符号表总表：");
		for (String key : synbTable.keySet()) {
			System.out.println(synbTable.get(key).toString());
		}

		System.out.println("\n活动记录表：");
		for (int i = vallStack.size(); i > 0; i--) {
			list = vallStack.pop();

			for (Vall vall : list) {
				System.out.println(vall.toString());
			}
		}

	}

	public static void main(String[] args) {
		SignTable sign = new SignTable();
		sign.dealWith();
		sign.display();
	}

}
