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
          }
          
          return NotifyHolder.success(Integer.parseInt(orderId), Double.parseDouble(totalAmount));
    }//这里的异步通知是支付宝发送过来的,这里我们只需要接收参数,解析是否转账,如果转账成功就改变订单的状态为已经支付
    
    public String createPayRequest(parentOrder mother,double all,String[]x) throws Exception {
    	 AlipayClient alipayClient = new DefaultAlipayClient(ALI_PAY_GATE_WAY,
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
	       /* https://docs.open.alipay.com/270/105899/  金额精确到小数点后两位 
*/	        	
	        form = form.replace(".submit()", "");
	        System.out.println(">>>>>>>>form表单：" + form);
	        
	        
	        return form;
    }
    
    public NotifyHolder queryDrop(parentOrder order) {
    	 AlipayClient alipayClient = new DefaultAlipayClient(ALI_PAY_GATE_WAY,APP_ID,APP_MERCHANT_PRIVATE_KEY,"json","utf-8",ALIPAY_PUBLIC_KEY,"RSA2");

         AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();

         request.setBizContent("{" +
                 "\"out_trade_no\":\"" + order.getId() + "\"}");
         try {
			AlipayTradeQueryResponse response = alipayClient.execute(request);
			System.out.println("订单状态>>>>>>>>"+response.getTradeStatus());
			if(!response.isSuccess()) {
				throw new BigException("订单查询失败");
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
