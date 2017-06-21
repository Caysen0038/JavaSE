package im.util.enums;

public enum DBInfo {
	/**
	 * 与config中关于DB配置节点名称对应
	 */
	DRIVER("db-driver","驱动地址"),ADDRESS("db-address","数据库地址")
		,PORT("db-port","端口"),NAME("db-name","数据库名称")
		,USERNAME("db-username","管理员用户名"),USERPASSWORD("db-userpassword","管理员密码");
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
