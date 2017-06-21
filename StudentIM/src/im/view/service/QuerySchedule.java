package im.view.service;

import java.util.List;

import im.entity.Field;
import im.entity.Record;
import im.service.proxy.QueryProxy;

public class QuerySchedule{
	private QueryProxy query=null;
	private static QuerySchedule schedule=new QuerySchedule();
	private QuerySchedule(){
		query=new QueryProxy();
	}
	public static QuerySchedule getCourse(){
		return schedule;
	}
	public List<Record> query(String id){
		List<Record> list=null;
		Field field=new Field();
		field.setFieldName("student_id");
		field.setValue(id);
		field.setTable("schedule");
		list=query.query(field.getTable(), field);
		return list;
	}
}
