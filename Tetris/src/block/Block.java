package block;
import javax.swing.*;
import java.awt.*;
import java.util.*;
public class Block {
	public static JPanel block;
	public static JPanel getBlock(){
		block=new JPanel();
//		ImageIcon img=new ImageIcon("src/img/red.png");
//		img.setImage(img.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
//		JLabel temp=new JLabel(img);
//		//temp.setBounds(0,0,50,50);
//		block.add(temp);
		block.setSize(50,50);
		//block.setBounds(0,0,50,50);
		block.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		block.setBackground(new Color(123,237,12,255));
		return block;
	}
}
