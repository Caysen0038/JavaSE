package im.entity;

import im.service.proxy.LoginProxy;
public class User implements Entity{
	/**
	 * ʹ�õ����ᴩ�����������������
	 * ����setter����Ȩ����£Ϊprivate�������ⲿ�Ƿ��޸�����
	 * �����Ȩ�޼��������������ԣ�setter���Ի��޸ĵ�ǰ�û����������Ȩ��
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
			return "ѧ��";
		}else if("teacher".equals(type)){
			return "��ʦ";
		}else if("admin".equals(type)){
			return "����Ա";
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
			 * �����в�����۸����ͣ����͹�ϵ���û�Ȩ��
			 * ֻ�����µ�¼�����޸�Ȩ��
			 */
			return;
		}
		this.type = type;
		/**
		 * �����û��ȼ�
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
		 * ���ж�ID�Ƿ�Ϊnull
		 */
		if(user.getId()==null||"".equals(user.getId())){
			return true;
		}
		return false;
	}
	
}
