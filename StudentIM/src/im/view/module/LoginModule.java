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
	 * �����͵�¼ģ�飬�ɰ������κ�������
	 * ��¼���ܶ������������ⲿ��
	 */
	private static final long serialVersionUID = 2187828886921057738L;
	private final String[] text={"��   ¼","ȡ   ��"};
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
		 * ��ֹʹ��
		 */
	}
	public LoginModule(JFrame window,Rectangle r){
		/**
		 * @param window ���õ�¼ģ��Ĵ�������
		 * @param r Rectangle setBounds()�в������ͣ�����ģ���ڴ����е�λ��
		 * @param theme ��������
		 */
		this.window=window;
		this.r=r;
		this.theme=Theme.getTheme();
		init();
	}
	public void run(){
		/*
		 * ���Ƚ���¼���ɶ��������̶߳���ִ��
		 */
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				changeText();
				loadTransit();
			}
		});
		/*	state==1 δ��¼
		 *  state==2 ��¼��֤��
		 *  state==3 ��¼��֤���
		 */
		login.setState(2);
		String[] user={name.getText(),String.valueOf(pass.getPassword())};
		java.util.List<String> error=Login.getLogin().LoginCheck(user);
		login.setState(3);
		login.remove(transit);
		login.repaint();
		//����ť��������Ϊ"��¼"
		changeText();
		/**
		 * @param error ��¼������Ϣ����¼��֤ҵ��ֻ�᷵�ص�¼�еĴ�����Ϣ�����errorΪ�գ����ʾ��¼�ɹ�
		 * ��¼�ɹ����û���Ϣ��ֱ�Ӵ���Adminʵ���У�ʵ���ں���̬Admin���󣬲��õ���ģʽ����¼��ö��󽫴���ڵ�ǰ�������������
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
		 * @param name �û����ı���
		 * @param pass �����ı���
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
		 * @param cue ������ʾ��ǩ����ʾerror������Ϣ
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
		 * @param login ��¼��ť
		 */
		login=new View("login");
		login.setBounds(pass.getX(),pass.getY()+pass.getHeight()+20
			,pass.getWidth(),pass.getHeight());
		loginText=new JLabel(text[0]);
		loginText.setBounds((login.getWidth()>>1)-35,0,70,login.getHeight());
		Font font=new Font("΢���ź�",Font.BOLD,22);
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
				 * �˼��������˲�׽����¼�֮�󣬻��ܸ���Form���϶��¼�����ֹ������ťȥ�϶�����
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
		 * ���ȶ���
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
		 * @method dispose() �ͷŵ�ǰ����ռ�õ��ڴ���Դ
		 */
		window.dispose();
		//����������
		MainWindow.getWindow().init();
	}
}
