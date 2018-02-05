package com.control;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ajax.ajaxDAO;
import com.domain.user;
import com.dto.orderDTO;
import com.service.orderServer;

@Controller
@RequestMapping("/order")
public class orderControl {
	
	@Autowired
	private orderServer order;
	
	private  Logger log=LoggerFactory.getLogger(orderControl.class);
	
	@RequestMapping("/pay")
	@ResponseBody
	public ajaxDAO pay(int addressId,String method,HttpSession session) {
		System.out.println("==============="+addressId+"]]]]]]]]]]]]]{{{{{"+method+"\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
		String cart=(String) session.getAttribute("cart");
		try {
			String msg=order.pay(addressId, Integer.parseInt(method), cart);
			return ajaxDAO.success(msg);
		}catch (Exception e) {
			// TODO: handle exception
			log.error("}}}}}}}"+e.getMessage(),e);//以后打印错误信息用error这种方法 
			return ajaxDAO.failure(e.getMessage());
		}
	}
	
	@RequestMapping("/cart")
	@ResponseBody
	public ajaxDAO cart(HttpSession session) {
		String cart=(String) session.getAttribute("cart");
		Subject subject = SecurityUtils.getSubject();
	 	user u=(user)subject.getSession().getAttribute("User");
		try {
			ArrayList<orderDTO> dto=order.order(u.getId(),cart);
			return ajaxDAO.success(dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return ajaxDAO.failure(e.getMessage());
		}
	}

}
