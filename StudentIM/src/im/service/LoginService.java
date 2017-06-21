package im.service;
import im.entity.*;
public interface LoginService extends Service{
	//登录业务接口
	Record loginCheck();
	void setUser(String id,String password);
}
