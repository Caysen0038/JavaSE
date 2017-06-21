package im.view.service;

import java.util.List;

import im.entity.Field;
import im.entity.Record;
import im.service.proxy.QueryProxy;

public class QueryScore{
	
	private QueryProxy query=null;
	private static QueryScore score=new QueryScore();
	public QueryScore(){
		init();
	}
	private void init(){
		query=new QueryProxy();
	}
	public List<Record> queryByStudentId(String id){
		Field field=new Field();
		field.setFieldName("student_id");
		field.setValue(id);
		List<Record> list=query.query("score", field);
		return list;
	}
	public static QueryScore getQuery(){
		return score;
	}
	public List<Record> queryScore(Field...field){
		List<Record> list=query.query("score", field);
		return list;
	}
}
