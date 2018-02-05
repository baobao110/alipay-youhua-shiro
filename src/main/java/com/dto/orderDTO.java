package com.dto;

import java.util.ArrayList;

public class orderDTO {
	
	private int orderId;
	
	private String createtime;
	
	private String total;
	
	private String statusInf;
	
	private int status;
	
	private ArrayList<InforDTO> dto;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getStatusInf() {
		return statusInf;
	}

	public void setStatusInf(String statusInf) {
		this.statusInf = statusInf;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ArrayList<InforDTO> getDto() {
		return dto;
	}

	public void setDto(ArrayList<InforDTO> dto) {
		this.dto = dto;
	}

	
	
	

}
