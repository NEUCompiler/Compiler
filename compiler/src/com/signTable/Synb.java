package com.signTable;

/**
 * 符号表总表元素实体类。
 * 
 * @author 刘鑫伟
 *
 */
public class Synb {

	// 名字，标识符源码。
	private String name;
	// 值(新增项)。
	private String value;
	/*
	 * 类型，指针，指向类型表相应项。:i(整型)，r(实型)，c(字符型)，b(布尔型)， a(数组型)，
	 * d(结构型)f(函数)，c(常量)，t(类型)，d(域名)，v,vn,vf(变量，换名形参，赋值形参)。
	 */
	private Type type;
	// 数据长度。
	private int length;

	/**
	 * 无参构造函数。
	 */
	public Synb() {
	}

	/**
	 * 有参构造函数。
	 * 
	 * @param name
	 * @param type
	 * @param length
	 */
	public Synb(String name, Type type, int length) {
		super();
		this.name = name;
		this.type = type;
		this.length = length;
	}

	/**
	 * 有参构造函数。
	 * 
	 * @param name
	 * @param value
	 * @param type
	 * @param cat
	 * @param length
	 */
	public Synb(String name, String value, Type type, int length) {
		super();
		this.name = name;
		this.value = value;
		this.type = type;
		this.length = length;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @param length
	 *            the length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "name=" + name + ", value=" + value + ", type=" + type
				+ ", length=" + length;
	}

}
