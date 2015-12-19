package com.signTable;

/**
 * 结构表元素实体类。
 * 
 * @author 刘鑫伟
 *
 */
public class Struts {

	// 结构的域名。
	private String id;
	// 区距。
	private String off;
	// 域成分类型指针，指向idk域成分类型(在类型表中的信息)。
	private String tp;

	/**
	 * 无参构造函数。
	 */
	public Struts() {
	}

	/**
	 * 有参构造函数。
	 * 
	 * @param id
	 * @param off
	 * @param tp
	 */
	public Struts(String id, String off, String tp) {
		super();
		this.id = id;
		this.off = off;
		this.tp = tp;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the off
	 */
	public String getOff() {
		return off;
	}

	/**
	 * @param off
	 *            the off to set
	 */
	public void setOff(String off) {
		this.off = off;
	}

	/**
	 * @return the tp
	 */
	public String getTp() {
		return tp;
	}

	/**
	 * @param tp
	 *            the tp to set
	 */
	public void setTp(String tp) {
		this.tp = tp;
	}

}
