package im.entity;
public class Record implements Entity{
	/**
	 * ��Ӧ���ݱ���һ������¼
	 * @param table ���������ݱ�
	 * @param db ���������ݿ�
	 * 
	 * ��Ϊ���õĴ�����ʽ�洢�Ͷ�ȡ���ݿ����ݣ�Ϊ��֤������ԣ���Լ�������ݿ��������ֶ����;���Ϊvarchar����
	 * ��ȡ��ɸ���Ӧ����Ҫת�����ͼ���
	 */
	private Field[] field=null;
	private final int DEFAULT_SIZE=1;
	private String table=null;
	private String db=null;
	@SuppressWarnings("unused")
	private Record(){
		//��ֹʹ�ã����봫��size������ʼ��field����
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
		 * ��ָ����field���鸴�����������е�field����
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
		 * ����ָ�����е�field��Ա
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
		 * ��ָ���ֶι���ָ֮����field�����Ա
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
		 * �����ֶ��������ֶ�ֵ
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
		 * �����ֶ����洢�ֶ�����
		 * @param fiedName �ֶ����������ֶ�����ѯ�ֶδ洢value
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
