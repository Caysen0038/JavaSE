package im.util.view;
import java.awt.*;
import java.util.Map;

import im.util.Config;
import im.util.enums.ThemeEnum;
public class Theme {
	/**
	 * ������⣬ֱ�ӻ��config�ļ��е�Ĭ������
	 * �û���ֱ��ͨ����������������Ĭ������
	 * ������ӹ��ܣ��û��Զ�������
	 * 
	 */
	private Map<String,String> info=null;
	private static Theme theme=new Theme();
	private Theme(){
		this.info=Config.getDOM().getTheme();
	}
	public static Theme getTheme(){
		return theme;
	}
	public String getName(){
		return info.get("theme-name");
	}
	public Color getBgColor(){
		String[] rgb=info.get(ThemeEnum.BG.getName()).split(",");
		return new Color(Integer.parseInt(rgb[0]),Integer.parseInt(rgb[1]),Integer.parseInt(rgb[2]));
	}
	public Color getFontColor(){
		String[] rgb=info.get(ThemeEnum.FONT.getName()).split(",");
		return new Color(Integer.parseInt(rgb[0]),Integer.parseInt(rgb[1]),Integer.parseInt(rgb[2]));
	}
	public String getFontType(){
		return info.get(ThemeEnum.TYPE.getName());
	}
	public Color getFloatColor(){
		String[] rgb=info.get(ThemeEnum.FLOAT.getName()).split(",");
		return new Color(Integer.parseInt(rgb[0]),Integer.parseInt(rgb[1]),Integer.parseInt(rgb[2]));
	}
	public Color getFontClickColor(){
		String[] rgb=info.get(ThemeEnum.FONTCLICK.getName()).split(",");
		return new Color(Integer.parseInt(rgb[0]),Integer.parseInt(rgb[1]),Integer.parseInt(rgb[2]));
	}
	public Color getClickBg(){
		String[] rgb=info.get(ThemeEnum.BGCLICK.getName()).split(",");
		return new Color(Integer.parseInt(rgb[0]),Integer.parseInt(rgb[1]),Integer.parseInt(rgb[2]));
	}
}
