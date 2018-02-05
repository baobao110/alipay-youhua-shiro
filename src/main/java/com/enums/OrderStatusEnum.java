package com.enums;

import com.exception.BigException;

public enum OrderStatusEnum {
	
	un_pay(0, "������"), un_received(1, "���ջ�"), elivered(2, "�ѷ���"), signed(3, "��ǩ��"), cancel(4, "��ȡ��");
	
	private int k;
    private String v;
    
	public int getK() {
		return k;
	}
	public void setK(int k) {
		this.k = k;
	}
	public String getV() {
		return v;
	}
	public void setV(String v) {
		this.v = v;
	}
	private OrderStatusEnum(int k, String v) {
		this.k = k;
		this.v = v;
	}
    
	public static OrderStatusEnum getStatus(int k) throws Exception {
		 for (OrderStatusEnum statusEnum : OrderStatusEnum.values()) {
	            if (k == statusEnum.getK()) {
	                return statusEnum;
	            }
	        }
		 
		 throw new BigException("û���ҵ���Ӧ��ö��");

	}
    
   

}


