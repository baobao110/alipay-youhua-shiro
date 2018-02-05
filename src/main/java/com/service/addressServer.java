package com.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dto.addressDTO;
import com.exception.BigException;
import com.mapper.addressMapper;
import com.mapper.areaMapper;
import com.mapper.cityMapper;
import com.mapper.provinceMapper;
import com.domain.*;

@Service
public class addressServer {
	
	@Autowired
	private provinceMapper province;
	
	@Autowired
	private cityMapper city;
	
	@Autowired
	private areaMapper area;
	
	@Autowired
	private addressMapper addressMap;
	
	public addressDTO List() {
		addressDTO dto=new addressDTO();
		ArrayList<province>pro=province.Province();
		ArrayList<addressDTO> a=new ArrayList<>(pro.size());
		dto.setDto(a);
		for(province i:pro) {
			addressDTO x=new addressDTO();
			x.setName(i.getProvincename());
			x.setNum(Integer.parseInt(i.getProvincenumber()));
			ArrayList<city> cit=city.City(i.getProvincenumber());
			ArrayList<addressDTO> b=new ArrayList<>(cit.size());
			x.setDto(b);
			a.add(x);
			for(city j:cit) {
				addressDTO y=new addressDTO();
				y.setName(j.getCityname());
				y.setNum(Integer.parseInt(j.getCitynumber()));
				ArrayList<area> local=area.Area(Integer.parseInt(j.getCitynumber()));
				ArrayList<addressDTO> c=new ArrayList<>(local.size());
				y.setDto(c);
				b.add(y);
				for(area k:local) {
					addressDTO z=new addressDTO();
					z.setName(k.getAreaname());
					z.setNum(Integer.parseInt(k.getAreanumber()));
					c.add(z);
				}
				
			}
			
		}
		
		return dto;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public  void add(String city,String area,String address,String name,String username,String userphone,String provinceNum,String cityNumber,int userId,String province) throws Exception {
		address a=new address();
		a.setAddress(address);
		a.setArea(area);
		a.setCity(city);
		a.setCitynumber(cityNumber);
		a.setName(name);
		a.setProvince(province);
		a.setProvincenum(provinceNum);
		a.setUserid(userId);
		a.setUsername(username);
		a.setUserphone(userphone);
		int rows=addressMap.insert(a);
		if(1!=rows) {
			throw new BigException("«Î‘Ÿ¥Œ±£¥Ê");
		}
	}
	
	public ArrayList<address> addressList(int userId){
		ArrayList<address> list=addressMap.selectByUserId(userId);
		if(null==list||list.isEmpty()) {
			return null;
		}
		else {
			return list;
		}
	}

}
