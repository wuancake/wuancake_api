package com.wuan.weekly.entity.maggic;

public class Msg {

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
	public Msg(String infoText, int infoCode) {
		super();
		this.infoText = infoText;
		this.infoCode = infoCode;
	}
	public Msg() {
		super();
	}
}
