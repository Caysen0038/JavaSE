package im.util.enums;

public enum DBInfo {
	/**
	 * ��config�й���DB���ýڵ����ƶ�Ӧ
	 */
	DRIVER("db-driver","������ַ"),ADDRESS("db-address","���ݿ��ַ")
		,PORT("db-port","�˿�"),NAME("db-name","���ݿ�����")
		,USERNAME("db-username","����Ա�û���"),USERPASSWORD("db-userpassword","����Ա����");
	private String name;
	private String mean;
	private DBInfo(String name,String mean){
		this.name=name;
		this.mean=mean;
	}
	public String getName(){
		return this.name;
	}
	public String getMean(){
		return this.mean;
	}
}
