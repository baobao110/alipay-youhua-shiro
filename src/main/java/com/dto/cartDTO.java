package com.dto;

import java.util.ArrayList;

public class cartDTO {
	
	private String bookName;
	
	private double bookPrice;
	
	private int Num;
	
	Boolean flag;
	
	private int stock;
	
	private ArrayList<cartDTO> dto;
	
	private String pictureUName;
	
	private double price;
	
	private int id;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPictureUName() {
		return pictureUName;
	}

	public void setPictureUName(String pictureUName) {
		this.pictureUName = pictureUName;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public double getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}

	public int getNum() {
		return Num;
	}

	public void setNum(int num) {
		Num = num;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public ArrayList<cartDTO> getDto() {
		return dto;
	}

	public void setDto(ArrayList<cartDTO> dto) {
		this.dto = dto;
	}
	
	

}
