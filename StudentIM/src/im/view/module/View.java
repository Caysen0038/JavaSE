package im.view.module;
import static im.util.view.Transparent.TRANSPARENT;

import javax.swing.*;
public class View extends JPanel{
	/**
	 * ����Module������࣬ģ���е��������̳и���
	 * @param state ��ǰ����Ĺ���״̬
	 * @param type �������
	 */
	private static final long serialVersionUID = 6398543212247764099L;
	private String type=null;
	private int state=1;
	private View(){
		this.setLayout(null);
		this.setBackground(TRANSPARENT);
	}
	public View(String type){
		this();
		this.type=type;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
}
