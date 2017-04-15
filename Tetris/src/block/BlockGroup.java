package block;
import java.awt.*;
import java.util.*;
import javax.swing.*;
public class BlockGroup{		//暂定活动范围 W500 H700
	private static JPanel blocks=new JPanel();
	private	static int[][] data=new int[3][3];
	//每个方块单独构造，下降完成后遍历方块数组，利用坐标将不为null的数据复制给面板组
	public synchronized static JPanel getBlock(int n){
		blocks.removeAll();
		blocks.setSize(150,150);
		blocks.setLayout(new GridLayout(3,3,0,0));
		blocks.setBackground(new Color(0,0,0,0));
		for(int i=0;i<data.length;i++){
			for(int j=0;j<data[i].length;j++){
					data[i][j]=0;
			}
		}
		setBlock(n);
		return blocks;
	}
	protected static boolean setBlock(int n){
		switch(n){
			case 0:setDataToO();break;
			case 1:setDataToI();break;
			case 2:setDataToW();break;
			case 3:setDataToL();break;
			case 4:setDataToRL();break;
			case 5:setDataToZ();break;
			case 6:setDataToRZ();break;
		}
		fullBlock();
		return true;
	}
	protected static void setDataToO(){
		data[0][0]=1;
		data[0][1]=1;
		data[1][0]=1;
		data[1][1]=1;
	}
	protected static void setDataToI(){
		data[0][0]=1;
		data[1][0]=1;
		data[2][0]=1;
	}
	protected static void setDataToW(){
		data[0][1]=1;
		data[1][0]=1;
		data[1][1]=1;
		data[1][2]=1;
	}
	protected static void setDataToL(){
		data[0][0]=1;
		data[1][0]=1;
		data[2][0]=1;
		data[2][1]=1;
	}
	protected static void setDataToRL(){
		data[0][1]=1;
		data[1][1]=1;
		data[2][0]=1;
		data[2][1]=1;
	}
	protected static void setDataToZ(){
		data[0][0]=1;
		data[0][1]=1;
		data[1][1]=1;
		data[1][2]=1;
	}
	protected static void setDataToRZ(){
		data[0][1]=1;
		data[0][2]=1;
		data[1][0]=1;
		data[1][1]=1;
	}
	protected static void fullBlock(){
		for(int i=0;i<data.length;i++){
			for(int j=0;j<data[i].length;j++){
				if(data[i][j]==1){
					blocks.add(Block.getBlock());
				}else{
					blocks.add(new JLabel());
				}
			}
		}
	}
	public static JPanel left(){
		for(int i=0;i<data.length;i++){
			for(int j=i;j<3;j++){
				//交换data[i][j]和data[j][i]的坐标，类似交换x,y轴坐标,进行坐标旋转
				int temp=data[i][j];
				data[i][j]=data[j][i];
				data[j][i]=temp;
			}
		}
		for(int i=0;i<data.length;i++){		//图形翻转，达到旋转九十度效果
			for(int j=0;j<data.length/2;j++){
				int temp=data[j][i];
				data[j][i]=data[data.length-1][i];
				data[data.length-1][i]=temp;
			}
		}
		blocks.removeAll();
		fullBlock();
		return blocks;
	}
	public static int[][] getCoor(){
		int n=0;
		//取得data中所有非空元素的数量
		for(int i=0;i<data.length;i++){
			for(int j=0;j<data[i].length;j++){
				if(data[i][j]==1){
					n++;
				}
			}
		}
		int[][] point=new int[n][2];
		int foot=0;
		for(int i=0;i<data.length;i++){
			for(int j=0;j<data[i].length;j++){
				if(data[i][j]==1){
					point[foot][0]=j;
					point[foot++][1]=i;
				}
			}
		}
//		for(int i=0;i<point.length;i++){
//			System.out.println("x:"+point[i][0]+"  y:"+point[i][1]);
//		}
		return point;
	}
	public static int getFootSide(){	//获取当前图形底部的y值
		int n=3;
		boolean flag=false;
		for(int i=data.length-1;i>=0;i--){
			for(int j=0;j<data[i].length;j++){
				if(data[i][j]==1){
					flag=true;
					break;
				}
			}
			if(flag) break;
			n--;
		}
		//System.out.println("下边界"+n);
		return n;
	}
	public static int getLeftSide(){	//获取当前图形左边界的x值
		int n=0;
		boolean flag=false;
		for(int i=0;i<data.length;i++){
			for(int j=0;j<data[i].length;j++){
				if(data[j][i]==1){
					n=i;
					flag=true;
					break;
				}	
			}
			if(flag) break;
		}
		return n;
	}
	public static int getRightSide(){	//获取当前图形右边界的x值
		int n=3;
		boolean flag=false;
		for(int i=data.length-1;i>=0;i--){
			for(int j=0;j<data.length;j++){
				if(data[j][i]==1){
					flag=true;
					break;
				}
			}
			if(!flag){
				n--;
			}else{
				break;
			}
		}
		return n;
	}
	public static int getTopSide(){
		return 0;
	}
	public static int[][] getFootEdge(){
		int n=0;
		for(int i=0;i<data.length;i++){
			for(int j=0;j<data[i].length;j++){
				if(i<2){
					if(data[i][j]==1&&data[i+1][j]!=1){
						n++;
					}
				}else{
					if(data[i][j]==1){
						n++;
					}
				}
			}
		}
		int[][] edge=new int[n][2];
		int foot=0;
		for(int i=0;i<data.length;i++){
			for(int j=0;j<data[i].length;j++){
				if(i<2){
					if(data[i][j]==1&&data[i+1][j]!=1){
						edge[foot][0]=j;
						edge[foot++][1]=i;
					}
				}else{
					if(data[i][j]==1){
						edge[foot][0]=j;
						edge[foot++][1]=i;
					}
				}
			}
		}
		return edge;
	}
	public static int[][] getLeftEdge(){
		int n=0;
		for(int i=0;i<data.length;i++){
			for(int j=0;j<data[i].length;j++){
				if(data[i][j]==1){
					n++;
					break;
				}
			}
		}
		int edge[][]=new int[n][2];
		int foot=0;
		for(int i=0;i<data.length;i++){
			for(int j=0;j<data[i].length;j++){
				if(data[i][j]==1){
					edge[foot][0]=j;
					edge[foot++][1]=i;
					break;
				}
			}
		}
		return edge;
	}
	public static int[][] getRightEdge(){
		int n=0;
		for(int i=data.length-1;i>=0;i--){
			for(int j=data[i].length-1;j>=0;j--){
				if(data[i][j]==1){
					n++;
					break;
				}
			}
		}
		int edge[][]=new int[n][2];
		int foot=0;
		for(int i=data.length-1;i>=0;i--){
			for(int j=data[i].length-1;j>=0;j--){
				if(data[i][j]==1){
					edge[foot][0]=j;
					edge[foot++][1]=i;
					break;
				}
			}
		}
		return edge;
	}
}	
