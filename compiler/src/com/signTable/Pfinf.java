package com.signTable;

/**
 * 函数表元素实体类。
 * @author 刘鑫伟
 *
 */
public class Pfinf {

	//层次号，该过函静态层次嵌套号。
	private String level;
	//区距，该过函自身数据区起始单元相对该过函值区区头位置。
	private String off;
	//参数个数，该过函的形式参数的个数。
	private String fn;
	//参数表，指向形参表。
	private String entry;
	//入口地址，该函数目标程序首地址(运行时填写)。
	private String param;
	
	/**
	 * 无参构造函数。
	 */
	public Pfinf() {
	}
		
	
	/**
	 * 有参构造函数。
	 * @param level
	 * @param off
	 * @param fn
	 * @param entry
	 * @param param
	 */
	public Pfinf(String level, String off, String fn, String entry, String param) {
		super();
		this.level = level;
		this.off = off;
		this.fn = fn;
		this.entry = entry;
		this.param = param;
	}


	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}


	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}


	/**
	 * @return the off
	 */
	public String getOff() {
		return off;
	}


	/**
	 * @param off the off to set
	 */
	public void setOff(String off) {
		this.off = off;
	}


	/**
	 * @return the fn
	 */
	public String getFn() {
		return fn;
	}


	/**
	 * @param fn the fn to set
	 */
	public void setFn(String fn) {
		this.fn = fn;
	}


	/**
	 * @return the entry
	 */
	public String getEntry() {
		return entry;
	}


	/**
	 * @param entry the entry to set
	 */
	public void setEntry(String entry) {
		this.entry = entry;
	}


	/**
	 * @return the param
	 */
	public String getParam() {
		return param;
	}


	/**
	 * @param param the param to set
	 */
	public void setParam(String param) {
		this.param = param;
	}
	
	
}
