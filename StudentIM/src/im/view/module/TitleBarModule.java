package im.view.module;

import static im.util.view.Transparent.TRANSPARENT;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import im.util.view.*;
public class TitleBarModule extends Module{
	/**
	 * 自定义标题栏
	 */
	private static final long serialVersionUID = -1016818417080901246L;
	private static final int DEFAULT_HEIGHT=40;
	private JFrame window=null;
	private Theme theme=null;
	private int width=0;
	private int height=0;
	private Font font=null;
	private String title=null;
	private JLabel label=null;
	private View button=null;
	private View icon=null;
	private boolean setIcon=false;
	@Deprecated
	@SuppressWarnings("unused")
	private TitleBarModule(){
		//禁止使用，必须传入参数
	}
	public TitleBarModule(JFrame window,String title,int width,boolean setIcon){
		this(window,title,width,DEFAULT_HEIGHT,setIcon);
	}
	public TitleBarModule(JFrame window,String title,int width,int height,boolean setIcon){
		/**
		 * @param window 加载此标题栏的窗体容器
		 * @param title 需要显示的标题栏名称
		 * @param theme 主题
		 */
		
		this.window=window;
		this.width=width;
		this.height=height;
		this.title=title;
		this.setIcon=setIcon;
		init();
	}
	private void init(){
		theme=Theme.getTheme();
		this.setBounds(0,0,width,height);
		this.setBackground(theme.getBgColor());
		loadButton();
		loadTitle();
		setIcon(setIcon);
	}
	public void setIcon(boolean setIcon){
		if(setIcon){
			loadIcon();
		}else if(icon!=null){
			this.remove(icon);
			this.repaint();
		}
	}
	private void loadIcon(){
		icon=new View("icon");
		icon.setBounds(0,0,height+10,height);
		icon.setBackground(TRANSPARENT);
		icon.add(Img.getImg("sources/img/logo.png", 10, 5, icon.getHeight()-5
				, icon.getHeight()-5));
		this.add(icon);
	}
	private void loadTitle(){
		if("".equals(title)||null==title){
			return;
		}
		font=new Font(theme.getFontType(),Font.BOLD,25);
		label=new JLabel(title);
		label.setFont(font);
		label.setForeground(theme.getFontColor());
		label.setBounds(70,0,this.width-button.getWidth()-100,height);
		label.setBackground(TRANSPARENT);
		label.setBorder(null);
		this.add(label);
	}
	private void loadButton(){
		/**
		 * 关闭和最小化按钮
		 */
		button=new View("button");
		button.setBackground(new Color(0,0,0,0));
		Module min=new Module("min");
		Module close=new Module("close");
		min.setBounds(0,0,40,height);
		close.setBounds(min.getWidth()+5,0,40, height);
		min.add(Img.getImg("sources/img/min.png",0,0,40,height));
		close.add(Img.getImg("sources/img/close.png",0,0,40, height));
		min.addMouseListener(new OnMouseEvent(this));
		close.addMouseListener(new OnMouseEvent(this));
		button.add(min);
		button.add(close);
		int w=min.getWidth()+close.getWidth();
		button.setBounds(width-w-1,0,w+1,height);
		this.add(button);
	}
	@Override
	public void destory(){
		
		
	}
	private class OnMouseEvent extends MouseAdapter{
		private Module source=null;
		private JPanel bar=null;
		public OnMouseEvent(Module bar){
			this.bar=bar;
		}
		@Override
		public void mouseEntered(MouseEvent e){
			source=(Module)e.getSource();
			source.setBackground(new Color(255,0,0,150));
			repaint();
		}
		@Override
		public void mouseExited(MouseEvent e){
			source=(Module)e.getSource();
			source.setBackground(new Color(0,0,0,0));
			repaint();
		}
		@Override
		public void mousePressed(MouseEvent e){
			
		}
		@Override
		public void mouseClicked(MouseEvent e){
			source=(Module)e.getSource();
			source.setBackground(new Color(0,0,0,0));
			bar.repaint();
			if("min".equals(source.getType())){
				window.setExtendedState(JFrame.ICONIFIED);
			}else{
				System.exit(1);
			}
		}
	}
	public int getButtonWidth(){
		return button.getWidth();
	}
}
