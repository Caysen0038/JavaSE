package im.dao;

import java.util.List;

import im.dbc.DBC;
import im.entity.Field;
import im.entity.Record;

public class DAOAdapter implements DAO{
	/**
	 * DAO�ӿ�������
	 * ����DAOʵ����̳У���������ʵ�ֲ�ͬ����
	 */
	@Override
	public void setDBC(DBC db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean doInsert(Record record) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(String table, Field[] field) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Record> findByKeyWord(String table, Field... field) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Record findById(String table, String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> queryTable() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Record> SQL(String sql) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean doUpdate(Record record) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
