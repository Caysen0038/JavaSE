package im.view.module.em.function;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import im.util.view.Img;
import im.view.module.IMButton;
import im.view.module.Module;
import im.view.module.em.EM;
import im.view.module.team.Do01;
import im.view.module.team.Do02;

public class ScoreInfo extends IMButton{

	/**
	 * 成绩管理页面
	 */
	private static final long serialVersionUID = 2532195197927595647L;
	private EM em=null;
	private Module edit=null;
	private static ScoreInfo function=new ScoreInfo();
	
	private ScoreInfo(){
		/**
		 * 采用单例模式，不需要通过构造实例化，所以设为private
		 */
	}
	private ScoreInfo(EM em){
		setEM(em);
		
	}
	public void setEM(EM em){
		this.em=em;
		init();
	}
	public static Module getFun(EM em){
		function.setEM(em);
		return function;
	}
	public void init(){
		super.init();
		this.setSize(EM.ICON_WIDTH,EM.ICON_HEIGHT);
		this.setBackground(Color.BLACK);
		this.add(Img.getImg("sources/img/scoreInfo.png", 0, 0,EM.ICON_WIDTH, EM.ICON_HEIGHT));
		this.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				load();
			}
		});
		
	}
	private void loadEdit(){
		edit=new Module("edit");
		edit.setBounds(0, 0, EM.WIDTH, EM.EDIT_HEIGHT);
		edit.setBackground(Color.WHITE);
		edit.add(new ScoreEdit());
		edit.repaint();
	}
	@Override
	public void load(){
		if(edit==null){
			loadEdit();
		}
		em.getEditModule().removeAll();
		em.getEditModule().add(edit);
		em.getEditModule().repaint();
	}
	private class ScoreEdit extends Module implements ActionListener{

		/**
		 * 管理页面
		 * @author 赵元钰
		 */
		private static final long serialVersionUID = 1820994278756808240L;
		JButton jb1;
		JButton jb2;

		JLabel jl;

		public ScoreEdit(){
		
		this.setSize(edit.getWidth(),edit.getHeight());
		jb1=new JButton("添加成绩");
		jb1.setFont(new Font("Serif",Font.BOLD,20));
		jb1.setBackground(Color.green);
		int x=edit.getWidth()-350>>1;
		jb1.setBounds(x,50,150,50);
		jb2=new JButton("查询成绩");
		jb2.setFont(new Font("Serif",Font.BOLD,20));
		jb2.setBackground(Color.green);
		this.setBackground(Color.WHITE);
		x+=250;
		jb2.setBounds(x,50,150,50);

		this.add(jb1);
		jb1.addActionListener(this);
		this.add(jb2);
		jb2.addActionListener(this);

		String pic="background.jpg";
	    ImageIcon bgd = new ImageIcon(pic);
	    jl= new JLabel(bgd);
	    jl.setBounds(0,0,900,700);
	    this.add(jl,new Integer(Integer.MIN_VALUE));

		this.setLayout(null);
		this.setLocation(0,0);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("添加成绩")){
			Do01 d1=new Do01();
//			dispose();
		}
		else{
			new Do02();
//			dispose();
		}
	}
	}
}
