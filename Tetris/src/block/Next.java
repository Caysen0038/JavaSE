package block;
import java.awt.*;
import java.util.*;
import javax.swing.*;
public class Next{		//暂定活动范围 W500 H700
	private static JPanel blocks=new JPanel();
	private	static int[][] data=new int[3][3];
	//每个方块单独构造，下降完成后遍历方块数组，利用坐标将不为null的数据复制给面板组
	public Next(){
	}
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
	public static boolean setBlock(int n){
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
	private static void setDataToO(){
		data[0][0]=1;
		data[0][1]=1;
		data[1][0]=1;
		data[1][1]=1;
	}
	private static void setDataToI(){
		data[0][0]=1;
		data[1][0]=1;
		data[2][0]=1;
	}
	private static void setDataToW(){
		data[0][1]=1;
		data[1][0]=1;
		data[1][1]=1;
		data[1][2]=1;
	}
	private static void setDataToL(){
		data[0][0]=1;
		data[1][0]=1;
		data[2][0]=1;
		data[2][1]=1;
	}
	private static void setDataToRL(){
		data[0][1]=1;
		data[1][1]=1;
		data[2][0]=1;
		data[2][1]=1;
	}
	private static void setDataToZ(){
		data[0][0]=1;
		data[0][1]=1;
		data[1][1]=1;
		data[1][2]=1;
	}
	private static void setDataToRZ(){
		data[0][1]=1;
		data[0][2]=1;
		data[1][0]=1;
		data[1][1]=1;
	}
	private static void fullBlock(){
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
}	
