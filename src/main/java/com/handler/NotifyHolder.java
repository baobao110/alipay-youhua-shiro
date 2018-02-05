package com.handler;

public class NotifyHolder {
	
	 private int orderId;
	 private double totalAmount;
	 private boolean paySuccess;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public boolean isPaySuccess() {
		return paySuccess;
	}
	public void setPaySuccess(boolean paySuccess) {
		this.paySuccess = paySuccess;
	}
	 
	   public static NotifyHolder failed() {
	        return new NotifyHolder(false);
	    }

	    public static NotifyHolder success(int orderId, double totalAmount) {
	        return new NotifyHolder(orderId, totalAmount, true);
	    }

	    private NotifyHolder() {

	    }

	    private NotifyHolder(boolean paySuccess) {
	        this.paySuccess = paySuccess;
	    }

	    private NotifyHolder(int orderId,double totalAmount, boolean paySuccess) {
	        this.orderId = orderId;
	        this.totalAmount = totalAmount;
	        this.paySuccess = paySuccess;
	    }
}
