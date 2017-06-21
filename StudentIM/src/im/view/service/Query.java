package im.view.service;


import im.entity.Field;
import im.entity.Record;
import im.service.proxy.*;
public class Query{
	
	private QueryProxy query=null;
	private static Query q=new Query();
	private Query(){
		query=new QueryProxy();
	}
	public Record queryById(String table,String id){
		System.out.println(table+" "+id);
		Field field=new Field();
		field.setFieldName("id");
		field.setTable(table);
		field.setValue(id);
		
		Record record=query.query(table, field).get(0);
		return record;
	}
	public static Query getQuery(){
		return q;
	}
}
