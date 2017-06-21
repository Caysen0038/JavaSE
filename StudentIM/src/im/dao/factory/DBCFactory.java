package im.dao.factory;

import im.dbc.DBC;
import im.dbc.DBCProxy;

public class DBCFactory{
	/**
	 * DBC静态工厂，DAO获取DBC连接必须通过静态工厂
	 */
	public static DBC getDBC(){
		DBC dbc=null;
		try{
			/**
			 * 通过代理实例化dbc
			 */
			dbc=new DBCProxy();
		}catch(Exception e){
			e.printStackTrace();
		}
		return dbc;
	}
}
