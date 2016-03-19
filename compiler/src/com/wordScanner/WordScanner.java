package com.wordScanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

/**
 * 词法扫描器
 * 
 * @author Gao, Bayi; Liu, Xinwei
 *
 */
public class WordScanner {

	// 词表路径。
	public static final String TABLE_PATH = "resource/table.txt";
	// 代码路径。
	public static final String CODE_PATH = "resource/code.txt";

	// 待扫描代码。
	private String code = "";
	// token。
	private String token = "";
	// 关键字表。
	private Map<String, String> keyWordMap = new HashMap<>();
	// 界符表。
	private Map<String, String> borderMap = new HashMap<>();
	// 标示符表。
	private Map<String, String> idSignMap = new HashMap<>();
	// 常数表。
	private Map<String, String> constantMap = new HashMap<>();

	private Stack<String> pastword = new Stack<>();

	{
		readCodeFromFile();
		writeToMap();
		scan(code);
	}

	/**
	 * 从文本中读取代码。
	 */
	public void readCodeFromFile() {
		try {
			File myFile = new File(CODE_PATH);
			FileReader fileReader = new FileReader(myFile);
			BufferedReader reader = new BufferedReader(fileReader);
			String line = null;

			while ((line = reader.readLine()) != null) {
				code = code + line + " ";
			}

			reader.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 将词表写入Map。
	 */
	public void writeToMap() {
		try {
			File myFile = new File(TABLE_PATH);
			FileReader fileReader = new FileReader(myFile);
			BufferedReader reader = new BufferedReader(fileReader);
			String line = null;
			String kind = null;

			while ((line = reader.readLine()) != null) {

				if ("KeyWord".equals(line) || "Border".equals(line)) {
					kind = line;
				} else if ("KeyWord".equals(kind)) {
					String[] splits = line.split(" ");
					keyWordMap.put(splits[0], splits[1]);
				} else if ("Border".equals(kind)) {
					String[] splits = line.split(" ");
					borderMap.put(splits[0], splits[1]);
				}
			}

			reader.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * 扫描代码。
	 * 
	 * @param code
	 *            代码字符串。
	 */
	public void scan(String code) {
		String word; // 当前单词。
		char start; // 开始字符。
		char ch; // 当前字符。

		while (!"".equals(code)) {
			int i = 0;

			while (code.indexOf(" ") == 0) {
				code = code.substring(1);
			}

			if ("".equals(code)) {
				break;
			}

			start = code.charAt(0);
			if ((start >= 'A' && start <= 'Z')
					|| (start >= 'a' && start <= 'z')) {
				ch = code.charAt(0);
				while ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')
						|| Character.isDigit(ch)) {
					i = i + 1;
					if (i >= code.length()) {
						break;
					}
					ch = code.charAt(i);
				}
				word = code.substring(0, i); // 截取字符串
				// 判断当前单词在哪个表中
				if (keyWordMap.containsKey(word)) {
					token = token + "<" + word + "," + "Keyword" + ","
							+ keyWordMap.get(word) + ">";
				} else if (idSignMap.containsKey(word)) {
					token = token + "<" + word + "," + "idSign" + ","
							+ idSignMap.get(word) + ">";
				} else {
					idSignMap.put(word, "0" + (idSignMap.size() + 1));
					token = token + "<" + word + "," + "idSign" + ","
							+ idSignMap.get(word) + ">";
				}
			} else if (Character.isDigit(start)) {
				int pointerTimes = 0;
				ch = code.charAt(0);

				while (Character.isDigit(ch)
						|| ((ch == '.') && pointerTimes == 0)) {
					if (ch == '.') {
						pointerTimes++;
					}
					i = i + 1;
					if (i >= code.length()) {
						break;
					}
					ch = code.charAt(i);
				}
				word = code.substring(0, i);
				if (constantMap.containsKey(word)) {
					token = token + "<" + word + "," + "constant" + ","
							+ constantMap.get(word) + ">";
				} else {
					constantMap.put(word, "0" + (constantMap.size() + 1));
					token = token + "<" + word + "," + "constant" + ","
							+ constantMap.get(word) + ">";
				}

			} else {
				start = code.charAt(0);
				if (start == '<' || start == '>' || start == '='
						|| start == ':') {
					ch = code.charAt(1);

					String signAdd = "" + start + ch;

					if (borderMap.containsKey(signAdd)) {
						i = i + 2;
						token = token + "<" + signAdd + "," + "border" + ","
								+ borderMap.get(signAdd) + ">";
					} else {
						i = i + 1;
						token = token + "<" + start + "," + "border" + ","
								+ borderMap.get("" + start) + ">";
					}
				} else {
					i = i + 1;
					token = token + "<" + start + "," + "border" + ","
							+ borderMap.get("" + start) + ">";
				}
			}
			code = code.substring(i);
			token = token + " ";
		}
	}

	/**
	 * 读单词，给语法分析提供使用。
	 * 
	 * @param code
	 *            待识别的程序。
	 * @return 当前单词。
	 */
	public String readWord() {
		String word = ""; // 当前单词。
		char start; // 开始字符。
		char ch; // 当前字符。
		int i = 0;

		while (code.indexOf(" ") == 0) {
			code = code.substring(1);
		}

		if ("".equals(code)) {
			return "";
		}

		start = code.charAt(0);
		if ((start >= 'A' && start <= 'Z') || (start >= 'a' && start <= 'z')) {
			ch = code.charAt(0);
			while ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')
					|| Character.isDigit(ch)) {
				i = i + 1;
				if (i >= code.length()) {
					break;
				}
				ch = code.charAt(i);
			}
			word = code.substring(0, i);
		} else if (Character.isDigit(start)) {
			int pointerTimes = 0;
			ch = code.charAt(0);

			while (Character.isDigit(ch) || ((ch == '.') && pointerTimes == 0)) {
				if (ch == '.') {
					pointerTimes++;
				}
				i = i + 1;
				if (i >= code.length()) {
					break;
				}
				ch = code.charAt(i);
			}
			word = code.substring(0, i);
		} else {
			if (start == '<' || start == '>' || start == '=' || start == ':') {
				ch = code.charAt(1);

				String signAdd = "" + start + ch;

				if (borderMap.containsKey(signAdd)) {
					i = i + 2;
					word = signAdd;
				} else {
					i = i + 1;
					word = "" + start;
				}
			} else {
				i = i + 1;
				word = "" + start;
			}
		}
		code = code.substring(i);

		return word;
	}

	public String read() {
		String current = readWord();
		String currents = null;
		if (borderMap.containsKey(current)) {
			currents = current + "|" + "p";
		} else if (idSignMap.containsKey(current)) {
			currents = current + "|" + "i";
		} else if (keyWordMap.containsKey(current)) {
			currents = current + "|" + "k";
		} else if (constantMap.containsKey(current)) {
			currents = current + "|" + "c";
		} else
			currents = current + "|" + "err";
		return currents;
	}

	public ArrayList<String> readWord(String code) {
		ArrayList<String> result = new ArrayList<>();
		this.code = code;
		while (!"".equals(this.code)) {
			result.add(readWord());
		}

		return result;
	}

	public ArrayList<String> fhq() {
		ArrayList<String> ret = new ArrayList<>();
		String now = null, past = null;
		now = readWord();
		while (!".".equals(now)) {
			if (":=".equals(now)) {
				past = pastword.pop();
				ret.add(past);
				pastword.push("" + now);
				now = readWord();
			} else {
				pastword.push("" + now);
				now = readWord();
			}
		}
		return ret;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	
	
	public void printToken() {
		String[] splits = token.split(" ");
		token = "";
		
		System.out.println("token:\n");
		
		for (int i=1; i<splits.length; i++) {
			if (i%8 == 0) {
				token = token + splits[i] + "\n";
			} else {
				token = token + splits[i] + " ";
			}
		}
		
		System.out.println(token);
	}

	public static void main(String[] args) {
		WordScanner scaner = new WordScanner();
		scaner.scan(scaner.code);
		scaner.printToken();

	}
}
