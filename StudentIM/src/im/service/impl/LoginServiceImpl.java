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
		 * 因为此类由代理调用，所以初始化安全，不需要将默认构造设置为private
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
			//通过id进行验证
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
		 * admin=null 即没有查询到该ID，登录失败
		 * field(4)为密码，如果不相等。也为登录失败
		 */
		if(record==null||!record.getField("password").getValue().equals(this.password)){
			return null;
		}
		//缓存上次登录的时间
		String time=record.getField("last_time").getValue();
		String date=TimeStamp.getDateTime();
		//设置当前时间到记录中的lasttime字段(mean==上次登录时间)
		record.setValueByFieldName("last_time",date);
		try{
			//更新lasttime
			System.out.println(DAOFactory.getDAO("record").doUpdate(record));
		}catch(Exception e){
			e.printStackTrace();
		}
		//再将缓存的lasttime存入记录中
		record.setValueByFieldName("last_time",time);
		return record;
	}
}
