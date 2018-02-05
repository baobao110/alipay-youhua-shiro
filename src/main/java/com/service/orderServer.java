package com.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.domain.address;
import com.domain.childOrder;
import com.domain.parentOrder;
import com.domain.shopcart;
import com.dto.InforDTO;
import com.dto.orderDTO;
import com.enums.OrderStatusEnum;
import com.exception.BigException;
import com.handler.AlipayHandler;
import com.handler.NotifyHolder;
import com.mapper.addressMapper;
import com.mapper.childOrderMapper;
import com.mapper.commodityMapper;
import com.mapper.parentOrderMapper;
import com.mapper.shopcartMapper;

@Service
public class orderServer {
	
	@Autowired
	private parentOrderMapper parent;
	
	@Autowired
	private childOrderMapper child;
	
	@Autowired
	private addressMapper map;
	

	@Autowired
	private shopcartMapper shop;
	
	@Autowired
	private commodityMapper commodity;
	
	 @Autowired
	 private AlipayHandler alipayHandler;

	
	  /*	@Value("${ali_app_id}")
	    private String APP_ID;

	    @Value("${ali_merchant_private_ley}")
	    private String APP_MERCHANT_PRIVATE_KEY;

	    @Value("${ali_public_key}")
	    private String ALIPAY_PUBLIC_KEY;

	    @Value("${ali_pay_url}")
	    private String ALI_PAY_GATE_WAY;

	    @Value("${ali_return_url}")
	    private String ALI_RETURN_URL;

	    @Value("${ali_notify_url}")
	    private String ALI_NOTIFY_URL;*/
	
	@Transactional(rollbackFor = Exception.class)
	public String  pay(int addressId,int pay,String cart) throws Exception {
		System.out.println("==============="+addressId+"]]]]]]]]]]]]]{{{{{"+pay+"\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
		parentOrder mother=new parentOrder();
		address selected=map.selectByPrimaryKey(addressId);
		System.out.println("address"+"]]]]]]]]]]"+selected);
		mother.setAreaname(selected.getArea());
		mother.setCityname(selected.getCity());
		mother.setCreatetime(new Date());
		mother.setPay(pay);
		mother.setProvincename(selected.getProvince());
		mother.setRemarks("付款");
		mother.setResource("购物车");
		mother.setStatus((byte) 0);//这里之所以设置为0是因为这里是从结算页面向支付页面跳转所以设置为0表示未支付
		String[]x=cart.trim().split("\\;");
		double total=0;
		for(int i=0;i<x.length;i++) {
			String[] s=x[i].trim().split("\\,");
			int id=Integer.parseInt(s[0]);
			System.out.println("id"+"xxxxxxxxxxxxxxxx"+id);
			shopcart car=shop.selectByLock(id);
			com.domain.commodity com=commodity.selectByPrimaryKey(car.getCommid());
			System.out.println("commodity"+"||||||||||||||||||"+com);
			double sum=car.getCommnum()*Double.parseDouble(com.getCommPrice());
			total+=sum;
			System.out.println("--------------------------"+s[1]);
			com.domain.commodity comm=commodity.selectByLock(car.getCommid());
			System.out.println("}}}}}}}}}}}}}}}}}"+car.getCommnum()+"+++++++++++++++++++++++++++++++++++++++");
			if(car.getCommnum()>comm.getStore()) {
				throw new BigException("库存不足");
			}
			int row=commodity.descNum(car.getCommid(),car.getCommnum());
			if(1!=row) {
				throw new BigException("操作有误");
			}
		}
		
		mother.setTotal(String.valueOf(total));
		mother.setUserid(selected.getUserid());//这里唯一需要注意的是这里的selected.getUserid(),上面没有给Userid赋值但是这里却可以获取id为什么?因为在这里数据库设置的是资增长所以不需要设置,这里就是巧妙的地方 
		mother.setUsername(selected.getUsername());
		mother.setUserphone(selected.getUserphone());
		int rows=parent.insert(mother);
		if(1!=rows) {
			throw new BigException("操作有误");
		}
		System.out.println("mother+=++"+mother.getId());
		
		for(int i=0;i<x.length;i++) {
			String[] s=x[i].trim().split("\\,");
			int id=Integer.parseInt(s[0]);
			shopcart car=shop.selectBycommId(id);
			com.domain.commodity com=commodity.selectByPrimaryKey(car.getCommid());
			childOrder order=new childOrder();
			order.setComid(com.getId());
			shopcart t=shop.selectBycommId(id);
			order.setCommnum(t.getCommnum());
			order.setComname(com.getCommName());
			order.setComprice(com.getCommPrice());
			order.setOrderid(mother.getId());
			String a=String.valueOf(t.getCommnum()).trim();
			/*String b=com.getCommPrice().trim();*/
			 BigDecimal num1 = new BigDecimal(a);
		     BigDecimal num2 = new BigDecimal(com.getCommPrice().trim());
			String value=num1.multiply(num2).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			order.setTotal(value.trim());
			int v= child.insert(order);
			if(1!=v) {
				throw new BigException("操作有误");
			}
			
		}
		BigDecimal   val  =   new   BigDecimal(mother.getTotal()); 
		double all= val.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();  
		
		 /* AlipayClient alipayClient = new DefaultAlipayClient(ALI_PAY_GATE_WAY,
	                APP_ID, APP_MERCHANT_PRIVATE_KEY, "JSON", "UTF-8", ALIPAY_PUBLIC_KEY, "RSA2"); //获得初始化的AlipayClient

	        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
	        alipayRequest.setReturnUrl(ALI_RETURN_URL);
	        alipayRequest.setNotifyUrl(ALI_NOTIFY_URL);//在公共参数中设置回跳和通知地址
	        alipayRequest.setBizContent("{" +
	                "    \"out_trade_no\":\"" + mother.getId() + "\"," +
	                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
	                "    \"total_amount\":\"" + String.valueOf(all) +"\"," +
	                "    \"subject\":\"dayuanit mall\"," +
	                "    \"body\":\"dayuanit\"," +
	                "  }");//填充业务参数
	       
	        alipayRequest.setBizContent("{" +
	                "    \"out_trade_no\":\"" + mother.getId() + "\"," +
	                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
	                "    \"total_amount\":\"" + String.valueOf(0.01) +"\"," +
	                "    \"subject\":\"dayuanit mall\"," +
	                "    \"body\":\"dayuanit\"," +
	                "  }");//填充业务参数
	        String form = "";
	        try {
	            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        https://docs.open.alipay.com/270/105899/  金额精确到小数点后两位 
	        	
	        form = form.replace(".submit()", "");
	        System.out.println(">>>>>>>>form表单：" + form);
	        
	        for(int i=0;i<x.length;i++) {
				String[] s=x[i].trim().split("\\,");
				int id=Integer.parseInt(s[0]);
				int effect=shop.updateBycomId(id, Integer.parseInt(s[1]));
				if(1!=effect) {
					throw new BigException("操作有误");
				}
				
	        }
	        
	        return form;*/
		 return alipayHandler.createPayRequest(mother,all,x);
		
	}//这个方法是请求到支付页面,根据购物车设置未0表示未支付,如果未支付就就不会提交表单给支付宝,这样就不会购物车状态改变,那么下面的请求都不会执行 
	
    public void processNotify(Map<String, String> params) throws Exception {

      /*  boolean signVerified = false;
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, "UTF-8", "RSA2"); //调用SDK验证签名
        } catch(AlipayApiException ae) {
            throw new BigException("支付宝异步通知验签失败");
        }

        if (!signVerified) {
            throw new BigException("支付宝异步通知验签失败");
        }

        //查找订单
        String orderId = params.get("out_trade_no");
        String totalAmount = params.get("total_amount");
        String appId = params.get("app_id");
        String tradeStatus = params.get("trade_status");

        if (!"TRADE_SUCCESS".equals(tradeStatus) && !"TRADE_FINISHED".equals(tradeStatus)) {
            throw new BigException("支付宝订单支付失败");
        }

        if (!this.APP_ID.equals(appId)) {
            throw new BigException("支付宝异步通知APPID不相等");
        }*/
		
		NotifyHolder notifyHolder = alipayHandler.processNotify(params);
		
		if (!notifyHolder.isPaySuccess()) {
	            throw new BigException("支付宝异步通知验签失败");
	       }

        parentOrder order = parent.getOrderLock(Integer.valueOf(notifyHolder.getOrderId()));//这里用悲观锁金额操作防止并发 
        if (null == order) {
            throw new BigException("订单不存在");
        }

        if (notifyHolder.getTotalAmount() != Double.parseDouble(order.getTotal())) {
            throw new BigException("订单金额不相等");
        }

        if (order.getStatus() != 0) {
            throw new BigException("订单不在待付款状态");
        }

        //修改订单状态
        int rows = parent.updateStatus(order.getId(), OrderStatusEnum.un_received.getK());//这里是支付页面向结果页面此时已经支付,根据支付结果更改状态 
        if (1 != rows) {
            throw new  BigException("订单状态修改失败");
        }
        
        
      /*  for(int i=0;i<x.length;i++) {
			String[] s=x[i].trim().split("\\,");
			int id=Integer.parseInt(s[0]);
			int effect=shop.updateBycomId(id, Integer.parseInt(s[1]));
			if(1!=effect) {
				throw new BigException("操作有误");
			}
			
        }*/
     
    }
    //注意上面的这个方法是支付宝发过来的异步请求核实数据
	
	   
    public ArrayList<orderDTO> order(int userId,String cart) throws Exception{
    	String[]x=cart.trim().split("\\;");
		  for(int i=0;i<x.length;i++) {
				String[] s=x[i].trim().split("\\,");
				int id=Integer.parseInt(s[0]);
				int effect=shop.updateBycomId(id, Integer.parseInt(s[1]));
				if(1!=effect) {
					throw new BigException("操作有误");
				}
				
	        }
    	ArrayList<parentOrder> order=parent.ListByUserId(userId);
    	ArrayList<orderDTO> dto=new ArrayList<>(order.size());
    	for(parentOrder i:order) {
    		orderDTO dto1=new orderDTO();
    		dto.add(dto1);
    		dto1.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(i.getModifytime()));
    		dto1.setOrderId(i.getId());
    		dto1.setStatusInf(OrderStatusEnum.getStatus(i.getStatus()).getV());
    		dto1.setStatus(i.getStatus());
    		dto1.setTotal(i.getTotal());
    		ArrayList<childOrder> childto=child.childOrder(i.getId());
    		ArrayList<InforDTO> dto2=new ArrayList<>(childto.size());
    		dto1.setDto(dto2);
    		for(childOrder j:childto) {
    			InforDTO dto3=new InforDTO();
    			dto2.add(dto3);
    			dto3.setNum(j.getCommnum());
    			com.domain.commodity com=commodity.selectByPrimaryKey(j.getComid());
    			dto3.setBookName(j.getComname());
    			dto3.setPictureURL(com.getPictureName());
    			dto3.setPrice(Double.parseDouble(j.getComprice()));
    		}
    		
    	}
    	return dto;
    }//进入这个方法说明用户已经支付所以这时账户金额应该变化,至于是否支付成功,只能通过定时器知道,
    
    public void addStore(Date date) throws Exception {
    	ArrayList<parentOrder> order=parent.ListBydate(date);
    	for(parentOrder i:order) {
    		ArrayList<childOrder> x=child.childOrder(i.getId());
    		for(childOrder j:x) {
    			int rows=commodity.aescNum(j.getComid(), j.getCommnum());
    			if(1!=rows) {
    				throw new BigException("存货添加失败");
    			}
    		}
    	}
    	
    }
    @Transactional(rollbackFor = Exception.class)
    public void drop(int id,Date date) throws Exception {
    	parentOrder order=parent.selectByPrimaryKey(id);
    	NotifyHolder notifyHolder =alipayHandler.queryDrop(order);
    	if(!notifyHolder.isPaySuccess()) {
    		throw new BigException("订单未支付");
    	}
    	if(notifyHolder.getTotalAmount()!=Double.parseDouble(order.getTotal())){
    		throw new BigException("金额不对");
    	}
    	
    	if (order.getStatus() != OrderStatusEnum.un_pay.getK()) {
            if (order.getStatus() == OrderStatusEnum.cancel.getK()) {
                //TODO 这笔钱怎么办？
                //这笔问题订单放到退款队列或者退款表里，由定时器去做相应的退款处理
                //或者是走线下，将问题订单持久化到数据库中，有相应的工作人员对其处理。
            } else {
                throw new BigException("订单不在待付款状态");
            }
        }
    	
    	//修改订单状态
    	int rows=parent.drop(id,date);
    	if(1!=rows) {
    		throw new BigException("订单状态修改失败");
    	}
    }
	//这个方法是用于掉单时的定时器,用queryDrop向阿里发出请求获取订单查询信息,之后根据阿里的查询结果是否在阿里那边转账到位确定是否将order的相关订单状态改为已付款,如果阿里那边的查询结果在规定时间内依旧为未付款那边order的状态只能为未付款,这样只能走线下

}
