package event;
import java.awt.event.*;
public class FormEvent extends WindowAdapter{
	public void windowClosing(WindowEvent e){
		System.out.println("���ڹر�");
		Runtime.getRuntime().gc();
		System.exit(1);
	}
	public void windowOpened(WindowEvent e){
		System.out.println("���ڴ�");
		Runtime.getRuntime().gc();
	}
}
