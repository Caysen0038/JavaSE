package im.view.service;

import java.util.List;

import im.entity.Field;
import im.entity.Record;
import im.service.proxy.QueryProxy;

public class QueryClass {
	private QueryProxy query=null;
	private static QueryClass cla=new QueryClass();
	private QueryClass(){
		init();
	}
	private void init(){
		query=new QueryProxy();
	}
	public Record queryById(String id){
		Field field=new Field();
		field.setFieldName("id");
		field.setValue(id);
		List<Record> list=query.query("class", field);
		return list.get(0);
	}
	public static QueryClass getQuery(){
		return cla;
	}
	public String[] queryAllName(){
		List<Record> list=query.query("class");
		String[] name=new String[list.size()];
		int i=0;
		for(Record temp:list){
			name[i++]=temp.getField("name").getValue();
		}
		return name;
	}
	public List<Record> querybyName(String name){
		Field field=new Field();
		field.setFieldName("name");
		field.setValue(name);
		List<Record> list=query.query("class", field);
		return list;
	}
	public List<Record> queryByMajor(String name){
		String id=QueryMajor.getQuery().queryByName(name).get(0).getField("id").getValue();
		Field field=new Field();
		field.setFieldName("major_id");
		field.setValue(id);
		List<Record> list=query.query("class", field);
		return list;
	}
	public List<Record> queryAll(){
		List<Record> record=query.query("class");
		return record;
	}
}
