package com.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ajax.ajaxDAO;
import com.domain.commodity;
import com.domain.commodityWithBLOBs;
import com.service.commodityServer;

@Controller
@RequestMapping("/commodity")
public class commodityControl {
	
	@Autowired
	private commodityServer com;
	
	@RequestMapping("/information")
	@ResponseBody
	public ajaxDAO getInformation(int id) {
		commodityWithBLOBs commodity=com.getInformation(id);
		return ajaxDAO.success(commodity);
	}

}
