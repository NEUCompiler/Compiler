package com.signTable;

/**
 * 活动记录表元素实体类。
 * @author 刘鑫伟
 *
 */
public class Vall {

	private String linkDataArea;
	private String formTerm;
	private String localDataArea;
	private String StackPoint;
	
	/**
	 * 无参构造函数。
	 */
	public Vall() {
	}
	
	/**
	 * 
	 * @param linkDataArea
	 * @param formTerm
	 * @param localDataArea
	 * @param stackPoint
	 */
	public Vall(String linkDataArea, String formTerm, String localDataArea,
			String stackPoint) {
		super();
		this.linkDataArea = linkDataArea;
		this.formTerm = formTerm;
		this.localDataArea = localDataArea;
		StackPoint = stackPoint;
	}

	/**
	 * @return the linkDataArea
	 */
	public String getLinkDataArea() {
		return linkDataArea;
	}

	/**
	 * @param linkDataArea the linkDataArea to set
	 */
	public void setLinkDataArea(String linkDataArea) {
		this.linkDataArea = linkDataArea;
	}

	/**
	 * @return the formTerm
	 */
	public String getFormTerm() {
		return formTerm;
	}

	/**
	 * @param formTerm the formTerm to set
	 */
	public void setFormTerm(String formTerm) {
		this.formTerm = formTerm;
	}

	/**
	 * @return the localDataArea
	 */
	public String getLocalDataArea() {
		return localDataArea;
	}

	/**
	 * @param localDataArea the localDataArea to set
	 */
	public void setLocalDataArea(String localDataArea) {
		this.localDataArea = localDataArea;
	}

	/**
	 * @return the stackPoint
	 */
	public String getStackPoint() {
		return StackPoint;
	}

	/**
	 * @param stackPoint the stackPoint to set
	 */
	public void setStackPoint(String stackPoint) {
		StackPoint = stackPoint;
	}
	
	
}
