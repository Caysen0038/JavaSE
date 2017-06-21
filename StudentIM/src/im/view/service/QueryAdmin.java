package im.view.service;

import java.util.List;

import im.entity.Field;
import im.entity.Record;
import im.service.proxy.QueryProxy;

public class QueryAdmin {
	private static QueryAdmin service=new QueryAdmin();
	private QueryProxy query=null;
	private QueryAdmin(){
		query=new QueryProxy();
	}
	public static QueryAdmin getQuery(){
		return service;
	}
	public List<Record> queryAll(){
		List<Record> record=query.query("admin");
		return record;
	}
	public Record queryById(String id){
		Field field=new Field();
		field.setFieldName("id");
		field.setValue(id);
		return query.query("admin", field).get(0);
	}
}
