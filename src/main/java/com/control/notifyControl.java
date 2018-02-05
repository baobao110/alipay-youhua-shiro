package com.control;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.orderServer;

@Controller
@RequestMapping("/notify")
public class notifyControl {
	
	@Autowired
	private orderServer order;
	
	  @RequestMapping("/alinotify")
	    public void alinotify(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
	        System.out.println(">>>>>>>>>> alipay notify");

	        Map<String,String> params = new HashMap<String,String>();

	        Map<String,String[]> requestParams = request.getParameterMap();
	        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
	            String name = (String) iter.next();
	            String[] values = (String[]) requestParams.get(name);
	            String valueStr = "";
	            for (int i = 0; i < values.length; i++) {
	                valueStr = (i == values.length - 1) ? valueStr + values[i]
	                        : valueStr + values[i] + ",";
	            }
	            //乱码解决，这段代码在出现乱码时使用
	            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
	            params.put(name, valueStr);
	        }
	        String cart=(String) session.getAttribute("cart");
	        try {
				order.processNotify(params);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

	        try (OutputStream os = response.getOutputStream()) {
	            os.write("success".getBytes());
	            os.flush();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	    }
}
	  
	  	
