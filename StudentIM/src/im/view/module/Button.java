package im.view.module;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import im.util.view.Theme;

public class Button extends View{
	
	/**
	 * 自定义按钮
	 */
	private static final long serialVersionUID = 3033156696985293312L;
	public static final int DEFAULT_WIDTH=100;
	public static final int DEFAULT_HEIGHT=40;
	private JLabel label=null;
	private int width,height;
	private Theme theme=null;
	private String info=null;
	public Button(String info){
		super(info);
		this.info=info;
		theme=Theme.getTheme();
		init();
	}
	private void init(){
		width=DEFAULT_WIDTH;
		height=DEFAULT_HEIGHT;
		this.setSize(width,height);
		this.setBackground(theme.getBgColor());
		loadLabel();
		this.addMouseListener(new MouseAdapter(){
			/**
			 * 不写入点击事件，点击时间由外部方写入
			 */
			View view=null;
			@Override
			public void mouseEntered(MouseEvent e){
				view=(View)e.getSource();
				view.setBackground(theme.getFloatColor());
				view.repaint();
			}
			@Override
			public void mousePressed(MouseEvent e){
				view.setBackground(theme.getClickBg());
				label.setForeground(theme.getFontClickColor());
				view.repaint();
			}
			@Override
			public void mouseReleased(MouseEvent e){
				view.setBackground(theme.getFloatColor());
				label.setForeground(theme.getFontColor());
				view.repaint();
			}
			@Override
			public void mouseExited(MouseEvent e){
				view.setBackground(theme.getBgColor());
				view.repaint();
			}
		});
	}
	private void loadLabel(){
		/**
		 * 加载按钮上的文字
		 */
		label=new JLabel(info);
		label.setFont(new Font(theme.getFontType(),Font.BOLD,20));
		label.setForeground(theme.getFontColor());
		label.setBounds(0,0,width,height);
		label.setHorizontalAlignment(JLabel.CENTER);
		this.add(label);
	}
	public void setText(String info){
		/**
		 * 更改按钮文字
		 */
		this.info=info;
		label.setText(info);
	}
	public void setButtonSize(int width,int height){
		/**
		 * 更改按钮大小，同时更改标签大小
		 */
		this.width=width;
		this.height=height;
		this.setSize(width,height);
		label.setBounds(0,0,width,height);
	}
	public String getText(){
		return this.info;
	}
}
