package im.view.service;

import java.util.List;

import im.entity.Field;
import im.entity.Record;
import im.service.proxy.QueryProxy;

public class QueryMajor {
	private static QueryMajor major=new QueryMajor();
	private QueryProxy query=null;
	private QueryMajor(){
		query=new QueryProxy();
	}
	public static QueryMajor getQuery(){
		return major;
	}
	public String[] queryAllName(){
		List<Record> record=query.query("major");
		String[] name=new String[record.size()];
		int i=0;
		for(Record temp:record){
			name[i++]=temp.getField("name").getValue();
		}
		return name;
	}
	public List<Record> queryAll(){
		List<Record> record=query.query("major");
		return record;
	}
	public Record queryById(String id){
		Field field=new Field();
		field.setFieldName("id");
		field.setValue(id);
		Record record=query.query("major", field).get(0);
		return record;
	}
	public List<Record> queryByName(String name){
		Field field=new Field();
		field.setFieldName("name");
		field.setValue(name);
		List<Record> list=query.query("major", field);
		return list;
	}
}
