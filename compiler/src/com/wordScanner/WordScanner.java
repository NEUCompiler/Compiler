package com.wordScanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

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
				code = code + line.trim();
			}

			reader.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// code = code.replace(" ", "");
		System.out.println(code);

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
				String[] splits = line.split(" ");

				if ("KeyWord".equals(line) || "Border".equals(line)) {
					kind = line;
				} else if ("KeyWord".equals(kind)) {
					keyWordMap.put(splits[0], splits[1]);
				} else if ("Border".equals(kind)) {
					borderMap.put(splits[0], splits[1]);

				}

			}

			reader.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// System.out.println(keyWordMap.toString());
		// System.out.println(borderMap.toString());aaa
	}

	/**
	 * 扫描代码。
	 * 
	 * @param code
	 *            代码字符串。
	 */
	public void scan() {
		String word; // 当前单词。
		char start; // 开始字符。
		char ch; // 当前字符。

		while (!"".equals(code)) {
			int i = 0;
			
			while (code.indexOf(" ") == 0) {
				code = code.substring(1);
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
				word = code.substring(0, i);
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
				if(start == '<' || start == '>' ||start == '='|| start == ':') {
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
		
		System.out.println(token);
	}

	public static void main(String[] args) {
		WordScanner scaner = new WordScanner();
		scaner.writeToMap();
		scaner.readCodeFromFile();
		scaner.scan();
	}
}