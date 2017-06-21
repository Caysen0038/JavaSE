package im.view.module;
import static im.util.view.Transparent.TRANSPARENT;

import javax.swing.*;
public class View extends JPanel{
	/**
	 * 低于Module的组件类，模块中的组件必须继承该类
	 * @param state 当前组件的工作状态
	 * @param type 组件类型
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
