package im.dao.factory;
import im.dao.*;
import im.dbc.*;
public class DAOFactory {
	/**
	 * 通过静态工厂获得DAO代理实例
	 */
	private static StringBuilder classPath=null;
	public static DAO getDAO(String name) throws Exception{
		/**
		 * @param name DAO代理名称
		 */
		classPath=new StringBuilder("im.dao.proxy.");
		name=toUpper(name);
		classPath.append(name).append("DAOProxy");
		/**
		 * 默认数据库URL为 jdbc:mysql://localhost:3306/imdb?name=root&password=admin;
		 */
		DBC db=DBCFactory.getDBC();
		/**
		 * 反射实例化class并向上转型至DAO
		 * @method setDBC() 设置DAO关联的数据库连接
		 */
		DAO dao=(DAO)Class.forName(classPath.toString()).newInstance();
		dao.setDBC(db);
		return dao;
	}
	private static String toUpper(String temp){
		/**
		 * 将temp首字母变为大写
		 */
		return temp.substring(0,1).toUpperCase()+temp.substring(1);
	}
}
