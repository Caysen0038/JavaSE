package im.view.module;
import javax.swing.*;

import static im.util.view.Transparent.TRANSPARENT;

import java.awt.*;
public class Module extends JPanel{
	/**
	 * 模块类，所以模块均继承均且必须继承该模块，窗体加载组件只认Module
	 * @param type 模块类型
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
		 * 销毁模块
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
		 * @method load() 要使用必须覆写
		 */
	}
}
