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
	 * 主窗体，部分参考了Word界面设计
	 * 
	 * @param WIDTH 窗体宽度
	 * @param HEIGHT 窗体高度
	 * @param screenWidth 当前屏幕的宽度
	 * @param screentHeight 当前屏幕高度
	 * @param theme 窗体主题
	 * @param titleBar 自定义标题栏模块
	 * @param sysIcon 系统托盘模块
	 * @param layered 分层容器
	 * @param content 普通容器，相当于分层容器的最底层
	 * @param adminView 显示当前用户的view组件
	 * @param dialog 自定义对话框模块
	 * @param menuBar 自定义菜单栏
	 * @param menu 菜单选项组，包含于menuBar
	 * @param menuLabel 菜单栏中的选项标签，每一个标签对应包含于一个menu
	 * @param em 用户编辑模块组（Edit Module）
	 * @param edit 用户整体编辑区
	 * @param fieldMenu 文件菜单下的模块，包含于edit
	 * @param tab 编辑区中的功能标签栏，包含于edit
	 * @param operate 编辑区中的用户操作区，包含于edit
	 * @param stateBar 编辑区中的状态栏，包含于edit
	 * @param tabCase 指向em模块中的tab模块，包含于tab中
	 * @param opCase 指向em模块中的edit编辑模块，包含于operate中
	 * @param stateCase 指向em模块中的stateBar模块，包含于state中
	 * 
	 * 以下为缓存参数
	 * @param menuCache 缓存当前被点击的菜单
	 * @param move 缓存拖拽窗体使用的基点
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
		this.setTitle("学生信息管理系统");
		this.setResizable(false);
		this.setBounds(x,y,WIDTH,HEIGHT);
		this.setUndecorated(true);
		this.setIconImage(new ImageIcon("sources/img/logo.png").getImage());
		layered=this.getLayeredPane();
		layered.setLayout(null);
		content=this.getContentPane();
		content.setLayout(null);
		//加载拖拽事件
		loadDragEvent();
		//系统托盘图标
		loadSysIcon();
		//标题栏
		loadTitleBar();
		//当前用户显示
		loadUserView();
		//菜单栏，菜单栏的加载会关联编辑区一起加载
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
				 * @param n 发生鼠标事件组件的类型
				 */
				View view=null;
				int index;
				@Override
				public void mouseEntered(MouseEvent e){
					/**
					 * 鼠标悬浮在组件上时发生
					 */
					view=(View)e.getSource();
					/**
					 * 鼠标移至菜单标签，就会显示其菜单下的功能标签
					 */
					loadTab(Integer.parseInt(view.getType()));
					if(view.getState()==MouseEnum.CLICKED.getState()){
						/**
						 * 判断事件组件的状态是否为click
						 */
						return;
					}
					/**
					 * 设置事件组件背景颜色为主题中鼠标悬浮时的组件颜色
					 */
					index=Integer.parseInt(view.getType());
					setMenuBg(index,MouseEnum.ENTERED);
				}
				@Override
				public void mousePressed(MouseEvent e){
					/**
					 * 鼠标点击组件时发生
					 * 因为之前鼠标进入时已经更改tab模块显示，所以点击后不需要再一次更改
					 */
					if(menuCache!=null){
						/**
						 * @param 缓存上一个被点击的菜单组件，当发生点击事件后，
						 *		 上一个组件的点击状态应被释放
						 */
						int temp=Integer.parseInt(menuCache.getType());
						setMenuBg(temp,MouseEnum.EXITED);
					}
					/**
					 * 设置当前被点击组件的显示颜色，同时缓存当前被点击的组件引用
					 */
					setMenuBg(index,MouseEnum.CLICKED);
					menuCache=view;
					loadOperate(index);
					loadStateBar(index);
				}
				@Override
				public void mouseExited(MouseEvent e){
					/**
					 * 鼠标移出组件时发生
					 * 首先还原tab为当前被点击的菜单下的tab
					 */
					loadTab(Integer.parseInt(menuCache.getType()));
					if(view.getState()!=MouseEnum.CLICKED.getState()){
						/**
						 * 如果发生组件状态不是被点击后退出。就还原其背景颜色
						 */
						setMenuBg(index,MouseEnum.EXITED);
					}
				}
			});
			menuBar.add(menu[i]);
			xtemp+=w;
		}
		/**
		 * 启动软件后默认的操作区为序号1的模块，即"基本信息"管理模块
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
		 * 根据发生的各种枚举状态对应产生不同行为，同时设置对应组件为对应状态
		 */
		switch(state){
			case NONE:break;
			case ENTERED:
				menu[index].setState(MouseEnum.ENTERED.getState());
				menu[index].setBackground(theme.getFloatColor());
				break;
			case PRESSED:
				/**
				 * 鼠标按下与鼠标点击状态识别为统一行为，执行同一动作
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
		 * 加载编辑区，关联加载功能标签，操作区，状态栏和文件菜单选项
		 * 启动应用后默认编辑模块为序号1的模块，即"基本信息"管理模块
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
				 * 如果tab模块为null，则初始化
				 */
				tab=new Module();
				tab.setBounds(0,0,WIDTH,120);
				tab.addMouseListener(new MouseAdapter(){
					@Override
					public void mouseClicked(MouseEvent e){
						/**
						 * 当用户点击功能标签里面的功能选项后，表示用户想要使用其功能
						 * 操作区和状态栏更改为其对应的模块
						 */
						try{
							/**
							 * 假设显示了fieldMenu，操作中肯定不需要显示他，所以假设性移出，如果出现空指针则为正常现象
							 * 首先获得tabModule指向的em中tab标识，返回其为点击状态
							 * 然后设置文件菜单为退出状态，并假设性移出
							 */
							int n=Integer.parseInt(tabModule.getType());
							setMenuBg(n,MouseEnum.CLICKED);
							setMenuBg(0,MouseEnum.EXITED);
							((FileEM)em[0]).hide();
							repaint();
						}catch(NullPointerException e1){
							//此异常不用捕捉
						}catch(Exception e1){
							e1.printStackTrace();
						}
						int n=Integer.parseInt(menuCache.getType());
						if(n==0){
							/**
							 * 如果为0，表示为文件菜单，不更改操作区和状态栏，退出事件
							 */
							return;
						}
						if(operate!=em[n].getEditModule()){
							/*
							 * 判断当前操作区是否为即将要更改的目标操作区
							 * 操作区和状态栏标识统一，更改时会一起更改，只需判断一个即可
							 */
							loadOperate(n);
							loadStateBar(n);
						}
					}
				});
				edit.add(tab);
			}
			/**
			 * 让tabMenu指向指定的em模块
			 * tab先删除之前所包含的组件，因为不确定删除哪一个，所以直接删除全部(删除全部也只会删除一个)
			 * 如果不删除组件，添加后的组件与之前的组件会产生覆盖，后添加的在上，但是如果想退回去显示下面的组件则不能
			 * 所以先删除，再重新添加tabMenu组件
			 */
			tabModule=em[i].getTabModule();
			tab.removeAll();
			tab.add(tabModule);
			try{
				/**
				 * 假设性移出，此时为非点击行为，只需要移出即可，不需要更改显示
				 */
				((FileEM)em[0]).hide();
//				layered.remove(fileMenu);
			}catch(NullPointerException e){
				//此异常不用捕捉
			}catch(Exception e){
				e.printStackTrace();
			}
			repaint();
		}
	}
	private void loadOperate(int i){
		if(i!=0){
			/*
			 * 如果i==0.则又loadTab()响应其加载fileMenu，此方法不用响应
			 */
			if(operate==null){
				operate=new Module();
				operate.setBounds(0,tab.getY()+tab.getHeight(),WIDTH,700);
				operate.addMouseListener(new MouseAdapter(){
					@Override
					public void mouseClicked(MouseEvent e){
						/**
						 * 当用户点击操作区后，视为用户想继续编辑
						 * 操作区，状态栏，菜单标签，功能标签栏更改为其对应的模块
						 */
						int index=Integer.parseInt(opModule.getType());
						try{
							/**
							 * 假设显示了fieldMenu，操作中肯定不需要显示他，所以假设性移出，如果出现空指针则为正常现象
							 * 首先获得opModule指向的em中tab标识，返回其为点击状态
							 * 然后设置文件菜单为退出状态，并假设性移出
							 * 同时设置cacheMenu背景，因为缓存菜单order和操作区order不一定为同一个(即type)
							 */
							setMenuBg(Integer.parseInt(menuCache.getType()),MouseEnum.EXITED);
							setMenuBg(0,MouseEnum.EXITED);
							setMenuBg(index,MouseEnum.CLICKED);
							((FileEM)em[0]).hide();
//							layered.remove(fileMenu);
							repaint();
						}catch(NullPointerException e1){
							//此异常不用捕捉
						}catch(Exception e1){
							e1.printStackTrace();
						}
						if(index==0){
							/**
							 * 如果为0，表示为文件菜单，不更改操作区和状态栏，退出事件
							 */
							return;
						}
						
						/*
						 * 操作区和状态栏为统一更改，所以无需再一次更改
						 * try-catch中已经设置菜单标签，此处只需更改功能标签栏
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
			 * 如果i==0.则又loadTab()响应其加载fileMenu，此方法不用响应
			 */
			if(stateBar==null){
				stateBar=new Module();
				stateBar.setBounds(0,operate.getY()+operate.getHeight(),WIDTH,30);
				stateBar.addMouseListener(new MouseAdapter(){
					@Override
					public void mouseClicked(MouseEvent e){
						/**
						 * 当用户点击状态栏后，视为用户想继续编辑
						 * 操作区，状态栏，菜单标签，功能标签栏更改为其对应的模块
						 */
						int n=Integer.parseInt(opModule.getType());
						try{
							/**
							 * 假设显示了fieldMenu，操作中肯定不需要显示他，所以假设性移出，如果出现空指针则为正常现象
							 * 首先获得stateModule指向的em中tab标识，返回其为点击状态
							 * 然后设置文件菜单为退出状态，并假设性移出
							 * 同时设置cacheMenu背景，因为缓存菜单order和操作区order不一定为同一个(即type)
							 */
							setMenuBg(Integer.parseInt(menuCache.getType()),MouseEnum.EXITED);
							setMenuBg(0,MouseEnum.EXITED);
							setMenuBg(n,MouseEnum.CLICKED);
							((FileEM)em[0]).hide();
//							layered.remove(fileMenu);
							repaint();
						}catch(NullPointerException e1){
							//此异常不用捕捉
						}catch(Exception e1){
							e1.printStackTrace();
						}
						if(n==0){
							/**
							 * 如果为0，表示为文件菜单，不更改操作区和状态栏，退出事件
							 */
							return;
						}
						
						/*
						 * 操作区和状态栏为统一更改，所以无需再一次更改
						 * try-catch中已经设置菜单标签，此处只需更改功能标签栏
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
		 * 文件菜单功能模块，此模块显示比较特殊。需单独处理
		 */
		fileMenu=((FileEM)em[0]).getMenu();
		layered.add(fileMenu,new Integer(600));
	}
	public void hideFileMenu(){
		
	}
	private void loadSysIcon(){
		/**
		 * 系统托盘
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
		 * 鼠标拖拽窗体移动事件
		 */
		this.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				/**
				 * 采集鼠标按下时的坐标作为基点
				 */
				move=new Point(e.getX(),e.getY());
			}
		});
		this.addMouseMotionListener(new MouseMotionAdapter(){
			Point framePoint=null;
			@Override
			public void mouseDragged(MouseEvent e){
				/**
				 * 拖动窗口
				 * 以move为基点，计算鼠标移动量，随后窗口增加相应移动量，鼠标又会回到基点位置
				 */
				framePoint=getLocation();
				int xtemp=(int)(framePoint.getX()+e.getX()-move.getX());
				int ytemp=(int)(framePoint.getY()+e.getY()-move.getY());
				setLocation(xtemp,ytemp);
			}
		});
	}
}
