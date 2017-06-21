package im.dbc;

import java.sql.*;

import im.util.Config;
import im.util.enums.DBInfo;

public class DBCImpl implements DBC{
	/**
	 * @param SEP 路径分隔符 (更好的选择是采用File类中的分隔符)
	 * @param address 数据库地址(默认为localhost,采用127.0.0.1作用相同)
	 * @param port 数据库端口
	 * @param dbName 数据库名称
	 * @param user DBA用户名
	 * @param password DBA密码 
	 * @param url 实例化后进行拼接的URL地址
	 */
	private final String SEP="/";
	private String address=null;
	private String port=null;
	private String dbName=null;
	private StringBuilder url=null;
	private String user=null;
	private String password=null;
	private Connection con=null;
	private String driver=null;
	private Config dom=null;
	public DBCImpl(String address,String port,String dbName,String user,String password){
		this.address=address;
		this.dbName=dbName;
		this.port=port;
		this.user=user;
		this.password=password;
		/**
		 * 拼接url
		 */
		this.url=new StringBuilder("jdbc:mysql:");
		url.append(SEP).append(SEP).append(this.address).append(":").append(this.port)
		.append(SEP).append(this.dbName).append("?useSSL=false");
	}
	@Override
	public Connection getConnection() throws Exception{
		dom=Config.getDOM();
		driver=dom.findByTagName(DBInfo.DRIVER.getName());
		try{
			/**
			 * 加载指定驱动
			 */
			Class.forName(driver);
		}catch(ClassNotFoundException e){
			/**
			 * 如果驱动异常，则加载默认驱动
			 */
			Class.forName(DRIVER);
		}
		con=DriverManager.getConnection(url.toString(),user,password);
		System.out.println("连接数据库");
		return con;
	}
	@Override
	public void close() throws Exception{
		if(!con.isClosed()){
			System.out.println("断开连接");
			con.close();
		}
	}
}
