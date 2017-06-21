package im.view.module.team;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TESTDB {
	private Connection con = null;
	private Statement st = null;
	private ResultSet rs = null;
	private int i = 0;

	public TESTDB() {
		// 2)加载数据库引擎 （驱动）

		try {
			String driver = "com.mysql.jdbc.Driver";// 关键：加载数据库引擎（驱动）
			Class.forName(driver);// 核心语句
			System.out.println("加载数据库引擎成功");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("加载数据库引擎（驱动）失败");
		}
		// 3)创建数据库连接
		try {
			String url = "jdbc:mysql://localhost:3306/imdb?useSSL=false";
			String user = "root";
			String password = "admin";
			con = DriverManager.getConnection(url, user, password);// 关键代码：创建数据库连接
			System.out.println("数据库连接成功");
			// 数据库连接成功
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据连接错误");
		}
	}
	public Connection getCon(){
		return con;
	}
	// 方法：查询
	public ResultSet query(String sql) {
		try {
			System.out.println("读取开始");

			st = con.createStatement();// 创建sql命令对象
			rs = st.executeQuery(sql);// 查询方法 结果集：集合（表）
		
			System.out.println("读取完毕");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据库查询失败");
		}
		return rs;
	}

	// 方法： 增加修改删除
	public void add_update_delete(String sql) {
		// 4）数据增加，修改，删除 首先创建sql命令对象，然后调用增加方法
		try {
			st = con.createStatement();// 创建sql命令对象
			i = st.executeUpdate(sql);// 查询方法

			System.out.println("操作成功" + i + "条");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据操作失败");
		}
	}
	//方法：关闭
	public void close() {
		try {
			st.close();// 关闭sql命令
			con.close();// 关闭数据库连接
			System.out.println("数据库正常关闭");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据库关闭出错");
		}
	}
}

