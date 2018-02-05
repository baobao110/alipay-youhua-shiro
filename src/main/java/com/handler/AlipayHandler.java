package com.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.domain.parentOrder;
import com.exception.BigException;
import com.mapper.shopcartMapper;

@Component
public class AlipayHandler {
	

    @Value("${ali_app_id}")
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
    private String ALI_NOTIFY_URL;
    
    private static final String TRADE_FINISHED = "TRADE_FINISHED";

    private static final String TRADE_SUCCESS = "TRADE_SUCCESS";

    private static final String trade_status = "trade_status";
    private static final String app_id_param = "app_id";
    private static final String out_trade_no = "out_trade_no";
    private static final String total_amount = "total_amount";
    
	@Autowired
	private shopcartMapper shop;
    
    public NotifyHolder processNotify(Map<String, String> params) throws Exception {
    	  boolean signVerified = false;
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
          }
          
          return NotifyHolder.success(Integer.parseInt(orderId), Double.parseDouble(totalAmount));
    }//������첽֪ͨ��֧�������͹�����,��������ֻ��Ҫ���ղ���,�����Ƿ�ת��,���ת�˳ɹ��͸ı䶩����״̬Ϊ�Ѿ�֧��
    
    public String createPayRequest(parentOrder mother,double all,String[]x) throws Exception {
    	 AlipayClient alipayClient = new DefaultAlipayClient(ALI_PAY_GATE_WAY,
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
	       /* https://docs.open.alipay.com/270/105899/  ��ȷ��С�������λ 
*/	        	
	        form = form.replace(".submit()", "");
	        System.out.println(">>>>>>>>form����" + form);
	        
	        
	        return form;
    }
    
    public NotifyHolder queryDrop(parentOrder order) {
    	 AlipayClient alipayClient = new DefaultAlipayClient(ALI_PAY_GATE_WAY,APP_ID,APP_MERCHANT_PRIVATE_KEY,"json","utf-8",ALIPAY_PUBLIC_KEY,"RSA2");

         AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();

         request.setBizContent("{" +
                 "\"out_trade_no\":\"" + order.getId() + "\"}");
         try {
			AlipayTradeQueryResponse response = alipayClient.execute(request);
			System.out.println("����״̬>>>>>>>>"+response.getTradeStatus());
			if(!response.isSuccess()) {
				throw new BigException("������ѯʧ��");
			}
			 if (!TRADE_SUCCESS.equals(response.getTradeStatus()) && !TRADE_FINISHED.equals(response.getTradeStatus())) {
	                return NotifyHolder.failed();
	            }
			 return NotifyHolder.success(order.getId(), Double.parseDouble(response.getTotalAmount()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return NotifyHolder.failed();
		}

    }

}
