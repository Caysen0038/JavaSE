package im.view.service;

import java.util.*;
import im.service.proxy.*;
public class Login{
	/**
	 * 显示层的业务控制，与业务层交互
	 */
	private List<String> error=null;
	private LoginProxy loginProxy=null;
	private static Login login=new Login();
	private Login(){
		
	}
	public List<String> LoginCheck(String[] user){
		
		try{
			Thread.sleep(1000);
		}catch(Exception e){
			
		}
		loginProxy=new LoginProxy();
		error=loginProxy.Login(user);
		loginProxy=null;
		return error;
	}
	public static Login getLogin(){
		return login;
	}
}
