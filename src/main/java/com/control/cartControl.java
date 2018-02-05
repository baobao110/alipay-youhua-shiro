package com.control;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ajax.ajaxDAO;
import com.domain.commodity;
import com.domain.shopcart;
import com.domain.user;
import com.dto.cartDTO;
import com.service.cartServer;

@RestController
@RequestMapping("/cart")
public class cartControl {
	
	@Autowired
	private cartServer cart;
	
	@RequestMapping("/addCart")
	public ajaxDAO add(int commodityId,int commodityNum) {
		Subject subject = SecurityUtils.getSubject();
	 	user u=(user)subject.getSession().getAttribute("User");
		try {
			cart.add(commodityId, commodityNum, u.getId());
			return ajaxDAO.success();
		}
		catch (Exception e) {
			// TODO: handle exception
			return ajaxDAO.failure(e.getMessage());
		}
	}
	
	@RequestMapping("/getNum")
	public ajaxDAO getNum() {
		Subject subject = SecurityUtils.getSubject();
	 	user u=(user)subject.getSession().getAttribute("User");
		int num=cart.getNum(u.getId());
		return ajaxDAO.success(num);
	}
	
	@RequestMapping("/detail")
	public ajaxDAO detail() {
		Subject subject = SecurityUtils.getSubject();
	 	user u=(user)subject.getSession().getAttribute("User");
		cartDTO dto=cart.detail(u.getId(),1);
		return ajaxDAO.success(dto);
	}
	
	@RequestMapping("/detail2")
	public ajaxDAO detail2() {
		Subject subject = SecurityUtils.getSubject();
	 	user u=(user)subject.getSession().getAttribute("User");
		cartDTO dto=cart.detail(u.getId(),2);
		return ajaxDAO.success(dto);
	}
	
	@RequestMapping("/delete")
	public  ajaxDAO delete(int id) {
		Subject subject = SecurityUtils.getSubject();
	 	user u=(user)subject.getSession().getAttribute("User");
		try {
			cart.delete(id, u.getId());
			return ajaxDAO.success();
		}
		catch (Exception e) {
			// TODO: handle exception
			return ajaxDAO.failure(e.getMessage());
		}
	}
	
	
	@RequestMapping("/result")
	@ResponseBody
	public ajaxDAO result(@RequestBody String str,HttpSession  session) {
		System.out.println("str-------------------"+str+"}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}]s");
		session.setAttribute("cart", str);
		String[]x=str.trim().split("\\;");//注意这里为什么用trim()是因为前端传递的数据可能会出现空格的情况,这时在前端看不出来,这里可以通过后台的输出打印查看 
		try {
			for(int i=0;i<x.length;i++) {
				System.out.println("LLLLLLLLLLLLLLLLLLL"+i+"PPPPPPPPPPPPPPPPPPPPPPPPPPPPPP");
				cart.update(x[i]);
			}
			return ajaxDAO.success();
		}catch (Exception e) {
			// TODO: handle exception
			return ajaxDAO.failure(e.getMessage());
		}
		
	}

}
