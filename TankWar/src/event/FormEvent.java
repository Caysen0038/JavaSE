package event;

import java.awt.event.WindowAdapter;

public class FormEvent extends WindowAdapter{
	public void windowClosing(java.awt.event.WindowEvent e){
		System.out.println("�رմ���");
		System.gc();
		System.exit(1);
	}
	public void windowOpened(java.awt.event.WindowEvent e){
		System.out.println("�򿪴���");
	}
}
