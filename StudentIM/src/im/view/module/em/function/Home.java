package im.view.module.em.function;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import im.entity.User;
import im.util.view.Img;
import im.util.view.Theme;
import im.util.view.TimeBox;
import im.view.module.*;
import im.view.module.IMButton;
import im.view.module.em.EM;
import im.view.service.QueryAdmin;
import im.view.service.QueryName;

public class Home extends IMButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5926791570130336769L;
	private EM em=null;
	private Module edit=null;
	private Theme theme=null;
	private TimeBox time=null;
	private static Home function=new Home();;
	@Deprecated
	private Home(){
		/**
		 * 采用单例模式，不需要通过构造实例化，所以设为private
		 */
	}
	private Home(EM em){
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
		theme=Theme.getTheme();
		this.setSize(EM.ICON_WIDTH,EM.ICON_HEIGHT);
		this.setBackground(Color.BLACK);
		this.add(Img.getImg("sources/img/home.png", 0, 0,EM.ICON_WIDTH, EM.ICON_HEIGHT));
		this.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				load();
			}
		});
	}
	private void loadEdit(){
		edit=new Module("edit");
		edit.setBounds(0, 0, EM.WIDTH, EM.EDIT_HEIGHT);
		edit.setBackground(Color.WHITE);
		edit.add(new HomeInfoTemplet());
	}
	@Override
	public void load(){
		if(edit==null){
			loadEdit();
		}
		em.getEditModule().removeAll();
		em.getEditModule().add(edit);
		em.getEditModule().repaint();
		loadTimeBox();
	}
	private void loadTimeBox(){
		if(time==null){
			time=new TimeBox();
			int xtemp=EM.WIDTH-time.getWidth()-10;
			time.setLocation(xtemp,0);
			time.setForeground(theme.getFontColor());
		}
		try{
			em.getStateBarModule().remove(time);
		}catch(Exception e){
			//假设性移出，不处理
		}
		em.getStateBarModule().add(time);
		em.getStateBarModule().repaint();
	}
	private class HomeInfoTemplet extends Module{

		/**
		 * 欢迎页显示模板
		 */
		private static final long serialVersionUID = -7513955780750127541L;
		private static final String IMG_PATH="sources/img/logo.png";
		private String path=null;
		private View img=null;
		private User user=null;
		public HomeInfoTemplet(){
			init();
		}
		private void init(){
			 user=User.getUser();
			theme=Theme.getTheme();
			this.setBounds(0,0,EM.WIDTH,EM.EDIT_HEIGHT);
			this.setBackground(theme.getFontColor());
			this.add(Img.getImg("sources/img/welcome.png", 0,0 , EM.WIDTH, EM.EDIT_HEIGHT));
			loadImg();
			loadInfo();
			
		}
		private void loadImg(){
			if(path==null||"".equals(path)){
				path=IMG_PATH;
			}
			img=new View("img");
			img.setBounds(50,50,200,200);
			img.add(Img.getImg(path, 0, 0, img.getWidth(), img.getHeight()));
			this.add(img);
		}
		private void loadInfo(){
			JLabel info=new JLabel();
			String name=null;
			if(!user.getType().equals("admin")){
				name=QueryName.getQuery().query(user.getType(),user.getId());
			}else{
				String temp=QueryAdmin.getQuery().queryById(user.getId())
						.getField("teacher_id").getValue();
				name=QueryName.getQuery().query("teacher",temp);
			}
			StringBuilder sb=new StringBuilder("<html>欢迎回来!<br/><br/>");
			sb.append(user.getTypeMean()).append(":").append(name);
			sb.append("<br/><h2>上次登录时间:").append(user.getLastTime()).append("<h2/>");
			sb.append("<html/>");
			info.setText(sb.toString());
			Font font=new Font(theme.getFontType(),Font.BOLD,30);
			info.setFont(font);
			info.setForeground(theme.getBgColor());
			info.setBounds(350,60,700,300);
			this.add(info);
		}

	}
}
