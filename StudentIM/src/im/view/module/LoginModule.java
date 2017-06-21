package im.view.module;

import javax.swing.*;

import im.util.view.Img;
import im.util.view.Theme;
import im.view.service.Login;
import im.view.window.MainWindow;

import java.awt.event.*;
import java.awt.*;
import java.util.*;
public class LoginModule extends Module implements Runnable{
	/**
	 * 泛用型登录模块，可安插在任何容器中
	 * 登录功能独立，不依赖外部类
	 */
	private static final long serialVersionUID = 2187828886921057738L;
	private final String[] text={"登   录","取   消"};
	private JFrame window=null;
	private Theme theme=null;
	private Rectangle r=null;
	private JTextField name=null;
	private JPasswordField pass=null;
	private JLabel cue=null;
	private View transit=null;
	private JLabel loginText=null;
	private View login=null;
	@Deprecated
	@SuppressWarnings("unused")
	private LoginModule(){
		/**
		 * 禁止使用
		 */
	}
	public LoginModule(JFrame window,Rectangle r){
		/**
		 * @param window 安置登录模块的窗体容器
		 * @param r Rectangle setBounds()中参数类型，设置模块在窗体中的位置
		 * @param theme 窗口主题
		 */
		this.window=window;
		this.r=r;
		this.theme=Theme.getTheme();
		init();
	}
	public void run(){
		/*
		 * 优先将登录过渡动画加入线程队列执行
		 */
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				changeText();
				loadTransit();
			}
		});
		/*	state==1 未登录
		 *  state==2 登录验证中
		 *  state==3 登录验证完成
		 */
		login.setState(2);
		String[] user={name.getText(),String.valueOf(pass.getPassword())};
		java.util.List<String> error=Login.getLogin().LoginCheck(user);
		login.setState(3);
		login.remove(transit);
		login.repaint();
		//将按钮文字设置为"登录"
		changeText();
		/**
		 * @param error 登录错误信息，登录验证业务只会返回登录中的错误信息，如果error为空，则表示登录成功
		 * 登录成功后用户信息会直接存入Admin实体中，实体内含静态Admin对象，采用单例模式，登录后该对象将存活于当前软件运行周期内
		 */
		if(!error.isEmpty()){
			setCueInfo(error);
		}else{
			loadMain();
		}
	}

	private void init(){
		this.setBounds(r);
		this.setBackground(Color.WHITE);
		loadText();
		loadButton();
		loadCue();
	}
	private void loadText(){
		/**
		 * @param name 用户名文本框
		 * @param pass 密码文本框
		 */
		Font font=new Font(theme.getFontType(),Font.PLAIN,20);
		name=new JTextField(10);
		pass=new JPasswordField(10);
		name.setFont(font);
		pass.setFont(font);
		name.setSize(250,45);
		pass.setSize(250,45);
		pass.setEchoChar('*');
		int xtemp=this.getWidth()-name.getWidth()>>1;
		name.setLocation(xtemp,15);
		pass.setLocation(xtemp,name.getY()+3+name.getHeight());
		this.add(name);
		this.add(pass);
	}
	private void loadCue(){
		/**
		 * @param cue 错误提示标签，显示error错误信息
		 */
		cue=new JLabel();
		cue.setLocation(10,20);
		cue.setSize(150,150);
		cue.setAutoscrolls(false);
		cue.setBackground(Color.white);
		cue.setOpaque(true);
		Font font=new Font(theme.getFontType(),Font.PLAIN,20);
		cue.setFont(font);
		cue.setForeground(Color.red);
		cue.setBorder(null);
		cue.setVerticalAlignment(JLabel.TOP);
		this.add(cue);
	}

	private void loadButton(){
		/*
		 * @param login 登录按钮
		 */
		login=new View("login");
		login.setBounds(pass.getX(),pass.getY()+pass.getHeight()+20
			,pass.getWidth(),pass.getHeight());
		loginText=new JLabel(text[0]);
		loginText.setBounds((login.getWidth()>>1)-35,0,70,login.getHeight());
		Font font=new Font("微软雅黑",Font.BOLD,22);
		loginText.setForeground(theme.getFontColor());
		loginText.setFont(font);
		loginText.setBackground(new Color(0,0,0,0));
		loginText.setBorder(null);
		login.add(loginText);
		login.setState(1);
		login.setBackground(theme.getBgColor());
		login.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e){
				if(login.getState()!=2){
					login.setBackground(theme.getFloatColor());
					login.repaint();
				}
				
			}
			@Override
			public void mouseClicked(MouseEvent e){
				/**
				 * 此监听器除了捕捉鼠标事件之后，还能覆盖Form的拖动事件，防止长按按钮去拖动窗口
				 */
				if(login.getState()!=2){
					login();
				}
			}
			@Override
			public void mouseExited(MouseEvent e){
				login.setBackground(theme.getBgColor());
				login.repaint();
			}
			
		});
		this.add(login);
	}
	@Override
	public void destory() {
		this.name=null;
		this.pass=null;
		this.cue=null;
		this.login=null;
		this.window=null;
	}
	private void loadTransit(){
		/**
		 * 过度动画
		 */
		if(transit==null){
			transit=new View("transit");
			transit.setSize(login.getHeight()-5,login.getHeight()-5);
			transit.setLocation(30,2);
			transit.add(Img.getImg("sources/img/transit.gif", 0, 0
					, transit.getWidth(), transit.getHeight()));
		}
		new Thread(new Runnable(){

			@Override
			public void run() {
				int i=0;
				while(i++<1000){
					login.repaint();
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}).start();
		login.remove(transit);
		login.add(transit);
		login.repaint();
	}
	private void changeText(){
		if(text[0].equals(loginText.getText())){
			loginText.setText(text[1]);
			return;
		}
		loginText.setText(text[0]);
	}
	public void login(){
		new Thread(this).start();
	}
	public void setCueInfo(String error){
		if(null==error||error.length()==0){
			return;
		}
		StringBuilder sb=new StringBuilder("<html>");
		sb.append(error).append("</html>");
		cue.setText(sb.toString());
	}
	public void setCueInfo(java.util.List<String> error){
		if(error.isEmpty()){
			return;
		}
		StringBuilder sb=new StringBuilder("<html>");
		Iterator<String> iter=error.iterator();
		while(iter.hasNext()){
			sb.append(iter.next()).append("<br>");
		}
		sb.append("</html>");
		cue.setText(sb.toString());
	}
	public void loadMain(){
		/*
		 * @method dispose() 释放当前窗体占用的内存资源
		 */
		window.dispose();
		//启动主窗体
		MainWindow.getWindow().init();
	}
}
