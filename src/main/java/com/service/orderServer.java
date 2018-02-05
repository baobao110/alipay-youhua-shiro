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
		mother.setRemarks("����");
		mother.setResource("���ﳵ");
		mother.setStatus((byte) 0);//����֮��������Ϊ0����Ϊ�����Ǵӽ���ҳ����֧��ҳ����ת��������Ϊ0��ʾδ֧��
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
				throw new BigException("��治��");
			}
			int row=commodity.descNum(car.getCommid(),car.getCommnum());
			if(1!=row) {
				throw new BigException("��������");
			}
		}
		
		mother.setTotal(String.valueOf(total));
		mother.setUserid(selected.getUserid());//����Ψһ��Ҫע����������selected.getUserid(),����û�и�Userid��ֵ��������ȴ���Ի�ȡidΪʲô?��Ϊ���������ݿ����õ������������Բ���Ҫ����,�����������ĵط� 
		mother.setUsername(selected.getUsername());
		mother.setUserphone(selected.getUserphone());
		int rows=parent.insert(mother);
		if(1!=rows) {
			throw new BigException("��������");
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
				throw new BigException("��������");
			}
			
		}
		BigDecimal   val  =   new   BigDecimal(mother.getTotal()); 
		double all= val.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();  
		
		 /* AlipayClient alipayClient = new DefaultAlipayClient(ALI_PAY_GATE_WAY,
	                APP_ID, APP_MERCHANT_PRIVATE_KEY, "JSON", "UTF-8", ALIPAY_PUBLIC_KEY, "RSA2"); //��ó�ʼ����AlipayClient

	        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//����API��Ӧ��request
	        alipayRequest.setReturnUrl(ALI_RETURN_URL);
	        alipayRequest.setNotifyUrl(ALI_NOTIFY_URL);//�ڹ������������û�����֪ͨ��ַ
	        alipayRequest.setBizContent("{" +
	                "    \"out_trade_no\":\"" + mother.getId() + "\"," +
	                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
	                "    \"total_amount\":\"" + String.valueOf(all) +"\"," +
	                "    \"subject\":\"dayuanit mall\"," +
	                "    \"body\":\"dayuanit\"," +
	                "  }");//���ҵ�����
	       
	        alipayRequest.setBizContent("{" +
	                "    \"out_trade_no\":\"" + mother.getId() + "\"," +
	                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
	                "    \"total_amount\":\"" + String.valueOf(0.01) +"\"," +
	                "    \"subject\":\"dayuanit mall\"," +
	                "    \"body\":\"dayuanit\"," +
	                "  }");//���ҵ�����
	        String form = "";
	        try {
	            form = alipayClient.pageExecute(alipayRequest).getBody(); //����SDK���ɱ�
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        https://docs.open.alipay.com/270/105899/  ��ȷ��С�������λ 
	        	
	        form = form.replace(".submit()", "");
	        System.out.println(">>>>>>>>form����" + form);
	        
	        for(int i=0;i<x.length;i++) {
				String[] s=x[i].trim().split("\\,");
				int id=Integer.parseInt(s[0]);
				int effect=shop.updateBycomId(id, Integer.parseInt(s[1]));
				if(1!=effect) {
					throw new BigException("��������");
				}
				
	        }
	        
	        return form;*/
		 return alipayHandler.createPayRequest(mother,all,x);
		
	}//�������������֧��ҳ��,���ݹ��ﳵ����δ0��ʾδ֧��,���δ֧���;Ͳ����ύ����֧����,�����Ͳ��Ṻ�ﳵ״̬�ı�,��ô��������󶼲���ִ�� 
	
    public void processNotify(Map<String, String> params) throws Exception {

      /*  boolean signVerified = false;
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, "UTF-8", "RSA2"); //����SDK��֤ǩ��
        } catch(AlipayApiException ae) {
            throw new BigException("֧�����첽֪ͨ��ǩʧ��");
        }

        if (!signVerified) {
            throw new BigException("֧�����첽֪ͨ��ǩʧ��");
        }

        //���Ҷ���
        String orderId = params.get("out_trade_no");
        String totalAmount = params.get("total_amount");
        String appId = params.get("app_id");
        String tradeStatus = params.get("trade_status");

        if (!"TRADE_SUCCESS".equals(tradeStatus) && !"TRADE_FINISHED".equals(tradeStatus)) {
            throw new BigException("֧��������֧��ʧ��");
        }

        if (!this.APP_ID.equals(appId)) {
            throw new BigException("֧�����첽֪ͨAPPID�����");
        }*/
		
		NotifyHolder notifyHolder = alipayHandler.processNotify(params);
		
		if (!notifyHolder.isPaySuccess()) {
	            throw new BigException("֧�����첽֪ͨ��ǩʧ��");
	       }

        parentOrder order = parent.getOrderLock(Integer.valueOf(notifyHolder.getOrderId()));//�����ñ�������������ֹ���� 
        if (null == order) {
            throw new BigException("����������");
        }

        if (notifyHolder.getTotalAmount() != Double.parseDouble(order.getTotal())) {
            throw new BigException("���������");
        }

        if (order.getStatus() != 0) {
            throw new BigException("�������ڴ�����״̬");
        }

        //�޸Ķ���״̬
        int rows = parent.updateStatus(order.getId(), OrderStatusEnum.un_received.getK());//������֧��ҳ������ҳ���ʱ�Ѿ�֧��,����֧���������״̬ 
        if (1 != rows) {
            throw new  BigException("����״̬�޸�ʧ��");
        }
        
        
      /*  for(int i=0;i<x.length;i++) {
			String[] s=x[i].trim().split("\\,");
			int id=Integer.parseInt(s[0]);
			int effect=shop.updateBycomId(id, Integer.parseInt(s[1]));
			if(1!=effect) {
				throw new BigException("��������");
			}
			
        }*/
     
    }
    //ע����������������֧�������������첽�����ʵ����
	
	   
    public ArrayList<orderDTO> order(int userId,String cart) throws Exception{
    	String[]x=cart.trim().split("\\;");
		  for(int i=0;i<x.length;i++) {
				String[] s=x[i].trim().split("\\,");
				int id=Integer.parseInt(s[0]);
				int effect=shop.updateBycomId(id, Integer.parseInt(s[1]));
				if(1!=effect) {
					throw new BigException("��������");
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
    }//�����������˵���û��Ѿ�֧��������ʱ�˻����Ӧ�ñ仯,�����Ƿ�֧���ɹ�,ֻ��ͨ����ʱ��֪��,
    
    public void addStore(Date date) throws Exception {
    	ArrayList<parentOrder> order=parent.ListBydate(date);
    	for(parentOrder i:order) {
    		ArrayList<childOrder> x=child.childOrder(i.getId());
    		for(childOrder j:x) {
    			int rows=commodity.aescNum(j.getComid(), j.getCommnum());
    			if(1!=rows) {
    				throw new BigException("������ʧ��");
    			}
    		}
    	}
    	
    }
    @Transactional(rollbackFor = Exception.class)
    public void drop(int id,Date date) throws Exception {
    	parentOrder order=parent.selectByPrimaryKey(id);
    	NotifyHolder notifyHolder =alipayHandler.queryDrop(order);
    	if(!notifyHolder.isPaySuccess()) {
    		throw new BigException("����δ֧��");
    	}
    	if(notifyHolder.getTotalAmount()!=Double.parseDouble(order.getTotal())){
    		throw new BigException("����");
    	}
    	
    	if (order.getStatus() != OrderStatusEnum.un_pay.getK()) {
            if (order.getStatus() == OrderStatusEnum.cancel.getK()) {
                //TODO ���Ǯ��ô�죿
                //������ⶩ���ŵ��˿���л����˿����ɶ�ʱ��ȥ����Ӧ���˿��
                //�����������£������ⶩ���־û������ݿ��У�����Ӧ�Ĺ�����Ա���䴦��
            } else {
                throw new BigException("�������ڴ�����״̬");
            }
        }
    	
    	//�޸Ķ���״̬
    	int rows=parent.drop(id,date);
    	if(1!=rows) {
    		throw new BigException("����״̬�޸�ʧ��");
    	}
    }
	//������������ڵ���ʱ�Ķ�ʱ��,��queryDrop���﷢�������ȡ������ѯ��Ϣ,֮����ݰ���Ĳ�ѯ����Ƿ��ڰ����Ǳ�ת�˵�λȷ���Ƿ�order����ض���״̬��Ϊ�Ѹ���,��������ǱߵĲ�ѯ����ڹ涨ʱ��������Ϊδ�����Ǳ�order��״ֻ̬��Ϊδ����,����ֻ��������

}
