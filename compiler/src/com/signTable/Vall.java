package com.signTable;

/**
 * 活动记录表元素实体类。
 * 
 * @author 刘鑫伟
 *
 */
public class Vall {

	// 从属于哪个活动记录块。
	private String belongs;
	// 局部数据区：用来存放局部变量、内情向量、临时单元。
	private String name;
	// 形式单元：用来存放实参的值或地址。(值)
	private String value;
	// 执行动作。
	private String action;

	/**
	 * 无参构造函数。
	 */
	public Vall() {
	}

	/**
	 * @param belongs
	 * @param value
	 * @param name
	 */
	public Vall(String belongs, String linkDataArea, String value) {
		super();
		this.belongs = belongs;
		this.value = value;
	}

	/**
	 * @return the belongs
	 */
	public String getBelongs() {
		return belongs;
	}

	/**
	 * @param belongs
	 *            the belongs to set
	 */
	public void setBelongs(String belongs) {
		this.belongs = belongs;
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
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[belongs=" + belongs + ", name=" + name + ", value="
				+ value + ", action=" + action + "]";
	}

}
