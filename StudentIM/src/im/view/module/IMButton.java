package im.view.module;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;

import im.util.view.Img;
import im.util.view.Theme;
import im.view.module.em.EM;
@SuppressWarnings("unused")
public class IMButton extends Module{

	/**
	 * 选项卡下的功能按钮
	 */
	private static final long serialVersionUID = 7947328838463289723L;
	public static final int DEFAULT_HEIGHT=100;
	public static final int DEFAULT_WIDTH=100;
	public static final int DEFAULT_X=0;
	public static final int DEFAULT_Y=0;
	private int height,width;
	private int x,y;
	private String text=null;
	private String img=null;
	private JLabel textCase=null;
	private View view=null;
	private Theme theme=null;
	private Font font=null;
	private Color fgColor=null;
	private Color over=null;
	private Color click=null;
	public IMButton(){
		this(DEFAULT_WIDTH,DEFAULT_HEIGHT,DEFAULT_X,DEFAULT_Y,"",null);
	}
	public IMButton(int width,int height){
		this(width,height,DEFAULT_X,DEFAULT_Y,"",null);
	}
	public IMButton(int width,int height,String text){
		this(width,height,DEFAULT_X,DEFAULT_Y,text,null);
	}
	public IMButton(int width,int height,int x,int y){
		this(width,height,x,y,"",null);
	}
	public IMButton(int width,int height,int x,int y,String text,String img){
		this.width=width;
		this.height=height;
		this.x=x;
		this.y=y;
		this.text=text;
		this.img=img;
		theme=Theme.getTheme();
		init();
	}
	public IMButton(String text){
		this(DEFAULT_WIDTH,DEFAULT_HEIGHT,DEFAULT_X,DEFAULT_Y,text,null);
	}
	public void init(){
		over=new Color(200,200,200);
		click=theme.getClickBg();
		fgColor=theme.getFontColor();
		this.setBounds(x,y,width,height);
		this.setBackground(Color.BLACK);
		img="";
		this.setFocusable(false);
		this.setEnabled(false);
		this.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e){
				repaint();
			}
			@Override
			public void mousePressed(MouseEvent e){
				setBorder(BorderFactory.createLineBorder(click,4));
				repaint();
			}
			@Override
			public void mouseReleased(MouseEvent e){
				setBorder(BorderFactory.createLineBorder(over,4));
				repaint();
			}
			@Override
			public void mouseExited(MouseEvent e){
				repaint();
			}
			
		});
		loadImg();
	}
	
	private void loadImg(){
		if(img==null){
			return;
		}
		view=new View("view");
		view.setBounds(2,2,width-4,width-4);
		view.add(Img.getImg(img, 0, 0, view.getWidth(), view.getHeight()));
		this.add(view);
	}
	public void setImage(String path){
		this.img=path;
		this.remove(view);
		loadImg();
		repaint();
	}
	public void setText(String text){
		this.text=text;
		loadText();
		repaint();
	}
	private void loadText(){
		if(text==null){
			return;
		}
		fgColor=theme.getFontColor();
		textCase=new JLabel(text);
		textCase.setForeground(fgColor);
		textCase.setBounds(2,2,getWidth(),getHeight());
		font=new Font(theme.getFontType(),Font.PLAIN,16);
		textCase.setFont(font);
		this.add(textCase);
	}

	@Override
	public void setLocation(int x, int y){
		super.setLocation(x, y);
		this.x=x;
		this.y=y;
		this.setBounds(x, y, width, height);
	}
	@Override
	public void setSize(int width,int height){
		this.width=width;
		this.height=height;
		this.setBounds(x,y,width,height);
		this.remove(view);
		loadImg();
	}
	public void setTextFont(Font font){
		this.font=font;
		textCase.setFont(font);
		textCase.repaint();
	}
	public void setTextCaseSize(int width,int height){
		textCase.setBounds(2,2,width-4,height-4);
	}
	public void setFontSize(int size){
		font=new Font(font.getFamily(),font.getStyle(),size);
		textCase.setFont(font);
		textCase.repaint();
	}
	@Override
	public Font getFont(){
		return this.font;
	}
	public void setTextForeground(Color color){
		fgColor=color;
		textCase.setForeground(fgColor);
	}
}
