package form;
import java.awt.*;
import block.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import block.BlockGroup;
import event.*;
public class Form implements Runnable{
	private  JFrame main;
	private  JPanel block;
	private  JPanel gamePane;
	private  JPanel scorePane;
	private  JPanel nextPane;
	private  JPanel memoryPane;
	private  JLabel freeLabel;
	private  JLabel scoreText;
	private  JPanel nextBlock;
	private  int x=200,y=0;
	private  JPanel blockPane;
	private  int[][] coor=new int[14][10];
	private  long speed=1000;
	private  boolean running=true;
	private  boolean run=true;
	private  int score=0;
	private  int nextCode;
	public Form(){
		main=new JFrame("B-2俄罗斯方块 Tetris  version 1.0");
		for(int i=0;i<coor.length;i++){
			for(int j=0;j<coor[i].length;j++){
				coor[i][j]=0;
			}
		}
		setForm();
		autoDown();
	}
	public synchronized void run(){
		try{
			while(run){
				if(BlockGroup.getFootSide()*50+y>=700){
					ensure();	
				}else{
					try{
						Thread.sleep(speed);
						if(!collide()&&BlockGroup.getFootSide()*50+y<700){
							y+=50;
							block.setBounds(x,y,150,150);
						}else{
							ensure();
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public  void setForm(){		//整个窗体
		main.setSize(800,800);
		main.setLocation(600, 100);
		main.setLayout(null);
		main.addWindowListener(new FormEvent());
		main.addKeyListener(new KeyEvent());
		setGameForm();
		main.add(gamePane);
		main.setVisible(true);
	}
	public  void setGameForm(){		//整个游戏面板，包含计分面板，next提示面板，当前游戏进行面板
		gamePane=new JPanel();
		gamePane.setBackground(new Color(111,186,115,255));
		gamePane.addKeyListener(new KeyEvent());
		gamePane.setBounds(30,20,700,700);
		gamePane.setLayout(null);
		block=BlockGroup.getBlock(getNextCode());
		block.setBounds(x,y,150,150);
		gamePane.add(block);
		setBlockPane();
		setScorePane();
		setNextPane();
		setMemoryPane();
		gamePane.add(memoryPane);
		gamePane.add(nextPane);
		gamePane.add(scorePane);
;		gamePane.add(blockPane);
	}
	public  void setBlockPane(){	//当前游戏活动面板
		blockPane=new JPanel();
		blockPane.setLayout(new GridLayout(14,10,0,0));
		blockPane.setBounds(0,0,500,700);
		blockPane.setBackground(new Color(255,186,115,255));
	}
	public  void setScorePane(){	//计分面板
		scorePane=new JPanel();
		scorePane.setLayout(null);
		scorePane.setBounds(550,300,120,70);
		scorePane.setBackground(new Color(255,255,255,255));
		scoreText=new JLabel();
		scoreText.setText(String.valueOf(score));
		scoreText.setBounds(0,0,120,70);
		scorePane.add(scoreText);
	}
	public  void setNextPane(){		//next提示面板
		nextPane=new JPanel();
		nextPane.setLayout(null);
		nextPane.setBounds(520,50,170,200);
		nextPane.setBackground(new Color(213,13,23,255));
		nextCode=getNextCode();
		nextBlock=Next.getBlock(nextCode);
		nextBlock.setBounds(10,10,150,150);
		nextPane.add(nextBlock);
	}
	public  void setMemoryPane(){	//临时使用，jvm内存面板
		memoryPane=new JPanel();
		memoryPane.setLayout(null);
		memoryPane.setBounds(550,500,120,50);
		memoryPane.setBackground(new Color(213,132,23,255));
		freeLabel=new JLabel();
		freeLabel.setText(String.valueOf(Runtime.getRuntime().freeMemory()));
		freeLabel.setBounds(0,0,120,50);
		memoryPane.add(freeLabel);
	}
	public  void autoDown(){		//自动下降
		while(running){
			if(run){
				System.out.println("自动下降");
				if(BlockGroup.getFootSide()*50+y>=700){
					ensure();	
				}else{
					try{
						Thread.sleep(speed);
						if(!collide()&&BlockGroup.getFootSide()*50+y<700){
							System.out.println("自动下降一格");
							y+=50;
							block.setBounds(x,y,150,150);
						}else{
							ensure();
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}else{
				System.out.println("停止自动下降");
			}
		}
	}
	public  boolean ensure(){		//确定block位置，保存,判断游戏是否结束/生成新block
		run=false;	//暂停自动下降
		System.out.println("确定block位置");
		saveBlock();
		clean();
		freeLabel.setText(String.valueOf(Runtime.getRuntime().freeMemory()));
		if(checkOver()){
			running=false;
			JOptionPane.showMessageDialog(null, "游戏结束", "提示",JOptionPane.WARNING_MESSAGE);
			return false;
		}else{
			Runtime.getRuntime().gc();
			y=0;
			x=200;
			block=BlockGroup.getBlock(nextCode);
			nextCode=getNextCode();
			nextBlock=Next.getBlock(nextCode);
			nextBlock.setBounds(10,10,150,150);
			nextPane.updateUI();
			block.setBounds(x,y,150,150);
			gamePane.validate();
			gamePane.repaint();
			run=true;	//启动自动下降
			return true;
		}
	}
	public  void clean(){		//判定需清除的row
		System.out.println("判断满行");
		for(int i=coor.length-1;i>=0;i--){
			boolean clean=true;
			for(int j=0;j<coor[i].length;j++){
				if(coor[i][j]!=1){
					clean=false;
					break;
				}
			}
			if(clean){
				cleanRow(i++);
			}
		}
		paint();
	}
	public  void cleanRow(int row){		//清除指定row
		System.out.println("清除"+row+"行");
		for(int i=row;i>=0;i--){
			if(i==0){
				for(int j=0;j<coor[i].length;j++){
					coor[i][j]=0;
				}
			}else{
				for(int j=0;j<coor[i].length;j++){
					coor[i][j]=coor[i-1][j];
				}
			}
		}
		scoreText.setText(String.valueOf(++score));
	}
	public  boolean collide(){		//判定下降是否有阻碍物
		System.out.println("判断是否有障碍物");
		boolean judge=false;
		int[][] blockEdge=BlockGroup.getFootEdge();
		int[] ytemp=new int[blockEdge.length];
		int[] xtemp=new int[blockEdge.length];
		for(int i=0;i<blockEdge.length;i++){
			ytemp[i]=y/50+blockEdge[i][1];
			xtemp[i]=x/50+blockEdge[i][0];
		}
		for(int i=0;i<ytemp.length;i++){
			if(ytemp[i]<coor.length-1){
				if(coor[ytemp[i]+1][xtemp[i]]==1){
					judge=true;
					break;
				}
			}else if(ytemp[i]==coor.length-1){
				judge=true;
				break;
			}
		}
		return judge;
	}
	public  void fall(){		//立即下降
		run=false;	//下降同时暂停自动下降
		while(!collide()&&BlockGroup.getFootSide()*50+y<700){
				//在下降同时停止自动下降
			y+=50;
		}
		run=true;
		ensure();
	}
	public  void saveBlock(){	//保存block到coor
		System.out.println("保存block");
		int[][] point=BlockGroup.getCoor();
		for(int i=0;i<point.length;i++){
			int xtemp=point[i][0]+x/50;
			int ytemp=point[i][1]+y/50;
			if(ytemp<14){
				coor[ytemp][xtemp]=1;
			}
		}
		for(int i=0;i<coor.length;i++){
			for(int j=0;j<coor[i].length;j++){
				if(coor[i][j]!=1){
					coor[i][j]=0;
				}
			}
		}
		paint();
	}
	public  void paint(){		//根据coor绘制pane
		System.out.println("重绘");
		blockPane.removeAll();
		for(int i=0;i<coor.length;i++){
			for(int j=0;j<coor[i].length;j++){
				if(coor[i][j]==1){
					JPanel t=new JPanel();
					t.setSize(50,50);
					t.setBackground(new Color(123,237,12,255));
					t.setBorder(BorderFactory.createLineBorder(Color.BLUE));
					blockPane.add(t);
					blockPane.repaint();
				}else{
					blockPane.add(new JLabel());
				}
			}
		}
		blockPane.repaint();
		blockPane.revalidate();
		//blockPane.validate();
		blockPane.updateUI();
		//blockPane.repaint();
	}
	public  boolean checkOver(){	//检查游戏是否结束
		System.out.println("检查游戏是否结束");
		boolean over=false;
		for(int i=0;i<coor[0].length;i++){
			if(coor[0][i]==1){
				over=true;
			}
		}
		return over;
	}
	public  int[][] getCoor(){
		return coor;
	}
	public  int getNextCode(){
		Random ran=new Random();
		int n=ran.nextInt(7);
		return n;
	}
	public boolean collideLeft(){
		int[][] edge=BlockGroup.getLeftEdge();
		boolean judge=false;
		for(int i=0;i<edge.length;i++){
			int xtemp=x/50+edge[i][0];
			int ytemp=y/50+edge[i][1];
			if(coor[ytemp][xtemp-1]==1){
				judge=true;
				break;
			}
		}
		return judge;
	}
	public boolean collideRight(){
		int[][] edge=BlockGroup.getRightEdge();
		boolean judge=false;
		for(int i=0;i<edge.length;i++){
			int xtemp=x/50+edge[i][0];
			int ytemp=y/50+edge[i][1];
			if(coor[ytemp][xtemp+1]==1){
				judge=true;
				break;
			}
		}
		return judge;
	}
	//游戏窗口监听事件内部类
	private  class KeyEvent extends KeyAdapter{
		public void keyPressed(java.awt.event.KeyEvent e){
			if(e.getKeyCode()==37&&BlockGroup.getLeftSide()*50+x>=50){
				if(running&&!collideLeft()){
					System.out.println("左移");
					x-=50;
				}
			}else if(e.getKeyCode()==38&&BlockGroup.getFootSide()*50+y<=650){
				if(!collide()&&running){
					System.out.println("下降一格");
					y+=50;
				}
			}else if(e.getKeyCode()==39&&BlockGroup.getRightSide()*50+x<=450){
				if(running&&!collideRight()){
					System.out.println("右移");
					x+=50;
				}
			}else if(e.getKeyCode()==40&&BlockGroup.getFootSide()*50+y<=650){
				System.out.println("下降到底");
					fall();
			}
			if(e.getKeyCode()==32&&y<=500&&running){
				System.out.println("变形");
				block=BlockGroup.left();
				if(x+BlockGroup.getRightSide()*50>500){
					x-=x+BlockGroup.getRightSide()*50-500;
				}else if(x<0){
					x+=50;
				}
				gamePane.updateUI();
			}
			block.setBounds(x,y,150,150);
		}
	}
}

