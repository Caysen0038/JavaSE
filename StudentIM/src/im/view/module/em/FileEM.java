package im.view.module.em;

import java.awt.event.*;

import im.util.enums.MouseEnum;
import im.util.view.*;
import im.view.module.*;
public class FileEM extends EM implements Runnable{
	/**
	 * 
	 * 此EM模块为定制类模块，泛用性不强，整个应用只会存在一个该模块
	 * 并且该模块下的所有功能模块均为定制模块，无泛用性
	 * 
	 * @param tab 功能菜单标签，供用户选择模块下各种功能
	 * @param orerate 主要编辑模块，完成各种功能操作
	 * @param stateBar 模块中的状态，因为模块功能不同，状态栏实现不同，所以归属于模块管理，不纳入窗体
	 */
	private static final long serialVersionUID = 8878792872361381670L;
	public static final int OPTION_HEIGHT=50;
	public static final int OPTION_WIDTH=400;
	private Module menu=null;
	private View group=null;
	private View[] option=null;
	private View back=null;
	private Theme theme=null;
	private FileEMOptionModule opModule=null;
	private static int count=3;
	@Deprecated
	@SuppressWarnings("unused")
	private FileEM(){
		
	}
	public FileEM(String type){
		this.setType(type);
		this.setName(" 文 件 ");
		
	}
	public void init(){
		theme=Theme.getTheme();
		opModule=new FileEMOptionModule(this);
		
		loadMenuBg();
		
		loadOption();
	}
	@Override
	public void run(){
		
	}
	public Module getMenu(){
		if(menu==null){
			init();
		}
		return menu;
	}
	private void loadMenuBg(){
		menu=new Module();
		menu.setBackground(theme.getBgColor());
		menu.setBounds(-400,100,400,HEIGHT);
		menu.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				/**
				 * 覆盖主面板点击事件
				 */
			}
		});
	}
	public void display(){
		load();
		menu.setLocation(0,menu.getY());
	}
	public void hide(){
		menu.setLocation(-menu.getWidth(),menu.getY());
	}
	public void load(){
		menu.removeAll();
		loadOption();
		menu.repaint();
	}
	private void loadOption(){
		count=FileEMOptionModule.getCount()+1;
		group=new View("group");
		option=new View[count];
		int h=0;
		for(int i=0;i<count;i++){
			option[i]=new View(String.valueOf(i));
			option[i].setBounds(0,h,OPTION_WIDTH,OPTION_HEIGHT);
			option[i].addMouseListener(new MouseAdapter(){
				/**
				 * 设置鼠标悬浮和点击时选项颜色变化
				 */
				View view=null;
				int index;
				@Override
				public void mouseEntered(MouseEvent e){
					view=(View)e.getSource();
					index=Integer.parseInt(view.getType());
					view.setBackground(theme.getFloatColor());
					view.repaint();
				}
				@Override 
				public void mousePressed(MouseEvent e){
					view.setBackground(theme.getClickBg());
					if(index>=1){
						opModule.setTextColor(index-1, theme.getFontClickColor());
					}
					view.repaint();
				}
				@Override
				public void mouseReleased(MouseEvent e){
					view.setBackground(theme.getFloatColor());
					if(index>=1){
						opModule.setTextColor(index-1, theme.getFontColor());
					}
					view.repaint();
					response(index);
				}
				@Override
				public void mouseExited(MouseEvent e){
					view.setBackground(theme.getBgColor());
					view.repaint();
				}
			});
			group.add(option[i]);
			h+=OPTION_HEIGHT;
		}

		loadBack();
		/**
		 * 因为此选项特殊，不关联在选项模块里，所以单独加载在EM里面
		 */
		option[0].add(back);
		group.setBounds(0,0,OPTION_WIDTH,h);
		for(int i=0;i<count-1;i++){
			option[i+1].add(opModule.getOption(i));
		}
		menu.add(group);
	}
	private void loadBack(){
		/**
		 * 因为此选项特殊，不关联在选项模块里，所以单独加载在EM里面
		 */
		back=new View("back");
		back.setBounds(0,0,120,OPTION_HEIGHT);
		back.add(Img.getImg("sources/img/back.png",50,10,OPTION_HEIGHT-20,OPTION_HEIGHT-20));
	}
	private void response(int index){
		switch(index){
			case 0:
				option[0].setState(MouseEnum.EXITED.getState());
				option[0].setBackground(theme.getBgColor());
				hide();
				break;
			default:
				opModule.loadOption(index-1);
		}
	}
	
	
	
	
	/*
	 * 以下方法为EM覆写方法，无具体实现，无使用
	 */
	@Override
	public Module getTabModule() {
		return null;
	}
	@Override
	public Module getEditModule() {
		return null;
	}
	@Override
	public Module getStateBarModule() {
		return null;
	}
	@Override
	public String getData() {
		return null;
	}
}
