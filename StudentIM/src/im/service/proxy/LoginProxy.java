package im.service.proxy;

import java.util.ArrayList;
import java.util.List;
import im.entity.*;
import im.service.LoginService;
import im.service.impl.LoginServiceImpl;

public class LoginProxy {
	private LoginService login=null;
	public LoginProxy(){
		login=new LoginServiceImpl();
	}
	public List<String> Login(String[] user){
		List<String> error=null;
		String id=user[0];
		String password=user[1];
		error=new ArrayList<>();
		if(null==id||"".equals(id)){
			error.add("���������ԱID!");
		}
		if(password==null||"".equals(password)){
			if(error.isEmpty()){
				error.add("���������Ա����!");
			}
		}
		if(!error.isEmpty()){
			return error;
		}
		login.setUser(id, password);
		Record record=login.loginCheck();
		if(record==null)
			error.add("ID�������������������!");
		if(record!=null){
			User.getUser().setInfo(record);;
		}
		return error;
	}
}
