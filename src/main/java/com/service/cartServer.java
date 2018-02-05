package com.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.domain.shopcart;
import com.dto.cartDTO;
import com.exception.BigException;
import com.mapper.commodityMapper;
import com.mapper.shopcartMapper;

@Service
public class cartServer {
	
	@Autowired
	private shopcartMapper cart;
	
	@Autowired
	private commodityMapper commodity;
	
	@Transactional(rollbackFor=Exception.class)
	public void add(int commodityId,int commodityNum,int userId) throws Exception {
		shopcart is =cart.select(userId, commodityId);
		if(null == is) {
			shopcart a=new shopcart();
			a.setCommid(commodityId);
			a.setCommnum(commodityNum);
			a.setUserid(userId);
			a.setCreateTime(new Date());
			a.setModifyTime(new Date());
			a.setStatus(1);
			int row=cart.insert(a);
			if(1!=row) {
				throw new BigException("添加购物车失败");
			}
		}
		
		else {
			int row=cart.updateNum(commodityNum, userId, commodityId, new Date());
			if(1!=row) {
				throw new BigException("添加购物车失败");
			}
		}
		
		 
	}
	
	public int getNum(int userId) {
		return cart.getNum(userId);
		
	}
	
	
	public cartDTO detail(int userId,int status) {
		DecimalFormat   df  = new DecimalFormat("######0.00");
		cartDTO dto=new cartDTO();
		ArrayList<shopcart> shop=cart.selectByMore(userId, status);
		ArrayList<cartDTO> List=new ArrayList<>(shop.size());
		dto.setDto(List);
		for(shopcart i:shop) {
			cartDTO x=new cartDTO();
			System.out.println("_______+++++++++"+i.getCommid());
			com.domain.commodity y=commodity.selectByPrimaryKey(i.getCommid());
			System.out.println(">>>>>>>>>>>+"+y);
			x.setDto(null);
			x.setBookPrice(Double.parseDouble(y.getCommPrice()));
			x.setFlag(false);
			x.setNum(i.getCommnum());
			x.setPictureUName(y.getPictureName());
			x.setStock(y.getStore());
			x.setBookName(y.getCommName());
			x.setId(y.getId());
			double price=x.getBookPrice()*x.getNum();
			x.setPrice(Double.parseDouble(df.format(price)));
			List.add(x);
			
		}
		return dto;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void delete(int id,int userId) throws BigException {
		shopcart shop=cart.select(userId, id);
		if(null==shop) {
			throw new BigException("商品不存在");
		}
		int row=cart.delete(userId, id);
		if(1!=row) {
			throw new BigException("删除失败");
		}
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void update(String shop ) throws Exception {
		String[] x=shop.trim().split("\\,");
		int id=Integer.parseInt(x[0]);
		int commNum=Integer.parseInt(x[1]);
		if(1!=cart.updateByPrimaryKey(id,commNum)) {
			throw new BigException("结算失败");
		}
	
	}
}
