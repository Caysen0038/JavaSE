package im.util.view;
import java.awt.*;
import java.util.Map;

import im.util.Config;
import im.util.enums.ThemeEnum;
public class Theme {
	/**
	 * 软件主题，直接获得config文件中的默认主题
	 * 用户可直接通过更改主题来设置默认主题
	 * 后期添加功能：用户自定义主题
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
