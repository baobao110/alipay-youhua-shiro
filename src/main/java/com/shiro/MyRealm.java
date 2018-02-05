package com.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.service.userServer;

@Component("myRealm")
public class MyRealm extends AuthorizingRealm{
	
	  private static final String realm_name = "my realm";
	  
	  @Autowired
	  private userServer u;
	  
	  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
	        // 认证代码
	        UsernamePasswordToken upToken = (UsernamePasswordToken)token;
	        String username = upToken.getUsername();
	        String password = String.valueOf(upToken.getPassword());
			try {
				u.login(username, password);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

	        return new SimpleAuthenticationInfo(username, password, realm_name);
	    }

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	  
	  

}
//注意这里的程序的标签,这里的标签名需要和shiro的xml文件的内容相联系这里和定时器类似 ,同时注意doGetAuthenticationInfo方法是认证,而doGetAuthorizationInfo是授权 
