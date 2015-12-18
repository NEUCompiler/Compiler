package com.middleCode;

/**
 * 四元式象实体类。
 * @author 刘鑫伟
 *
 */
public class Quat {

	private String first;
	private String second;
	private String third;
	private String fourth;
	
	public Quat() {
	}
	
	public Quat(String first) {
		super();
		this.first = first;
	}

	public Quat(String first, String second, String third, String fourth) {
		super();
		this.first = first;
		this.second = second;
		this.third = third;
		this.fourth = fourth;
	}

	/**
	 * @return the first
	 */
	public String getFirst() {
		return first;
	}

	/**
	 * @param first the first to set
	 */
	public void setFirst(String first) {
		this.first = first;
	}

	/**
	 * @return the second
	 */
	public String getSecond() {
		return second;
	}

	/**
	 * @param second the second to set
	 */
	public void setSecond(String second) {
		this.second = second;
	}

	/**
	 * @return the third
	 */
	public String getThird() {
		return third;
	}

	/**
	 * @param third the third to set
	 */
	public void setThird(String third) {
		this.third = third;
	}

	/**
	 * @return the fourth
	 */
	public String getFourth() {
		return fourth;
	}

	/**
	 * @param fourth the fourth to set
	 */
	public void setFourth(String fourth) {
		this.fourth = fourth;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Quat [first=" + first + ", second=" + second + ", third="
				+ third + ", fourth=" + fourth + "]";
	}
	
}
