package im.view.service;

import im.entity.User;
import im.entity.Record;

public class ChangePassword{
	private static ChangePassword change=new ChangePassword();
	private ChangePassword(){
		init();
	}
	private void init(){
		
	}
	public String change(String[] password){
		for(String temp:password){
			if(temp==null||temp.length()==0){
				return "请正确输入密码!";
			}
		}
		if(!password[1].equals(password[2])){
			return "两次密码输入不匹配!";
		}
		
		if(!password[0].equals(User.getUser().getPassword())){
			return "原始密码错误!";
		}
		User admin=User.getUser();
		Record record=Query.getQuery().queryById(admin.getType(), admin.getId());
		record.getField("password").setValue(password[1]);
		if(Save.getSave().save(record)){
			return "修改成功";
		}else{
			return "修改失败，请重启软件后重试";
		}
	}
	public static ChangePassword getQuery(){
		return change;
	}
}
