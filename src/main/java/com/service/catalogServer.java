package com.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.catalog;
import com.domain.commodity;
import com.dto.bookDTO;
import com.dto.catalogDTO;
import com.mapper.catalogMapper;
import com.mapper.commodityMapper;

@Service
public class catalogServer {
	
	@Autowired
	private catalogMapper catalog;
	
	@Autowired
	private commodityMapper commodity;
	
	public  ArrayList<catalogDTO> List() {
		 ArrayList<catalog> cata=catalog.List("-1");
		 ArrayList<catalogDTO>list=new ArrayList<>(cata.size());
		 for(catalog i:cata) {
			 ArrayList<catalog> log=catalog.List(i.getCatalogNum());
			 ArrayList<catalogDTO>flow=new ArrayList<>(log.size());
			 catalogDTO dto=new catalogDTO();
			 dto.setCatalogName(i.getCatalogName());
			 dto.setCatalogNum(i.getCatalogNum());
			 dto.setChildNode(i.getChildNode());
			 dto.setDto(flow);
			 list.add(dto);
			 for(catalog j:log) {
				 catalogDTO child=new catalogDTO();
				 child.setCatalogName(j.getCatalogName());
				 child.setCatalogNum(j.getCatalogNum());
				 child.setChildNode(j.getChildNode());
				 flow.add(child);
			 }
		 }
		 return list;
	}//注意这里的DTO的用法很特殊
	
	public ArrayList<catalog> ParentList(){
		 ArrayList<catalog> cata=catalog.List("-1");
		 return cata;
	}
		 
			
	

}
