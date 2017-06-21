package im.view.service;

import java.util.List;

import im.entity.Field;
import im.entity.Record;
import im.service.proxy.QueryProxy;

public class QueryPart {
	private static QueryPart part=new QueryPart();
	private QueryProxy query=null;
	private QueryPart(){
		query=new QueryProxy();
	}
	public static QueryPart getQuery(){
		return part;
	}
	public String[] queryAllName(){
		List<Record> record=query.query("part");
		String[] name=new String[record.size()];
		int i=0;
		for(Record temp:record){
			name[i++]=temp.getField("name").getValue();
		}
		return name;
	}
	public List<Record> queryAll(){
		List<Record> record=query.query("part");
		return record;
	}
	public Record queryById(String id){
		Field field=new Field();
		field.setFieldName("id");
		field.setValue(id);
		Record record=query.query("part", field).get(0);
		return record;
	}
	public List<Record> queryByName(String name){
		Field field=new Field();
		field.setFieldName("name");
		field.setValue(name);
		List<Record> list=query.query("part", field);
		return list;
	}
	public List<Record> queryByTeacherId(String id){
		Field field=new Field();
		field.setFieldName("teacher_id");
		field.setValue(id);
		List<Record> list=query.query("part", field);
		return list;
	}
}
