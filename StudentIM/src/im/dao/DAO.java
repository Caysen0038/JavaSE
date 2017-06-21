package im.dao;
import java.util.List;
import im.dbc.*;
import im.entity.*;
public interface DAO {
	/**
	 * 统一DAO接口
	 */
	void setDBC(DBC db);
	boolean doInsert(Record record) throws Exception;
	boolean doUpdate(Record record) throws Exception;
	boolean doRemove(String table,Field[] field) throws Exception;
	List<Record> findByKeyWord(String table,Field... field) throws Exception;
	Record findById(String table,String id) throws Exception;
	List<String> queryTable() throws Exception;
	List<Record> SQL(String sql) throws Exception;
}
