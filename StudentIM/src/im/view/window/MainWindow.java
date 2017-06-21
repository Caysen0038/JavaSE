package im.view.window;

import static im.util.view.Transparent.TRANSPARENT;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import im.entity.User;
import im.util.view.SystemIcon;
import im.util.view.Theme;
import im.util.enums.MouseEnum;
import im.view.module.*;
import im.view.module.em.*;
import im.view.service.QueryAdmin;
import im.view.service.QueryName;

public class MainWindow extends JFrame implements Runnable{
	/**
	 * �����壬���ֲο���Word�������
	 * 
	 * @param WIDTH ������
	 * @param HEIGHT ����߶�
	 * @param screenWidth ��ǰ��Ļ�Ŀ��
	 * @param screentHeight ��ǰ��Ļ�߶�
	 * @param theme ��������
	 * @param titleBar �Զ��������ģ��
	 * @param sysIcon ϵͳ����ģ��
	 * @param layered �ֲ�����
	 * @param content ��ͨ�������൱�ڷֲ���������ײ�
	 * @param adminView ��ʾ��ǰ�û���view���
	 * @param dialog �Զ���Ի���ģ��
	 * @param menuBar �Զ���˵���
	 * @param menu �˵�ѡ���飬������menuBar
	 * @param menuLabel �˵����е�ѡ���ǩ��ÿһ����ǩ��Ӧ������һ��menu
	 * @param em �û��༭ģ���飨Edit Module��
	 * @param edit �û�����༭��
	 * @param fieldMenu �ļ��˵��µ�ģ�飬������edit
	 * @param tab �༭���еĹ��ܱ�ǩ����������edit
	 * @param operate �༭���е��û���������������edit
	 * @param stateBar �༭���е�״̬����������edit
	 * @param tabCase ָ��emģ���е�tabģ�飬������tab��
	 * @param opCase ָ��emģ���е�edit�༭ģ�飬������operate��
	 * @param stateCase ָ��emģ���е�stateBarģ�飬������state��
	 * 
	 * ����Ϊ�������
	 * @param menuCache ���浱ǰ������Ĳ˵�
	 * @param move ������ק����ʹ�õĻ���
	 */
	private static MainWindow window=new MainWindow();
	private static final long serialVersionUID = -7010112739825966043L;
	public static final int WIDTH=1400;
	public static final int HEIGHT=950;
	private int x=0;
	private int y=0;
	public int screenWidth;
	public int screenHeight;
	private Theme theme=null;
	private Module titleBar=null;
	private SystemIcon sysIcon=null;
	private JLayeredPane layered=null;
	private Container content=null;
	private User user=null;
	private View userView=null;
	private Point move=null;
	private Module menuBar=null;
	private EM[] em=null;
	private View[] menu=null;
	private View edit=null;
	private Module fileMenu=null;
	private Module tab=null,operate=null,stateBar=null;
	private Module tabModule=null,opModule=null,stateModule=null;
	private JLabel[] menuLabel=null;
	private View menuCache=null;
	public static void main(String[] args){
		MainWindow.getWindow().init();
	}
	public static MainWindow getWindow(){
		return window;
	}
	private MainWindow(){
		
	}
	public void run(){
		
	}
	public void init(){
		theme=Theme.getTheme();
		screenWidth=((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
		screenHeight=((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);
		x=(screenWidth-WIDTH)>>1;
		y=(screenHeight-HEIGHT)>>1-30;
		this.setTitle("ѧ����Ϣ����ϵͳ");
		this.setResizable(false);
		this.setBounds(x,y,WIDTH,HEIGHT);
		this.setUndecorated(true);
		this.setIconImage(new ImageIcon("sources/img/logo.png").getImage());
		layered=this.getLayeredPane();
		layered.setLayout(null);
		content=this.getContentPane();
		content.setLayout(null);
		//������ק�¼�
		loadDragEvent();
		//ϵͳ����ͼ��
		loadSysIcon();
		//������
		loadTitleBar();
		//��ǰ�û���ʾ
		loadUserView();
		//�˵������˵����ļ��ػ�����༭��һ�����
		loadMenuBar();
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	private void loadTitleBar(){
		titleBar=new TitleBarModule(this,"",WIDTH,true);
		layered.add(titleBar,new Integer(600));
	}

	private void loadUserView(){
		user=User.getUser();
		String name=null;
		if(!user.getType().equals("admin")){
			name=QueryName.getQuery().query(user.getType(),user.getId());
		}else{
			String temp=QueryAdmin.getQuery().queryById(user.getId())
					.getField("teacher_id").getValue();
			name=QueryName.getQuery().query("teacher",temp);
		}
		String info=user.getTypeMean()+":"+name;
		userView=new View("admin");
		JLabel text=new JLabel(info);
		text.setBackground(TRANSPARENT);
		Font font=new Font(theme.getFontType(),Font.PLAIN,22);
		text.setFont(font);
		text.setForeground(theme.getFontColor());
		int l=(int)((info.length()*2)*11.2);
		int xtemp=WIDTH-100-l-((TitleBarModule)titleBar).getButtonWidth();
		userView.setBounds(xtemp,0,l,titleBar.getHeight());
		text.setBounds(0,0,userView.getWidth(),userView.getHeight());
		userView.add(text);
		userView.setBackground(TRANSPARENT);
		layered.add(userView,new Integer(700));
	}
	private void loadMenuBar(){
		loadEM();
		menuBar=new Module();
		menuBar.setBounds(0,titleBar.getHeight(),WIDTH,60);
		menuBar.setBackground(theme.getBgColor());
		layered.add(menuBar,new Integer(400));
		loadMenu();
	}
	private void loadEM(){
		String type=User.getUser().getType();
		int count=type.equals("student")?2:(type.equals("teacher")?5:7);
		em=new EM[count];
		for(int i=0;i<count;i++){
			em[i]=EMFactory.getEM(i);
		}
	}
	private void loadMenu(){
		menu=new View[em.length];
		menuLabel=new JLabel[em.length];
		Font font=new Font(theme.getFontType(),Font.BOLD,26);
		int xtemp=0;
		int h=menuBar.getHeight();
		for(int i=0,n=menu.length;i<n;i++){
			int w=(int)(em[i].getName().length()*24);
			menu[i]=new View(String.valueOf(i));
			menu[i].setBounds(xtemp,0,w,h);
			menuLabel[i]=new JLabel(em[i].getName());
			menuLabel[i].setFont(font);
			menuLabel[i].setForeground(theme.getFontColor());
			menuLabel[i].setBounds(0,0,w,h);
			menu[i].add(menuLabel[i]);
			menu[i].setBackground(theme.getBgColor());
			menu[i].addMouseListener(new MouseAdapter(){
				/**
				 * @param n ��������¼����������
				 */
				View view=null;
				int index;
				@Override
				public void mouseEntered(MouseEvent e){
					/**
					 * ��������������ʱ����
					 */
					view=(View)e.getSource();
					/**
					 * ��������˵���ǩ���ͻ���ʾ��˵��µĹ��ܱ�ǩ
					 */
					loadTab(Integer.parseInt(view.getType()));
					if(view.getState()==MouseEnum.CLICKED.getState()){
						/**
						 * �ж��¼������״̬�Ƿ�Ϊclick
						 */
						return;
					}
					/**
					 * �����¼����������ɫΪ�������������ʱ�������ɫ
					 */
					index=Integer.parseInt(view.getType());
					setMenuBg(index,MouseEnum.ENTERED);
				}
				@Override
				public void mousePressed(MouseEvent e){
					/**
					 * ��������ʱ����
					 * ��Ϊ֮ǰ������ʱ�Ѿ�����tabģ����ʾ�����Ե������Ҫ��һ�θ���
					 */
					if(menuCache!=null){
						/**
						 * @param ������һ��������Ĳ˵����������������¼���
						 *		 ��һ������ĵ��״̬Ӧ���ͷ�
						 */
						int temp=Integer.parseInt(menuCache.getType());
						setMenuBg(temp,MouseEnum.EXITED);
					}
					/**
					 * ���õ�ǰ������������ʾ��ɫ��ͬʱ���浱ǰ��������������
					 */
					setMenuBg(index,MouseEnum.CLICKED);
					menuCache=view;
					loadOperate(index);
					loadStateBar(index);
				}
				@Override
				public void mouseExited(MouseEvent e){
					/**
					 * ����Ƴ����ʱ����
					 * ���Ȼ�ԭtabΪ��ǰ������Ĳ˵��µ�tab
					 */
					loadTab(Integer.parseInt(menuCache.getType()));
					if(view.getState()!=MouseEnum.CLICKED.getState()){
						/**
						 * ����������״̬���Ǳ�������˳����ͻ�ԭ�䱳����ɫ
						 */
						setMenuBg(index,MouseEnum.EXITED);
					}
				}
			});
			menuBar.add(menu[i]);
			xtemp+=w;
		}
		/**
		 * ���������Ĭ�ϵĲ�����Ϊ���1��ģ�飬��"������Ϣ"����ģ��
		 */
		menu[1].setState(MouseEnum.CLICKED.getState());
		menuCache=menu[1];
		loadEdit();
		menuCache=menu[1];
		menuLabel[1].setForeground(theme.getFontClickColor());
		menu[1].setBackground(theme.getClickBg());
	}
	private void setMenuBg(int index,MouseEnum state){
		/**
		 * ���ݷ����ĸ���ö��״̬��Ӧ������ͬ��Ϊ��ͬʱ���ö�Ӧ���Ϊ��Ӧ״̬
		 */
		switch(state){
			case NONE:break;
			case ENTERED:
				menu[index].setState(MouseEnum.ENTERED.getState());
				menu[index].setBackground(theme.getFloatColor());
				break;
			case PRESSED:
				/**
				 * ��갴���������״̬ʶ��Ϊͳһ��Ϊ��ִ��ͬһ����
				 */
			case CLICKED:
				menu[index].setState(MouseEnum.CLICKED.getState());
				menuCache=menu[index];
				menuLabel[index].setForeground(theme.getFontClickColor());
				menu[index].setBackground(Color.WHITE);
				break;
			case RELEASED:break;
			case EXITED:
				menu[index].setState(MouseEnum.EXITED.getState());
				menuLabel[index].setForeground(theme.getFontColor());
				menu[index].setBackground(theme.getBgColor());
				break;
				default:;
		}
	}
	private void loadEdit(){
		/**
		 * ���ر༭�����������ع��ܱ�ǩ����������״̬�����ļ��˵�ѡ��
		 * ����Ӧ�ú�Ĭ�ϱ༭ģ��Ϊ���1��ģ�飬��"������Ϣ"����ģ��
		 */
		edit=new View("edit");
		loadTab(1);
		loadOperate(1);
		loadStateBar(1);
		loadFileMenu();
		edit.setBounds(0,menuBar.getY()+menuBar.getHeight(),WIDTH,850);
		content.add(edit);
	}
	private void loadTab(int i){
		if(i==0){
			layered.remove(fileMenu);
			layered.add(fileMenu,new Integer(600));
			((FileEM)em[0]).display();
			repaint();
		}else{
			if(tab==null){
				/**
				 * ���tabģ��Ϊnull�����ʼ��
				 */
				tab=new Module();
				tab.setBounds(0,0,WIDTH,120);
				tab.addMouseListener(new MouseAdapter(){
					@Override
					public void mouseClicked(MouseEvent e){
						/**
						 * ���û�������ܱ�ǩ����Ĺ���ѡ��󣬱�ʾ�û���Ҫʹ���书��
						 * ��������״̬������Ϊ���Ӧ��ģ��
						 */
						try{
							/**
							 * ������ʾ��fieldMenu�������п϶�����Ҫ��ʾ�������Լ������Ƴ���������ֿ�ָ����Ϊ��������
							 * ���Ȼ��tabModuleָ���em��tab��ʶ��������Ϊ���״̬
							 * Ȼ�������ļ��˵�Ϊ�˳�״̬�����������Ƴ�
							 */
							int n=Integer.parseInt(tabModule.getType());
							setMenuBg(n,MouseEnum.CLICKED);
							setMenuBg(0,MouseEnum.EXITED);
							((FileEM)em[0]).hide();
							repaint();
						}catch(NullPointerException e1){
							//���쳣���ò�׽
						}catch(Exception e1){
							e1.printStackTrace();
						}
						int n=Integer.parseInt(menuCache.getType());
						if(n==0){
							/**
							 * ���Ϊ0����ʾΪ�ļ��˵��������Ĳ�������״̬�����˳��¼�
							 */
							return;
						}
						if(operate!=em[n].getEditModule()){
							/*
							 * �жϵ�ǰ�������Ƿ�Ϊ����Ҫ���ĵ�Ŀ�������
							 * ��������״̬����ʶͳһ������ʱ��һ����ģ�ֻ���ж�һ������
							 */
							loadOperate(n);
							loadStateBar(n);
						}
					}
				});
				edit.add(tab);
			}
			/**
			 * ��tabMenuָ��ָ����emģ��
			 * tab��ɾ��֮ǰ���������������Ϊ��ȷ��ɾ����һ��������ֱ��ɾ��ȫ��(ɾ��ȫ��Ҳֻ��ɾ��һ��)
			 * �����ɾ���������Ӻ�������֮ǰ�������������ǣ�����ӵ����ϣ�����������˻�ȥ��ʾ������������
			 * ������ɾ�������������tabMenu���
			 */
			tabModule=em[i].getTabModule();
			tab.removeAll();
			tab.add(tabModule);
			try{
				/**
				 * �������Ƴ�����ʱΪ�ǵ����Ϊ��ֻ��Ҫ�Ƴ����ɣ�����Ҫ������ʾ
				 */
				((FileEM)em[0]).hide();
//				layered.remove(fileMenu);
			}catch(NullPointerException e){
				//���쳣���ò�׽
			}catch(Exception e){
				e.printStackTrace();
			}
			repaint();
		}
	}
	private void loadOperate(int i){
		if(i!=0){
			/*
			 * ���i==0.����loadTab()��Ӧ�����fileMenu���˷���������Ӧ
			 */
			if(operate==null){
				operate=new Module();
				operate.setBounds(0,tab.getY()+tab.getHeight(),WIDTH,700);
				operate.addMouseListener(new MouseAdapter(){
					@Override
					public void mouseClicked(MouseEvent e){
						/**
						 * ���û��������������Ϊ�û�������༭
						 * ��������״̬�����˵���ǩ�����ܱ�ǩ������Ϊ���Ӧ��ģ��
						 */
						int index=Integer.parseInt(opModule.getType());
						try{
							/**
							 * ������ʾ��fieldMenu�������п϶�����Ҫ��ʾ�������Լ������Ƴ���������ֿ�ָ����Ϊ��������
							 * ���Ȼ��opModuleָ���em��tab��ʶ��������Ϊ���״̬
							 * Ȼ�������ļ��˵�Ϊ�˳�״̬�����������Ƴ�
							 * ͬʱ����cacheMenu��������Ϊ����˵�order�Ͳ�����order��һ��Ϊͬһ��(��type)
							 */
							setMenuBg(Integer.parseInt(menuCache.getType()),MouseEnum.EXITED);
							setMenuBg(0,MouseEnum.EXITED);
							setMenuBg(index,MouseEnum.CLICKED);
							((FileEM)em[0]).hide();
//							layered.remove(fileMenu);
							repaint();
						}catch(NullPointerException e1){
							//���쳣���ò�׽
						}catch(Exception e1){
							e1.printStackTrace();
						}
						if(index==0){
							/**
							 * ���Ϊ0����ʾΪ�ļ��˵��������Ĳ�������״̬�����˳��¼�
							 */
							return;
						}
						
						/*
						 * ��������״̬��Ϊͳһ���ģ�����������һ�θ���
						 * try-catch���Ѿ����ò˵���ǩ���˴�ֻ����Ĺ��ܱ�ǩ��
						 */
						//loadTab(n);
					}
				});
				edit.add(operate);
			}
			opModule=em[i].getEditModule();
			operate.removeAll();
			operate.add(opModule);
			repaint();
		}
	}
	private void loadStateBar(int i){
		if(i!=0){
			/*
			 * ���i==0.����loadTab()��Ӧ�����fileMenu���˷���������Ӧ
			 */
			if(stateBar==null){
				stateBar=new Module();
				stateBar.setBounds(0,operate.getY()+operate.getHeight(),WIDTH,30);
				stateBar.addMouseListener(new MouseAdapter(){
					@Override
					public void mouseClicked(MouseEvent e){
						/**
						 * ���û����״̬������Ϊ�û�������༭
						 * ��������״̬�����˵���ǩ�����ܱ�ǩ������Ϊ���Ӧ��ģ��
						 */
						int n=Integer.parseInt(opModule.getType());
						try{
							/**
							 * ������ʾ��fieldMenu�������п϶�����Ҫ��ʾ�������Լ������Ƴ���������ֿ�ָ����Ϊ��������
							 * ���Ȼ��stateModuleָ���em��tab��ʶ��������Ϊ���״̬
							 * Ȼ�������ļ��˵�Ϊ�˳�״̬�����������Ƴ�
							 * ͬʱ����cacheMenu��������Ϊ����˵�order�Ͳ�����order��һ��Ϊͬһ��(��type)
							 */
							setMenuBg(Integer.parseInt(menuCache.getType()),MouseEnum.EXITED);
							setMenuBg(0,MouseEnum.EXITED);
							setMenuBg(n,MouseEnum.CLICKED);
							((FileEM)em[0]).hide();
//							layered.remove(fileMenu);
							repaint();
						}catch(NullPointerException e1){
							//���쳣���ò�׽
						}catch(Exception e1){
							e1.printStackTrace();
						}
						if(n==0){
							/**
							 * ���Ϊ0����ʾΪ�ļ��˵��������Ĳ�������״̬�����˳��¼�
							 */
							return;
						}
						
						/*
						 * ��������״̬��Ϊͳһ���ģ�����������һ�θ���
						 * try-catch���Ѿ����ò˵���ǩ���˴�ֻ����Ĺ��ܱ�ǩ��
						 */
						//loadTab(n);
					}
				});
				edit.add(stateBar);
			}
			stateModule=em[i].getStateBarModule();
			stateBar.removeAll();
			stateBar.add(stateModule);
			repaint();
		}
	}
	private void loadFileMenu(){
		/**
		 * �ļ��˵�����ģ�飬��ģ����ʾ�Ƚ����⡣�赥������
		 */
		fileMenu=((FileEM)em[0]).getMenu();
		layered.add(fileMenu,new Integer(600));
	}
	public void hideFileMenu(){
		
	}
	private void loadSysIcon(){
		/**
		 * ϵͳ����
		 */
		sysIcon=new SystemIcon("sources/img/sysicon.png");
		sysIcon.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(e.getClickCount()==2){
					setExtendedState(Frame.NORMAL);
					setVisible(true);
				}
			}
		});
	}
	private void loadDragEvent(){
		/**
		 * �����ק�����ƶ��¼�
		 */
		this.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				/**
				 * �ɼ���갴��ʱ��������Ϊ����
				 */
				move=new Point(e.getX(),e.getY());
			}
		});
		this.addMouseMotionListener(new MouseMotionAdapter(){
			Point framePoint=null;
			@Override
			public void mouseDragged(MouseEvent e){
				/**
				 * �϶�����
				 * ��moveΪ���㣬��������ƶ�������󴰿�������Ӧ�ƶ���������ֻ�ص�����λ��
				 */
				framePoint=getLocation();
				int xtemp=(int)(framePoint.getX()+e.getX()-move.getX());
				int ytemp=(int)(framePoint.getY()+e.getY()-move.getY());
				setLocation(xtemp,ytemp);
			}
		});
	}
}
