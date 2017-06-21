package im.entity;

public class Person implements Entity{
	private String id=null;
	private String name=null;
	private String type=null;
	public Person(){
		this(null,null,null);
	}
	public Person(String id){
		this(id,null,null);
	}
	public Person(String id,String type){
		this(id,type,null);
	}
	public Person(String id,String type,String name){
		this.id=id;
		this.name=name;
		this.type=type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
