package im.view.service;

import java.util.List;

import im.entity.Field;
import im.entity.Record;
import im.service.proxy.QueryProxy;

public class QueryStudent {
	private QueryProxy query=null;
	private static QueryStudent student=new QueryStudent();
	private QueryStudent(){
		query=new QueryProxy();
	}
	public static QueryStudent getQuery(){
		return student;
	}
	public List<Record> queryAll(){
		List<Record> list=query.query("student");
		return list;
	}
	public List<Record> queryById(String id){
		Field field=new Field();
		field.setFieldName("id");
		field.setValue(id);
		List<Record> list=query.query("student", field);
		return list;
	}
	public List<Record> queryByClass(String id){
		Field field=new Field();
		field.setFieldName("class_id");
		field.setValue(id);
		List<Record> list=query.query("student", field);
		return list;
	}
	public List<Record> queryMajor(String id){
		Field field=new Field();
		field.setFieldName("major_id");
		field.setValue(id);
		List<Record> list=query.query("student", field);
		return list;
	}
}
