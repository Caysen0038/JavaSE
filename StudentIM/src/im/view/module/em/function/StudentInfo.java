package im.view.module.em.function;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import im.util.view.Img;
import im.view.module.IMButton;
import im.view.module.Module;
import im.view.module.em.EM;
import im.view.module.team.StudentOP;

public class StudentInfo extends IMButton{
	/**
	 * 学生信息
	 */
	private static final long serialVersionUID = 6723346156921669910L;
	private EM em=null;
	private Module edit=null;
	private static StudentInfo function=new StudentInfo();
	
	private StudentInfo(){
		/**
		 * 采用单例模式，不需要通过构造实例化，所以设为private
		 */
	}
	private StudentInfo(EM em){
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
		this.add(Img.getImg("sources/img/studentInfo.png", 0, 0,EM.ICON_WIDTH, EM.ICON_HEIGHT));
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
		/**
		 * @class StudentOP
		 * @author 张亚
		 */
		edit.add(new StudentOP());
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
}
