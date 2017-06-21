package im.view.service;

import java.util.List;

import im.entity.Field;
import im.entity.Record;
import im.service.proxy.QueryProxy;

public class QueryName{
	
	private QueryProxy query=null;
	private static QueryName name=new QueryName();
	private QueryName(){
		init();
	}
	private void init(){
		query=new QueryProxy();
	}
	public String query(String table,String id){
		Field field=new Field();
		field.setFieldName("id");
		field.setValue(id);
		List<Record> list=query.query(table, field);
		if(list.isEmpty()){
			return null;
		}else{
			return list.get(0).getField("name").getValue();
		}
	}
	public static QueryName getQuery(){
		return name;
	}
}
