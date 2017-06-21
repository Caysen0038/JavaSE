package im.view.service;

import java.util.List;

import im.entity.Field;
import im.entity.Record;
import im.service.proxy.QueryProxy;

public class QueryTeacher {
	private QueryProxy query=null;
	private static QueryTeacher teacher=new QueryTeacher();
	private QueryTeacher(){
		query=new QueryProxy();
	}
	public static QueryTeacher getQuery(){
		return teacher;
	}
	public List<Record> queryAll(){
		List<Record> list=query.query("teacher");
		return list;
	}
	public List<Record> queryById(String id){
		Field field=new Field();
		field.setFieldName("id");
		field.setValue(id);
		List<Record> list=query.query("teacher", field);
		return list;
	}
	public List<Record> queryByPart(String id){
		Field field=new Field();
		field.setFieldName("part_id");
		field.setValue(id);
		List<Record> list=query.query("teacher", field);
		return list;
	}
	public List<Record> queryByKind(String kind){
		Field field=new Field();
		field.setFieldName("kind");
		field.setValue(kind);
		List<Record> list=query.query("teacher", field);
		return list;
	}
}
