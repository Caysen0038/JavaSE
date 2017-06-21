package im.view.module;

import javax.swing.*;

import im.util.enums.ThemeEnum;
import im.util.view.Theme;

import java.awt.Color;
import java.awt.Font;
import java.util.*;
public class ThemeTemplet extends Module{

	/**
	 * 主题显示模板，包括显示主题的主要颜色和主题名称
	 */
	private static final long serialVersionUID = -8486724395141751463L;
	public static final int HEIGHT=130;
	public static final int WIDTH=100;
	private View color=null;
	private JLabel text=null;
	private Map<String,String> info=null;
	private Theme theme=null;
	@SuppressWarnings("unused")
	private ThemeTemplet(){
		
	}
	public ThemeTemplet(Map<String,String> info){
		setInfo(info);
		init();
	}
	public void setInfo(Map<String,String> info){
		this.info=info;
	}
	private void init(){
		theme=Theme.getTheme();
		this.setSize(WIDTH,HEIGHT);
		this.setBackground(Color.WHITE);
		loadTemplet();
	}
	private void loadTemplet(){
		color=new View("color");
		color.setBounds(0,0,WIDTH,80);
		color.setBorder(BorderFactory.createLineBorder(theme.getBgColor(),2));
		String[] str=info.get(ThemeEnum.BG.getName()).split(",");
		//System.out.println(info.get(ThemeEnum.NAME.getName())+"  "+info.get(ThemeEnum.BG.getName()));
		Color temp=new Color(Integer.parseInt(str[0]),Integer.parseInt(str[1]),Integer.parseInt(str[2]));
		color.setBackground(temp);
		text=new JLabel(info.get(ThemeEnum.NAME.getName()));
		text.setBorder(BorderFactory.createLineBorder(theme.getBgColor(),2));
		text.setHorizontalAlignment(JLabel.CENTER);
		text.setFont(new Font(theme.getFontType(),Font.BOLD,20));
		text.setBounds(0,color.getHeight(),WIDTH,HEIGHT-color.getHeight());
		this.add(color);
		this.add(text);
	}
	public void load(){
		this.removeAll();
		init();
	}
	public String getName(){
		return info.get(ThemeEnum.NAME.getName());
	}
	public void edit(){
		
	}
}
