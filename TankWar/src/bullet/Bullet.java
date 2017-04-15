package bullet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import form.TankWar;
public class Bullet extends JPanel implements Runnable{
	private JLabel bullet;
	private int x,y;
	private long time=70l;
	private int speed=20;
	private int angle;
	private TankWar war;
	private String kind;
	private int num;
	private boolean clean=false;
	public Bullet(int x,int y,int angle,String kind,TankWar war){
		this.war=war;
		this.setLayout(null);
		this.x=x;
		this.y=y;
		this.kind=kind;
		if("player".equals(kind)){
			num=10;
		}else{
			num=11;
		}
		this.angle=angle;
		bullet=new JLabel();
		bullet.setSize(10,10);
		bullet.setBackground(Color.WHITE);
		this.add(bullet);
		this.setPosition();
		new Thread(this).start();
	}
	public void run(){
		try{
			while(y>0&&y<740&&x>0&&x<940){
				if(!war.collide(x, y,num)&&!war.collide(x+10, y+10, num)){
					this.setBounds(x,y,10,10);
					if(war.collide(x,y)){
						//ײ���ж���Ҫ��д���˴����ж�����ײ��
						war.cleanWall(x,y);
						clean=true;
					}
					if(war.collide(x+10, y+10)){
						war.cleanWall(x+10, y+10);
						clean=true;
					}
					if(war.collide(x+10, y)){
						war.cleanWall(x+10, y);
						clean=true;
					}
					if(war.collide(x, y+10)){
						war.cleanWall(x, y+10);
						clean=true;
					}
					if(clean){
						break;
					}
				}else{
					break;
				}
				move();
				Thread.sleep(time);
			}
			this.setVisible(false);
			war.getGamePane().remove(this);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void setPosition(){	//���ݴ�ʱ̹�˵ķ�ת�Ƕȵ���ǹ��λ��
		if(angle==0){
			x+=20;
		}else if(angle==90){
			y+=20;
			x+=40;
		}else if(angle==180){
			y+=40;
			x+=20;
		}else if(angle==-90){
			y+=20;
		}
		this.setBounds(x,y,10,10);
	}
	public void move(){		//�ӵ��ƶ�
		switch(angle){
			case 0:y-=speed;break;
			case 90:x+=speed;break;
			case 180:y+=speed;break;
			case -90:x-=speed;break;
		}
	}
}
