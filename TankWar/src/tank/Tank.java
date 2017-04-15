package tank;
import bullet.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
public class Tank extends JPanel{
	private int level;
	private int speed;
	private Bullet bullet;
	private String kind;
	private JLabel img;
	private ImageIcon icon;
	public int angle=0;
	public Tank(String kind,int level){
		this.kind=kind;
		this.level=level;
		if("player".equals(kind)){
			speed=5;
		}
		setImg();
		this.setLayout(null);
		this.add(img);
		this.setSize(50,50);
		this.setBackground(new Color(1,1,1,0));
	}
	public void setImg(){
		setIcon();
		img=new JLabel(icon);
		img.setBackground(new Color(2,1,1,0));
		img.setBounds(0,0,50,50);
	}
	public void setIcon(){
		icon=new ImageIcon("img/tank.png");
		icon.setImage(icon.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
	}
	public int getSpeed(){
		return this.speed;
	}
	public int getLevel(){
		return this.level;
	}
	public int getAngle(){
		return this.angle;
	}
	public void revolve(int angle){		//翻转重绘
		this.angle=angle;
		this.repaint();
	}
    protected void paintComponent(Graphics g) {		//方法重写，翻转
	    double angle = Math.toRadians(this.angle);
	    Graphics2D g2d = (Graphics2D) g;
	    // 消除锯齿
	    RenderingHints renderingHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    renderingHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	    renderingHints.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
	    g2d.setRenderingHints(renderingHints);
	    g2d.rotate(angle, getWidth()/2, getHeight()/2);  
    }
}
