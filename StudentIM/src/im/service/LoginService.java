package im.service;
import im.entity.*;
public interface LoginService extends Service{
	//��¼ҵ��ӿ�
	Record loginCheck();
	void setUser(String id,String password);
}
