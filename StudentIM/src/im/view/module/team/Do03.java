package im.view.module.team;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class Do03 extends JFrame implements ActionListener{
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

	JButton jb1;
	JButton jb2;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Do03 d3=new Do03();
	}
public Do03(){
	jl1=new JLabel("��Ҫ��ѯѧ���ǣ�");
	jl1.setBounds(100,50,150,30);
	jl2=new JLabel("��������磺");
	jl2.setBounds(100,150,100,30);
	jl3=new JLabel("Linux����ϵͳ��");
	jl3.setBounds(100,200,100,30);
	jl4=new JLabel("�����רҵӢ�");
	jl4.setBounds(100,250,200,30);
	jl5=new JLabel("�������Ϣ����������");
	jl5.setBounds(100,300,200,30);
	jl6=new JLabel("Java������ƣ�");
	jl6.setBounds(100,350,100,30);
	jl7=new JLabel("���ݿ�Ӧ��ʵѵ�̳̣�");
	jl7.setBounds(100,400,200,30);
	jl8=new JLabel("�ߵ���ѧ��");
	jl8.setBounds(100,450,100,30);
	jl9=new JLabel("XML��");
	jl9.setBounds(100,500,100,30);
	jl11=new JLabel(" ");
	jl11.setBounds(100,100,200,30);

	jtf1=new JTextField(10);
	jtf1.setBounds(200,50,200,25);
	jtf2=new JTextField(10);
	jtf2.setBounds(320,150,150,25);
	jtf2.setVisible(false);
	jtf3=new JTextField(10);
	jtf3.setBounds(320,200,150,25);
	jtf3.setVisible(false);
	jtf4=new JTextField(10);
	jtf4.setBounds(320,250,150,25);
	jtf4.setVisible(false);
	jtf5=new JTextField(10);
	jtf5.setBounds(320,300,150,25);
	jtf5.setVisible(false);
	jtf6=new JTextField(10);
	jtf6.setBounds(320,350,150,25);
	jtf6.setVisible(false);
	jtf7=new JTextField(10);
	jtf7.setBounds(320,400,150,25);
	jtf7.setVisible(false);
	jtf8=new JTextField(10);
	jtf8.setBounds(320,450,150,25);
	jtf8.setVisible(false);
	jtf9=new JTextField(10);
	jtf9.setBounds(320,500,150,25);
	jtf9.setVisible(false);

	jb1=new JButton("��ѯ");
	jb1.setBounds(400,50,100,25);
	jb2=new JButton("����");
	jb2.setBounds(402,570,100,30);

	String pic="background.jpg";
    ImageIcon bgd = new ImageIcon(pic);
    jl10= new JLabel(bgd);
    jl10.setBounds(0,0,600,700);
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

	this.add(jtf1);
	this.add(jtf2);
	this.add(jtf3);
	this.add(jtf4);
	this.add(jtf5);
	this.add(jtf6);
	this.add(jtf7);
	this.add(jtf8);
	this.add(jtf9);

	this.add(jb1);
	jb1.addActionListener(this);
	this.add(jb2);
	jb2.addActionListener(this);

	this.setLayout(null);
	this.setTitle("��ѯ�ɼ�");
	this.setSize(600, 700);
	this.setLocation(300, 10);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setResizable(false);
	this.setVisible(true);
	}
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
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
	}else{
		dispose();
		}
	}
}