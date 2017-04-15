package tank;
import java.awt.Color;
import bullet.*;
import java.util.*;
import javax.swing.*;
import form.*;
import java.awt.*;
public class AI extends JPanel implements Runnable{
	private TankWar war;
	private int level;
	private Tank tank;
	private int x,y;
	private int width=50;
	private int height=50;
	private int speed=5;
	private String kind;
	private int angle;
	private int direction;
	private int probability;
	private int num;
	private Random ran=new Random();
	private boolean live=true;
	public AI(int level,String kind,int x,int y){
		this.level=level;
		this.kind=kind;
		this.x=x;
		this.y=y;
		probability=50;
		tank=new Tank(kind,level);
		this.setLayout(null);
		this.add(tank);
		this.setBounds(this.x,this.y,width,height);
	}
	public synchronized void load(TankWar war,int num){
		this.war=war;
		this.num=num;
		war.getGamePane().add(this);
		war.getGamePane().repaint();
		new Thread(this).start();
	}
	public void run(){
		try{
			while(live){
				if(num==2){
					System.out.println("移动判断");
				}
				if(ran.nextInt(probability)==1){
					war.getGamePane().add(new Bullet(x,y,angle,"enemy",war));
				}
				if(!goHead()){
					//System.out.println("自动转向");
					setAngle();
				}
				Thread.sleep(100);
			}
			war.setAIcoor(-50, -50, num);
			war.getGamePane().remove(this);
		}catch(Exception e){
			
		}
	}
	public void setAngle(){		//随机转向
		switch((ran.nextInt(4))+1){
			case 1:angle=-90;break;
			case 2:angle=0;break;
			case 3:angle=90;break;
			case 4:angle=180;break;
		}
		tank.revolve(angle);
	}
	public boolean goHead(){	//判断是否继续向前
		
		int turn;
		turn=ran.nextInt(20);
		boolean keep=false;
		try{
			if(num==2){
				System.out.println(x+" "+y);
			}
			if(turn()&&turn==1){
				//System.out.println("判断转向");
				angle+=90*getDirection(1,3);
				tank.revolve(angle);
			}
		
		if(num==2){
			System.out.println("角度:"+angle);
		}
		switch(angle){
			case -90:
				if(num==2)
					System.out.println(y);
				if(x-2>=0&&!war.collide(x-2, y)&&!war.collide(x-2, y+49)){
					if(!war.collide(x,y,num)&&!war.collide(x,y+50,num)){
						if(x-speed>=0){
							this.setBounds(x-=this.speed,y,width,height);
							war.setAIcoor(x, y, num);
							keep=true;
						}
					}
					
				} 
				break;
			case 0:
				if(y-2>=0&&!war.collide(x, y-2)&&!war.collide(x+49,y-2)){
					if(!war.collide(x,y,num)&&!war.collide(x+50,y,num)){
						if(y-this.speed>=0){
							this.setBounds(x,y-=this.speed,width,height);
							war.setAIcoor(x, y, num);
							keep=true;
						}
					}
					
				}
				break;
			case 90:
				if(num==2)
					System.out.println(y);
				if(x+52<950&&!war.collide(x+52, y)&&!war.collide(x+52, y+49)){
					if(!war.collide(x+50,y,num)&&!war.collide(x+50,y+50,num)){
						if(x+speed<=900){
							this.setBounds(x+=this.speed,y,width,height);
							war.setAIcoor(x, y, num);
							keep=true;
						}
					}
					
				}
				break;
			case 180:
				if(num==2)
					System.out.println(y);
				if(y+52<750&&!war.collide(x, y+52)&&!war.collide(x+49, y+52)){
					if(!war.collide(x,y+50,num)&&!war.collide(x+50,y+50,num)){
						if(y+speed<=700){
							this.setBounds(x,y+=this.speed,width,height);
							war.setAIcoor(x, y, num);
							keep=true;
						}
					}
					
				}
				break;
			default:
				if(num==2)
					System.out.println("判断失败"+x+" "+y);
				
		}
		}catch(Exception e){
			System.out.println("AI运动判断出错");
			//e.printStackTrace();
		}
		if(num==2){
			System.out.println("是否移动:"+keep);
		}
		return keep;
	}
	public boolean turn(){	//判断转向方向
		boolean sway=false;
		switch(angle){
		case -90:
			if((!war.collide(x, y+49)&&!war.collide(x+49, y+49))||(!war.collide(x, y)&&!war.collide(x+49,y))){
				sway=true;
			}
			break;
		case 0:
			if((!war.collide(x, y)&&!war.collide(x,y+49))||(!war.collide(x+49, y)&&!war.collide(x+49, y+49))){
				sway=true;
			}
			break;
		case 90:
			if((!war.collide(x, y+49)&&!war.collide(x+49, y+49))||(!war.collide(x, y)&&!war.collide(x+49,y))){
				sway=true;
			}
			break;
		case 180:
			if((!war.collide(x, y)&&!war.collide(x,y+49))||(!war.collide(x+49, y)&&!war.collide(x+49, y+49))){
				sway=true;
			}
			break;
		}
		return sway;
	}
	public int getDirection(int a,int b){
		return ran.nextInt(2)==0?a:b;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void stop(){
		System.out.println("击毁敌人");
		live=false;
	}
}
