package com.dto;

import java.util.ArrayList;

public class catalogDTO {
	
	private String catalogName;
	
	private String catalogNum;
	
	private int childNode;
	
	private String parentNum;
	
	private ArrayList<catalogDTO> dto;//这里的dto类的变量仿照的是catalog的只有最后一项为自己本身的集合

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public String getCatalogNum() {
		return catalogNum;
	}

	public void setCatalogNum(String catalogNum) {
		this.catalogNum = catalogNum;
	}

	public int getChildNode() {
		return childNode;
	}

	public void setChildNode(int childNode) {
		this.childNode = childNode;
	}

	public String getParentNum() {
		return parentNum;
	}

	public void setParentNum(String parentNum) {
		this.parentNum = parentNum;
	}

	public ArrayList<catalogDTO> getDto() {
		return dto;
	}

	public void setDto(ArrayList<catalogDTO> dto) {
		this.dto = dto;
	}

	
	

}
