package im.entity;
public class Record implements Entity{
	/**
	 * 对应数据表中一整条记录
	 * @param table 关联的数据表
	 * @param db 关联的数据库
	 * 
	 * 因为采用的此种形式存储和读取数据库数据，为保证其可行性，现约定将数据库中所有字段类型均设为varchar类型
	 * 读取后可根据应用需要转换类型即可
	 */
	private Field[] field=null;
	private final int DEFAULT_SIZE=1;
	private String table=null;
	private String db=null;
	@SuppressWarnings("unused")
	private Record(){
		//禁止使用，必须传入size参数初始化field长度
	}
	public Record(int size){
		field=new Field[size>1?size:DEFAULT_SIZE];
	}
	public Record(Field[] field){
		this(field.length);
		setField(field);
	}
	public boolean setField(Field[] field){
		/*
		 * 将指定的field数组复制至本对象中的field数组
		 */
		if (field.length!=this.field.length){
			return false;
		}
		System.arraycopy(field, 0, this.field, 0, field.length);
		return true;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getDb() {
		return db;
	}
	public void setDb(String db) {
		this.db = db;
	}
	public Field getField(int index){
		/*
		 * 返回指定序列的field成员
		 */
		return field[index];
	}
	public Field getField(String fieldName){
		for(int i=0,n=field.length;i<n;i++){
			if(field[i].getFieldName().equals(fieldName)){
				return field[i];
			}
		}
		return null;
	}
	public void setField(Field field,int index){
		/*
		 * 将指定字段关联之指定的field数组成员
		 */
		if(index>this.field.length||index<0){
			return;
		}
		this.field[index]=field;
	}
	public int getSize(){
		return this.field.length;
	}
	public Field[] getFields(){
		return this.field;
	}
	public String findField(String fieldName){
		/*
		 * 根据字段名查找字段值
		 */
		if(fieldName==null||fieldName.length()==0){
			return null;
		}
		for(int i=0,n=field.length;i<n;i++){
			if(field[i].getFieldName().equals(fieldName)){
				return field[i].getValue();
			}
		}
		return null;
	}
	public boolean setValueByFieldName(String fieldName,String value){
		/*
		 * 根据字段名存储字段数据
		 * @param fiedName 字段名，根据字段名查询字段存储value
		 */
		for(int i=0,n=field.length;i<n;i++){
			if(field[i].getFieldName().equals(fieldName)){
				field[i].setValue(value);
				return true;
			}
		}
		return false;
	}
}
