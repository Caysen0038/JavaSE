package calculate;

public class Bracket{
	private int[] bracket;
	private int top=0;
	public Bracket(int size){
		bracket=new int[size];
	}
	public void putBracket(int sign){
		this.bracket[top++]=sign;
	}
	public int getTop(){
		if(top>=0){
			return bracket[top-1];
		}else{
			return 0;
		}
	}
	public int outTop(){
		if(top>=0){
			return bracket[--top];
		}else{
			return 0;
		}
	}
	public boolean isNull(){
		return top==0;
	}
}
