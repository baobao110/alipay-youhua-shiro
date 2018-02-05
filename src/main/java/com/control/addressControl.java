package com.control;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ajax.ajaxDAO;
import com.domain.address;
import com.dto.addressDTO;
import com.service.addressServer;
@RestController
@RequestMapping("/address")
public class addressControl {
	
	@Autowired
	private addressServer addressServer;
	
	
	@RequestMapping("/list")
	public ajaxDAO List() {
		addressDTO dto=addressServer.List();
		return ajaxDAO.success(dto);
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public ajaxDAO save(String city,String area,String name,String userphone,String provinceNum,String cityNumber,String province,String address) {
		try {
			addressServer.add(city, area, address, name, "≥¬÷‹", userphone, provinceNum, cityNumber, 33, province);
			return ajaxDAO.success();
		}
		catch (Exception e) {
			// TODO: handle exception
			return ajaxDAO.failure(e.getMessage());
		}
	}
	
	@RequestMapping("/addressList")
	@ResponseBody
	public ajaxDAO  addressList() {
		ArrayList<address> list=addressServer.addressList(33);
		if(null==list||list.isEmpty()) {
			return ajaxDAO.failure("º”‘ÿ ß∞‹");
		}
		else {
			return ajaxDAO.success(list);
		}
	}
	
	

}
