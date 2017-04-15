package event;
import java.awt.event.*;
public class FormEvent extends WindowAdapter{
	public void windowClosing(WindowEvent e){
		Runtime.getRuntime().gc();
		System.exit(1);
	}
	
}
