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
		main=new JFrame("B-2����˹���� Tetris  version 1.0");
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
	public  void setForm(){		//��������
		main.setSize(800,800);
		main.setLocation(600, 100);
		main.setLayout(null);
		main.addWindowListener(new FormEvent());
		main.addKeyListener(new KeyEvent());
		setGameForm();
		main.add(gamePane);
		main.setVisible(true);
	}
	public  void setGameForm(){		//������Ϸ��壬�����Ʒ���壬next��ʾ��壬��ǰ��Ϸ�������
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
	public  void setBlockPane(){	//��ǰ��Ϸ����
		blockPane=new JPanel();
		blockPane.setLayout(new GridLayout(14,10,0,0));
		blockPane.setBounds(0,0,500,700);
		blockPane.setBackground(new Color(255,186,115,255));
	}
	public  void setScorePane(){	//�Ʒ����
		scorePane=new JPanel();
		scorePane.setLayout(null);
		scorePane.setBounds(550,300,120,70);
		scorePane.setBackground(new Color(255,255,255,255));
		scoreText=new JLabel();
		scoreText.setText(String.valueOf(score));
		scoreText.setBounds(0,0,120,70);
		scorePane.add(scoreText);
	}
	public  void setNextPane(){		//next��ʾ���
		nextPane=new JPanel();
		nextPane.setLayout(null);
		nextPane.setBounds(520,50,170,200);
		nextPane.setBackground(new Color(213,13,23,255));
		nextCode=getNextCode();
		nextBlock=Next.getBlock(nextCode);
		nextBlock.setBounds(10,10,150,150);
		nextPane.add(nextBlock);
	}
	public  void setMemoryPane(){	//��ʱʹ�ã�jvm�ڴ����
		memoryPane=new JPanel();
		memoryPane.setLayout(null);
		memoryPane.setBounds(550,500,120,50);
		memoryPane.setBackground(new Color(213,132,23,255));
		freeLabel=new JLabel();
		freeLabel.setText(String.valueOf(Runtime.getRuntime().freeMemory()));
		freeLabel.setBounds(0,0,120,50);
		memoryPane.add(freeLabel);
	}
	public  void autoDown(){		//�Զ��½�
		while(running){
			if(run){
				System.out.println("�Զ��½�");
				if(BlockGroup.getFootSide()*50+y>=700){
					ensure();	
				}else{
					try{
						Thread.sleep(speed);
						if(!collide()&&BlockGroup.getFootSide()*50+y<700){
							System.out.println("�Զ��½�һ��");
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
				System.out.println("ֹͣ�Զ��½�");
			}
		}
	}
	public  boolean ensure(){		//ȷ��blockλ�ã�����,�ж���Ϸ�Ƿ����/������block
		run=false;	//��ͣ�Զ��½�
		System.out.println("ȷ��blockλ��");
		saveBlock();
		clean();
		freeLabel.setText(String.valueOf(Runtime.getRuntime().freeMemory()));
		if(checkOver()){
			running=false;
			JOptionPane.showMessageDialog(null, "��Ϸ����", "��ʾ",JOptionPane.WARNING_MESSAGE);
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
			run=true;	//�����Զ��½�
			return true;
		}
	}
	public  void clean(){		//�ж��������row
		System.out.println("�ж�����");
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
	public  void cleanRow(int row){		//���ָ��row
		System.out.println("���"+row+"��");
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
	public  boolean collide(){		//�ж��½��Ƿ����谭��
		System.out.println("�ж��Ƿ����ϰ���");
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
	public  void fall(){		//�����½�
		run=false;	//�½�ͬʱ��ͣ�Զ��½�
		while(!collide()&&BlockGroup.getFootSide()*50+y<700){
				//���½�ͬʱֹͣ�Զ��½�
			y+=50;
		}
		run=true;
		ensure();
	}
	public  void saveBlock(){	//����block��coor
		System.out.println("����block");
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
	public  void paint(){		//����coor����pane
		System.out.println("�ػ�");
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
	public  boolean checkOver(){	//�����Ϸ�Ƿ����
		System.out.println("�����Ϸ�Ƿ����");
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
	//��Ϸ���ڼ����¼��ڲ���
	private  class KeyEvent extends KeyAdapter{
		public void keyPressed(java.awt.event.KeyEvent e){
			if(e.getKeyCode()==37&&BlockGroup.getLeftSide()*50+x>=50){
				if(running&&!collideLeft()){
					System.out.println("����");
					x-=50;
				}
			}else if(e.getKeyCode()==38&&BlockGroup.getFootSide()*50+y<=650){
				if(!collide()&&running){
					System.out.println("�½�һ��");
					y+=50;
				}
			}else if(e.getKeyCode()==39&&BlockGroup.getRightSide()*50+x<=450){
				if(running&&!collideRight()){
					System.out.println("����");
					x+=50;
				}
			}else if(e.getKeyCode()==40&&BlockGroup.getFootSide()*50+y<=650){
				System.out.println("�½�����");
					fall();
			}
			if(e.getKeyCode()==32&&y<=500&&running){
				System.out.println("����");
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

