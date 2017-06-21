package im.service.impl;

import im.dao.factory.*;
import im.entity.*;
import im.service.*;
import im.util.TimeStamp;

public class LoginServiceImpl implements LoginService{
	private String id=null;
	private String password=null;
	private Record record=null;
	private String[] userType={"student","admin","teacher"};
	public LoginServiceImpl(){
		/**
		 * ��Ϊ�����ɴ�����ã����Գ�ʼ����ȫ������Ҫ��Ĭ�Ϲ�������Ϊprivate
		 */
	}
	public LoginServiceImpl(String id,String password){
		setUser(id,password);
	}
	@Override
	public void setUser(String id,String password){
		this.id=id;
		this.password=password;
	}
	@Override
	public Record loginCheck(){
		try{
			//ͨ��id������֤
			for(int i=0,n=userType.length;i<n;i++){
				record=DAOFactory.getDAO("record").findById(userType[i],id);
				if(record!=null){
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		/**
		 * admin=null ��û�в�ѯ����ID����¼ʧ��
		 * field(4)Ϊ���룬�������ȡ�ҲΪ��¼ʧ��
		 */
		if(record==null||!record.getField("password").getValue().equals(this.password)){
			return null;
		}
		//�����ϴε�¼��ʱ��
		String time=record.getField("last_time").getValue();
		String date=TimeStamp.getDateTime();
		//���õ�ǰʱ�䵽��¼�е�lasttime�ֶ�(mean==�ϴε�¼ʱ��)
		record.setValueByFieldName("last_time",date);
		try{
			//����lasttime
			System.out.println(DAOFactory.getDAO("record").doUpdate(record));
		}catch(Exception e){
			e.printStackTrace();
		}
		//�ٽ������lasttime�����¼��
		record.setValueByFieldName("last_time",time);
		return record;
	}
}
