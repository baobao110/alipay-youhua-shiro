package com.control;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.domain.user;


@Controller
@RequestMapping("/Page")
public class PageControl {
	
	@RequestMapping("/toInformation")
	 public ModelAndView toCommodityInfo(int commodityId) {
	 	ModelAndView modelAndView = new ModelAndView();
	 	modelAndView.addObject("commodityId",commodityId);
	 	modelAndView.setViewName("home/introduction");
	 	return modelAndView;
	}
	
	@RequestMapping("/toCart")
	 public ModelAndView toCart() {
		Subject subject = SecurityUtils.getSubject();
		user u=(user) subject.getSession().getAttribute("User");
	 	ModelAndView modelAndView = new ModelAndView();
	 	modelAndView.addObject("userId",u.getId());
	 	modelAndView.setViewName("home/shopcart");
	 	return modelAndView;
	}
	
	@RequestMapping("/toAddress")
	 public ModelAndView toAddress() {
	 	ModelAndView modelAndView = new ModelAndView();
	 	Subject subject = SecurityUtils.getSubject();
	 	user u=(user)subject.getSession().getAttribute("User");
	 	modelAndView.addObject("userId",u.getId());
	 	modelAndView.setViewName("person/address");
	 	return modelAndView;
	}
	
	@RequestMapping("/toPay")
	 public ModelAndView toPay() {
	 	ModelAndView modelAndView = new ModelAndView();
	 	Subject subject = SecurityUtils.getSubject();
	 	user u=(user)subject.getSession().getAttribute("User");//这里通过shiro的方式获取session
	 	modelAndView.addObject("userId",u.getId());
	 	modelAndView.setViewName("home/pay");
	 	return modelAndView;
	}//注意这里的spring的Control的方法形式这里addObject方法保存的是需要传递到前端的变量 ,setViewName方法传递的是需要跳转的地址链接
	
	@RequestMapping("/order")
	 public ModelAndView order() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("person/order");
	 	return modelAndView;
	}
	
	@RequestMapping("/toLogin")
	public String toLogin() {
		return "home/login";
	}
	
	@RequestMapping("/toRegister")
	 public ModelAndView toRegister(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home/register");
		String uuID=UUID.randomUUID().toString();
		session.setAttribute("UUID", uuID);
	 	return modelAndView;
	}
	
}
