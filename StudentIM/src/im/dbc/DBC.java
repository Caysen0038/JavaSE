package im.dbc;
import java.sql.*;
public interface DBC {
	/**
	 * Ĭ�����ݿ����������ݿ�URL
	 * ����������´�config�ļ��ж�ȡ�����������ݻ�
	 */
	String DRIVER="com.mysql.jdbc.Driver";
	String ADDRESS="localhost";
	String PORT="3306";
	String DBNAME="imdb";
	String USER="root";
	String PASSWORD="admin";
	String PATH="config/config.xml";
	Connection getConnection() throws Exception;
	void close() throws Exception;
}
