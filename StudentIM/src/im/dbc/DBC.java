package im.dbc;
import java.sql.*;
public interface DBC {
	/**
	 * 默认数据库驱动与数据库URL
	 * 但常规情况下从config文件中读取配置连接数据化
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
