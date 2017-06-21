package im.service;
import java.util.*;
import im.entity.*;
public interface QueryService extends Service{
	//查询业务接口
	List<Record> query(String table,Field... field);
}
