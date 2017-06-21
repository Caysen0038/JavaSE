package im.view.module.team;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class Do04 extends JFrame implements ActionListener{
	JLabel jl1;
	JLabel jl2;
	JLabel jl3;
	JLabel jl4;
	JLabel jl5;
	JLabel jl6;
	JLabel jl7;

	JTextField jtf1;
	JPasswordField jtf2;

	JComboBox jcb;

	JButton jb1;
	JButton jb2;

	JPanel jp;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Do04 d4=new Do04();
	}
public Do04(){
	jl1=new JLabel("登录账号：");
	jl1.setFont(new Font("Serif",Font.PLAIN,18));
	jl1.setBounds(176,100,100,20);
	jl2=new JLabel("登录密码：");
	jl2.setFont(new Font("Serif",Font.PLAIN,18));
	jl2.setBounds(176,150,100,20);
	jl3=new JLabel("登录身份：");
	jl3.setFont(new Font("Serif",Font.PLAIN,18));
	jl3.setBounds(197,200,100,20);
//	jl5=new JLabel("—选择—");
//	jl5.setBounds(0,0,100,25);
//	jl5.setFont(new Font("Serif",Font.PLAIN,13));
//	jl5.setForeground(Color.gray);
//	jl6=new JLabel("—教师—");
//	jl6.setSize(100,25);
//	jl6.setFont(new Font("Serif",Font.PLAIN,14));
//	jl7=new JLabel("—学生—");
//	jl7.setSize(100,25);
//	jl7.setFont(new Font("Serif",Font.PLAIN,14));

	jtf1=new JTextField(10);
	jtf1.setBounds(267,100,150,26);
	jtf2=new JPasswordField(16);
	jtf2.setBounds(267,150,150,26);

	String[] status={"—选择—","—教师—","—学生—"};
	jcb=new JComboBox(status);
//	jcb.addItem(jl5);
//	jcb.addItem(jl6);
//	jcb.addItem(jl7);
	jcb.setFont(new Font("Serif",Font.PLAIN,14));
	jcb.setBounds(287,195,100,26);

	jb1=new JButton("登录");
	jb1.setFont(new Font("Serif",Font.PLAIN,12));
	jb1.setBounds(162,250,100,30);
	jb2=new JButton("取消");
	jb2.setFont(new Font("Serif",Font.PLAIN,12));
	jb2.setBounds(322,250,100,30);

	this.add(jl1);
	this.add(jl2);
	this.add(jl3);

	this.add(jtf1);
	this.add(jtf2);

	this.add(jcb);

	this.add(jb1);
	jb1.addActionListener(this);
	this.add(jb2);
	jb2.addActionListener(this);

	String pic="timg.jpg";
    ImageIcon bgd = new ImageIcon(pic);
    jl4= new JLabel(bgd);
    jl4.setBounds(-500,-30,1200,400);
    jp=(JPanel)this.getContentPane();
    jp.setOpaque(false);
    this.getLayeredPane().add(jl4,new Integer(Integer.MIN_VALUE));
	
	this.setLayout(null);
	this.setTitle("欢迎登陆学生管理系统");
	this.setSize(600, 400);
	this.setLocation(300, 150);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setResizable(false);
	this.setVisible(true);
}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String str=(String)jcb.getSelectedItem();
		if(str=="—教师—"){
			if(e.getActionCommand().equals("登录")){
				 JOptionPane.showMessageDialog(this,"老师欢迎您，您已登录成功！");
				 dispose();
			}
		}else if(str=="—学生—"){
			if(e.getActionCommand().equals("登录")){
				 JOptionPane.showMessageDialog(this,"同学欢迎您，您已登录成功！");
				 dispose();
			}
		}
		else if(str=="—选择—"){
			if(e.getActionCommand().equals("登录")){
			 JOptionPane.showMessageDialog(this,"请选择登录身份！");
			}
		}
		if(e.getActionCommand().equals("取消")){
			dispose();
		}
	}
}