package com.gaobayi;

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
	// 符号表。
	private Map<String, String> signMap = new HashMap<>();
	// 常数表。
	private Map<String, String> constantMap = new HashMap<>();
	
	/**
	 * 从文本中读取代码。
	 */
	public void readCodeFromFile() {
		try {
			File myFile = new File(CODE_PATH);
			FileReader fileReader =new FileReader(myFile);
			BufferedReader reader =new BufferedReader(fileReader);
			String line = null;
			
			while ((line = reader.readLine()) != null) { 
				code = code + line.trim();
			}
			
			reader.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		code = code.replace(" ", "");
		System.out.println(code);
		
	}
	/**
	 * 将词表写入Map。
	 */
	public void writeToMap() {
		try {
			File myFile = new File(TABLE_PATH);
			FileReader fileReader =new FileReader(myFile);
			BufferedReader reader =new BufferedReader(fileReader);
			String line = null;
			String kind = null;
			
			while ((line = reader.readLine()) != null) { 
				String[] splits = line.split(" ");
				
				if("KeyWord".equals(line)||"Border".equals(line))
				{
					kind = line;
				}
				else if ("KeyWord".equals(kind)){
					keyWordMap.put( splits[0],splits[1] );
				}
				else if("Border".equals(kind)){
					borderMap.put( splits[0],splits[1] );
					
				}
					
				}
				
			reader.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
//		System.out.println(keyWordMap.toString());
//		System.out.println(borderMap.toString());
	}
	
	/**
	 * 扫描代码。
	 * @param code 代码字符串。
	 */
	public void scan(String code) {
	}
	
	public void addTable() {
		
	}
	
	public static void main(String[] args) {
	 WordScanner scaner = new WordScanner();
	 scaner.writeToMap();
	}
	}

