package com.enums;

import com.exception.BigException;

public enum OrderStatusEnum {
	
	un_pay(0, "待付款"), un_received(1, "待收货"), elivered(2, "已发货"), signed(3, "已签收"), cancel(4, "已取消");
	
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
		 
		 throw new BigException("没有找到对应的枚举");

	}
    
   

}


