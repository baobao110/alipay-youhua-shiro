package com.dto;

import java.util.ArrayList;

public class bookDTO {
	
	private String bookName;
	
	private String bookPrice;
	
	private String pictureName;
	
	private String catalog;
	
	private int id;
	
	private  ArrayList<bookDTO> dto;

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(String bookPrice) {
		this.bookPrice = bookPrice;
	}

	public String getPictureName() {
		return pictureName;
	}

	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}

	public ArrayList<bookDTO> getDto() {
		return dto;
	}

	public void setDto(ArrayList<bookDTO> dto) {
		this.dto = dto;
	}

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	

}
