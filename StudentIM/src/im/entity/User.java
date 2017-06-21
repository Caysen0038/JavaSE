package im.entity;

import im.service.proxy.LoginProxy;
public class User implements Entity{
	/**
	 * 使用单例贯穿于整个软件运行周期
	 * 类中setter方法权限收拢为private，进制外部非法修改数据
	 * 软件的权限加载依靠类中属性，setter属性会修改当前用户的软件操作权限
	 */
	private String id=null;
	private String type=null;
	private int level=0;
	private String lastTime=null;
	private String password=null;
	private static User user=new User();
	static{
		new LoginProxy().Login(new String[]{"admin","admin"});
	}
	private User(){
		
	}
	public static User getUser(){
		return user;
	}
	public void setInfo(Record record){
		setId(record.findField("id"));
		setType(record.getTable());
		setLastTime(record.findField("last_time"));
		setPassword(record.findField("password"));
	}
	public String getPassword() {
		return password;
	}
	private void setPassword(String password) {
		this.password = password;
	}
	public String getId() {
		return id;
	}
	public String getTypeMean(){
		if("student".equals(type)){
			return "学生";
		}else if("teacher".equals(type)){
			return "老师";
		}else if("admin".equals(type)){
			return "管理员";
		}else{
			return type;
		}
	}
	private void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public int getLevel(){
		return this.level;
	}
	private void setLevel(int level){
		this.level=level;
	}
	private void setType(String type) {
		if(this.type!=null){
			/**
			 * 运行中不允许篡改类型，类型关系到用户权限
			 * 只有重新登录才能修改权限
			 */
			return;
		}
		this.type = type;
		/**
		 * 设置用户等级
		 */
		setLevel(type.equals("student")?1:type.equals("teacher")?2:3);
	}
	public String getLastTime() {
		return lastTime;
	}
	private void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
	public boolean isEmpty(){
		/**
		 * 仅判断ID是否为null
		 */
		if(user.getId()==null||"".equals(user.getId())){
			return true;
		}
		return false;
	}
	
}
