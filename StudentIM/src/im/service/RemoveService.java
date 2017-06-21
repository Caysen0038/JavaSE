package im.service;
import im.entity.*;
public interface RemoveService extends Service{
	//删除数据业务接口
	boolean removce(String table,Field[] field);
}
