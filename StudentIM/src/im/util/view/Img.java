package im.util.view;
import java.awt.Image;

import javax.swing.*;
public class Img extends JLabel{
	/**
	 * �õ�һ���ں�ͼ��ķ����ͱ�ǩ���
	 */
	private static final long serialVersionUID = 3836751169485366088L;
	private Img(){}
	public static JLabel getImg(String path,int x,int y,int width,int height){
		/**
		 * @param path ͼ��·��
		 */
		JLabel img=new JLabel();
		ImageIcon icon=new ImageIcon(path);
		icon.setImage(icon.getImage().getScaledInstance(width, height,Image.SCALE_REPLICATE));
		img.setIcon(icon);
		img.setBounds(x,y,width,height);
		return img;
	}
}
