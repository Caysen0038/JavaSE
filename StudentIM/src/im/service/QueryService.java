package im.service;
import java.util.*;
import im.entity.*;
public interface QueryService extends Service{
	//��ѯҵ��ӿ�
	List<Record> query(String table,Field... field);
}
