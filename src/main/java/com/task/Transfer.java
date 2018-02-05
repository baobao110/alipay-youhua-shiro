package com.task;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.control.orderControl;
import com.domain.parentOrder;
import com.domain.shopcart;
import com.mapper.parentOrderMapper;
import com.mapper.shopcartMapper;
import com.service.orderServer;

@Component("alipay")
public class Transfer {
	@Autowired
	private shopcartMapper cart;
	
	@Autowired
	private parentOrderMapper parent;
	
	@Autowired
	private orderServer order;
	
	private  Logger log=LoggerFactory.getLogger(Transfer.class);
	
	
	public void work(){
		int offset=0;
		int move=3;
		Calendar cal=Calendar.getInstance();//��ȡ��ǰ��ʱ��
		cal.add(cal.MINUTE, -1);//ʱ����ǰ��1����
		Date date=cal.getTime();
		ArrayList<shopcart> shop1;
		do {
			shop1=cart.selectBystatus(3,date,offset,move);
			for(shopcart i:shop1) {
				ArrayList<parentOrder> make=parent.ListByUserId(i.getUserid());
				for(parentOrder j:make) {
					if(j.getStatus()==0) {
							try {
								order.drop(j.getId(), date);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}//��ֹ�������ǹ��ﳵ�Ǳ�״̬Ϊ�Ѿ������order�Ǳ߻���δ���� 
					}
				}
			}
			offset+=move;
		}while(shop1.size()<0);
		
	}

}


