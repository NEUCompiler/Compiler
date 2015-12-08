package com.signTable;

/**
 * 数组表元素实体类。
 * @author 刘鑫伟
 *
 */
public class Ainf {

	//数组的下界。
	private String low;
	//数组的上界。
	private String up;
	//成分类型指针，指向该维数组成分类型(在类型表中的信息)。
	private String ctp;
	//成分类型的长度，成分类型的数据所值单元的个数。
	private String clen;
	
	/**
	 * 无参构造函数。
	 */
	public Ainf() {
	}
	
	/**
	 * 有参构造函数。
	 * @param low
	 * @param up
	 * @param ctp
	 * @param clen
	 */
	public Ainf(String low, String up, String ctp, String clen) {
		super();
		this.low = low;
		this.up = up;
		this.ctp = ctp;
		this.clen = clen;
	}

	/**
	 * @return the low
	 */
	public String getLow() {
		return low;
	}

	/**
	 * @param low the low to set
	 */
	public void setLow(String low) {
		this.low = low;
	}

	/**
	 * @return the up
	 */
	public String getUp() {
		return up;
	}

	/**
	 * @param up the up to set
	 */
	public void setUp(String up) {
		this.up = up;
	}

	/**
	 * @return the ctp
	 */
	public String getCtp() {
		return ctp;
	}

	/**
	 * @param ctp the ctp to set
	 */
	public void setCtp(String ctp) {
		this.ctp = ctp;
	}

	/**
	 * @return the clen
	 */
	public String getClen() {
		return clen;
	}

	/**
	 * @param clen the clen to set
	 */
	public void setClen(String clen) {
		this.clen = clen;
	}
	
	
	
	
}
