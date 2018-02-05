package com.control;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ajax.ajaxDAO;
import com.domain.catalog;
import com.domain.commodity;
import com.dto.bookDTO;
import com.dto.catalogDTO;
import com.service.catalogServer;
import com.service.commodityServer;

@Controller
@RequestMapping("/catalog")
public class catalogControl {
	
	@Autowired
	private catalogServer catalog;
	@Autowired
	private commodityServer commodity;
	
	@RequestMapping("/home")
	public String home() {
		return "home/home";//这里注意spring的路径
	}
	
	@RequestMapping("/cataList")
	@ResponseBody
	public ajaxDAO cataList() {
		ArrayList<catalogDTO> parent=catalog.List();
		return ajaxDAO.success(parent);
	}
	
	
	@RequestMapping("/book")
	@ResponseBody
	public ajaxDAO book() {
		ArrayList<bookDTO> book=commodity.bookList();
		return ajaxDAO.success(book);
	}
	
	
	@RequestMapping("/ParentList")
	@ResponseBody
	public ajaxDAO ParentList() {
		ArrayList<catalog> parent=catalog.ParentList();
		return ajaxDAO.success(parent);
	}
	
	@RequestMapping("/Lazyload")
	@ResponseBody
	public ajaxDAO Lazyload(String catalogNum) {
		bookDTO load=commodity.lazyLoad(catalogNum);
		return ajaxDAO.success(load);
	}
	

}
