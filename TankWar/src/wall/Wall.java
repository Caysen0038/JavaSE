package wall;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Wall extends JPanel{
	private int width=25;
	private int height=25;
	public Wall(int x,int y){
		this.setSize(width,height);
		this.setBackground(Color.yellow);
		this.setBounds(x,y,width,height);
		this.setBorder(BorderFactory.createLineBorder(Color.cyan));
	}
}
