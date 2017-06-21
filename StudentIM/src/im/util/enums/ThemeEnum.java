package im.util.enums;

public enum ThemeEnum {
	/**
	 * ��config�ļ���theme���ø������Խڵ����ƶ�Ӧ
	 */
	NAME("theme-name","��������"),BG("bg-color","������ɫ")
		,FONT("font-color","������ɫ"),FLOAT("float-color","���������ɫ")
		,FONTCLICK("font-click","��������ɫ"),BGCLICK("bg-click","���������ɫ")
		,TYPE("font-type","����");
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
