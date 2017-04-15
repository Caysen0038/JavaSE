package form;
import java.awt.*;
import wall.*;
import event.*;
import java.awt.event.*;
import javax.swing.*;
import wall.*;
import bullet.Bullet;

import java.util.*;
import tank.*;
public class TankWar extends Thread{
	private JFrame main;
	private JPanel gamePane;
	private Tank tank=null;
	private int x,y;
	private int count=0;
	private Wall[][] wall=new Wall[750/25][950/25];
	private int[][] map;
	private int level=1;
	private AI[] enemy=new AI[10];
	private int AIcount=0;
	private int[][] AIcoor=new int[10][2];
	public TankWar(){
		this.main=new JFrame("Tank War     -version 1.0");
		setMain();
		setGamePane();
		setAI(0,0,"enemy");
		setAI(200,0,"enemy");
		setAI(500,0,"enemy");
		setAI(700,0,"enemy");
		setAI(900,0,"enemy");
		setAI(0,700,"enemy");
		setAI(200,700,"enemy");
		setAI(700,700,"enemy");
		setAI(800,700,"enemy");
		setAI(900,700,"enemy");
		main.add(gamePane);
		this.main.setVisible(true);
		this.start();
	}
	public void run(){
		try{
			for(int i=0;i<AIcount;i++){
				enemy[i].load(this,i);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void setMain(){		//初始化窗体
		main.setBounds(500,100,1000,800);
		main.setLayout(null);
		main.addWindowListener(new FormEvent());
		main.addKeyListener(new KeyEvent());
	}
	public void setGamePane(){		//初始化游戏面板
		gamePane=new JPanel();
		gamePane.setBounds(15,0,950,750);
		gamePane.setBackground(Color.black);
		gamePane.setLayout(null);
		setPlayer();
		setWall();
		gamePane.add(tank);
		
	}
	public void setAI(int x,int y,String kind){
		enemy[AIcount++]=new AI(1,kind,x,y);
		setAIcoor(x,y,AIcount-1);
	}
	public JPanel getGamePane(){
		return this.gamePane;
	}
	public void setWall(){		//初始化游戏墙体
		map=new wall.Map().getMap(1);
		for(int i=1;i<map.length;i++){
			for(int j=1;j<map[i].length;j++){
				if(map[i][j]==1){
					wall[i-1][j-1]=new Wall(j*25-25,i*25-25);
					gamePane.add(wall[i-1][j-1]);
				}
			}
		}
	}
	public void setPlayer(){	//设置玩家坦克属性
		tank=new Tank("player",1);
		resetPlayCoor();
		tank.setSize(50,50);
		tank.setLocation(x,y);
	}
	public void resetPlayCoor(){	//初始化玩家坦克位置
		x=325;
		y=700;
	}
	public void fire(){		//开火
		gamePane.add(new Bullet(x,y,tank.getAngle(),"player",this));
	}
	public synchronized void cleanWall(int x,int y){	//清除被射击的墙体
		int xtemp=x/25;
		int ytemp=y/25;

		try{
			
			if(wall[ytemp][xtemp]!=null){
				gamePane.remove(wall[ytemp][xtemp]);
				wall[ytemp][xtemp]=null;
				gamePane.repaint();
			}
		}catch(Exception e){}
	}
	public boolean collide(int x,int y){		//检查墙体碰撞，优化合成为同一个collide
		if(wall[y/25][x/25]!=null){
			return true;
		}else{
			return false;
		}
	}
	public void setAIcoor(int x,int y,int num){
		AIcoor[num][0]=x;
		AIcoor[num][1]=y;
	}
	public synchronized boolean collide(int x,int y,int num){	//检查坦克以及子弹相撞
		int xtemp;
		int ytemp;
		switch(num){
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
				xtemp=this.x;
				ytemp=this.y;
				if(x>=0&&x<=900&&y>=0&&y<=700){
					if(x<xtemp+50&&x>=xtemp&&y>=ytemp&&y<ytemp+50){
						return true;
					}
					for(int i=0;i<AIcount;i++){
						if(i==num){
							continue;
						}
						xtemp=AIcoor[i][0];
						ytemp=AIcoor[i][1];
						if(x<xtemp+50&&x>=xtemp&&y>=ytemp&&y<ytemp+50){
							return true;
						}
					}
				}
				break;
			case 10:
				for(int i=0;i<AIcount;i++){
					xtemp=AIcoor[i][0];
					ytemp=AIcoor[i][1];
					if(x<xtemp+50&&x>=xtemp&&y>=ytemp&&y<ytemp+50){
						enemy[i].stop();
						gamePane.remove(enemy[i]);
						gamePane.repaint();
						return true;
					}
				}
				break;
			case 11:
				xtemp=this.x;
				ytemp=this.y;
				if(x<xtemp+50&&x>=xtemp&&y>=ytemp&&y<ytemp+50){
					gamePane.remove(tank);
					gamePane.repaint();
					gameOver();
					return true;
				}
				break;
			default:
				xtemp=AIcoor[0][0];
				ytemp=AIcoor[0][1];
				if(x<xtemp+50&&x>=xtemp&&y>=ytemp&&y<ytemp+50){
					return true;
				}
				break;
		}
		
		
		return false;
	}
	public int getTankX(){
		return x;
	}
	public int getTankY(){
		return y;
	}
	public void gameOver(){
		JOptionPane.showMessageDialog(null, "游戏结束", "提示",JOptionPane.WARNING_MESSAGE);
	}
	private class KeyEvent extends KeyAdapter{
		public void keyPressed(java.awt.event.KeyEvent e){
			//System.out.println(e.getKeyCode());
			switch(e.getKeyCode()){
				case 37:
					if(tank.getAngle()!=-90){		//转向
						tank.revolve(-90);
					}
					if(x-2>0&&!collide(x-2,y)&&!collide(x-2,y+47)){
						if(!collide(x-2,y,tank.getAngle())&&!collide(x-2,y+47,tank.getAngle())){
							x-=tank.getSpeed();	//左移
							tank.setLocation(x,y);
						}
					}
					break;
				case 38:
					if(tank.getAngle()!=0){		//转向
						tank.revolve(0);
					}
					if(y-2>0&&!collide(x,y-2)&&!collide(x+47,y-2)){
						if(!collide(x,y-2,tank.getAngle())&&!collide(x+47,y-2,tank.getAngle())){
							y-=tank.getSpeed();	//上移
							tank.setLocation(x,y);
						}
					}
					break;
				case 39:
					if(tank.getAngle()!=90){	//转向
						tank.revolve(90);
					}
					if(x+53<950&&!collide(x+53,y)&&!collide(x+53,y+47)){
						if(!collide(x+53,y,tank.getAngle())&&!collide(x+53,y+47,tank.getAngle())){
							x+=tank.getSpeed();	//右移
							tank.setLocation(x,y);
						}
					}
					break;
				case 40:
					if(tank.getAngle()!=1800){		//转向
						tank.revolve(180);
					}
					if(y+52<750&&!collide(x,y+52)&&!collide(x+47,y+52)){
						if(!collide(x,y+52,tank.getAngle())&&!collide(x+47,y+52,tank.getAngle())){
							y+=tank.getSpeed();	//下移
							tank.setLocation(x,y);
						}
					}
					break;
				case 32:
						fire();		//射击
						break;
			}
		}
	}
}
