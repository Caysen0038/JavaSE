package im.dao.proxy;
import im.dao.*;
import im.dao.impl.*;
import im.dbc.*;
import im.entity.*;
import java.util.*;
public class SQLDAOProxy extends DAOAdapter{
	private DBC db=null;
	private SQLDAOImpl dao=null;
	@Deprecated
	public SQLDAOProxy(){
		
	}
	public SQLDAOProxy(DBC db){
		setDBC(db);
	}
	@Override
	public void setDBC(DBC db){
		this.db=db;
		dao=new SQLDAOImpl(db);
	}
	@Override
	public List<Record> SQL(String sql) throws Exception{
		//sql°²È«¼ì²é
		if (sql==null||"".equals(sql)){
			return null;
		}
		List<Record> list=dao.SQL(sql);
		db.close();
		db=null;
		dao=null;
		return list;
	}
}
