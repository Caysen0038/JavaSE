package im.dbc;

import java.sql.*;

import im.util.Config;
import im.util.enums.DBInfo;

public class DBCImpl implements DBC{
	/**
	 * @param SEP ·���ָ��� (���õ�ѡ���ǲ���File���еķָ���)
	 * @param address ���ݿ��ַ(Ĭ��Ϊlocalhost,����127.0.0.1������ͬ)
	 * @param port ���ݿ�˿�
	 * @param dbName ���ݿ�����
	 * @param user DBA�û���
	 * @param password DBA���� 
	 * @param url ʵ���������ƴ�ӵ�URL��ַ
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
		 * ƴ��url
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
			 * ����ָ������
			 */
			Class.forName(driver);
		}catch(ClassNotFoundException e){
			/**
			 * ��������쳣�������Ĭ������
			 */
			Class.forName(DRIVER);
		}
		con=DriverManager.getConnection(url.toString(),user,password);
		System.out.println("�������ݿ�");
		return con;
	}
	@Override
	public void close() throws Exception{
		if(!con.isClosed()){
			System.out.println("�Ͽ�����");
			con.close();
		}
	}
}
