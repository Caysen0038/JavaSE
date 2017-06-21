package im.service;
import im.entity.*;
public interface InsertService extends Service {
	//插入数据业务接口
	int insert(Record[] entity);
}
