package event;
import java.awt.event.*;
public class FormEvent extends WindowAdapter{
	public void windowClosing(WindowEvent e){
		System.out.println("窗口关闭");
		Runtime.getRuntime().gc();
		System.exit(1);
	}
	public void windowOpened(WindowEvent e){
		System.out.println("窗口打开");
		Runtime.getRuntime().gc();
	}
}
