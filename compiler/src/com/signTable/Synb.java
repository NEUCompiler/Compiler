package com.signTable;

/**
 * 符号表元素实体类。
 * @author 刘鑫伟
 *
 */
public class Synb {
	
	//名字。
	private String name;
	//类型。
	private String type;
	//种类。
	private String cat;
	//地址。
	private String addr;
	
	/**
	 * 无参构造函数。
	 */
	public Synb() {
	}


	/**
	 * 有参构造函数。
	 * @param name
	 * @param type
	 * @param cat
	 * @param addr
	 */
	public Synb(String name, String type, String cat, String addr) {
		super();
		this.name = name;
		this.type = type;
		this.cat = cat;
		this.addr = addr;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}


	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}


	/**
	 * @return the cat
	 */
	public String getCat() {
		return cat;
	}


	/**
	 * @param cat the cat to set
	 */
	public void setCat(String cat) {
		this.cat = cat;
	}


	/**
	 * @return the addr
	 */
	public String getAddr() {
		return addr;
	}


	/**
	 * @param addr the addr to set
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	
	
}
