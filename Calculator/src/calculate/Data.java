
package calculate;
import java.math.*;
public class Data{
	private BigDecimal[] data;
	private Operator sign;
	private int top=0;
	public Data(int size){
		data=new BigDecimal[size];
		sign=new Operator(size);
	}
	public void putData(BigDecimal num){
		data[top++]=num;
	}
	public BigDecimal getTop(){
		if(top>0){
			return data[top-1];
		}else{
			return new BigDecimal(0);
		}
	}
	public BigDecimal outTop(){
		if(top>0){
			return data[--top];
		}else{
			return new BigDecimal(0);
		}
	}
	public void putSign(int sign){
		this.sign.putOperator(sign);
	}
	public int getSign(){
		return this.sign.getTop();
	}
	public int outSign(){
		return this.sign.outTop();
	}
	public boolean isDataNull(){
		return top==0;
	}
	public boolean isSignNull(){
		return sign.isNull();
	}
}
