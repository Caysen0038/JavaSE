package im.dao.impl;
import im.dao.*;
import im.dbc.DBC;
import im.entity.*;
import im.util.Config;

import java.sql.*;
import java.util.*;
public class RecordDAOImpl extends DAOAdapter{
	/**
	 * 此类为增删查改实现类，不包含其他多余操作
	 * @param con 数据库连接，DBC由代理类，实现类保存Connection
	 * @param rsm 获得返回结果集的数据表信息
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
		//获得要执行的数据表名称
		String table=record.getTable();
		//获得记录中的字段信息
		Field[] field=record.getFields();
		StringBuilder sb=new StringBuilder("insert into ");
		sb.append(table).append(" values(");
		/**
		 * 根据字段信息拼接SQL语句
		 * 因为涉及到不确定的字符串拼接，性能上优先考虑StringBuilder而非线程安全的StringBuffer
		 * 拼接字符串尽量避免直接使用String的"+";
		 * 
		 * 循环内涉及重复计算引用长度等操作时，在循环初始化时建立一个变量临时存储引用长度
		 * 避免每次循环都进入计算，例子如下
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
		 * for循环插入PreparedStatement执行参数
		 */
		for (int i=1;i<=field.length;i++){
			ps.setString(i,field[i-1].getValue());
		}
		boolean success=false;
		//取得执行结果
		if(ps.executeUpdate()>0){
			success=true;
		}
		/**
		 * @method destory() 清空所有堆指向
		 */
		destory();
		return success;
	}
	/**
	 * 以下方法执行逻辑除SQL语句外大致相同
	 * 下面注释只提及不同部分
	 */
	@Override
	public boolean doUpdate(Record record) throws Exception{
		/**
		 * 更新逻辑为取出record的第一个字段作为id，以id为条件插入剩下的数据
		 * 所以约定record的field[0]为id
		 * 用此方法更新数据更新量较大，后期需优化传入参数
		 * 改为类似于(field[] field,String table,String id)
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
		 * @param table 表名
		 * @param field 可选参数项
		 * 
		 * 根据关键字查询数据，table为必需参数，但允许不传入field参数，那样就会无条件查询
		 * 之所以选择可选参数而非采用数组形式传入field，因为此处的field为非必要参数，外部引用是课根据需要传入
		 * 或者不传入，如果是数组形式，即使不需要，也必须写入一个null值作为参数传递，显得很多余
		 * 
		 * 方法Remove()中因为涉及到删除记录，所以必须传入field作为删除条件，没有删除条件将会删除所有记录
		 * 即使真需要删除所有记录，也必须传入一个null作为参数传递
		 * 
		 * 以上就是此类中分别采用数组参数和可选参数的想法
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
		 * @param rs 查询结果集
		 * rsm从rs中获取结果集的结构信息
		 */
		rs=ps.executeQuery();
		rsm=rs.getMetaData();
		/**
		 * @param n 获取结果集中字段个数，用于实例化record存储数据
		 */
		int n=rsm.getColumnCount();
		List<Record> record=new ArrayList<>();;
		/**
		 * 循环类尽量不要重复新建对象引用
		 * 将引用建在循环外，循环内只更改引用
		 * 避免死循环进入StackOverflow
		 */
		Record temp=null;
		Config config=Config.getDOM();
		Map<String,String> info=config.getFieldName(rsm.getTableName(1));
		while(rs.next()){
			temp=new Record(n);
			Field f=null;
			//使用循环将字段信息存入field类中，在讲field类存入记录record
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
	 * 清除所有引用关联
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
