package im.util.view;

import javax.swing.JLabel;

import static im.util.TimeStamp.getDateTime;
import java.awt.Font;
public class TimeBox extends JLabel implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3847118339788375434L;
	public TimeBox(){
		init();
	}
	public void init(){
		this.setSize(200,30);
		this.setFont(new Font("ºÚÌå",Font.PLAIN,18));
		new Thread(this).start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		for(;;){
			setText(getDateTime());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
