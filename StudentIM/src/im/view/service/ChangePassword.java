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
				return "����ȷ��������!";
			}
		}
		if(!password[1].equals(password[2])){
			return "�����������벻ƥ��!";
		}
		
		if(!password[0].equals(User.getUser().getPassword())){
			return "ԭʼ�������!";
		}
		User admin=User.getUser();
		Record record=Query.getQuery().queryById(admin.getType(), admin.getId());
		record.getField("password").setValue(password[1]);
		if(Save.getSave().save(record)){
			return "�޸ĳɹ�";
		}else{
			return "�޸�ʧ�ܣ����������������";
		}
	}
	public static ChangePassword getQuery(){
		return change;
	}
}
