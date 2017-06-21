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
		// 2)�������ݿ����� ��������

		try {
			String driver = "com.mysql.jdbc.Driver";// �ؼ����������ݿ����棨������
			Class.forName(driver);// �������
			System.out.println("�������ݿ�����ɹ�");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("�������ݿ����棨������ʧ��");
		}
		// 3)�������ݿ�����
		try {
			String url = "jdbc:mysql://localhost:3306/imdb?useSSL=false";
			String user = "root";
			String password = "admin";
			con = DriverManager.getConnection(url, user, password);// �ؼ����룺�������ݿ�����
			System.out.println("���ݿ����ӳɹ�");
			// ���ݿ����ӳɹ�
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("�������Ӵ���");
		}
	}
	public Connection getCon(){
		return con;
	}
	// ��������ѯ
	public ResultSet query(String sql) {
		try {
			System.out.println("��ȡ��ʼ");

			st = con.createStatement();// ����sql�������
			rs = st.executeQuery(sql);// ��ѯ���� ����������ϣ���
		
			System.out.println("��ȡ���");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("���ݿ��ѯʧ��");
		}
		return rs;
	}

	// ������ �����޸�ɾ��
	public void add_update_delete(String sql) {
		// 4���������ӣ��޸ģ�ɾ�� ���ȴ���sql�������Ȼ��������ӷ���
		try {
			st = con.createStatement();// ����sql�������
			i = st.executeUpdate(sql);// ��ѯ����

			System.out.println("�����ɹ�" + i + "��");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("���ݲ���ʧ��");
		}
	}
	//�������ر�
	public void close() {
		try {
			st.close();// �ر�sql����
			con.close();// �ر����ݿ�����
			System.out.println("���ݿ������ر�");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("���ݿ�رճ���");
		}
	}
}

