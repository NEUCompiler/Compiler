package com.signTable;

/**
 * 长度表元素实体类。 
 * @author 刘鑫伟
 *
 */
public class Len {

	//标识名。
	private String name;
	//长度。
	private String length;
	
	/**
	 * 无参构造函数。
	 */
	public Len() {
	}
	
	/**
	 * 有参构造函数。
	 * @param name
	 * @param length
	 */
	public Len(String name, String length) {
		super();
		this.name = name;
		this.length = length;
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
	 * @return the length
	 */
	public String getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(String length) {
		this.length = length;
	}
	
	
	
}
