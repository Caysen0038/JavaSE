package im.dao.impl;
import im.dao.*;
import im.dbc.DBC;
import im.entity.*;
import im.util.Config;

import java.sql.*;
import java.util.*;
public class RecordDAOImpl extends DAOAdapter{
	/**
	 * ����Ϊ��ɾ���ʵ���࣬�����������������
	 * @param con ���ݿ����ӣ�DBC�ɴ����࣬ʵ���ౣ��Connection
	 * @param rsm ��÷��ؽ���������ݱ���Ϣ
	 */
	private Connection con=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private ResultSetMetaData rsm=null;
	@Deprecated
	public RecordDAOImpl(){
		
	}
	public RecordDAOImpl(DBC db){
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
	public boolean doInsert(Record record) throws Exception {
		//���Ҫִ�е����ݱ�����
		String table=record.getTable();
		//��ü�¼�е��ֶ���Ϣ
		Field[] field=record.getFields();
		StringBuilder sb=new StringBuilder("insert into ");
		sb.append(table).append(" values(");
		/**
		 * �����ֶ���Ϣƴ��SQL���
		 * ��Ϊ�漰����ȷ�����ַ���ƴ�ӣ����������ȿ���StringBuilder�����̰߳�ȫ��StringBuffer
		 * ƴ���ַ�����������ֱ��ʹ��String��"+";
		 * 
		 * ѭ�����漰�ظ��������ó��ȵȲ���ʱ����ѭ����ʼ��ʱ����һ��������ʱ�洢���ó���
		 * ����ÿ��ѭ����������㣬��������
		 */
		for (int i=0,n=field.length;i<n;i++){
			sb.append("?");
			if (i<n-1){
				sb.append(",");
			}
		}
		sb.append(")");
		System.out.println(sb.toString());
		ps=con.prepareStatement(sb.toString());
		/**
		 * forѭ������PreparedStatementִ�в���
		 */
		for (int i=1;i<=field.length;i++){
			ps.setString(i,field[i-1].getValue());
		}
		boolean success=false;
		//ȡ��ִ�н��
		if(ps.executeUpdate()>0){
			success=true;
		}
		/**
		 * @method destory() ������ж�ָ��
		 */
		destory();
		return success;
	}
	/**
	 * ���·���ִ���߼���SQL����������ͬ
	 * ����ע��ֻ�ἰ��ͬ����
	 */
	@Override
	public boolean doUpdate(Record record) throws Exception{
		/**
		 * �����߼�Ϊȡ��record�ĵ�һ���ֶ���Ϊid����idΪ��������ʣ�µ�����
		 * ����Լ��record��field[0]Ϊid
		 * �ô˷����������ݸ������ϴ󣬺������Ż��������
		 * ��Ϊ������(field[] field,String table,String id)
		 */
		String table=record.getTable();
		Field[] field=record.getFields();
		StringBuilder sb=new StringBuilder("update ");
		sb.append(table).append(" set ");
		for (int i=1,n=field.length;i<n;i++){
			sb.append(field[i].getFieldName()).append("=?");
			if (i<n-1){
				sb.append(",");
			}
		}
		sb.append(" where id=").append("'").append(field[0].getValue()).append("'");
		String sql=sb.toString();
		System.out.println(sql);
		ps=con.prepareStatement(sql);
		for(int i=1,n=field.length;i<n;i++){
			ps.setString(i,field[i].getValue());
		}
		boolean success=false;
		if(ps.executeUpdate()>0){
			success=true;
		}
		destory();
		return success;
	}
	@Override
	public boolean doRemove(String table,Field[] field) throws Exception {
		StringBuilder sb=new StringBuilder("delete from ");
		sb.append(table).append(" where ");
		for (int i=0,n=field.length;i<n;i++){
			sb.append(field[i].getFieldName()).append("=?");
			if(i<n-1){
				sb.append(" and ");
			}
		}
		String sql=sb.toString();
		System.out.println(sql);
		ps=con.prepareStatement(sql);
		for (int i=1,n=field.length;i<=n;i++){
			ps.setString(i,field[i-1].getValue());
		}
		boolean success=false;
		if (ps.executeUpdate()>0){
			success=true;
		}
		destory();
		return success;
	}
	@Override
	public List<Record> findByKeyWord(String table,Field... field) throws Exception {
		/**
		 * @param table ����
		 * @param field ��ѡ������
		 * 
		 * ���ݹؼ��ֲ�ѯ���ݣ�tableΪ�������������������field�����������ͻ���������ѯ
		 * ֮����ѡ���ѡ�������ǲ���������ʽ����field����Ϊ�˴���fieldΪ�Ǳ�Ҫ�������ⲿ�����ǿθ�����Ҫ����
		 * ���߲����룬�����������ʽ����ʹ����Ҫ��Ҳ����д��һ��nullֵ��Ϊ�������ݣ��Եúܶ���
		 * 
		 * ����Remove()����Ϊ�漰��ɾ����¼�����Ա��봫��field��Ϊɾ��������û��ɾ����������ɾ�����м�¼
		 * ��ʹ����Ҫɾ�����м�¼��Ҳ���봫��һ��null��Ϊ��������
		 * 
		 * ���Ͼ��Ǵ����зֱ������������Ϳ�ѡ�������뷨
		 */
		StringBuilder sb=new StringBuilder("select * from ");
		sb.append(table);
		if(field.length>0){
			sb.append(" where ");
			for(int i=0,n=field.length;i<n;i++){
				sb.append(field[i].getFieldName()).append("=?");
				if(i<n-1){
					sb.append(",");
				}
			}
		}
		String sql=sb.toString();
		System.out.println(sql);
		ps=con.prepareStatement(sql);
		for(int i=1,n=field.length;i<=n;i++){
			ps.setString(i,field[i-1].getValue());
		}
		/**
		 * @param rs ��ѯ�����
		 * rsm��rs�л�ȡ������Ľṹ��Ϣ
		 */
		rs=ps.executeQuery();
		rsm=rs.getMetaData();
		/**
		 * @param n ��ȡ��������ֶθ���������ʵ����record�洢����
		 */
		int n=rsm.getColumnCount();
		List<Record> record=new ArrayList<>();;
		/**
		 * ѭ���ྡ����Ҫ�ظ��½���������
		 * �����ý���ѭ���⣬ѭ����ֻ��������
		 * ������ѭ������StackOverflow
		 */
		Record temp=null;
		Config config=Config.getDOM();
		Map<String,String> info=config.getFieldName(rsm.getTableName(1));
		while(rs.next()){
			temp=new Record(n);
			Field f=null;
			//ʹ��ѭ�����ֶ���Ϣ����field���У��ڽ�field������¼record
			for(int i=1;i<=n;i++){
				f=new Field();
				f.setMean(info.get(rsm.getColumnName(i)));
				f.setValue(rs.getString(i));
				f.setFieldName(rsm.getColumnName(i));
				temp.setField(f,i-1);
			}
			temp.setTable(rsm.getTableName(1));
			temp.setDb(rsm.getSchemaName(1));
			record.add(temp);
		}
		destory();
		return record;
	}
	@Override
	public Record findById(String table,String id) throws Exception {
		StringBuilder sb=new StringBuilder("select * from ");
		sb.append(table).append(" where id=?");
		String sql=sb.toString();
		System.out.println(sql);
		ps=con.prepareStatement(sql);
		ps.setString(1, id);
		rs=ps.executeQuery();
		rsm=rs.getMetaData();
		Record record=null;
		if(rs.next()){
			int n=rsm.getColumnCount();
			record=new Record(n);
			Field field=null;
			Config config=Config.getDOM();
			Map<String,String> info=config.getFieldName(rsm.getTableName(1));
			for(int i=1;i<=n;i++){
				field=new Field();
				field.setMean(info.get(rsm.getColumnName(i)));
				field.setValue(rs.getString(i));
				field.setFieldName(rsm.getColumnName(i));
				record.setField(field,i-1);
			}
			record.setTable(rsm.getTableName(1));
			record.setDb(rsm.getSchemaName(1));
		}
		destory();
		return record;
	}
	/**
	 * ����������ù���
	 */
	private void destory(){
		try{
			ps.close();
			if (rs!=null){
				rs.close();
			}
			rsm=null;
			ps=null;
			rs=null;
			con=null;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
