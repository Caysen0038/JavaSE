package im.util.enums;

public enum ThemeEnum {
	/**
	 * 与config文件中theme设置各个属性节点名称对应
	 */
	NAME("theme-name","主题名称"),BG("bg-color","背景颜色")
		,FONT("font-color","字体颜色"),FLOAT("float-color","鼠标悬浮颜色")
		,FONTCLICK("font-click","字体点击颜色"),BGCLICK("bg-click","背景点击颜色")
		,TYPE("font-type","字体");
	private String name;
	private String mean;
	private ThemeEnum(String name,String mean){
		this.name=name;
	}
	public String getName(){
		return this.name;
	}
	public String getMean(){
		return this.mean;
	}
}
