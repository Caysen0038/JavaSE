package im.view.module.team;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
//import java.sql.*;//�����
//import java.sql.DriverManager;
//import java.sql.Connection;
//import java.sql.Statement;
//import java.sql.ResultSet;
//import java.sql.SQLException;

public class Do02 extends JDialog implements ActionListener{
	JLabel jl1;
	JLabel jl2;
	JLabel jl3;
	JLabel jl4;
	JLabel jl5;
	JLabel jl6;
	JLabel jl7;
	JLabel jl8;
	JLabel jl9;
	JLabel jl10;
	JLabel jl11;
	JLabel jl12;
	
	JTextField jtf1;
	JTextField jtf2;
	JTextField jtf3;
	JTextField jtf4;
	JTextField jtf5;
	JTextField jtf6;
	JTextField jtf7;
	JTextField jtf8;
	JTextField jtf9;

	JPanel jp;

	JComboBox jcb;

	JButton jb1;
	JButton jb2;
	JButton jb3;

//	JMenuBar jmb;
//	JMenu jm1;
//	JMenu jm2;
//	JMenu jm3;
//	JMenuItem jmi1;
//	JMenuItem jmi2;

//	static ResultSet rs;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Do02 d2=new Do02();
		
//		String driver = "com.mysql.jdbc.Driver";
//
//		try {
//			Class.forName(driver);
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			System.out.println("�������ݿ�����ʧ��");
//			System.exit(0);
//		}
//		System.out.println("���ݿ������ɹ�");
//		try {
//			String url = "jdbc:mysql://localhost:3306/imdb";
//			String user = "root";
//			String password = "123456";
//			Connection con = DriverManager.getConnection(url, user, password);
//			System.out.println("���ݿ����ӳɹ�");
//
//			Statement stmt = con.createStatement();
//			rs = stmt.executeQuery("select * from score");
//			System.out.println(rs.getString("sutdent_id"));
//			stmt.close();
//			con.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.exit(0);
//		}
	}
public Do02(){
	jl1=new JLabel("��Ҫ��ѯѧ���ǣ�");
	jl1.setFont(new Font("Serif",Font.BOLD,14));
	jl1.setBounds(200,30,150,30);
	jl2=new JLabel("��������磺");
	jl2.setBounds(250,130,100,30);
	jl3=new JLabel("Linux����ϵͳ��");
	jl3.setBounds(250,180,100,30);
	jl4=new JLabel("�����רҵӢ�");
	jl4.setBounds(250,230,200,30);
	jl5=new JLabel("�������Ϣ����������");
	jl5.setBounds(250,280,200,30);
	jl6=new JLabel("Java������ƣ�");
	jl6.setBounds(250,330,100,30);
	jl7=new JLabel("���ݿ�Ӧ��ʵѵ�̳̣�");
	jl7.setBounds(250,380,200,30);
	jl8=new JLabel("�ߵ���ѧ��");
	jl8.setBounds(250,430,100,30);
	jl9=new JLabel("XML��");
	jl9.setBounds(250,480,100,30);
	jl11=new JLabel(" ");
	jl11.setFont(new Font("Serif",Font.BOLD,16));
	jl11.setBounds(250,80,200,30);
	jl12=new JLabel("ѧ�ڣ�");
	jl12.setFont(new Font("Serif",Font.BOLD,14));
	jl12.setBounds(473,30,50,30);

	jtf1=new JTextField(10);
	jtf1.setBounds(320,30,150,25);
	jtf2=new JTextField(10);
	jtf2.setBounds(470,130,150,25);
	jtf2.setVisible(false);
	jtf3=new JTextField(10);
	jtf3.setBounds(470,180,150,25);
	jtf3.setVisible(false);
	jtf4=new JTextField(10);
	jtf4.setBounds(470,230,150,25);
	jtf4.setVisible(false);
	jtf5=new JTextField(10);
	jtf5.setBounds(470,280,150,25);
	jtf5.setVisible(false);
	jtf6=new JTextField(10);
	jtf6.setBounds(470,330,150,25);
	jtf6.setVisible(false);
	jtf7=new JTextField(10);
	jtf7.setBounds(470,380,150,25);
	jtf7.setVisible(false);
	jtf8=new JTextField(10);
	jtf8.setBounds(470,430,150,25);
	jtf8.setVisible(false);
	jtf9=new JTextField(10);
	jtf9.setBounds(470,480,150,25);
	jtf9.setVisible(false);

	String[] status={"1","2","3","4","5","6","7","8"};
	jcb=new JComboBox(status);
	jcb.setFont(new Font("Serif",Font.BOLD,14));
	jcb.setBounds(516,30,70,26);

	jb1=new JButton("��ѯ");
	jb1.setBounds(600,30,100,27);
	jb2=new JButton("�޸�");
	jb2.setBounds(312,530,100,30);
	jb2.setVisible(false);
	jb3=new JButton("ȡ��");
	jb3.setBounds(472,530,100,30);
	jb3.setVisible(false);

//	jmb=new JMenuBar();
//	jm1=new JMenu("�ɼ�");
//	jm2=new JMenu("����");
//	jm3=new JMenu("�˳�");
//	jm3.addMouseListener(new MouseAdapter(){
//		@Override
//		public void mouseClicked(MouseEvent e){
//			new Do05();
//			dispose();
//		}
//	});
//	jmi1=new JMenuItem("���");
//	jmi1.addActionListener(new ActionListener() {
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			new Do01();
//			dispose();
//		}
//	});
//	jmi2=new JMenuItem("��ѯ");
//	jmi2.addActionListener(new ActionListener() {
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			new Do02();
//			dispose();
//		}
//	});

	String pic="background.jpg";
    ImageIcon bgd = new ImageIcon(pic);
    jl10= new JLabel(bgd);
    jl10.setBounds(0,0,900,700);
    jp=(JPanel)this.getContentPane();
    jp.setOpaque(false);
    this.getLayeredPane().add(jl10,new Integer(Integer.MIN_VALUE));

	this.add(jl1);
	this.add(jl2);
	this.add(jl3);
	this.add(jl4);
	this.add(jl5);
	this.add(jl6);
	this.add(jl7);
	this.add(jl8);
	this.add(jl9);
	this.add(jl11);
	this.add(jl12);

	this.add(jtf1);
	this.add(jtf2);
	this.add(jtf3);
	this.add(jtf4);
	this.add(jtf5);
	this.add(jtf6);
	this.add(jtf7);
	this.add(jtf8);
	this.add(jtf9);

	this.add(jcb);

	this.add(jb1);
	jb1.addActionListener(this);
	this.add(jb2);
	jb2.addActionListener(this);
	this.add(jb3);
	jb3.addActionListener(this);

//	jm1.add(jmi1);
//	jm1.addSeparator();
//	jm1.add(jmi2);
//	jmb.add(jm1);
//	jmb.add(jm2);
//	jmb.add(jm3);

//	this.setJMenuBar(jmb);

	this.setLayout(null);
	this.setTitle("��ѯ�ɼ�");
	this.setSize(900,650);
	this.setLocation(200,60);
	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//	this.addWindowListener(new WindowAdapter(){
//		@Override
//		public void windowClosed(WindowEvent e){
//			System.out.println("�ͷ�");
//			((JFrame)e.getSource()).dispose();
//		}
//	});
	this.setResizable(false);
	this.setVisible(true);
	}
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
//	String str=(String)jcb.getSelectedItem();
	//String sql="select * from score where student_id='' and term=''";
//	String stid = null;
//	String term = null;
//	try {
//		stid = rs.getString("student_id");
//		term=rs.getString("term");
//	} catch (SQLException e1) {
//		// TODO Auto-generated catch block
//		e1.printStackTrace();
//	}
	if(e.getSource()==jb1){
		jl11.setText("���Ĳ�ѯ������£�");
		jtf2.setVisible(true);
		jtf2.setEditable(false);
		jtf3.setVisible(true);
		jtf3.setEditable(false);
		jtf4.setVisible(true);
		jtf4.setEditable(false);
		jtf5.setVisible(true);
		jtf5.setEditable(false);
		jtf6.setVisible(true);
		jtf6.setEditable(false);
		jtf7.setVisible(true);
		jtf7.setEditable(false);
		jtf8.setVisible(true);
		jtf8.setEditable(false);
		jtf9.setVisible(true);
		jtf9.setEditable(false);
		jb2.setVisible(true);
	}else if(e.getSource()==jb2){
		jl11.setText("�����޸ģ�");
		jtf2.setEditable(true);
		jtf3.setEditable(true);
		jtf4.setEditable(true);
		jtf5.setEditable(true);
		jtf6.setEditable(true);
		jtf7.setEditable(true);
		jtf8.setEditable(true);
		jtf9.setEditable(true);
		jb2.setText("����");
		jb3.setVisible(true);
		if(e.getActionCommand().equals("����")){
			jl11.setText("�޸ĳɹ���");
			JOptionPane.showMessageDialog(this,"�޸ĳɹ���");
			jtf2.setEditable(false);
			jtf3.setEditable(false);
			jtf4.setEditable(false);
			jtf5.setEditable(false);
			jtf6.setEditable(false);
			jtf7.setEditable(false);
			jtf8.setEditable(false);
			jtf9.setEditable(false);
			jb2.setText("�޸�");
			jb3.setVisible(false);
		}
	}else if(e.getActionCommand().equals("ȡ��")){
		jl11.setText("���Ĳ�ѯ������£�");
		jtf2.setEditable(false);
		jtf3.setEditable(false);
		jtf4.setEditable(false);
		jtf5.setEditable(false);
		jtf6.setEditable(false);
		jtf7.setEditable(false);
		jtf8.setEditable(false);
		jtf9.setEditable(false);
		jb2.setText("�޸�");
		jb3.setVisible(false);
	}
	
	}
}