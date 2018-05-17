package com.wuan.weekly.entity;

public class Info {

	protected String infoText;
	protected int infoCode;
	
	
	public String getInfoText() {
		return infoText;
	}
	public void setInfoText(String infoText) {
		this.infoText = infoText;
	}
	public int getInfoCode() {
		return infoCode;
	}
	public void setInfoCode(int infoCode) {
		this.infoCode = infoCode;
	}
	public Info() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public Info(String infoText, int infoCode) {
		super();
		this.infoText = infoText;
		this.infoCode = infoCode;
	}
	
	
}
