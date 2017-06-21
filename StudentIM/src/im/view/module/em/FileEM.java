package im.view.module.em;

import java.awt.event.*;

import im.util.enums.MouseEnum;
import im.util.view.*;
import im.view.module.*;
public class FileEM extends EM implements Runnable{
	/**
	 * 
	 * ��EMģ��Ϊ������ģ�飬�����Բ�ǿ������Ӧ��ֻ�����һ����ģ��
	 * ���Ҹ�ģ���µ����й���ģ���Ϊ����ģ�飬�޷�����
	 * 
	 * @param tab ���ܲ˵���ǩ�����û�ѡ��ģ���¸��ֹ���
	 * @param orerate ��Ҫ�༭ģ�飬��ɸ��ֹ��ܲ���
	 * @param stateBar ģ���е�״̬����Ϊģ�鹦�ܲ�ͬ��״̬��ʵ�ֲ�ͬ�����Թ�����ģ����������봰��
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
		this.setName(" �� �� ");
		
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
				 * ������������¼�
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
				 * ������������͵��ʱѡ����ɫ�仯
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
		 * ��Ϊ��ѡ�����⣬��������ѡ��ģ������Ե���������EM����
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
		 * ��Ϊ��ѡ�����⣬��������ѡ��ģ������Ե���������EM����
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
	 * ���·���ΪEM��д�������޾���ʵ�֣���ʹ��
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
