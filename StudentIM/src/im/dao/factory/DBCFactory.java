package im.dao.factory;

import im.dbc.DBC;
import im.dbc.DBCProxy;

public class DBCFactory{
	/**
	 * DBC��̬������DAO��ȡDBC���ӱ���ͨ����̬����
	 */
	public static DBC getDBC(){
		DBC dbc=null;
		try{
			/**
			 * ͨ������ʵ����dbc
			 */
			dbc=new DBCProxy();
		}catch(Exception e){
			e.printStackTrace();
		}
		return dbc;
	}
}
