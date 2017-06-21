package im.dao.proxy;
import im.entity.*;

import java.util.List;

import im.dao.*;
import im.dao.impl.*;
import im.dbc.*;
public class RecordDAOProxy extends DAOAdapter{
	private DBC db=null;
	private RecordDAOImpl dao=null;
	@Deprecated
	public RecordDAOProxy(){
		//避免使用无参构造，必须参入DBC参数得到数据库连接
	}
	public RecordDAOProxy(DBC db){
		setDBC(db);
	}
	@Override
	public void setDBC(DBC db) {
		this.db=db;
		dao=new RecordDAOImpl(db);
	}
	@Override
	public boolean doInsert(Record record) throws Exception {
		boolean success=dao.doInsert(record);
		//关闭数据库连接
		db.close();
		dao=null;
		return success;
	}
	@Override
	public boolean doUpdate(Record record) throws Exception{
		boolean success=dao.doUpdate(record);
		db.close();
		dao=null;
		return success;
	}
	@Override
	public boolean doRemove(String table,Field[] field) throws Exception {
		boolean success=dao.doRemove(table, field);
		db.close();
		db=null;
		dao=null;
		return success;
	}
	@Override
	public List<Record> findByKeyWord(String table,Field... field) throws Exception {
		List<Record> list=dao.findByKeyWord(table, field);
		db.close();
		db=null;
		dao=null;
		return list;
	}
	@Override
	public Record findById(String table,String id) throws Exception {
		Record record=dao.findById(table, id);
		db.close();
		db=null;
		dao=null;
		return record;
	}
}
