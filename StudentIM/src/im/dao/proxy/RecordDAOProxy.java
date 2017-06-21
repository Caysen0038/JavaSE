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
		//����ʹ���޲ι��죬�������DBC�����õ����ݿ�����
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
		//�ر����ݿ�����
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
