package im.view.window;

import java.awt.*;
import java.awt.event.*;

import im.util.view.*;
import im.view.module.*;
import javax.swing.*;

public class LoginWindow extends JFrame{
	/**
	 * 登录窗体，仿PC腾讯QQ登录界面
	 */
	private static final long serialVersionUID = -8468726125855408697L;
	public static final int WIDTH=600;
	public static final int HEIGHT=450;
	private Theme theme=null;
	private int screenWidth;
	private int screenHeight;
	private int x=0;
	private int y=0;
	private Module loginView=null;
	private Module bgView=null;
	private JLayeredPane layered=null;
	private Container content=null;
	private Module titleBar=null;
	@SuppressWarnings("unused")
	private SystemIcon sysIcon=null;
	private Point move=null;
	@SuppressWarnings("unused")
	public static void main(String[] args){
		LoginWindow form=new LoginWindow();
	}
	public LoginWindow(){
		init();
	}
	/**
	 * @method init() 初始化操作
	 */
	public void init(){
		//设置窗口主题
		theme=Theme.getTheme();
		//获得当前屏幕的分辨率
		screenWidth=((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
		screenHeight=((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);
		/**
		 * x=(screenWidth-WIDTH)/2
		 * y=(screenHieght-HEIGHT)/2
		 */
		x=(screenWidth-WIDTH)>>1;
		y=(screenHeight-HEIGHT)>>1;
		/**
		 * @param layered 即JLayeredPane，获得当前JFrame的分层容器面板，用于分层叠放组件
		 * @param content 即Container，常用容器面板，相当于layered的最底层，将组件加在此容器
		 * 相当于直接JFrame.add()
		 */
		layered=this.getLayeredPane();
		content=this.getContentPane();
		this.setTitle("信息管理系统登录");
		this.setSize(WIDTH, HEIGHT);
		this.setLocation(x, y);
		this.setUndecorated(true);
		this.setResizable(false);
		this.setLayout(null);
		this.setIconImage(new ImageIcon("sources/img/logo.png").getImage());
		//窗口鼠标拖拽
		loadDragEvent();
		//标题栏
		loadTitleBar();
		//加载登录模块
		loadLogin();
		//背景
		loadBackground();
		//如果不设置此操作，关闭窗口会进程会挂入后台
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	private void loadLogin(){
		int temp=(HEIGHT>>1)+(HEIGHT>>4);
		Rectangle r=new Rectangle(0,temp,this.getWidth(),this.getHeight()-temp);
		loginView=new LoginModule(this,r);
		content.add(loginView,0);
	}
	private void loadTitleBar(){
		titleBar=new TitleBarModule(this,"",WIDTH,false);
		//标题栏位于高层
		layered.add(titleBar,new Integer(600));
	}
	private void loadBackground(){
		bgView=new Module();
		bgView.setBounds(0,0,WIDTH,(HEIGHT>>1)+(HEIGHT>>4));
		bgView.setBackground(theme.getBgColor());
		bgView.add(Img.getImg("sources/img/loginBg.png", 0, 0
				, bgView.getWidth(), bgView.getHeight()));
		content.add(bgView);
	}
	private void loadDragEvent(){
		this.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				//获得鼠标点击的坐标点作为基点
				move=new Point(e.getX(),e.getY());
			}
		});
		this.addMouseMotionListener(new MouseMotionAdapter(){
			Point framePoint=null;
			@Override
			public void mouseDragged(MouseEvent e){
				/**
				 * 拖动窗口
				 */
				framePoint=getLocation();
				int xtemp=(int)(framePoint.getX()+e.getX()-move.getX());
				int ytemp=(int)(framePoint.getY()+e.getY()-move.getY());
				setLocation(xtemp,ytemp);
			}
		});
	}
//	private void loadSystemIcon(){
//		//系统托盘图标
//		sysIcon=new SystemIcon("sources/img/close.png");
//		sysIcon.addMouseListener(new MouseAdapter(){
//			public void mouseClicked(MouseEvent e){
//				if(e.getClickCount()==2){
//					//双击系统托盘图标显示窗口
//					setExtendedState(Frame.NORMAL);
//					setVisible(true);
//				}
//			}
//		});
//	}
}
