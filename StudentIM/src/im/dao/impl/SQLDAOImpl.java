package im.dao.impl;
import im.dao.*;
import im.dbc.DBC;
import im.entity.Field;
import im.entity.Record;

import java.sql.*;
import java.util.*;
public class SQLDAOImpl extends DAOAdapter{
	/**
	 * ����ΪһЩ���ӵ����ݲ���ʵ��
	 * ֱ��ִ��SQL��䣬��ִ����������
	 */
	private Connection con=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private ResultSetMetaData rsm=null;
	@Deprecated
	public SQLDAOImpl(){
		
	}
	public SQLDAOImpl(DBC db){
		setDBC(db);
	}
	@Override
	public void setDBC(DBC db){
		try{
			this.con=db.getConnection();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Override
	public List<Record> SQL(String sql) throws Exception{
		/**
		 * @param sql ��Ҫִ�е�SQL��䣬��Ч�Լ�����ⲿʵ��
		 */
		ps=con.prepareStatement(sql);
		sql=sql.toLowerCase();
		/**
		 * ������ǲ�ѯ��䣬����Ҫ���ؽ����
		 */
		if (!(sql.indexOf("select")>-1)){
			ps.executeUpdate();
			return null;
		}
		rs=ps.executeQuery();
		rsm=rs.getMetaData();
		int n=rsm.getColumnCount();
		List<Record> list=new ArrayList<>();
		while(rs.next()){
			Record record=new Record(n);
			record.setTable(rsm.getTableName(1));
			Field field=null;
			for(int i=1,j=record.getSize();i<=j;i++){
				field=new Field();
				field.setValue(rs.getString(i));
				field.setFieldName(rsm.getColumnName(i));
				record.setField(field,i-1);
			}
			list.add(record);
		}
		//�������
		con=null;
		ps.close();
		rs.close();
		ps=null;
		rs=null;
		rsm=null;
		return list;
	}
}
