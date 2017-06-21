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
public class Do01 extends JDialog implements ActionListener{
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
	JButton jb4;

//	JMenuBar jmb;
//	JMenu jm1;
//	JMenu jm2;
//	JMenu jm3;
//	JMenuItem jmi1;
//	JMenuItem jmi2;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Do01 d1=new Do01();
	}

public Do01(){
	jl1=new JLabel("添加成绩的学号：");
	jl1.setBounds(170,30,150,30);
	jl2=new JLabel("计算机网络：");
	jl2.setBounds(250,150,100,30);
	jl2.setVisible(false);
	jl3=new JLabel("Linux操作系统：");
	jl3.setBounds(250,200,100,30);
	jl3.setVisible(false);
	jl4=new JLabel("计算机专业英语：");
	jl4.setBounds(250,250,200,30);
	jl4.setVisible(false);
	jl5=new JLabel("计算机信息技术基础：");
	jl5.setBounds(250,300,200,30);
	jl5.setVisible(false);
	jl6=new JLabel("Java程序设计：");
	jl6.setBounds(250,350,100,30);
	jl6.setVisible(false);
	jl7=new JLabel("数据库应用实训教程：");
	jl7.setBounds(250,400,200,30);
	jl7.setVisible(false);
	jl8=new JLabel("高等数学：");
	jl8.setBounds(250,450,100,30);
	jl8.setVisible(false);
	jl9=new JLabel("XML：");
	jl9.setBounds(250,500,100,30);
	jl9.setVisible(false);
	jl10=new JLabel("学期：");
	jl10.setBounds(450,30,50,30);
	jl11=new JLabel("请添加该同学的成绩：");
	jl11.setFont(new Font("Serif",Font.BOLD,14));
	jl11.setBounds(250,90,200,30);
	jl11.setVisible(false);
	
	jtf1=new JTextField(10);
	jtf1.setBounds(270,30,150,25);
	jtf2=new JTextField(10);
	jtf2.setBounds(470,150,150,25);
	jtf2.setVisible(false);
	jtf3=new JTextField(10);
	jtf3.setBounds(470,200,150,25);
	jtf3.setVisible(false);
	jtf4=new JTextField(10);
	jtf4.setBounds(470,250,150,25);
	jtf4.setVisible(false);
	jtf5=new JTextField(10);
	jtf5.setBounds(470,300,150,25);
	jtf5.setVisible(false);
	jtf6=new JTextField(10);
	jtf6.setBounds(470,350,150,25);
	jtf6.setVisible(false);
	jtf7=new JTextField(10);
	jtf7.setBounds(470,400,150,25);
	jtf7.setVisible(false);
	jtf8=new JTextField(10);
	jtf8.setBounds(470,450,150,25);
	jtf8.setVisible(false);
	jtf9=new JTextField(10);
	jtf9.setBounds(470,500,150,25);
	jtf9.setVisible(false);

	String[] status={"1","2","3","4","5","6","7","8"};
	jcb=new JComboBox(status);
	jcb.setFont(new Font("Serif",Font.BOLD,14));
	jcb.setBounds(500,30,70,26);
	
	jb1=new JButton("添加");
	jb1.setBounds(242,550,100,30);
	jb1.setVisible(false);
	jb2=new JButton("重置");
	jb2.setBounds(392,550,100,30);
	jb2.setVisible(false);
	jb3=new JButton("取消");
	jb3.setBounds(542,550,100,30);
	jb3.setVisible(false);
	jb4=new JButton("搜索");
	jb4.setBounds(600,30,100,27);

//	jmb=new JMenuBar();
//	jm1=new JMenu("成绩");
//	jm2=new JMenu("帮助");
//	jm3=new JMenu("退出");
//	jm3.addMouseListener(new MouseAdapter(){
//		@Override
//		public void mouseClicked(MouseEvent e){
//			new Do05();
//			dispose();
//		}
//	});
//	jmi1=new JMenuItem("添加");
//	jmi1.addActionListener(new ActionListener() {
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			new Do01();
//			dispose();
//		}
//	});
//	jmi2=new JMenuItem("查询");
//	jmi2.addActionListener(new ActionListener() {
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			new Do02();
//			dispose();
//		}
//	});

	
	this.add(jl1);
	this.add(jl2);
	this.add(jl3);
	this.add(jl4);
	this.add(jl5);
	this.add(jl6);
	this.add(jl7);
	this.add(jl8);
	this.add(jl9);
	this.add(jl10);
	this.add(jl11);
	
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

//	this.add(jm1);
	
	this.add(jb1);
	jb1.addActionListener(this);
	this.add(jb2);
	jb2.addActionListener(this);
	this.add(jb3);
	jb3.addActionListener(this);
	this.add(jb4);
	jb4.addActionListener(this);

//	jm1.add(jmi1);
//	jm1.addSeparator();
//	jm1.add(jmi2);
//	jmb.add(jm1);
//	jmb.add(jm2);
//	jmb.add(jm3);

//	this.setJMenuBar(jmb);

	String pic="background.jpg";
    ImageIcon bgd = new ImageIcon(pic);
    jl10= new JLabel(bgd);
    jl10.setBounds(0,0,900,700);
    jp=(JPanel)this.getContentPane();//把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
    jp.setOpaque(false);
    this.getLayeredPane().add(jl10,new Integer(Integer.MIN_VALUE));

	this.setLayout(null);
	this.setTitle("添加成绩");
	this.setSize(900,650);
	this.setLocation(200,60);
	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//	this.addWindowListener(new WindowAdapter(){
//		@Override
//		public void windowClosed(WindowEvent e){
//			System.out.println("释放");
//			((JFrame)e.getSource()).dispose();
//		}
//	});
	this.setResizable(false);
	this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str=(String)jcb.getSelectedItem();
		if(str=="1"||str=="2"||str=="3"||str=="4"||str=="5"||str=="6"||str=="7"||str=="8"){
			if(e.getActionCommand().equals("搜索")){
				jl2.setVisible(true);
				jl3.setVisible(true);
				jl4.setVisible(true);
				jl5.setVisible(true);
				jl6.setVisible(true);
				jl7.setVisible(true);
				jl8.setVisible(true);
				jl9.setVisible(true);
				jl11.setVisible(true);
				jtf2.setVisible(true);
				jtf3.setVisible(true);
				jtf4.setVisible(true);
				jtf5.setVisible(true);
				jtf6.setVisible(true);
				jtf7.setVisible(true);
				jtf8.setVisible(true);
				jtf9.setVisible(true);
				jb1.setVisible(true);
				jb2.setVisible(true);
			}else if(e.getActionCommand().equals("重置")){
				jtf2.setText("");
				jtf3.setText("");
				jtf4.setText("");
				jtf5.setText("");
				jtf6.setText("");
				jtf7.setText("");
				jtf8.setText("");
				jtf9.setText("");
			}else if(e.getActionCommand().equals("添加")){
				JOptionPane.showMessageDialog(this,"添加成功！");
				jl11.setText("添加成功，预览该同学成绩：");
				jb1.setText("修改");
				jtf2.setEditable(false);
				jtf3.setEditable(false);
				jtf4.setEditable(false);
				jtf5.setEditable(false);
				jtf6.setEditable(false);
				jtf7.setEditable(false);
				jtf8.setEditable(false);
				jtf9.setEditable(false);
			}else if(e.getActionCommand().equals("修改")){
				jl11.setText("请修改该同学成绩：");
				jb1.setText("保存");
				jb3.setVisible(true);
				jtf2.setEditable(true);
				jtf3.setEditable(true);
				jtf4.setEditable(true);
				jtf5.setEditable(true);
				jtf6.setEditable(true);
				jtf7.setEditable(true);
				jtf8.setEditable(true);
				jtf9.setEditable(true);
			}else if(e.getActionCommand().equals("保存")){
				JOptionPane.showMessageDialog(this,"修改成功！");
				jl11.setText("修改成功，预览该同学成绩：");
				jb1.setText("修改");
				jb3.setVisible(false);
				jtf2.setEditable(false);
				jtf3.setEditable(false);
				jtf4.setEditable(false);
				jtf5.setEditable(false);
				jtf6.setEditable(false);
				jtf7.setEditable(false);
				jtf8.setEditable(false);
				jtf9.setEditable(false);
			}else if(e.getActionCommand().equals("取消")){
				jl11.setText("取消修改，预览该同学成绩：");
				jb1.setText("修改");
				jb3.setVisible(false);
				jtf2.setEditable(false);
				jtf3.setEditable(false);
				jtf4.setEditable(false);
				jtf5.setEditable(false);
				jtf6.setEditable(false);
				jtf7.setEditable(false);
				jtf8.setEditable(false);
				jtf9.setEditable(false);
			}
		}
	}
}