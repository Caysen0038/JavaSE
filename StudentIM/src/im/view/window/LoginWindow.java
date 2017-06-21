package im.view.window;

import java.awt.*;
import java.awt.event.*;

import im.util.view.*;
import im.view.module.*;
import javax.swing.*;

public class LoginWindow extends JFrame{
	/**
	 * ��¼���壬��PC��ѶQQ��¼����
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
	 * @method init() ��ʼ������
	 */
	public void init(){
		//���ô�������
		theme=Theme.getTheme();
		//��õ�ǰ��Ļ�ķֱ���
		screenWidth=((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
		screenHeight=((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);
		/**
		 * x=(screenWidth-WIDTH)/2
		 * y=(screenHieght-HEIGHT)/2
		 */
		x=(screenWidth-WIDTH)>>1;
		y=(screenHeight-HEIGHT)>>1;
		/**
		 * @param layered ��JLayeredPane����õ�ǰJFrame�ķֲ�������壬���ڷֲ�������
		 * @param content ��Container������������壬�൱��layered����ײ㣬��������ڴ�����
		 * �൱��ֱ��JFrame.add()
		 */
		layered=this.getLayeredPane();
		content=this.getContentPane();
		this.setTitle("��Ϣ����ϵͳ��¼");
		this.setSize(WIDTH, HEIGHT);
		this.setLocation(x, y);
		this.setUndecorated(true);
		this.setResizable(false);
		this.setLayout(null);
		this.setIconImage(new ImageIcon("sources/img/logo.png").getImage());
		//���������ק
		loadDragEvent();
		//������
		loadTitleBar();
		//���ص�¼ģ��
		loadLogin();
		//����
		loadBackground();
		//��������ô˲������رմ��ڻ���̻�����̨
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
		//������λ�ڸ߲�
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
				//�����������������Ϊ����
				move=new Point(e.getX(),e.getY());
			}
		});
		this.addMouseMotionListener(new MouseMotionAdapter(){
			Point framePoint=null;
			@Override
			public void mouseDragged(MouseEvent e){
				/**
				 * �϶�����
				 */
				framePoint=getLocation();
				int xtemp=(int)(framePoint.getX()+e.getX()-move.getX());
				int ytemp=(int)(framePoint.getY()+e.getY()-move.getY());
				setLocation(xtemp,ytemp);
			}
		});
	}
//	private void loadSystemIcon(){
//		//ϵͳ����ͼ��
//		sysIcon=new SystemIcon("sources/img/close.png");
//		sysIcon.addMouseListener(new MouseAdapter(){
//			public void mouseClicked(MouseEvent e){
//				if(e.getClickCount()==2){
//					//˫��ϵͳ����ͼ����ʾ����
//					setExtendedState(Frame.NORMAL);
//					setVisible(true);
//				}
//			}
//		});
//	}
}
