package im.view.module;
import javax.swing.*;

import static im.util.view.Transparent.TRANSPARENT;

import java.awt.*;
public class Module extends JPanel{
	/**
	 * ģ���࣬����ģ����̳о��ұ���̳и�ģ�飬����������ֻ��Module
	 * @param type ģ������
	 */
	private static final long serialVersionUID = -5847732666105381226L;
	private String type=null;
	private String name=null;
	public Module(){
		this.setLayout(null);
		this.setBackground(TRANSPARENT);
	}
	public Module(boolean isDoubleBuffered){
		super(isDoubleBuffered);
		this.setLayout(null);
		this.setBackground(TRANSPARENT);
	}
	public Module(LayoutManager layout){
		super(layout);
		this.setBackground(TRANSPARENT);
	}
	public Module(LayoutManager layout, boolean isDoubleBuffered){
		super(layout,isDoubleBuffered);
		this.setBackground(TRANSPARENT);
	}
	public Module(String type){
		this();
		this.type=type;
	}
	public String getType(){
		return this.type;
	}
	public void setType(String type){
		this.type=type;
	}
	public void destory(){
		/**
		 * ����ģ��
		 */
	}
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return this.name;
	}
	public void load(){
		/**
		 * @method load() Ҫʹ�ñ��븲д
		 */
	}
}
