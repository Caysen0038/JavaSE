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
public class Do05 extends JFrame implements ActionListener{
	JMenuBar jmb;
	JMenu jm1;
	JMenu jm2;
	JMenu jm3;
	JMenuItem jmi1;
	JMenuItem jmi2;

	JButton jb1;
	JButton jb2;

	JLabel jl;

	JPanel jp;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Do05 d5=new Do05();
	}
public Do05(){
	jmb=new JMenuBar();
	jm1=new JMenu("成绩");
	jm2=new JMenu("帮助");
	jm3=new JMenu("退出");
	jm3.addMouseListener(new MouseAdapter(){
		@Override
		public void mouseClicked(MouseEvent e){
			dispose();
		}

	});
	jmi1=new JMenuItem("添加");
	jmi1.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			new Do01();
//			dispose();
		}
	});
	jmi2=new JMenuItem("查询");
	jmi2.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			new Do02();
//			dispose();
		}
	});

	jb1=new JButton("添加成绩");
	jb1.setFont(new Font("Serif",Font.BOLD,20));
	jb1.setBackground(Color.green);
	jb1.setBounds(100,50,150,50);
	jb2=new JButton("查询成绩");
	jb2.setFont(new Font("Serif",Font.BOLD,20));
	jb2.setBackground(Color.green);
	jb2.setBounds(300,50,150,50);
	
	jm1.add(jmi1);
	jm1.addSeparator();
	jm1.add(jmi2);
	jmb.add(jm1);
	jmb.add(jm2);
	jmb.add(jm3);

	this.setJMenuBar(jmb);

	this.add(jb1);
	jb1.addActionListener(this);
	this.add(jb2);
	jb2.addActionListener(this);

	String pic="background.jpg";
    ImageIcon bgd = new ImageIcon(pic);
    jl= new JLabel(bgd);
    jl.setBounds(0,0,900,700);
    jp=(JPanel)this.getContentPane();
    jp.setOpaque(false);
    this.getLayeredPane().add(jl,new Integer(Integer.MIN_VALUE));

	this.setLayout(null);
	this.setTitle("成绩");
	this.setSize(900,700);
	this.setLocation(200,10);
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
	// TODO Auto-generated method stub
	if(e.getActionCommand().equals("添加成绩")){
		Do01 d1=new Do01();
//		dispose();
	}
	else{
		new Do02();
//		dispose();
	}
}
}
