package com.service;

import java.text.DecimalFormat;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.catalog;
import com.domain.commodity;
import com.domain.commodityWithBLOBs;
import com.dto.bookDTO;
import com.mapper.catalogMapper;
import com.mapper.commodityMapper;

@Service
public class commodityServer {
	
	@Autowired
	private catalogMapper catalog;
	
	@Autowired
	private commodityMapper commodity;
	
	public  ArrayList<bookDTO> bookList(){
		DecimalFormat   df  = new DecimalFormat("######0.00");
		 ArrayList<catalog> cata=catalog.List("-1");
		 ArrayList<bookDTO> book=new ArrayList<>(cata.size());
		 for(catalog i:cata) {
			 bookDTO dto=new bookDTO();
			 dto.setCatalog(i.getCatalogName());
			 book.add(dto);
			 ArrayList<commodity> list=commodity.Flow(i.getCatalogNum());
			 ArrayList<bookDTO> flow=new ArrayList<>(list.size());
			 dto.setDto(flow);
			 for(commodity j:list) {
				 bookDTO child=new bookDTO();
				 child.setBookName(j.getCommName());
				 if(child.getBookName().length()>10) {
					 child.setBookName(child.getBookName().substring(0,10)+"......");//防止书名过长
				 }
				 child.setBookPrice(df.format(j.getCommPrice()));
				 child.setPictureName(j.getPictureName());
				 flow.add(child);
			 }
		 }
			 
		 return book;
	}
	
	
	public bookDTO lazyLoad(String catalogNum){
		 catalog x=catalog.getNum(catalogNum);
		 bookDTO flow=new bookDTO();
		 ArrayList<commodity> list=commodity.Flow(catalogNum);
		 flow.setCatalog(x.getCatalogName());
		 ArrayList< bookDTO> dto=new ArrayList<>(list.size());
		 flow.setDto(dto);
		 for(commodity i:list) {
			 bookDTO book=new bookDTO();
			 book.setBookName(i.getCommName());
			 book.setBookPrice(i.getCommPrice());
			 book.setPictureName(i.getPictureName());
			 book.setId(i.getId());
			 dto.add(book); 
		 }
		
		 return flow;
	}
	
	public commodityWithBLOBs getInformation(int id) {
		commodityWithBLOBs com=commodity.selectByPrimaryKey(id);
		return com;
	}
	


}
