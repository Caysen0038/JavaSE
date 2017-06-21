package im.view.module.em;

import java.awt.Color;

import im.util.view.Img;
import im.util.view.Theme;
import im.view.module.Module;
import im.view.module.em.function.BaseEMFactory;

public class BaseEM extends EM{
	/**
	 * @param tab ���ܲ˵���ǩ�����û�ѡ��ģ���¸��ֹ���
	 * @param orerate ��Ҫ�༭ģ�飬��ɸ��ֹ��ܲ���
	 * @param stateBar ģ���е�״̬����Ϊģ�鹦�ܲ�ͬ��״̬��ʵ�ֲ�ͬ�����Թ�����ģ����������봰��
	 * @param admin �����û�Ȩ�ޣ������û�Ȩ���ṩ��ͬ�Ĺ���
	 * 
	 */
	private static final long serialVersionUID = 8878792872361381670L;
	private Module tab=null;
	private Module operate=null;
	private Module stateBar=null;
	private Module[] function=null;
	/**
	 * @param count ���ƹ�������
	 */
	private int count=2;
	@Deprecated
	@SuppressWarnings("unused")
	private BaseEM(){
		
	}
	public BaseEM(String type){
		this.setType(type);
		this.setName(" �������� ");
	}
	public void init(){
		
	}
	private void loadTab(){
		tab=new Module(this.getType());
		tab.setBounds(0,0,WIDTH,TAB_HEIGHT);
		tab.setBackground(Color.DARK_GRAY);
		tab.add(Img.getImg("sources/img/base.png", 0, 0, tab.getWidth(), tab.getHeight()));
		loadFunction();
		
	}
	private void loadOperate(){
		operate=new Module(this.getType());
		operate.setBounds(0,0,WIDTH,EDIT_HEIGHT);
		operate.setBackground(Color.WHITE);
	}
	private void loadStateBar(){
		stateBar=new Module(this.getType());
		stateBar.setBounds(0,0,WIDTH,STATE_HEIGHT);
		stateBar.setBackground(Theme.getTheme().getBgColor());
	}
	private void loadFunction(){
		function=new Module[count];
		int ytemp=15,xtemp=30;
		for(int i=0;i<count;i++){
			/**
			 * ͨ����̬������ù���ʵ��
			 */
			function[i]=BaseEMFactory.getFunction(i,this);
			function[i].setLocation(xtemp, ytemp);
			xtemp+=function[i].getWidth();
			tab.add(function[i]);
		}
		/**
		 * ��ʼ����Ĭ�ϼ��ص�һ���ҳ��
		 */
		function[0].load();
		tab.repaint();
	}
	@Override
	public void load(){
		
	}
	@Override
	public Module getTabModule() {
		if(this.tab==null){
			loadTab();
		}
		return this.tab;
	}
	@Override
	public Module getEditModule() {
		if(this.operate==null){
			loadOperate();
		}
		return this.operate;
	}
	@Override
	public Module getStateBarModule() {
		if(this.stateBar==null){
			loadStateBar();
		}
		return this.stateBar;
	}
	@Override
	public String getData() {
		// TODO Auto-generated method stub
		return null;
	}
}
