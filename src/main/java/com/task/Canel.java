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
		Calendar cal=Calendar.getInstance();//��ȡ��ǰ��ʱ��
		cal.add(cal.MINUTE, -5);//ʱ����ǰ����5����
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
				ArrayList<parentOrder> orders=parent.ListBydate(date);//ע������ı�������Ȼ���ܳ��ֹ��ﳵȡ������order���Ǵ�����
				for(parentOrder j:orders) {
					parent.updateOrderStatus(date);	
				}
				cart.updateCanel(0,i.getId());
			}
			offset+=move;
		}while(offset<shop.size());//��ʱ��δ֧��ȡ������
	}

}
//�����÷�ҳ�ļ�������һ��������̫�� 
//����Ķ�ʱ��������5���������û��֧����ȡ������