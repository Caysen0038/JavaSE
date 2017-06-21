package im.view.module;

import static im.util.view.Transparent.TRANSPARENT;

import java.awt.*;
import javax.swing.JLabel;

import im.util.view.Theme;
import im.view.module.em.FileEM;

public class FileEMOptionModule extends Module{
	/**
	 * 此模块属于FileEM定制模块，泛用性不强
	 */
	private static final long serialVersionUID = -7623986551771804656L;
	private FileEM em=null;
	private Module menuCase=null;
	private JLabel[] option=null;
	private Module[] opModule=null;
	private Theme theme=null;
	private static int count=3;
	@Deprecated
	@SuppressWarnings("unused")
	private FileEMOptionModule(){
		/**
		 * 禁止使用，必须传入容器
		 */
	}
	public FileEMOptionModule(FileEM em){
		/**
		 * @param em FileEM定制，所以传入的容器也为fielEM
		 */
		this.em=em;
		
		init();
	}
	private void init(){
		theme=Theme.getTheme();
		option=new JLabel[count];
		loadOpModule();
		Font font=new Font(theme.getFontType(),Font.PLAIN,22);
		for(int i=0;i<count;i++){
			option[i]=new JLabel(opModule[i].getName());
			option[i].setBounds(50,0,FileEM.OPTION_WIDTH,FileEM.OPTION_HEIGHT);
			option[i].setBackground(TRANSPARENT);
			option[i].setFont(font);
			option[i].setForeground(theme.getFontColor());
		}
	}
	private void loadOpModule(){
		opModule=new Module[count];
		opModule[0]=new SetModule(em);
		opModule[1]=new ThemeSetModule(em);
		opModule[2]=new QuitModule(em);
		
	}
	public void display(int index){
		
	}
	public void loadOption(int index){
		menuCase=em.getMenu();
		menuCase.removeAll();
		opModule[index].load();
		menuCase.add(opModule[index]);
		menuCase.repaint();
	}
	public JLabel getOption(int index){
		return option[index];
	}
	public static int getCount(){
		return count;
	}
	public void setTextColor(int index,Color color){
		option[index].setForeground(color);
	}
	
}
