package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.domain.shopcart;
import com.domain.user;
import com.mapper.addressMapper;
import com.mapper.areaMapper;
import com.mapper.catalogMapper;
import com.mapper.cityMapper;
import com.mapper.commodityMapper;
import com.mapper.parentOrderMapper;
import com.mapper.provinceMapper;
import com.mapper.shopcartMapper;
import com.mapper.userMapper;

@RunWith(SpringRunner.class)
@ContextConfiguration({ "/spring-config.xml" })
public class test {
	
	@Autowired
	private catalogMapper cata;
	
	@Autowired
	private commodityMapper comm;
	
	@Autowired
	private shopcartMapper cart;
	
	@Autowired
	private provinceMapper province;
	
	@Autowired
	private cityMapper city;

	@Autowired
	private areaMapper area;
	
	@Autowired
	private addressMapper address;
	
	@Autowired
	private parentOrderMapper parent;
	
	@Autowired
	private userMapper use;
	
	@Test
	public void testSet() {
		/*shopcart record=new shopcart();
		record.setCommid(1);
		record.setCreateTime(new Date());
		record.setUserid(1111);
		record.setCommnum(22);
		record.setModifyTime(new Date());
		record.setStatus(1);*/
//		assertEquals(1, cart.updateNum(2, 11, 3,new Date()));
		/*assertNotNull(cart.select(11,3));*/
//		assertNotNull(cart.selectByUserId(11));
		/*assertNotNull( province.Province());*/
//		assertNotNull(city.City("110000"));
		/*assertNotNull(parent.ListByUserId(33));*/
		user u=new user();
		u.setUsername("4444");
		u.setPassword("777");
		assertEquals(1, use.addUser(u));
		
 	}

}
