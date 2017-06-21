package im.dao.factory;
import im.dao.*;
import im.dbc.*;
public class DAOFactory {
	/**
	 * ͨ����̬�������DAO����ʵ��
	 */
	private static StringBuilder classPath=null;
	public static DAO getDAO(String name) throws Exception{
		/**
		 * @param name DAO��������
		 */
		classPath=new StringBuilder("im.dao.proxy.");
		name=toUpper(name);
		classPath.append(name).append("DAOProxy");
		/**
		 * Ĭ�����ݿ�URLΪ jdbc:mysql://localhost:3306/imdb?name=root&password=admin;
		 */
		DBC db=DBCFactory.getDBC();
		/**
		 * ����ʵ����class������ת����DAO
		 * @method setDBC() ����DAO���������ݿ�����
		 */
		DAO dao=(DAO)Class.forName(classPath.toString()).newInstance();
		dao.setDBC(db);
		return dao;
	}
	private static String toUpper(String temp){
		/**
		 * ��temp����ĸ��Ϊ��д
		 */
		return temp.substring(0,1).toUpperCase()+temp.substring(1);
	}
}
