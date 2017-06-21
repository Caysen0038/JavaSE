package im.service.proxy;

import im.entity.Field;
import im.entity.Record;
import im.service.*;
import im.service.impl.*;
import java.util.*;

public class QueryProxy implements QueryService{
	private QueryServiceImpl service=null;
	
	public QueryProxy(){ 
		
	}
	@Override
	public List<Record> query(String tableName,Field... field){
		service=new QueryServiceImpl();
		List<Record> list=service.query(tableName, field);
		service=null;
		return list;
	}
}
