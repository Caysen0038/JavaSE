package im.entity;

public class Field{
	/**
	 * �������ڴ洢��¼�е����ֶε���Ϣ
	 * @param��fieldName �ֶ�����
	 * @param value �ֶ�����
	 * @param mean �ֶ�����ΪӢ�ģ��˴�Ϊ�ֶ����Ƶ����ĺ���
	 * @param pk ����
	 * @param fk ���
	 * @param nn �Ƿ�Ϊnot null����
	 * @param DB �洢�����ݿ�
	 * @param index ��¼�е��ֶ�������
	 * @param type �ֶ�����
	 * @param table	�洢�����ݱ�
	 */
	private String fieldName=null;
	private String value=null;
	private String mean=null;
	private boolean pk=false;
	private boolean fk=false;
	private boolean nn=false;
	private String DB=null;
	private String type=null;
	private String table=null;
	private int index;
	
	public Field(){
		this(null,null,null);
	}
	public Field(String value){
		this(null,value,null);
	}
	public Field(String fieldName,String value){
		this(fieldName,value,null);
	}
	public Field(String fieldName,String value,String mean){
		this.fieldName=fieldName;
		this.value=value;
		this.mean=mean;
	}
	public String getFieldName(){
		return this.fieldName;
	}
	public String getValue(){
		return this.value;
	}
	public void setFieldName(String fieldName){
		this.fieldName=fieldName;
	}
	public void setValue(String value){
		this.value=value;
	}
	public boolean isPk() {
		return pk;
	}
	public void setPk(boolean pk) {
		this.pk = pk;
	}
	public boolean isNn() {
		return nn;
	}
	public void setNn(boolean nn) {
		this.nn = nn;
	}
	public String getDB() {
		return DB;
	}
	public void setDB(String dB) {
		DB = dB;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getMean() {
		return mean;
	}
	public void setMean(String mean) {
		this.mean = mean;
	}
	public boolean isFk() {
		return fk;
	}
	public void setFk(boolean fk) {
		this.fk = fk;
	}
}
