package calculate;

public class Operator{
	private int[] operator;
	private int top=0;
	public Operator(int size){
		this.operator=new int[size];
	}
	public void putOperator(int num){
		operator[top++]=num;
	}
	public int getTop(){
		if(top>0){
			return operator[top-1];
		}else{
			return 0;
		}
	}
	public int outTop(){
		if(top>=0){
			return operator[--top];
		}else{
			return 0;
		}
	}
	public boolean isNull(){
		return top==0;
	}
}
