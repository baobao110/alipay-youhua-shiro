package com.task;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.domain.parentOrder;
import com.domain.shopcart;
import com.mapper.parentOrderMapper;
import com.mapper.shopcartMapper;
import com.service.orderServer;

@Component("canell")
public class Canel {
	
	@Autowired
	private shopcartMapper cart;
	
	@Autowired
	private parentOrderMapper parent;
	
	@Autowired
	private orderServer order;
	
	
	public void work() {
		Calendar cal=Calendar.getInstance();//获取当前的时间
		cal.add(cal.MINUTE, -5);//时间向前计算5分钟
		Date date=cal.getTime();
		int offset=0;
		int move=3;
		try {
			order.addStore(date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<shopcart> shop;
		do {
			shop=cart.selectBystatus(2,date,offset,move);
			for(shopcart i:shop) {
				ArrayList<parentOrder> orders=parent.ListBydate(date);//注意这里的悲观锁不然可能出现购物车取消但是order还是待付款
				for(parentOrder j:orders) {
					parent.updateOrderStatus(date);	
				}
				cart.updateCanel(0,i.getId());
			}
			offset+=move;
		}while(offset<shop.size());//长时间未支付取消订单
	}

}
//这里用分页的技术避免一次数据量太大 
//这里的定时器作用是5分钟类如果没有支付就取消订单