package im.dbc;
import java.sql.*;
import java.util.Map;

import im.util.Config;
import im.util.enums.DBInfo;
public class DBCProxy implements DBC{
	/**
	 * DBC代理类，后期优化做为DBC参数的有效性检查
	 */
	public String address;
	public String port;
	public String name;
	public String username;
	public String password;
	private Connection con=null;
	private DBCImpl dbc=null;
	private Config dom=null;
	private Map<String,String> info=null;
	public DBCProxy(){
		init();
	}
	private void init(){
		dom=Config.getDOM();
		info=dom.getDB();
		address=info.get(DBInfo.ADDRESS.getName());
		port=info.get(DBInfo.PORT.getName());
		name=info.get(DBInfo.NAME.getName());
		username=info.get(DBInfo.USERNAME.getName());
		password=info.get(DBInfo.USERPASSWORD.getName());
		dbc=new DBCImpl(address,port,name,username,password);
	}
	@Override
	public Connection getConnection() throws Exception{
		con=dbc.getConnection();
		return con;
	}
	@Override
	public void close() throws Exception{
		dbc.close();
		info.clear();
		info=null;
		dom=null;
		dbc=null;
	}
}
