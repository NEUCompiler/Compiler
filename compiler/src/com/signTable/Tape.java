package com.signTable;

/**
 * 类型表元素实体类。
 * @author 刘鑫伟
 *
 */
public class Tape {

	//类码，类型代码。
	private String tVal;
	//指针，根据数据类型不同，指向不同的表项。
	private String tPoint;
	
	/**
	 * 无参构造函数。
	 */
	public Tape() {
	}
	
	/**
	 * 有参构造函数。
	 * @param tVal
	 * @param tPoint
	 */
	public Tape(String tVal, String tPoint) {
		super();
		this.tVal = tVal;
		this.tPoint = tPoint;
	}

	/**
	 * @return the tVal
	 */
	public String gettVal() {
		return tVal;
	}

	/**
	 * @param tVal the tVal to set
	 */
	public void settVal(String tVal) {
		this.tVal = tVal;
	}

	/**
	 * @return the tPoint
	 */
	public String gettPoint() {
		return tPoint;
	}

	/**
	 * @param tPoint the tPoint to set
	 */
	public void settPoint(String tPoint) {
		this.tPoint = tPoint;
	}
	
	
	
}
