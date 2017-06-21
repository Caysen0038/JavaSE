package im.view.module;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import im.util.view.Theme;
import im.view.window.MainWindow;

public class IMDialog extends JDialog{

	/**
	 * 拟定自定义对话框，未启用
	 */
	private static final long serialVersionUID = -2900145935757225085L;
	private String info=null;
	private View[] button=null;
	private JLabel label=null;
	private boolean response=false;
	private IMDialog(){
		
	}
	public IMDialog(String info){
		super(MainWindow.getWindow(),true);
		this.info=info;
	}
	public void show(){
		this.setSize(400,300);
		int x=MainWindow.getWindow().screenWidth-getWidth()>>1;
		int y=MainWindow.getWindow().screenHeight-getHeight()>>1;
		this.setLocation(x, y);
		loadLabel();
		loadButton();
		this.setVisible(true);
	}
	private void loadLabel(){
		label=new JLabel(info);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("黑体",Font.PLAIN,24));
		label.setSize(100,40);
		this.add(label);
	}
	private void loadButton(){
		Theme theme=Theme.getTheme();
		String[] temp={"确定","取消"};
		button=new View[2];
		Font font=new Font(theme.getFontType(),Font.BOLD,20);
		int w=100,h=40;
		int xtemp=getWidth()-w*2-30>>1;
		int ytemp=getHeight()-h-30;
		JLabel buttonText=null;
		for(int i=0;i<2;i++){
			buttonText=new JLabel(temp[i]);
			buttonText.setFont(font);
			buttonText.setForeground(theme.getFontColor());
			buttonText.setBounds(0,0,w,h);
			buttonText.setHorizontalAlignment(JLabel.CENTER);
			button[i]=new View(String.valueOf(i));
			button[i].setForeground(theme.getFontColor());
			button[i].setBackground(theme.getBgColor());
			button[i].setBounds(xtemp,ytemp,w,h);
			button[i].addMouseListener(new MouseAdapter(){
				View view=null;
				int index;
				@Override
				public void mouseEntered(MouseEvent e){
					view=(View)e.getSource();
					index=Integer.parseInt(view.getType());
					view.setBackground(theme.getFloatColor());
					view.repaint();
				}
				@Override
				public void mousePressed(MouseEvent e){
					view.setBackground(theme.getClickBg());
					view.repaint();
				}
				@Override
				public void mouseClicked(MouseEvent e){
						dispose();
				}
				@Override
				public void mouseReleased(MouseEvent e){
					view.setBackground(theme.getFloatColor());
					view.repaint();
				}
				@Override
				public void mouseExited(MouseEvent e){
					view.setBackground(theme.getBgColor());
					view.repaint();
				}
			});
			button[i].add(buttonText);
			this.add(button[i]);
			xtemp+=w+10;
		}
	}
}
