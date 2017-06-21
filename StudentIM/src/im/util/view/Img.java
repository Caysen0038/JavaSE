package im.util.view;
import java.awt.Image;

import javax.swing.*;
public class Img extends JLabel{
	/**
	 * 得到一个内含图标的泛用型标签组件
	 */
	private static final long serialVersionUID = 3836751169485366088L;
	private Img(){}
	public static JLabel getImg(String path,int x,int y,int width,int height){
		/**
		 * @param path 图标路径
		 */
		JLabel img=new JLabel();
		ImageIcon icon=new ImageIcon(path);
		icon.setImage(icon.getImage().getScaledInstance(width, height,Image.SCALE_REPLICATE));
		img.setIcon(icon);
		img.setBounds(x,y,width,height);
		return img;
	}
}
