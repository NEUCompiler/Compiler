package com.signTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 符号表。
 * @author 刘鑫伟
 *
 */
public class signTable {

	//符号表总表。
	private List<Synb> synbList = new ArrayList<>();
	//类型表。
	private List<Tape> tapeList = new ArrayList<>();
	//数组表。
	private List<Ainf> ainfList = new ArrayList<>();
	//结构表。
	private List<Rinf> rinfList = new ArrayList<>();
	//函数表。
	private List<Pfinf> pfinfList = new ArrayList<>();
	//长度表。
	private List<Len> lenList = new ArrayList<>();
	//活动记录表。
	private List<Vall> vallList = new ArrayList<>();
	//常量表。
	private Map<String, String> constantTable = new HashMap<>();
	
	
}
